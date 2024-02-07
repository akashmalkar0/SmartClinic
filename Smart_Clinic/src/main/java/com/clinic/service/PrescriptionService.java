package com.clinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.entity.*;
import com.clinic.repository.*;

@Service
public class PrescriptionService {

	@Autowired
	private PrescriptionRepository preRepo;

	public List<Prescription> getAllPrescription() {
		return preRepo.findAll();
	}

	public void save(Prescription prescription) {
		preRepo.save(prescription);
	}

	public Prescription getById(Long id) {
		Optional<Prescription> optional = preRepo.findById(id);
		Prescription prescription = null;
		if (optional.isPresent())
			prescription = optional.get();
		else
			throw new RuntimeException("Prescription not found for id : " + id);
		return prescription;
	}

	public void deleteById(long id) {
		preRepo.deleteById(id);
	}

}
