package org.Sikoling.main.restful.modelperizinan;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;

public class ModelPerizinanDTO implements Serializable {

	private static final long serialVersionUID = -5867092603678965937L;
	private String id;
	private String nama;
	private String singkatan;
	
	public ModelPerizinanDTO() {
	}
	
	public ModelPerizinanDTO(ModelPerizinan modelPerizinan) {
		if(modelPerizinan != null) {
			this.id = modelPerizinan.getId();
			this.nama = modelPerizinan.getNama();
			this.singkatan = modelPerizinan.getSingkatan();
		}		
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

	public String getSingkatan() {
		return singkatan;
	}

	public void setSingkatan(String singkatan) {
		this.singkatan = singkatan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public ModelPerizinan toModelPerizinan() {
		return new ModelPerizinan(id, nama, singkatan);
	}

	public int hashCode() {
		int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.nama);
        hash = 71 * hash + Objects.hashCode(this.singkatan);
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
        
        final ModelPerizinanDTO other = (ModelPerizinanDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nama != other.nama) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "ModelPeizinanDTO{" + "id=" + this.id + ", nama=" + this.nama + "}";	    
	}
	
}
