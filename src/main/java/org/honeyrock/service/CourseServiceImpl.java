package org.honeyrock.service;


import org.honeyrock.domain.CourseRatingVO;
import java.util.List;
import org.honeyrock.domain.CourseVO;
import org.honeyrock.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.java.Log;

@Service
@Log
public class CourseServiceImpl implements CourseService {

	@Setter(onMethod_=@Autowired)
	private CourseMapper mapper;
	
	
	@Override
	public int register(CourseVO vo) {

		return mapper.insert(vo);
	}


	@Override
	public int starInsert(CourseRatingVO vo2) {
		
		return mapper.starInsert(vo2);
	}

	public List<CourseVO> getList(String usermail) {
		return mapper.getList(usermail);
	}


	@Override
	public int delete(String coursekey) {
		return mapper.delete(coursekey);
	}


	@Override
	public int modify(CourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}


	@Override
	public int find(CourseVO vo) {
		// TODO Auto-generated method stub
		return mapper.find(vo);
	}

}
