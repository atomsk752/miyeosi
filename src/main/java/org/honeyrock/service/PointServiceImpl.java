package org.honeyrock.service;

import java.util.List;
import java.util.Map;

import org.honeyrock.domain.PointVO;
import org.honeyrock.mapper.PointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
@Service
public class PointServiceImpl implements PointService {

	@Setter(onMethod_=@Autowired)
	private PointMapper pointMapper;
	
	
	@Override
	public List<PointVO> getList() {
		return pointMapper.getList();
	}

}
