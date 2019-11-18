package com.pinguin.issuetracker.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinguin.issuetracker.dto.DeveloperPointsDto;
import com.pinguin.issuetracker.dto.StoryDto;
import com.pinguin.issuetracker.model.Issue;
import com.pinguin.issuetracker.repository.IssueRepository;

@Service
public class PlanServiceImpl implements PlanService {

	private static final Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);

	private static final Integer MAX_DEVELOPER_POINTS_PER_WEEK = Integer.valueOf(10);

	@Autowired
	IssueRepository issueRepository;

	@Autowired
	StoryService storyServiceImpl;

	@Autowired
	DeveloperService devServiceImpl;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public void generatePlan() {

		Map<Integer, Issue> estimatedStories = storyServiceImpl.getAllEstimatedStories();
		Integer developerCount = devServiceImpl.getDeveloperCount().intValue();

		Integer currentWeek = Integer.valueOf(1);
		while (estimatedStories.size() > 0) {
			logger.debug("Current Week :: {}", currentWeek);
			logger.debug("estimatedStories size :: {}", estimatedStories.size());
			Integer remainingPoinsInWeek = this.getRemaningPointsInWeek(currentWeek, developerCount);

			if (remainingPoinsInWeek > 0) {
				DeveloperPointsDto developerPoints = getMostAvailableDeveloperWithAvailablePointsForWeek(currentWeek);
				Issue storyToAssign = null;
				if (developerPoints != null && developerPoints.getPoints() != null)
					storyToAssign = storyServiceImpl.getStoryWithClosestPointTo(developerPoints.getPoints());
				if (storyToAssign != null) {
					storyToAssign.setDeveloperId(devServiceImpl.retriveDeveloper(developerPoints.getDeveloperId()));
					storyToAssign.setAssignedWeek(currentWeek);
					issueRepository.save(storyToAssign);
					estimatedStories.remove(storyToAssign.getId());
				} else {
					currentWeek++;
				}
			} else {
				currentWeek++;
			}
		}

	}

	@Override
	public Map<Integer, List<StoryDto>> getWeaklyPlan() {

		Map<Integer, List<StoryDto>> assignmentList = new HashMap<>();
		List<StoryDto> stories = issueRepository.getStoryListWithNoDeveloperAssiged().stream()
				.map(this::convertEntityToDto).collect(Collectors.toList());

		List<StoryDto> storyListForCurrentWeek;
		for (StoryDto storyItem : stories) {
			if (assignmentList.containsKey(storyItem.getAssignedWeek())) {
				storyListForCurrentWeek = assignmentList.get(storyItem.getAssignedWeek());
				storyListForCurrentWeek.add(storyItem);
			} else {
				storyListForCurrentWeek = new ArrayList<>();
				storyListForCurrentWeek.add(storyItem);
				assignmentList.put(storyItem.getAssignedWeek(), storyListForCurrentWeek);
			}
		}
		return assignmentList;

	}

	private StoryDto convertEntityToDto(Issue issue) {
		return modelMapper.map(issue, StoryDto.class);
	}

	private Integer getRemaningPointsInWeek(Integer week, Integer developerCount) {
		return (int) ((developerCount * MAX_DEVELOPER_POINTS_PER_WEEK) - getAssignedPointsInWeek(week));
	}

	private Long getAssignedPointsInWeek(Integer week) {
		Long result = storyServiceImpl.getEstimatedPointCountForCurrentWeek(week);
		if (result == null) {
			result = 0L;
		}
		return result;
	}

	private DeveloperPointsDto getMostAvailableDeveloperWithAvailablePointsForWeek(Integer weekNo) {
		DeveloperPointsDto mostAvailableDeveloperandAvailablePoint = storyServiceImpl
				.getMostAvailableDeveloperIdForStory(weekNo);
		if (mostAvailableDeveloperandAvailablePoint != null) {
			mostAvailableDeveloperandAvailablePoint
					.setPoints(MAX_DEVELOPER_POINTS_PER_WEEK - mostAvailableDeveloperandAvailablePoint.getPoints());
		}
		return mostAvailableDeveloperandAvailablePoint;
	}

}
