package com.prosantosgui.techunter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prosantosgui.techunter.model.Vaga;
import com.prosantosgui.techunter.services.VagaService;

@RestController
@RequestMapping("/vagas")
public class VagaController {

	@Autowired
	private VagaService service;
	
	@GetMapping
	public ResponseEntity<List<Vaga>> findAll(){
		List<Vaga> list = service.findAll(); 
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Vaga> findById(@PathVariable Long id){
		Vaga obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
