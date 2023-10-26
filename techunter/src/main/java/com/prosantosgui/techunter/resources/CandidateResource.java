package com.prosantosgui.techunter.resources;

import com.prosantosgui.techunter.model.Candidate;
import com.prosantosgui.techunter.services.CandidateService;
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
@Tag(name = "Candidates", description = "Candidates API")
public class CandidateResource {

	@Autowired
	private CandidateService service;
	
	@GetMapping(value = "/candidates")
	@Operation(summary = "Return all candidates", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error retrieving data"),
	})
	public ResponseEntity<List<Candidate>> findAll(){
		List<Candidate> list = service.findAll(); 
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/candidates/{id}")
	@Operation(summary = "Return a candidate", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error retrieving data"),
	})
	public ResponseEntity<Candidate> findById(@PathVariable String id){
		Candidate obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@Operation(summary = "Update a candidate", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error updating data"),
	})
	@PutMapping(value = "/candidates/{id}")
	public ResponseEntity<Candidate> update(@PathVariable String id, @RequestBody Candidate modifiedCandidate){
		try{
			Candidate candidateSaved = service.findById(id);
			if(candidateSaved != null){
				Candidate updatedCandidate = service.mapNewCandidate(candidateSaved, modifiedCandidate);
				updatedCandidate.setLogin(id);
				service.saveCandidate(updatedCandidate);
				return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}

	@Operation(summary = "Save a new candidate", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error saving data"),
	})
	@PostMapping(value = "/candidates")
	public ResponseEntity<Candidate> save(@RequestBody Candidate candidate){
		try {
			return service.saveCandidate(candidate);
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}

	@Operation(summary = "Delete a candidate", method = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error deleting data"),
	})
	@DeleteMapping(value = "/candidates/{id}")
	public ResponseEntity<String> deleteById(@PathVariable String id){
		try{
			return service.deleteById(id);
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}
}
