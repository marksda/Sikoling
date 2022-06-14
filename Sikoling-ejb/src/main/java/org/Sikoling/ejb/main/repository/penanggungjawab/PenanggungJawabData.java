package org.Sikoling.ejb.main.repository.penanggungjawab;

import java.io.Serializable;
import jakarta.persistence.*;

import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.jabatan.JabatanData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;


@Entity
@Table(name="master.tbl_penanggung_jawab")
@NamedQueries({
@NamedQuery(name="PenanggungJawabData.findAll", query="SELECT p FROM PenanggungJawabData p"),
@NamedQuery(name="PenanggungJawabData.findAllByQueryNama", query="SELECT p FROM PenanggungJawabData p WHERE p.nama ILIKE :nama"),
@NamedQuery(name="PenanggungJawabData.findAllByPemilik", query="SELECT p FROM PenanggungJawabData p WHERE p.pemilik = :idPemilik"),
@NamedQuery(name="PenanggungJawabData.findAllByPemilikANDQueryNama", query="SELECT p FROM PenanggungJawabData p WHERE p.nama ILIKE :nama AND p.pemilik = :idPemilik")
})
public class PenanggungJawabData implements Serializable {
	private static final long serialVersionUID = 6630651928270252860L;

	@Id
	private String id;

	@Column(name="desa")
	@JoinColumn(name = "desa", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private DesaData desa;

	@Column(name="detail_alamat")
	private String detailAlamat;

	@Column(name="jabatan")
	@JoinColumn(name = "jabatan", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private JabatanData jabatan;

	@Column(name="kabupaten")
	@JoinColumn(name = "kabupaten", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private KabupatenData kabupaten;

	@Column(name="kecamatan")
	@JoinColumn(name = "kecamatan", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private KecamatanData kecamatan;

	@Column(name="nama")
	private String nama;

	@Column(name="nomor_handphone")
	private String nomorHandphone;

	@Column(name="nomor_identitas")
	private String nomorIdentitas;

	@Column(name="propinsi")
	@JoinColumn(name = "propinsi", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private PropinsiData propinsi;

	@Column(name="sex")
	@JoinColumn(name = "sex", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private JenisKelaminData sex;
	
	@Column(name="pemilik")
	private String pemilik;

	public PenanggungJawabData() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DesaData getDesa() {
		return this.desa;
	}

	public void setDesa(DesaData desa) {
		this.desa = desa;
	}

	public String getDetailAlamat() {
		return this.detailAlamat;
	}

	public void setDetailAlamat(String detailAlamat) {
		this.detailAlamat = detailAlamat;
	}

	public JabatanData getJabatan() {
		return this.jabatan;
	}

	public void setJabatan(JabatanData jabatan) {
		this.jabatan = jabatan;
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

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNomorHandphone() {
		return this.nomorHandphone;
	}

	public void setNomorHandphone(String nomorHandphone) {
		this.nomorHandphone = nomorHandphone;
	}

	public String getNomorIdentitas() {
		return this.nomorIdentitas;
	}

	public void setNomorIdentitas(String nomorIdentitas) {
		this.nomorIdentitas = nomorIdentitas;
	}

	public PropinsiData getPropinsi() {
		return this.propinsi;
	}

	public void setPropinsi(PropinsiData propinsi) {
		this.propinsi = propinsi;
	}

	public JenisKelaminData getSex() {
		return this.sex;
	}

	public void setSex(JenisKelaminData sex) {
		this.sex = sex;
	}


	public String getPemilik() {
		return pemilik;
	}

	public void setPemilik(String pemilik) {
		this.pemilik = pemilik;
	}
}