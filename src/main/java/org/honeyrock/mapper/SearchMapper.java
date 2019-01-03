package org.honeyrock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
<<<<<<< HEAD
import org.honeyrock.domain.ImageVO;
=======
>>>>>>> origin/map
import org.honeyrock.domain.PointVO;

public interface SearchMapper {
	
	public List<PointVO> searchPoint(String keyword);

	public List<String> getPointName();
	
	public List<PointVO> getList();
	
	public List<PointVO> getList2();
	
	public PointVO getPoint(Integer pno);

	@Select("select img, keyword, lat, lng, pno, title from tbl_point where pno = 9 or pno = 10 or pno = 11 or pno = 12 or pno = 14 or pno = 17 or pno = 18 or pno = 23")
<<<<<<< HEAD
	public List<PointVO> getRecommendList();
	
=======
    public List<PointVO> getRecommendList();
>>>>>>> origin/map
}
	
