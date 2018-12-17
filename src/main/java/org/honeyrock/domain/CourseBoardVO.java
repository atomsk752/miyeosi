package org.honeyrock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CourseBoardVO {

	private Integer coursebno;
	private String usermail;
	private String coursekey;
	private String coursetitle;
	private String coursecontent;
	private Integer good;
	private Integer bad;
	private Integer del;
	private Date regdate;
	private Date updatedate;
	
	
}
