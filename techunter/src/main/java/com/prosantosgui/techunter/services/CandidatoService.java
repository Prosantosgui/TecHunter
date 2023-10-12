package com.prosantosgui.techunter.services;

import com.prosantosgui.techunter.model.Candidato;
import com.prosantosgui.techunter.repositories.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CandidatoService {

	@Autowired
	private CandidatoRepository candidatoRepository;

	public List<Candidato> findAll() {
		return candidatoRepository.findAll();
	}

	public Candidato findById(String id) {
		Optional<Candidato> obj = candidatoRepository.findById(id);
		return obj.get();
	}
}
