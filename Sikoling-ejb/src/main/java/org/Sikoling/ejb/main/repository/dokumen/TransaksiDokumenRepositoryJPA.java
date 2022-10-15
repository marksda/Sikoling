package org.Sikoling.ejb.main.repository.dokumen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.entity.ItemAttributeDokumen;
import org.Sikoling.ejb.abstraction.entity.TransaksiDokumen;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.ITransaksiDokumenRepository;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.pelakuusaha.KategoriPelakuUsahaData;
import org.Sikoling.ejb.main.repository.perusahaan.AlamatPerusahaanData;
import org.Sikoling.ejb.main.repository.perusahaan.KontakPerusahaanData;
import org.Sikoling.ejb.main.repository.perusahaan.PerusahaanData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;

public class TransaksiDokumenRepositoryJPA implements ITransaksiDokumenRepository<TransaksiDokumen> {

	private final EntityManager entityManager;	
	
	public TransaksiDokumenRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<TransaksiDokumen> getAll() {
		return entityManager.createNamedQuery("TransaksiDokumenData.findAll", TransaksiDokumenData.class)
				.getResultList()
				.stream()
				.map(d -> convertTransaksiDokumenDataToTransaksiDokumen(d))
				.collect(Collectors.toList());
	}

	@Override
	public TransaksiDokumen save(TransaksiDokumen t) {
		TransaksiDokumenData transaksiDokumenData = convertTransaksiDokumenToTransaksiDokumenData(t);
		entityManager.persist(transaksiDokumenData);
		entityManager.flush();
		
		return convertTransaksiDokumenDataToTransaksiDokumen(transaksiDokumenData);
	}

	@Override
	public TransaksiDokumen update(TransaksiDokumen t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransaksiDokumen> getAllByPage(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransaksiDokumen> getByNama(String nama) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransaksiDokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	private TransaksiDokumen convertTransaksiDokumenDataToTransaksiDokumen(TransaksiDokumenData d) {
		KategoriDokumen kategoriDokumen = new KategoriDokumen(
				d.getDokumen().getKategori().getId(), 
				d.getDokumen().getKategori().getNama());	
		
		Dokumen dokumen = new Dokumen(
				d.getDokumen().getId(), 
				kategoriDokumen, 
				d.getDokumen().getNama());
		
		Jsonb jsonb = JsonbBuilder.create();
		@SuppressWarnings("serial")
		List<ItemAttributeDokumen<Object>> attributeDokumen = jsonb
				.fromJson(
						d.getAttribute(), 
						new ArrayList<ItemAttributeDokumen<Object>>(){}.getClass().getGenericSuperclass());
		
//		AutorisasiData autorisasiData = d.getUploader();
//		
//		PersonData personData  = autorisasiData.getPersonData();
//		
//		JenisKelaminData jenisKelaminData = personData.getSex();
//		
//		AlamatPersonData alamatPersonData = personData.getAlamat();
//		
//		Propinsi propinsiPerson = new Propinsi(
//				alamatPersonData.getPropinsi().getId(), 
//				alamatPersonData.getPropinsi().getNama());
//		
//		Kabupaten kabupatenPerson = new Kabupaten(
//				alamatPersonData.getKabupaten().getId(), 
//				alamatPersonData.getKabupaten().getNama());
//		
//		Kecamatan kecamatanPerson = new Kecamatan(
//				alamatPersonData.getKecamatan().getId(), 
//				alamatPersonData.getKecamatan().getNama());
//		
//		Desa desaPerson = new Desa(
//				alamatPersonData.getDesa().getId(), 
//				alamatPersonData.getDesa().getNama());
//		
//		Alamat alamatPerson = new Alamat(
//				propinsiPerson, kabupatenPerson, kecamatanPerson, 
//				desaPerson, alamatPersonData.getDetailAlamat());
//				
//		KontakPersonData kontakPersonData = personData.getKontak();
//		
//		Kontak kontak = new Kontak(kontakPersonData.getTelepone(), null, kontakPersonData.getEmail());
//		JenisKelamin jenisKelamin = new JenisKelamin(jenisKelaminData.getId(), jenisKelaminData.getNama());
//		
//		Person person = new Person(
//				personData.getId(), personData.getNama(), 
//				jenisKelamin, 
//				alamatPerson, personData.getScanKtp(), kontak);
//		
//		HakAksesData hakAksesData = autorisasiData.getHakAkses();
//		HakAkses hakAkses = new HakAkses(
//				hakAksesData.getId(), 
//				hakAksesData.getNama(), 
//				hakAksesData.getKeterangan());
//		
//		Autorisasi autorisasi = new Autorisasi(
//				autorisasiData.getId(), 
//				person, 
//				autorisasiData.getIdLama(), 
//				hakAkses, 
//				autorisasiData.getStatusInternal(), 
//				autorisasiData.getIsVerified(), 
//				autorisasiData.getUserName());
		
		PerusahaanData perusahaanData = d.getPerusahaan();
		
		ModelPerizinanData modelPerizinanData = perusahaanData.getModelPerizinanData();
		ModelPerizinan modelPerizinan = new ModelPerizinan(modelPerizinanData.getId(), 
				modelPerizinanData.getNama(), modelPerizinanData.getSingkatan());
		
		SkalaUsahaData skalaUsahaData = perusahaanData.getSkalaUsaha();
		SkalaUsaha skalaUsaha = new SkalaUsaha(skalaUsahaData.getId(), 
				skalaUsahaData.getNama(), skalaUsahaData.getSingkatan());
		
		PelakuUsahaData pelakuUsahaData = perusahaanData.getPelakuUsahaData();
		KategoriPelakuUsahaData kategoriPelakuUsahaData = pelakuUsahaData.getKategoriPelakuUsahaData();
		KategoriPelakuUsaha kategoriPelakuUsaha = new KategoriPelakuUsaha(kategoriPelakuUsahaData.getId(), 
				kategoriPelakuUsahaData.getNama());
		PelakuUsaha detailPelakuUsaha = new PelakuUsaha(
				pelakuUsahaData.getId(), pelakuUsahaData.getNama(), 
				pelakuUsahaData.getSingkatan(), kategoriPelakuUsaha);
		
		AlamatPerusahaanData alamatPerusahaanData = perusahaanData.getAlamatPerusahaanData();
		PropinsiData propinsiDataPerusahaan = alamatPerusahaanData.getPropinsi();
		Propinsi propinsiPerusahaan = new Propinsi(
				propinsiDataPerusahaan.getId(), propinsiDataPerusahaan.getNama());
		KabupatenData kabupatenDataPerusahaan = alamatPerusahaanData.getKabupaten();
		Kabupaten kabupatenPerusahaan = new Kabupaten(
				kabupatenDataPerusahaan.getId(), kabupatenDataPerusahaan.getNama());
		KecamatanData kecamatanDataPerusahaan = alamatPerusahaanData.getKecamatan();
		Kecamatan kecamatanPerusahaan = new Kecamatan(
				kecamatanDataPerusahaan.getId(), kecamatanDataPerusahaan.getNama());
		DesaData desaDataPerusahaan = alamatPerusahaanData.getDesa();
		Desa desaPerusahaan = new Desa(desaDataPerusahaan.getId(), desaDataPerusahaan.getNama());
		
		Alamat alamatPerusahaan = new Alamat(
				propinsiPerusahaan, kabupatenPerusahaan, kecamatanPerusahaan, 
				desaPerusahaan, alamatPerusahaanData.getKeterangan());
		
		KontakPerusahaanData kontakData = perusahaanData.getKontakPerusahaanData();
		Kontak kontakPerusahaan = new Kontak(
				kontakData.getTelepone(), kontakData.getFax(), kontakData.getEmail());
		
		
		Perusahaan perusahaan = new Perusahaan(
				perusahaanData.getId(), 
				perusahaanData.getNama(), 
				modelPerizinan, 
				skalaUsaha,
				detailPelakuUsaha, 
				alamatPerusahaan, 
				kontakPerusahaan);
		
		return new TransaksiDokumen(
				d.getId(), 
				dokumen, 
				attributeDokumen, 
				d.getTanggalUpload(), 
				d.getIsBerlaku(), 
				perusahaan);
	}
	
	private TransaksiDokumenData convertTransaksiDokumenToTransaksiDokumenData(TransaksiDokumen t) {		
		
		Perusahaan perusahaan = t.getPerusahaan();
		
		Alamat alamatPerusahaan = perusahaan.getAlamat();
		AlamatPerusahaanData alamatPerusahaanData = new AlamatPerusahaanData();
		Propinsi propinsiPerusahaan = alamatPerusahaan.getPropinsi();
		PropinsiData propinsiPerusahaanData = new PropinsiData();
		propinsiPerusahaanData.setId(propinsiPerusahaan.getId());
		propinsiPerusahaanData.setNama(propinsiPerusahaan.getNama());
		Kabupaten kabupatenPerusahaan = alamatPerusahaan.getKabupaten();
		KabupatenData kabupatenPerusahaanData = new KabupatenData();
		kabupatenPerusahaanData.setId(kabupatenPerusahaan.getId());
		kabupatenPerusahaanData.setNama(kabupatenPerusahaan.getNama());
		kabupatenPerusahaanData.setPropinsi(propinsiPerusahaanData);
		Kecamatan kecamatanPerusahaan = alamatPerusahaan.getKecamatan();
		KecamatanData kecamatanPerusahaanData = new KecamatanData();
		kecamatanPerusahaanData.setId(kecamatanPerusahaan.getId());
		kecamatanPerusahaanData.setNama(kecamatanPerusahaan.getNama());
		kecamatanPerusahaanData.setKabupaten(kabupatenPerusahaanData);
		Desa desaPerusahaan = alamatPerusahaan.getDesa();
		DesaData desaPerusahaanData = new DesaData();
		desaPerusahaanData.setId(desaPerusahaan.getId());
		desaPerusahaanData.setNama(desaPerusahaan.getNama());
		desaPerusahaanData.setKecamatan(kecamatanPerusahaanData);		
		alamatPerusahaanData.setPropinsi(propinsiPerusahaanData);
		alamatPerusahaanData.setKabupaten(kabupatenPerusahaanData);
		alamatPerusahaanData.setKecamatan(kecamatanPerusahaanData);
		alamatPerusahaanData.setDesa(desaPerusahaanData);
		alamatPerusahaanData.setKeterangan(alamatPerusahaan.getKeterangan());
		
		
		ModelPerizinan modelPerizinan = perusahaan.getModelPerizinan();
		ModelPerizinanData modelPerizinanData = new ModelPerizinanData();
		modelPerizinanData.setId(modelPerizinan.getId());
		modelPerizinanData.setNama(modelPerizinan.getNama());
		modelPerizinanData.setSingkatan(modelPerizinan.getSingkatan());
		
		SkalaUsaha skalaUsaha = perusahaan.getSkalaUsaha();
		SkalaUsahaData skalaUsahaData = new SkalaUsahaData();
		skalaUsahaData.setId(skalaUsaha.getId());
		skalaUsahaData.setNama(skalaUsaha.getNama());
		skalaUsahaData.setSingkatan(skalaUsaha.getSingkatan());
		
		PelakuUsaha pelakuUsaha = perusahaan.getPelakuUsaha();
		KategoriPelakuUsaha kategoriPelakuUsaha = pelakuUsaha.getKategoriPelakuUsaha();
		KategoriPelakuUsahaData kategoriPelakuUsahaData = new KategoriPelakuUsahaData();
		kategoriPelakuUsahaData.setId(kategoriPelakuUsaha.getId());
		kategoriPelakuUsahaData.setNama(kategoriPelakuUsaha.getNama());
		
		PelakuUsahaData pelakuUsahaData = new PelakuUsahaData();
		pelakuUsahaData.setId(pelakuUsaha.getId());
		pelakuUsahaData.setNama(pelakuUsaha.getNama());
		pelakuUsahaData.setSingkatan(pelakuUsaha.getSingkatan());
		pelakuUsahaData.setKategoriPelakuUsahaData(kategoriPelakuUsahaData);

		PerusahaanData perusahaanData = new PerusahaanData();
		perusahaanData.setId(perusahaan.getId());
		perusahaanData.setNama(perusahaan.getNama());
		perusahaanData.setAlamatPerusahaanData(alamatPerusahaanData);
		perusahaanData.setModelPerizinanData(modelPerizinanData);
		perusahaanData.setSkalaUsaha(skalaUsahaData);
		perusahaanData.setPelakuUsahaData(pelakuUsahaData);		
		
		Dokumen dokumen = t.getDokumen();
		DokumenData dokumenData = new DokumenData();
		KategoriDokumen kategoriDokumen = dokumen.getKategoriDokumen();
		KategoriDokumenData kategoriDokumenData = new KategoriDokumenData();
		kategoriDokumenData.setId(kategoriDokumen.getId());
		kategoriDokumenData.setNama(kategoriDokumen.getNama());
		dokumenData.setId(dokumen.getId());
		dokumenData.setNama(dokumen.getNama());
		dokumenData.setKategori(kategoriDokumenData);
		
		Jsonb jsonb = JsonbBuilder.create();
		List<ItemAttributeDokumen<Object>> attributeDokumen = t.getAttribute();
		String attributeDokumenData = jsonb.toJson(attributeDokumen);
		
		TransaksiDokumenData transaksiDokumenData = new TransaksiDokumenData();
		transaksiDokumenData.setId(t.getIdTransaksi());
		transaksiDokumenData.setPerusahaan(perusahaanData);
		transaksiDokumenData.setDokumen(dokumenData);
		transaksiDokumenData.setTanggalUpload(t.getTanggalTransaksi());
		transaksiDokumenData.setAttribute(attributeDokumenData);		
		
		return transaksiDokumenData;
	}

	
}
