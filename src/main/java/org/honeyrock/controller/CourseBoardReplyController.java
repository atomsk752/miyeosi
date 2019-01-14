package org.honeyrock.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.honeyrock.domain.CourseBoardReplyVO;
import org.honeyrock.domain.CourseBoardVO;
import org.honeyrock.domain.PointReplyVO;
import org.honeyrock.domain.PointVO;
import org.honeyrock.persistence.CourseBoardReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courseboardreplies/*")
public class CourseBoardReplyController {
	
	@Autowired
	private CourseBoardReplyRepository replyRepo;
	
	@PostMapping("/{board_coursebno}")
	public ResponseEntity<List<CourseBoardReplyVO>> addReply(
			@PathVariable("board_coursebno") Integer coursebno,
			@RequestBody CourseBoardReplyVO reply){
		
		CourseBoardVO board = new CourseBoardVO();
		board.setCoursebno(coursebno);
		
		reply.setBoard(board);
		replyRepo.save(reply);
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
	}
	
	private List<CourseBoardReplyVO> getListByBoard(CourseBoardVO board) throws RuntimeException{
		return replyRepo.getRepliesofCourseBoard(board);
	}
	
	@Transactional
	@DeleteMapping("/{board_coursebno}/{courserno}")
	public ResponseEntity<List<CourseBoardReplyVO>> remove(
			@PathVariable("board_coursebno") Integer coursebno,
			@PathVariable("courserno")Integer courserno){
		
		replyRepo.deleteById(courserno);
		
		CourseBoardVO board = new CourseBoardVO();
		board.setCoursebno(coursebno);
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping("/{board_coursebno}")
	public ResponseEntity<List<CourseBoardReplyVO>> modify(
			@PathVariable("board_coursebno") Integer coursebno,
			@RequestBody CourseBoardReplyVO reply){
		
		replyRepo.findById(reply.getCourserno()).ifPresent(origin -> {
			origin.setReply(reply.getReply());
			replyRepo.save(origin);
		});;
		
		CourseBoardVO board = new CourseBoardVO();
		board.setCoursebno(coursebno);
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
	}
	
	@GetMapping("/{board_coursebno}")
	public ResponseEntity<List<CourseBoardReplyVO>> getReplies(
			@PathVariable("board_coursebno") Integer coursebno){
		
		CourseBoardVO board = new CourseBoardVO();
		board.setCoursebno(coursebno);
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
	}

}
