package com.TourAndTravelWebsite.Models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	public UserBooking() {
		super();
	}
	public UserBooking(String whereTo, Integer howMany, Date arrivals, Date leaving) {
		super();
		this.whereTo = whereTo;
		this.howMany = howMany;
		this.arrivals = arrivals;
		this.leaving = leaving;
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
	
	

}
