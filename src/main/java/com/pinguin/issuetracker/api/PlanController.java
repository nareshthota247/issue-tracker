package com.pinguin.issuetracker.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinguin.issuetracker.dto.StoryDto;
import com.pinguin.issuetracker.service.PlanService;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
	
	@Autowired
	PlanService planServiceImpl;
	
	@PostMapping( produces = { "application/json" })
	public ResponseEntity<Void> generatePlan() {
		planServiceImpl.generatePlan();
		return ResponseEntity.ok().build();
	}
	
	@GetMapping( produces = { "application/json" })
	public ResponseEntity<Map<Integer, List<StoryDto>>> getPlan() {
		return ResponseEntity.ok(planServiceImpl.getWeaklyPlan());
	}
}
