package com.pinguin.issuetracker.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pinguin.issuetracker.enums.BugPriority;
import com.pinguin.issuetracker.enums.IssueType;
import com.pinguin.issuetracker.enums.Status;
import com.pinguin.issuetracker.enums.validations.ValueOfBugStatusEnum;
import com.pinguin.issuetracker.enums.validations.ValueOfIssueTypeEnum;
import com.pinguin.issuetracker.enums.validations.ValueOfPriorityEnum;

public class BugDto {

	private Integer id;
	@NotNull
	private String title;
	@NotNull
	private String description;
	private Integer developerId;
	@ValueOfPriorityEnum(enumClass = BugPriority.class)
	@NotNull
	private String priority;
	@ValueOfBugStatusEnum(anyOf = {Status.NEW, Status.VERIFIED, Status.RESOLVED})
	@NotNull
	private String status;
	@ValueOfIssueTypeEnum(anyOf = IssueType.BUG)
	private String issueType;
	@JsonFormat(pattern="yyyyMMdd")
	private Date creationDate;

	public BugDto() {
		super();
	}
	
	public BugDto(@NotNull String title, @NotNull String description, Integer developerId,
			@ValueOfPriorityEnum(enumClass = BugPriority.class) @NotNull String priority,
			@ValueOfBugStatusEnum(anyOf = { Status.NEW, Status.VERIFIED, Status.RESOLVED }) @NotNull String status,
			@ValueOfIssueTypeEnum(anyOf = IssueType.BUG) String issueType) {
		super();
		this.title = title;
		this.description = description;
		this.developerId = developerId;
		this.priority = priority;
		this.status = status;
		this.issueType = issueType;
	}

	
	public BugDto(Integer id, @NotNull String title, @NotNull String description, Integer developerId,
			@ValueOfPriorityEnum(enumClass = BugPriority.class) @NotNull String priority,
			@ValueOfBugStatusEnum(anyOf = { Status.NEW, Status.VERIFIED, Status.RESOLVED }) @NotNull String status,
			@ValueOfIssueTypeEnum(anyOf = IssueType.BUG) String issueType) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.developerId = developerId;
		this.priority = priority;
		this.status = status;
		this.issueType = issueType;
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

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
