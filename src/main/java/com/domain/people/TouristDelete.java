package com.domain.people;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.domain.activity.TouristPackage;
import com.domain.finance.Discount;

@Entity
public class TouristDelete {

	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nationalId;
	private String firstName;
	private String lastName;
	private Date dob;
	private String nationality;
	private String profession;
	@ManyToOne(cascade = CascadeType.ALL)
	private Discount personalDiscount;
	@ManyToOne(cascade = CascadeType.ALL)
	private TouristPackage tourPackage;
	
	public TouristDelete() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Discount getPersonalDiscount() {
		return personalDiscount;
	}
	
	public void setPersonalDiscount(Discount personalDiscount) {
		this.personalDiscount = personalDiscount;
	}

	public TouristPackage getTourPackage() {
		return tourPackage;
	}

	public void setTourPackage(TouristPackage tourPackage) {
		this.tourPackage = tourPackage;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getNationalId() {
		return nationalId;
	}
	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	
	
}
