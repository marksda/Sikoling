package org.Sikoling.ejb.main.repository.dokumen;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.IRegisterDokumenRepository;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kategoripelakuusaha.KategoriPelakuUsahaData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.perusahaan.AlamatPerusahaanData;
import org.Sikoling.ejb.main.repository.perusahaan.KontakPerusahaanData;
import org.Sikoling.ejb.main.repository.perusahaan.PerusahaanData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;
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
				.map(d -> convertRegisterDokumenDataToRegisterDokumen(d))
				.collect(Collectors.toList());
	}

	@Override
	public RegisterDokumen save(RegisterDokumen t) {
		RegisterDokumenData registerDokumenData = convertRegisterDokumenToRegisterDokumenData(t);
		entityManager.persist(registerDokumenData);
		entityManager.flush();
		
		return convertRegisterDokumenDataToRegisterDokumen(registerDokumenData);
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

	private Perusahaan convertPerusahaanDataToPerusahaan(PerusahaanData d) {
		ModelPerizinanData modelPerizinanData = d.getModelPerizinanData();
		SkalaUsahaData skalaUsahaData = d.getSkalaUsahaData();
		PelakuUsahaData pelakuUsahaData = d.getPelakuUsahaData();
		KategoriPelakuUsahaData kategoriPelakuUsahaData = pelakuUsahaData.getKategoriPelakuUsahaData();
		AlamatPerusahaanData alamatPerusahaanData = d.getAlamatPerusahaanData();
		PropinsiData propinsiDataPerusahaan = alamatPerusahaanData.getPropinsiData();
		KabupatenData kabupatenDataPerusahaan = alamatPerusahaanData.getKabupatenData();
		KecamatanData kecamatanDataPerusahaan = alamatPerusahaanData.getKecamatanData();
		DesaData desaDataPerusahaan = alamatPerusahaanData.getDesaData();
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
						),
				null,
				d.isStatusVerifikasi()
				);
	}
	
	private Person covertPersonDataToPerson(PersonData d) {
		return new Person(d.getId(), d.getNama(), null, null, null, null);
	}
	
	private RegisterDokumen convertRegisterDokumenDataToRegisterDokumen(RegisterDokumenData d) {
		RegisterDokumen registerDokumen;
		Dokumen dokumen;		
		MasterDokumenData masterDokumenData = d.getDokumenData();
		KategoriDokumenData kategoriDokumenData = masterDokumenData.getKategoriDokumenData();
		
		switch (d.getDokumenData().getId()) {
		case "010301":
			RegisterDokumenOssData dokumenOssData = d.getDokumenOssData();
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
					masterDokumenData.getId(), 
					masterDokumenData.getNama(), 
					new KategoriDokumen(
							kategoriDokumenData.getId(), 
							kategoriDokumenData.getNama(), 
							kategoriDokumenData.getParent()), 
					detailAttributeDokumenJson
					);
			registerDokumen = new RegisterDokumen(
					dokumen, 
					convertPerusahaanDataToPerusahaan(d.getPerusahaanData()),
					d.getLokasiFile(), 
					d.getTanggalRegistrasi(), 
					covertPersonDataToPerson(d.getUploader())
					);
			break;
		default:
			registerDokumen = null;
			break;
		}		
		
		return registerDokumen;

	}
	
	private RegisterDokumenData convertRegisterDokumenToRegisterDokumenData(RegisterDokumen t) {
		Dokumen dokumen = t.getDokumen();
		
		RegisterDokumenData registerDokumenData = new RegisterDokumenData();
		
		PerusahaanData perusahaanData = new PerusahaanData();
		registerDokumenData.setPerusahaanData(perusahaanData);		
		
		MasterDokumenData masterDokumenData = new MasterDokumenData();
		masterDokumenData.setId(dokumen.getId());
		registerDokumenData.setDokumenData(masterDokumenData);		
		
		registerDokumenData.setTanggalRegistrasi(t.getTanggalRegistrasi());	
		
		PersonData uploader = new PersonData();
		uploader.setId(t.getUploader().getNik());
		registerDokumenData.setUploader(uploader);		

		switch (dokumen.getId()) {
		case "010301":
			JsonObject detailAttributeDokumen = dokumen.getDetailAttributeDokumen();
			RegisterDokumenOssData dokumenOssData = new RegisterDokumenOssData();	
			dokumenOssData.setNib(detailAttributeDokumen.getString("nib"));
			dokumenOssData.setTanggalPenerbitan(LocalDate.parse(detailAttributeDokumen.getString("tanggalPenerbitan")));
			dokumenOssData.setDaftarRegisterKbliData(convertJsonArrayKbliToDaftarRegisterKbliData(detailAttributeDokumen.getJsonArray("daftarKbli")));
			
			registerDokumenData.setDokumenOssData(dokumenOssData);
			break;
		default:
			break;
		}
		
		return registerDokumenData;
	}
			
	@Override
	public DeleteResponse delete(String id) {
		String[] arrOfId = id.split("*", 2);
		RegisterDokumenDataId registerDokumenDataId = new RegisterDokumenDataId();
		registerDokumenDataId.setPerusahaanData(arrOfId[0]);
		registerDokumenDataId.setDokumenData(arrOfId[1]);
		
		RegisterDokumenData registerDokumenData = entityManager.find(RegisterDokumenData.class, registerDokumenDataId);
		entityManager.remove(registerDokumenData);
		return new DeleteResponse(true, id);
	}
	
}
