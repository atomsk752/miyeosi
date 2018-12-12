package org.honeyrock.service;

import java.util.List;
import java.util.Map;

import org.honeyrock.domain.PointVO;
import org.honeyrock.mapper.PointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
@Service
public class PointServiceImpl implements PointService {

	@Setter(onMethod_=@Autowired)
	private PointMapper pointMapper;
	
	
	@Override
	public List<PointVO> getList() {
		return pointMapper.getList();
	}


	@Override
	public PointVO get(PointVO vo) {
		
		return pointMapper.read(vo);
	}


	@Override
	public void register(PointVO vo) {
		
		pointMapper.register(vo);
	}


	@Override
	public boolean modify(PointVO vo) {
		
		return pointMapper.update(vo)==1;
	}


	@Override
	public boolean delete(PointVO vo) {
		
		return pointMapper.delete(vo)==1;
	}


	@Override
	public int count(PointVO vo) {
		
		return pointMapper.count(vo);
	}


}
