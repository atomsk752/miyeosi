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
		
		member = new CustomMember(vo);
		
		log.info("member : " + member);
		
		return member;
	}

}
