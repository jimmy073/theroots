package com.repository.people;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.people.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>{

}
