package com.ll.demo03.domain.auth.auth.service;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.global.app.AppConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthTokenService {
	public String genToken(Member member, long expireSeconds) {
		Claims claims = Jwts
			.claims()
			.add("id", member.getId())
			.add("username", member.getUsername())
			.build();

		Date issuedAt = new Date();
		Date expiration = new Date(issuedAt.getTime() + 1000 * expireSeconds);

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(issuedAt)
			.setExpiration(expiration)
			.signWith(SignatureAlgorithm.HS256, AppConfig.getJwtSecretKey())
			.compact();
	}

	public Map<String, Object> getDataFrom(String token) {
		Claims payload = Jwts.parser()
			.setSigningKey(AppConfig.getJwtSecretKey())
			.build()
			.parseClaimsJws(token)
			.getPayload();

		return Map.of(
			"id", payload.get("id", Integer.class),
			"username", payload.get("username", String.class)
		);
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(AppConfig.getJwtSecretKey()).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
