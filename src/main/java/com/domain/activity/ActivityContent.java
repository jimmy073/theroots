package com.domain.activity;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
public class ActivityContent {
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable=false, unique=true)
	private String name;
	private double price;
	private double distance;
	private String photoOne;
	private String photoTwo;
	private String status;
	@ManyToOne
	private Activity activity;
	@ManyToMany
	private Collection<TouristPackage> tourPackage;
	
	public Long getId() {
		return id;
	}
		
	public String getStatus() {
		return status;
	}

	public Collection<TouristPackage> getTourPackage() {
		return tourPackage;
	}

	public void setTourPackage(Collection<TouristPackage> tourPackage) {
		this.tourPackage = tourPackage;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String contentName) {
		this.name = contentName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getPhotoOne() {
		return photoOne;
	}
	public void setPhotoOne(String photoOne) {
		this.photoOne = photoOne;
	}
	public String getPhotoTwo() {
		return photoTwo;
	}
	public void setPhotoTwo(String photoTwo) {
		this.photoTwo = photoTwo;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	
	

}
