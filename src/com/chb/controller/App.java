package com.chb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class App {
	@RequestMapping("index")
	public String index(){
		return "redirect:pages/front/index.html";
	}

}
