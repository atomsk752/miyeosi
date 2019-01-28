package org.honeyrock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.honeyrock.domain.ImageVO;
import org.honeyrock.domain.MahoutVO;
import org.honeyrock.domain.PointVO;

public interface SearchMapper {
	
	public List<PointVO> searchPoint(String keyword);

	public List<String> getPointName();
	
	public List<PointVO> getList();
	
	public List<PointVO> getList2();
	
	public PointVO getPoint(Integer pno);

	@Select("select img, keyword, lat, lng, pno, title from tbl_point where pno = 9 or pno = 10 or pno = 11 or pno = 12 or pno = 14 or pno = 17 or pno = 18 or pno = 23")
	public List<PointVO> getRecommendList();
	
	@Select("select img, keyword, lat, lng, pno, title, category from tbl_point where pno = 50 or pno = 18 or pno = 62 or pno = 51 or pno = 71")
	public List<PointVO> getSiteList();
	
	@Select("select img, keyword, lat, lng, pno, title, category  from tbl_point where pno = 130 or pno = 132 or pno = 135 or pno = 138 or pno = 139")
	public List<PointVO> getRestaurantList();
	
	@Select("select img, keyword, lat, lng, pno, title, category  from tbl_point where pno = 148 or pno = 140 or pno = 143 or pno = 145 or pno = 147")
	public List<PointVO> getHotelList();
	
	/*@Insert("insert into recommend user_id, item_id, value values(#{uno}, #{pno}, 4.0)")
	public int mahoutRegister(MahoutVO vo);*/
}
	
