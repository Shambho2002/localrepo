package com.TourAndTravelWebsite.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TourAndTravelWebsite.Models.UserRegisteration;
import com.TourAndTravelWebsite.Repository.UserRegisterationRepository;

@Service
public class UserRegisterationService {
	
	@Autowired
	private UserRegisterationRepository userregisterationRepository;
	
	// For user Register
	public void addUserRegister(UserRegisteration userregisteration) {
		userregisterationRepository.save(userregisteration);
	}
	
	public List<UserRegisteration> getUserRegister(){
		return userregisterationRepository.findAll();
	}
	
	public UserRegisteration findByEmail(String email) {
	    return userregisterationRepository.findByEmail(email);
	}
	
	public UserRegisteration getUserRegister(Long id) {
	    return userregisterationRepository.findById(id).orElse(null);
	}

	public UserRegisteration findById(Long userId) {
		// TODO Auto-generated method stub
		return getUserRegister(userId);
	}



}
