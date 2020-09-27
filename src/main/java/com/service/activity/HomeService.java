package com.service.activity;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domain.activity.Home;


@Service
public interface HomeService {

    Home saveHome(Home home);
	
	Home findHome(Long id);
	
	List<Home> listOfHomes();
	
}
