package com.pinguin.issuetracker.enums.jpaconverter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.pinguin.issuetracker.enums.BugPriority;

@Converter(autoApply = true)
public class PriorityJpaConverter implements AttributeConverter<BugPriority, Integer> {

	@Override
	public Integer convertToDatabaseColumn(BugPriority attribute) {
		if (attribute == null) {
            return null;
        }
        return attribute.getValue();
	}

	@Override
	public BugPriority convertToEntityAttribute(Integer value) {
		if (value == null) {
            return null;
        }
 
         return Stream.of(BugPriority.values())
          .filter(c -> c.getValue().equals(value))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
	}

}
