package com.prosantosgui.techunter.services;

import com.prosantosgui.techunter.model.Recruiter;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RecruiterService {

	List<Recruiter> findAll();

	Recruiter findById(String id);

	ResponseEntity<Recruiter> saveRecruiter(Recruiter recruiter);

	ResponseEntity<Recruiter> deleteById(String id);
}
