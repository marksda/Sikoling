package org.Sikoling.main.restful.log;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.log.FlowLog;
import org.Sikoling.main.restful.otoritas.OtoritasDTO;
import org.Sikoling.main.restful.permohonan.PosisiTahapPemberkasanDTO;

public class FlowLogDTO implements Serializable {

	private static final long serialVersionUID = -4918969599694746478L;
	private String id;
	private LocalDate tanggal;
	private KategoriFlowLogDTO kategoriFlowLog;
	private PosisiTahapPemberkasanDTO pengirimBerkas;
	private PosisiTahapPemberkasanDTO penerimaBerkas;
	private StatusFlowLogDTO statusFlowLog;
	private String keterangan;
	private OtoritasDTO pengakses;
	
	public FlowLogDTO() {
	}
	
	public FlowLogDTO(FlowLog t) {
		if(t != null) {			
			this.id = t.getId();
			this.tanggal = t.getTanggal();
			this.kategoriFlowLog = t.getKategoriFlowLog() != null ?
					new KategoriFlowLogDTO(t.getKategoriFlowLog()) : null;
			this.pengirimBerkas = t.getPengirimBerkas() != null ?
					new PosisiTahapPemberkasanDTO(t.getPengirimBerkas()) : null;
			this.penerimaBerkas = t.getPenerimaBerkas() != null ?
					new PosisiTahapPemberkasanDTO(t.getPenerimaBerkas()) : null;
			this.statusFlowLog = t.getStatusFlowLog() != null ?
					new StatusFlowLogDTO(t.getStatusFlowLog()) : null;
			this.keterangan = t.getKeterangan();
			this.pengakses = t.getPengakses() != null ?
					new OtoritasDTO(t.getPengakses()) : null;
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
	
	public PosisiTahapPemberkasanDTO getPengirimBerkas() {
		return pengirimBerkas;
	}

	public void setPengirimBerkas(PosisiTahapPemberkasanDTO pengirimBerkas) {
		this.pengirimBerkas = pengirimBerkas;
	}

	public PosisiTahapPemberkasanDTO getPenerimaBerkas() {
		return penerimaBerkas;
	}

	public void setPenerimaBerkas(PosisiTahapPemberkasanDTO penerimaBerkas) {
		this.penerimaBerkas = penerimaBerkas;
	}

	public StatusFlowLogDTO getStatusFlowLog() {
		return statusFlowLog;
	}

	public void setStatusFlowLog(StatusFlowLogDTO statusFlowLog) {
		this.statusFlowLog = statusFlowLog;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public OtoritasDTO getPengakses() {
		return pengakses;
	}

	public void setPengakses(OtoritasDTO pengakses) {
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
				pengirimBerkas != null ?
						pengirimBerkas.toPosisiTahapPemberkasan() : null, 
				penerimaBerkas != null ?
						penerimaBerkas.toPosisiTahapPemberkasan() : null,	
				statusFlowLog != null ?
						statusFlowLog.toStatusFlowLog() : null,
				keterangan, 
				pengakses != null ? pengakses.toOtoritas() : null
				);
	}

}
