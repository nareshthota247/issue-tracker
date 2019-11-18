package com.pinguin.issuetracker.enums;

public enum IssueType {
	
	BUG(1),
	STORY(2);
	
	private Integer value;
	
	private IssueType(Integer val) {
		this.value = val;
	}

	public Integer getValue() {
		return value;
	}
}
