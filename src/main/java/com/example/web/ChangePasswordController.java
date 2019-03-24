package com.example.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.User;
import com.example.enums.CharacterEnum;
import com.example.service.LoginUserDetails;
import com.example.service.UserService;

@Controller
@RequestMapping("/change_password")
public class ChangePasswordController {
	
	@Autowired
	UserService userService;
	
//	@Autowired
//	PasswordEncoder passwordEncoder;
	
	/**
	 * パスワード変更画面表示
	 * @param form パスワードフォーム
	 * @param model モデル
	 * @return パスワード変更画面
	 */
	@GetMapping
	public String changePasswordForm(ChangePasswordForm form, Model model) {
		form.setRegisteredPassword(CharacterEnum.BLANK.getCharacter());
		form.setChangePassword(CharacterEnum.BLANK.getCharacter());
		form.setConfirmPassword(CharacterEnum.BLANK.getCharacter());
		model.addAttribute("changePasswordForm", form);
		return "cleaning_rota/change_password";
	}
	
	@PostMapping(path = "edit")
	public String changePassword(@Validated ChangePasswordForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		
		// 文字数エラー
		if (result.hasErrors()) {
//			return "redirect:/change_password";
			return "cleaning_rota/change_password";
		}
		
		// 変更前パスワードチェック
		PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
		Optional<User> user = userService.findByUserId(userDetails.getUser().getUserId());
		String r = passwordEncoder.encode(form.getRegisteredPassword());
		
//		String w = "demo";
//		String e = passwordEncoder.encode(w);
//		System.out.println(e);
		
//		String t = passwordEncoder.encode(user.get().getEncodedPassword());
		boolean x = passwordEncoder.matches("demo", passwordEncoder.encode(user.get().getEncodedPassword()));
//		boolean x = passwordEncoder.matches(user.get().getEncodedPassword(), passwordEncoder.encode(form.getRegisteredPassword()));
		if (!x) {
			return "cleaning_rota/change_password";
		}
			
		return "redirect:/change_password";
	}
	
	/**
	 * 戻るボタンクリック
	 * @return メニュー表示機能
	 */
	@PostMapping(path = "edit", params = "goToMenu")
	public String goToMenu() {
		return "redirect:/menu";
	}
	
}
