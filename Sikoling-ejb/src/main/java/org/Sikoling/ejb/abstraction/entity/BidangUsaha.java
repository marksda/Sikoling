package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class BidangUsaha implements Serializable {

	private static final long serialVersionUID = -5728047210096243435L;
	private final int id;
	private final String nama;
	
	public BidangUsaha(int id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
	}

	public int getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 71;
		hash = 51 * hash + Objects.hashCode(this.nama);
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
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "BidangUsaha{" + "id=" + Integer.toString(id) + ", nama=" + nama + "}";
	}

}
