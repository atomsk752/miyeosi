package org.honeyrock.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.honeyrock.domain.MemberVO;
import org.honeyrock.service.FacebookLogin;
import org.honeyrock.service.GoogleLogin;
import org.honeyrock.service.KakaoLogin;
import org.honeyrock.service.LoginService;
import org.honeyrock.service.NaverLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Setter;
import lombok.extern.java.Log;

@RestController
@Log
public class SocialLoginController {
	
	@Setter(onMethod_ = @Autowired)
	private LoginService service;
	
	@RequestMapping(value = "/kakaologin" , produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
	public void kakaoLogin(@RequestParam("code") String code, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		JsonNode token = KakaoLogin.getAccessToken(code);
		log.info("=====" + code);
		log.info("=====" + token);

		JsonNode userInfo = KakaoLogin.getKakaoUserInfo(token.path("access_token").toString());

		MemberVO vo = KakaoLogin.changeData(userInfo);
		log.info("vo : " + vo);
		String usermail = vo.getUsermail();
		String usernick = vo.getUsernick();
		String redirectPage = "";
		
		//DB에서 멤버리스트
		List<MemberVO> memberList = service.getList();
		
		//DB에 저장된 sns_id 없을 때, 회원가입 페이지로 넘길 정보 (FlashMap 객체 생성)
		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		
		for(int i = 0; i < memberList.size(); i++) {
			log.info("memberList : " + memberList.get(i).getUsermail());
			if(memberList.get(i).getUsermail().equals(usermail)){
				redirectPage = "/index";
				break;
			}else {
			// sns_id와 맵핑된 유저 데이터가 DB에 없을 시 회원가입 페이지로 이동 
			// flashmap에데이터 전달 
			fm.put("usernick", usernick);
			fm.put("usermail", usermail);
			log.info("map: " + fm);
			
			redirectPage = "/login/signup";
			
			RequestContextUtils.saveOutputFlashMap(redirectPage, request, response);
			}
		}
		response.sendRedirect(redirectPage);
	}
	
	@RequestMapping(value = "/naverlogin", produces = "application/json", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void naverlogin(@RequestParam("code") String code, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, @RequestParam("state") String state) throws Exception {

		JsonNode token = NaverLogin.getAccessToken(code);
		log.info("=====" + code);
		log.info("=====" + state);
		log.info("=====" + token);

		JsonNode userInfo = NaverLogin.getNaverUserInfo(token.path("access_token").toString());

		MemberVO vo = NaverLogin.changeData(userInfo);
		log.info("vo : " + vo);
		String usermail = vo.getUsermail();
		String usernick = vo.getUsernick();
		String redirectPage = "";
		
		//DB에서 멤버리스트
		List<MemberVO> memberList = service.getList();
		
		//DB에 저장된 sns_id 없을 때, 회원가입 페이지로 넘길 정보 (FlashMap 객체 생성)
		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		
		for(int i = 0; i < memberList.size(); i++) {
			log.info("memberList : " + memberList.get(i).getUsermail());
			if(memberList.get(i).getUsermail().equals(usermail)){
				redirectPage = "/index";
				break;
			}else {
			// sns_id와 맵핑된 유저 데이터가 DB에 없을 시 회원가입 페이지로 이동 
			// flashmap에데이터 전달 
			fm.put("usernick", usernick);
			fm.put("usermail", usermail);
			log.info("map: " + fm);
			
			redirectPage = "/login/signup";
			
			RequestContextUtils.saveOutputFlashMap(redirectPage, request, response);
			}
		}
		response.sendRedirect(redirectPage);
	}
	
	@RequestMapping(value = "/googlelogin" , produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
	public void googleLogin(@RequestParam("code") String code, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		JsonNode token = GoogleLogin.getAccessToken(code);
		log.info("=====" + code);
		log.info("=====" + token);

		JsonNode userInfo = GoogleLogin.getGoogleUserInfo(token.path("access_token").toString());

		MemberVO vo = GoogleLogin.changeData(userInfo);
		log.info("vo : " + vo);
		String usermail = vo.getUsermail();
		String usernick = vo.getUsernick();
		String redirectPage = "";
		
		//DB에서 멤버리스트
		List<MemberVO> memberList = service.getList();
		
		//DB에 저장된 sns_id 없을 때, 회원가입 페이지로 넘길 정보 (FlashMap 객체 생성)
		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		
		for(int i = 0; i < memberList.size(); i++) {
			log.info("memberList : " + memberList.get(i).getUsermail());
			if(memberList.get(i).getUsermail().equals(usermail)){
				redirectPage = "/index";
				break;
			}else {
			// sns_id와 맵핑된 유저 데이터가 DB에 없을 시 회원가입 페이지로 이동 
			// flashmap에데이터 전달 
			fm.put("usernick", usernick);
			fm.put("usermail", usermail);
			log.info("map: " + fm);
			
			redirectPage = "/login/signup";
			
			RequestContextUtils.saveOutputFlashMap(redirectPage, request, response);
			}
		}
		response.sendRedirect(redirectPage);
	}
	
	@RequestMapping(value = "/facebooklogin" , produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
	public void facebookLogin(@RequestParam("code") String code, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		JsonNode token = FacebookLogin.getAccessToken(code);
		log.info("=====" + code);
		log.info("=====" + token);

		JsonNode userInfo = FacebookLogin.getFacebookUserInfo(token.path("access_token").toString());
		
		log.info("=====user info : " + userInfo);
		
		MemberVO vo = FacebookLogin.changeData(userInfo);
		log.info("vo : " + vo);
		String usermail = vo.getUsermail();
		String usernick = vo.getUsernick();
		String redirectPage = "";
		
		//DB에서 멤버리스트
		List<MemberVO> memberList = service.getList();
		
		//DB에 저장된 sns_id 없을 때, 회원가입 페이지로 넘길 정보 (FlashMap 객체 생성)
		FlashMap fm = RequestContextUtils.getOutputFlashMap(request);
		
		for(int i = 0; i < memberList.size(); i++) {
			log.info("memberList : " + memberList.get(i).getUsermail());
			if(memberList.get(i).getUsermail().equals(usermail)){
				redirectPage = "/index";
				break;
			}else {
			// sns_id와 맵핑된 유저 데이터가 DB에 없을 시 회원가입 페이지로 이동 
			// flashmap에데이터 전달 
			fm.put("usernick", usernick);
			fm.put("usermail", usermail);
			log.info("map: " + fm);
			
			redirectPage = "/login/signup";
			
			RequestContextUtils.saveOutputFlashMap(redirectPage, request, response);
			}
		}
		response.sendRedirect(redirectPage);
	}

}
