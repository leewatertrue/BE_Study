package com.ll.demo03.domain.member.member.entity;

import static lombok.AccessLevel.PROTECTED;

import com.ll.demo03.global.jpa.entity.BaseTime;

import jakarta.persistence.Entity;
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
public class Member extends BaseTime {
	private String username;
	private String password;
	private String nickname;
}

