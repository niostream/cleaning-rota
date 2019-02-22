package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cleaning_rota/change_password")
public class ChangePasswordController {
	
	@GetMapping
	public String index() {
		return null;
	}

}
