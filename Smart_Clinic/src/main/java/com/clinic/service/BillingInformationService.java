package com.clinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinic.entity.*;
import com.clinic.repository.*;

@Service
public class BillingInformationService {

	@Autowired
	private BillingInformationRepository billRepo;

	public List<BillingInformation> getAllBillingInformation() {
		return billRepo.findAll();
	}

	public void save(BillingInformation billingInformation) {
		billRepo.save(billingInformation);
	}

	public BillingInformation getById(Long id) {
		Optional<BillingInformation> optional = billRepo.findById(id);
		BillingInformation billingInformation = null;
		if (optional.isPresent())
			billingInformation = optional.get();
		else
			throw new RuntimeException("BillingInformation not found for id : " + id);
		return billingInformation;
	}

	public void deleteViaId(long id) {
		billRepo.deleteById(id);
	}
}
