package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;
import jakarta.persistence.*;

//@Entity
//@Table(name="master.tbl_kbli")
//@NamedQuery(name="KBLIData.findAll", query="SELECT k FROM KBLIData k")
@Embeddable
@Table(name="master.tbl_kbli")
@NamedQuery(name="KBLIData.findAll", query="SELECT k FROM KBLIData k")
public class KBLIData implements Serializable {
		
	private static final long serialVersionUID = -8124207435585377505L;

	private String kode;

	private String nama;

//	@JoinColumn(name="nib_oss", referencedColumnName = "nib_oss")
//	@ManyToOne(optional = false)
//	private OSSData oss;

	public KBLIData() {
	}

	public String getKode() {
		return this.kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

//	public OSSData getOss() {
//		return this.oss;
//	}
//
//	public void setOss(OSSData oss) {
//		this.oss = oss;
//	}

}