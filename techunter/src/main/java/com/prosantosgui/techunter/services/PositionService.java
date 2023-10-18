package com.prosantosgui.techunter.services;

import com.prosantosgui.techunter.model.Position;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PositionService {

	List<Position> findAll() ;
	

	Position findById(Long id);

	ResponseEntity<Position> savePosition(Position position);
}
