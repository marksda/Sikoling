package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.dokumen.DokumenNibOss;

public class DokumenNibOssDTO extends DokumenDTO implements Serializable {

	private static final long serialVersionUID = -1573998338773596635L;
	private String nomor;
	private LocalDate tanggal;
	private List<KbliDTO> daftarKbli;
	
	public DokumenNibOssDTO() {
	}
	
	public DokumenNibOssDTO(DokumenNibOss t) {
		super();
		if(t != null) {
			this.setId(t.getId());
			this.setNama(t.getNama());
			this.nomor = t.getNomor();
			this.tanggal = t.getTanggal();
			this.daftarKbli = t.getDaftarKbli() != null ? 
					t.getDaftarKbli().stream()
					.map(i -> new KbliDTO(i)).collect(Collectors.toList()) : null;
		}
	}
	
	public String getNomor() {
		return nomor;
	}
	
	public void setNomor(String nomor) {
		this.nomor = nomor;
	}
	
	public LocalDate getTanggal() {
		return tanggal;
	}
	
	public void setTanggal(LocalDate tanggal) {
		this.tanggal = tanggal;
	}
			
	public List<KbliDTO> getDaftarKbli() {
		return daftarKbli;
	}

	public void setDaftarKbli(List<KbliDTO> daftarKbli) {
		this.daftarKbli = daftarKbli;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 1073;
        hash = 171 * hash + Objects.hashCode(this.getId());
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
        
        final DokumenNibOssDTO other = (DokumenNibOssDTO) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "DokumenNibOssDTO{id="
				.concat(this.getId())
				.concat(", nama=")
				.concat(this.getNama())
				.concat(", nomor=")
				.concat(this.getNomor())
				.concat("}");	  
	}

	public DokumenNibOss toDokumenNibOss() {
		return new DokumenNibOss(
				this.getId(), 
				this.getNama(), 
				this.nomor, 
				this.tanggal,
				this.daftarKbli != null ? 
						this.daftarKbli
							.stream()
							.map(i -> i.toKbli())
							.collect(Collectors.toList()) : null
				);
	}

}
