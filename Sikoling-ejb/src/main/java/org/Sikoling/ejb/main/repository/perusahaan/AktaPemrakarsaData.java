package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Embeddable
@AttributeOverrides({
@AttributeOverride( name = "nomor", column = @Column(name = "nomor_akta")),
@AttributeOverride( name = "tanggal", column = @Column(name = "tanggal_akta")),
@AttributeOverride( name = "namaNotaris", column = @Column(name = "nama_notaris"))
})
public class AktaPemrakarsaData implements Serializable {

	private static final long serialVersionUID = -4505500674108221832L;
	private String nomor;
	private Date tanggal;
	private String namaNotaris;	
	
	public AktaPemrakarsaData() {
		super();
	}

	public String getNomor() {
		return nomor;
	}
	
	public void setNomor(String nomor) {
		this.nomor = nomor;
	}
	
	public Date getTanggal() {
		return tanggal;
	}
	
	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}
	
	public String getNamaNotaris() {
		return namaNotaris;
	}
	
	public void setNamaNotaris(String namaNotaris) {
		this.namaNotaris = namaNotaris;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
