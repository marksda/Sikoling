package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.repository.IDokumenPerusahaanRepository;
import org.Sikoling.ejb.main.repository.perusahaan.PerusahaanData;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;

public class DokumenPerusahaanRepositoryJPA implements IDokumenPerusahaanRepository {

	private final EntityManager entityManager;	
	
	public DokumenPerusahaanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

	@Override
	public List<Dokumen> getAll() {
		return entityManager.createNamedQuery("DokumenPerusahaanData.findAll", DokumenPerusahaanData.class)
				.getResultList()
				.stream()
				.map(t -> convertDokumenPerusahaanDataToDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public Dokumen save(Dokumen t) {
		DokumenPerusahaanData dokumenPerusahaanData = convertDokumenPerusahaanToDokumenPerusahaanData(t);
		entityManager.persist(dokumenPerusahaanData);
		entityManager.flush();
		
		return convertDokumenPerusahaanDataToDokumenPerusahaan(dokumenPerusahaanData);
	}

	@Override
	public Dokumen update(Dokumen t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dokumen> getAllByPage(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dokumen> getByNama(String nama) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dokumen> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	private Dokumen convertDokumenPerusahaanDataToDokumenPerusahaan(DokumenPerusahaanData d) {
//		Perusahaan perusahaan = new Perusahaan();
//		
//		DetailDokumenPerusahaanData detailDokumenPerusahaanData = new DetailDokumenPerusahaanData();
//		detailDokumenPerusahaanData.setId(d.getDetailDokumen().getId());
//		
//		Jsonb jsonb = JsonbBuilder.create();
//		
//		Map<String, Object> attribute = jsonb
//				.fromJson(d.getAttribute(), null)
//		
//		return new DokumenPerusahaan(
//				"id", perusahaanData, detailDokumenPerusahaanData, 
//				d.getTanggalUpload(), d.isBerlaku(), null, d.getLokasiFile());
		return null;
	}
	
	private DokumenPerusahaanData convertDokumenPerusahaanToDokumenPerusahaanData(Dokumen t) {
		DokumenPerusahaanData dokumenPerusahaanData = new DokumenPerusahaanData();
		dokumenPerusahaanData.setId("test");
		
		PerusahaanData perusahaanData = new PerusahaanData();
		perusahaanData.setId(t.getPerusahaan().getId());
		dokumenPerusahaanData.setPerusahaan(perusahaanData);
		
		DetailDokumenPerusahaanData detailDokumenPerusahaanData = new DetailDokumenPerusahaanData();
		detailDokumenPerusahaanData.setId(t.getNamaDokumen().getId());		
		dokumenPerusahaanData.setDetailDokumen(detailDokumenPerusahaanData);
		
		dokumenPerusahaanData.setTanggalUpload(t.getTanggalUpload());
		dokumenPerusahaanData.setBerlaku(t.isBerlaku());
		
		Jsonb jsonb = JsonbBuilder.create();		 
		String result = jsonb.toJson(t.getAttribute());
		dokumenPerusahaanData.setAttribute(result);
		
		dokumenPerusahaanData.setLokasiFile(t.getLokasiFile());
		
		return dokumenPerusahaanData;
	}
}
