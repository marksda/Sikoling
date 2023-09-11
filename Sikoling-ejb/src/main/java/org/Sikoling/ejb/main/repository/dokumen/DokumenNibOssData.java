package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_dok_nib")
@NamedQueries({
	@NamedQuery(name="DokumenNibOssData.updateId", query="UPDATE DokumenNibOssData SET nib = :idBaru WHERE id = :idLama")
})
public class DokumenNibOssData implements Serializable {

	private static final long serialVersionUID = 8612019941448379437L;

	@Id
	@Column(name = "register_dokumen")
	private String id; 
	
	@Column(name = "tanggal")
	private LocalDate tanggalPenetapan;
	
	@Column(name = "nib")
	private String nib; 

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private RegisterDokumenData registerDokumenData;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "nib")
	private List<RegisterKbliData> daftarRegisterKbli;
	
	public DokumenNibOssData() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNib() {
		return nib;
	}

	public void setNib(String nomor) {
		this.nib = nomor;
	}

	public LocalDate getTanggalPenetapan() {
		return tanggalPenetapan;
	}

	public void setTanggalPenetapan(LocalDate tanggalPenetapan) {
		this.tanggalPenetapan = tanggalPenetapan;
	}
	
	public List<RegisterKbliData> getDaftarRegisterKbli() {
		return daftarRegisterKbli;
	}

	public void setDaftarRegisterKbli(List<RegisterKbliData> daftarRegisterKbli) {
		this.daftarRegisterKbli = daftarRegisterKbli;
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
        hash = 171 * hash + Objects.hashCode(nib);
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
        
        final DokumenNibOssData other = (DokumenNibOssData) obj;
        
        if (!nib.equalsIgnoreCase(other.getNib())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "NibOssData{nomor="
				.concat(nib)
				.concat("}");	  
	}

	
}
