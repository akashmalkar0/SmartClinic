package com.clinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.entity.*;
import com.clinic.repository.*;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patRepo;

	public List<Patient> getAllPatient() {
		return patRepo.findAll();
	}

	public void save(Patient patient) {
		patRepo.save(patient);
	}

	public Patient getById(Long id) {
		Optional<Patient> optional = patRepo.findById(id);
		Patient patient = null;
		if (optional.isPresent())
			patient = optional.get();
		else
			throw new RuntimeException("Patient not found for id : " + id);
		return patient;
	}

	public void deleteById(long id) {
		patRepo.deleteById(id);
	}

}
