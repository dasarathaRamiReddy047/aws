package com.CI.CD.CI.CD.Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
	
	@GetMapping("/")
	public String getWeb() {
		return "index";
	}
}
