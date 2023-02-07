package org.Sikoling.main.restful.alamat;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.main.restful.desa.DesaDTO;
import org.Sikoling.main.restful.kabupaten.KabupatenDTO;
import org.Sikoling.main.restful.kecamatan.KecamatanDTO;
import org.Sikoling.main.restful.propinsi.PropinsiDTO;

public class AlamatDTO implements Serializable {
	
	private static final long serialVersionUID = -3073992539638923482L;
	private PropinsiDTO propinsi;
	private KabupatenDTO kabupaten;
	private KecamatanDTO kecamatan;
	private DesaDTO desa;
	private String keterangan;
	
	public AlamatDTO() {		
	}
	
	public AlamatDTO(Alamat alamat) {
		if(alamat != null) {
			this.propinsi = new PropinsiDTO(alamat.getPropinsi());
			this.kabupaten = new KabupatenDTO(alamat.getKabupaten());
			this.kecamatan = new KecamatanDTO(alamat.getKecamatan());
			this.desa = new DesaDTO(alamat.getDesa());
			this.keterangan = alamat.getKeterangan();
		}
		else {
			this.propinsi = null;
			this.kabupaten = null;
			this.kecamatan = null;
			this.desa = null;
			this.keterangan = null;
		}
	}

	public PropinsiDTO getPropinsi() {
		return propinsi;
	}

	public void setPropinsi(PropinsiDTO propinsi) {
		this.propinsi = propinsi;
	}

	public KabupatenDTO getKabupaten() {
		return kabupaten;
	}

	public void setKabupaten(KabupatenDTO kabupaten) {
		this.kabupaten = kabupaten;
	}

	public KecamatanDTO getKecamatan() {
		return kecamatan;
	}

	public void setKecamatan(KecamatanDTO kecamatan) {
		this.kecamatan = kecamatan;
	}

	public DesaDTO getDesa() {
		return desa;
	}

	public void setDesa(DesaDTO desa) {
		this.desa = desa;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 11;
        hash = 59 * hash + Objects.hashCode(propinsi.getId());
        hash = 59 * hash + Objects.hashCode(this.kabupaten.toString());
        hash = 59 * hash + Objects.hashCode(this.kecamatan.toString());
        hash = 59 * hash + Objects.hashCode(this.desa.toString());
        hash = 59 * hash + Objects.hashCode(this.keterangan);
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
        
        final AlamatDTO other = (AlamatDTO) obj;
        
        if (this.propinsi.getId() != other.getPropinsi().getId()) {
            return false;
        }
        
        if (this.kabupaten.getId() != other.getKabupaten().getId()) {
            return false;
        }
        
        if (this.kecamatan.getId() != other.getKecamatan().getId()) {
            return false;
        }
        
        if (this.desa.getId() != other.getDesa().getId()) {
            return false;
        }
        
        if(!this.keterangan.equalsIgnoreCase(other.getKeterangan())) {
        	return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "AlamatPenanggungJawabDTO{" + this.propinsi.toString() + ", " + this.kabupaten.toString()
				+ ", " + this.kecamatan.toString() + this.desa.toString() + ", detail=" + this.keterangan + "}";	  
	}
	
	public Alamat toAlamat() {
		return new Alamat(propinsi.toPropinsi(), kabupaten.toKabupaten(), kecamatan.toKecamatan(), desa.toDesa(), keterangan);
	}

}
