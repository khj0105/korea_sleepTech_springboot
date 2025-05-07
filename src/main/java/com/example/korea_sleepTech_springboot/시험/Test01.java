//1. Spring Boot의 주된 장점은 무엇입니까?
//UI 개발에 최적화되어 있다.
//JSP만을 사용해야 한다.
//복잡한 설정 없이 빠른 애플리케이션 개발이 가능하다.
//빌드를 수동으로 해야 한다.
//정답: 3
//        2. 아래 중 Spring Boot 애플리케이션 실행 시 필수로 포함되어야 하는 클래스에 붙는 애너테이션은?
//@Controller
//@RestController
//@Service
//@SpringBootApplication
//정답: 4
//        3. @Autowired 애너테이션의 역할은 무엇입니까?
//클래스를 Bean으로 등록한다.
//필드 값을 초기화한다.
//의존성을 자동 주입한다.
//HTTP 요청을 처리한다.
//        정답: 3
//        4. @RestController는 어떤 애너테이션의 조합입니까?
//@Controller + @RequestMapping
//@Controller + @ResponseBody
//@Service + @ResponseBody
//@Component + @RequestBody
//정답: 2
//        5. Spring Boot에서 요청 URL과 메서드를 연결하기 위한 애너테이션은?
//@Autowired
//@GetMapping, @PostMapping
//@Service
//@Value
//정답: 2
//        6. Spring Boot에서 application.properties 또는 application.yml 파일의 목적은?
//HTML 작성
//데이터베이스 직접 연결
//프로젝트 설정 및 환경 구성
//REST API 테스트
//정답: 3
//        7. Spring Boot 애플리케이션에서 각 계층의 역할에 대한 설명으로 올바른 것은 무엇입니까?
//Controller는 데이터베이스와 직접 연결되어 데이터를 저장하거나 조회한다.
//Service는 사용자의 요청을 받아 HTTP 응답을 반환한다.
//Repository는 비즈니스 로직을 처리하고 클라이언트 응답을 생성한다.
//DTO는 계층 간 데이터 전달을 위한 객체이며, Entity는 데이터베이스 테이블과 매핑된다.
//정답: 4
//        8. 아래 중 의존성 주입 방식이 아닌 것은?
//생성자 주입
//필드 주입
//setter 주입
//객체 직접 생성
//정답: 4
//        9. Spring Boot 프로젝트에서 MySQL과 같은 DB와 연결하기 위해 사용하는 JPA의 기본 인터페이스는 무엇입니까?
//JdbcTemplate
//        CrudRepository
//JpaRepository
//        EntityManager
//정답: 2
//        10. Spring Boot에서 API 요청 본문을 객체로 받기 위한 애너테이션은?
//@ModelAttribute
//@PathVariable
//@RequestBody
//@ResponseBody
//정답: 3
//        11. Spring Boot에서 빈(Bean)을 수동으로 등록할 때 사용하는 애너테이션은 무엇입니까?
//정답: @Bean
//12. REST API에서 자주 사용되는 HTTP 메서드 중 '리소스 삭제'에 사용되는 것은 무엇입니까?
//정답: DELETE
//13. 컨트롤러에서 경로 변수(path variable)를 매핑하기 위한 애너테이션은 무엇입니까?
//정답: @PathVariable
//14. HTTP 요청의 쿼리 파라미터 값을 메서드의 매개변수에 바인딩할 때 사용하는 애너테이션은 무엇입니까?
//정답: @RequestParam
//15. @Service 애너테이션은 어떤 계층에서 사용하는가?
//정답: 비즈니스 로직 처리 계층
//16. @Repository는 어떤 용도로 사용하는가?
//정답: 데이터베이스 접근 계층
//17. Spring에서 데이터를 수정(Update)할 때 주로 사용하는 HTTP 메서드는 무엇입니까?
//정답: PUT 또는 PATCH
//18. Spring Boot에서 데이터베이스 연결 설정은 어느 파일에 작성합니까?
//정답: application.properties 또는 application.yml
//19. 다음 요구사항을 만족하는 Spring Boot의 Controller 코드를 작성하시오.
//<요구사항>
//GET 요청 /hello에 대해 “Hello, Spring Boot!” 문자열을 반환하는 API를 작성하시오.
//@RestController
//public class HelloController {
//
//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello, Spring Boot!";
//    }
//}
//​
//        20. 다음 요구사항을 만족하는 User 엔티티 클래스를 작성하시오.
//<요구사항>
//필드: id(Long), username(String), email(String)
//@Entity 사용
//기본 생성자와 모든 필드를 초기화하는 생성자 추가
//@Id, @GeneratedValue 사용
//import jakarta.persistence.*;
//
//@Entity
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String username;
//    private String email;
//
//    public User() {}
//
//    public User(String username, String email) {
//        this.username = username;
//        this.email = email;
//    }
//
//    // Getter/Setter 생략 가능
//}