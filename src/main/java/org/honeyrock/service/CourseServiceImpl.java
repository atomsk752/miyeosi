package org.honeyrock.service;

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
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

}
