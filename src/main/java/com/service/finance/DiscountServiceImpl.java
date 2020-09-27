package com.service.finance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.finance.Discount;
import com.repository.finance.DiscountRepository;
@Service
public class DiscountServiceImpl implements DiscountService {

	
	private DiscountRepository discountRepository;
	
	@Autowired	
	public void setDiscountRepository(DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}

	@Override
	public Discount saveDiscount(Discount discount) {
		return discountRepository.save(discount);
	}

	@Override
	public Discount findDiscount(String name) {
		return discountRepository.findDiscountByName(name);
	}

	@Override
	public List<Discount> listOfDicount() {
		return discountRepository.findAll();
	}


	
	

}
