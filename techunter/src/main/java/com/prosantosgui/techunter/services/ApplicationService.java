package com.prosantosgui.techunter.services;

import com.prosantosgui.techunter.model.Application;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicationService {

    List<Application> findAll();

    ResponseEntity<Application> saveApplication(Application application);
}
