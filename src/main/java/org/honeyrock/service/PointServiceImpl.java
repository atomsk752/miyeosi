package org.honeyrock.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.honeyrock.domain.PageParam;
import org.honeyrock.domain.PointVO;
import org.honeyrock.mapper.PointMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
@Service
public class PointServiceImpl implements PointService {

	@Setter(onMethod_=@Autowired)
	private PointMapper pointMapper;
	
	
	@Override
	public List<PointVO> getList(PageParam pageParam) {
		return pointMapper.getList(pageParam);
	}


	@Override
	public PointVO get(PointVO vo) {
		
		return pointMapper.read(vo);
	}


	@Override
	public void register(PointVO vo) {
		
		pointMapper.register(vo);
	}


	@Override

	public int getTotal(PageParam pageParam) {
		// TODO Auto-generated method stub
		return pointMapper.count(pageParam);
	}
	public boolean modify(PointVO vo) {
		
		return pointMapper.update(vo)==1;
	}


	@Override
	public boolean delete(PointVO vo) {
		
		return pointMapper.delete(vo)==1;
	}


	@Override
	public int count(PageParam pageParam) {
		
		return pointMapper.count(pageParam);
	}


	@Override
	public JSONArray getBlog(String keyword) {
		String clientId = "XzwPg3Xx4q9mjPVtFq1q";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "qyTwn2vCHL";//애플리케이션 클라이언트 시크릿값";
        JSONArray itemsArray = null;
        try {
            String text = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            String str = response.toString();
//            System.out.println(str);
            
            JSONParser jsonParse = new JSONParser();
            
            JSONObject jsonObj = (JSONObject) jsonParse.parse(str);
            
            itemsArray = (JSONArray) jsonObj.get("items");
            
            for(int i=0; i<itemsArray.size();i++) {
//            	System.out.println("=================items : "+(i+1)+"=============================");
            	JSONObject itemsObject = (JSONObject) itemsArray.get(i);
            	
            	Object title = itemsObject.get("title");
            	Object descs = itemsObject.get("description");
            	Object link = itemsObject.get("link");
            	Object postdate = itemsObject.get("postdate");
            	
//            	System.out.println("제목: " + title);
//            	System.out.println("본문: " + descs);
//            	System.out.println("링크: " + link);
//            	System.out.println("포스트날짜: " + postdate);
            	
//            	System.out.println(itemsObject.get("title"));
//            	System.out.println(itemsObject.get("description"));
//            	System.out.println(itemsObject.get("link"));
//            	System.out.println(itemsObject.get("postdate"));

        		return itemsArray;
            	
            }
        } catch (Exception e) {
            System.out.println(e);
        }
		return itemsArray;
	}


	@Override
	public List<PointVO> getImg() {
		// TODO Auto-generated method stub
		return pointMapper.getImg();
	}
	
	@Override
	public List<PointVO> getPList(PageParam pageParam) {

		return pointMapper.getPList(pageParam);
	}


	@Override
	public List<PointVO> getRPList(PageParam pageParam) {

		return pointMapper.getRPList(pageParam);
	}
}
