package com.service.activity;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domain.activity.Tent;

@Service
public interface TentService {

Tent saveTent(Tent tent);
	
	Tent findTent(Long id);
	
	List<Tent> listOfTents();
}
