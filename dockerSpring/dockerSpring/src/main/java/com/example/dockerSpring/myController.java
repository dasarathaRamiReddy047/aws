package com.example.dockerSpring;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class myController {
	
	static String randomWebAppId = new String(new Random().toString());
	
	@GetMapping("/")
	@ResponseBody
	public String index() {
		return  "[WEBAPPID " + randomWebAppId + "] Hello World from spring";
	}
	
}
