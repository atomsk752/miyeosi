package org.honeyrock.controller;

import org.honeyrock.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class SampleController {
	
	@Setter(onMethod_ = @Autowired)
	private SearchService service;
	
	@GetMapping("/index")
	public void index() {
		
	}
	
	@GetMapping("/search")
	public void search() {
		
	}
	
	@GetMapping("/simple")
	public void simple(Model model) {
		model.addAttribute("List", service.getList());
	}

	
	@GetMapping("/map")
	public void map() {
		
	}
}
