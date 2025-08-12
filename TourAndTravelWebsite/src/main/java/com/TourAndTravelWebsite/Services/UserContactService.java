package com.TourAndTravelWebsite.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TourAndTravelWebsite.Models.UserContact;
import com.TourAndTravelWebsite.Repository.UserContactRepository;

@Service
public class UserContactService {
	
	@Autowired
	private UserContactRepository usercontactRepo;
	
	// For Get User Contact
	public void addUserContact(UserContact usercontact) {
		usercontactRepo.save(usercontact);
	}
	
	public List<UserContact> getUserContact(){
		return usercontactRepo.findAll();
	}

}
