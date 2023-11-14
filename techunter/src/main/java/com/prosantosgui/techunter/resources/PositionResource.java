package com.prosantosgui.techunter.resources;

import com.prosantosgui.techunter.model.Position;
import com.prosantosgui.techunter.model.dto.PositionDTO;
import com.prosantosgui.techunter.services.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
		try {
			Position obj = service.findById(id);
			return ResponseEntity.ok().body(obj);
		}catch (RuntimeException e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/positions/{id}")
	@Operation(summary = "Update an existing position", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error updating data"),
	})
	public ResponseEntity<Position> update(@PathVariable Long id, @RequestBody Position ModifiedPosition){
		try{
			Position positionSaved = service.findById(id);
			if(positionSaved != null){
				return service.updatePosition(id, positionSaved, ModifiedPosition);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping(value = "/positions")
	@Operation(summary = "Save a new position", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error saving data"),
	})
	public ResponseEntity<PositionDTO> save(@RequestBody PositionDTO position){
		try{
			return service.savePosition(position);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping(value = "/positions/{id}")
	@Operation(summary = "Delete an existing position", method = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error deleting data"),
	})
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		try{
			return service.deleteById(id);

		}catch (Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
