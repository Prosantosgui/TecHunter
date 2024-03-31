package com.prosantosgui.techunter.services.impl;

import com.prosantosgui.techunter.model.Recruiter;
import com.prosantosgui.techunter.repositories.RecruiterRepository;
import com.prosantosgui.techunter.services.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    public List<Recruiter> findAll() {
        return recruiterRepository.findAll();
    }

    public Recruiter findById(String id) {
        Optional<Recruiter> obj = recruiterRepository.findById(id);

        return obj.orElse(null);
    }

    public ResponseEntity<Recruiter> saveRecruiter(Recruiter recruiter){
        Recruiter recruiterSaved = recruiterRepository.save(recruiter);
        return new ResponseEntity<>(recruiterSaved, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Recruiter> deleteById(String id) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);

        if(recruiter.isPresent()){
            recruiterRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            throw new NoSuchElementException();
        }

    }

    @Override
    public Recruiter mapNewRecruiter(Recruiter recruiterSaved, Recruiter modifiedRecruiter) {
        recruiterSaved.setName(modifiedRecruiter.getName());
        recruiterSaved.setCompany(modifiedRecruiter.getCompany());
        recruiterSaved.setCountry(modifiedRecruiter.getCountry());

        return recruiterSaved;
    }
}
