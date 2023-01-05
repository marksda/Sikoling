package org.Sikoling.ejb.main.repository.log;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.main.repository.permohonan.RegisterPermohonanData;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_flow_log_permohonan")
@NamedQueries({
	@NamedQuery(name="FlowLogPermohonanData.findAll", 
		query="SELECT d FROM FlowLogPermohonanData d"),
	@NamedQuery(name="FlowLogPermohonanData.findByIdPengakses", 
		query="SELECT d FROM FlowLogPermohonanData d WHERE d.flowLog.pengaksesData.id = :idPengakses"),
	@NamedQuery(name="FlowLogPermohonanData.findByIdPerusahaan", 
	query="SELECT d FROM FlowLogPermohonanData d WHERE d.registerPermohonan.perusahaanData.id = :idRegisterPerusahaan")
})
@IdClass(FlowLogPermohonanDataId.class)
public class FlowLogPermohonanData implements Serializable {

	private static final long serialVersionUID = -2504965922918814618L;
	
	@Id
	@JoinColumn(name = "flow_log", referencedColumnName = "id", insertable = true, updatable = true)
	@OneToOne
	private FlowLogData flowLog;
	
	@Id 
	@JoinColumn(name = "register_permohonan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private RegisterPermohonanData registerPermohonan;

	public FlowLogData getFlowLog() {
		return flowLog;
	}

	public void setFlowLog(FlowLogData flowLog) {
		this.flowLog = flowLog;
	}

	public RegisterPermohonanData getRegisterPermohonan() {
		return registerPermohonan;
	}

	public void setRegisterPermohonan(RegisterPermohonanData registerPermohonan) {
		this.registerPermohonan = registerPermohonan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

	@Override
	public int hashCode() {
		int hash = 1171;
		hash = 171 * hash + Objects.hashCode(flowLog.getId());
		hash = 171 * hash + Objects.hashCode(registerPermohonan.getId());
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
        
        final FlowLogPermohonanData other = (FlowLogPermohonanData) obj;
        
        if (!this.getFlowLog().getId().equals(other.getFlowLog().getId())) {
            return false;
        }
        
        if (!this.getRegisterPermohonan().getId().equals(other.getRegisterPermohonan().getId())) {
            return false;
        }
        
        return true;
	}
	
	
}
