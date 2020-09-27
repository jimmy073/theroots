package com.service.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.activity.Tent;
import com.repository.activity.TentRepository;

@Service
public class TentServiceImpl implements TentService {
	
	private TentRepository tentRepository;
	
	@Autowired
	public void setTentRepository(TentRepository tentRepository) {
		this.tentRepository = tentRepository;
	}

	@Override
	public Tent saveTent(Tent tent) {
		return tentRepository.save(tent);
	}

	@Override
	public Tent findTent(Long id) {
		Optional<Tent> tent = this.tentRepository.findById(id);
        if (tent.isPresent())
            return tent.get();
        else
            throw new RuntimeException("Not found Exception");
	}

	@Override
	public List<Tent> listOfTents() {
		return tentRepository.findAll();
	}

	

}
