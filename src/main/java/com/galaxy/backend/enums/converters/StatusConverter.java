package com.galaxy.backend.enums.converters;

import com.galaxy.backend.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        return Arrays.stream(Status.values()).filter(status -> status.getValue().equals(dbData)).findFirst().orElseThrow();
    }
}
