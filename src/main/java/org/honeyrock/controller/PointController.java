package org.honeyrock.controller;

import org.honeyrock.domain.PageParam;
import org.honeyrock.domain.PointVO;
import org.honeyrock.service.PointService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/*")
@Log
@AllArgsConstructor
public class PointController {
	
	@Setter(onMethod_=@Autowired)
	private PointService service;

	@GetMapping("/list")
	public void listGET(Model model, @ModelAttribute("pageObj") PageParam pageParam) {
		
		pageParam.setTotal(service.getTotal(pageParam));
		log.info("============================");
		log.info("" + pageParam.getCategory());
		log.info("============================");
		model.addAttribute("list", service.getList(pageParam));

	}


	@PostMapping("/modify")
	public String modifyPOST(PointVO vo, RedirectAttributes rttr) {
		
		log.info("" + vo);
		
		rttr.addFlashAttribute("result", service.modify(vo)? "SUCCESS":"FAIL");
		
		String pnoStr = String.valueOf(vo.getPno());
		
		
		return "redirect:/boards/read?pno=" + pnoStr;
	}
	
	@PostMapping("/delete")
	public String deletePOST(@ModelAttribute("point") PointVO vo, RedirectAttributes rttr) {
		
		rttr.addFlashAttribute("result", service.delete(vo)? "SUCCESS":"FAIL");
		
		return "redirect:/boards/list";
	}
	
	@GetMapping({"/read", "/modify"})
	public void readGET(PointVO vo, Model model) {
		
		PointVO point = service.get(vo);
		
		model.addAttribute("detail", point);
	}
	
	@GetMapping("/readBlog")
	@ResponseBody
	public JSONArray getPointBlog(String title) {
		
		JSONArray jsonArr = service.getBlog(title);
		
		return jsonArr;
	}
	
	
	
	@GetMapping("/register")
	public void registerGET() {
		
	}
	
	@PostMapping("/register")
	@PreAuthorize("permitAll")
	public String registerPOST(PointVO vo, RedirectAttributes rttr) {
		
		service.register(vo);
		
		return "redirect:/boards/list";
	}
}
