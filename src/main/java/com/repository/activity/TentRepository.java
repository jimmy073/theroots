package com.repository.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.activity.Tent;

@Repository
public interface TentRepository extends JpaRepository<Tent, Long> {

}
