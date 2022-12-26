package org.Sikoling.ejb.main.repository.perusahaan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.entity.dokumen.SuratArahan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.dokumen.MasterDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.RegisterDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.SuratArahanData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kategoripelakuusaha.KategoriPelakuUsahaData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class RegisterPerusahaanRepositoryJPA implements IRegisterPerusahaanRepository {
	
	private final EntityManager entityManager;	

	public RegisterPerusahaanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<RegisterPerusahaan> getAll() {
		return entityManager.createNamedQuery("RegisterPerusahaanData.findAll", RegisterPerusahaanData.class)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPerusahaanDataToRegisterPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public RegisterPerusahaan save(RegisterPerusahaan t) {	
			RegisterPerusahaanData registerPerusahaanData = convertRegisterPerusahaanToRegisterPerusahaanData(t);
			AutorityPerusahaanData autorityPerusahaanData = new AutorityPerusahaanData();
			autorityPerusahaanData.setAutority(registerPerusahaanData.getKreator());
			autorityPerusahaanData.setPerusahaan(registerPerusahaanData);			
			entityManager.persist(registerPerusahaanData);
			entityManager.persist(autorityPerusahaanData);
			entityManager.flush();
			
			return convertRegisterPerusahaanDataToRegisterPerusahaan(
					entityManager.find(
							RegisterPerusahaanData.class, registerPerusahaanData.getId()
							)
					);				
	}
	
	@Override
	public DeleteResponse delete(String id) {
		RegisterPerusahaanData registerPerusahaanData = entityManager.find(RegisterPerusahaanData.class, id);
		entityManager.remove(registerPerusahaanData);			
		return new DeleteResponse(true, id);
	}
	
	@Override
	public DeleteResponse deleteLinkKepemilikanPerusahaan(String idAutority, String idRegisterPerusahaan) {
		AutorityPerusahaanDataId id = new AutorityPerusahaanDataId();
		id.setAutority(idAutority);
		id.setPerusahaan(idRegisterPerusahaan);
		
		AutorityPerusahaanData autorityPerusahaanData = entityManager.find(AutorityPerusahaanData.class, id);
		entityManager.remove(autorityPerusahaanData);			
		return new DeleteResponse(true, idRegisterPerusahaan);
	}
	
	@Override
	public RegisterPerusahaan update(RegisterPerusahaan t) {
		RegisterPerusahaanData registerPerusahaanData = convertRegisterPerusahaanToRegisterPerusahaanData(t);		
		registerPerusahaanData = entityManager.merge(registerPerusahaanData);
		return convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanData);
	}
		
	@Override
	public List<RegisterPerusahaan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterPerusahaanData.findAll", RegisterPerusahaanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPerusahaanDataToRegisterPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPerusahaan> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("RegisterPerusahaanData.findByQueryNama", RegisterPerusahaanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPerusahaanDataToRegisterPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPerusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("RegisterPerusahaanData.findByQueryNama", RegisterPerusahaanData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPerusahaanDataToRegisterPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean cekById(String id) {
		RegisterPerusahaanData perusahaanData = entityManager.find(RegisterPerusahaanData.class, id);
		if(perusahaanData == null) {
			return false;
		}
		else {
			return true;
		}		 
	}
	
	@Override
	public RegisterPerusahaan getByNpwp(String npwp) {		
		try {
			RegisterPerusahaanData registerPerusahaanData = entityManager.createNamedQuery("RegisterPerusahaanData.findByNpwp", RegisterPerusahaanData.class)
					.setParameter("npwp", npwp)
					.getSingleResult();
			return convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanData);
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	private RegisterPerusahaanData convertRegisterPerusahaanToRegisterPerusahaanData(RegisterPerusahaan t) {	
		
		RegisterPerusahaanData registerPerusahaanData = new RegisterPerusahaanData();	
		registerPerusahaanData.setId(
				t.getId() != null ? t.getId() : getGenerateIdRegisterPerusahaan()
				);
		
		Perusahaan perusahaan = t.getPerusahaan();
		registerPerusahaanData.setNama(perusahaan.getNama());		
				
		AlamatPerusahaanData alamatPerusahaanData = new AlamatPerusahaanData();
		alamatPerusahaanData.setKeterangan(perusahaan.getAlamat().getKeterangan());
		DesaData desaData = new DesaData();
		desaData.setId(perusahaan.getAlamat().getDesa().getId());
		alamatPerusahaanData.setDesaData(desaData);
		KecamatanData kecamatanData = new KecamatanData();
		kecamatanData.setId(perusahaan.getAlamat().getKecamatan().getId());
		alamatPerusahaanData.setKecamatanData(kecamatanData);
		KabupatenData kabupatenData = new KabupatenData();
		kabupatenData.setId(perusahaan.getAlamat().getKabupaten().getId());
		alamatPerusahaanData.setKabupatenData(kabupatenData);
		PropinsiData propinsiData = new PropinsiData();
		propinsiData.setId(perusahaan.getAlamat().getPropinsi().getId());
		alamatPerusahaanData.setPropinsiData(propinsiData);
		registerPerusahaanData.setAlamatPerusahaanData(alamatPerusahaanData);
		
		ModelPerizinanData modelPerizinanData = new ModelPerizinanData();
		modelPerizinanData.setId(perusahaan.getModelPerizinan().getId());
		registerPerusahaanData.setModelPerizinanData(modelPerizinanData);
		
		SkalaUsahaData skalaUsahaData = new SkalaUsahaData();
		skalaUsahaData.setId(perusahaan.getSkalaUsaha().getId());
		registerPerusahaanData.setSkalaUsahaData(skalaUsahaData);
			
		PelakuUsaha pelakuUsaha = perusahaan.getPelakuUsaha();
		KategoriPelakuUsaha kategoriPelakuUsaha = pelakuUsaha.getKategoriPelakuUsaha();
		PelakuUsahaData pelakuUsahaData = new PelakuUsahaData();
		pelakuUsahaData.setId(pelakuUsaha.getId());
		KategoriPelakuUsahaData kategoriPelakuUsahaData = new KategoriPelakuUsahaData();
		kategoriPelakuUsahaData.setId(kategoriPelakuUsaha.getId());
		pelakuUsahaData.setKategoriPelakuUsahaData(kategoriPelakuUsahaData);
		registerPerusahaanData.setPelakuUsahaData(pelakuUsahaData);
		
		KontakPerusahaanData kontakPerusahaanData = new KontakPerusahaanData();
		kontakPerusahaanData.setEmail(perusahaan.getKontak().getEmail());
		kontakPerusahaanData.setFax(perusahaan.getKontak().getFax());
		kontakPerusahaanData.setTelepone(perusahaan.getKontak().getTelepone());	
		registerPerusahaanData.setKontakPerusahaanData(kontakPerusahaanData);		
		
		Authority authorityKreator = t.getKreator();
		AutorisasiData autorisasiDataKreator = new AutorisasiData();
		autorisasiDataKreator.setId(authorityKreator.getId());
		registerPerusahaanData.setKreator(autorisasiDataKreator);
		
		registerPerusahaanData.setTanggalRegistrasi(t.getTanggalRegistrasi());
		registerPerusahaanData.setNpwp(perusahaan.getId());

		registerPerusahaanData.setStatusVerifikasi(perusahaan.isStatusVerifikasi());
		
		return registerPerusahaanData;
	}
	
	private RegisterPerusahaan convertRegisterPerusahaanDataToRegisterPerusahaan(RegisterPerusahaanData d) {
		
		ModelPerizinan modelPerizinan = d.getModelPerizinanData() != null ? new ModelPerizinan(
				d.getModelPerizinanData().getId(), d.getModelPerizinanData().getNama(), 
				d.getModelPerizinanData().getSingkatan()) : null;	
		
		SkalaUsaha skalaUsaha = d.getSkalaUsahaData() != null ? new SkalaUsaha(
				d.getSkalaUsahaData().getId(), d.getSkalaUsahaData().getNama(), 
				d.getSkalaUsahaData().getSingkatan()) : null;
		
		KategoriPelakuUsaha kategoriPelakuUsaha = d.getPelakuUsahaData() != null ? new KategoriPelakuUsaha(
				d.getPelakuUsahaData().getKategoriPelakuUsahaData().getId(), 
				d.getPelakuUsahaData().getKategoriPelakuUsahaData().getNama()) : null;
		
		PelakuUsaha pelakuUsaha = d.getPelakuUsahaData() != null ? new PelakuUsaha(
				d.getPelakuUsahaData().getId(), d.getPelakuUsahaData().getNama(), d.getPelakuUsahaData().getSingkatan(), kategoriPelakuUsaha) : null;
		
		Alamat alamatPerusahaan = d.getAlamatPerusahaanData() != null ? new Alamat(
				d.getAlamatPerusahaanData().getPropinsiData() != null ? new Propinsi(d.getAlamatPerusahaanData().getPropinsiData().getId(), d.getAlamatPerusahaanData().getPropinsiData().getNama()) : null,
				d.getAlamatPerusahaanData().getKabupatenData() != null ? new Kabupaten(d.getAlamatPerusahaanData().getKabupatenData().getId(), d.getAlamatPerusahaanData().getKabupatenData().getNama()) : null,
				d.getAlamatPerusahaanData().getKecamatanData() != null ? new Kecamatan(d.getAlamatPerusahaanData().getKecamatanData().getId(), d.getAlamatPerusahaanData().getKecamatanData().getNama()) : null,
				d.getAlamatPerusahaanData().getDesaData() != null ? new Desa(d.getAlamatPerusahaanData().getDesaData().getId(), d.getAlamatPerusahaanData().getDesaData().getNama()) : null,
				d.getAlamatPerusahaanData().getKeterangan()) : null;
		
		Kontak kontakPerusahaan = d.getKontakPerusahaanData() != null ? new Kontak(
				d.getKontakPerusahaanData().getTelepone() != null ? d.getKontakPerusahaanData().getTelepone() : null, 
				d.getKontakPerusahaanData().getFax() != null ? d.getKontakPerusahaanData().getFax() : null, 
				d.getKontakPerusahaanData().getEmail() != null ? d.getKontakPerusahaanData().getEmail() : null) : null;
		List<RegisterDokumenData> daftarRegisterDokumenData = d.getDaftarRegisterDokumenData();
		List<RegisterDokumen> daftarRegisterDokumen = new ArrayList<RegisterDokumen>();
		
		if(daftarRegisterDokumenData != null) {
			for(RegisterDokumenData item : daftarRegisterDokumenData) {		
				daftarRegisterDokumen.add(convertRegisterDokumenDataToRegisterDokumen(item));
			}
		}		
		
		AutorisasiData kreatorData = d.getKreator();
		Authority kreator = kreatorData != null ? new Authority(
				kreatorData.getId(),
				null, 
				null, 
				null, 
				null, 
				kreatorData.getUserName()) : null;
		
		AutorisasiData verifikatorData = d.getVerifikator();
		Authority verifikator = verifikatorData != null ? new Authority(
				verifikatorData.getId(), 
				null, 
				null, 
				verifikatorData.getStatusInternal(),
				verifikatorData.getIsVerified(), 
				verifikatorData.getUserName()
				) : null;
		
		
		return new RegisterPerusahaan(
				d.getId(),
				d.getTanggalRegistrasi(), 
				kreator, 
				verifikator, 
				new Perusahaan( 
						d.getNpwp(), 
						d.getNama(), 
						modelPerizinan, 
						skalaUsaha, 
						pelakuUsaha, 
						alamatPerusahaan, 
						kontakPerusahaan, 
						daftarRegisterDokumen, 
						d.getStatusVerifikasi()
						)
				);
	}
	
	@Override
	public List<RegisterPerusahaan> getByIdKreator(String idKreator) {
		return entityManager.createNamedQuery("RegisterPerusahaanData.findByIdKreator", AutorityPerusahaanData.class)
				.setParameter("idKreator", idKreator)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPerusahaanDataToRegisterPerusahaan(t.getPerusahaan()))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPerusahaan> getByIdLinkKepemilikan(String idAutorisasi) {
		return entityManager.createNamedQuery("AutorityPerusahaanData.findByPemilik", AutorityPerusahaanData.class)
				.setParameter("idAutorisasi", idAutorisasi)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPerusahaanDataToRegisterPerusahaan(t.getPerusahaan()))
				.collect(Collectors.toList());
	}
			
	private String getGenerateIdRegisterPerusahaan() {
		int tahun = LocalDate.now().getYear();
		String hasil;
		
		Query q = entityManager.createQuery("SELECT MAX(p.id) "
				+ "FROM RegisterPerusahaanData p "
				+ "WHERE EXTRACT(YEAR FROM p.tanggalRegistrasi) = :tahun");
		
		q.setParameter("tahun", tahun);
		
		try {
			hasil = (String) q.getSingleResult();
			hasil = hasil.substring(0, 4);
			Long idBaru = Long.valueOf(hasil)  + 1;
			hasil = LPad(Long.toString(idBaru), 4, '0');
			return hasil.concat(Integer.toString(tahun));
		} catch (Exception e) {			
			hasil = "0001";			
			return hasil.concat(Integer.toString(tahun));
		}		
	}
	
	private String LPad(String str, Integer length, char car) {
		  return (String.format("%" + length + "s", "").replace(" ", String.valueOf(car)) + str).substring(str.length(), length + str.length());
	}
	
	private RegisterDokumen convertRegisterDokumenDataToRegisterDokumen(RegisterDokumenData d) {
		MasterDokumenData masterDokumenData = d.getDokumenData();
		AutorisasiData uploaderData = d.getUploader();
		 
		if(d.getSuratArahanData() != null) {
			SuratArahanData suratArahanData = d.getSuratArahanData();
			return new RegisterDokumen(
					d.getId(), 
					new SuratArahan(
							masterDokumenData.getId(), 
							masterDokumenData.getNama(), 
							null, 
							suratArahanData.getNoSurat(), 
							suratArahanData.getTanggalSurat(), 
							suratArahanData.getPerihalSurat(), 
							suratArahanData.getUraianKegiatan()
							), 
					null, 
					null, 
					d.getTanggalRegistrasi(), 
					new Authority(
							null, 
							null, 
							null, 
							null, 
							null, 
							uploaderData.getUserName()
							)
					);
		}
		else {
			return null;
		}
	}
}

	