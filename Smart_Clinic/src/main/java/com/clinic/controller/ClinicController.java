package com.clinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.clinic.entity.*;
import com.clinic.service.*;

import jakarta.validation.Valid;

@Controller
public class ClinicController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private BillingInformationService billingInformationService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private PrescriptionService prescriptionService;

	private UserService userService;

	public ClinicController(UserService userService) {
		this.userService = userService;
	}

	// handler method to handle home page request
	@GetMapping("/index")
	public String home() {
		return "index";
	}

	// handler method to handle login request
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/service")
	public String service() {
		return "service";
	}

	// handler method to handle user registration form request
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		// create model object to store form data
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "register";
	}

	// handler method to handle user registration form submit request
	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
		User existingUser = userService.findUserByEmail(userDto.getEmail());

		if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
		}

		if (result.hasErrors()) {
			model.addAttribute("user", userDto);
			return "/register";
		}

		userService.saveUser(userDto);
		return "redirect:/register?success";
	}

	// handler method to handle list of users
	@GetMapping("/users")
	public String users(Model model) {
		List<UserDto> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

	@GetMapping("/appointments")
	public String viewAppointments(Model model) {
		model.addAttribute("allAppointments", appointmentService.getAllAppointment());
		return "appointments";
	}

	@GetMapping("/appointments/add")
	public String addNewAppointment(Model model) {
		Appointment appointment = new Appointment();
		model.addAttribute("appointment", appointment);
		return "newAppointment";
	}

	@PostMapping("/appointments/save")
	public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
		appointmentService.save(appointment);
		return "redirect:/appointments";
	}

	@GetMapping("/appointments/edit/{id}")
	public String editAppointmentForm(@PathVariable(value = "id") long id, Model model) {
		Appointment appointment = appointmentService.getById(id);
		model.addAttribute("appointment", appointment);
		return "updateAppointment";
	}

	@GetMapping("/appointments/delete/{id}")
	public String deleteAppointmentById(@PathVariable(value = "id") long id) {
		appointmentService.deleteViaId(id);
		return "redirect:/appointments";
	}

	@GetMapping("/billing")
	public String viewBillingInformationPage(Model model) {
		model.addAttribute("allBillingInfo", billingInformationService.getAllBillingInformation());
		return "billing";
	}

	@GetMapping("/billing/add")
	public String addNewBillingInformation(Model model) {
		BillingInformation billingInformation = new BillingInformation();
		model.addAttribute("billingInformation", billingInformation);
		return "newbillinginfo";
	}

	@PostMapping("/billing/save")
	public String saveBillingInformation(@ModelAttribute("billingInformation") BillingInformation billingInformation) {
		billingInformationService.save(billingInformation);
		return "redirect:/billing";
	}

	@GetMapping("/billing/edit/{id}")
	public String editBillingInfoForm(@PathVariable(value = "id") long id, Model model) {
		BillingInformation billingInformation = billingInformationService.getById(id);
		model.addAttribute("billingInformation", billingInformation);
		return "updatebillinginfo";
	}

	@GetMapping("/billing/delete/{id}")
	public String deleteBillingInfoById(@PathVariable(value = "id") long id) {
		billingInformationService.deleteViaId(id);
		return "redirect:/billing";
	}

	@GetMapping("/doctors")
	public String viewAllDoctors(Model model) {
		model.addAttribute("allDoctors", doctorService.getAllDoctor());
		return "doctors";
	}

	@GetMapping("/doctors/addNew")
	public String addNewDoctor(Model model) {
		Doctor doctor = new Doctor();
		model.addAttribute("doctor", doctor);
		return "newdoctor";
	}

	@PostMapping("/doctors/save")
	public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
		doctorService.save(doctor);
		return "redirect:/doctors";
	}

	@GetMapping("/doctors/showFormForUpdate/{id}")
	public String updateDoctorForm(@PathVariable(value = "id") long id, Model model) {
		Doctor doctor = doctorService.getById(id);
		model.addAttribute("doctor", doctor);
		return "updatedoctor";
	}

	@GetMapping("/doctors/delete/{id}")
	public String deleteDoctorById(@PathVariable(value = "id") long id) {
		doctorService.deleteById(id);
		return "redirect:/doctors";
	}

	@GetMapping("/patients")
	public String viewAllPatients(Model model) {
		model.addAttribute("allPatients", patientService.getAllPatient());
		return "patient";
	}

	@GetMapping("/patients/addNew")
	public String addNewPatientForm(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "patientnew";
	}

	@PostMapping("/patients/save")
	public String savePatient(@ModelAttribute("patient") Patient patient) {
		patientService.save(patient);
		return "redirect:/patients";
	}

	@GetMapping("/patients/showFormForUpdate/{id}")
	public String updatePatientForm(@PathVariable(value = "id") long id, Model model) {
		Patient patient = patientService.getById(id);
		model.addAttribute("patient", patient);
		return "patientupdate";
	}

	@GetMapping("/patients/delete/{id}")
	public String deletePatientById(@PathVariable(value = "id") long id) {
		patientService.deleteById(id);
		return "redirect:/patients";
	}

	@GetMapping("/prescriptions")
	public String viewAllPrescriptions(Model model) {
		model.addAttribute("allPrescriptions", prescriptionService.getAllPrescription());
		return "prescription";
	}

	@GetMapping("/prescriptions/addnew")
	public String addNewPrescription(Model model) {
		Prescription prescription = new Prescription();
		model.addAttribute("prescription", prescription);
		return "prescriptionnew";
	}

	@PostMapping("/prescriptions/save")
	public String savePrescription(@ModelAttribute("prescription") Prescription prescription) {
		prescriptionService.save(prescription);
		return "redirect:/prescriptions";
	}

	@GetMapping("/prescriptions/showFormForUpdate/{id}")
	public String updatePrescriptionForm(@PathVariable(value = "id") long id, Model model) {
		Prescription prescription = prescriptionService.getById(id);
		model.addAttribute("prescription", prescription);
		return "prescriptionupdate";
	}

	@GetMapping("/prescriptions/delete/{id}")
	public String deletePrescription(@PathVariable(value = "id") long id) {
		prescriptionService.deleteById(id);
		return "redirect:/prescriptions";
	}
}
