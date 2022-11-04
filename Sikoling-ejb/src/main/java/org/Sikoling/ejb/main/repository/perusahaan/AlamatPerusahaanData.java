package org.Sikoling.ejb.main.repository.perusahaan;

import java.io.Serializable;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import jakarta.persistence.*;


@Embeddable
@AttributeOverrides({
@AttributeOverride( name = "keterangan", column = @Column(name = "detail_alamat"))
})
public class AlamatPerusahaanData implements Serializable {
	
	private static final long serialVersionUID = -8334774162037892428L;

	@JoinColumn(name = "desa", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne
	private DesaData desa;

	@JoinColumn(name = "kabupaten", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne
	private KabupatenData kabupaten;

	@JoinColumn(name = "kecamatan", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne
	private KecamatanData kecamatan;

	@Column(name="detail_alamat")
	private String keterangan;

	@JoinColumn(name = "propinsi", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne
	private PropinsiData propinsi;

	public AlamatPerusahaanData() {
	}

	public DesaData getDesa() {
		return this.desa;
	}

	public void setDesa(DesaData desa) {
		this.desa = desa;
	}

	public KabupatenData getKabupaten() {
		return this.kabupaten;
	}

	public void setKabupaten(KabupatenData kabupaten) {
		this.kabupaten = kabupaten;
	}

	public KecamatanData getKecamatan() {
		return this.kecamatan;
	}

	public void setKecamatan(KecamatanData kecamatan) {
		this.kecamatan = kecamatan;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public PropinsiData getPropinsi() {
		return this.propinsi;
	}

	public void setPropinsi(PropinsiData propinsi) {
		this.propinsi = propinsi;
	}

}