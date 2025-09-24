package com.ll.demo03.domain.surl.surl.dto;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;

import com.ll.demo03.domain.surl.surl.entity.Surl;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
@Getter
public class SurlDto {
	private long id;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private long authorId;
	private String authorName;
	private String body;
	private String url;
	private long count;

	public SurlDto(Surl surl) {
		this.id = surl.getId();
		this.createDate = surl.getCreateDate();
		this.modifyDate = surl.getModifyDate();
		this.authorId = surl.getAuthor().getId();
		this.authorName = surl.getAuthor().getName();
		this.body = surl.getBody();
		this.url = surl.getUrl();
		this.count = surl.getCount();
	}
}
