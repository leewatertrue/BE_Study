package com.ll.demo02;

import lombok.*;

@Getter
@Setter
@Builder
public class Todo {
	private long id;
	private String body;
}
