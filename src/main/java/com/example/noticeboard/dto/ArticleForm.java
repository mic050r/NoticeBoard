package com.example.noticeboard.dto;

import com.example.noticeboard.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 새 어노테이션 추가 -> 생성자 삭제 ok
@ToString // 새 어노테이션 추가 -> toString() 삭제 ok
public class ArticleForm {
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
