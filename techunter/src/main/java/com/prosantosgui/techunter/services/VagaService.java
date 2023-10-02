package com.prosantosgui.techunter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosantosgui.techunter.model.Vaga;
import com.prosantosgui.techunter.repositories.VagaRepository;


@Service
public class VagaService {

	@Autowired
	private VagaRepository categoryRepository;

	public List<Vaga> findAll() {
		return categoryRepository.findAll();
	}

	public Vaga findById(Long id) {
		Optional<Vaga> obj = categoryRepository.findById(id);
		return obj.get();
	}
}
