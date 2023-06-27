package org.Sikoling.ejb.main.repository.otoritas;

import java.io.Serializable;

import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanData;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_autority_perusahaan")
@NamedQueries({
	@NamedQuery(name="OtoritasPerusahaanData.findAll", query="SELECT d FROM OtoritasPerusahaanData d"),
	@NamedQuery(name="OtoritasPerusahaanData.findByPemilik", query = "SELECT d FROM OtoritasPerusahaanData d WHERE d.autority.id = :idAutorisasi")
})
@IdClass(OtoritasPerusahaanDataId.class)
public class OtoritasPerusahaanData implements Serializable {

	private static final long serialVersionUID = -5441108494516995827L;
	
	@Id
	@JoinColumn(name = "autority", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private OtoritasData autority;
	
	@Id
	@JoinColumn(name = "perusahaan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private RegisterPerusahaanData perusahaan;
	
	public OtoritasPerusahaanData() {
	}

	public OtoritasData getAutority() {
		return autority;
	}

	public void setAutority(OtoritasData autority) {
		this.autority = autority;
	}

	public RegisterPerusahaanData getPerusahaan() {
		return perusahaan;
	}

	public void setPerusahaan(RegisterPerusahaanData perusahaan) {
		this.perusahaan = perusahaan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
