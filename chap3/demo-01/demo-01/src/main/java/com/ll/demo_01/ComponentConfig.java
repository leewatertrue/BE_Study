package com.ll.demo_01;

import org.springframework.context.annotation.*;

@Configuration
public class ComponentConfig {
	@Bean
	public ComponentC componentC() {
		return new ComponentC();
	}

	@Bean
	public ComponentD componentD() {
		return new ComponentD();
	}

	@Bean
	public ComponentE componentE() {
		return new ComponentE();
	}
}
