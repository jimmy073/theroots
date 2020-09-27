package com.repository.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.activity.HomeRoom;

@Repository
public interface HomeRoomRepository extends JpaRepository<HomeRoom, Long> {

}
