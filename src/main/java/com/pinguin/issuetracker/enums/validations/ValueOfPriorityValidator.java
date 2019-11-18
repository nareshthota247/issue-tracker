package com.pinguin.issuetracker.enums.validations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValueOfPriorityValidator implements ConstraintValidator<ValueOfPriorityEnum, CharSequence>{

	private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfPriorityEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass()
            .getEnumConstants())
            .map(Enum::name)
            .collect(Collectors.toList());
        
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        
        StringBuilder enumList = new StringBuilder("[");
        enumList.append(acceptedValues.stream().map(Object::toString).collect(Collectors.joining(", ")));
        enumList.append("]");
        
    	context.disableDefaultConstraintViolation();
    	context.buildConstraintViolationWithTemplate("must be any of "+ enumList.toString()).addConstraintViolation();

        return acceptedValues.contains(value.toString().toUpperCase());
    }
    
}
