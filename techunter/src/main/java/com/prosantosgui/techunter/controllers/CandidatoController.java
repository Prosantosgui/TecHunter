package com.prosantosgui.techunter.controllers;

import com.prosantosgui.techunter.model.Candidato;
import com.prosantosgui.techunter.services.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

	@Autowired
	private CandidatoService service;
	
	@GetMapping
	public ResponseEntity<List<Candidato>> findAll(){
		List<Candidato> list = service.findAll(); 
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Candidato> findById(@PathVariable String id){
		Candidato obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
