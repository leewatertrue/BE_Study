package com.ll.demo03;

import java.time.*;

import lombok.*;

@Getter
@Setter
@Builder
public class Surl {
	private long id;
	@Builder.Default
	private LocalDateTime createDate = LocalDateTime.now(); // 자동으로 최신값
	@Builder.Default
	private LocalDateTime modifyDate = LocalDateTime.now();
	private String body;
	private String url;

	@Setter(AccessLevel.NONE) // 외부에서 setter 막음
	private long count;

	public void increaseCount() {
		count++;
	}
}
