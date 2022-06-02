package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_bidang_usaha database table.
 * 
 */
@Entity
@Table(name="tbl_bidang_usaha")
@NamedQuery(name="BidangUsahaData.findAll", query="SELECT b FROM BidangUsahaData b")
public class BidangUsahaData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String keterangan;

	public BidangUsahaData() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}