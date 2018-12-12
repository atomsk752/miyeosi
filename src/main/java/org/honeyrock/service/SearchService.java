package org.honeyrock.service;

import java.util.List;

import org.honeyrock.domain.PointVO;

public interface SearchService {
	
	public List<PointVO> searchPoint(String keyword);
	
	public List<String> getName();
	
	public List<PointVO> getList();

}
