package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class PaktaIntegritas implements Serializable {

	private static final long serialVersionUID = 7758064924551524262L;
	private final String id;
	private final Perusahaan pemrakarsa;
	private final PenanggungJawab penanggungJawab;
	private final Alamat alamatKegiatanUsaha;
	
	public PaktaIntegritas(String id, Perusahaan pemrakarsa, PenanggungJawab penanggungJawab,
			Alamat alamatKegiatanUsaha) {
		super();
		this.id = id;
		this.pemrakarsa = pemrakarsa;
		this.penanggungJawab = penanggungJawab;
		this.alamatKegiatanUsaha = alamatKegiatanUsaha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public Perusahaan getPemrakarsa() {
		return pemrakarsa;
	}

	public PenanggungJawab getPenanggungJawab() {
		return penanggungJawab;
	}

	public Alamat getAlamatKegiatanUsaha() {
		return alamatKegiatanUsaha;
	}
	
	public int hashCode() {
		int hash = 31;
		hash = 41 * hash + Objects.hashCode(this.pemrakarsa.getId());
		hash = 41 * hash + Objects.hashCode(this.penanggungJawab.getId());
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
        
        final PaktaIntegritas other = (PaktaIntegritas) obj;
        
        if (!this.id.equals(other.id)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "PaktaIntegritas{" + "id=" + id + ", nama=" + pemrakarsa.getNama() + "}";
	}


}
