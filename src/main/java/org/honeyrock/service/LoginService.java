package org.honeyrock.service;

import java.util.List;

import org.honeyrock.domain.MemberVO;

public interface LoginService {

	public List<MemberVO> getList();
	
	public MemberVO read(String usermail);
	
	public int register(MemberVO vo);
	
	public int registerAuth(MemberVO vo);
	
	public int update(MemberVO vo);
	
	public int delete(MemberVO vo);
	
}
