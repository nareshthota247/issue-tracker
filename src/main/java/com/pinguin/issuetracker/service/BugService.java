package com.pinguin.issuetracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pinguin.issuetracker.dto.BugDto;
import com.pinguin.issuetracker.model.Issue;

@Service
public interface BugService {

	BugDto saveBug(BugDto bugDto);
	BugDto getBug(Integer bugId);
	Issue retriveBug(Integer bugId);
	List<BugDto> getAllBug();
	void deleteBug(Integer bugId);
	
}
