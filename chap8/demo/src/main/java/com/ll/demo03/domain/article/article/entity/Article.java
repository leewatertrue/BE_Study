package com.ll.demo03.domain.article.article.entity;

import static lombok.AccessLevel.PROTECTED;

import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.global.jpa.entity.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
@Getter
@Setter
public class Article extends BaseTime {
	private String title;
	@Column(columnDefinition = "TEXT")
	private String body;
	@ManyToOne
	private Member author;
}
