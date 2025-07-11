package com.example.korea_sleepTech_springboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* cf) 응답 DTO는 생성 시점에 모든 데이터가 완성되어야 반환!
*       >> 기본 생성자가 사실상 필요 없음
* 
* */
@Getter
@Builder
// @NoArgsConstructor
@AllArgsConstructor
public class PostListResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
}