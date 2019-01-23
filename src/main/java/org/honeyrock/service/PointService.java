package org.honeyrock.service;

import java.util.List;

import org.honeyrock.domain.PageParam;
import org.honeyrock.domain.PointVO;
import org.json.simple.JSONArray;



public interface PointService {

	public List<PointVO> getList(PageParam pageParam);
	
	public int getTotal(PageParam pageParam);
	
	public PointVO get(PointVO vo);
	
	public void register(PointVO vo);
	
	public boolean modify(PointVO vo);
	
	public boolean delete(PointVO vo);
	
	public int count(PageParam pageParam);

	public JSONArray getBlog(String keyword);
	
	public List<PointVO> getImg();
	
	//dash board popular point
	public List<PointVO> getPList(PageParam pageParam);
	
	//dash board recent point
	public List<PointVO> getRPList(PageParam pageParam);
	
}
