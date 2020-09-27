package com.domain.activity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Home {

	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable=false)
	private String name;
	private String photo1;
	private String photo2;
	private String photo3;
	private String photo4;
	@OneToMany(mappedBy = "home", fetch = FetchType.EAGER)
	private List<HomeRoom> rooms;
	
	
	
	public List<HomeRoom> getRooms() {
		return rooms;
	}
	public void setRooms(List<HomeRoom> rooms) {
		this.rooms = rooms;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoto1() {
		return photo1;
	}
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}
	public String getPhoto2() {
		return photo2;
	}
	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}
	public String getPhoto3() {
		return photo3;
	}
	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}
	public String getPhoto4() {
		return photo4;
	}
	public void setPhoto4(String photo4) {
		this.photo4 = photo4;
	}
	
	
	
}
