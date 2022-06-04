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

	private String password;

	@Column(name="status_internal")
	private Boolean statusInternal;

	@Column(name="status_login")
	private Boolean statusLogin;

	@Temporal(TemporalType.DATE)
	@Column(name="tanggal_registrasi")
	private Date tanggalRegistrasi;

	private String user;

	public UserData() {
	}

	public UserData(String id, String password, Boolean statusInternal, Boolean statusLogin, Date tanggalRegistrasi,
			String user) {
		super();
		this.id = id;
		this.password = password;
		this.statusInternal = statusInternal;
		this.statusLogin = statusLogin;
		this.tanggalRegistrasi = tanggalRegistrasi;
		this.user = user;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStatusInternal() {
		return this.statusInternal;
	}

	public void setStatusInternal(Boolean statusInternal) {
		this.statusInternal = statusInternal;
	}

	public Boolean getStatusLogin() {
		return this.statusLogin;
	}

	public void setStatusLogin(Boolean statusLogin) {
		this.statusLogin = statusLogin;
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