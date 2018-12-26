package org.honeyrock.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.honeyrock.domain.MemberVO;
import org.honeyrock.service.LoginService;
import org.honeyrock.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class SampleController {
	
	@Setter(onMethod_ = @Autowired)
	private SearchService searchService;
	
	@Setter(onMethod_ = @Autowired)
	private LoginService loginService;
	
	@GetMapping("/index")
	public void index() {
		
	}
	
	@GetMapping("/search")
	public void search() {
		
	}
	
	@GetMapping("/simple")
	public void simple(Model model) {
		model.addAttribute("List", searchService.getList());
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@PostMapping("/login")
	public void customLogin(MemberVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("vo : " + vo);
	}
	
	@GetMapping("/signup")
	public void signup() {
		
	}
	
	@PostMapping("/signup")
	public void signupPOST(MemberVO vo, RedirectAttributes rttr) {
		log.info("vo : " + vo);
		
	}

}
