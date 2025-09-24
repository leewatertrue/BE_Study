package com.ll.demo03.domain.article.article.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ll.demo03.domain.article.article.entity.Article;
import com.ll.demo03.domain.article.article.repository.ArticleRepository;
import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.global.rsData.RsData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 클래스 단위로 트랜잭션 달기
public class ArticleService {
	private final ArticleRepository articleRepository;

	public long count() {
		return articleRepository.count();
	}

	// RsData로 감싸면 result 코드와 메시지를 넣을 수 있음
	@Transactional
	public RsData<Article> write(Member author, String title, String body) {
		Article article = Article
			.builder()
			.author(author)
			.title(title)
			.body(body)
			.build();

		articleRepository.save(article);

		return RsData.of("%d번 게시물이 작성되었습니다.".formatted(article.getId()), article);
	}

	@Transactional
	public void delete(Article article) {
		articleRepository.delete(article);
	}

	public Optional<Article> findById(long id) {
		return articleRepository.findById(id);
	}

	public List<Article> findAll() {
		return articleRepository.findAll();
	}
}
