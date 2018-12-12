package org.honeyrock.service;

import java.util.List;

import org.honeyrock.domain.PageParam;
import org.honeyrock.domain.PointVO;


public interface PointService {

	
	public List<PointVO> getList(PageParam pageParam);
	
	public int getTotal(PageParam pageParam);
	
	public PointVO get(PointVO vo);
	
	public void register(PointVO vo);
}
