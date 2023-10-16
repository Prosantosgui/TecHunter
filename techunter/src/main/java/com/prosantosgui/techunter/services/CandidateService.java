package com.prosantosgui.techunter.services;

import com.prosantosgui.techunter.model.Candidate;
import com.prosantosgui.techunter.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	public List<Candidate> findAll() {
		return candidateRepository.findAll();
	}

	public Candidate findById(String id) {
		Optional<Candidate> obj = candidateRepository.findById(id);
		return obj.get();
	}
}
