package com.example.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ChangePasswordForm {
	
	/** 登録済みパスワード */
	@NotNull
	@Size(min = 1, max = 20)
	private String registeredPassword;
	
	/** 変更パスワード */
	@NotNull
	@Size(min = 8, max = 20)
//	@Pattern(regexp = "^[a-zA-Z]*$")
//	@Pattern(regexp = "^[0-9]*$")
	private String changePassword;
	
	/** 確認パスワード */
	@NotNull
	@Size(min = 8, max = 20)
//	@Pattern(regexp = "^[a-zA-Z]*$")
//	@Pattern(regexp = "^[0-9]*$")
	private String confirmPassword;

}
