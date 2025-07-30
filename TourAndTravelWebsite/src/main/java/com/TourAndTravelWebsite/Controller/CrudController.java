package com.TourAndTravelWebsite.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TourAndTravelWebsite.Models.UserBooking;
import com.TourAndTravelWebsite.Models.UserContact;
import com.TourAndTravelWebsite.Models.UserRegisteration;
import com.TourAndTravelWebsite.Services.UserBookingService;
import com.TourAndTravelWebsite.Services.UserContactService;
import com.TourAndTravelWebsite.Services.UserRegisterationService;

@Controller
public class CrudController {
	
	@Autowired
	private UserBookingService userbookingService;
	
	@Autowired
	private UserContactService usercontactService;
	
	@Autowired
	private UserRegisterationService userregisterationService;
	
	@GetMapping("/index")
	public String showHomePage() {
		return "index";
	}
	
	@GetMapping("/contact")
	public String showContactPage() {
		return "contact";
	}
	
	@GetMapping("/booking")
	public String showBookingPage() {
		return "booking";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/registeration")
	public String showRegisterationPage() {
		return "registeration";
	}
	
	@GetMapping("/registeration-success")
	public String showRegisterationSuccessPage() {
		return "registeration-success";
	}
	
	@GetMapping("/format-password")
	public String showFormatPasswordPage() {
		return "format-password";
	}
	
	@GetMapping("/confirmation")
	public String showConfirmationPage() {
		return "confirmation";
	}
	
	@PostMapping("/booking")
	public String getBookingForm(@RequestParam("whereTo") String whereTo, 
								@RequestParam("howMany") Integer howMany, 
								@RequestParam("arrivals") Date arrivals, 
								@RequestParam("leaving") Date leaving, Model model) {
		UserBooking userbooking = new UserBooking(whereTo,howMany,arrivals,leaving);
		userbooking.setWhereTo(whereTo);
		userbooking.setHowMany(howMany);
		userbooking.setArrivals(arrivals);
		userbooking.setLeaving(leaving);
		userbookingService.addUserBooking(userbooking);
		model.addAttribute("userbooking", userbookingService.getUserBooking());
		return "booking-thankyou";
	}
	
	@PostMapping("/contact")
	public String getContactForm(@RequestParam("name") String name, 
								@RequestParam("email") String email, 
								@RequestParam("number") Long number, 
								@RequestParam("subject") String subject, 
								@RequestParam("message") String message, Model model) {
		UserContact usercontact = new UserContact(name,email,number,subject,message);
		usercontact.setName(name);
		usercontact.setEmail(email);
		usercontact.setNumber(number);
		usercontact.setSubject(subject);
		usercontact.setMessage(message);
		usercontactService.addUserContact(usercontact);
		//model.addAttribute("confirmation", "Your message has been successfully sent!!");
		model.addAttribute("usercontact", usercontactService.getUserContact());
		return "contact-thankyou";
	}
	
	@PostMapping("/registeration")
	public String getRegisterationPage(@RequestParam("name") String name,
										@RequestParam("email") String email,
										@RequestParam("password") String password,
										@RequestParam("confirmPassword")String confirmPassword, Model model) {
		UserRegisteration userregisteration = new UserRegisteration(name,email,password,confirmPassword);
		userregisteration.setName(name);
		userregisteration.setEmail(email);
		userregisteration.setConfirmPassword(confirmPassword);
		userregisteration.setPassword(password);
		userregisterationService.addUserRegister(userregisteration);
		model.addAttribute("userregisteration", userregisterationService.getUserRegister());
		return "registeration-success";
	}
	
	@PostMapping("/login")
	public String getLoginPage(
	        @RequestParam("email") String email,
	        @RequestParam("password") String password,
	        Model model
	) {
	    // get all registered users
	    List<UserRegisteration> users = userregisterationService.getUserRegister();

	    // simple search
	    boolean matched = false;
	    for (UserRegisteration user : users) {
	        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
	            matched = true;
	            break;
	        }
	    }

	    if (matched) {
	        return "login-success";
	    } else {
	        model.addAttribute("error", "Invalid email or password");
	        return "login-not-success";  // redisplay login form
	    }
	}
	
	@PostMapping("/format-password")
	public String handleForgotPassword(
	        @RequestParam("email") String email,
	        @RequestParam("password") String password,
	        @RequestParam("confirmPassword") String confirmPassword,
	        Model model) {
	    
	    // Check if user exists
	    UserRegisteration user = userregisterationService.findByEmail(email);
	    
	    if (user == null) {
	        model.addAttribute("error", "No user found with this email.");
	        return "format-password";
	    }
	    
	    if (password == null || password.isBlank()) {
	        model.addAttribute("error", "Password cannot be blank.");
	        return "format-password";
	    }
	    
	    if (!password.equals(confirmPassword)) {
	        model.addAttribute("error", "Passwords do not match.");
	        return "format-password";
	    }
	    
	    // update the password
	    user.setPassword(password);
	    user.setConfirmPassword(confirmPassword);
	    
	    userregisterationService.addUserRegister(user); // will save the updated user
	    model.addAttribute("message", "Password successfully updated!");
	    
	    return "confirmation"; // create this page to confirm password change
	}




	

}












