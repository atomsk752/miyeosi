package org.honeyrock.mapper;

import java.util.List;

import org.honeyrock.domain.PointVO;

public interface SearchMapper {
	
	public List<PointVO> searchPoint(String keyword);

	public List<String> getPointName();
	
	public List<PointVO> getList();

}
