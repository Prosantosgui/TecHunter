package com.prosantosgui.techunter.resources;

import com.prosantosgui.techunter.model.Candidate;
import com.prosantosgui.techunter.services.CandidateService;
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
}
