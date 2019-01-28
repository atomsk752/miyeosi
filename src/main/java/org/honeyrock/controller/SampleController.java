package org.honeyrock.controller;


import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.honeyrock.domain.CourseBoardVO;
import org.honeyrock.domain.CourseVO;
import org.honeyrock.domain.MemberVO;
import org.honeyrock.domain.PointVO;
import org.honeyrock.mapper.SearchMapper;
import org.honeyrock.domain.PageParam;
import org.honeyrock.security.domain.CustomMember;
import org.honeyrock.service.CourseBoardService;
import org.honeyrock.service.CourseService;
import org.honeyrock.service.LoginService;
import org.honeyrock.service.PointService;
import org.honeyrock.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	private CourseService courseService;
	
	@Setter(onMethod_ = @Autowired)
	private PointService pointService;
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwEncoder;

	@Setter(onMethod_ = @Autowired)
	private CourseBoardService service;

	
	@GetMapping("/index")
	public void index(Model model, @ModelAttribute("pageObj") PageParam pageParam) {
		model.addAttribute("list", service.getList(pageParam));
		model.addAttribute("List", pointService.getList(pageParam));
		model.addAttribute("CList", service.getCList(pageParam));
		model.addAttribute("PList", pointService.getPList(pageParam));
		model.addAttribute("RCList", service.getRCList(pageParam));
		model.addAttribute("RPList", pointService.getRPList(pageParam));
		pageParam.setTotal(pointService.getTotal(pageParam));
	}
	
	@GetMapping("/search")
	public void search() {
		
	}
	
	@GetMapping("/map")
	public void map() {
		
	}
	
	@PostMapping("/map")
	public String mapPost(CourseVO courseVo, CourseBoardVO courseBoardVO) {
		
		log.info("courseVO : " + courseVo);
		log.info("courseBoardVO : " + courseBoardVO);
		
		String dbcourseKey = "";
		List<CourseVO> courseList = courseService.getList(courseVo.getUsermail());
		log.info("find : " + courseService.find(courseVo));
		if(courseService.find(courseVo) == 1){
			courseService.modify(courseVo);
			service.modify(courseBoardVO);
		}else {
			courseService.register(courseVo);
			service.register(courseBoardVO);
		}
		return "redirect:/mypage";
	}
	
	@GetMapping("/simple")
	public void simple(Model model) {

		model.addAttribute("List", searchService.getList());
		
		model.addAttribute("List2", searchService.getList2());

	}
	
	@GetMapping("/mypage")
	public void mypage(Model model, @AuthenticationPrincipal CustomMember customMember) {
		model.addAttribute("list", courseService.getList(customMember.getUsername()));
	}
	
	@GetMapping("/gallery")
	public void share(Model model,@ModelAttribute("pageObj") PageParam pageParam) {
		pageParam.setTotal(pointService.getTotal(pageParam));
		model.addAttribute("list", pointService.getList(pageParam));

	}
	
	@GetMapping({"/login/customLogin","/login/customLogout"})
	public void login(HttpServletRequest request) {
		// 이전페이지로 이동 하지만 회원가입페이지는 막음.
		String referrer = request.getHeader("Referer");
		
		log.info("referer: " + referrer);
		
		
		
		if(referrer != null &&   referrer.indexOf("http://localhost:8080/login/signup")<0) {
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
