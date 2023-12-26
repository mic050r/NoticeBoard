package com.example.noticeboard.controller;

import com.example.noticeboard.dto.ArticleForm;
import com.example.noticeboard.entity.Article;
import com.example.noticeboard.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Slf4j // 로깅 기능을 위한 어노테이션 추가 -> Simple Logging Facade for Java의 약자
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
        log.info(form.toString()); // 로깅 코드 추가
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString()); // 로깅 코드 추가

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}") // 데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model) { // 매개변수로 id 받아오기
        log.info("id = " + id); // id를 잘 받았는지 확인하는 로그 찍기

        // 1. id를 조회하여 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){ // 매개변수로 DTO 받아오기
        log.info(form.toString());
        return "";
    }
}
