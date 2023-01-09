package org.Sikoling.ejb.main.repository.bidangusaha;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;


@Entity
@Table(name="master.tbl_bidang_usaha")
@NamedQueries({
@NamedQuery(name="BidangUsahaData.findAll", query="SELECT b FROM BidangUsahaData b"),
@NamedQuery(name="BidangUsahaData.findByNama", query="SELECT b FROM BidangUsahaData b WHERE b.nama LIKE :nama")})
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
	
	@Override
	public int hashCode() {
		int hash = 1217;
		hash = 13 * hash + Objects.hashCode(this.id);
		hash = 13 * hash + Objects.hashCode(this.nama);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final BidangUsahaData other = (BidangUsahaData) obj;
        
        if (!this.id.equals(other.id)) {
            return false;
        }
        
        if (!this.nama.equals(other.nama)) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "BidangUsahaData{" + "id=" + id + "nama=" + nama + "}";
	}

}