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
@Table(name = "tbl_point_reply")
@ToString(exclude = "point")
public class PointReplyVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rno;
	
	private String pointreply;
	private String pointreplyer;
	private Integer good;
	private Integer bad;
	private String img;
	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp
	private Timestamp updatedate;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private PointVO point;
	
}
