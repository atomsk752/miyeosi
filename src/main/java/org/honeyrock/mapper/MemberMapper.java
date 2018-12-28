package org.honeyrock.mapper;

import java.util.List;

import org.honeyrock.domain.MemberVO;

public interface MemberMapper {
	
	public List<MemberVO> getList();
	
	public MemberVO read(String usermail);
	
	public int register(MemberVO vo);
	
	public int registerAuth(MemberVO vo);
	
	public int update(MemberVO vo);
	
	public int delete(MemberVO vo);
	
	public int setDefaultkey(String usermail);
	
	public int encodeDefaultkey(MemberVO vo);
	
	public int resetDefaultkey(String usermail);

}
