package com.pinguin.issuetracker.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinguin.issuetracker.dto.DeveloperPointsDto;
import com.pinguin.issuetracker.dto.StoryDto;
import com.pinguin.issuetracker.exceptionhandeling.NoRecordException;
import com.pinguin.issuetracker.model.Issue;
import com.pinguin.issuetracker.repository.IssueRepository;

@Service
public class StoryServiceImpl implements StoryService {

	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	DeveloperService devServiceImpl;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public StoryDto saveStory(StoryDto storyDto) {
		Issue bugIssue = convertDtoToEntity(storyDto);
		bugIssue.setDeveloperId(devServiceImpl.retriveDeveloper(storyDto.getDeveloperId()));
		bugIssue = issueRepository.save(bugIssue);
		return convertEntityToDto(bugIssue);
	}

	@Override
	public StoryDto getStory(Integer storyId) {
		return convertEntityToDto(retriveStory(storyId));
	}

	@Override
	public Issue retriveStory(Integer storyId) {
		return issueRepository.findById(storyId)
				.orElseThrow(() -> new NoRecordException("No Story"));
	}

	@Override
	public List<StoryDto> getAllStory() {
		return issueRepository.findAll().stream().map(this :: convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public void deleteStory(Integer storyId) {
		issueRepository.delete(retriveStory(storyId));
	}

	private StoryDto convertEntityToDto(Issue issue) {
		return modelMapper.map(issue, StoryDto.class);
	}
	
	private Issue convertDtoToEntity(StoryDto bugDto) {
		return modelMapper.map(bugDto, Issue.class);
	}

	@Override
	public Map<Integer, Issue> getAllEstimatedStories() {
		Map<Integer, Issue> result = new HashMap<>();
		for (Issue story : issueRepository.getAllEstimatedStories()) {
			result.put(story.getId(), story);
		}
		return result;
	}

	@Override
	public Long getEstimatedPointCountForCurrentWeek(Integer weekNo) {
		return issueRepository.getEstimatedPointCountForTheWeek(weekNo);
	}

	@Override
	public DeveloperPointsDto getMostAvailableDeveloperIdForStory(Integer weekNo) {

		DeveloperPointsDto developerPoints = null;
		List<Object[]> queryResult = issueRepository.getMostAvailableDeveloperIdForStory(weekNo);
		if(!queryResult.isEmpty()) {
			Object[] tuple = queryResult.get(0);
			
			developerPoints = new DeveloperPointsDto();
		
			developerPoints.setDeveloperId((Integer) tuple[0]);
			if(tuple[1] != null) {
				developerPoints.setPoints(((BigInteger) tuple[1]).intValue());
			}
			else {
				developerPoints.setPoints(0);
			}
			
		}
		return developerPoints;
	
	}

	@Override
	public Issue getStoryWithClosestPointTo(Integer points) {

		List<Issue> resultList = issueRepository.getStoryWithClosestPointTo(points); 
		if(!resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}
}
