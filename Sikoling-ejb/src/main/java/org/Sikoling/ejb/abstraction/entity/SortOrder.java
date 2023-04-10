package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class SortOrder implements Serializable {

	private static final long serialVersionUID = 8944515055413129914L;
	private final String fieldName;
	private final String value;
	
	public SortOrder(String fieldName, String value) {
		this.fieldName = fieldName;
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public int hashCode() {
		int hash = 171;
		hash = 151 * hash + Objects.hashCode(fieldName);
		hash = 151 * hash + Objects.hashCode(value);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final SortOrder other = (SortOrder) obj;
        
        if ( !this.fieldName.equals(other.getFieldName()) ) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "{SortOrder{ fieldName:"
				.concat(fieldName)
				.concat(", value:")
				.concat(value)
				.concat("}");
	}
}
