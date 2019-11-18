package com.pinguin.issuetracker.api;

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

import com.pinguin.issuetracker.dto.BugDto;
import com.pinguin.issuetracker.service.BugService;

@RestController
@RequestMapping(value="/api/bug")
public class BugController {

	@Autowired
	BugService bugServiceImpl;
	
	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<BugDto> createBug(@Valid @RequestBody BugDto bugDto) {
		if (bugDto.getId()==null)
			return ResponseEntity.ok(bugServiceImpl.saveBug(bugDto));
		else
			return ResponseEntity.badRequest().build();
	}
	
	@PutMapping(consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<BugDto> updateBug(@Valid @RequestBody BugDto bugDto) {
		if (bugDto.getId() instanceof Integer)
			return ResponseEntity.ok(bugServiceImpl.saveBug(bugDto));
		else
			return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(value = "/{id}", produces = { "application/json" })
	public ResponseEntity<BugDto> getBug(@PathVariable("id") Integer bugId) {
			return ResponseEntity.ok(bugServiceImpl.getBug(bugId));
	}
	
	@DeleteMapping(value = "/{id}", produces = { "application/json" })
	public ResponseEntity<Void> getAllBug(@PathVariable("id") Integer bugId) {
		bugServiceImpl.deleteBug(bugId);	
		return ResponseEntity.ok().build();
	}
}
