package com.pinguin.issuetracker.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DeveloperDto {

	@Positive
	private int id;
	@NotNull
	private String developerName;
	
	public DeveloperDto() {
		super();
	}
	public DeveloperDto(@Positive int id, @NotNull String developerName) {
		super();
		this.id = id;
		this.developerName = developerName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty
	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

}
