package com.prosantosgui.techunter.resources;

import com.prosantosgui.techunter.model.Position;
import com.prosantosgui.techunter.services.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = {"application/json"})
@Tag(name = "Positions", description = "Positions API")
public class PositionResource {

	@Autowired
	private PositionService service;
	
	@GetMapping(value = "/positions")
	@Operation(summary = "Return all positions", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error retrieving data"),
	})
	public ResponseEntity<List<Position>> findAll(){
		List<Position> list = service.findAll(); 
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/positions/{id}")
	@Operation(summary = "Return a position", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error retrieving data"),
	})
	public ResponseEntity<Position> findById(@PathVariable Long id){
		Position obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
