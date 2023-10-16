package com.prosantosgui.techunter.resources;

import com.prosantosgui.techunter.model.Position;
import com.prosantosgui.techunter.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionResource {

	@Autowired
	private PositionService service;
	
	@GetMapping
	public ResponseEntity<List<Position>> findAll(){
		List<Position> list = service.findAll(); 
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Position> findById(@PathVariable Long id){
		Position obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
