package com.example.web;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/menu")
public class MenuController {

	/**
	 * メニュー画面表示
	 * @return メニュー画面
	 */
	@GetMapping
	public String index() {
		return "/cleaning_rota/menu";
	}
	
	/**
	 * 掃除当番表表示機能遷移
	 * @return 掃除当番表表示機能
	 */
	@PostMapping(path = "/cleaning_rota")
	public String forwardCleaningRota() {
		return "redirect:/cleaning_rota?executedDate=" + LocalDate.now();
	}
	
	/**
	 * パスワード変更機能遷移
	 * @return パスワード変更機能
	 */
	@PostMapping(path = "/change_password")
	public String forwardChangePassword() {
		return "redirect:/change_password";
	}
	
	/**
	 * ユーザー登録機能遷移
	 * @return ユーザー登録機能
	 */
	@PostMapping(path = "/regist_user")
	public String forwardRegistUser() {
		return "redirect:/regist_user";
	}

}
