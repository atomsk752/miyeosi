package org.honeyrock.controller;

import org.honeyrock.domain.PointVO;
import org.honeyrock.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.honeyrock.domain.PageParam;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/*")
@Log
@AllArgsConstructor
public class PointController {
	
	@Setter(onMethod_=@Autowired)
	private PointService service;

	@GetMapping("/list")
	public void listGET(Model model, @ModelAttribute("pageObj") PageParam pageParam) {
		
		pageParam.setTotal(service.getTotal(pageParam));
		model.addAttribute("list", service.getList(pageParam));

	}
	
	@GetMapping({"/read", "/modify"})
	public void readGET(PointVO vo, Model model) {
		
		model.addAttribute("detail", service.get(vo));
	}
	
	@GetMapping("/register")
	public void registerGET() {
		
	}
	
	@PostMapping("/register")
	@PreAuthorize("permitAll")
	public String registerPOST(PointVO vo, RedirectAttributes rttr) {
		
		service.register(vo);
		
		return "redirect:/boards/list";
	}
	
	
}
