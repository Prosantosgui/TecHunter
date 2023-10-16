package com.prosantosgui.techunter.services;

import com.prosantosgui.techunter.model.Position;
import com.prosantosgui.techunter.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PositionService {

	@Autowired
	private PositionRepository positionRepository;

	public List<Position> findAll() {
		return positionRepository.findAll();
	}

	public Position findById(Long id) {
		Optional<Position> obj = positionRepository.findById(id);
		return obj.get();
	}
}
