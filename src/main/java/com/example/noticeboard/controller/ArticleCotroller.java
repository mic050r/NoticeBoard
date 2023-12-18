package com.example.noticeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러 선언
public class ArticleCotroller {

    @GetMapping("/articles/new") // URL 요청 접수
    public String newArticleForm() {
        return "articles/new";
    }
}
