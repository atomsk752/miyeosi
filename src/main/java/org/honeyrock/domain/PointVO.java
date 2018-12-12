package org.honeyrock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PointVO {

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
	private Date regdate;
	private Date updatedate;
	private String addpoint;
	private String thumb;
	private Integer del;
	private String writer;
	
}
