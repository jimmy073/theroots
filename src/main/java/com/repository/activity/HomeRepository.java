package com.repository.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.activity.Home;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long>{

}
