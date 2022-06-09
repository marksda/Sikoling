package org.Sikoling.main.restful.bidangusaha;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.BidangUsaha;

public class BidangUsahaDTO implements Serializable {

	private static final long serialVersionUID = 2926591911688484354L;
	private int id;
	private String nama;
	
	public BidangUsahaDTO() {
	}
	
	public BidangUsahaDTO(BidangUsaha bidangUsaha) {
		super();
		this.id = bidangUsaha.getId();
		this.nama = bidangUsaha.getNama();
	}
	
	public BidangUsahaDTO(int id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.nama);
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
        
        final BidangUsahaDTO other = (BidangUsahaDTO) obj;
        
        if (this.id != other.id) {
            return false;
        }
        
        if (this.nama != other.nama) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "BidangUsahaDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}

	public BidangUsaha toBidangUsaha() {
		return new BidangUsaha(id, nama);
	}
}
