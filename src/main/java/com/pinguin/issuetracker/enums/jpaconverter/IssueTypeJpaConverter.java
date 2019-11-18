package com.pinguin.issuetracker.enums.jpaconverter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.pinguin.issuetracker.enums.IssueType;

@Converter(autoApply = true)
public class IssueTypeJpaConverter implements AttributeConverter<IssueType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(IssueType attribute) {
		if (attribute == null) {
            return null;
        }
        return attribute.getValue();
	}

	@Override
	public IssueType convertToEntityAttribute(Integer value) {
		if (value == null) {
            return null;
        }
 
         return Stream.of(IssueType.values())
          .filter(c -> c.getValue().equals(value))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
	}

}


