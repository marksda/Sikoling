package org.Sikoling.ejb.main.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_pj_pemrakarsa database table.
 * 
 */
@Entity
@Table(name="tbl_penanggung_jawab")
@NamedQuery(name="PenanggungJawabPemrakarsaData.findAll", query="SELECT p FROM PenanggungJawabPemrakarsaData p")
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
	@JoinColumn(name = "pemilik", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.ALL)
	private PemrakarsaData pemilik;

	public PenanggungJawabData() {
	}	

	public PenanggungJawabData(String id, DesaData desa, String detailAlamat, JabatanData jabatan,
			KabupatenData kabupaten, KecamatanData kecamatan, String nama, String nomorHandphone, String nomorIdentitas,
			PropinsiData propinsi, JenisKelaminData sex) {
		super();
		this.id = id;
		this.desa = desa;
		this.detailAlamat = detailAlamat;
		this.jabatan = jabatan;
		this.kabupaten = kabupaten;
		this.kecamatan = kecamatan;
		this.nama = nama;
		this.nomorHandphone = nomorHandphone;
		this.nomorIdentitas = nomorIdentitas;
		this.propinsi = propinsi;
		this.sex = sex;
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


	public PemrakarsaData getPemilik() {
		return pemilik;
	}

	public void setPemilik(PemrakarsaData pemilik) {
		this.pemilik = pemilik;
	}
}