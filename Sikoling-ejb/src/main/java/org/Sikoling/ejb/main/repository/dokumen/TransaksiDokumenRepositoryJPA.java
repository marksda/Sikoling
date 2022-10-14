package org.Sikoling.ejb.main.repository.dokumen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Autorisasi;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.entity.ItemAttributeDokumen;
import org.Sikoling.ejb.abstraction.entity.ItemTransaksiDokumen;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.ITransaksiDokumenRepository;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.hakakses.HakAksesData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.pelakuusaha.KategoriPelakuUsahaData;
import org.Sikoling.ejb.main.repository.person.AlamatPersonData;
import org.Sikoling.ejb.main.repository.person.KontakPersonData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.perusahaan.AlamatPerusahaanData;
import org.Sikoling.ejb.main.repository.perusahaan.KontakData;
import org.Sikoling.ejb.main.repository.perusahaan.PerusahaanData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;

public class TransaksiDokumenRepositoryJPA implements ITransaksiDokumenRepository<ItemTransaksiDokumen> {

	private final EntityManager entityManager;	
	
	public TransaksiDokumenRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<ItemTransaksiDokumen> getAll() {
		return entityManager.createNamedQuery("TransaksiDokumenData.findAll", TransaksiDokumenData.class)
				.getResultList()
				.stream()
				.map(d -> convertTransaksiDokumenDataToTransaksiDokumen(d))
				.collect(Collectors.toList());
	}

	@Override
	public ItemTransaksiDokumen save(ItemTransaksiDokumen t) {
		TransaksiDokumenData dokumenPerusahaanData = convertDokumenPerusahaanToDokumenPerusahaanData(t);
		entityManager.persist(dokumenPerusahaanData);
		entityManager.flush();
		
		return convertTransaksiDokumenDataToTransaksiDokumen(dokumenPerusahaanData);
	}

	@Override
	public ItemTransaksiDokumen update(ItemTransaksiDokumen t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemTransaksiDokumen> getAllByPage(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemTransaksiDokumen> getByNama(String nama) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemTransaksiDokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	private ItemTransaksiDokumen convertTransaksiDokumenDataToTransaksiDokumen(TransaksiDokumenData d) {
		KategoriDokumen kategoriDokumen = 
				new KategoriDokumen(d.getDokumen().getKategori().getId(), d.getDokumen().getKategori().getNama());
		
		Dokumen dokumen = new Dokumen(d.getDokumen().getId(), kategoriDokumen, d.getDokumen().getNama());
		
		Jsonb jsonb = JsonbBuilder.create();
		@SuppressWarnings("serial")
		List<ItemAttributeDokumen<Object>> attribute = jsonb
				.fromJson(d.getAttribute(), new ArrayList<ItemAttributeDokumen<Object>>(){}.getClass().getGenericSuperclass());
		
		AutorisasiData autorisasiData = d.getUploader();
		PersonData personData  = autorisasiData.getPersonData();
		JenisKelaminData jenisKelaminData = personData.getSex();
		AlamatPersonData alamatPersonData = personData.getAlamat();
		Propinsi propinsi = new Propinsi(
				alamatPersonData.getPropinsi().getId(), 
				alamatPersonData.getPropinsi().getNama());
		Kabupaten kabupaten = new Kabupaten(
				alamatPersonData.getKabupaten().getId(), 
				alamatPersonData.getKabupaten().getNama());
		Kecamatan kecamatan = new Kecamatan(
				alamatPersonData.getKecamatan().getId(), 
				alamatPersonData.getKecamatan().getNama());
		Desa desa = new Desa(
				alamatPersonData.getDesa().getId(), 
				alamatPersonData.getDesa().getNama());
		Alamat alamat = new Alamat(propinsi, kabupaten, kecamatan, desa, alamatPersonData.getDetailAlamat());
				
		KontakPersonData kontakPersonData = personData.getKontak();
		Kontak kontak = new Kontak(kontakPersonData.getTelepone(), null, kontakPersonData.getEmail());
		
		Person person = new Person(
				personData.getId(), personData.getNama(), 
				new JenisKelamin(jenisKelaminData.getId(), jenisKelaminData.getNama()), 
				alamat, personData.getScanKtp(), kontak);
		
		HakAksesData hakAksesData = autorisasiData.getHakAkses();
		HakAkses hakAkses = new HakAkses(
				hakAksesData.getId(), 
				hakAksesData.getNama(), 
				hakAksesData.getKeterangan());
		
		Autorisasi autorisasi = new Autorisasi(
				autorisasiData.getId(), 
				person, 
				autorisasiData.getIdLama(), 
				hakAkses, 
				autorisasiData.getStatusInternal(), 
				autorisasiData.getIsVerified(), 
				autorisasiData.getUserName());
		
		PerusahaanData perusahaanData = d.getPerusahaan();
		ModelPerizinanData modelPerizinanData = perusahaanData.getModelPerizinanData();
		ModelPerizinan modelPerizinan = new ModelPerizinan(modelPerizinanData.getId(), 
				modelPerizinanData.getNama(), modelPerizinanData.getSingkatan());
		KategoriPelakuUsahaData jenisPelakuUsahaData = perusahaanData.getKategoriPelakuUsahaData();
		KategoriPelakuUsaha jenisPelakuUsaha = new KategoriPelakuUsaha(jenisPelakuUsahaData.getId(), 
				jenisPelakuUsahaData.getNama());
		SkalaUsahaData skalaUsahaData = perusahaanData.getSkalaUsaha();
		SkalaUsaha skalaUsaha = new SkalaUsaha(skalaUsahaData.getId(), 
				skalaUsahaData.getNama(), skalaUsahaData.getSingkatan());
		PelakuUsahaData detailPelakuUsahaData = perusahaanData.getPelakuUsahaData();
		PelakuUsaha detailPelakuUsaha = new PelakuUsaha(
				detailPelakuUsahaData.getId(), detailPelakuUsahaData.getNama(), 
				detailPelakuUsahaData.getSingkatan(), jenisPelakuUsaha);
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
				
		
		
		Perusahaan perusahaan = new Perusahaan(
				perusahaanData.getId(), 
				perusahaanData.getNama(), 
				modelPerizinan, 
				skalaUsaha,
				jenisPelakuUsaha, 
				detailPelakuUsaha, 
				alamatPerusahaan, 
				null, null);
		
		return new ItemTransaksiDokumen(
				d.getId(), 
				dokumen, 
				attribute, 
				d.getTanggalUpload(), 
				d.getIsBerlaku(), 
				autorisasi, 
				perusahaan);
	}
	
	private TransaksiDokumenData convertDokumenPerusahaanToDokumenPerusahaanData(ItemTransaksiDokumen t) {
//		TransaksiDokumenData dokumenPerusahaanData = new TransaksiDokumenData();
//		dokumenPerusahaanData.setId("test");
//		
//		PerusahaanData perusahaanData = new PerusahaanData();
//		perusahaanData.setId(t.getPerusahaan().getId());
//		dokumenPerusahaanData.setPerusahaan(perusahaanData);
//		
//		DokumenData detailDokumenPerusahaanData = new DokumenData();
//		detailDokumenPerusahaanData.setId(t.getNamaDokumen().getId());		
//		dokumenPerusahaanData.setDetailDokumen(detailDokumenPerusahaanData);
//		
//		dokumenPerusahaanData.setTanggalUpload(t.getTanggalUpload());
//		dokumenPerusahaanData.setBerlaku(t.isBerlaku());
//		
//		Jsonb jsonb = JsonbBuilder.create();		 
//		String result = jsonb.toJson(t.getAttribute());
//		dokumenPerusahaanData.setAttribute(result);
//		
//		dokumenPerusahaanData.setLokasiFile(t.getLokasiFile());
//		
//		return dokumenPerusahaanData;
		return null;
	}

	
}
