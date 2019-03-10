package com.example.web;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/menu")
public class MenuController {
	
	@GetMapping
	public String index() {
		return "/cleaning_rota/menu";
	}
	
	@PostMapping(path = "/cleaning_rota")
	public String forwardCleaningRota() {
		return "redirect:/cleaning_rota?executedDate=" + LocalDate.now();
	}
	
	@PostMapping(path = "/change_password")
	public String forwardChangePassword() {
		return "redirect:/change_password";
	}
	
	@PostMapping(path = "/regist_user")
	public String forwardRegistUser() {
		return "redirect:/regist_user";
	}

}
