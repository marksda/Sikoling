package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.repository.ITransaksiDokumenRepository;
import org.Sikoling.ejb.main.repository.perusahaan.PerusahaanData;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;

public class TransaksiDokumenRepositoryJPA implements ITransaksiDokumenRepository {

	private final EntityManager entityManager;	
	
	public TransaksiDokumenRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Dokumen> getAll() {
		return entityManager.createNamedQuery("DokumenPerusahaanData.findAll", TransaksiDokumenData.class)
				.getResultList()
				.stream()
				.map(t -> convertDokumenPerusahaanDataToDokumenPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public Dokumen save(Dokumen t) {
		TransaksiDokumenData dokumenPerusahaanData = convertDokumenPerusahaanToDokumenPerusahaanData(t);
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

	private Dokumen convertDokumenPerusahaanDataToDokumenPerusahaan(TransaksiDokumenData d) {
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
	
	private TransaksiDokumenData convertDokumenPerusahaanToDokumenPerusahaanData(Dokumen t) {
		TransaksiDokumenData dokumenPerusahaanData = new TransaksiDokumenData();
		dokumenPerusahaanData.setId("test");
		
		PerusahaanData perusahaanData = new PerusahaanData();
		perusahaanData.setId(t.getPerusahaan().getId());
		dokumenPerusahaanData.setPerusahaan(perusahaanData);
		
		DokumenData detailDokumenPerusahaanData = new DokumenData();
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
