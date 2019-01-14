package org.honeyrock.persistence;

import java.util.List;

import org.honeyrock.domain.CourseBoardReplyVO;
import org.honeyrock.domain.CourseBoardVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourseBoardReplyRepository extends CrudRepository<CourseBoardReplyVO, Integer> {

	@Query("select r from CourseBoardReplyVO r where r.board = ?1 " +
			" and r.courserno > 0 order by r.courserno asc")
	public List<CourseBoardReplyVO> getRepliesofCourseBoard(CourseBoardVO board);
	
}
