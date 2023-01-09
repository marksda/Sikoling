package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class KordinatGeografis implements Serializable{

	private static final long serialVersionUID = -7038210265723223234L;
	private final Double latitude;
	private final Double longitute;
	
	public KordinatGeografis(Double latitude, Double longitute) {
		super();
		this.latitude = latitude;
		this.longitute = longitute;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitute() {
		return longitute;
	}
	
	@Override
	public int hashCode() {
		int hash = 137;
		hash = 13 * hash + Objects.hashCode(latitude);
		hash = 13 * hash + Objects.hashCode(longitute);
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
        
        final KordinatGeografis other = (KordinatGeografis) obj;
        
        if (!latitude.equals(other.getLatitude())) {
            return false;
        }
        
        if (!longitute.equals(other.getLongitute())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "KordinatGeografis{latitude=" 
				.concat(latitude.toString())
				.concat("longitute=")
				.concat(longitute.toString())
				.concat("}");
	}
}
