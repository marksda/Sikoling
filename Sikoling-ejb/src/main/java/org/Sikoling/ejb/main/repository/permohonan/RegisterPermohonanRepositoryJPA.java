package org.Sikoling.ejb.main.repository.permohonan;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.KategoriPermohonan;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.RegisterPermohonan;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.StatusTahapPemberkasan;
import org.Sikoling.ejb.abstraction.entity.StatusWali;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegisterPermohonan> getAllByPage(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegisterPermohonan> getByQueryNama(String nama) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegisterPermohonan> getByQueryNamaAndPage(String nama, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegisterPermohonan> getByIdPengakses(String idPengakses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegisterPermohonan> getByIdPerusahaan(String idPerusahaan) {
		// TODO Auto-generated method stub
		return null;
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
		StatusTahapPemberkasanData statusTahapPemberkasanData = d.getStatusTahapPemberkasanData();
		StatusTahapPemberkasan statusTahapPemberkasan = statusTahapPemberkasanData != null ?
				new StatusTahapPemberkasan(
						statusTahapPemberkasanData.getId(), 
						statusTahapPemberkasanData.getNama(), 
						statusTahapPemberkasanData.getKeterangan()
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
		
		return registerPermohonanData;
	}
}
