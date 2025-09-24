package com.ll.demo03.global.rq;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.domain.member.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequestScope // rq의 생명주기를 변경. 내 요청이 들어올 때마다 하나씩 생긴다.
@RequiredArgsConstructor
public class Rq {
	private final HttpServletRequest req;
	private final HttpServletResponse resp;
	private final MemberService memberService;

	public Member getMember() {
		return memberService.getReferenceById(1L);
	}

	public String getCurrentUrlPath() {
		return req.getRequestURI();
	}

	public void setStatusCode(int statusCode) {
		resp.setStatus(statusCode);
	}
}
