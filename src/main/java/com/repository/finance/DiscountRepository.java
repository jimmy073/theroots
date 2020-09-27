package com.repository.finance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.finance.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

	Discount findDiscountByName(String name);
	
}
