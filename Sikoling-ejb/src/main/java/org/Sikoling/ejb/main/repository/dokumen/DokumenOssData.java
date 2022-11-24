package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_register_dokumen_oss")
@NamedQueries({
	@NamedQuery(name="DokumenOssData.findAll", query="SELECT d FROM DokumenOssData d"),
	@NamedQuery(name="DokumenOssData.findByNib", query = "SELECT d FROM DokumenOssData d WHERE d.nib = :nib")
})
public class DokumenOssData implements Serializable {

	private static final long serialVersionUID = 411899176836678359L;
	
	@Id
	private String nib;
	
	@Column(name = "tanggal_penerbitan", columnDefinition = "DATE")
	private LocalDate tanggalPenerbitan;
	
	@JoinColumn(name="master_register_dokumen", referencedColumnName = "id", insertable = true, updatable = false)
	@OneToOne(cascade = CascadeType.ALL)
	private RegisterDokumenData registerDokumenData;
	
	@OneToMany(mappedBy="registerDokumenOssData")
    private Set<RegisterKbliData> daftarRegisterKbliData;
	
	public DokumenOssData() {
	}

	public String getNib() {
		return nib;
	}

	public void setNib(String nib) {
		this.nib = nib;
	}

	public LocalDate getTanggalPenerbitan() {
		return tanggalPenerbitan;
	}

	public void setTanggalPenerbitan(LocalDate tanggalPenerbitan) {
		this.tanggalPenerbitan = tanggalPenerbitan;
	}

	public RegisterDokumenData getRegisterDokumenData() {
		return registerDokumenData;
	}

	public void setRegisterDokumenData(RegisterDokumenData registerDokumenData) {
		this.registerDokumenData = registerDokumenData;
	}

	public Set<RegisterKbliData> getDaftarRegisterKbliData() {
		return daftarRegisterKbliData;
	}

	public void setDaftarRegisterKbliData(Set<RegisterKbliData> daftarRegisterKbliData) {
		this.daftarRegisterKbliData = daftarRegisterKbliData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
