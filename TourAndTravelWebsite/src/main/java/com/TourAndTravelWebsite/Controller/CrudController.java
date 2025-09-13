package com.TourAndTravelWebsite.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.TourAndTravelWebsite.Models.UserBooking;
import com.TourAndTravelWebsite.Models.UserContact;
import com.TourAndTravelWebsite.Models.UserRegisteration;
import com.TourAndTravelWebsite.Services.UserBookingService;
import com.TourAndTravelWebsite.Services.UserContactService;
import com.TourAndTravelWebsite.Services.UserRegisterationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CrudController {
	
	@Autowired
	private UserBookingService userbookingService;
	
	@Autowired
	private UserContactService usercontactService;
	
	@Autowired
	private UserRegisterationService userregisterationService;
	
	@GetMapping("/index")
	public String showHomePage(HttpSession session, Model model) {
	    UserRegisteration user = (UserRegisteration) session.getAttribute("loggedInUser");
	    if (user != null) {
	        model.addAttribute("user", user); // ensure user is available to the view
	    }
	    return "index";
	}
	
	@GetMapping("/contact")
	public String showContactPage() {
		return "contact";
	}
	
	@PostMapping("/booking/")
	public String handleBookingWithoutUser() {
		return "please-login";
	}
	
	// GET /booking - Show booking form
    @GetMapping("/booking")
    public String showBookingPage(HttpSession session, Model model) {
        UserRegisteration loggedInUser = (UserRegisteration) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("actionUrl", "/booking/" + loggedInUser.getId());
        } else {
            model.addAttribute("actionUrl", "/please-login");
        }
        return "booking"; // booking.html must exist in templates/
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
	@GetMapping("/please-login")
	public String showPleaseLoginPage() {
		return "please-login";
	}
	@GetMapping("/login-success")
	public String showLoginSuccessPage() {
		return "login-success";
	}
	@GetMapping("/terms&condition")
	public String showTermsAndConditionPage(){
		return "terms&condition";
	}
	@GetMapping("/privacy")
	public String showPrivacyPage() {
		return "privacy";
	}
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        // Redirect to home page or login page
        return "redirect:/";
    }
	
	@PostMapping("/booking/{userId}")
	public String getBookingForm(@PathVariable Long userId,
	                             @RequestParam("whereTo") String whereTo,
	                             @RequestParam("howMany") Integer howMany,
	                             @RequestParam("arrivals") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivals,
	                             @RequestParam("leaving") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate leaving,
	                             Model model,
	                             HttpSession session) {

		// Check if logged in
	    UserRegisteration loggedInUser = (UserRegisteration) session.getAttribute("loggedInUser");
	    if (loggedInUser == null) {
	        return "please-login";
	    }
	    // Fetch user from DB
	    UserRegisteration user = userregisterationService.getUserRegister(userId);
	    if (user == null) {
	        throw new IllegalArgumentException("User not found with id: " + userId);
	    }

	    // Convert LocalDate to java.sql.Date for persistence
	    java.sql.Date sqlArrivals = java.sql.Date.valueOf(arrivals);
	    java.sql.Date sqlLeaving = java.sql.Date.valueOf(leaving);

	    // Create booking
	    UserBooking userbooking = new UserBooking(whereTo, howMany, sqlArrivals, sqlLeaving, user);
	    userbookingService.addUserBooking(userbooking);

	    model.addAttribute("userbooking", userbookingService.getUserBooking());

	    return "booking-thankyou";
	}
	
	@PostMapping("/booking")
	public String handleBookingWithoutUser(HttpSession session) {
	    UserRegisteration loggedInUser = (UserRegisteration) session.getAttribute("loggedInUser");
	    if (loggedInUser == null) {
	        return "redirect:/please-login";
	    }
	    // save booking for loggedInUser
	    return "redirect:/booking/" + loggedInUser.getId();
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
	                                   @RequestParam("confirmPassword") String confirmPassword,
	                                   @RequestParam(value = "profilePhoto", required = false) MultipartFile profilePhoto,
	                                   Model model) {

	    String profilePhotoUrl = "/images/default-avatar.png"; // default avatar

	    try {
	        if (profilePhoto != null && !profilePhoto.isEmpty()) {
	            String fileName = UUID.randomUUID() + "_" + profilePhoto.getOriginalFilename();
	            
	            // Use an absolute folder outside src
	            Path uploadPath = Paths.get("C:/TourUploads");
	            Files.createDirectories(uploadPath);
	            profilePhoto.transferTo(uploadPath.resolve(fileName).toFile());

	            profilePhotoUrl = "/uploads/" + fileName; // URL for frontend
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        model.addAttribute("error", "Photo upload failed. Default avatar used.");
	    }

	    UserRegisteration userregisteration =
	            new UserRegisteration(name, email, password, confirmPassword, profilePhotoUrl);

	    userregisterationService.addUserRegister(userregisteration);
	    model.addAttribute("userregisteration", userregisteration);

	    return "registeration-success";
	}



	
	@PostMapping("/login")
    public String processLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        List<UserRegisteration> users = userregisterationService.getUserRegister();

        for (UserRegisteration user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                session.setAttribute("loggedInUser", user);
                return "redirect:/login/" + user.getId();
            }
        }

        model.addAttribute("error", "Invalid email or password");
        return "login-not-success";
    } // <-- Make sure this closing brace is present

    @GetMapping("/login/{userId}")
    public String userHomePage(@PathVariable Long userId, Model model, HttpSession session) {
        UserRegisteration user = userregisterationService.findById(userId);
        model.addAttribute("user", user);
        session.setAttribute("loggedInUser", user); // ensure session has updated user
        
        return "index";
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












