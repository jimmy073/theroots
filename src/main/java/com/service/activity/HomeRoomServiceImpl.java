package com.service.activity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.activity.HomeRoom;
import com.repository.activity.HomeRoomRepository;

@Service
public class HomeRoomServiceImpl implements HomeRoomService {

	private HomeRoomRepository homeRoomRepository;
	
	@Autowired	
	public void setHomeRoomRepository(HomeRoomRepository homeRoomRepository) {
		this.homeRoomRepository = homeRoomRepository;
	}

	@Override
	public HomeRoom saveHomeRoom(HomeRoom homeRoom) {
		return homeRoomRepository.save(homeRoom);
	}

	@Override
	public HomeRoom findHomeRoom(Long id) {
		Optional<HomeRoom> homeRoom = this.homeRoomRepository.findById(id);
        if (homeRoom.isPresent())
            return homeRoom.get();
        else
            throw new RuntimeException("Not found Exception");
	}

	@Override
	public List<HomeRoom> listOfHomeRooms() {
		return homeRoomRepository.findAll();
	}

}
