package com.pinguin.issuetracker;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pinguin.issuetracker.dto.BugDto;
import com.pinguin.issuetracker.enums.BugPriority;
import com.pinguin.issuetracker.enums.Status;
import com.pinguin.issuetracker.model.Issue;

@SpringBootApplication
public class IssueTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueTrackerApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		Converter<String, BugPriority> bugPriorityConverter = ctx -> ctx.getSource() == null ? null
				: BugPriority.valueOf(ctx.getSource().toUpperCase());
		
		modelMapper.typeMap(BugDto.class, Issue.class).addMappings(mapper -> mapper.using(bugPriorityConverter)
				.map(BugDto::getPriority, Issue::setPriority));
		
		Converter<String, Status> bugStatusConverter = ctx -> ctx.getSource() == null ? null
				: Status.valueOf(ctx.getSource().toUpperCase());
		
		modelMapper.typeMap(BugDto.class, Issue.class).addMappings(mapper -> mapper.using(bugStatusConverter)
				.map(BugDto::getStatus, Issue::setStatus));
		
		return modelMapper;
	}
}
