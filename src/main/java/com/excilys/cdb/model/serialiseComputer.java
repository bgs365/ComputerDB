package com.excilys.cdb.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 
 * @author sanogo
 *
 */
@Converter
public class serialiseComputer implements AttributeConverter<java.time.LocalDate, java.sql.Timestamp> {

	/**
	 * convert from class.
	 */
	  @Override
	  public java.sql.Timestamp convertToDatabaseColumn(java.time.LocalDate entityValue) {
	    return entityValue == null ? null : java.sql.Timestamp.valueOf(entityValue.atStartOfDay());
	  }

	  /**
	   * convert from data base.
	   */
	  @Override
	  public java.time.LocalDate convertToEntityAttribute(java.sql.Timestamp dbValue) {
	    return dbValue == null ? null : dbValue.toLocalDateTime().toLocalDate(); 
	  }
	}
