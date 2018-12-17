package org.honeyrock.service;

import java.util.List;

import org.honeyrock.domain.PageParam;
import org.honeyrock.domain.CourseBoardVO;



public interface CourseBoardService {

	
	public List<CourseBoardVO> getList(PageParam pageParam);
	
	public int getTotal(PageParam pageParam);
	
	public CourseBoardVO get(CourseBoardVO vo);
	
	public void register(CourseBoardVO vo);

	
	public boolean modify(CourseBoardVO vo);
	
	public boolean delete(CourseBoardVO vo);
	
	public int count(PageParam pageParam);

}
