package com.pinguin.issuetracker.enums.validations;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pinguin.issuetracker.enums.Status;

public class ValueOfBugStatusValidator implements ConstraintValidator<ValueOfBugStatusEnum, String>{

	private Status[] subset;
	 
    @Override
    public void initialize(ValueOfBugStatusEnum constraint) {
        this.subset = constraint.anyOf();
    }
 
    @Override
    public boolean isValid(String status, ConstraintValidatorContext context) {
    	try {
    		return Arrays.asList(subset).contains(Status.valueOf(status.toUpperCase()));
    	}catch (IllegalArgumentException e) {
    		return Boolean.FALSE;
		}
    }
}
