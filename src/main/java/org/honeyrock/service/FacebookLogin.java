package org.honeyrock.service;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.honeyrock.domain.MemberVO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.java.Log;

@Log
public class FacebookLogin {
	
	public static JsonNode getAccessToken(String autorize_code) {

		final String RequestUrl = "https://graph.facebook.com/v3.2/oauth/access_token";

		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", "470771946780386")); // REST API KEY
		postParams.add(new BasicNameValuePair("client_secret", "386ab0d5a6c9c79bf1951d9416d9fd55"));
		postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/facebooklogin")); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", autorize_code)); // 로그인 과정중 얻은 code 값

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		JsonNode returnNode = null;

		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("Sending 'POST' request to URL : " + RequestUrl);
			System.out.println("Post parameters : " + postParams);
			System.out.println("Response Code : " + responseCode);

			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;

	}
	
	public static JsonNode getFacebookUserInfo(String autorize_code) {

		final String RequestUrl = "https://graph.facebook.com/v3.2/me?fields=email,name,id,age_range,gender,picture,birthday&access_token=" + autorize_code;
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);

		// add header
		post.addHeader("Authorization", "Bearer " + autorize_code);

		JsonNode returnNode = null;

		try {
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			System.out.println("Response Code : " + responseCode);

			// JSON 형태 반환값 처리

			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;
	}
	
	public static MemberVO changeData(JsonNode userInfo) {
		
		log.info("service -> userinfo : "+ userInfo);
		
		MemberVO vo = new MemberVO();
		vo.setUsernick(userInfo.path("name").asText());
		vo.setUsermail(userInfo.path("email").asText());
		
		log.info("vo : " + vo);
		return vo;
	}

}

