package org.honeyrock.service;

import org.honeyrock.domain.CourseRatingVO;
import org.honeyrock.domain.CourseVO;

public interface CourseService {
	
	public int register(CourseVO vo);

	public int starInsert(CourseRatingVO vo2);
}
