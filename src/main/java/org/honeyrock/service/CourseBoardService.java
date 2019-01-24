package org.honeyrock.service;

import java.util.List;

import org.honeyrock.domain.CourseBoardVO;
import org.honeyrock.domain.PageParam;



public interface CourseBoardService {

	
	public List<CourseBoardVO> getList(PageParam pageParam);
	
	public int getTotal(PageParam pageParam);
	
	public CourseBoardVO get(CourseBoardVO vo);
	
	public void register(CourseBoardVO vo);

	
	public boolean modify(CourseBoardVO vo);
	
	public boolean delete(CourseBoardVO vo);
	
	public int count(PageParam pageParam);
	
	//dash board popular course
	public List<CourseBoardVO> getCList(PageParam pageParam);
	
	//dash board recent course
	public List<CourseBoardVO> getRCList(PageParam pageParam);
	
	
}
