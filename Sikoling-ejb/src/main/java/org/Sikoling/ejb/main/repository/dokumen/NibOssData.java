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
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_dok_nib")
@NamedQueries({
	@NamedQuery(name="NibOssData.findAll", query="SELECT d FROM NibOssData d")
})
public class NibOssData implements Serializable {

	private static final long serialVersionUID = 8612019941448379437L;

	@Id
	private String nomor;
	
	@Column(name = "tanggal")
	private LocalDate tanggalPenetapan;

	@JoinColumn(name = "register_dokumen", referencedColumnName = "id", insertable = true, updatable = true)
	@OneToOne
	private RegisterDokumenData registerDokumenData;

	@OneToMany(mappedBy = "nib", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<RegisterKbliData> daftarKbli;
	
	public NibOssData() {
	}

	public String getNomor() {
		return nomor;
	}

	public void setNomor(String nomor) {
		this.nomor = nomor;
	}

	public LocalDate getTanggalPenetapan() {
		return tanggalPenetapan;
	}

	public void setTanggalPenetapan(LocalDate tanggalPenetapan) {
		this.tanggalPenetapan = tanggalPenetapan;
	}
	
	public List<RegisterKbliData> getDaftarKbli() {
		return daftarKbli;
	}

	public void setDaftarKbli(List<RegisterKbliData> daftarKbli) {
		this.daftarKbli = daftarKbli;
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
        
        final NibOssData other = (NibOssData) obj;
        
        if (!nomor.equalsIgnoreCase(other.getNomor())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "NibOssData{nomor="
				.concat(nomor)
				.concat(", tanggalPenetapan=")
				.concat(this.getTanggalPenetapan().toString())
				.concat("}");	  
	}

}
