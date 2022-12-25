package org.Sikoling.ejb.main.repository.dokumen;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_surat_arahan")
@NamedQueries({
	@NamedQuery(name="SuratArahanData.findAll", query="SELECT d FROM SuratArahanData d")
})
public class SuratArahanData implements Serializable {

	private static final long serialVersionUID = -3597035130639423972L;
	
	@Id
	private String id;
	
	@Column(name = "no_surat")
	private String noSurat;
	
	@Column(name = "tanggal_surat")
	private LocalDate tanggalSurat;
	
	@Column(name = "perihal_surat")
	private String perihalSurat;
	
	@Column(name = "uraian_kegiatan")
	private String uraianKegiatan;
	
}
