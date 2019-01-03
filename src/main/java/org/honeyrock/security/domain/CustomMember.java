package org.honeyrock.security.domain;

import java.util.stream.Collectors;

import org.honeyrock.domain.MemberVO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class CustomMember extends User {
	
	private MemberVO vo;
	
/*	public CustomUser(MemberVO vo) {
		this(vo.getUsermail(), vo.getUserpw(), vo.getUsernick(), vo.getUsersex(), vo.getUserage(), true, true, true, true,
				vo.getUserrole().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList())
				);
		this.vo = vo;
	}*/
	
	public CustomMember(MemberVO vo) {
		super(vo.getUsermail(), vo.getUserpw(), 
				vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList())
				);
		this.vo = vo;
	}
	
//	public CustomUser(String username, String password, String usernick, String usersex, String userage, 
//			boolean enabled, boolean accountNonExpired,
//			boolean credentialsNonExpired, boolean accountNonLocked,
//			Collection<? extends GrantedAuthority> authorities) {
//		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//		// TODO Auto-generated constructor stub
//	}
	
}
