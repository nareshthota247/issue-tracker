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

import com.pinguin.issuetracker.dto.StoryDto;
import com.pinguin.issuetracker.service.StoryService;

@RestController
@RequestMapping(value ="/api/story")
public class StoryController {

	@Autowired
	StoryService storyServiceImpl;
	
	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<StoryDto> createStory(@Valid @RequestBody StoryDto storyDto) {
		if (storyDto.getId()==null)
			return ResponseEntity.ok(storyServiceImpl.saveStory(storyDto));
		else
			return ResponseEntity.badRequest().build();
	}
	
	@PutMapping(consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<StoryDto> updateStory(@Valid @RequestBody StoryDto storyDto) {
		if (storyDto.getId() instanceof Integer)
			return ResponseEntity.ok(storyServiceImpl.saveStory(storyDto));
		else
			return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(value = "/{id}", produces = { "application/json" })
	public ResponseEntity<StoryDto> getStory(@PathVariable("id") Integer storyId) {
			return ResponseEntity.ok(storyServiceImpl.getStory(storyId));
	}
	
	@DeleteMapping(value = "/{id}", produces = { "application/json" })
	public ResponseEntity<Void> getAllStory(@PathVariable("id") Integer storyId) {
		storyServiceImpl.deleteStory(storyId);	
		return ResponseEntity.ok().build();
	}

}
