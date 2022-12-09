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
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.repository.IPerusahaanRepository;
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

public class PerusahaanRepositoryJPA implements IPerusahaanRepository {
	
	private final EntityManager entityManager;	

	public PerusahaanRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Perusahaan> getAll() {
		return entityManager.createNamedQuery("PerusahaanData.findAll", PerusahaanData.class)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public Perusahaan save(Perusahaan t, Person s) {
		PerusahaanData perusahaanData = convertPerusahaanToPerusahaanData(t, s);		
		entityManager.persist(perusahaanData);
		entityManager.flush();		
		return convertPerusahaanDataToPerusahaan(perusahaanData);
	}

	@Override
	public DeleteResponse delete(String id) {
		PerusahaanData perusahaanData = entityManager.find(PerusahaanData.class, id);
		entityManager.remove(perusahaanData);			
		return new DeleteResponse(true, id);
	}
	
	@Override
	public Perusahaan update(Perusahaan t, Person s) {
		PerusahaanData pemrakarsaData = convertPerusahaanToPerusahaanData(t, s);
		pemrakarsaData = entityManager.merge(pemrakarsaData);
		return convertPerusahaanDataToPerusahaan(pemrakarsaData);
	}
	
	@Override
	public Perusahaan updateById(String id, Perusahaan perusahaan) {
		String idBaru = perusahaan.getId();
		PerusahaanData perusahaanData = convertPerusahaanToPerusahaanData(perusahaan);
		perusahaanData.setId(id);
		perusahaanData = entityManager.merge(perusahaanData);
		if(!idBaru.equals(id)) {
			perusahaanData.setId(idBaru);
		}
		
		return convertPerusahaanDataToPerusahaan(perusahaanData);
	}
	
	@Override
	public Perusahaan updateStatusVerifikasi(Perusahaan t, boolean statusVerifikasi) {
		PerusahaanData perusahaanData = convertPerusahaanToPerusahaanData(t);
		perusahaanData.setStatusVerifikasi(statusVerifikasi);
		perusahaanData = entityManager.merge(perusahaanData);
		return convertPerusahaanDataToPerusahaan(perusahaanData);
	}

	@Override
	public List<Perusahaan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("PerusahaanData.findAll", PerusahaanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Perusahaan> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("PerusahaanData.findByQueryNama", PerusahaanData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<Perusahaan> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("PerusahaanData.findByQueryNama", PerusahaanData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean cekById(String id) {
		PerusahaanData perusahaanData = entityManager.find(PerusahaanData.class, id);
		if(perusahaanData == null) {
			return false;
		}
		else {
			return true;
		}		 
	}
	
	@Override
	public Perusahaan getByNpwp(String npwp) {
		return convertPerusahaanDataToPerusahaan(entityManager.find(PerusahaanData.class, npwp));
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
	
	private PerusahaanData convertPerusahaanToPerusahaanData(Perusahaan t, Person s) {	
		
		PerusahaanData perusahaanData = new PerusahaanData();	
		perusahaanData.setId(t.getId());
		perusahaanData.setNama(t.getNama());
				
		AlamatPerusahaanData alamatPerusahaanData = new AlamatPerusahaanData();
		alamatPerusahaanData.setKeterangan(t.getAlamat().getKeterangan());
		DesaData desaData = new DesaData();
		desaData.setId(t.getAlamat().getDesa().getId());
		alamatPerusahaanData.setDesaData(desaData);
		KecamatanData kecamatanData = new KecamatanData();
		kecamatanData.setId(t.getAlamat().getKecamatan().getId());
		alamatPerusahaanData.setKecamatanData(kecamatanData);
		KabupatenData kabupatenData = new KabupatenData();
		kabupatenData.setId(t.getAlamat().getKabupaten().getId());
		alamatPerusahaanData.setKabupatenData(kabupatenData);
		PropinsiData propinsiData = new PropinsiData();
		propinsiData.setId(t.getAlamat().getPropinsi().getId());
		alamatPerusahaanData.setPropinsiData(propinsiData);
		perusahaanData.setAlamatPerusahaanData(alamatPerusahaanData);
		
		ModelPerizinanData modelPerizinanData = new ModelPerizinanData();
		modelPerizinanData.setId(t.getModelPerizinan().getId());
		perusahaanData.setModelPerizinanData(modelPerizinanData);
		
		SkalaUsahaData skalaUsahaData = new SkalaUsahaData();
		skalaUsahaData.setId(t.getSkalaUsaha().getId());
		perusahaanData.setSkalaUsahaData(skalaUsahaData);
			
		PelakuUsaha pelakuUsaha = t.getPelakuUsaha();
		KategoriPelakuUsaha kategoriPelakuUsaha = pelakuUsaha.getKategoriPelakuUsaha();
		PelakuUsahaData pelakuUsahaData = new PelakuUsahaData();
		pelakuUsahaData.setId(pelakuUsaha.getId());
		KategoriPelakuUsahaData kategoriPelakuUsahaData = new KategoriPelakuUsahaData();
		kategoriPelakuUsahaData.setId(kategoriPelakuUsaha.getId());
		pelakuUsahaData.setKategoriPelakuUsahaData(kategoriPelakuUsahaData);
		perusahaanData.setPelakuUsahaData(pelakuUsahaData);
		
		KontakPerusahaanData kontakPerusahaanData = new KontakPerusahaanData();
		kontakPerusahaanData.setEmail(t.getKontak().getEmail());
		kontakPerusahaanData.setFax(t.getKontak().getFax());
		kontakPerusahaanData.setTelepone(t.getKontak().getTelepone());	
		perusahaanData.setKontakPerusahaanData(kontakPerusahaanData);		
		
		List<RegisterDokumen> daftarRegisterDokumen = t.getDaftarRegisterDokumen();
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
		
		perusahaanData.setDaftarRegisterDokumenData(daftarRegisterDokumenData);
		perusahaanData.setStatusVerifikasi(t.isStatusVerifikasi());
		PersonData personData = new PersonData();
		personData.setId(s.getNik());
		perusahaanData.setKreator(personData);
		
		return perusahaanData;
	}
	
	private Perusahaan convertPerusahaanDataToPerusahaan(PerusahaanData d) {
		
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
		
		return new Perusahaan( 
				d.getId(), d.getNama(), modelPerizinan, skalaUsaha, pelakuUsaha, 
				alamatPerusahaan, kontakPerusahaan, daftarRegisterDokumen, d.isStatusVerifikasi()
				);
	}
	
	@Override
	public List<Perusahaan> getByIdPerson(String personId) {
		return entityManager.createNamedQuery("PersonPerusahaanData.findByPemilik", PersonPerusahaanData.class)
				.setParameter("personId", personId)
				.getResultList()
				.stream()
				.map(t -> convertPerusahaanDataToPerusahaan(t.getPerusahaan()))
				.collect(Collectors.toList());
	}
	
}
