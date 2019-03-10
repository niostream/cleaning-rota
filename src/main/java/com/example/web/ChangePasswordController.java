package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.enums.CharacterEnum;

@Controller
@RequestMapping("/change_password")
public class ChangePasswordController {
	
	@GetMapping
	public String changePasswordForm(ChangePasswordForm form, Model model) {
		form.setRegisteredPassword(CharacterEnum.BLANK.getCharacter());
		form.setChangePassword(CharacterEnum.BLANK.getCharacter());
		form.setConfirmPassword(CharacterEnum.BLANK.getCharacter());
		model.addAttribute("changePasswordForm", form);
		return "cleaning_rota/change_password";
	}
	
	@PostMapping(path = "edit")
	public String changePassword(@Validated ChangePasswordForm form, BindingResult result) {
		
		// 文字数エラー
		if (result.hasErrors()) {
			return "redirect:/change_password";
		}
		
		
		return "redirect:/change_password";
	}

}
