package com.example.noticeboard.controller;

import com.example.noticeboard.dto.ArticleForm;
import com.example.noticeboard.entity.Article;
import com.example.noticeboard.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 컨트롤러 선언
public class ArticleCotroller {
    @Autowired // 스프링 부트가 미리 생성해 높은 리파지터리 객체 주입(DI) -> 의존성 주입
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new") // URL 요청 접수
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { // 폼 데이터를 DTO로 받기
        System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        System.out.println(saved.toString());

        return "";
    }
}
