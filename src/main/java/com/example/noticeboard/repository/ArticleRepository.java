package com.example.noticeboard.repository;

import com.example.noticeboard.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// Article : 관리 대상 엔티티의 클래스 타입, Long : 관리 대상 엔티티의 대푯값 타입
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
