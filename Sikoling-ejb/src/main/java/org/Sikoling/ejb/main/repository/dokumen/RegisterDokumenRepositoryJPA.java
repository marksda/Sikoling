package org.Sikoling.ejb.main.repository.dokumen;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.SuratArahan;
import org.Sikoling.ejb.abstraction.repository.IRegisterDokumenRepository;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

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
				.map(d -> convertRegisterDokumenDataToRegisterDokumen(d))
				.collect(Collectors.toList());
	}

	@Override
	public RegisterDokumen save(RegisterDokumen t) {
		RegisterDokumenData registerDokumenData = convertRegisterDokumenToRegisterDokumenData(t);
		entityManager.persist(registerDokumenData);
		entityManager.flush();
		
		return convertRegisterDokumenDataToRegisterDokumen(entityManager.find(RegisterDokumenData.class, registerDokumenData.getId()));
	}
	
	@Override
	public RegisterDokumen update(RegisterDokumen t) {
		RegisterDokumenData registerDokumenData = convertRegisterDokumenToRegisterDokumenData(t);
		registerDokumenData = entityManager.merge(registerDokumenData);
		return convertRegisterDokumenDataToRegisterDokumen(registerDokumenData);
	}
	
	@Override
	public List<RegisterDokumen> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterDokumenData.findAll", RegisterDokumenData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertRegisterDokumenDataToRegisterDokumen(t))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<RegisterDokumen> getByNamaPerusahaan(String namaPerusahaan) {
		namaPerusahaan = "%" + namaPerusahaan + "%";
		return entityManager.createNamedQuery("RegisterDokumenData.findByNamaPerusahaan", RegisterDokumenData.class)
				.setParameter("namaPerusahaan", namaPerusahaan)
				.getResultList()
				.stream()
				.map(d -> convertRegisterDokumenDataToRegisterDokumen(d))
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
				.map(t -> convertRegisterDokumenDataToRegisterDokumen(t))
				.collect(Collectors.toList());
	}	
	
	@Override
	public List<RegisterDokumen> getByIdPerusahaan(String idPerusahaan) {
		return entityManager.createNamedQuery("RegisterDokumenData.findByIdPerusahaan", RegisterDokumenData.class)
				.setParameter("idPerusahaan", idPerusahaan)
				.getResultList()
				.stream()
				.map(d -> convertRegisterDokumenDataToRegisterDokumen(d))
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
				.map(t -> convertRegisterDokumenDataToRegisterDokumen(t))
				.collect(Collectors.toList());
	}	
	
	@Override
	public List<RegisterDokumen> getByNamaDokumen(String namaDokumen) {
		namaDokumen = "%" + namaDokumen + "%";
		return entityManager.createNamedQuery("RegisterDokumenData.findByNamaDokumen", RegisterDokumenData.class)
				.setParameter("namaDokumen", namaDokumen)
				.getResultList()
				.stream()
				.map(d -> convertRegisterDokumenDataToRegisterDokumen(d))
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
				.map(d -> convertRegisterDokumenDataToRegisterDokumen(d))
				.collect(Collectors.toList());
	}	
	
	@Override
	public List<RegisterDokumen> getByIdDokumen(String idDokumen) {
		return entityManager.createNamedQuery("RegisterDokumenData.findByIdDokumen", RegisterDokumenData.class)
				.setParameter("idDokumen", idDokumen)
				.getResultList()
				.stream()
				.map(d -> convertRegisterDokumenDataToRegisterDokumen(d))
				.collect(Collectors.toList());
	}
		
	@Override
	public List<RegisterDokumen> getByIdDokumenAndPage(String idDokumen, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterDokumenData.findByIdDokumen", RegisterDokumenData.class)
				.setParameter("idDokumen", idDokumen)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(d -> convertRegisterDokumenDataToRegisterDokumen(d))
				.collect(Collectors.toList());
	}
		
//	private Set<RegisterKbliData> convertJsonArrayKbliToDaftarRegisterKbliData(JsonArray daftarKbli) {
//		Iterator<JsonValue> iteratorDaftarKbli = daftarKbli.iterator();
//		
//		Set<RegisterKbliData> daftarKbliData = new HashSet<RegisterKbliData>();
//		while (iteratorDaftarKbli.hasNext()) {
//			 JsonObject kbliJsonObject = iteratorDaftarKbli.next().asJsonObject();
//			 RegisterKbliData registerKbliData = new RegisterKbliData();
//			 KbliData kbliData = new KbliData();
//			 kbliData.setId(kbliJsonObject.getString("kode"));
//			 registerKbliData.setKbliData(kbliData);				 
//			 daftarKbliData.add(registerKbliData);
//		}
//		
//		return daftarKbliData;
//	}
	
	private RegisterDokumen convertRegisterDokumenDataToRegisterDokumen(RegisterDokumenData d) {
		MasterDokumenData masterDokumenData = d.getDokumenData();
		AutorisasiData uploaderData = d.getUploader();
		RegisterPerusahaanData registerPerusahaanData = d.getPerusahaanData();
		PelakuUsahaData pelakuUsahaData = registerPerusahaanData.getPelakuUsahaData();
		 
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
					new Perusahaan(
							registerPerusahaanData.getId(), 
							registerPerusahaanData.getNama(), 
							null, 
							null, 
							new PelakuUsaha(
									pelakuUsahaData.getId(), 
									pelakuUsahaData.getNama(), 
									pelakuUsahaData.getSingkatan(), 
									null
									), 
							null, 
							null, 
							null, 
							registerPerusahaanData.getStatusVerifikasi()
							), 
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
	
	private RegisterDokumenData convertRegisterDokumenToRegisterDokumenData(RegisterDokumen t) {
		Dokumen dokumen = t.getDokumen();
		
		RegisterDokumenData registerDokumenData = new RegisterDokumenData();
		registerDokumenData.setId(
				t.getId() != null ? t.getId() : getGenerateIdRegisterDokumen()
				);		
		RegisterPerusahaanData registerPerusahaanData = new RegisterPerusahaanData();
		registerPerusahaanData.setId(t.getPerusahaan().getId());
		registerDokumenData.setPerusahaanData(registerPerusahaanData);		
		MasterDokumenData masterDokumenData = new MasterDokumenData();
		masterDokumenData.setId(dokumen.getId());
		registerDokumenData.setDokumenData(masterDokumenData);
		registerDokumenData.setTanggalRegistrasi(t.getTanggalRegistrasi());	
		Authority uploader = t.getUploader();
		AutorisasiData uploaderData = new AutorisasiData();
		uploaderData.setId(uploader.getId());		
		registerDokumenData.setUploader(uploaderData);		
				
		if(dokumen instanceof SuratArahan) {
			SuratArahan suratArahan = (SuratArahan) dokumen;
			SuratArahanData suratArahanData = new SuratArahanData();
			suratArahanData.setNoSurat(suratArahan.getNoSurat());
			suratArahanData.setTanggalSurat(suratArahan.getTanggalSurat());
			suratArahanData.setPerihalSurat(suratArahan.getPerihalSurat());
			suratArahanData.setUraianKegiatan(suratArahan.getUraianKegiatan());
			registerDokumenData.setSuratArahanData(null);
		}		
		
		return registerDokumenData;
	}
			
	@Override
	public DeleteResponse delete(String id) {
		RegisterDokumenData registerDokumenData = entityManager.find(RegisterDokumenData.class, id);
		entityManager.remove(registerDokumenData);
		return new DeleteResponse(true, id);
	}
	
	private String getGenerateIdRegisterDokumen() {
		int tahun = LocalDate.now().getYear();
		String hasil;
		
		Query q = entityManager.createQuery("SELECT MAX(rd.id) "
				+ "FROM RegisterDokumenData rd "
				+ "WHERE EXTRACT(YEAR FROM rd.tanggalRegistrasi) = :tahun");
		
		q.setParameter("tahun", tahun);
		
		try {
			hasil = (String) q.getSingleResult();
			hasil = hasil.substring(0, 6);
			Long idBaru = Long.valueOf(hasil)  + 1;
			hasil = LPad(Long.toString(idBaru), 6, '0');
			return hasil.concat(Integer.toString(tahun));
		} catch (Exception e) {	
			hasil = "000001";			
			return hasil.concat(Integer.toString(tahun));
		}		
	}
	
	private String LPad(String str, Integer length, char car) {
		  return (String.format("%" + length + "s", "").replace(" ", String.valueOf(car)) + str).substring(str.length(), length + str.length());
	}
	
}
