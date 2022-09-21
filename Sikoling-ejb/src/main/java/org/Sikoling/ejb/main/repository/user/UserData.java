package org.Sikoling.ejb.main.repository.user;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="master.tbl_user")
@NamedQueries({
@NamedQuery(name="UserData.findAll", query="SELECT u FROM UserData u"),
@NamedQuery(name="UserData.findByQueryNama", query="SELECT u FROM UserData u WHERE u.user LIKE :nama"),
@NamedQuery(name="UserData.authenticationQuery", query="SELECT u FROM UserData u WHERE u.user = :nama AND u.password = :password")
})
public class UserData implements Serializable {
	
	private static final long serialVersionUID = 3347534045164637038L;

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