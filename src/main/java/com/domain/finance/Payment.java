package com.domain.finance;

import java.util.Date;

import javax.persistence.*;

import com.domain.activity.TouristPackage;
import com.domain.people.User;

@Entity
public class Payment {
	
	@Id@GeneratedValue
	private Long id;
	@Column(unique=true)
	private String paymentId;
	private double paymentAmount;
	private double paidAmount;
	private double remainedAmount;
	private String paymentMode;
	private String paymentType;
	@ManyToOne
	private User paidBy;
	@OneToOne
	private TouristPackage tourPackage;
	private Date paymentDate;
	private Date paymentDeadline;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long id) {
		this.paymentId = "P"+id;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getRemainedAmount() {
		return remainedAmount;
	}
	public void setRemainedAmount(double remainedAmount) {
		this.remainedAmount = remainedAmount;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public User getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(User paidBy) {
		this.paidBy = paidBy;
	}
	public TouristPackage getTourPackage() {
		return tourPackage;
	}
	public void setTourPackage(TouristPackage tourPackage) {
		this.tourPackage = tourPackage;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Date getPaymentDeadline() {
		return paymentDeadline;
	}
	public void setPaymentDeadline(Date paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}
	
	

}
