package com.ll.demo03.global.rsData;

import static lombok.AccessLevel.PRIVATE;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.demo03.standard.dto.Empty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Spring Doc + openapi fetch 쓸 때 data 가 있으면 매끄럽게 잘 돌아간다

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@Getter
public class RsData<T> {
	public static final RsData<Empty> OK = of("200-1", "성공", new Empty());
	public static final RsData<Empty> FAIL = of("500-1", "실패", new Empty());

	@NonNull
	String resultCode; //200-1 (성공 케이스)

	@NonNull
	int statusCode; //200 (성공 케이스인데 정수화)

	@NonNull
	String msg; // 어떻게 성공했는지 메시지

	@NonNull
	T data;

	public static RsData<Empty> of(String msg) {
		return of("200-1", msg, new Empty());
	}

	public static <T> RsData<T> of(T data) {
		return of("200-1", "성공", data);
	}

	public static <T> RsData<T> of(String msg, T data) {
		return of("200-1", msg, data);
	}

	public static <T> RsData<T> of(String resultCode, String msg) {
		return of(resultCode, msg, (T) new Empty());
	}

	public static <T> RsData<T> of(String resultCode, String msg, T data) {
		int statusCode = Integer.parseInt(resultCode.split("-", 2)[0]);

		RsData<T> tRsData = new RsData<>(resultCode, statusCode, msg, data);

		return tRsData;
	}

	@NonNull
	@JsonIgnore
	public boolean isSuccess() {
		return getStatusCode() >= 200 && getStatusCode() < 400;
	}

	@NonNull
	@JsonIgnore
	public boolean isFail() {
		return !isSuccess();
	}

	public <T> RsData<T> newDataOf(T data) {
		return new RsData<>(resultCode, statusCode, msg, data);
	}
}
