package com.pinguin.issuetracker.enums;

public enum BugPriority {
	
	CRITICAL(1,"Critical"),
	MAJOR(2,"Major"),
	MINOR(3,"Minor");

	private Integer value;
	private String name;
	
	private BugPriority(final int val, final String name) {
		this.value = val;
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
	
}
