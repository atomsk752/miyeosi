package org.honeyrock.mapper;

import java.util.List;

import org.honeyrock.domain.CourseBoardVO;
import org.honeyrock.domain.PageParam;
import org.honeyrock.domain.PointVO;

public interface CourseBoardMapper {
	
	public List<CourseBoardVO> getList(PageParam pageParam);
	
	public int count(PageParam pageParam);

	public CourseBoardVO read(CourseBoardVO vo);
	
	public void register(CourseBoardVO vo);
	
	public int update(CourseBoardVO vo);
	
	public int delete(CourseBoardVO vo);
	
	//dash board popular course
	public List<CourseBoardVO> getCList(PageParam pageParam);
	
	//dash board recent course
	public List<CourseBoardVO> getRCList(PageParam pageParam);
	
	
}
