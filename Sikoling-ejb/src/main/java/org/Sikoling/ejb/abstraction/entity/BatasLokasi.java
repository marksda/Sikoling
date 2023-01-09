package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class BatasLokasi implements Serializable {

	private static final long serialVersionUID = -4273653776461707458L;
	private final String utara;
	private final String timur;
	private final String selatan;
	private final String barat;
	
	public BatasLokasi(String utara, String timur, String selatan, String barat) {
		this.utara = utara;
		this.timur = timur;
		this.selatan = selatan;
		this.barat = barat;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUtara() {
		return utara;
	}

	public String getTimur() {
		return timur;
	}

	public String getSelatan() {
		return selatan;
	}

	public String getBarat() {
		return barat;
	}
	
	@Override
	public int hashCode() {
		int hash = 117;
		hash = 131 * hash + Objects.hashCode(utara);
		hash = 131 * hash + Objects.hashCode(timur);
		hash = 131 * hash + Objects.hashCode(selatan);
		hash = 131 * hash + Objects.hashCode(barat);
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
        
        final BatasLokasi other = (BatasLokasi) obj;
        
        if (!this.utara.equals(other.getUtara())) {
            return false;
        }
        
        if (!this.timur.equals(other.getTimur())) {
            return false;
        }
        
        if (!this.selatan.equals(other.getSelatan())) {
            return false;
        }
        
        if (!this.barat.equals(other.getBarat())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "BatasLokasi{utara=" 
				.concat(utara)
				.concat(", timur=")
				.concat(timur)
				.concat(", selatan=")
				.concat(selatan)
				.concat(", barat=")
				.concat(barat)
				.concat("}");
	}

}
