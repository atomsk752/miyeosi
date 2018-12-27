package org.honeyrock.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.honeyrock.domain.MemberVO;
import org.honeyrock.service.LoginService;
import org.honeyrock.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/simple")
	public void simple(Model model) {
		model.addAttribute("List", searchService.getList());
	}
	
	@GetMapping({"/login/customLogin","/login/customLogout"})
	public void login() {
		
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
		
		vo.setUserpw(pwEncoder.encode(vo.getUserpw()));
		
		log.info("vo : " + vo);
		
		loginService.register(vo);
		
		rttr.addFlashAttribute("result", loginService.registerAuth(vo));
		
		return "redirect:/login/customLogin";
		
	}

}
