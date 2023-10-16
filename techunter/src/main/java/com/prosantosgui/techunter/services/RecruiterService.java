package com.prosantosgui.techunter.services;

import com.prosantosgui.techunter.model.Recruiter;
import com.prosantosgui.techunter.repositories.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RecruiterService {

	@Autowired
	private RecruiterRepository recruiterRepository;

	public List<Recruiter> findAll() {
		return recruiterRepository.findAll();
	}

	public Recruiter findById(String id) {
		Optional<Recruiter> obj = recruiterRepository.findById(id);
		return obj.get();
	}
}
