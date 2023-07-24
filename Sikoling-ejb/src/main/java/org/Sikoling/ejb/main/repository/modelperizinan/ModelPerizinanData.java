package org.Sikoling.ejb.main.repository.modelperizinan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kategori_model_perizinan")
@NamedQueries({
	@NamedQuery(name="ModelPerizinanData.updateId", query="UPDATE ModelPerizinanData SET id = :idBaru WHERE id = :idLama")
})
public class ModelPerizinanData implements Serializable {

	private static final long serialVersionUID = 2331489181647860142L;
	
	@Id
	private String id;
	
	private String nama;
	
	private String singkatan;

	public ModelPerizinanData() {
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

	public String getSingkatan() {
		return singkatan;
	}

	public void setSingkatan(String singkatan) {
		this.singkatan = singkatan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
