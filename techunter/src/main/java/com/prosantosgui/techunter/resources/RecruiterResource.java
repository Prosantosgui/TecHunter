package com.prosantosgui.techunter.resources;

import com.prosantosgui.techunter.model.Recruiter;
import com.prosantosgui.techunter.services.RecruiterService;
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

	@Operation(summary = "Update an existing recruiter", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error updating data"),
	})
	@PutMapping(value = "/recruiters/{id}")
	public ResponseEntity<Recruiter> update(@PathVariable String id, @RequestBody Recruiter modifiedRecruiter){
		try{
			Recruiter recruiterSaved = service.findById(id);
			if(recruiterSaved != null){
				Recruiter updatedRecruiter = service.mapNewRecruiter(recruiterSaved, modifiedRecruiter);
				updatedRecruiter.setLogin(id);
				service.saveRecruiter(updatedRecruiter);
				return new ResponseEntity<>(updatedRecruiter,HttpStatus.OK);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping(value = "/recruiters")
	@Operation(summary = "Save a new recruiter", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error saving data"),
	})
	public ResponseEntity<Recruiter> save(@RequestBody Recruiter recruiter){
		try{
			return service.saveRecruiter(recruiter);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(recruiter, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/recruiters/{id}")
	@Operation(summary = "Delete an existing recruiter", method = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucess"),
			@ApiResponse(responseCode = "422", description = "Invalid data"),
			@ApiResponse(responseCode = "400", description = "Invalid Parameters"),
			@ApiResponse(responseCode = "500", description = "Error deleting data"),
	})
	public ResponseEntity<Recruiter> deleteById(@PathVariable String id){
		try{
			return service.deleteById(id);

		}catch (Exception e){
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}
}
