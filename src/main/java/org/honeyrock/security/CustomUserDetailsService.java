package org.honeyrock.security;

import org.honeyrock.domain.MemberVO;
import org.honeyrock.security.domain.CustomMember;
import org.honeyrock.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
public class CustomUserDetailsService implements UserDetailsService {
		
	@Setter(onMethod_ = @Autowired)
	private LoginService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("Load user by username : " + username);
		
		MemberVO vo = service.read(username);
		
		log.info("vo : " + vo);
		
		CustomMember member;
		
		//customer(user) 로그인에서 일반 로그인인 경우
		if (vo.getDefaultkey().equals("0")) {
			member = new CustomMember(vo);
		}
		//customer(user) 로그인에서 소셜 로그인인 경우		
		else { 
			vo.setUserpw(vo.getDefaultkey());
			member = new CustomMember(vo);
			service.resetDefaultkey(username); //다시 0으로 리셋
		}
		log.info("member : " + member);
		
		return member;
	}

}
