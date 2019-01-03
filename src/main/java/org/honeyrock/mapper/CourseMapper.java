package org.honeyrock.mapper;

import org.honeyrock.domain.CourseRatingVO;
import org.honeyrock.domain.CourseVO;

public interface CourseMapper {
	
	public int insert(CourseVO vo);

	public int starInsert(CourseRatingVO vo2);
	
}
