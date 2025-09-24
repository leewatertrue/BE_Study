package com.ll.demo03.domain.surl.surl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ll.demo03.domain.surl.surl.entity.Surl;

public interface SurlRepository extends JpaRepository<Surl, Long> {

}
