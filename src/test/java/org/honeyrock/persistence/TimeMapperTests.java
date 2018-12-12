package org.honeyrock.persistence;

import org.apache.catalina.mapper.Mapper;
import org.honeyrock.domain.PageParam;
import org.honeyrock.domain.PointVO;
import org.honeyrock.mapper.PointMapper;
import org.honeyrock.mapper.TimeMapper;
import org.honeyrock.service.PointService;
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
	
	@Setter(onMethod_= @Autowired)
	private PointService pointService;
	
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
	public void testGetList(PageParam pageParam) {
		
		pointService.getList(pageParam).forEach(point -> log.info(""+point));
	}

}
