package com.prosantosgui.techunter.services.impl;

import com.prosantosgui.techunter.model.Candidate;
import com.prosantosgui.techunter.repositories.CandidateRepository;
import com.prosantosgui.techunter.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate findById(String id) {
        Optional<Candidate> obj = candidateRepository.findById(id);

        if(obj.isPresent()){
            return obj.get();
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public ResponseEntity<Candidate> saveCandidate(Candidate candidate) {
        Candidate candidateSaved = candidateRepository.save(candidate);
        return new ResponseEntity<>(candidateSaved, HttpStatus.CREATED);
    }

    @Override
    public Candidate mapNewCandidate(Candidate candidateSaved, Candidate modifiedCandidate) {
        candidateSaved.setPassword(modifiedCandidate.getPassword());
        candidateSaved.setName(modifiedCandidate.getName());
        candidateSaved.setPhoneNumber(modifiedCandidate.getPhoneNumber());
        candidateSaved.setEmail(modifiedCandidate.getEmail());
        candidateSaved.setLocation(modifiedCandidate.getLocation());
        candidateSaved.setImmediateAvailability(modifiedCandidate.ImmediateAvailability());
        candidateSaved.setState(modifiedCandidate.getState());

        return candidateSaved;
    }

    @Override
    public ResponseEntity<String> deleteById(String id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);

        if(candidate.isPresent()){
            candidateRepository.deleteById(id);
            return new ResponseEntity<>("Candidate deleted successfully!",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Candidate not found!",HttpStatus.NOT_FOUND);
        }
    }
}
