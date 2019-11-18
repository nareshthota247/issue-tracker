package com.pinguin.issuetracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pinguin.issuetracker.dto.DeveloperDto;
import com.pinguin.issuetracker.model.Developer;

@Service
public interface DeveloperService {
	
	DeveloperDto createDeveloper(String developerDto);
	DeveloperDto updateDeveloper(DeveloperDto developerDto);
	DeveloperDto getDeveloper(Integer developerId);
	Developer retriveDeveloper(Integer developerId);
	List<DeveloperDto> getAllDeveloper();
	void deleteDeveloper(Integer developerId);
	Long getDeveloperCount();

}
