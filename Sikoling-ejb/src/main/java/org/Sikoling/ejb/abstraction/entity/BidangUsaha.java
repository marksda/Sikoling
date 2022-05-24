package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class BidangUsaha implements Serializable {

	private static final long serialVersionUID = -5728047210096243435L;
	private int id;
	private String keterangan;
	
	public BidangUsaha(int id, String keterangan) {
		super();
		this.id = id;
		this.keterangan = keterangan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.keterangan);
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
        
        final BidangUsaha other = (BidangUsaha) obj;        

        
        if ( !(this.id == other.id)) {
            return false;
        }
        
        if (!this.keterangan.equals(other.keterangan)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "BidangUsaha{" + "id=" + Integer.toString(id) + ", keterangan=" + keterangan + "}";
	}

}
