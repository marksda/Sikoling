package org.Sikoling.main.restful.log;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.log.FlowLog;
import org.Sikoling.main.restful.authority.AuthorityDTO;
import org.Sikoling.main.restful.permohonan.PosisiTahapPemberkasanDTO;

public class FlowLogDTO implements Serializable {

	private static final long serialVersionUID = -4918969599694746478L;
	private String id;
	private LocalDate tanggal;
	private KategoriFlowLogDTO kategoriFlowLog;
	private PosisiTahapPemberkasanDTO posisiTahapPemberkasan;
	private String keterangan;
	private AuthorityDTO pengakses;
	
	public FlowLogDTO() {
	}
	
	public FlowLogDTO(FlowLog t) {
		if(t != null) {			
			this.id = t.getId();
			this.tanggal = t.getTanggal();
			this.kategoriFlowLog = t.getKategoriFlowLog() != null ?
					new KategoriFlowLogDTO(t.getKategoriFlowLog()) : null;
			this.posisiTahapPemberkasan = t.getPosisiTahapPemberkasan() != null ?
					new PosisiTahapPemberkasanDTO(t.getPosisiTahapPemberkasan()) : null;
			this.keterangan = t.getKeterangan();
			this.pengakses = t.getPengakses() != null ?
					new AuthorityDTO(t.getPengakses()) : null;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getTanggal() {
		return tanggal;
	}

	public void setTanggal(LocalDate tanggal) {
		this.tanggal = tanggal;
	}

	public KategoriFlowLogDTO getKategoriFlowLog() {
		return kategoriFlowLog;
	}

	public void setKategoriFlowLog(KategoriFlowLogDTO kategoriFlowLog) {
		this.kategoriFlowLog = kategoriFlowLog;
	}

	public PosisiTahapPemberkasanDTO getPosisiTahapPemberkasan() {
		return posisiTahapPemberkasan;
	}

	public void setPosisiTahapPemberkasan(PosisiTahapPemberkasanDTO posisiTahapPemberkasan) {
		this.posisiTahapPemberkasan = posisiTahapPemberkasan;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public AuthorityDTO getPengakses() {
		return pengakses;
	}

	public void setPengakses(AuthorityDTO pengakses) {
		this.pengakses = pengakses;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 1113;
        hash = 171 * hash + Objects.hashCode(this.id);
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
        
        final FlowLogDTO other = (FlowLogDTO) obj;
        
        if (!this.id.equalsIgnoreCase(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "FlowLogDTO{id="
				.concat(id)
				.concat(", tanggal=")
				.concat(tanggal.toString())
				.concat("}");	  
	}

	public FlowLog toFlowLog() {
		return new FlowLog(
				id, 
				tanggal, 
				kategoriFlowLog != null ? kategoriFlowLog.toKategoriFlowLog() : null, 
				posisiTahapPemberkasan != null ? posisiTahapPemberkasan.toPosisiTahapPemberkasan() : null, 
				keterangan, 
				pengakses != null ? pengakses.toAuthority() : null
				);
	}

}
