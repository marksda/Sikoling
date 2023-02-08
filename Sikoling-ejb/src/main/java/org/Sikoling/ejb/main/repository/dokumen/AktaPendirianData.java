package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.main.repository.perusahaan.PegawaiPerusahaanData;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_akta_pendirian")
@NamedQueries({
	@NamedQuery(name="AktaPendirianData.findAll", query="SELECT d FROM SuratArahanData d")
})
public class AktaPendirianData implements Serializable {

	private static final long serialVersionUID = -7152377516780829121L;

	@Id
	private String id;
	
	private String nomor;
	
	private LocalDate tanggal;
	
	private String notaris;
	
	@JoinColumn(name = "penanggung_jawab_perusahaan", referencedColumnName = "id", insertable = true, updatable = true)
	@OneToOne(optional = false)
	private PegawaiPerusahaanData penanggungJawabData;

	@MapsId
	@OneToOne
	@JoinColumn(name = "id", referencedColumnName = "id")
	private RegisterDokumenData registerDokumenData;
	
	public AktaPendirianData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomor() {
		return nomor;
	}

	public void setNomor(String nomor) {
		this.nomor = nomor;
	}

	public LocalDate getTanggal() {
		return tanggal;
	}

	public void setTanggal(LocalDate tanggal) {
		this.tanggal = tanggal;
	}

	public String getNotaris() {
		return notaris;
	}

	public void setNotaris(String notaris) {
		this.notaris = notaris;
	}

	public PegawaiPerusahaanData getPenanggungJawabData() {
		return penanggungJawabData;
	}

	public void setPenanggungJawabData(PegawaiPerusahaanData penanggungJawabData) {
		this.penanggungJawabData = penanggungJawabData;
	}

	public RegisterDokumenData getRegisterDokumenData() {
		return registerDokumenData;
	}

	public void setRegisterDokumenData(RegisterDokumenData registerDokumenData) {
		this.registerDokumenData = registerDokumenData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 71;
		hash = 181 * hash + Objects.hashCode(this.id);
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
        
        final AktaPendirianData other = (AktaPendirianData) obj;
        
        if (!this.id.equals(other.getId())) {
            return false;
        }
        
        return true;
	}
	
	
	
}
