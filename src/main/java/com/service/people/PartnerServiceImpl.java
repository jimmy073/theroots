package com.service.people;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.people.Partner;
import com.repository.people.PartnerRepository;

@Service
public class PartnerServiceImpl implements PartnerService {

	private PartnerRepository partnerRepository;
	
	@Autowired	
	public void setPartnerRepository(PartnerRepository partnerRepository) {
		this.partnerRepository = partnerRepository;
	}

	@Override
	public Partner savePartner(Partner partner) {
		return partnerRepository.save(partner);
	}

	@Override
	public Partner findOnePartner(Long id) {
		Optional<Partner> partner = this.partnerRepository.findById(id);
        if (partner.isPresent())
            return partner.get();
        else
            throw new RuntimeException("Not found Exception");
	}

	@Override
	public List<Partner> listOfPartners() {
		return partnerRepository.findAll();
	}

}
