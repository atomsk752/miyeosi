package org.honeyrock.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "tbl_course_board")
@EqualsAndHashCode(of = "coursebno")
@ToString(exclude = "replies")
public class CourseBoardVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coursebno;
	private String usermail;
	private String coursekey;
	private String coursetitle;
	private String coursecontent;
	private Integer good;
	private Integer bad;
	private Integer del;
	@CreationTimestamp
	private Date regdate;
	@UpdateTimestamp
	private Date updatedate;
	private String courseimg;
	private String usernick;
	
	@OneToMany(mappedBy = "board" , cascade = CascadeType.ALL)
	private List<CourseBoardReplyVO> replies;
	
}
