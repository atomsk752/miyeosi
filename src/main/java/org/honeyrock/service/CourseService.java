package org.honeyrock.service;

import org.honeyrock.domain.CourseRatingVO;

import java.util.List;

import org.honeyrock.domain.CourseVO;

public interface CourseService {
	
	public List<CourseVO> getList(String usermail);
	
	public int register(CourseVO vo);
	
	public int delete(String coursekey);

	public int starInsert(CourseRatingVO vo2);
	
	public int modify(CourseVO vo);
	
	public int find(CourseVO vo);
}
