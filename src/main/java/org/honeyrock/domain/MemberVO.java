package org.honeyrock.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	
	private String usermail;
	private String userpw;
	private String username;
	private String usernick;
	private String usersex;
	private String userage;
	private Date regdate;
	private Date updatedate;
	private Integer status;
	private Integer userlevel;
	private String defaultkey;
	private Integer uno;
	
	private List<MemberAuthVO> authList;
	
}
