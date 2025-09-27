package com.TourAndTravelWebsite.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomExceptionHandler implements ErrorController {
	
	@RequestMapping("/error")
	public String handleError() {
		return "404"; // your templates/404.html
	}

}
