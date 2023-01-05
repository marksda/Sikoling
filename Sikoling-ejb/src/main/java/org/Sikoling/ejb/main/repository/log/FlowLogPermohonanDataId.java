package org.Sikoling.ejb.main.repository.log;

import java.io.Serializable;
import java.util.Objects;

public class FlowLogPermohonanDataId implements Serializable {

	private static final long serialVersionUID = 6256591459066519839L;
	private String flowLog;
	private String registerPermohonan;
	
	public FlowLogPermohonanDataId() {
	}

	public String getFlowLog() {
		return flowLog;
	}

	public void setFlowLog(String flowLog) {
		this.flowLog = flowLog;
	}

	public String getRegisterPermohonan() {
		return registerPermohonan;
	}

	public void setRegisterPermohonan(String registerPermohonan) {
		this.registerPermohonan = registerPermohonan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

	@Override
	public int hashCode() {
		int hash = 1131;
        hash = 171 * hash + Objects.hashCode(flowLog);
        hash = 171 * hash + Objects.hashCode(registerPermohonan);
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
        
        final FlowLogPermohonanDataId other = (FlowLogPermohonanDataId) obj;
        
        if (!this.flowLog.equalsIgnoreCase(other.getFlowLog())) {
            return false;
        }  
        
        if (!this.registerPermohonan.equalsIgnoreCase(other.getRegisterPermohonan())) {
            return false;
        }  

        return true;
	}
		
}
