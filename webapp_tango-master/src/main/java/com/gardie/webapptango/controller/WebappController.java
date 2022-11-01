package com.gardie.webapptango.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebappController {

	@GetMapping("/")
    public String home() {
        return "home";
    }
	
}
