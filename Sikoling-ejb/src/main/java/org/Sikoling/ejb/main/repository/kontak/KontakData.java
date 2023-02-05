package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;
import jakarta.persistence.*;


@Embeddable
public class KontakPerusahaanData implements Serializable {

	private static final long serialVersionUID = -2623776256607009635L;
	private String telepone;
	private String fax;
	private String email;
	
	public KontakPerusahaanData() {
		super();
	}

	public String getTelepone() {
		return telepone;
	}

	public void setTelepone(String telepone) {
		this.telepone = telepone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
