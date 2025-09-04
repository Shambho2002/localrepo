package com.TourAndTravelWebsite.Models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Booking")
public class UserBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String whereTo;
	private Integer howMany;
	private Date arrivals;
	private Date leaving;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserRegisteration user;

	public UserBooking() {
		super();
	}

	public UserBooking(String whereTo, Integer howMany, Date arrivals, Date leaving, UserRegisteration user) {
		super();
		this.whereTo = whereTo;
		this.howMany = howMany;
		this.arrivals = arrivals;
		this.leaving = leaving;
		this.user = user;
	}

	public String getWhereTo() {
		return whereTo;
	}

	public void setWhereTo(String whereTo) {
		this.whereTo = whereTo;
	}

	public Integer getHowMany() {
		return howMany;
	}

	public void setHowMany(Integer howMany) {
		this.howMany = howMany;
	}

	public Date getArrivals() {
		return arrivals;
	}

	public void setArrivals(Date arrivals) {
		this.arrivals = arrivals;
	}

	public Date getLeaving() {
		return leaving;
	}

	public void setLeaving(Date leaving) {
		this.leaving = leaving;
	}

	public UserRegisteration getUser() {
		return user;
	}

	public void setUser(UserRegisteration user) {
		this.user = user;
	}

	

	

	
	

	

	

	
	
	
	
	

}
