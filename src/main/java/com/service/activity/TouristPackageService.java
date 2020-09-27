package com.service.activity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.domain.activity.TouristPackage;
import com.domain.people.User;

@Service
public interface TouristPackageService {

	TouristPackage saveTourPackage(TouristPackage touristPackage);
	
	TouristPackage findTourPackage(Long id);

	List<TouristPackage> listOfTouristPackages();
	
	List<TouristPackage> listOfUserPackages(User user);
	
	double packageCost(TouristPackage tourPackage);
	
	// Page<TouristPackage> tourPackagesPage(int pageNo, int pageSize);
	
}
