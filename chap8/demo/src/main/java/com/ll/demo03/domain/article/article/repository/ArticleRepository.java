package com.ll.demo03.domain.article.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ll.demo03.domain.article.article.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
