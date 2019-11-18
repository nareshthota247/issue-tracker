package com.pinguin.issuetracker.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pinguin.issuetracker.enums.IssueType;
import com.pinguin.issuetracker.enums.Status;
import com.pinguin.issuetracker.enums.validations.ValueOfBugStatusEnum;
import com.pinguin.issuetracker.enums.validations.ValueOfIssueTypeEnum;

public class StoryDto {

	private Integer id;
	@NotNull
	private String title;
	@NotNull
	private String description;
	private Integer developerId;
	@ValueOfBugStatusEnum(anyOf = {Status.NEW, Status.ESTIMATED, Status.COMPLETED})
	private String status;
	@NotNull
	private Integer storyPoint;
	@ValueOfIssueTypeEnum(anyOf = IssueType.STORY)
	private String issueType;
	private Integer assignedWeek;
	@JsonFormat(pattern="yyyyMMdd")
	private Date creationDate;
	
	public StoryDto() {
		super();
	}
	
	public StoryDto(@NotNull String title, @NotNull String description, Integer developerId,
			@ValueOfBugStatusEnum(anyOf = { Status.NEW, Status.ESTIMATED, Status.COMPLETED }) String status,
			@NotNull Integer storyPoint, @ValueOfIssueTypeEnum(anyOf = IssueType.STORY) String issueType) {
		super();
		this.title = title;
		this.description = description;
		this.developerId = developerId;
		this.status = status;
		this.storyPoint = storyPoint;
		this.issueType = issueType;
	}
	

	public StoryDto(Integer id, @NotNull String title, @NotNull String description, Integer developerId,
			@ValueOfBugStatusEnum(anyOf = { Status.NEW, Status.ESTIMATED, Status.COMPLETED }) String status,
			@NotNull Integer storyPoint, @ValueOfIssueTypeEnum(anyOf = IssueType.STORY) String issueType) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.developerId = developerId;
		this.status = status;
		this.storyPoint = storyPoint;
		this.issueType = issueType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(Integer developerId) {
		this.developerId = developerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStoryPoint() {
		return storyPoint;
	}

	public void setStoryPoint(Integer storyPoint) {
		this.storyPoint = storyPoint;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public Integer getAssignedWeek() {
		return assignedWeek;
	}

	public void setAssignedWeek(Integer assignedWeek) {
		this.assignedWeek = assignedWeek;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}
