package com.pinguin.issuetracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinguin.issuetracker.dto.BugDto;
import com.pinguin.issuetracker.exceptionhandeling.NoRecordException;
import com.pinguin.issuetracker.model.Issue;
import com.pinguin.issuetracker.repository.IssueRepository;

@Service
public class BugServiceImpl implements BugService {

	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	DeveloperService devServiceImpl;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public BugDto saveBug(BugDto bugDto) {
		Issue bugIssue = convertDtoToEntity(bugDto);
		bugIssue.setDeveloperId(devServiceImpl.retriveDeveloper(bugDto.getDeveloperId()));
		bugIssue = issueRepository.save(bugIssue);
		return convertEntityToDto(bugIssue);
	}

	@Override
	public BugDto getBug(Integer bugId) {
		return convertEntityToDto(retriveBug(bugId));
	}

	@Override
	public List<BugDto> getAllBug() {
		return issueRepository.findAll().stream().map(this :: convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public void deleteBug(Integer bugId) {
		issueRepository.delete(retriveBug(bugId));
	}

	@Override
	public Issue retriveBug(Integer bugId) {
		return issueRepository.findById(bugId)
				.orElseThrow(() -> new NoRecordException("No Bug"));
	}

	private BugDto convertEntityToDto(Issue issue) {
		return modelMapper.map(issue, BugDto.class);
	}
	
	private Issue convertDtoToEntity(BugDto bugDto) {
		return modelMapper.map(bugDto, Issue.class);
	}
}
