package com.example.noticeboard.api;

import com.example.noticeboard.dto.ArticleForm;
import com.example.noticeboard.entity.Article;
import com.example.noticeboard.repository.ArticleRepository;
import com.sun.net.httpserver.HttpsServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // REST API 용 컨트롤러 선언
public class ArticleApiController {
    @Autowired // 게시글 리파지터리 주입
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    // GET
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                 @RequestBody ArticleForm dto){
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity(); // dto를 엔티티로 변환
        log.info("id : {}, article: {}", id, article.toString());
        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()){
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id:{}, article{}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4. 업데이트 및 정상 응답(200) 하기
        Article updated = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
}
