package org.Sikoling.ejb.main.repository.jenispelakuusaha;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_kategori_pelaku_usaha")
@NamedQueries({
@NamedQuery(name="JenisPelakuUsahaData.findAll", query="SELECT FROM JenisPelakuUsahaData p"),
@NamedQuery(name="JenisPelakuUsahaData.findByQueryNama", query="SELECT FROM JenisPelakuUsahaData p WHERE p.")
})
public class JenisPelakuUsahaData implements Serializable {

	private static final long serialVersionUID = -9028556396730975196L;
	
	@Id
	private String id;
	
	private String nama;

	public JenisPelakuUsahaData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

}
