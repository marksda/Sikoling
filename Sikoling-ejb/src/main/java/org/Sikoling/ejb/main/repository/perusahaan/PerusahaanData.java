package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;

import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_perusahaan")
@NamedQueries({
	@NamedQuery(name="PerusahaanData.findAll", query="SELECT p FROM PerusahaanData p"),
	@NamedQuery(name="PerusahaanData.findById", query="SELECT p FROM PerusahaanData p WHERE p.id = :id")
})
public class PerusahaanData implements Serializable {

	private static final long serialVersionUID = -1530817238930561908L;
	
	@Id
	private String id;
	
	private String nama;
	
	@JoinColumn(name="pelaku_usaha", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private PelakuUsahaData pelakuUsahaData;

	public PerusahaanData() {
		
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

	public PelakuUsahaData getPelakuUsahaData() {
		return pelakuUsahaData;
	}

	public void setPelakuUsahaData(PelakuUsahaData pelakuUsahaData) {
		this.pelakuUsahaData = pelakuUsahaData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
