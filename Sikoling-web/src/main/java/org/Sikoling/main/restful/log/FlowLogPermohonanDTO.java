package org.Sikoling.main.restful.log;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;
import org.Sikoling.ejb.abstraction.entity.log.FlowLogPermohonan;
import org.Sikoling.ejb.abstraction.entity.log.KategoriFlowLog;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.main.restful.permohonan.RegisterPermohonanDTO;

public class FlowLogPermohonanDTO extends FlowLogDTO implements Serializable {

	private static final long serialVersionUID = 6037871086119389158L;
	private RegisterPermohonanDTO registerPermohonan;
	
	public FlowLogPermohonanDTO() {
	}
	
	public FlowLogPermohonanDTO(FlowLogPermohonan t) {
		super(t != null ? new FlowLog(
				t.getId(), 
				t.getTanggal(), 
				t.getKategoriFlowLog() != null ?
						new KategoriFlowLog(
								t.getKategoriFlowLog().getId(), 
								t.getKategoriFlowLog().getNama()
								) : null, 
				t.getPosisiTahapPemberkasan() != null ?
						new PosisiTahapPemberkasan(
								t.getPosisiTahapPemberkasan().getId(), 
								t.getPosisiTahapPemberkasan().getNama(), 
								t.getPosisiTahapPemberkasan().getKeterangan()
								) : null, 
				t.getKeterangan(), 
				t.getPengakses() != null ?
						new Authority(
								t.getPengakses().getId(), 
								null, 
								null, 
								null, 
								null, 
								t.getPengakses().getUserName()
								) : null
				) : null
			);
		
		if(t != null) {
			this.registerPermohonan = t.getRegisterPermohonan() != null ?
					new RegisterPermohonanDTO(t.getRegisterPermohonan()) : null;
		}
	}

	
	public RegisterPermohonanDTO getRegisterPermohonan() {
		return registerPermohonan;
	}

	
	public void setRegisterPermohonan(RegisterPermohonanDTO registerPermohonan) {
		this.registerPermohonan = registerPermohonan;
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
        
        final FlowLogPermohonanDTO other = (FlowLogPermohonanDTO) obj;
        
        if (!this.getId().equalsIgnoreCase(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "FlowLogPermohonanDTO{id="
				.concat(this.getId())
				.concat(", tanggal=")
				.concat(this.getTanggal().toString())
				.concat(", noSurat=")
				.concat(this.getRegisterPermohonan().getId())
				.concat("}");	  
	}

	public FlowLogPermohonan toFlowLogPermohonan() {
		return new FlowLogPermohonan(
				this.getId(), 
				this.getTanggal(), 
				this.getKategoriFlowLog() != null ? 
						this.getKategoriFlowLog().toKategoriFlowLog() : null, 
				this.getPosisiTahapPemberkasan() != null ?
						this.getPosisiTahapPemberkasan().toPosisiTahapPemberkasan() : null, 
				this.getKeterangan(), 
				this.getPengakses() != null ?
						this.getPengakses().toAuthority() : null, 
				this.registerPermohonan != null ?
						this.registerPermohonan.toRegisterPermohonan() : null
				);
	}
}
