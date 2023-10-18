package com.prosantosgui.techunter.resources;

import com.prosantosgui.techunter.model.Recruiter;
import com.prosantosgui.techunter.services.RecruiterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = {"application/json"})
@Tag(name = "Recruiters", description = "Recruiters API")
public class RecruiterResource {

	@Autowired
	private RecruiterService service;
	
	@GetMapping(value = "/recruiters")
	@Operation(summary = "Return all recruiters", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error retrieving data"),
	})
	public ResponseEntity<List<Recruiter>> findAll(){
		List<Recruiter> list = service.findAll(); 
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/recruiters/{id}")
	@Operation(summary = "Return a recruiter", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error retrieving data"),
	})
	public ResponseEntity<Recruiter> findById(@PathVariable String id){
		Recruiter obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
