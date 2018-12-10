package org.honeyrock.controller;

import org.honeyrock.domain.PointVO;
import org.honeyrock.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/")
@Log
@AllArgsConstructor
public class PointController {
	
	@Setter(onMethod_= @Autowired)
	private PointService service;

	@GetMapping("/list")
	public void listGET(Model model) {
		
		model.addAttribute("list", service.getList());

	}
	
	
}
