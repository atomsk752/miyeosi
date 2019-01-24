package org.honeyrock.service;

import java.util.List;

import org.honeyrock.domain.PageParam;
import org.honeyrock.domain.CourseBoardVO;
import org.honeyrock.mapper.CourseBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
@Service
public class CourseBoardServiceImpl implements CourseBoardService {

	@Setter(onMethod_=@Autowired)
	private CourseBoardMapper CourseBoardMapper;
	
	
	@Override
	public List<CourseBoardVO> getList(PageParam pageParam) {
		return CourseBoardMapper.getList(pageParam);
	}


	@Override
	public CourseBoardVO get(CourseBoardVO vo) {
		
		return CourseBoardMapper.read(vo);
	}


	@Override
	public void register(CourseBoardVO vo) {
		
		CourseBoardMapper.register(vo);
	}


	@Override

	public int getTotal(PageParam pageParam) {
		// TODO Auto-generated method stub
		return CourseBoardMapper.count(pageParam);
	}
	public boolean modify(CourseBoardVO vo) {
		
		return CourseBoardMapper.update(vo)==1;
	}


	@Override
	public boolean delete(CourseBoardVO vo) {
		
		return CourseBoardMapper.delete(vo)==1;
	}


	@Override
	public int count(PageParam pageParam) {
		
		return CourseBoardMapper.count(pageParam);
	}
	
	@Override
	public List<CourseBoardVO> getCList(PageParam pageParam) {
		
		return CourseBoardMapper.getCList(pageParam);
	}

	@Override
	public List<CourseBoardVO> getRCList(PageParam pageParam) {
		
		return CourseBoardMapper.getRCList(pageParam);
	}


}
