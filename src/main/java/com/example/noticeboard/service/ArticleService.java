package com.example.noticeboard.service;

import com.example.noticeboard.dto.ArticleForm;
import com.example.noticeboard.entity.Article;
import com.example.noticeboard.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service // 서비스 객체 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity(); // dto를 엔티티로 변환후 article에 저장
        if(article.getId() != null) return null;
        return articleRepository.save(article);
    }



    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity(); // dto를 엔티티로 변환
        log.info("id : {}, article: {}", id, article.toString());
        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()){
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id:{}, article{}", id, article.toString());
            return null;
        }
        // 4. 업데이트 및 정상 응답(200) 하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }
}
