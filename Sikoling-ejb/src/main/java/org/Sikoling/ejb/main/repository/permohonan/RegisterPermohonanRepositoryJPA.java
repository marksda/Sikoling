package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.StatusWali;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonan;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPermohonanRepository;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanData;

import jakarta.persistence.EntityManager;

public class RegisterPermohonanRepositoryJPA implements IRegisterPermohonanRepository {
	
	private final EntityManager entityManager;	

	public RegisterPermohonanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	

	@Override
	public List<RegisterPermohonan> getAll() {
		return entityManager.createNamedQuery("RegisterPermohonanData.findAll", RegisterPermohonanData.class)
				.getResultList()
				.stream()
				.map(d -> convertRegisterPermohonanDataToRegisterPermohonan(d))
				.collect(Collectors.toList());
	}

	@Override
	public RegisterPermohonan save(RegisterPermohonan t) {
		RegisterPermohonanData registerPermohonanData = convertRegisterPermohonanToRegisterPermohonanData(t);
		entityManager.persist(registerPermohonanData);
		entityManager.flush();
		
		return convertRegisterPermohonanDataToRegisterPermohonan(
				entityManager.find(RegisterPermohonanData.class, registerPermohonanData.getId())
				);
	}

	@Override
	public RegisterPermohonan update(RegisterPermohonan t) {
		RegisterPermohonanData registerPermohonanData = convertRegisterPermohonanToRegisterPermohonanData(t);
		registerPermohonanData = entityManager.merge(registerPermohonanData);
		return null;
	}

	@Override
	public List<RegisterPermohonan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterPermohonanData.findAll", RegisterPermohonanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPermohonanDataToRegisterPermohonan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPermohonan> getByIdPengakses(String idPengakses) {
		return entityManager.createNamedQuery("RegisterPermohonanData.findByPengakses", RegisterPermohonanData.class)
				.setParameter("idPengakses", idPengakses)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPermohonanDataToRegisterPermohonan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPermohonan> getByIdPerusahaan(String idPerusahaan) {
		return entityManager.createNamedQuery("RegisterPermohonanData.findByPerusahaan", RegisterPermohonanData.class)
				.setParameter("idRegisterPerusahaan", idPerusahaan)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPermohonanDataToRegisterPermohonan(t))
				.collect(Collectors.toList());
	}

	private RegisterPermohonan convertRegisterPermohonanDataToRegisterPermohonan(RegisterPermohonanData d) {
		KategoriPermohonanData kategoriPermohonanData = d.getKategoriPermohonanData();
		KategoriPermohonan kategoriPermohonan = kategoriPermohonanData != null ?
				new KategoriPermohonan(
						kategoriPermohonanData.getId(), 
						kategoriPermohonanData.getNama()
						) : null;
				
		RegisterPerusahaanData registerPerusahaanData = d.getPerusahaanData();
		RegisterPerusahaan registerPerusahaan = null;
		
		
		if(registerPerusahaanData != null) {
			PelakuUsahaData pelakuUsahaData = registerPerusahaanData.getPelakuUsahaData();		
			PelakuUsaha pelakuUsaha = pelakuUsahaData != null ? 
					new PelakuUsaha(	
							pelakuUsahaData.getId(), 
							pelakuUsahaData.getNama(), 
							pelakuUsahaData.getSingkatan(), 
							null						
							) : null;
			Perusahaan perusahaan = new Perusahaan(
					registerPerusahaanData.getNpwp(), 
					registerPerusahaanData.getNama(), 
					null, 
					null, 
					pelakuUsaha, 
					null, 
					null, 
					null, 
					null
					);
			
			registerPerusahaan =  new RegisterPerusahaan(
					registerPerusahaanData.getId(),
					registerPerusahaanData.getTanggalRegistrasi(), 
					null, 
					null, 
					perusahaan
					);
		}
		
		AutorisasiData autorisasiData = d.getAutorisasiData();
		Authority pengurusPermohonan = autorisasiData != null ?
				new Authority(
						autorisasiData.getId(), 
						null, 
						null, 
						null, 
						null, 
						autorisasiData.getUserName()
						) : null;
		
		KategoriPengurusPermohonanData statusPengurus = d.getKategoriPengurusPermohonanData();
		StatusWali statusWali = statusPengurus != null ?
				new StatusWali(statusPengurus.getId(), statusPengurus.getNama()) : null;
		PosisiTahapPemberkasanData posisiTahapPemberkasanData = d.getPosisiTahapPemberkasanData();
		PosisiTahapPemberkasan statusTahapPemberkasan = posisiTahapPemberkasanData != null ?
				new PosisiTahapPemberkasan(
						posisiTahapPemberkasanData.getId(), 
						posisiTahapPemberkasanData.getNama(), 
						posisiTahapPemberkasanData.getKeterangan()
						) : null;
		
		return new RegisterPermohonan(
				d.getId(), 
				kategoriPermohonan, 
				d.getTanggalRegistrasi(), 
				registerPerusahaan, 
				pengurusPermohonan, 
				statusWali, 
				statusTahapPemberkasan, 
				null, 
				null
				);
	}
	
	private RegisterPermohonanData convertRegisterPermohonanToRegisterPermohonanData(RegisterPermohonan t) {
		RegisterPermohonanData registerPermohonanData = new RegisterPermohonanData();
		registerPermohonanData.setId(t.getId());
		
		KategoriPermohonanData kategoriPermohonanData = new KategoriPermohonanData();
		kategoriPermohonanData.setId(t.getKategoriPermohonan().getId());
		registerPermohonanData.setKategoriPermohonanData(kategoriPermohonanData);
		
		registerPermohonanData.setTanggalRegistrasi(t.getTanggalRegistrasi());
		
		RegisterPerusahaanData registerPerusahaanData = new RegisterPerusahaanData();
		registerPerusahaanData.setId(t.getPerusahaan().getId());
		registerPermohonanData.setPerusahaanData(registerPerusahaanData);
		
		AutorisasiData pengaksesData = new AutorisasiData();
		pengaksesData.setId(t.getPengurusPermohonan().getId());
		registerPermohonanData.setAutorisasiData(pengaksesData);
		
		KategoriPengurusPermohonanData kategoriPengurusPermohonanData = new KategoriPengurusPermohonanData();		
		kategoriPengurusPermohonanData.setId(t.getStatusWaliPengurusPermohonan().getId());
		registerPermohonanData.setKategoriPengurusPermohonanData(kategoriPengurusPermohonanData);
		
		PosisiTahapPemberkasanData posisiTahapPemberkasanData = new PosisiTahapPemberkasanData();
		posisiTahapPemberkasanData.setId(t.getPosisiBerkas().getId());
		registerPermohonanData.setPosisiTahapPemberkasanData(posisiTahapPemberkasanData);		
		
		return registerPermohonanData;
	}

	
	@Override
	public DeleteResponse delete(String id) {
		RegisterPermohonanData registerPermohonanData = entityManager.find(RegisterPermohonanData.class, id);
		entityManager.remove(registerPermohonanData);			
		return new DeleteResponse(true, id);
	}
}
