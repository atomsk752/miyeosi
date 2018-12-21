package org.honeyrock.persistence;

import java.util.List;

import org.honeyrock.domain.PointReplyVO;
import org.honeyrock.domain.PointVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PointReplyRepository extends CrudRepository<PointReplyVO, Integer> {
	
	@Query("select r from PointReplyVO r where r.point = ?1 " + 
			" and r.rno > 0 order by r.rno asc")
	public List<PointReplyVO> getRepliesOfPoint(PointVO point);

}
//select r from FreeReply r where r.board = ?1 order by r.rno asc