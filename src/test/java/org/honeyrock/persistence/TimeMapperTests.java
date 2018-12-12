package org.honeyrock.persistence;

import org.honeyrock.domain.PointVO;
import org.honeyrock.mapper.PointMapper;
import org.honeyrock.mapper.TimeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@MapperScan("org.honeyrock.mapper")
public class TimeMapperTests {
	
	@Setter(onMethod_= @Autowired)
	private TimeMapper timeMapper;
	
	@Setter(onMethod_= @Autowired)
	private PointMapper pointMapper;
	
	@Test
	public void testGetTime() {
		log.info("====================================");
		log.info(""+timeMapper.getTime());
	}
	
	@Test
	public void testGetTime2() {
		log.info("====================================");
		log.info(""+timeMapper.getTime2());
	}
	
	@Test
	public void testGetList() {
		PointVO vo = new PointVO();
		vo.setPno(70);
		log.info("====================================");
		log.info(""+pointMapper.read(vo).toString());
	}
	
	@Test
	public void updateTest() {
		PointVO vo = new PointVO();
		vo.setPno(74);
		vo.setTitle("가파도");
		vo.setCategory("T");
		vo.setLat(33.1759218);
		vo.setLng(126.271395);
		vo.setImg("http://tong.visitkorea.or.kr/cms/resource/18/1999118_image2_1.jpg");
		vo.setKeyword("가파도,청보리,섬,배,제주,축제,시간,마라도,제주도,여행,사진,밭,바다,바람,자전거,모슬포항,길,코스,올레길,마을,풍경,항,보리,여객선,산방산");
		log.info("====================================");
		log.info("" + pointMapper.update(vo));
	}

}
