package com.example.jpa1ap;

import com.example.jpa1ap.entities.Patient;
import com.example.jpa1ap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import static jdk.internal.org.jline.utils.Colors.h;

@SpringBootApplication
public class Jpa1ApApplication implements CommandLineRunner {

	private PatientRepository patientRepository;

	public Jpa1ApApplication(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Jpa1ApApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(
				new Patient(null, "Soufiane", new Date(), false, 19));
		patientRepository.save(
				new Patient(null, "loriga", new Date(), true, 199));
		patientRepository.save(
				new Patient(null, "ahmed", new Date(), false, 16));
		patientRepository.save(
				new Patient(null, "oussama", new Date(), true, 666));
		patientRepository.save(
				new Patient(null, "yassin", new Date(), true, 450));
		patientRepository.save(
				new Patient(null, "anas", new Date(), true, 777));

		patientRepository.findAll().forEach(p -> {
			System.out.println(p.getNom());
		});
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}

