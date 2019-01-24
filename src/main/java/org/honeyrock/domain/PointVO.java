package org.honeyrock.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
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
@Table(name = "tbl_point")
@EqualsAndHashCode(of = "pno")
@ToString(exclude = "replies")
public class PointVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pno;
	private double lat;
	private double lng;
	private String title;
	private String descs;
	private String keyword;
	private Integer cost;
	private double time;
	private String category;
	private Integer good;
	private Integer bad;
	private String img;
	private String addpoint;
	private String thumb;
	private Integer del;
	private String writer;
	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp
	private Timestamp updatedate;
	
	@OneToMany(mappedBy = "point", cascade=CascadeType.ALL)
	private List<PointReplyVO> replies;
	
	private ArrayList<ImageVO> imgList;
}
