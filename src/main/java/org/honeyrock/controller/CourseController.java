package org.honeyrock.controller;

import org.honeyrock.domain.CourseVO;
import org.honeyrock.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RequestMapping("/course/")
@RestController
@Log
@AllArgsConstructor
public class CourseController {
	
	private CourseService service;
	
	@PostMapping(value = "/new",
			consumes = "application/json",
			produces = { MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody CourseVO vo){
		int insertCount = service.register(vo);
		
		return insertCount == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
	}
	
	@DeleteMapping(value = "/{coursekey}")
	public ResponseEntity<Integer> remove(@PathVariable("coursekey") String coursekey){
		
		return new ResponseEntity<>(service.delete(coursekey), HttpStatus.OK);
			
	}

}
