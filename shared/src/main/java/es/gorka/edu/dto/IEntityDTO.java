package es.gorka.edu.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public interface IEntityDTO extends Serializable {
	
	default public String toDebug() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
