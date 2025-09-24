package com.ll.demo03.global.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@Configuration
public class AppConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} // 비밀번호 암호화

	@Getter
	public static ObjectMapper objectMapper;

	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Getter
	private static String jwtSecretKey;

	@Value("${custom.secret.jwt.secretKey}")
	public void setJwtSecretKey(String jwtSecretKey) {
		this.jwtSecretKey = jwtSecretKey;
	}

	@Getter
	private static long accessTokenExpirationSec;

	@Value("${custom.accessToken.expirationSec}")
	public void setJwtSecretKey(long accessTokenExpirationSec) {
		this.accessTokenExpirationSec = accessTokenExpirationSec;
	}
}
