package com.pinguin.issuetracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinguin.issuetracker.dto.DeveloperDto;
import com.pinguin.issuetracker.exceptionhandeling.NoRecordException;
import com.pinguin.issuetracker.model.Developer;
import com.pinguin.issuetracker.repository.DeveloperRepository;
import com.pinguin.issuetracker.repository.IssueRepository;

@Service
public class DeveloperServiceImpl implements DeveloperService {

	@Autowired
	DeveloperRepository devRepository;

	@Autowired
	IssueRepository issueRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public DeveloperDto createDeveloper(String developer) {
		return convertEntityToDto(devRepository.save(new Developer(developer)));
	}

	@Override
	public DeveloperDto updateDeveloper(DeveloperDto developerDto) {
		Developer developer = retriveDeveloper(developerDto.getId());
		developer.setDeveloperName(developerDto.getDeveloperName());
		return convertEntityToDto(devRepository.save(developer));
	}

	@Override
	public DeveloperDto getDeveloper(Integer developerId) {
		return convertEntityToDto(retriveDeveloper(developerId));
	}

	@Override
	public void deleteDeveloper(Integer developerId) {
		Developer developer = retriveDeveloper(developerId);
		try {
			devRepository.delete(developer);
		}catch (Exception e) {
			throw new NoRecordException("Developer is assigned to bug or story");
		}
	}

	@Override
	public List<DeveloperDto> getAllDeveloper() {
		return devRepository.findAll().stream().map(this :: convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public Developer retriveDeveloper(Integer developerId) {
		return devRepository.findById(developerId)
				.orElseThrow(() -> new NoRecordException("No Developer"));
	}

	private DeveloperDto convertEntityToDto(Developer developer) {
		return modelMapper.map(developer, DeveloperDto.class);
	}

	@Override
	public Long getDeveloperCount() {
		return devRepository.count();
	}

}
