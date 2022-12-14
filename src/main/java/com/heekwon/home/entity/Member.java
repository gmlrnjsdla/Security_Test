package com.heekwon.home.entity;

import javax.persistence.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.heekwon.home.Role;
import com.heekwon.home.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "home_member")
@SequenceGenerator(name = "homemember_seq_generator", 
	sequenceName = "homemember_seq", 
	allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mnum;
	
	@Column(unique = true)
	private String mid;
	
	private String mname;
	
	private String mpw;
	
	private String memail;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
		
		Member member = new Member();
		member.setMid(memberDto.getMid());
		member.setMname(memberDto.getMname());
		member.setMemail(memberDto.getMemail());
		
		String mpw = passwordEncoder.encode(memberDto.getMpw());
		//암호화된 비밀번호
		member.setMpw(mpw);
		member.setRole(Role.USER);
		
		return member;
	}
}
