package com.chb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class App {
	@RequestMapping("index")
	public String index(){
		return "redirect:pages/front/index.html";
	}
	
	@RequestMapping("business")
	public String business(){
		return "redirect:pages/business/login.html";
	}
	
	@RequestMapping("admin")
	public String admin(){
		return "redirect:pages/admin/index.html";
	}

}
