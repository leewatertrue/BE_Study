package com.ll.demo_01;

import org.springframework.stereotype.*;

import lombok.*;

@Component
@RequiredArgsConstructor // @autowired 생략 가능 (동일 기능)
public class ComponentA {
	private final ComponentB componentB;
	private final ComponentC componentC;
	private final ComponentC componentD;
	private final ComponentC componentE;

	public String action() {
		return "ComponentA action / " + componentB.getAction();
	}
}
