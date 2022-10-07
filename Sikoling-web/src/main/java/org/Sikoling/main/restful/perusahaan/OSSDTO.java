package org.Sikoling.main.restful.perusahaan;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.Sikoling.ejb.abstraction.entity.OSS;


public class OSSDTO implements Serializable {

	private static final long serialVersionUID = 6822815110997753247L;
	private String nib;
	private Date tanggal;
	private List<KBLIDTO> kblis;
	
	public OSSDTO() {
		
	}
	
	public OSSDTO(OSS oss) {
		this.nib = oss.getNib();
		this.tanggal = oss.getTanggal();
		this.kblis = oss.getKblis()
				.stream()
				.map(t -> new KBLIDTO(t))
				.collect(Collectors.toList());
	}
	
	public OSSDTO(String nib, Date tanggal, List<KBLIDTO> kblis) {
		super();
		this.nib = nib;
		this.tanggal = tanggal;
		this.kblis = kblis;
	}

	public String getNib() {
		return nib;
	}

	public void setNib(String nib) {
		this.nib = nib;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public List<KBLIDTO> getKblis() {
		return kblis;
	}

	public void setKblis(List<KBLIDTO> kblis) {
		this.kblis = kblis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 23;
        hash = 113 * hash + Objects.hashCode(this.nib);
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
        
        final OSSDTO other = (OSSDTO) obj;
        
        if (!this.nib.equalsIgnoreCase(other.nib)) {
            return false;
        }        

        return true;
	}

	@Override
	public String toString() {
		return "OSSDTO{" + "nib=" + nib + ", tanggal=" + tanggal.toString() + '}';	  
	}
	
	public OSS toOSS() {
		return new OSS(nib, tanggal, kblis.stream().map(d -> d.toKBLI()).collect(Collectors.toList()));
	}

}
