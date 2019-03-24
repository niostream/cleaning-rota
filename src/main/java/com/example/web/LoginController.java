package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	/**
	 * ログイン画面表示
	 * @return ログイン画面
	 */
	@GetMapping(path = "loginForm")
	public String loginForm() {
		return "loginForm";
	}

}
