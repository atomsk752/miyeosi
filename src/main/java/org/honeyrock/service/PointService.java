package org.honeyrock.service;

import java.util.List;

import org.honeyrock.domain.PointVO;



public interface PointService {

	
	public List<PointVO> getList();
	
	public PointVO get(PointVO vo);
	
	public void register(PointVO vo);
	
	public boolean modify(PointVO vo);
	
	public boolean delete(PointVO vo);
	
	public int count(PointVO vo);
}
