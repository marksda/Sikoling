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
	private DesaData desaData;

	@JoinColumn(name = "kabupaten", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne
	private KabupatenData kabupatenData;

	@JoinColumn(name = "kecamatan", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne
	private KecamatanData kecamatanData;

	@Column(name="detail_alamat")
	private String keterangan;

	@JoinColumn(name = "propinsi", referencedColumnName = "id", insertable = true, updatable = false)
	@ManyToOne
	private PropinsiData propinsiData;

	public AlamatPerusahaanData() {
	}

	public DesaData getDesaData() {
		return this.desaData;
	}

	public void setDesaData(DesaData desaData) {
		this.desaData = desaData;
	}

	public KabupatenData getKabupatenData() {
		return this.kabupatenData;
	}

	public void setKabupatenData(KabupatenData kabupatenData) {
		this.kabupatenData = kabupatenData;
	}

	public KecamatanData getKecamatanData() {
		return this.kecamatanData;
	}

	public void setKecamatanData(KecamatanData kecamatanData) {
		this.kecamatanData = kecamatanData;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public PropinsiData getPropinsiData() {
		return this.propinsiData;
	}

	public void setPropinsiData(PropinsiData propinsiData) {
		this.propinsiData = propinsiData;
	}

}