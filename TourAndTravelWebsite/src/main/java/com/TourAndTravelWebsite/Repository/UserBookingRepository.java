package com.TourAndTravelWebsite.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TourAndTravelWebsite.Models.UserBooking;

@Repository
public interface UserBookingRepository extends JpaRepository<UserBooking, Long>{

}
