package org.honeyrock.service;

import java.util.List;

import org.honeyrock.domain.CourseVO;

public interface CourseService {
	
	public List<CourseVO> getList(String usermail);
	
	public int register(CourseVO vo);
	
	public int delete(String coursekey);

}
