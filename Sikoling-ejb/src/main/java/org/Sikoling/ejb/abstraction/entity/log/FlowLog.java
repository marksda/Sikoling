package org.Sikoling.ejb.abstraction.entity.log;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;

public class FlowLog implements Serializable {

	private static final long serialVersionUID = -1215598809352960984L;
	private final String id;
	private final LocalDate tanggal;
	private final KategoriFlowLog kategoriFlowLog;
	private final PosisiTahapPemberkasan posisiTahapPemberkasan;
	private final String keterangan;
	private final Authority pengakses;
	
	public FlowLog(String id, LocalDate tanggal, KategoriFlowLog kategoriFlowLog,
			PosisiTahapPemberkasan posisiTahapPemberkasan, String keterangan, Authority pengakses) {
		super();
		this.id = id;
		this.tanggal = tanggal;
		this.kategoriFlowLog = kategoriFlowLog;
		this.posisiTahapPemberkasan = posisiTahapPemberkasan;
		this.keterangan = keterangan;
		this.pengakses = pengakses;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public LocalDate getTanggal() {
		return tanggal;
	}

	public KategoriFlowLog getKategoriFlowLog() {
		return kategoriFlowLog;
	}

	public PosisiTahapPemberkasan getPosisiTahapPemberkasan() {
		return posisiTahapPemberkasan;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public Authority getPengakses() {
		return pengakses;
	}
	
	@Override
	public int hashCode() {
		int hash = 1171;
		hash = 171 * hash + Objects.hashCode(id);
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
        
        final FlowLog other = (FlowLog) obj;
        
        if (!this.getId().equals(other.getId())) {
            return false;
        }
        
        return true;
	}
	
	@Override
	public String toString() {
		return "FlowLog {"
				.concat("id=")
				.concat(this.id)
				.concat(", ")
				.concat("tanggal=")
				.concat(this.tanggal.toString())
				.concat("}");
	}	


}
