package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SecurityConfig;
import com.example.domain.User;
import com.example.enums.CharacterEnum;
import com.example.service.LoginUserDetails;
import com.example.service.UserService;

@Controller
@RequestMapping("/change_password")
public class ChangePasswordController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SecurityConfig securityConfig;
	
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
	
	/**
	 * パスワード変更処理実行
	 * @param form
	 * @param result
	 * @param userDetails
	 * @return
	 */
	@PostMapping(path = "edit")
	public String changePassword(@Validated ChangePasswordForm form, BindingResult result,
			@AuthenticationPrincipal LoginUserDetails userDetails) {
		
		// 文字数エラー
		if (result.hasErrors()) {
//			return "redirect:/change_password";
			return "cleaning_rota/change_password";
		}
		
		// 変更前パスワードチェック
		if (!securityConfig.passwordEncoder().matches(form.getRegisteredPassword(),
				userDetails.getUser().getPassword())) {
			return "cleaning_rota/change_password";
		}
		
		// 登録パスワードチェック
		if (!form.getChangePassword().equals(form.getConfirmPassword())) {
			return "cleaning_rota/change_password"; 
		}
		
		// パスワード登録
		User user = userDetails.getUser();
		user.setPassword(securityConfig.passwordEncoder().encode(form.getConfirmPassword()));
		userService.updatePasswordByUserId(user);
			
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
