package com.example.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ChangePasswordForm {
	
	@NotNull
	@Size(min = 8, max = 20)
	private String registeredPassword;
	
	@NotNull
	@Size(min = 8, max = 20)
	private String changePassword;
	
	@NotNull
	@Size(min = 8, max = 20)
	private String confirmPassword;

}
