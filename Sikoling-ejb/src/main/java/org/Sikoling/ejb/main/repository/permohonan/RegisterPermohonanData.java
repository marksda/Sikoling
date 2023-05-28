package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.log.StatusFlowLogData;
import org.Sikoling.ejb.main.repository.perusahaan.PegawaiPerusahaanData;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_register_permohonan")
@NamedQueries({
	@NamedQuery(name="RegisterPermohonanData.findAll", query="SELECT p FROM RegisterPermohonanData p"),
	@NamedQuery(name="RegisterPermohonanData.findByPerusahaan", query="SELECT p FROM RegisterPermohonanData p WHERE p.perusahaanData.id = :idPerusahaan"),
	@NamedQuery(name="RegisterPermohonanData.findByPengirim", query="SELECT p FROM RegisterPermohonanData p WHERE p.posisiTahapPemberkasanPengirimData.id = :idPengirim"),
	@NamedQuery(name="RegisterPermohonanData.findByPenerima", query="SELECT p FROM RegisterPermohonanData p WHERE p.posisiTahapPemberkasanPenerimaData.id = :idPenerima"),
	@NamedQuery(name="RegisterPermohonanData.findByPenerimaAtauPenerima", query="SELECT p FROM RegisterPermohonanData p WHERE p.posisiTahapPemberkasanPengirimData.id = :idPengirim OR p.posisiTahapPemberkasanPenerimaData.id = :idPenerima"),
	@NamedQuery(name="RegisterPermohonanData.findByPenerimaAtauPenerimaOnProcess", query="SELECT p FROM RegisterPermohonanData p WHERE (p.posisiTahapPemberkasanPengirimData.id = :idPengirim OR p.posisiTahapPemberkasanPenerimaData.id = :idPenerima) AND p.statusFlowData IN :daftarIdFlow"),
	@NamedQuery(name="RegisterPermohonanData.findByPengakses", query="SELECT p FROM RegisterPermohonanData p WHERE p.autorisasiData.id = :idKreator")
})
public class RegisterPermohonanData implements Serializable {

	private static final long serialVersionUID = 2002625779202189639L;	
	
	@Id
	private String id;
	
	@JoinColumn(name="kategori_permohonan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private KategoriPermohonanData kategoriPermohonanData;	
	
	@Column(name="tanggal_registrasi", insertable = true, updatable = true)
	private LocalDate tanggalRegistrasi;	
	
	@JoinColumn(name="perusahaan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private RegisterPerusahaanData perusahaanData;
	
	@JoinColumn(name="pengakses", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private AutorisasiData autorisasiData;
	
	@JoinColumn(name="kategori_pengurus_permohonan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private KategoriPengurusPermohonanData kategoriPengurusPermohonanData;
	
	@JoinColumn(name="posisi_tahap_pemberkasan_pengirim", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private PosisiTahapPemberkasanData posisiTahapPemberkasanPengirimData;
	
	@JoinColumn(name="posisi_tahap_pemberkasan_penerima", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private PosisiTahapPemberkasanData posisiTahapPemberkasanPenerimaData;
	
	@JoinColumn(name="penanggung_jawab", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private PegawaiPerusahaanData penanggungJawab;
	
	@JoinColumn(name="status_flow", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private StatusFlowLogData statusFlowData;
	
	@OneToMany(mappedBy = "registerPermohonan", cascade = CascadeType.ALL)
	private List<DokumenPersyaratanPermohonanData> daftarDokumenSyarat;
	
//	@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
	@OneToOne(mappedBy = "registerPermohonanData", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private RegisterPermohonanSuratArahanData permohonanSuratArahanData;
	
//	@OneToMany(mappedBy = "registerPermohonan", fetch = FetchType.LAZY)
//	private List<FlowLogPermohonanData> daftarFlowLogPermohonanData;

	public RegisterPermohonanData() {
	}
	
	public RegisterPermohonanSuratArahanData getPermohonanSuratArahanData() {
		return permohonanSuratArahanData;
	}

	public void setPermohonanSuratArahanData(RegisterPermohonanSuratArahanData permohonanSuratArahanData) {
		this.permohonanSuratArahanData = permohonanSuratArahanData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KategoriPermohonanData getKategoriPermohonanData() {
		return kategoriPermohonanData;
	}

	public void setKategoriPermohonanData(KategoriPermohonanData kategoriPermohonanData) {
		this.kategoriPermohonanData = kategoriPermohonanData;
	}

	public LocalDate getTanggalRegistrasi() {
		return tanggalRegistrasi;
	}

	public void setTanggalRegistrasi(LocalDate tanggalRegistrasi) {
		this.tanggalRegistrasi = tanggalRegistrasi;
	}

	public RegisterPerusahaanData getPerusahaanData() {
		return perusahaanData;
	}

	public void setPerusahaanData(RegisterPerusahaanData perusahaanData) {
		this.perusahaanData = perusahaanData;
	}

	public AutorisasiData getAutorisasiData() {
		return autorisasiData;
	}

	public void setAutorisasiData(AutorisasiData autorisasiData) {
		this.autorisasiData = autorisasiData;
	}

	public KategoriPengurusPermohonanData getKategoriPengurusPermohonanData() {
		return kategoriPengurusPermohonanData;
	}

	public void setKategoriPengurusPermohonanData(KategoriPengurusPermohonanData kategoriPengurusPermohonanData) {
		this.kategoriPengurusPermohonanData = kategoriPengurusPermohonanData;
	}

	public List<DokumenPersyaratanPermohonanData> getDaftarDokumenSyarat() {
		return daftarDokumenSyarat;
	}

	public void setDaftarDokumenSyarat(List<DokumenPersyaratanPermohonanData> daftarDokumenSyarat) {
		this.daftarDokumenSyarat = daftarDokumenSyarat;
	}

	public PegawaiPerusahaanData getPenanggungJawab() {
		return penanggungJawab;
	}

	public void setPenanggungJawab(PegawaiPerusahaanData penanggungJawab) {
		this.penanggungJawab = penanggungJawab;
	}

	public PosisiTahapPemberkasanData getPosisiTahapPemberkasanPengirimData() {
		return posisiTahapPemberkasanPengirimData;
	}

	public void setPosisiTahapPemberkasanPengirimData(PosisiTahapPemberkasanData posisiTahapPemberkasanPengirimData) {
		this.posisiTahapPemberkasanPengirimData = posisiTahapPemberkasanPengirimData;
	}

	public PosisiTahapPemberkasanData getPosisiTahapPemberkasanPenerimaData() {
		return posisiTahapPemberkasanPenerimaData;
	}

	public void setPosisiTahapPemberkasanPenerimaData(PosisiTahapPemberkasanData posisiTahapPemberkasanPenerimaData) {
		this.posisiTahapPemberkasanPenerimaData = posisiTahapPemberkasanPenerimaData;
	}

	public StatusFlowLogData getStatusFlowData() {
		return statusFlowData;
	}

	public void setStatusFlowData(StatusFlowLogData statusFlowData) {
		this.statusFlowData = statusFlowData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	

	@Override
	public int hashCode() {
		int hash = 713;
        hash = 1071 * hash + Objects.hashCode(id);
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
        
        final RegisterPermohonanData other = (RegisterPermohonanData) obj;
        
        if (!id.equals(other.getId())) {
            return false;
        }        

        return true;
	}
	
	@Override
	public String toString() {
		return "RegisterPermohonanData{id="
				.concat(id)
				.concat("}");	  
	}
	 
}
