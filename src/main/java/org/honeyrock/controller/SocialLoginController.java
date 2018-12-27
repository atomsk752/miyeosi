package org.honeyrock.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.honeyrock.domain.MemberVO;
import org.honeyrock.service.KakaoLogin;
import org.honeyrock.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		response.sendRedirect("/login");
		
	}

}
