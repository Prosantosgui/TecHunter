package com.prosantosgui.techunter.services;

import com.prosantosgui.techunter.model.Candidate;
import org.springframework.http.ResponseEntity;

import java.util.List;



public interface CandidateService {

	List<Candidate> findAll();

	Candidate findById(String id);

	ResponseEntity<Candidate> saveCandidate(Candidate candidate);

	Candidate mapNewCandidate(Candidate candidateSaved, Candidate modifiedCandidate);

	ResponseEntity<String> deleteById(String id);
}
