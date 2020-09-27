package com.service.people;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domain.activity.TouristPackage;
import com.domain.people.TouristDelete;

@Service
public interface TouristService {

	TouristDelete saveTourist(TouristDelete tousrist);
	
	List<TouristDelete> lisOfTourists();
	
	List<TouristDelete> listOfTouristInPackage(TouristPackage touristPackage);
	
}
