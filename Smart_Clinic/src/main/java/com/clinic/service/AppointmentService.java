package com.clinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.entity.*;
import com.clinic.repository.*;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository apoRepo;

	public List<Appointment> getAllAppointment() {
		return apoRepo.findAll();
	}

	public void save(Appointment appointment) {
		apoRepo.save(appointment);
	}

	public Appointment getById(Long id) {
		Optional<Appointment> optional = apoRepo.findById(id);
		Appointment appointment = null;
		if (optional.isPresent())
			appointment = optional.get();
		else
			throw new RuntimeException("Appointment not found for id : " + id);
		return appointment;
	}

	public void deleteViaId(long id) {
		apoRepo.deleteById(id);
	}
}
