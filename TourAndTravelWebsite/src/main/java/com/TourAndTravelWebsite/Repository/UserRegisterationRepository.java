package com.TourAndTravelWebsite.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TourAndTravelWebsite.Models.UserRegisteration;

@Repository
public interface UserRegisterationRepository extends JpaRepository<UserRegisteration, Long>{
	UserRegisteration findByEmail(String email);


}
