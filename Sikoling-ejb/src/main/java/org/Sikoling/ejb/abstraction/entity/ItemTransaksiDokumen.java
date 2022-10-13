package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ItemTransaksiDokumen implements Serializable {

	private static final long serialVersionUID = 2847248198419126532L;
	private final String idTransaksi;
	private final Dokumen dokumen;
	private final List<ItemAttributeDokumen<Object>> attribute;
	private final Date tanggalTransaksi;
	private final boolean isBerlaku;
	private final Autorisasi autorisasi;
	
	public ItemTransaksiDokumen(String idTransaksi, Dokumen dokumen, List<ItemAttributeDokumen<Object>> attribute,
			Date tanggalTransaksi, boolean isBerlaku, Autorisasi autorisasi) {
		super();
		this.idTransaksi = idTransaksi;
		this.dokumen = dokumen;
		this.attribute = attribute;
		this.tanggalTransaksi = tanggalTransaksi;
		this.isBerlaku = isBerlaku;
		this.autorisasi = autorisasi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIdTransaksi() {
		return idTransaksi;
	}

	public Dokumen getDokumen() {
		return dokumen;
	}

	public List<ItemAttributeDokumen<Object>> getAttribute() {
		return attribute;
	}

	public Date getTanggalTransaksi() {
		return tanggalTransaksi;
	}

	public boolean isBerlaku() {
		return isBerlaku;
	}

	public Autorisasi getAutorisasi() {
		return autorisasi;
	}
	
	public int hashCode() {
		int hash = 19;
		hash = 41 * hash + Objects.hashCode(this.idTransaksi);
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
        
        final ItemTransaksiDokumen other = (ItemTransaksiDokumen) obj;
        
        if (!this.idTransaksi.equals(other.getIdTransaksi())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "ItemTransaksiDokumen {"
				.concat("id=")
				.concat(this.idTransaksi)
				.concat(", ")
				.concat("nama dokumen=")
				.concat(this.dokumen.getNama())
				.concat("}");
	}	

}
