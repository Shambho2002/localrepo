package com.TourAndTravelWebsite.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TourAndTravelWebsite.Models.UserBooking;
import com.TourAndTravelWebsite.Repository.UserBookingRepository;

@Service
public class UserBookingService {
	
	@Autowired
	private UserBookingRepository userbookingRepo;
	
	// For User Booking
	public void addUserBooking(UserBooking userbooking) {
		userbookingRepo.save(userbooking);
	}
	
	public List<UserBooking> getUserBooking(){
		return userbookingRepo.findAll();
	}

}
