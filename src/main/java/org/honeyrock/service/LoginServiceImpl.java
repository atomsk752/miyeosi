package org.honeyrock.service;

import java.util.List;

import org.honeyrock.domain.MemberVO;
import org.honeyrock.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class LoginServiceImpl implements LoginService {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Override
	public List<MemberVO> getList() {
		return mapper.getList();
	}

	@Override
	public MemberVO read(String usermail) {
		return mapper.read(usermail);
	}

	@Override
	public int register(MemberVO vo) {
		return mapper.register(vo);
	}

	@Override
	public int update(MemberVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int delete(MemberVO vo) {
		return mapper.delete(vo);
	}

	@Override
	public int registerAuth(MemberVO vo) {
		return mapper.registerAuth(vo);
	}
	
	

}
