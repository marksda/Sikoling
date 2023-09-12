package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_dok_generik")
@NamedQueries({
	@NamedQuery(name="DokumenGenerikData.updateId", query="UPDATE DokumenGenerikData SET id = :idBaru WHERE id = :idLama")
})
public class DokumenGenerikData implements Serializable {

	private static final long serialVersionUID = -7775947311949179865L;
	
	@Id
	@Column(name = "register_dokumen")
	private String id; 
	
	@Column(name = "tanggal")
	private LocalDate tanggalPenetapan;
	
	@Column(name = "nomor")
	private String nomor; 

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private RegisterDokumenData registerDokumenData;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getTanggalPenetapan() {
		return tanggalPenetapan;
	}

	public void setTanggalPenetapan(LocalDate tanggalPenetapan) {
		this.tanggalPenetapan = tanggalPenetapan;
	}

	public String getNomor() {
		return nomor;
	}

	public void setNomor(String nomor) {
		this.nomor = nomor;
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
		int hash = 713;
        hash = 171 * hash + Objects.hashCode(nomor);
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
        
        final DokumenGenerikData other = (DokumenGenerikData) obj;
        
        if (!nomor.equalsIgnoreCase(other.getNomor())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "DokumenGenerikData{nomor="
				.concat(nomor)
				.concat("}");	  
	}


}
