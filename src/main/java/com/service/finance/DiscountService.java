package com.service.finance;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domain.finance.Discount;

@Service
public interface DiscountService {

	Discount saveDiscount(Discount discount);
	
	Discount findDiscount(String name);
	
	List<Discount> listOfDicount();
	
}
