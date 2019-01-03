package org.honeyrock.mapper;

import java.util.List;

import org.honeyrock.domain.CourseVO;

public interface CourseMapper {
	
	public List<CourseVO> getList(String usermail);
	
	public int insert(CourseVO vo);
	
	public int delete(String coursekey);

}
