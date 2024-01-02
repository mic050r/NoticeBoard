package com.example.noticeboard.dto;

import com.example.noticeboard.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 새 어노테이션 추가 -> 생성자 삭제 ok
@ToString // 새 어노테이션 추가 -> toString() 삭제 ok
@Getter
public class ArticleForm {
    private Long id; // id 필드 추가
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    public Article toEntity() {
        return new Article(id, title, content);
    }
}

