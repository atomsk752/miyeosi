package org.honeyrock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class APISearchBlog {

	public static void main(String[] args) {
        String clientId = "XzwPg3Xx4q9mjPVtFq1q";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "hHsugwQQJz";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode("쇠소깍", "UTF-8");
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
            System.out.println(str);
            
            JSONParser jsonParse = new JSONParser();
            
            JSONObject jsonObj = (JSONObject) jsonParse.parse(str);
            
            JSONArray itemsArray = (JSONArray) jsonObj.get("items");
            
            for(int i=0; i<itemsArray.size();i++) {
            	System.out.println("=================items : "+(i+1)+"=============================");
            	JSONObject itemsObject = (JSONObject) itemsArray.get(i);
            	
            	Object title = itemsObject.get("title");
            	Object descs = itemsObject.get("description");
            	Object link = itemsObject.get("link");
            	Object postdate = itemsObject.get("postdate");
            	
            	System.out.println("제목: " + title);
            	System.out.println("본문: " + descs);
            	System.out.println("링크: " + link);
            	System.out.println("포스트날짜: " + postdate);
//            	System.out.println(itemsObject.get("title"));
//            	System.out.println(itemsObject.get("description"));
//            	System.out.println(itemsObject.get("link"));
//            	System.out.println(itemsObject.get("postdate"));
            	
            }
        } catch (Exception e) {
            System.out.println(e);
        }
	}
}