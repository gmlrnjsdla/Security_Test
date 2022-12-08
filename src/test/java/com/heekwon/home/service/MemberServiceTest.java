package com.heekwon.home.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import com.heekwon.home.dto.MemberDto;
import com.heekwon.home.entity.Member;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberServiceTest {

	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	public Member createMember() {
		
		MemberDto memberDto = new MemberDto();
		memberDto.setMid("tiger123123");
		memberDto.setMpw("12345");
		memberDto.setMname("홍길동");
		memberDto.setMemail("qwec@abc.com");
		
		return Member.createMember(memberDto, passwordEncoder);
	}
	
	public Member createMember2() {
		
		MemberDto memberDto = new MemberDto();
		memberDto.setMid("tiger12123123");
		memberDto.setMpw("12345");
		memberDto.setMname("홍길동");
		memberDto.setMemail("qweqwewqc@abc.com");
		
		return Member.createMember(memberDto, passwordEncoder);
	}
	
	@Test
	@DisplayName("회원가입 테스트")
	public void saveMemberTest() {
		Member member1 = createMember();		
		Member savedMember = memberService.saveMember(member1);
		assertEquals(member1.getMid(), savedMember.getMid());
	}
	
	@Test
	@DisplayName("중복 회원가입 테스트")
	public void duplicateMemberTest() {
		
		Member member1 = createMember2();
		Member member2 = createMember2();
		Member resultMember1 = memberService.saveMember(member1);
		
		Throwable e = assertThrows(IllegalStateException.class, () ->{
		Member resultMember2 = memberService.saveMember(member2);});
		
		System.out.println(e.getMessage());
		assertEquals("이미가입된 회원입니다!", e.getMessage());
		
		
	}
	
}
