package com.domain.finance;

import java.util.List;

import javax.persistence.*;

import com.domain.people.TouristDelete;

@Entity
public class Discount {

	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(unique = true, nullable = false)
	private String name;
	private double percentage;
	@OneToMany(mappedBy = "personalDiscount")
	private List<TouristDelete> tourists;
	
	public List<TouristDelete> getTourists() {
		return tourists;
	}
	public void setTourists(List<TouristDelete> tourists) {
		this.tourists = tourists;
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
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	@Override
	public String toString() {
		return  name;
	}
	
	
	
}
