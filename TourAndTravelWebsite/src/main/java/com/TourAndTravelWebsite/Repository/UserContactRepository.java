package com.TourAndTravelWebsite.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TourAndTravelWebsite.Models.UserContact;

@Repository
public interface UserContactRepository extends JpaRepository<UserContact, Long>{

}
