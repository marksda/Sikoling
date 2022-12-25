package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kategori_surat_arahan")
@NamedQueries({
	@NamedQuery(name="KategoriSuratArahanData.findAll", query="SELECT p FROM KategoriSuratArahanData p")
})
public class KategoriSuratArahanData implements Serializable {

	private static final long serialVersionUID = -3417029871348746267L;
	
	@Id
	private String id;
	
	private String nama;

	public KategoriSuratArahanData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
}
