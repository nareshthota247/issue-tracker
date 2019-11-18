package com.pinguin.issuetracker.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Developer implements Serializable {

	private static final long serialVersionUID = 6439247184855704228L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String developerName;
	
	@OneToMany(mappedBy = "developerId")
	private List<Issue> issueList;
	
	public Developer() {
		super();
	}

	public Developer(String developerName) {
		super();
		this.developerName = developerName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}
	
}
