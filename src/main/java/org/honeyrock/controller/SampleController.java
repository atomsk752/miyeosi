package org.honeyrock.controller;


import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.honeyrock.domain.MemberVO;
import org.honeyrock.domain.PointVO;
import org.honeyrock.mapper.SearchMapper;
import org.honeyrock.service.LoginService;
import org.honeyrock.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Log
public class SampleController {
	
	@Setter(onMethod_ = @Autowired)
	private SearchService searchService;
	
	@Setter(onMethod_ = @Autowired)
	private LoginService loginService;
	

	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwEncoder;

	
	@GetMapping("/index")
	public void index() {
		
	}
	
	@GetMapping("/search")
	public void search() {
		
	}
	
	@GetMapping("/map")
	public void map() {
		
	}
	
	@GetMapping("/simple")
	public void simple(Model model) {
		model.addAttribute("List", searchService.getList());
		
		model.addAttribute("List2", searchService.getList2());
	}
	
	@GetMapping({"/login/customLogin","/login/customLogout"})
	public void login(HttpServletRequest request) {
		// 이전페이지로 이동 하지만 회원가입페이지는 막음.
		String referrer = request.getHeader("Referer");
		if(referrer.indexOf("http://localhost:8080/login/signup")<0) {
			request.getSession().setAttribute("prevPage", referrer);
		}
	}
	
	@GetMapping("/login/customLoginTemp")
	public void loginTemp(@RequestParam String username, @RequestParam String password, Model model) {
		log.info("===========소셜 로그인페이지 ===============");
		log.info(username);
		log.info(password);
		model.addAttribute("username", username);
		model.addAttribute("password", password);
	}
	
/*	@PostMapping("/login/customLogin")
	public void customLogin(MemberVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("vo : " + vo);
		
		MemberVO dbVO = loginService.read(vo.getUsermail());
	} */
	
	@GetMapping("/login/signup")
	public void signup(HttpServletRequest req, Model model) {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(req);
		log.info("inputFlashMap: " + inputFlashMap);
		
		
		//받은 정보를 UserVO 인스턴스 vo에 담아서 welcome.html(회원가입 페이지)로 전달. 
		MemberVO vo = new MemberVO();
		if (inputFlashMap != null) {
			vo.setUsernick((String) inputFlashMap.get("usernick"));
			vo.setUsermail((String) inputFlashMap.get("usermail"));
			log.info("UserVO: " + vo);
		}
		model.addAttribute("vo", vo);
	}
	
	@PostMapping("/login/signup")
	public String signupPOST(MemberVO vo, RedirectAttributes rttr) {
		
		log.info("vo : " + vo);
		
		vo.setUserpw(pwEncoder.encode(vo.getUserpw()));
		
		loginService.register(vo);
		
		rttr.addFlashAttribute("result", loginService.registerAuth(vo));
		
		return "redirect:/login/customLogin";
		
	}


}
