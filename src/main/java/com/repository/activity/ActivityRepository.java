package com.repository.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.activity.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{

}
