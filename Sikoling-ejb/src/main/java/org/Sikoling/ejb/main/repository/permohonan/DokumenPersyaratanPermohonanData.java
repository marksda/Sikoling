package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.main.repository.dokumen.RegisterDokumenData;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_dokumen_syarat_permohonan")
@NamedQueries({
	@NamedQuery(name="DokumenPersyaratanPermohonanData.findAll", query="SELECT p FROM DokumenPersyaratanPermohonanData p")
})
@IdClass(DokumenPersyaratanPermohonanDataId.class)
public class DokumenPersyaratanPermohonanData implements Serializable {

	private static final long serialVersionUID = 6971271338445997868L;
	
	@Id
	@JoinColumn(name = "id_register_permohonan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private RegisterPermohonanData registerPermohonan;
	
	@Id
	@JoinColumn(name = "id_register_dokumen", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private RegisterDokumenData registerDokumen;

	public DokumenPersyaratanPermohonanData() {
	}

	public RegisterPermohonanData getRegisterPermohonan() {
		return registerPermohonan;
	}

	public void setRegisterPermohonan(RegisterPermohonanData registerPermohonan) {
		this.registerPermohonan = registerPermohonan;
	}

	public RegisterDokumenData getRegisterDokumen() {
		return registerDokumen;
	}

	public void setRegisterDokumen(RegisterDokumenData registerDokumen) {
		this.registerDokumen = registerDokumen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

	@Override
	public int hashCode() {
		int hash = 713;
        hash = 173 * hash + Objects.hashCode(registerPermohonan);
        hash = 173 * hash + Objects.hashCode(registerDokumen);
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
        
        final DokumenPersyaratanPermohonanData other = (DokumenPersyaratanPermohonanData) obj;
        
        if (!this.registerPermohonan.getId().equals(other.getRegisterPermohonan().getId())) {
            return false;
        }  
        
        if (!this.registerDokumen.getId().equals(other.getRegisterDokumen().getId())) {
            return false;
        }  

        return true;
	}
	
	@Override
	public String toString() {
		return "DokumenPersyaratanPermohonanData{idRegisterPermohonan="
				.concat(registerPermohonan.getId())
				.concat("idRegisterDokumen=")
				.concat(registerDokumen.getId())
				.concat("}");	  
	}
	 
}
