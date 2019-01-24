package org.honeyrock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.honeyrock.domain.PageParam;
import org.honeyrock.domain.PointVO;

public interface PointMapper {

	public List<PointVO> getList(PageParam pageParam);
	
	public int count(PageParam pageParam);
	
	public PointVO read(PointVO vo);
	
	public void register(PointVO vo);

	public int update(PointVO vo);
	
	public int delete(PointVO vo);
	

	public List<PointVO> getImg();
	
	//dash board popular point
	public List<PointVO> getPList(PageParam pageParam);

	//dash board recent point
	public List<PointVO> getRPList(PageParam pageParam);
}
