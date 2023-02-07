package org.Sikoling.ejb.main.repository.perusahaan;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import org.Sikoling.ejb.main.repository.alamat.AlamatData;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kategoripelakuusaha.KategoriPelakuUsahaData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.kontak.KontakData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class RegisterPerusahaanRepositoryJPA implements IRegisterPerusahaanRepository {
	
	private final EntityManager entityManager;	
	private final DataConverter dataConverter;

	public RegisterPerusahaanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}

	@Override
	public List<RegisterPerusahaan> getAll() {
		return entityManager.createNamedQuery("RegisterPerusahaanData.findAll", RegisterPerusahaanData.class)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(t))
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
			
			return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(
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
		return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanData);
	}
		
	@Override
	public List<RegisterPerusahaan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterPerusahaanData.findAll", RegisterPerusahaanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPerusahaan> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("RegisterPerusahaanData.findByQueryNama", RegisterPerusahaanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(t))
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
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(t))
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
			return dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanData);
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	@Override
	public List<RegisterPerusahaan> getByIdKreator(String idKreator) {
		return entityManager.createNamedQuery("RegisterPerusahaanData.findByIdKreator", AutorityPerusahaanData.class)
				.setParameter("idKreator", idKreator)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(t.getPerusahaan()))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPerusahaan> getByIdLinkKepemilikan(String idAutorisasi) {
		return entityManager.createNamedQuery("AutorityPerusahaanData.findByPemilik", AutorityPerusahaanData.class)
				.setParameter("idAutorisasi", idAutorisasi)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaan(t.getPerusahaan()))
				.collect(Collectors.toList());
	}	
	
	@Override
	public List<RegisterPerusahaan> getByIdLinkKepemilikanTanpaRegisterDokumen(String idAutorisasi) {
		return entityManager.createNamedQuery("AutorityPerusahaanData.findByPemilik", AutorityPerusahaanData.class)
				.setParameter("idAutorisasi", idAutorisasi)
				.getResultList()
				.stream()
				.map(t -> dataConverter.convertRegisterPerusahaanDataToRegisterPerusahaanWithOutRegisterDokumen(t.getPerusahaan()))
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
	
	private RegisterPerusahaanData convertRegisterPerusahaanToRegisterPerusahaanData(RegisterPerusahaan t) {	
		
		RegisterPerusahaanData registerPerusahaanData = new RegisterPerusahaanData();	
		registerPerusahaanData.setId(
				t.getId() != null ? t.getId() : getGenerateIdRegisterPerusahaan()
				);
		
		Perusahaan perusahaan = t.getPerusahaan();
		registerPerusahaanData.setNama(perusahaan.getNama());		
				
		AlamatData alamatPerusahaanData = new AlamatData();
		alamatPerusahaanData.setDetailAlamat(perusahaan.getAlamat().getKeterangan());
		DesaData desaData = new DesaData();
		desaData.setId(perusahaan.getAlamat().getDesa().getId());
		alamatPerusahaanData.setDesa(desaData);
		KecamatanData kecamatanData = new KecamatanData();
		kecamatanData.setId(perusahaan.getAlamat().getKecamatan().getId());
		alamatPerusahaanData.setKecamatan(kecamatanData);
		KabupatenData kabupatenData = new KabupatenData();
		kabupatenData.setId(perusahaan.getAlamat().getKabupaten().getId());
		alamatPerusahaanData.setKabupaten(kabupatenData);
		PropinsiData propinsiData = new PropinsiData();
		propinsiData.setId(perusahaan.getAlamat().getPropinsi().getId());
		alamatPerusahaanData.setPropinsi(propinsiData);
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
		pelakuUsahaData.setKategoriPelakuUsaha(kategoriPelakuUsahaData);
		registerPerusahaanData.setPelakuUsaha(pelakuUsahaData);
		
		KontakData kontakPerusahaanData = new KontakData();
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
		
}

	