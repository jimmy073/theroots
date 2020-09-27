package com.domain.people;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Partner {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable=false, unique = true)
	private String name;
	private String type;
	private double discount;
	@OneToOne
	private User user;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String partnerName) {
		this.name = partnerName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	

}
