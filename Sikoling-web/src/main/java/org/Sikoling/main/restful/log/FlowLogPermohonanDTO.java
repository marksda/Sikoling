package org.Sikoling.main.restful.log;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.log.FlowLogPermohonan;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.main.restful.autority.AutorityDTO;
import org.Sikoling.main.restful.permohonan.PosisiTahapPemberkasanDTO;
import org.Sikoling.main.restful.permohonan.RegisterPermohonanArahanDTO;
import org.Sikoling.main.restful.permohonan.RegisterPermohonanDTO;

public class FlowLogPermohonanDTO extends FlowLogDTO implements Serializable {

	private static final long serialVersionUID = 6037871086119389158L;
	private RegisterPermohonanDTO registerPermohonan;
	
	public FlowLogPermohonanDTO() {
	}
	
	public FlowLogPermohonanDTO(FlowLogPermohonan t) {
		super();		
		if(t != null) {
			this.setId(t.getId());
			this.setTanggal(t.getTanggal());
			KategoriFlowLogDTO kategoriFlowLogDTO = t.getKategoriFlowLog() != null ?
					new KategoriFlowLogDTO(t.getKategoriFlowLog()) : null;
			this.setKategoriFlowLog(kategoriFlowLogDTO);
			
			PosisiTahapPemberkasanDTO pengirimBerkas = t.getPengirimBerkas() != null ?
					new PosisiTahapPemberkasanDTO(t.getPengirimBerkas()) : null;
			this.setPengirimBerkas(pengirimBerkas);
			
			PosisiTahapPemberkasanDTO penerimaBerkas = t.getPenerimaBerkas() != null ?
					new PosisiTahapPemberkasanDTO(t.getPenerimaBerkas()) : null;
			this.setPenerimaBerkas(penerimaBerkas);
			
			StatusFlowLogDTO statusFlowLog = t.getStatusFlowLog() != null ?
					new StatusFlowLogDTO(t.getStatusFlowLog()) : null;
			this.setStatusFlowLog(statusFlowLog);
			
			this.setKeterangan(t.getKeterangan());
			AutorityDTO pengakses = t.getPengakses() != null ?
					new AutorityDTO(t.getPengakses()) : null;
			this.setPengakses(pengakses);
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
		RegisterPermohonan registerPermohonan = null;
		
		if(this.registerPermohonan instanceof RegisterPermohonanArahanDTO) {
			RegisterPermohonanArahanDTO s = (RegisterPermohonanArahanDTO) this.registerPermohonan;
			registerPermohonan = s.toRegisterPermohonanArahan();
		}
		
		return new FlowLogPermohonan(
				this.getId(), 
				this.getTanggal(), 
				this.getKategoriFlowLog() != null ? 
				this.getKategoriFlowLog().toKategoriFlowLog() : null, 
				this.getPengirimBerkas() != null ?
						this.getPengirimBerkas().toPosisiTahapPemberkasan() : null, 
				this.getPenerimaBerkas() != null ?
						this.getPenerimaBerkas().toPosisiTahapPemberkasan() : null,	
				this.getStatusFlowLog() != null ?
								this.getStatusFlowLog().toStatusFlowLog() : null,  
				this.getKeterangan(), 
				this.getPengakses() != null ?
						this.getPengakses().toAuthority() : null, 
				this.registerPermohonan != null ? registerPermohonan : null
				);
	}
}
