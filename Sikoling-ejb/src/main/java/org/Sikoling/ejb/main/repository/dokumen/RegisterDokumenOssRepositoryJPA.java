package org.Sikoling.ejb.main.repository.dokumen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.String;
import org.Sikoling.ejb.abstraction.entity.DokumenOss;
import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.Kbli;
import org.Sikoling.ejb.abstraction.repository.IDokumenOssRepository;

import jakarta.persistence.EntityManager;

public class RegisterDokumenOssRepositoryJPA implements IDokumenOssRepository {

	private final EntityManager entityManager;		
	
	public RegisterDokumenOssRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	

	@Override
	public List<DokumenOss> getAll() {
		return entityManager.createNamedQuery("DokumenOssData.findAll", RegisterDokumenOssData.class)
				.getResultList()
				.stream()
				.map(t -> convertDokumenOssDataToDokumenOss(t))
				.collect(Collectors.toList());
	}

	@Override
	public DokumenOss save(DokumenOss t, String s) {
		RegisterDokumenOssData dokumenOssData = convertDokumenOssToDokumenOssData(t, s);
		entityManager.persist(dokumenOssData);
		entityManager.flush();		
		return convertDokumenOssDataToDokumenOss(dokumenOssData);
	}

	@Override
	public DokumenOss update(DokumenOss t, String s) {
		RegisterDokumenOssData dokumenOssData = convertDokumenOssToDokumenOssData(t, s);
		dokumenOssData = entityManager.merge(dokumenOssData);
		return convertDokumenOssDataToDokumenOss(dokumenOssData);
	}

	@Override
	public DeleteResponse delete(String nib) {
		RegisterDokumenOssData dokumenOssData = entityManager.find(RegisterDokumenOssData.class, nib);
		entityManager.remove(dokumenOssData);
		return new DeleteResponse(true, nib);
	}

	@Override
	public DokumenOss updateById(String nib, DokumenOss dokumenOss, String s) {
		RegisterDokumenOssData updaData = convertDokumenOssToDokumenOssData(dokumenOss, s);
		RegisterDokumenOssData dokumenOssData = entityManager.find(RegisterDokumenOssData.class, nib);
		dokumenOssData.setNib(updaData.getNib());
		dokumenOssData.setTanggalPenerbitan(updaData.getTanggalPenerbitan());
		dokumenOssData.setDaftarRegisterKbliData(updaData.getDaftarRegisterKbliData());
				
		return convertDokumenOssDataToDokumenOss(dokumenOssData);
	}

	@Override
	public List<DokumenOss> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("DokumenOssData.findAll", RegisterDokumenOssData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDokumenOssDataToDokumenOss(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<DokumenOss> getByNib(String nib) {
		return entityManager.createNamedQuery("DokumenOssData.findByNib", RegisterDokumenOssData.class)
				.setParameter("nib", nib)
				.getResultList()
				.stream()
				.map(t -> convertDokumenOssDataToDokumenOss(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<DokumenOss> getByNibAndPage(String nib, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("DokumenOssData.findByNib", RegisterDokumenOssData.class)
				.setParameter("nib", nib)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertDokumenOssDataToDokumenOss(t))
				.collect(Collectors.toList());
	}
	
	private RegisterDokumenOssData convertDokumenOssToDokumenOssData(DokumenOss t, String s) {
		RegisterDokumenOssData dokumenOssData = new RegisterDokumenOssData();		
		dokumenOssData.setNib(t.getNib());
		dokumenOssData.setTanggalPenerbitan(LocalDate.now());
		
		RegisterDokumenData registerDokumenData = new RegisterDokumenData();
		registerDokumenData.setId(s);		
		dokumenOssData.setRegisterDokumenData(registerDokumenData);
		
		Set<RegisterKbliData> daftarRegisterKbliData = new HashSet<RegisterKbliData>();		
		List<Kbli> daftarKbli = t.getDaftarKbli();
		
		for(Kbli item : daftarKbli) {
			RegisterKbliData registerKbliData = new RegisterKbliData();
			KbliData kbliData = new KbliData();
			kbliData.setId(item.getKode());
			registerKbliData.setKbliData(kbliData);
		}	
		
		dokumenOssData.setDaftarRegisterKbliData(daftarRegisterKbliData);
		
		return dokumenOssData;
	}
	
	private DokumenOss convertDokumenOssDataToDokumenOss(RegisterDokumenOssData d) {
		MasterDokumenData dokumenData = d.getRegisterDokumenData().getDokumen();
		KategoriDokumenData kategoriDokumenData = dokumenData.getKategoriDokumenData();
		String dokumen = new String(
				dokumenData.getId(), 
				dokumenData.getNama(), 
				new KategoriDokumen(
						kategoriDokumenData.getId(), 
						kategoriDokumenData.getNama(), 
						kategoriDokumenData.getParent()
						)				
				);

		Set<RegisterKbliData> daftaRegisterKbliData = d.getDaftarRegisterKbliData();
		List<Kbli> daftarKbli = new ArrayList<Kbli>();
		
		for(RegisterKbliData item : daftaRegisterKbliData) {
			KbliData kbliData = item.getKbliData();
			Kbli kbli = new Kbli(kbliData.getId(), kbliData.getNama(), kbliData.getKategori());
			daftarKbli.add(kbli);		
		}
		
		return new DokumenOss(dokumen, d.getNib(), d.getTanggalPenerbitan(), daftarKbli);				
	}
}
