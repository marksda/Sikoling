package org.Sikoling.ejb.abstraction.entity.log;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;

public class FlowLogPermohonan extends FlowLog implements Serializable {

	private static final long serialVersionUID = 1100660154440413260L;
	private final RegisterPermohonan registerPermohonan;
	
	public FlowLogPermohonan(String id, LocalDate tanggal, KategoriFlowLog kategoriFlowLog,
			PosisiTahapPemberkasan pengirimBerkas, PosisiTahapPemberkasan penerimaBerkas, 
			StatusFlowLog statusFlowLog, String keterangan, Otoritas pengakses,
			RegisterPermohonan registerPermohonan) {
		super(id, tanggal, kategoriFlowLog, pengirimBerkas, penerimaBerkas, statusFlowLog, keterangan, pengakses);
		this.registerPermohonan = registerPermohonan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RegisterPermohonan getRegisterPermohonan() {
		return registerPermohonan;
	}

	@Override
	public int hashCode() {
		int hash = 1171;
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
        
        final FlowLogPermohonan other = (FlowLogPermohonan) obj;
        
        if (!this.getId().equals(other.getId())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "FlowLogPermohonan {"
				.concat("id=")
				.concat(this.getId())
				.concat(", ")
				.concat("tanggal=")
				.concat(this.getTanggal().toString())
				.concat("}");
	}	

}
