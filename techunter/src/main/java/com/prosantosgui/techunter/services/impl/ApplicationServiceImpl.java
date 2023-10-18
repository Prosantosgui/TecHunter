package com.prosantosgui.techunter.services.impl;

import com.prosantosgui.techunter.model.Application;
import com.prosantosgui.techunter.repositories.ApplicationRepository;
import com.prosantosgui.techunter.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    
    @Autowired
    private ApplicationRepository applicationRepository;
    
    @Override
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    public ResponseEntity<Application> saveApplication(Application application) {
        Application applicationSaved = applicationRepository.save(application);
        return new ResponseEntity<>(applicationSaved, HttpStatus.CREATED);
    }
}
