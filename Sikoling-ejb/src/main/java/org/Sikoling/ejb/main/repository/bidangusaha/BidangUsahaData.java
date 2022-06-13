package org.Sikoling.ejb.main.repository.bidangusaha;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="master.tbl_bidang_usaha")
@NamedQueries({
@NamedQuery(name="BidangUsahaData.findAll", query="SELECT b FROM BidangUsahaData b"),
@NamedQuery(name="BidangUsahaData.findAllByQueryNama", query="SELECT b FROM BidangUsahaData b WHERE b.nama ILIKE :nama")})
public class BidangUsahaData implements Serializable {
	
	private static final long serialVersionUID = -8154322742494393038L;

	@Id
	private Integer id;

	@Column(name="nama")
	private String nama;

	public BidangUsahaData() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}