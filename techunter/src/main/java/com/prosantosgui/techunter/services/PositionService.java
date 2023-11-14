package com.prosantosgui.techunter.services;

import com.prosantosgui.techunter.model.Position;
import com.prosantosgui.techunter.model.dto.PositionDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PositionService {

	List<Position> findAll() ;

	Position findById(Long id);

	ResponseEntity<Position> savePosition(Position position);

	ResponseEntity<String> deleteById(Long id);

	Position mapNewPosition(Long id, Position positionSaved, Position modifiedPosition);

	ResponseEntity<Position> updatePosition(Long id, Position positionSaved, Position modifiedPosition);


}
