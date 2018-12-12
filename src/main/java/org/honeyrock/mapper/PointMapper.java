package org.honeyrock.mapper;

import java.util.List;
import org.honeyrock.domain.PointVO;



public interface PointMapper {
	
	public List<PointVO> getList();
	
	public PointVO read(PointVO vo);
	
	public void register(PointVO vo);
	
	public int update(PointVO vo);
	
	public int delete(PointVO vo);
	
	public int count(PointVO vo);
}
