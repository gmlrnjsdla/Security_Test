package com.heekwon.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heekwon.home.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	public Member findByMid(String mid);
	
	
}
