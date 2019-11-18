package com.pinguin.issuetracker.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pinguin.issuetracker.dto.StoryDto;

@Service
public interface PlanService {

	void generatePlan();
	Map<Integer, List<StoryDto>> getWeaklyPlan();
}
