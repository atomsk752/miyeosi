package org.honeyrock.controller;

import org.honeyrock.domain.CourseBoardVO;
import org.honeyrock.domain.PageParam;
import org.honeyrock.domain.CourseBoardVO;
import org.honeyrock.service.CourseBoardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/courseboard/*")
@Log
@AllArgsConstructor
public class CourseBoardController {
	
	private CourseBoardService service;

	@GetMapping("/list")
	public void listGET(Model model, @ModelAttribute("pageObj") PageParam pageParam) {
		
		pageParam.setTotal(service.getTotal(pageParam));
		model.addAttribute("list", service.getList(pageParam));

	}


	@PostMapping("/modify")
	public String modifyPOST(CourseBoardVO vo, RedirectAttributes rttr, @ModelAttribute("pageObj") PageParam pageParam) {
		
		log.info("" + vo);
		
		rttr.addFlashAttribute("result", service.modify(vo)? "SUCCESS":"FAIL");
		
		return pageParam.getLink("redirect:/courseboard/list");
	}
	
	@PostMapping("/delete")
	public String deletePOST(@ModelAttribute("CourseBoard") CourseBoardVO vo, RedirectAttributes rttr, @ModelAttribute("pageObj") PageParam pageParam) {
		
		rttr.addFlashAttribute("result", service.delete(vo)? "SUCCESS":"FAIL");
		
		return pageParam.getLink("redirect:/courseboard/list");
	}
	
	@GetMapping({"/read", "/modify"})
	public void readGET(CourseBoardVO vo, Model model, @ModelAttribute("pageObj") PageParam pageParam) {
		
		model.addAttribute("detail", service.get(vo));
	}
	
	@GetMapping("/register")
	public void registerGET() {
		
	}
	
	@PostMapping("/register")
	@PreAuthorize("permitAll")
	public String registerPOST(CourseBoardVO vo, RedirectAttributes rttr) {
		
		service.register(vo);
		
		return "redirect:/courseboard/list";
	}
	
	
}
