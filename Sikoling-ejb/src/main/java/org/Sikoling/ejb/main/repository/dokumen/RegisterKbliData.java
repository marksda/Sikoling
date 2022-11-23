package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_register_kbli_2020")
@NamedQueries({
	@NamedQuery(name="RegisterKbliData.findAll", query="SELECT d FROM RegisterKbliData d"),
	@NamedQuery(name="RegisterKbliData.findById", query = "SELECT d FROM RegisterKbliData d WHERE d.dokumen.nama LIKE :namaDokumen"),
	@NamedQuery(name="RegisterKbliData.findByKode", query="SELECT d FROM RegisterKbliData d WHERE d.dokumen.id = :idDokumen"),
	@NamedQuery(name="RegisterKbliData.findByNama", query = "SELECT d FROM RegisterKbliData d WHERE d.perusahaan.nama LIKE :namaPerusahaan")
})
public class RegisterKbliData implements Serializable {

	private static final long serialVersionUID = -1341807963805050449L;
	
	@Id
	private String id;
	
	@JoinColumn(name = "register_dokumen_oss", referencedColumnName = "nib", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private RegisterDokumenOssData registerDokumenOssData;	
	
	@JoinColumn(name = "kode", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne(optional = false)
	private KbliData kbliData;
	
	public RegisterKbliData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RegisterDokumenOssData getRegisterDokumenOssData() {
		return registerDokumenOssData;
	}

	public void setRegisterDokumenOssData(RegisterDokumenOssData registerDokumenOssData) {
		this.registerDokumenOssData = registerDokumenOssData;
	}
	
	public KbliData getKbliData() {
		return kbliData;
	}

	public void setKbliData(KbliData kbliData) {
		this.kbliData = kbliData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
