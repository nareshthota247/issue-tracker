package com.pinguin.issuetracker.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pinguin.issuetracker.dto.DeveloperPointsDto;
import com.pinguin.issuetracker.dto.StoryDto;
import com.pinguin.issuetracker.model.Issue;

@Service
public interface StoryService {

	StoryDto saveStory(StoryDto storyDto);
	StoryDto getStory(Integer storyId);
	Issue retriveStory(Integer storyId);
	List<StoryDto> getAllStory();
	void deleteStory(Integer storyId);
	Map<Integer, Issue> getAllEstimatedStories();
	Long getEstimatedPointCountForCurrentWeek(Integer weekNo);
	DeveloperPointsDto getMostAvailableDeveloperIdForStory(Integer weekNo);
	Issue getStoryWithClosestPointTo(Integer points);
}
