package com.ll.demo03.domain.surl.surl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.domain.surl.surl.entity.Surl;

public interface SurlRepository extends JpaRepository<Surl, Long> {

	List<Surl> findByAuthorOrderByIdDesc(Member author);
}
