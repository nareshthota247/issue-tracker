package com.pinguin.issuetracker.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinguin.issuetracker.dto.DeveloperDto;
import com.pinguin.issuetracker.service.DeveloperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api/developer")
@Api(value = "Employee payslip for a flexible pay cycle")
public class DeveloperController {
	
	@Autowired
	DeveloperService devServiceImpl;
	
	@ApiOperation(value = "Payslip generator end point", response = DeveloperDto.class)
	@GetMapping(value = "/{id}", produces = { "application/json" })
	public ResponseEntity<DeveloperDto> getDeveloper(@PathVariable("id") Integer developerId) {
		return ResponseEntity.ok(devServiceImpl.getDeveloper(developerId));
	}
	
	@ApiOperation(value = "Payslip generator end point", response = List.class)
	@GetMapping(value = "/list", produces = { "application/json" })
	public ResponseEntity<List<DeveloperDto>> getDevelopers() {
		return ResponseEntity.ok(devServiceImpl.getAllDeveloper());
	}
	
	@ApiOperation(value = "Payslip generator end point", response = DeveloperDto.class)
	@PostMapping(value = "/{developerName}", produces = { "application/json" })
	public ResponseEntity<DeveloperDto> createDeveloper(@PathVariable("developerName") String developerName) {
		return ResponseEntity.ok(devServiceImpl.createDeveloper(developerName));
	}
	
	@ApiOperation(value = "Payslip generator end point")
	@DeleteMapping(value = "/{id}", produces = { "application/json" })
	public ResponseEntity<Void> deleteDeveloper(@PathVariable("id") Integer developerId) {
		devServiceImpl.deleteDeveloper(developerId);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Payslip generator end point", response = DeveloperDto.class)
	@PutMapping(consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<DeveloperDto> updateDeveloper(@Valid @RequestBody DeveloperDto developerDto) {
		return ResponseEntity.ok(devServiceImpl.updateDeveloper(developerDto));
	}
	
}
