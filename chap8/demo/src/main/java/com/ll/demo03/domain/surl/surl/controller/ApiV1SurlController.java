package com.ll.demo03.domain.surl.surl.controller;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ll.demo03.domain.auth.auth.service.AuthService;
import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.domain.surl.surl.dto.SurlDto;
import com.ll.demo03.domain.surl.surl.entity.Surl;
import com.ll.demo03.domain.surl.surl.service.SurlService;
import com.ll.demo03.global.exceptions.GlobalException;
import com.ll.demo03.global.rq.Rq;
import com.ll.demo03.global.rsData.RsData;
import com.ll.demo03.standard.dto.Empty;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/surls")
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
@Tag(name = "ApiSurlController", description = "Surl CRUD 컨트롤러")
public class ApiV1SurlController {
	private final SurlService surlService;
	private final AuthService authService;
	private final Rq rq;


	@AllArgsConstructor
	@Getter
	public static class SurlAddReqBody {
		@NotBlank
		private String body;
		@NotBlank
		private String url;
	}

	@AllArgsConstructor
	@Getter
	public static class SurlAddRespBody {
		private SurlDto item;
	}

	@PostMapping("")
	@Transactional
	@Operation(summary = "생성")
	public RsData<SurlAddRespBody> add(
		@RequestBody @Valid SurlAddReqBody reqBody
	) {
		Member member = rq.getMember(); // 현재 브라우저로 로그인한 회원

		RsData<Surl> addRs = surlService.add(member, reqBody.body, reqBody.url);

		return addRs.newDataOf(
			new SurlAddRespBody(
				new SurlDto(addRs.getData())
			)
		);
	}


	@AllArgsConstructor
	@Getter
	public static class SurlGetRespBody {
		private SurlDto item;
	}

	@GetMapping("/{id}")
	@Operation(summary = "단건조회")
	public RsData<SurlGetRespBody> get(
		@PathVariable long id
	) {
		Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

		authService.checkCanGetSurl(rq.getMember(), surl);

		return RsData.of(
			new SurlGetRespBody(
				new SurlDto(surl)
			)
		);
	}


	@AllArgsConstructor
	@Getter
	public static class SurlGetItemsRespBody {
		private List<SurlDto> items;
	}

	@GetMapping("")
	@Operation(summary = "다건조회")
	public RsData<SurlGetItemsRespBody> getItems() {
		Member member = rq.getMember();

		List<Surl> surls = surlService.findByAuthorOrderByIdDesc(member);

		return RsData.of(
			new SurlGetItemsRespBody(
				surls.stream()
					.map(SurlDto::new)
					.toList()
			)
		);
	}


	@DeleteMapping("/{id}")
	@Transactional
	@Operation(summary = "삭제")
	public RsData<Empty> delete(
		@PathVariable long id
	) {
		Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

		authService.checkCanDeleteSurl(rq.getMember(), surl);

		surlService.delete(surl);

		return RsData.OK;
	}


	@AllArgsConstructor
	@Getter
	public static class SurlModifyReqBody {
		@NotBlank
		private String body;
		@NotBlank
		private String url;
	}

	@AllArgsConstructor
	@Getter
	public static class SurlModifyRespBody {
		private SurlDto item;
	}

	@PutMapping("/{id}")
	@Transactional
	@Operation(summary = "수정")
	public RsData<SurlModifyRespBody> modify(
		@PathVariable long id,
		@RequestBody @Valid SurlModifyReqBody reqBody
	) {
		Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

		authService.checkCanModifySurl(rq.getMember(), surl);

		RsData<Surl> modifyRs = surlService.modify(surl, reqBody.body, reqBody.url);

		return modifyRs.newDataOf(
			new SurlModifyRespBody(
				new SurlDto(modifyRs.getData())
			)
		);
	}
}
