package com.ll.demo03.domain.member.member.dto;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;

import com.ll.demo03.domain.member.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
@Getter
public class MemberDto {
	private long id;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private String username;
	private String nickname;

	public MemberDto(Member member) {
		this.id = member.getId();
		this.createDate = member.getCreateDate();
		this.modifyDate = member.getModifyDate();
		this.username = member.getUsername();
		this.nickname = member.getNickname();
	}
}
