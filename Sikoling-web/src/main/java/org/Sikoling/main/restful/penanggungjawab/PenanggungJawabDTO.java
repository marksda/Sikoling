package org.Sikoling.main.restful.penanggungjawab;

import java.io.Serializable;
import java.util.Objects;

import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.main.restful.jabatan.JabatanDTO;
import org.Sikoling.main.restful.sex.JenisKelaminDTO;

public class PenanggungJawabDTO implements Serializable {
	private static final long serialVersionUID = 3847984353247321174L;
	private String id;
	private String nama;
	private AlamatDTO alamat;
	private JabatanDTO jabatan;
	private JenisKelaminDTO jenisKelamin;
	private String noIdentitas;
	private String noHandphone;
	private String idPemilik;
	
	
	public PenanggungJawabDTO() {
		
	}
	
	public PenanggungJawabDTO(PenanggungJawab penanggungJawab) {
		this.id = penanggungJawab.getId();
		this.nama = penanggungJawab.getNama();
//		this.alamat = new Al
		this.jabatan = new JabatanDTO(penanggungJawab.getJabatan());
		this.jenisKelamin = new JenisKelaminDTO(penanggungJawab.getJenisKelamin());
		this.noIdentitas = penanggungJawab.getNoIdentitas();
		this.noHandphone = penanggungJawab.getNoHandphone();
		this.idPemilik = penanggungJawab.getIdPemilik();
	}
	
	public PenanggungJawabDTO(String id, String nama, AlamatDTO alamat, JabatanDTO jabatan,
			JenisKelaminDTO jenisKelamin, String noIdentitas, String noHandphone, String idPemilik) {
		super();
		this.id = id;
		this.nama = nama;
		this.alamat = alamat;
		this.jabatan = jabatan;
		this.jenisKelamin = jenisKelamin;
		this.noIdentitas = noIdentitas;
		this.noHandphone = noHandphone;
		this.idPemilik = idPemilik;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public AlamatDTO getAlamat() {
		return alamat;
	}

	public void setAlamat(AlamatDTO alamat) {
		this.alamat = alamat;
	}

	public JabatanDTO getJabatan() {
		return jabatan;
	}

	public void setJabatan(JabatanDTO jabatan) {
		this.jabatan = jabatan;
	}

	public JenisKelaminDTO getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(JenisKelaminDTO jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public String getNoIdentitas() {
		return noIdentitas;
	}

	public void setNoIdentitas(String noIdentitas) {
		this.noIdentitas = noIdentitas;
	}

	public String getNoHandphone() {
		return noHandphone;
	}

	public void setNoHandphone(String noHandphone) {
		this.noHandphone = noHandphone;
	}

	public String getIdPemilik() {
		return idPemilik;
	}

	public void setIdPemilik(String idPemilik) {
		this.idPemilik = idPemilik;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int hashCode() {
		int hash = 29;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.nama);
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
        
        final PenanggungJawabDTO other = (PenanggungJawabDTO) obj;
        
        if (this.id != other.id) {
            return false;
        }
        
        if (this.nama != other.nama) {
            return false;
        }

        return true;
	}

	@Override
	public String toString() {
		return "PenanggungJawabDTO{" + "id=" + id + ", nama=" + nama + '}';	  
	}

	public PenanggungJawab toPenanggungJawab() {		
		return new PenanggungJawab(id, nama, alamat.toAlamat(), jabatan.toJabatan(), jenisKelamin.toJenisKelamin(), 
				noIdentitas, noHandphone, idPemilik);
	}

}
