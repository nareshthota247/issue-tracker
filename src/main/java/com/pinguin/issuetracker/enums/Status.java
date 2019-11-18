package com.pinguin.issuetracker.enums;

public enum Status {
	
	NEW(1,"New"),
	VERIFIED(2,"Verified"),
	RESOLVED(3,"Resolved"),
	ESTIMATED(4,"Estimated"),
	COMPLETED(5,"Completed");
	
	private Integer value;
	private String name;
	
	private Status(final int val, final String name) {
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
