package com.heekwon.home.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heekwon.home.entity.Member;
import com.heekwon.home.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{

	private final MemberRepository memberRepository;
	
	public Member saveMember(Member member) {
		
		validateDuplicateMember(member);
		Member member1 = memberRepository.save(member);
		
		return member1;
	}
	
	private void validateDuplicateMember(Member member) {
		Member resultMember = memberRepository.findByMid(member.getMid());
		
		if(resultMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다!");
		}
		
	}

	@Override
	public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
			
		Member member = memberRepository.findByMid(mid);
		
		if(member == null) {
			throw new UsernameNotFoundException(mid);
		}
		
		
		return User.builder()
				.username(member.getMid())
				.password(member.getMpw())
				.roles(member.getRole().toString())
				.build();
	}
	
	
	
	
}
