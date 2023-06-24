package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;

import org.Sikoling.ejb.main.repository.otoritas.AutorisasiData;

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
	@NamedQuery(name="AutorityPerusahaanData.findAll", query="SELECT d FROM AutorityPerusahaanData d"),
	@NamedQuery(name="AutorityPerusahaanData.findByPemilik", query = "SELECT d FROM AutorityPerusahaanData d WHERE d.autority.id = :idAutorisasi")
})
@IdClass(AutorityPerusahaanDataId.class)
public class AutorityPerusahaanData implements Serializable {

	private static final long serialVersionUID = -5441108494516995827L;
	
	@Id
	@JoinColumn(name = "autority", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private AutorisasiData autority;
	
	@Id
	@JoinColumn(name = "perusahaan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private RegisterPerusahaanData perusahaan;
	
	public AutorityPerusahaanData() {
	}

	public AutorisasiData getAutority() {
		return autority;
	}

	public void setAutority(AutorisasiData autority) {
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
