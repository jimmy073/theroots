package com.service.people;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.activity.TouristPackage;
import com.domain.people.TouristDelete;
import com.repository.people.TouristRepository;

@Service
public class TouristServiceImpl implements TouristService {
	
	private TouristRepository touristRepository;
	
	@Autowired
	public void setTouristRepository(TouristRepository touristRepository) {
		this.touristRepository = touristRepository;
	}

	@Override
	public TouristDelete saveTourist(TouristDelete tourist) {
		return touristRepository.save(tourist);
	}

	@Override
	public List<TouristDelete> lisOfTourists() {
		return touristRepository.findAll();
	}

	@Override
	public List<TouristDelete> listOfTouristInPackage(TouristPackage touristPackage) {
		return touristRepository.findByTourPackage(touristPackage);
	}

	

	

}
