package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;


@Embeddable
@AttributeOverrides({
@AttributeOverride( name = "nib", column = @Column(name = "nib_oss")),
@AttributeOverride( name = "tanggal", column = @Column(name = "tanggal_penerbitan_nib_oss"))
})
public class OSSData implements Serializable {

	private static final long serialVersionUID = -8651481608218481624L;	
	private String nib;
	private Date tanggal;
	
	@ElementCollection  
	@OneToMany(mappedBy = "oss", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<KBLIData> kbliDatas;

	public OSSData() {
		super();
	}

	public String getNib() {
		return nib;
	}

	public void setNib(String nib) {
		this.nib = nib;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public List<KBLIData> getKbliDatas() {
		return kbliDatas;
	}

	public void setKbliDatas(List<KBLIData> kbliDatas) {
		this.kbliDatas = kbliDatas;
	}	

}
