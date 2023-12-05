package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kategori_permohonan")
@NamedQueries({
	@NamedQuery(name="KategoriPermohonanData.updateId", query="UPDATE KategoriPermohonanData SET id = :idBaru WHERE id = :idLama")
})
public class KategoriPermohonanData implements Serializable {

	private static final long serialVersionUID = -3199159794154985247L;
	
	@Id
	private String id;
	
	private String nama;

	public KategoriPermohonanData() {
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
