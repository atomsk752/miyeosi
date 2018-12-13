package org.honeyrock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class PageParam {

	private Integer pno, page, display, start, end, total;
	private Boolean next, prev;
	private String[] types;
	private String keywords, type;
	
	
	public PageParam() {
		
		this.page = 1;
		this.display = 5;
	}
	
	public PageParam(int page, int display) {
		this.page = page;
		this.display = display;
	}
	
	public void setTotal(int total) {
		this.total = total;
		this.end = (int)(Math.ceil(this.page/10.0))*10;
		this.start = this.end - 9;
		if((this.end)*this.display >=this.total) {
			this.end = (int)Math.ceil(this.total/(double)this.display);
			this.next = false;
		}else {
			this.next = true;
		}
		this.prev = this.start!= 1;
	}//end total
	
	public int getSkip() {
		return (this.page-1) * this.display;
	}//end skip
	

	
	public void setType(String type) {
		this.type = type;
		
		if(type == null || type.trim().length()==0) {
			this.types=null;
			return;
		}
		this.types=type.split("");
	}//end type
	
	
	public String getListLink() {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
		//.queryParam("bno", this.bno)
		.queryParam("page", this.page)
		.queryParam("display", this.getDisplay())
		.queryParam("type", this.getType())
		.queryParam("keyword", this.getKeywords());
		return builder.toUriString();
	}//end link (unused)
	
}
