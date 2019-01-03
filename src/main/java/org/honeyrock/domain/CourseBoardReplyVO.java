package org.honeyrock.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "tbl_course_board_reply")
@ToString(exclude = "board")
public class CourseBoardReplyVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courserno;
	private String usermail;
	private String coursekey;
	private String reply;
	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp
	private Timestamp updatedate;
	private Integer good;
	private Integer bad;
	private String img;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private CourseBoardVO board;
	

}
