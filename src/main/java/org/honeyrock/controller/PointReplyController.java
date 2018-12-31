package org.honeyrock.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.honeyrock.domain.PointReplyVO;
import org.honeyrock.domain.PointVO;
import org.honeyrock.persistence.PointReplyRepository;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replies/*")
public class PointReplyController {

	@Autowired
	private PointReplyRepository replyRepo;
	
	@PostMapping("/{point_pno}")
	public ResponseEntity<List<PointReplyVO>> addReply(
			@PathVariable("point_pno")Integer pno,
			@RequestBody PointReplyVO reply){
		System.out.println("addReply-------------------------------------");
		System.out.println("pno: "+pno);
		System.out.println("reply: "+ reply);
		
		PointVO point = new PointVO();
		point.setPno(pno);
		
		reply.setPoint(point);
		
		replyRepo.save(reply);
		
		return new ResponseEntity<>(getListByPoint(point), HttpStatus.CREATED);
	}
	
	private List<PointReplyVO> getListByPoint(PointVO point) throws RuntimeException{
		
		System.out.println("getListByPoint......" + point);
		return replyRepo.getRepliesOfPoint(point);
	}
	
	@Transactional
	@DeleteMapping("/{point_pno}/{rno}")
	public ResponseEntity<List<PointReplyVO>> remove(
			@PathVariable("point_pno")Integer pno,
			@PathVariable("rno")Integer rno){
		
		System.out.println("delete reply...."+rno);
		
		replyRepo.deleteById(rno);
		
		PointVO point = new PointVO();
		point.setPno(pno);
		
		return new ResponseEntity<>(getListByPoint(point), HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping("/{point_pno}")
	public ResponseEntity<List<PointReplyVO>> modify(
			@PathVariable("point_pno")Integer pno,
			@RequestBody PointReplyVO reply){
		
		System.out.println("modify reply...." + reply);
		
		replyRepo.findById(reply.getRno()).ifPresent(origin -> {
			origin.setPointreply(reply.getPointreply());
			replyRepo.save(origin);
		});
		PointVO point = new PointVO();
		point.setPno(pno);
		
		return new ResponseEntity<>(getListByPoint(point), HttpStatus.CREATED);
	}
	
	@GetMapping("/{point_pno}")
	public ResponseEntity<List<PointReplyVO>> getReplies(
			@PathVariable("point_pno")Integer pno){
		
		System.out.println("get all replies...................");
		
		PointVO point = new PointVO();
		point.setPno(pno);
		return new ResponseEntity<>(getListByPoint(point), HttpStatus.OK);
		
	}
	
	
	
}
