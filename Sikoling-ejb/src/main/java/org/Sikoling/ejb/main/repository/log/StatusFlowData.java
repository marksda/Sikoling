package org.Sikoling.ejb.main.repository.log;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="master.tbl_status_flow_jenis_log")
@NamedQueries({
	@NamedQuery(name="StatusFlowData.findAll", query="SELECT p FROM StatusFlowData p")
})
public class StatusFlowData implements Serializable {

	private static final long serialVersionUID = -2041190065241750462L;
	
	@Id
	private String id;
	
	private String keterangan;

	public StatusFlowData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	@Override
	public int hashCode() {
		int hash = 773;
        hash = 1071 * hash + Objects.hashCode(id);
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
        
        final StatusFlowData other = (StatusFlowData) obj;
        
        if (!id.equals(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "StatusFlowData{id="
				.concat(id)
				.concat("}");	  
	}
	 

}
