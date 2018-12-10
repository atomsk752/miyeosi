package org.honeyrock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.honeyrock.domain.PointVO;


public interface PointMapper {
	
	public List<PointVO> getList();

}
