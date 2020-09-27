package com.repository.activity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.activity.TouristPackage;
import com.domain.people.User;

@Repository
public interface TouristPackageRepository extends JpaRepository<TouristPackage, Long> {

	List<TouristPackage> findTouristPackagesByPackageOwner(User user);
	
}
