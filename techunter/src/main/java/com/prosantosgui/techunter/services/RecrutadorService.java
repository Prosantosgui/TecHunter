package com.prosantosgui.techunter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosantosgui.techunter.model.Recrutador;
import com.prosantosgui.techunter.repositories.RecrutadorRepository;


@Service
public class RecrutadorService {

	@Autowired
	private RecrutadorRepository categoryRepository;

	public List<Recrutador> findAll() {
		return categoryRepository.findAll();
	}

	public Recrutador findById(String id) {
		Optional<Recrutador> obj = categoryRepository.findById(id);
		return obj.get();
	}
}
