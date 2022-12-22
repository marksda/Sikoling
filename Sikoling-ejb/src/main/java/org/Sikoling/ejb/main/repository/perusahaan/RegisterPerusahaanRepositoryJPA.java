package org.Sikoling.ejb.main.repository.perusahaan;

import java.time.LocalDate;
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
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
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
	public DeleteResponse deleteLinkKepemilikanPerusahaan(String idAutority, String idPerusahaan) {
		AutorityPerusahaanDataId id = new AutorityPerusahaanDataId();
		id.setAutority(idAutority);
		id.setPerusahaan(idPerusahaan);
		
		AutorityPerusahaanData personPerusahaanData = entityManager.find(AutorityPerusahaanData.class, id);
		entityManager.remove(personPerusahaanData);			
		return new DeleteResponse(true, idPerusahaan);
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
	
	private RegisterPerusahaanData convertRegisterPerusahaanToRegisterPerusahaanData(RegisterPerusahaan t) {	
		
		RegisterPerusahaanData registerPerusahaanData = new RegisterPerusahaanData();	
		registerPerusahaanData.setId(
				t.getId() != null ? t.getId() : getGenerateIdRegisterPerusahaan()
				);
		
		Perusahaan perusahaan = t.getPerusahaan();
		registerPerusahaanData.setId(perusahaan.getId());
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
		
//		List<RegisterDokumen> daftarRegisterDokumen = perusahaan.getDaftarRegisterDokumen();
//		List<RegisterDokumenData> daftarRegisterDokumenData = new ArrayList<RegisterDokumenData>();
//		
//		
//		if(daftarRegisterDokumen != null) {
//			for(RegisterDokumen item : daftarRegisterDokumen) {
//				RegisterDokumenData registerDokumenData = new RegisterDokumenData();
//	
//				MasterDokumenData dokumenData = new MasterDokumenData();
//				dokumenData.setId(item.getDokumen().getId());
//				registerDokumenData.setDokumenData(dokumenData);
//				registerDokumenData.setTanggalRegistrasi(item.getTanggalRegistrasi());
//				registerDokumenData.setUploader(autorisasiDataKreator);
//				
//				registerDokumenData.setLokasiFile(item.getLokasiFile());
//				
//				switch (item.getDokumen().getId()) {
//				case "010301":				
//					JsonObject detailAttributeDokumen = item.getDokumen().getDetailAttributeDokumen();
//					RegisterDokumenOssData dokumenOssData = new RegisterDokumenOssData();
//					dokumenOssData.setNib(detailAttributeDokumen.getString("nib"));
//					dokumenOssData.setTanggalPenerbitan(LocalDate.parse(detailAttributeDokumen.getString("tanggalPenerbitan")));
//					dokumenOssData.setDaftarRegisterKbliData(convertJsonArrayKbliToDaftarRegisterKbliData(detailAttributeDokumen.getJsonArray("daftarKbli")));
//					
//					registerDokumenData.setDokumenOssData(dokumenOssData);
//					break;
//				default:
//					break;
//				}
//				daftarRegisterDokumenData.add(registerDokumenData);
//				
//			}
//		}
//		
//		registerPerusahaanData.setDaftarRegisterDokumenData(daftarRegisterDokumenData);
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
//		List<RegisterDokumenData> daftarRegisterDokumenData = d.getDaftarRegisterDokumenData();
//		List<RegisterDokumen> daftarRegisterDokumen = new ArrayList<RegisterDokumen>();
//		
//		if(daftarRegisterDokumenData != null) {
//			for(RegisterDokumenData item : daftarRegisterDokumenData) {		
//				MasterDokumenData dokumenData = item.getDokumenData();
//				KategoriDokumenData kategoriDokumenData = dokumenData.getKategoriDokumenData();
//				
//				RegisterDokumen registerDokumen;
//				AutorisasiData uploaderData = item.getUploader();
//				
//				Authority uploader = new Authority(
//						uploaderData.getId(), 
//						null, 
//						null, 
//						false, 
//						false, 
//						uploaderData.getUserName());
//				Dokumen dokumen;		
//				
//			
//				switch (dokumenData.getId()) {
//				case "010301":				
//					RegisterDokumenOssData dokumenOssData = item.getDokumenOssData();						
//					JsonObjectBuilder jsonDetailAttributeDokumenBuilder = Json.createObjectBuilder();
//					jsonDetailAttributeDokumenBuilder.add("nib", dokumenOssData.getNib());
//					jsonDetailAttributeDokumenBuilder.add("tanggalPenerbitan", dokumenOssData.getTanggalPenerbitan().toString());
//					
//					JsonArrayBuilder jsonArrayDaftarKbliBuilder = Json.createArrayBuilder();		
//					Set<RegisterKbliData> daftarRegisterKbliData = dokumenOssData.getDaftarRegisterKbliData();
//					Iterator<RegisterKbliData> iteratorDaftarRegitrasiKbliData = daftarRegisterKbliData.iterator();
//					
//					while (iteratorDaftarRegitrasiKbliData.hasNext()) {
//						RegisterKbliData registerKbliData = (RegisterKbliData) iteratorDaftarRegitrasiKbliData.next();
//						JsonObjectBuilder jsonKbliBuilder = Json.createObjectBuilder();
//						jsonKbliBuilder.add("kode", registerKbliData.getKbliData().getId());
//						jsonKbliBuilder.add("nama", registerKbliData.getKbliData().getNama());
//						jsonArrayDaftarKbliBuilder.add(jsonKbliBuilder);				
//					}
//					
//					jsonDetailAttributeDokumenBuilder.add("daftarKbli", jsonArrayDaftarKbliBuilder);
//					JsonObject detailAttributeDokumenJson = jsonDetailAttributeDokumenBuilder.build();
//					
//					dokumen = new Dokumen(
//							dokumenData.getId(), 
//							dokumenData.getNama(), 
//							new KategoriDokumen(
//									kategoriDokumenData.getId(), 
//									kategoriDokumenData.getNama(), 
//									kategoriDokumenData.getParent()
//									),
//							detailAttributeDokumenJson
//							);
//					
//					registerDokumen = new RegisterDokumen(
//							dokumen,
//							null, 
//							item.getLokasiFile(), 
//							item.getTanggalRegistrasi(), 
//							uploader
//							);
//							
//					daftarRegisterDokumen.add(registerDokumen);
//					break;
//				default:
//					daftarRegisterDokumen = null;
//					break;
//				}			
//			}
//		}		
		
		AutorisasiData kreatorData = d.getKreator();
		Authority kreator = new Authority(
				kreatorData.getId(),
				null, 
				null, 
				false, 
				false, 
				null);
		
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
						d.getId(), 
						d.getNama(), 
						modelPerizinan, 
						skalaUsaha, 
						pelakuUsaha, 
						alamatPerusahaan, 
						kontakPerusahaan, 
						null, 
						d.isStatusVerifikasi()
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
	public List<RegisterPerusahaan> getByIdLinkKepemilikan(String idLinkKepemilikan) {
		return entityManager.createNamedQuery("PersonPerusahaanData.findByPemilik", AutorityPerusahaanData.class)
				.setParameter("personId", idLinkKepemilikan)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPerusahaanDataToRegisterPerusahaan(t.getPerusahaan()))
				.collect(Collectors.toList());
	}
		
//	private boolean isEksisData(String npwp) {
//		int hasil = entityManager.createNamedQuery("RegisterPerusahaanData.findByNpwp", RegisterPerusahaanData.class)
//				.setParameter("npwp", npwp)
//				.getFirstResult();
//		if(hasil == 0) {
//			return false;
//		}
//		else {
//			return true;
//		}
//	}
		
	private String getGenerateIdRegisterPerusahaan() {
		int tahun = LocalDate.now().getYear();
		String hasil;
		
		Query q = entityManager.createQuery("SELECT count(p.id) jml "
				+ "FROM master.tbl_perusahaan p "
				+ "WHERE date_part('year', p.tanggal_registrasi) = :tahun");
		
		q.setParameter("tahun", tahun);
		
		try {
			int idBaru = (int) q.getSingleResult() + 1;
			hasil = LPad(Integer.toString(idBaru), 4, '0');
			return hasil.concat(Integer.toString(tahun));
		} catch (Exception e) {			
			hasil = "0001";			
			return hasil.concat(Integer.toString(tahun));
		}		
	}
	
	private String LPad(String str, Integer length, char car) {
		  return (str + String.format("%" + length + "s", "").replace(" ", String.valueOf(car))).substring(0, length);
	}
	
}

	