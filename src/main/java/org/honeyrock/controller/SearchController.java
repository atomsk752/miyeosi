package org.honeyrock.controller;

import java.util.List;

import org.honeyrock.domain.PointVO;
import org.honeyrock.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
@Transactional
public class SearchController {
	
	@Setter(onMethod_ = @Autowired)
	private SearchService service;
	
	
	@GetMapping("/searchTest")
	@ResponseBody
	public PointVO getPoint(Integer pno) {
		
		log.info("---------------------------");
		
		log.info("pno: " + pno);
		
		log.info("---------------------------");
		
		PointVO point =  service.getPoint(pno);
		
		log.info(""+ point);
		
		log.info("" + point.getImgList());
		
		return point;
		
		
	}
	
	@PostMapping("/autocomplete")
	@ResponseBody
	public ResponseEntity<String[]> autoComplete() {
		log.info("autocomplete");
		
		List<String> list = service.getName();
		
		return new ResponseEntity<>(list.toArray(new String[list.size()]),HttpStatus.OK);
	}
	
	@GetMapping("/searchPoint/{keyword}")
	@ResponseBody
	public ResponseEntity<List<PointVO>> searchMenu(@PathVariable("keyword") String keyword){
		log.info("searchMenu post.....");
		log.info(""+service.searchPoint(keyword));
		
		return new ResponseEntity<>(service.searchPoint(keyword),HttpStatus.OK);
	}
	
	

}
