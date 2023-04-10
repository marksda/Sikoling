package org.Sikoling.main.restful.queryparams;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Filter;

public class FilterDTO implements Serializable {

	private static final long serialVersionUID = 3784996760649335190L;
	
	private String fieldName;
	private String value;
	
	public FilterDTO() {
	}
	
	public FilterDTO(Filter t) {
		if(t != null) {
			this.fieldName = t.getFieldName();
			this.value = t.getValue();
		}
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 1113;
        hash = 171 * hash + Objects.hashCode(this.fieldName);
        hash = 171 * hash + Objects.hashCode(this.value);
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
        
        final FilterDTO other = (FilterDTO) obj;
        
        if (!this.fieldName.equalsIgnoreCase(other.getFieldName())) {
            return false;
        }  
        
        if (!this.value.equalsIgnoreCase(other.getValue())) {
            return false;
        }  

        return true;
	}
	
	@Override
	public String toString() {
		return "FilterDTO{fieldNama="				
				.concat(this.fieldName)
				.concat(", value=")
				.concat(this.value)
				.concat("}");	  
	}

	public Filter toFilter() {
		return new Filter(fieldName, value);
	}

}
