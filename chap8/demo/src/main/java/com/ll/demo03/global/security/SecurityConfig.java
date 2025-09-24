package com.ll.demo03.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ll.demo03.global.rsData.RsData;
import com.ll.demo03.standard.util.Ut;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
	private final CustomAuthenticationFilter customAuthenticationFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorizeRequests ->
				authorizeRequests
					.requestMatchers(HttpMethod.POST, "/api/*/members", "/api/*/members/login")
					.permitAll()
					.requestMatchers("/h2-console/**")
					.permitAll()
					.requestMatchers("/actuator/**")
					.permitAll()
					.anyRequest() // 위에 있는 것 외 어떤 요청이든 다 인증되어야 함
					.authenticated()
			)
			.headers( // h2 콘솔
				headers ->
					headers.frameOptions(
						frameOptions ->
							frameOptions.sameOrigin()
					)
			)
			.csrf(
				csrf ->
					csrf.disable()
			)
			.formLogin( // 로그인 화면은 허용
				formLogin ->
					formLogin
						.permitAll()
			)
			.exceptionHandling(
				exceptionHandling -> exceptionHandling
					.authenticationEntryPoint(
						(request, response, authException) -> {
							response.setContentType("application/json;charset=UTF-8");
							response.setStatus(403);
							response.getWriter().write(
								Ut.json.toString(
									RsData.of("403-1", request.getRequestURI() + ", " + authException.getLocalizedMessage())
								)
							);
						}
					)
			)
			.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
