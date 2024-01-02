package com.example.noticeboard.api;

import com.example.noticeboard.dto.ArticleForm;
import com.example.noticeboard.entity.Article;
import com.example.noticeboard.repository.ArticleRepository;
import com.example.noticeboard.service.ArticleService;
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
    private ArticleService articleService;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    // GET
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) { // 반환형 수정
        Article created = articleService.create(dto);
        return (created != null) ? // 생성하면 정상, 실패하면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                 @RequestBody ArticleForm dto){
        Article updated = articleService.update(id,dto);
        return (updated != null) ? // 생성하면 정상, 실패하면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ? // 생성하면 정상, 실패하면 오류 응답
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction=test") // 여러 게시글 생성 요청 접수
    public ResponseEntity<List<Article>> transactionTest
            (@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos); // 서비스 호출
        return (createdList != null) ? // 생성하면 정상, 실패하면 오류 응답
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } // transactionTest() 메소드 정의
}
