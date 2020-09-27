package com.service.people;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domain.people.Partner;

@Service
public interface PartnerService {

	 Partner savePartner(Partner partner);
	 
	 Partner findOnePartner(Long id);
	 
	 List<Partner> listOfPartners();
}
