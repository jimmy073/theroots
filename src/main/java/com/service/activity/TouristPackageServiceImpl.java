package com.service.activity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.activity.ActivityContent;
import com.domain.activity.TouristPackage;
import com.domain.finance.Payment;
import com.domain.people.TouristDelete;
import com.domain.people.User;
import com.repository.activity.TouristPackageRepository;
import com.service.finance.PaymentService;
import com.service.people.TouristService;

@Service
public class TouristPackageServiceImpl implements TouristPackageService {

	private TouristPackageRepository touristPackageRepository;
	private TouristService touristService;
	private PaymentService paymentService;
	
	@Autowired	
	public void setTouristPackageRepository(TouristPackageRepository touristPackageRepository) {
		this.touristPackageRepository = touristPackageRepository;
	}
	
	@Autowired
	public void setTouristService(TouristService touristService) {
		this.touristService = touristService;
	}
	
	@Autowired
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Override
	public List<TouristPackage> listOfTouristPackages() {
		return touristPackageRepository.findAll();
	}

	@Override
	public TouristPackage saveTourPackage(TouristPackage touristPackage) {
		return touristPackageRepository.save(touristPackage);
	}

	@Override
	public List<TouristPackage> listOfUserPackages(User user) {
		return touristPackageRepository.findTouristPackagesByPackageOwner(user);
	}

	@Override
	public TouristPackage findTourPackage(Long id) {
		Optional<TouristPackage> touristPackage = this.touristPackageRepository.findById(id);
        if (touristPackage.isPresent())
            return touristPackage.get();
        else
            throw new RuntimeException("Not found Exception");
	}

	@Override
	public double packageCost(TouristPackage tourPackage) {
		double tourDiscount=0, tourDiscountAmount=0, tourPackageCost, contentsCost=0;
		List<TouristDelete> tourists = touristService.listOfTouristInPackage(tourPackage);
		Collection<ActivityContent> contents =tourPackage.getContents();
		int touristNumber = tourists.size();
		if((tourists.size()>0) && (contents.size()>0)) {
			for(TouristDelete t: tourists) {
				tourDiscount += t.getPersonalDiscount().getPercentage();
			}
			for(ActivityContent act : contents) {
				contentsCost += act.getPrice();
			}
		double tourDiscountPercent = ((tourDiscount/touristNumber)/100);
		
		tourDiscountAmount = (contentsCost * tourDiscountPercent)*touristNumber; //here discount
		
		tourPackageCost = (contentsCost*touristNumber)-tourDiscountAmount;
		
		tourPackage.setPackageDiscount(tourDiscountPercent*100);
		tourPackage.setNumberOfContents(contents.size());
		tourPackage.setNumberOfTourists(tourists.size());
		tourPackage.setAmount(tourPackageCost); //here
		tourPackage.setTotalPackageCost(contentsCost*touristNumber);
		tourPackage.setDiscountAmount(tourDiscountAmount); 
		tourPackage.setPackagePayment(packagePayment(tourPackage));
		saveTourPackage(tourPackage);
		return tourists.size();
		}else {
			tourPackage.setAmount(tourists.size());
			saveTourPackage(tourPackage);
			return tourPackageCost=0;
		}
	}
	
	public Payment packagePayment(TouristPackage tourPackage) {
		Payment payment = new Payment();
		payment.setTourPackage(tourPackage);
		payment.setPaymentAmount(tourPackage.getAmount());
		payment.setPaymentId(payment.getId());
		return paymentService.savePayment(payment);		
	}

}
