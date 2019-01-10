package org.honeyrock.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.java.Log;

@Controller
@Log
public class ImageController {

	
	@GetMapping("/img")
	@ResponseBody
	public byte[] getImage( String name){
		
		try {
			
			System.out.println(name);
			
			URL url = new URL(name);
			
			InputStream in = url.openStream();
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();			
			
			IOUtils.copy(in, bos);
			
			return bos.toByteArray();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
