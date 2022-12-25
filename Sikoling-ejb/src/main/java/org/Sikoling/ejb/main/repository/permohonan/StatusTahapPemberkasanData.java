package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_status_tahap_pemberkasan")
@NamedQueries({
	@NamedQuery(name="StatusTahapPemberkasanData.findAll", query="SELECT p FROM StatusTahapPemberkasanData p")
})
public class StatusTahapPemberkasanData implements Serializable {

	private static final long serialVersionUID = -1953183406580036213L;
	
	@Id
	private String id;
	
	private String nama;
	
	private String keterangan;

	public StatusTahapPemberkasanData() {
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
		
	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
