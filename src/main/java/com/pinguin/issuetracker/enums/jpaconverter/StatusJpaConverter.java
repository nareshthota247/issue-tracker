package com.pinguin.issuetracker.enums.jpaconverter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.pinguin.issuetracker.enums.Status;

@Converter(autoApply = true)
public class StatusJpaConverter implements AttributeConverter<Status, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Status attribute) {
		if (attribute == null) {
            return null;
        }
        return attribute.getValue();
	}

	@Override
	public Status convertToEntityAttribute(Integer value) {
		if (value == null) {
            return null;
        }
 
         return Stream.of(Status.values())
          .filter(c -> c.getValue().equals(value))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
	}

}

