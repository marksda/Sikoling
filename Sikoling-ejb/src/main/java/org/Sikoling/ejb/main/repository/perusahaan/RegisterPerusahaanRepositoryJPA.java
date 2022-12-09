package org.Sikoling.ejb.main.repository.perusahaan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.dokumen.MasterDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.RegisterDokumenOssData;
import org.Sikoling.ejb.main.repository.dokumen.KategoriDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.KbliData;
import org.Sikoling.ejb.main.repository.dokumen.RegisterDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.RegisterKbliData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kategoripelakuusaha.KategoriPelakuUsahaData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;
import jakarta.persistence.EntityManager;

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
		entityManager.persist(registerPerusahaanData);
		entityManager.flush();		
		return convertRegisterPerusahaanDataToRegisterPerusahaan(registerPerusahaanData);
	}
	
	@Override
	public DeleteResponse delete(String id) {
		RegisterPerusahaanData registerPerusahaanData = entityManager.find(RegisterPerusahaanData.class, id);
		entityManager.remove(registerPerusahaanData);			
		return new DeleteResponse(true, id);
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
		return convertRegisterPerusahaanDataToRegisterPerusahaan(entityManager.find(RegisterPerusahaanData.class, npwp));
	}
	
	private Set<RegisterKbliData> convertJsonArrayKbliToDaftarRegisterKbliData(JsonArray daftarKbli) {
		Iterator<JsonValue> iteratorDaftarKbli = daftarKbli.iterator();
		
		Set<RegisterKbliData> daftarKbliData = new HashSet<RegisterKbliData>();
		while (iteratorDaftarKbli.hasNext()) {
			 JsonObject kbliJsonObject = iteratorDaftarKbli.next().asJsonObject();
			 RegisterKbliData registerKbliData = new RegisterKbliData();
			 KbliData kbliData = new KbliData();
			 kbliData.setId(kbliJsonObject.getString("kode"));
			 registerKbliData.setKbliData(kbliData);				 
			 daftarKbliData.add(registerKbliData);
		}
		
		return daftarKbliData;
	}
	
	private RegisterPerusahaanData convertRegisterPerusahaanToRegisterPerusahaanData(RegisterPerusahaan t) {	
		
		RegisterPerusahaanData RegisterPerusahaanData = new RegisterPerusahaanData();	
		
		Perusahaan perusahaan = t.getPerusahaan();
		RegisterPerusahaanData.setId(perusahaan.getId());
		RegisterPerusahaanData.setNama(perusahaan.getNama());
				
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
		RegisterPerusahaanData.setAlamatPerusahaanData(alamatPerusahaanData);
		
		ModelPerizinanData modelPerizinanData = new ModelPerizinanData();
		modelPerizinanData.setId(perusahaan.getModelPerizinan().getId());
		RegisterPerusahaanData.setModelPerizinanData(modelPerizinanData);
		
		SkalaUsahaData skalaUsahaData = new SkalaUsahaData();
		skalaUsahaData.setId(perusahaan.getSkalaUsaha().getId());
		RegisterPerusahaanData.setSkalaUsahaData(skalaUsahaData);
			
		PelakuUsaha pelakuUsaha = perusahaan.getPelakuUsaha();
		KategoriPelakuUsaha kategoriPelakuUsaha = pelakuUsaha.getKategoriPelakuUsaha();
		PelakuUsahaData pelakuUsahaData = new PelakuUsahaData();
		pelakuUsahaData.setId(pelakuUsaha.getId());
		KategoriPelakuUsahaData kategoriPelakuUsahaData = new KategoriPelakuUsahaData();
		kategoriPelakuUsahaData.setId(kategoriPelakuUsaha.getId());
		pelakuUsahaData.setKategoriPelakuUsahaData(kategoriPelakuUsahaData);
		RegisterPerusahaanData.setPelakuUsahaData(pelakuUsahaData);
		
		KontakPerusahaanData kontakPerusahaanData = new KontakPerusahaanData();
		kontakPerusahaanData.setEmail(perusahaan.getKontak().getEmail());
		kontakPerusahaanData.setFax(perusahaan.getKontak().getFax());
		kontakPerusahaanData.setTelepone(perusahaan.getKontak().getTelepone());	
		RegisterPerusahaanData.setKontakPerusahaanData(kontakPerusahaanData);		
		
		List<RegisterDokumen> daftarRegisterDokumen = perusahaan.getDaftarRegisterDokumen();
		List<RegisterDokumenData> daftarRegisterDokumenData = new ArrayList<RegisterDokumenData>();
		
		
		if(daftarRegisterDokumen != null) {
			for(RegisterDokumen item : daftarRegisterDokumen) {
				RegisterDokumenData registerDokumenData = new RegisterDokumenData();
	
				MasterDokumenData dokumenData = new MasterDokumenData();
				dokumenData.setId(item.getDokumen().getId());
				registerDokumenData.setDokumenData(dokumenData);
				registerDokumenData.setTanggalRegistrasi(item.getTanggalRegistrasi());
				
				PersonData personData = new PersonData();
				personData.setId(registerDokumenData.getUploader().getId());
				registerDokumenData.setUploader(personData);
				registerDokumenData.setLokasiFile(item.getLokasiFile());
				
				switch (item.getDokumen().getId()) {
				case "010301":				
					JsonObject detailAttributeDokumen = item.getDokumen().getDetailAttributeDokumen();
					RegisterDokumenOssData dokumenOssData = new RegisterDokumenOssData();
					dokumenOssData.setNib(detailAttributeDokumen.getString("nib"));
					dokumenOssData.setTanggalPenerbitan(LocalDate.parse(detailAttributeDokumen.getString("tanggalPenerbitan")));
					dokumenOssData.setDaftarRegisterKbliData(convertJsonArrayKbliToDaftarRegisterKbliData(detailAttributeDokumen.getJsonArray("daftarKbli")));
					
					registerDokumenData.setDokumenOssData(dokumenOssData);
					break;
				default:
					break;
				}
				daftarRegisterDokumenData.add(registerDokumenData);
				
			}
		}
		
		RegisterPerusahaanData.setDaftarRegisterDokumenData(daftarRegisterDokumenData);
		RegisterPerusahaanData.setStatusVerifikasi(perusahaan.isStatusVerifikasi());
		PersonData kreator = new PersonData();
		kreator.setId(t.getKreator().getNik());
		RegisterPerusahaanData.setKreator(kreator);
		
		return RegisterPerusahaanData;
	}
	
	private RegisterPerusahaan convertRegisterPerusahaanDataToRegisterPerusahaan(RegisterPerusahaanData d) {
		
		ModelPerizinan modelPerizinan = new ModelPerizinan(
				d.getModelPerizinanData().getId(), d.getModelPerizinanData().getNama(), 
				d.getModelPerizinanData().getSingkatan());	
		
		SkalaUsaha skalaUsaha = new SkalaUsaha(
				d.getSkalaUsahaData().getId(), d.getSkalaUsahaData().getNama(), 
				d.getSkalaUsahaData().getSingkatan());
		
		KategoriPelakuUsaha kategoriPelakuUsaha = new KategoriPelakuUsaha(
				d.getPelakuUsahaData().getKategoriPelakuUsahaData().getId(), 
				d.getPelakuUsahaData().getKategoriPelakuUsahaData().getNama());
		
		PelakuUsaha pelakuUsaha = new PelakuUsaha(
				d.getPelakuUsahaData().getId(), d.getPelakuUsahaData().getNama(), d.getPelakuUsahaData().getSingkatan(), kategoriPelakuUsaha);
		
		Alamat alamatPerusahaan = new Alamat(
				new Propinsi(d.getAlamatPerusahaanData().getPropinsiData().getId(), d.getAlamatPerusahaanData().getPropinsiData().getNama()),
				new Kabupaten(d.getAlamatPerusahaanData().getKabupatenData().getId(), d.getAlamatPerusahaanData().getKabupatenData().getNama()),
				new Kecamatan(d.getAlamatPerusahaanData().getKecamatanData().getId(), d.getAlamatPerusahaanData().getKecamatanData().getNama()),
				new Desa(d.getAlamatPerusahaanData().getDesaData().getId(), d.getAlamatPerusahaanData().getDesaData().getNama()),
				d.getAlamatPerusahaanData().getKeterangan());
		
		Kontak kontakPerusahaan = new Kontak(d.getKontakPerusahaanData().getTelepone(), 
				d.getKontakPerusahaanData().getFax(), d.getKontakPerusahaanData().getEmail());
		List<RegisterDokumenData> daftarRegisterDokumenData = d.getDaftarRegisterDokumenData();
		List<RegisterDokumen> daftarRegisterDokumen = new ArrayList<RegisterDokumen>();
		
		if(daftarRegisterDokumenData != null) {
			for(RegisterDokumenData item : daftarRegisterDokumenData) {		
				MasterDokumenData dokumenData = item.getDokumenData();
				KategoriDokumenData kategoriDokumenData = dokumenData.getKategoriDokumenData();
				RegisterDokumen registerDokumen;
				Dokumen dokumen;
				Person uploader = new Person(
						item.getUploader().getId(), 
						item.getUploader().getNama(), 
						null, null, null, null);
			
				switch (dokumenData.getId()) {
				case "010301":				
					RegisterDokumenOssData dokumenOssData = item.getDokumenOssData();						
					JsonObjectBuilder jsonDetailAttributeDokumenBuilder = Json.createObjectBuilder();
					jsonDetailAttributeDokumenBuilder.add("nip", dokumenOssData.getNib());
					jsonDetailAttributeDokumenBuilder.add("tanggalPenerbitan", dokumenOssData.getTanggalPenerbitan().toString());
					
					JsonArrayBuilder jsonArrayDaftarKbliBuilder = Json.createArrayBuilder();		
					Set<RegisterKbliData> daftarRegisterKbliData = dokumenOssData.getDaftarRegisterKbliData();
					Iterator<RegisterKbliData> iteratorDaftarRegitrasiKbliData = daftarRegisterKbliData.iterator();
					
					while (iteratorDaftarRegitrasiKbliData.hasNext()) {
						RegisterKbliData registerKbliData = (RegisterKbliData) iteratorDaftarRegitrasiKbliData.next();
						JsonObjectBuilder jsonKbliBuilder = Json.createObjectBuilder();
						jsonKbliBuilder.add("kode", registerKbliData.getKbliData().getId());
						jsonKbliBuilder.add("nama", registerKbliData.getKbliData().getNama());
						jsonArrayDaftarKbliBuilder.add(jsonKbliBuilder);				
					}
					
					jsonDetailAttributeDokumenBuilder.add("daftarKbli", jsonArrayDaftarKbliBuilder);
					JsonObject detailAttributeDokumenJson = jsonDetailAttributeDokumenBuilder.build();
					
					dokumen = new Dokumen(
							dokumenData.getId(), 
							dokumenData.getNama(), 
							new KategoriDokumen(
									kategoriDokumenData.getId(), 
									kategoriDokumenData.getNama(), 
									kategoriDokumenData.getParent()
									),
							detailAttributeDokumenJson
							);
					
					registerDokumen = new RegisterDokumen(dokumen, null, item.getLokasiFile(), item.getTanggalRegistrasi(), uploader);
							
					daftarRegisterDokumen.add(registerDokumen);
					break;
				default:
					daftarRegisterDokumen = null;
					break;
				}			
			}
		}
		
		return new RegisterPerusahaan(
				d.getTanggalRegistrasi(), 
				new Person(d.getKreator().getId(), d.getKreator().getNama(), null, null, null, null), 
				new Person(d.getVerifikator().getId(), d.getVerifikator().getNama(), null, null, null, null), 
				new Perusahaan( 
						d.getId(), d.getNama(), modelPerizinan, skalaUsaha, pelakuUsaha, 
						alamatPerusahaan, kontakPerusahaan, daftarRegisterDokumen, d.isStatusVerifikasi()
						)
				);
	}
	
	@Override
	public List<RegisterPerusahaan> getByIdPerson(String personId) {
		return entityManager.createNamedQuery("PersonPerusahaanData.findByPemilik", PersonPerusahaanData.class)
				.setParameter("personId", personId)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPerusahaanDataToRegisterPerusahaan(t.getPerusahaan()))
				.collect(Collectors.toList());
	}
		
}
