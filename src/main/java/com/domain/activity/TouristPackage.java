package com.domain.activity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.domain.finance.Payment;
import com.domain.people.TouristDelete;
import com.domain.people.User;

@Entity
public class TouristPackage {

	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable=false)
	private String name;
	private Date startDate;
	private Date endDate;
	private double amount;
	private double numberOfTourists;
	private double numberOfContents;
	private double discountAmount;
	private String staffApproval;
	@ManyToOne
	private User packageOwner;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<TouristDelete> tourists;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "packages_contents",
            joinColumns = {@JoinColumn(name = "tour_id")},
            inverseJoinColumns = {@JoinColumn(name = "content_id")}
    )	
	private Collection<ActivityContent> contents;
	private double packageDiscount;
	private double totalPackageCost;
	@OneToOne
	private Payment packagePayment;
	
	
	public int getTouristsNumber() {
		return getTourists().size();
	}
	public int getContentsNumber() {
		return contents.size();
	}
	
	// SETTERS AND GETTERS
	
	public List<TouristDelete> getTourists() {
		return tourists;
	}
	public void setTourists(List<TouristDelete> tourists) {
		this.tourists = tourists;
	}
	public double getPackageDiscount() {
		return packageDiscount;
	}
	public void setPackageDiscount(double packageDiscount) {
		this.packageDiscount = packageDiscount;
	}
	public User getPackageOwner() {
		return packageOwner;
	}
	
	public void setPackageOwner(User packageOwner) {
		this.packageOwner = packageOwner;
	}
	
	public Collection<ActivityContent> getContents() {
		return contents;
	}
	public void setContents(Collection<ActivityContent> contents) {
		this.contents = contents;
	}
	public void setContents(List<ActivityContent> contents) {
		this.contents = contents;
	}
	
	
	public String getStaffApproval() {
		return staffApproval;
	}
	public void setStaffApproval(String staffApproval) {
		this.staffApproval = staffApproval;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getNumberOfTourists() {
		return numberOfTourists;
	}
	public void setNumberOfTourists(double numberOfTourists) {
		this.numberOfTourists = numberOfTourists;
	}
	public double getNumberOfContents() {
		return numberOfContents;
	}
	public void setNumberOfContents(double numberOfContents) {
		this.numberOfContents = numberOfContents;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public double getTotalPackageCost() {
		return totalPackageCost;
	}
	public void setTotalPackageCost(double totalPackageCost) {
		this.totalPackageCost = totalPackageCost;
	}
	public Payment getPackagePayment() {
		return packagePayment;
	}
	public void setPackagePayment(Payment packagePayment) {
		this.packagePayment = packagePayment;
	}
	
	
	
	
	
	
}
