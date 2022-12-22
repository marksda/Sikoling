package org.Sikoling.ejb.main.repository.permohonan;

import java.io.Serializable;
import java.time.LocalDate;

import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.perusahaan.PerusahaanData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_permohonan")
@NamedQueries({
	@NamedQuery(name="RegisterPermohonanData.findAll", query="SELECT p FROM RegisterPermohonanData p"),
	@NamedQuery(name="RegisterPermohonanData.findByPengakses", query="SELECT p FROM RegisterPermohonanData p WHERE p.npwp = :npwp")
})
public class RegisterPermohonanData implements Serializable {

	private static final long serialVersionUID = 2002625779202189639L;	
	
	@Id
	private String id;
	
	@JoinColumn(name="kategori_permohonan", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne
	private KategoriPermohonanData kategoriPermohonanData;
	
	@Column(name="tanggal_registrasi")
	private LocalDate tanggalRegistrasi;	
	
	@JoinColumn(name="perusahaan", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne
	private PerusahaanData perusahaanData;
	
	@JoinColumn(name="pengakses", referencedColumnName = "user_name", insertable = true, updatable = false)
	@ManyToOne
	private AutorisasiData autorisasiData;

}
