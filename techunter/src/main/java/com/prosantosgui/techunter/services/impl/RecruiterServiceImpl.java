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

        if(obj.isPresent()){
            return obj.get();
        }else{
            throw new NoSuchElementException();
        }
    }

    public ResponseEntity<Recruiter> saveRecruiter(Recruiter recruiter){
        Recruiter recruiterSaved = recruiterRepository.save(recruiter);
        return new ResponseEntity<>(recruiterSaved, HttpStatus.CREATED);
    }
}