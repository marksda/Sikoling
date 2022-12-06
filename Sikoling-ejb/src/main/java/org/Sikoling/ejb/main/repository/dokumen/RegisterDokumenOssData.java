package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_register_dokumen_oss")
@NamedQueries({
	@NamedQuery(name="RegisterDokumenOssData.findAll", query="SELECT d FROM RegisterDokumenOssData d"),
	@NamedQuery(name="RegisterDokumenOssData.findByNib", query = "SELECT d FROM RegisterDokumenOssData d WHERE d.nib = :nib")
})
public class RegisterDokumenOssData implements Serializable {

	private static final long serialVersionUID = 411899176836678359L;
	
	@Id
	private String nib;
	
	@Column(name = "tanggal_penerbitan", columnDefinition = "DATE")
	private LocalDate tanggalPenerbitan;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name = "perusahaan", referencedColumnName = "perusahaan", insertable = true, updatable = false),
		@JoinColumn(name = "dokumen", referencedColumnName = "dokumen", insertable = true, updatable = false)
	})
	private RegisterDokumenData registerDokumenData;
	
	@OneToMany(mappedBy="registerDokumenOssData")
    private Set<RegisterKbliData> daftarRegisterKbliData;
	
	public RegisterDokumenOssData() {
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
