package com.taotao.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageShowController {

	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	@RequestMapping("/{pagePath}")
	public String pageShow(String pagePath){
		return pagePath;
	}
	
}
