package org.honeyrock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ImageVO {

	private Integer pno;
	private Integer ino;
	private String iname;
	private Date regdate;
	private Date updatedate;
	
	
}
