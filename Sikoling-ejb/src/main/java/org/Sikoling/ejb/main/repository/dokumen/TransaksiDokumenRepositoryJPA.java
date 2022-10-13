package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import org.Sikoling.ejb.abstraction.entity.ItemTransaksiDokumen;
import org.Sikoling.ejb.abstraction.repository.ITransaksiDokumenRepository;
import jakarta.persistence.EntityManager;

public class TransaksiDokumenRepositoryJPA implements ITransaksiDokumenRepository<ItemTransaksiDokumen> {

	private final EntityManager entityManager;	
	
	public TransaksiDokumenRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<ItemTransaksiDokumen> getAll() {
//		return entityManager.createNamedQuery("DokumenPerusahaanData.findAll", TransaksiDokumenData.class)
//				.getResultList()
//				.stream()
//				.map(t -> convertDokumenPerusahaanDataToDokumenPerusahaan(t))
//				.collect(Collectors.toList());
		return null;
	}

	@Override
	public ItemTransaksiDokumen save(ItemTransaksiDokumen t) {
		TransaksiDokumenData dokumenPerusahaanData = convertDokumenPerusahaanToDokumenPerusahaanData(t);
		entityManager.persist(dokumenPerusahaanData);
		entityManager.flush();
		
		return convertDokumenPerusahaanDataToDokumenPerusahaan(dokumenPerusahaanData);
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

	private ItemTransaksiDokumen convertDokumenPerusahaanDataToDokumenPerusahaan(TransaksiDokumenData d) {
//		Dokumen dokumen = new Dokumen(d.getId(), d.get, null, null)
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
