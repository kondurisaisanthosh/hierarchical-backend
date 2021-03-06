package com.hierarchical.HierProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hierarchical.HierProject.bean.RegisterBean;
import com.hierarchical.HierProject.exception.ApiRequestException;
import com.hierarchical.HierProject.repos.RegisterRepo;
import com.hierarchical.HierProject.repos.UserRepo;
import com.hierarchical.HierProject.model.User;

@RestController
public class RegisterController {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RegisterRepo registerRepo;
	
	
	@PostMapping(value="/register")
	public @ResponseBody String registerData(@RequestBody RegisterBean registerBean) {
		
		String errorMessage="";
		
		if(userRepo.checkuser(registerBean.getUsername()) ==1) {
			errorMessage+="username";
		}
		
		if(registerRepo.checkEmail(registerBean.getEmail()) ==1) {
			errorMessage+=" email";
		}
		
		if(registerRepo.checkPhone(registerBean.getPhone()) ==1) {
			errorMessage+=" phone";
		}
		if(errorMessage.length()!=0) {
			throw new ApiRequestException(errorMessage);
		}
		
		userRepo.storeUserData(registerBean.getUsername(),registerBean.getPassword());
		User user=userRepo.findByUsername(registerBean.getUsername());
		registerRepo.storeRegisterData(registerBean.getEmail(), registerBean.getPhone(), registerBean.getDob(), 1, user.getUser_UUID());
		return "registered";
		
	}
}
