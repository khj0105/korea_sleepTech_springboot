package com.example.korea_sleepTech_springboot.filter;

import com.example.korea_sleepTech_springboot.provider.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * === JwtAuthenticationFilter ===
 * : JWT 인증 필터
 * - 요청에서 JWT 토큰을 추출
 * - request의 header에서 토큰을 추출하여 검증
 *   >> 유효한 경우 security의 context에 인증 정보 설정
 *
 * cf) Spring Security이 OncePerRequestFilter를 상속받아 매 요청 시 실행
 * */
@Component // 스프링에서 해당 클래스를 관리하도록 지정, 의존성 주입
@RequiredArgsConstructor // 생성자 주입
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    /*
     * doFilterInternal
     * : 요청 시 헤더의 Authorization에서 JWT 토큰을 추출
     * - JwtProvider에서 만든 removeBearer()을 호출하여 토큰을 파싱
     * - JwtProvider를 사용하여 토큰 검증 및 "사용자 ID 추출"
     *
     * >> 추출한 사용자 ID를 바탕으로 SecurityContext에 인증 정보를 설정하는 메서드 호출
     * */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            // 요청 헤더에서 JWT 토큰 추출
            String authorizationHeader = request.getHeader("Authorization");

            // 헤더에서 토큰을 파싱하여 가져옴 ("Bearer "을 제거)
            String token = (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
                    ? jwtProvider.removeBearer(authorizationHeader) // "Bearer " 제거 메서드 호출(removeBearer())
                    : null;

            // 토큰이 없거나 유효하지 않으면 필터 체인을 타고 다음 단계로 이동
            if (token == null || !jwtProvider.isValidToken(token)) {
                // 토큰이 유효하지 않은 경우: 시큐리티 설정 없이 로직 실행
                filterChain.doFilter(request, response);
                // 이후의 필터를 거치지 않고 해당 메서드가 종료
                return;
            }

            // JWT 토큰이 유효한 경우 해당 토큰에서 사용자 ID를 추출
            String username = jwtProvider.getUsernameFromJwt(token);
            Set<String> roles = jwtProvider.getRolesFromJwt(token);

            // 추출한 사용자 ID를 SecurityContext에 인증 정보 설정
            setAuthenticationContext(request, username, roles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }

    /*
     * === setAuthenticationContext ===
     * : SecurityContext에 인증 정보를 설정하는 메서드
     *
     * - setAuthenticationContext()는 요청에서 username 값을 SecurityContext에 인증 정보로 설정
     *   : UsernamePasswordAuthenticationToken을 생성하고
     *       , 해당 토큰에 username 값을 넣어 인증 정보로 등록
     *
     * >> Spring Security는 SecurityContextHoler에 있는 인증 정보를 자동으로
     *       , 컨트롤러의 메서드에 주입시킬 수 있음 (@AuthenticationPrincipal)
     * */
    private void setAuthenticationContext(HttpServletRequest request, String username, Set<String> roles) {

        List<GrantedAuthority> authorities = roles.stream()
                // JWT 로그인 방식에서 사용자가 요청을 보낼 때
                // , 토큰에 담긴 roles 정보를 hasRole("")과 매칭시키기 위함
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());


        // 사용자 ID를 바탕으로 UsernamePasswordAuthenticationToken(인증 토큰) 생성
        // : 기본 설정 - 권한 없음
        AbstractAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(username, null, authorities/*AuthorityUtils.NO_AUTHORITIES*/);

        // 요청에 대한 세부 정보를 설정
        // : 생성된 인증 토큰에 요청의 세부 사항을 설정
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // 빈 SecurityContext 객체 생성 후, 인증 토큰 주입
        // : 사용자가 인증되었다는 정보를 담음
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authenticationToken);

        // SecurityContextHolder에 생성된 컨텍스트를 설정
        SecurityContextHolder.setContext(securityContext);
    }
}