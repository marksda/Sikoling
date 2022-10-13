package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class ItemAttributeDokumen<T> implements Serializable {

	private static final long serialVersionUID = -3166732876247970646L;
	private final String nama;
	private final T nilai;
	
	public ItemAttributeDokumen(String nama, T nilai) {
		super();
		this.nama = nama;
		this.nilai = nilai;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNama() {
		return nama;
	}

	public T getNilai() {
		return nilai;
	}

	public int hashCode() {
		int hash = 13;
		hash = 71 * hash + Objects.hashCode(this.nama);
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
        
        @SuppressWarnings("unchecked")
		final ItemAttributeDokumen<T> other = (ItemAttributeDokumen<T>) obj;
        
        if (!this.nama.equals(other.getNama())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "DetailDokumenPerusahaan{"				
				.concat("nama=")
				.concat(this.nama)
				.concat(", ")
				.concat("nilai=")
				.concat(this.nilai.toString())
				.concat("}");
	}

}
