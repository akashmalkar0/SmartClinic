package com.clinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.entity.*;
import com.clinic.repository.*;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository docRepo;

	public List<Doctor> getAllDoctor() {
		return docRepo.findAll();
	}

	public void save(Doctor doctor) {
		docRepo.save(doctor);
	}

	public Doctor getById(Long id) {
		Optional<Doctor> optional = docRepo.findById(id);
		Doctor doctor = null;
		if (optional.isPresent())
			doctor = optional.get();
		else
			throw new RuntimeException("Doctor not found for id : " + id);
		return doctor;
	}

	public void deleteById(long id) {
		docRepo.deleteById(id);
	}

}
