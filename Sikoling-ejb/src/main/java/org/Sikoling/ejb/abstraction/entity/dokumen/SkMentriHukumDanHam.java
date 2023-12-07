package org.Sikoling.ejb.abstraction.entity.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SkMentriHukumDanHam extends Dokumen implements Serializable {
	
	private static final long serialVersionUID = 1335670893256866084L;	
	private final String nomor;
	private final LocalDate tanggal;
	private final Long modalDasar;
	private final Long modalDitempatkan;
	
	public SkMentriHukumDanHam(String id, String nama, String nomor, LocalDate tanggal, Long modalDasar, Long modalDitempatkan) {
		super(id, nama);
		this.nomor = nomor;
		this.tanggal = tanggal;
		this.modalDasar = modalDasar;
		this.modalDitempatkan = modalDitempatkan;
	}

	public String getNomor() {
		return nomor;
	}

	public LocalDate getTanggal() {
		return tanggal;
	}

	public Long getModalDasar() {
		return modalDasar;
	}

	public Long getModalDitempatkan() {
		return modalDitempatkan;
	}
	
	@Override
	public int hashCode() {
		int hash = 137;
        hash = 121 * hash + Objects.hashCode(this.nomor);
        hash = 121 * hash + Objects.hashCode(this.tanggal.toString());
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
        
        final SkMentriHukumDanHam other = (SkMentriHukumDanHam) obj;
        
        if (!this.nomor.equalsIgnoreCase(other.nomor)) {
            return false;
        }
        
        if (!Objects.equals(this.tanggal, other.tanggal)) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "SkMentriHukumDanHam{" + "nomor=" + nomor + ", tanggal=" + tanggal.toString() + "}";
	}
		
	
}
