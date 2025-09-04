package com.TourAndTravelWebsite.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TourAndTravelWebsite.Models.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long>{

}
