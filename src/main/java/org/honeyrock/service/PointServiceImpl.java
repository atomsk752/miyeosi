package org.honeyrock.service;

import java.util.List;

import org.honeyrock.domain.PageParam;
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
	public List<PointVO> getList(PageParam pageParam) {
		return pointMapper.getList(pageParam);
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
	public int getTotal(PageParam pageParam) {
		// TODO Auto-generated method stub
		return pointMapper.count(pageParam);
	}


}
