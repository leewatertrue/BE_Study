package com.ll.demo03;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.*;

@Controller
public class SurlController {
	private List<Surl> surls = new ArrayList<>();
	private long surlsLastId;

	@GetMapping("/all")
	@ResponseBody
	public List<Surl> getAll() {
		return surls;
	}

	@GetMapping("/add")
	@ResponseBody
	public Surl add(String body, String url) {
		Surl surl = Surl
			.builder()
			.id(++surlsLastId)
			.body(body)
			.url(url)
			.build();

		surls.add(surl);

		return surl;
	}

	@GetMapping("/s/{body}/**") // **는 뭐든지 다 올 수 있음
	@ResponseBody
	public Surl add(
		@PathVariable String body,
		HttpServletRequest req
	) {
		String url = req.getRequestURI();

		if ( req.getQueryString() != null ) {
			url += "?" + req.getQueryString();
		} // 전체 url

		String[] urlBits = url.split("/", 4); // 4개 이상으로 나누기 방지

		System.out.println("Arrays.toString(urlBits) : " + Arrays.toString(urlBits));

		url = urlBits[3];

		Surl surl = Surl
			.builder()
			.id(++surlsLastId)
			.body(body)
			.url(url)
			.build();

		surls.add(surl);

		return surl;
	}

	@GetMapping("/g/{id}")
	//@ResponseBody을 제외한 상태로 앞에 redirect를 적용하면 url로 이동
	public String go(
		@PathVariable long id
	) {
		Surl surl = surls
			.stream()
			.filter(_surl -> _surl.getId() == id)
			.findFirst()
			.orElse(null);

		if ( surl == null ) throw new RuntimeException("%d번 URL을 찾을 수 없습니다.".formatted(id));
		// 함수 정지

		surl.increaseCount();

		return "redirect:" + surl.getUrl();
	}
}
