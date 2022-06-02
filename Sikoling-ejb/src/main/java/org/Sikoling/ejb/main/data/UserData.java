package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_user database table.
 * 
 */
@Entity
@Table(name="tbl_user")
@NamedQuery(name="UserData.findAll", query="SELECT u FROM UserData u")
public class UserData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String jenis;

	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name="tanggal_registrasi")
	private Date tanggalRegistrasi;

	private String user;

	public UserData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJenis() {
		return this.jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getTanggalRegistrasi() {
		return this.tanggalRegistrasi;
	}

	public void setTanggalRegistrasi(Date tanggalRegistrasi) {
		this.tanggalRegistrasi = tanggalRegistrasi;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}