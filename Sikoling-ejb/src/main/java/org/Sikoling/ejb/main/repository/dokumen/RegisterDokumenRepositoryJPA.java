package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.IRegisterDokumenRepository;
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

import jakarta.persistence.EntityManager;

public class RegisterDokumenRepositoryJPA implements IRegisterDokumenRepository {

	private final EntityManager entityManager;	
	
	public RegisterDokumenRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<RegisterDokumen> getAll() {
		return entityManager.createNamedQuery("RegisterDokumenData.findAll", RegisterDokumenData.class)
				.getResultList()
				.stream()
				.map(d -> convertTransaksiDokumenDataToTransaksiDokumen(d))
				.collect(Collectors.toList());
	}

	@Override
	public RegisterDokumen save(RegisterDokumen t) {
		RegisterDokumenData registerDokumenData = convertTransaksiDokumenToTransaksiDokumenData(t);
		entityManager.persist(registerDokumenData);
		entityManager.flush();
		//save detailnya disini
		return convertTransaksiDokumenDataToTransaksiDokumen(registerDokumenData);
	}

	@Override
	public RegisterDokumen update(RegisterDokumen t) {
		RegisterDokumenData registerDokumenData = convertTransaksiDokumenToTransaksiDokumenData(t);
		registerDokumenData = entityManager.merge(registerDokumenData);
		return convertTransaksiDokumenDataToTransaksiDokumen(registerDokumenData);
	}

	@Override
	public List<RegisterDokumen> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterDokumenData.findAll", RegisterDokumenData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertTransaksiDokumenDataToTransaksiDokumen(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterDokumen> getByNamaPerusahaan(String namaPerusahaan) {
		namaPerusahaan = "%" + namaPerusahaan + "%";
		return entityManager.createNamedQuery("RegisterDokumenData.findByNamaPerusahaan", RegisterDokumenData.class)
				.setParameter("namaPerusahaan", namaPerusahaan)
				.getResultList()
				.stream()
				.map(d -> convertTransaksiDokumenDataToTransaksiDokumen(d))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterDokumen> getByNamaPerusahaanAndPage(String namaPerusahaan, Integer page, Integer pageSize) {
		namaPerusahaan = "%" + namaPerusahaan + "%";
		return entityManager.createNamedQuery("RegisterDokumenData.findByNamaPerusahaan", RegisterDokumenData.class)
				.setParameter("namaPerusahaan", namaPerusahaan)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertTransaksiDokumenDataToTransaksiDokumen(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<RegisterDokumen> getByIdPerusahaan(String idPerusahaan) {
		return entityManager.createNamedQuery("RegisterDokumenData.findByIdPerusahaan", RegisterDokumenData.class)
				.setParameter("idPerusahaan", idPerusahaan)
				.getResultList()
				.stream()
				.map(d -> convertTransaksiDokumenDataToTransaksiDokumen(d))
				.collect(Collectors.toList());
	}

	
	@Override
	public List<RegisterDokumen> getByIdPerusahaanAndPage(String idPerusahaan, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterDokumenData.findByIdPerusahaan", RegisterDokumenData.class)
				.setParameter("idPerusahaan", idPerusahaan)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertTransaksiDokumenDataToTransaksiDokumen(t))
				.collect(Collectors.toList());
	}

	
	@Override
	public List<RegisterDokumen> getByNamaDokumen(String namaDokumen) {
		namaDokumen = "%" + namaDokumen + "%";
		return entityManager.createNamedQuery("RegisterDokumenData.findByNamaDokumen", RegisterDokumenData.class)
				.setParameter("namaDokumen", namaDokumen)
				.getResultList()
				.stream()
				.map(d -> convertTransaksiDokumenDataToTransaksiDokumen(d))
				.collect(Collectors.toList());
	}

	
	@Override
	public List<RegisterDokumen> getByNamaDokumenAndPage(String namaDokumen, Integer page, Integer pageSize) {
		namaDokumen = "%" + namaDokumen + "%";
		return entityManager.createNamedQuery("RegisterDokumenData.findByNamaDokumen", RegisterDokumenData.class)
				.setParameter("namaDokumen", namaDokumen)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertTransaksiDokumenDataToTransaksiDokumen(d))
				.collect(Collectors.toList());
	}

	
	@Override
	public List<RegisterDokumen> getByIdDokumen(String idDokumen) {
		return entityManager.createNamedQuery("RegisterDokumenData.findByIdDocument", RegisterDokumenData.class)
				.setParameter("idDokumen", idDokumen)
				.getResultList()
				.stream()
				.map(d -> convertTransaksiDokumenDataToTransaksiDokumen(d))
				.collect(Collectors.toList());
	}

	
	@Override
	public List<RegisterDokumen> getByIdDokumenAndPage(String idDokumen, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterDokumenData.findByIdDocument", RegisterDokumenData.class)
				.setParameter("idDokumen", idDokumen)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertTransaksiDokumenDataToTransaksiDokumen(d))
				.collect(Collectors.toList());
	}

	private Dokumen convertDokumenDataToDokumen(DokumenData d) {		
		return new Dokumen(
				d.getId(), 
				d.getNama(),
				new KategoriDokumen(d.getKategori().getId(), d.getKategori().getNama(), d.getKategori().getParent())
				);
	}
	
	private Perusahaan convertPerusahaanDataToPerusahaan(PerusahaanData d) {
		ModelPerizinanData modelPerizinanData = d.getModelPerizinanData();
		SkalaUsahaData skalaUsahaData = d.getSkalaUsaha();
		PelakuUsahaData pelakuUsahaData = d.getPelakuUsahaData();
		KategoriPelakuUsahaData kategoriPelakuUsahaData = pelakuUsahaData.getKategoriPelakuUsahaData();
		AlamatPerusahaanData alamatPerusahaanData = d.getAlamatPerusahaanData();
		PropinsiData propinsiDataPerusahaan = alamatPerusahaanData.getPropinsi();
		KabupatenData kabupatenDataPerusahaan = alamatPerusahaanData.getKabupaten();
		KecamatanData kecamatanDataPerusahaan = alamatPerusahaanData.getKecamatan();
		DesaData desaDataPerusahaan = alamatPerusahaanData.getDesa();
		KontakPerusahaanData kontakData = d.getKontakPerusahaanData();
		
		return new Perusahaan(
				d.getId(), 
				d.getNama(), 
				new ModelPerizinan(
						modelPerizinanData.getId(), 
						modelPerizinanData.getNama(), 
						modelPerizinanData.getSingkatan()
						), 
				new SkalaUsaha(
						skalaUsahaData.getId(), 
						skalaUsahaData.getNama(), 
						skalaUsahaData.getSingkatan()
						), 
				new PelakuUsaha(
						pelakuUsahaData.getId(), 
						pelakuUsahaData.getNama(), 
						pelakuUsahaData.getSingkatan(), 
						new KategoriPelakuUsaha(kategoriPelakuUsahaData.getId(), kategoriPelakuUsahaData.getNama())
						), 
				new Alamat(
						new Propinsi(
								propinsiDataPerusahaan.getId(), 
								propinsiDataPerusahaan.getNama()
								), 
						new Kabupaten(
								kabupatenDataPerusahaan.getId(), 
								kabupatenDataPerusahaan.getNama()
								), 
						new Kecamatan(
								kecamatanDataPerusahaan.getId(), 
								kecamatanDataPerusahaan.getNama()
								), 
						new Desa(
								desaDataPerusahaan.getId(), 
								desaDataPerusahaan.getNama()
								), 
						alamatPerusahaanData.getKeterangan()), 
				new Kontak(
						kontakData.getTelepone(), 
						kontakData.getFax(), 
						kontakData.getEmail()
						)
				);
	}
	
	private RegisterDokumen convertTransaksiDokumenDataToTransaksiDokumen(RegisterDokumenData d) {
		RegisterDokumenData registerDokumenData = entityManager.find(RegisterDokumenData.class, d.getId());
		
		return new RegisterDokumen(
				registerDokumenData.getId(), 
				convertDokumenDataToDokumen(registerDokumenData.getDokumen()), 
				registerDokumenData.getTanggalUpload(), 
				registerDokumenData.getIsBerlaku(), 
				convertPerusahaanDataToPerusahaan(registerDokumenData.getPerusahaan()), null);

	}
	
	private RegisterDokumenData convertTransaksiDokumenToTransaksiDokumenData(RegisterDokumen t) {	
		
		RegisterDokumenData registerDokumenData = new RegisterDokumenData();
		
		//setId
		registerDokumenData.setId(t.getId());
		
		//setPerusahaan		
		PerusahaanData perusahaanData = new PerusahaanData();
		perusahaanData.setId(t.getPerusahaan().getId());
		registerDokumenData.setPerusahaan(perusahaanData);
		
		//setDokumen
		DokumenData dokumenData = new DokumenData();
		dokumenData.setId(t.getDokumen().getId());
		registerDokumenData.setDokumen(dokumenData);
		
		//setTanggalUpload
		registerDokumenData.setTanggalUpload(t.getTanggalTransaksi());
		
		//setIsBerlaku
		registerDokumenData.setIsBerlaku(true);
		
		return registerDokumenData;
	}
	
}
