package org.honeyrock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CourseVO {

	private String coursekey;
	private String coursename;
	private String usermail;
	private String keyword;
	private Date regdate;
	private Date updatedate;
	private Integer good;
	private Integer bad;
	private Integer cost;
	private Integer days;
	private Integer del;
	private Integer rating;
	private Integer coursebno;
	private String courseimg;
	
}
