package com.pinguin.issuetracker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.pinguin.issuetracker.enums.BugPriority;
import com.pinguin.issuetracker.enums.IssueType;
import com.pinguin.issuetracker.enums.Status;
import com.pinguin.issuetracker.enums.jpaconverter.IssueTypeJpaConverter;
import com.pinguin.issuetracker.enums.jpaconverter.PriorityJpaConverter;
import com.pinguin.issuetracker.enums.jpaconverter.StatusJpaConverter;

@Entity
public class Issue implements Serializable{

	private static final long serialVersionUID = 1208772659767455344L;

	public Issue() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Convert(converter = IssueTypeJpaConverter.class)
	private IssueType issueType;
	private String title;
	private String description;
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date creationDate;
	@UpdateTimestamp
	private Date updateDate;
	@Convert(converter = StatusJpaConverter.class)
	private Status status;
	private Integer storyPoint;
	@Convert(converter = PriorityJpaConverter.class)
	private BugPriority priority;
	private Integer assignedWeek;

	@ManyToOne
    @JoinColumn(name="developerId")
    private Developer developerId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getStoryPoint() {
		return storyPoint;
	}

	public void setStoryPoint(Integer storyPoint) {
		this.storyPoint = storyPoint;
	}

	public BugPriority getPriority() {
		return priority;
	}

	public void setPriority(BugPriority priority) {
		this.priority = priority;
	}

	public Integer getAssignedWeek() {
		return assignedWeek;
	}

	public void setAssignedWeek(Integer assignedWeek) {
		this.assignedWeek = assignedWeek;
	}

	public Developer getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(Developer developerId) {
		this.developerId = developerId;
	}

}
