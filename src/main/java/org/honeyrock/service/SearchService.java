package org.honeyrock.service;

import java.util.List;

import org.honeyrock.domain.ImageVO;
import org.honeyrock.domain.PointVO;
import org.json.simple.JSONArray;

public interface SearchService {
	
	public List<PointVO> searchPoint(String keyword);
	
	public List<String> getName();
	
	public List<PointVO> getList();
	
	public List<PointVO> getList2();
	

	public PointVO getPoint(Integer pno);
}