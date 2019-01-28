package org.honeyrock.mapper;

import org.honeyrock.domain.CourseRatingVO;
import java.util.List;

import org.honeyrock.domain.CourseVO;

public interface CourseMapper {
	
	public List<CourseVO> getList(String usermail);
	
	public int insert(CourseVO vo);
	
	public int delete(String coursekey);

	public int starInsert(CourseRatingVO vo2);
	
	public int update(CourseVO vo);
	
	public int find(CourseVO vo);
	
}
