package com.TourAndTravelWebsite.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TourAndTravelWebsite.Models.UserLogin;
import com.TourAndTravelWebsite.Repository.UserLoginRepository;

@Service
public class UserLoginService {
	
	@Autowired
	private UserLoginRepository userloginRepository;
	
	// For User Login
	public void addUserLogin(UserLogin userlogin) {
		userloginRepository.save(userlogin);
	}
	
	public List<UserLogin> getUserLogin(){
		return userloginRepository.findAll();
	}

}
