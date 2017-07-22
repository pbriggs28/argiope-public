package com.preston.argiope.app.config.jpa;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Automatically converts {@link LocalDateTime} objects to {@link Date} objects
 * to be persisted and vice versa to be retrieved.
 * 
 * @author pbriggs
 *
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
	
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
    	return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
    	return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
    }
}
