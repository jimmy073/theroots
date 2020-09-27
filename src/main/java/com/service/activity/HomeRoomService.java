package com.service.activity;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domain.activity.HomeRoom;

@Service
public interface HomeRoomService {

	HomeRoom saveHomeRoom(HomeRoom homeRoom);
	
	HomeRoom findHomeRoom(Long id);
	
	List<HomeRoom> listOfHomeRooms();
	
}
