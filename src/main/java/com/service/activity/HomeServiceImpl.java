package com.service.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.activity.Home;
import com.repository.activity.HomeRepository;

@Service
public class HomeServiceImpl implements HomeService {
	
	private HomeRepository homeRepository;
	
	@Autowired
	public void setHomeRepository(HomeRepository homeRepository) {
		this.homeRepository = homeRepository;
	}

	@Override
	public Home saveHome(Home home) {
		return homeRepository.save(home);
	}

	@Override
	public Home findHome(Long id) {
		Optional<Home> home = this.homeRepository.findById(id);
        if (home.isPresent())
            return home.get();
        else
            throw new RuntimeException("Not found Exception");
	}

	@Override
	public List<Home> listOfHomes() {
		return homeRepository.findAll();
	}

	
}
