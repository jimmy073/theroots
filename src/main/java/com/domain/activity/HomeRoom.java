package com.domain.activity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class HomeRoom {

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable=false, unique=true)
	private String roomNumber;
	private String status;
	private double price;
	private String type;
	@ManyToOne
	private Home home;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Home getHome() {
		return home;
	}
	public void setHome(Home home) {
		this.home = home;
	}
	
	
	
}
