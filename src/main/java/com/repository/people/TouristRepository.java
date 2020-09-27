package com.repository.people;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.activity.TouristPackage;
import com.domain.people.TouristDelete;

@Repository
public interface TouristRepository extends JpaRepository<TouristDelete, Long> {

	List<TouristDelete> findByTourPackage(TouristPackage touristPackage);
	
}
