package com.pinguin.issuetracker.enums.validations;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pinguin.issuetracker.enums.IssueType;

public class ValueOfIssueTypeValidator implements ConstraintValidator<ValueOfIssueTypeEnum, String>{

	private IssueType[] subset;
	 
    @Override
    public void initialize(ValueOfIssueTypeEnum constraint) {
        this.subset = constraint.anyOf();
    }
 
    @Override
    public boolean isValid(String status, ConstraintValidatorContext context) {
        
    	try {
    		return Arrays.asList(subset).contains(IssueType.valueOf(status.toUpperCase()));
    	}catch (IllegalArgumentException e) {
    		return Boolean.FALSE;
		}
    }
}

