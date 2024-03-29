package org.Sikoling.ejb.main.repository.kecamatan;

import java.io.Serializable;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import jakarta.persistence.*;


@Entity
@Table(name="master.tbl_kecamatan")
@NamedQueries({
	@NamedQuery(name="KecamatanData.updateId", query="UPDATE KecamatanData SET id = :idBaru WHERE id = :idLama")
})
public class KecamatanData implements Serializable {
	private static final long serialVersionUID = 5701915325336067933L;

	@Id
	private String id;
	
	private String nama;

	@JoinColumn(name = "kabupaten", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(optional = false)
	private KabupatenData kabupaten;

	public KecamatanData() {		
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KabupatenData getKabupaten() {
		return this.kabupaten;
	}

	public void setKabupaten(KabupatenData kabupaten) {
		this.kabupaten = kabupaten;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}