package org.Sikoling.ejb.main.repository.permohonan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.StatusDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.SuratArahan;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusWali;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonan;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.repository.IRegisterPermohonanRepository;
import org.Sikoling.ejb.main.repository.alamat.AlamatData;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.dokumen.KategoriDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.MasterDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.RegisterDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.SuratArahanData;
import org.Sikoling.ejb.main.repository.hakakses.HakAksesData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kategoripelakuusaha.KategoriPelakuUsahaData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.kontak.KontakData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

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
		
		return convertRegisterPermohonanDataToRegisterPermohonan(registerPermohonanData);
		
//		return convertRegisterPermohonanDataToRegisterPermohonan(
//				entityManager.find(RegisterPermohonanData.class, registerPermohonanData.getId())
//				);
	}

	@Override
	public RegisterPermohonan update(RegisterPermohonan t) {
		RegisterPermohonanData registerPermohonanData = convertRegisterPermohonanToRegisterPermohonanData(t);
		registerPermohonanData = entityManager.merge(registerPermohonanData);
		return convertRegisterPermohonanDataToRegisterPermohonan(
				entityManager.find(RegisterPermohonanData.class, registerPermohonanData.getId())
				);
	}

	@Override
	public List<RegisterPermohonan> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterPermohonanData.findAll", RegisterPermohonanData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPermohonanDataToRegisterPermohonan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPermohonan> getByIdPengakses(String idPengakses) {
		return entityManager.createNamedQuery("RegisterPermohonanData.findByPengakses", RegisterPermohonanData.class)
				.setParameter("idPengakses", idPengakses)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPermohonanDataToRegisterPermohonan(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterPermohonan> getByIdPerusahaan(String idPerusahaan) {
		return entityManager.createNamedQuery("RegisterPermohonanData.findByPerusahaan", RegisterPermohonanData.class)
				.setParameter("idRegisterPerusahaan", idPerusahaan)
				.getResultList()
				.stream()
				.map(t -> convertRegisterPermohonanDataToRegisterPermohonan(t))
				.collect(Collectors.toList());
	}

	private RegisterPermohonan convertRegisterPermohonanDataToRegisterPermohonan(RegisterPermohonanData d) {
		
		KategoriPermohonan kategoriPermohonan = convertKategoriPermohonanDataToKategoriPermohonan(d.getKategoriPermohonanData());
		RegisterPerusahaan registerPerusahaan = convertRegisterPerusahaanDataToRegisterPerusahaan(d.getPerusahaanData());
		Authority pengurusPermohonan = convertAutorisasiDataToAuthority(d.getAutorisasiData());
		StatusWali statusWali = convertKategoriPengurusPermohonanDataToStatusWali(d.getKategoriPengurusPermohonanData());		
		Person penanggungJawabPermohonan = convertPersonDataToPerson(d.getPenanggungJawab());				
		PosisiTahapPemberkasan statusTahapPemberkasan = convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(d.getPosisiTahapPemberkasanData());
		
		List<RegisterDokumen> daftarRegisterDokumenSyarat = convertDaftarDokumenPersyaratanPermohonanToDaftarRegisterDokumen(d.getDaftarDokumenSyarat());
		
		return new RegisterPermohonan(
				d.getId(), 
				kategoriPermohonan, 
				d.getTanggalRegistrasi(), 
				registerPerusahaan, 
				pengurusPermohonan, 
				statusWali, 
				penanggungJawabPermohonan,
				statusTahapPemberkasan, 
				daftarRegisterDokumenSyarat, 
				null
				);
	}
	
	private RegisterPermohonanData convertRegisterPermohonanToRegisterPermohonanData(RegisterPermohonan t) {
		RegisterPermohonanData registerPermohonanData = new RegisterPermohonanData();
		registerPermohonanData.setId(
				t.getId() != null ? t.getId() : getGenerateIdRegisterPermohonan()
				);
		
		KategoriPermohonanData kategoriPermohonanData = new KategoriPermohonanData();
		kategoriPermohonanData.setId(t.getKategoriPermohonan().getId());
		registerPermohonanData.setKategoriPermohonanData(kategoriPermohonanData);
		
		registerPermohonanData.setTanggalRegistrasi(t.getTanggalRegistrasi());
		
		RegisterPerusahaanData registerPerusahaanData = new RegisterPerusahaanData();
		registerPerusahaanData.setId(t.getPerusahaan().getId());		
		registerPermohonanData.setPerusahaanData(registerPerusahaanData);
		
		AutorisasiData pengaksesData = new AutorisasiData();
		pengaksesData.setId(t.getPengurusPermohonan().getId());
		registerPermohonanData.setAutorisasiData(pengaksesData);
		
		KategoriPengurusPermohonanData kategoriPengurusPermohonanData = new KategoriPengurusPermohonanData();		
		kategoriPengurusPermohonanData.setId(t.getStatusWaliPengurusPermohonan().getId());
		registerPermohonanData.setKategoriPengurusPermohonanData(kategoriPengurusPermohonanData);
		
		PosisiTahapPemberkasanData posisiTahapPemberkasanData = new PosisiTahapPemberkasanData();
		posisiTahapPemberkasanData.setId(t.getPosisiBerkas().getId());
		registerPermohonanData.setPosisiTahapPemberkasanData(posisiTahapPemberkasanData);	
		
		PersonData penanggungJawabPermohonanData = new PersonData();
		penanggungJawabPermohonanData.setId(t.getPenanggungJawabPermohonan().getNik());
		registerPermohonanData.setPenanggungJawab(penanggungJawabPermohonanData);
		
		registerPermohonanData.setDaftarDokumenSyarat(
				convertDaftarRegisterDokumenToDaftarDokumenPersyaratanData(t.getDaftarDokumenSyarat(), registerPermohonanData)
				);
		
		return registerPermohonanData;
	}
	
	@Override
	public DeleteResponse delete(String id) {
		RegisterPermohonanData registerPermohonanData = entityManager.find(RegisterPermohonanData.class, id);
		entityManager.remove(registerPermohonanData);			
		return new DeleteResponse(true, id);
	}
		
	private String getGenerateIdRegisterPermohonan() {
		int tahun = LocalDate.now().getYear();
		String hasil;
		
		Query q = entityManager.createQuery("SELECT MAX(rd.id) "
				+ "FROM RegisterPermohonanData rd "
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

	private List<DokumenPersyaratanPermohonanData> convertDaftarRegisterDokumenToDaftarDokumenPersyaratanData(List<RegisterDokumen> d, RegisterPermohonanData registerPermohonanData) {
		List<DokumenPersyaratanPermohonanData> daftarDokumenPersyaratanPermohonan = new ArrayList<DokumenPersyaratanPermohonanData>();
		Iterator<RegisterDokumen> iter = d.iterator();
		
		DokumenPersyaratanPermohonanData item;
		while (iter.hasNext()) {
			RegisterDokumen registerDokumen = (RegisterDokumen) iter.next();
			RegisterDokumenData registerDokumenData = new RegisterDokumenData();
			registerDokumenData.setId(registerDokumen.getId());			
			
			item = new DokumenPersyaratanPermohonanData();
			item.setRegisterDokumen(registerDokumenData);
			item.setRegisterPermohonan(registerPermohonanData);
			
			daftarDokumenPersyaratanPermohonan.add(item);
		}
		
		return daftarDokumenPersyaratanPermohonan;
	}
	
	private KategoriDokumen convertKategoriDokumenDataToKategoriDokumen(KategoriDokumenData d) {
		KategoriDokumen kategoriDokumen = null;
		
		if(d != null) {
			kategoriDokumen = new KategoriDokumen(d.getId(), d.getNama(), d.getParent());
		}
		
		return kategoriDokumen;
	}
	
	private Dokumen convertMasterDokumenDataToDokumen(MasterDokumenData d) {
		Dokumen dokumen = null;
		
		if(d != null) {
			KategoriDokumen kategoriDokumen = convertKategoriDokumenDataToKategoriDokumen(d.getKategoriDokumenData());
			dokumen = new Dokumen(d.getId(), d.getNama(), kategoriDokumen);
		}
		
		return dokumen;
	}
	
	private SuratArahan convertSuratArahanDataToSuratArahan(Dokumen dok, SuratArahanData d) {
		SuratArahan suratArahan = null;
		
		if(d != null) {
			suratArahan = new SuratArahan(
					dok.getId(), 
					dok.getNama(), 
					dok.getKategoriDokumen(), 
					d.getNoSurat(), 
					d.getTanggalSurat(), 
					d.getPerihalSurat(), 
					d.getUraianKegiatan()
					);
		}
		
		return suratArahan;
	}
	
	private RegisterDokumen convertRegisterDokumenDataToRegisterDokumen(RegisterDokumenData d) {
		RegisterDokumen registerDokumen = null;
		
		if(d != null) {			
			SuratArahan suratArahan = convertSuratArahanDataToSuratArahan(
					convertMasterDokumenDataToDokumen(d.getDokumenData()), 
					d.getSuratArahanData()
					);			
			Authority authority = convertAutorisasiDataToAuthority(d.getUploader());			
			RegisterPerusahaan registerPerusahaan = convertRegisterPerusahaanDataToRegisterPerusahaan(d.getPerusahaanData());
			
			if(suratArahan != null) {
				registerDokumen = new RegisterDokumen(
						d.getId(), 
						suratArahan, 
						null, 
						null, 
						d.getStatusDokumen() != null ? new StatusDokumen(
								d.getStatusDokumen().getId(), d.getStatusDokumen().getNama()) : null,
						d.getTanggalRegistrasi(), 
						authority,
						d.getStatusVerified()
						);
			}
		}
		
		return registerDokumen;
	}
	
	private List<RegisterDokumen> convertDaftarDokumenPersyaratanPermohonanToDaftarRegisterDokumen(List<DokumenPersyaratanPermohonanData> d) {
		List<RegisterDokumen> daftarRegisterDokumen = new ArrayList<RegisterDokumen>();
		Iterator<DokumenPersyaratanPermohonanData> iter = d.iterator();
		
		DokumenPersyaratanPermohonanData dokumenPersyaratanPermohonanData = null;
		RegisterDokumen item;
		while (iter.hasNext()) {
			dokumenPersyaratanPermohonanData = (DokumenPersyaratanPermohonanData) iter.next();		
			
			item = convertRegisterDokumenDataToRegisterDokumen(dokumenPersyaratanPermohonanData.getRegisterDokumen());

			daftarRegisterDokumen.add(item);
		}
		
		return daftarRegisterDokumen;
	}
			
	private KategoriPermohonan convertKategoriPermohonanDataToKategoriPermohonan(KategoriPermohonanData d) {
		KategoriPermohonan kategoriPermohonan = null;
		
		if(d != null) {
			kategoriPermohonan = new KategoriPermohonan(d.getId(), d.getNama());
		}
		
		return kategoriPermohonan;
	}
	
	private StatusWali convertKategoriPengurusPermohonanDataToStatusWali(KategoriPengurusPermohonanData d) {
		StatusWali statusWali = null;
				
		if(d != null) {
			statusWali = new StatusWali(d.getId(), d.getNama());
		}
		
		return statusWali;
	}
	
	private Propinsi convertPropinsiDataToPropinsi(PropinsiData d) {
		Propinsi propinsi = null;
		
		if(d != null) {
			propinsi = new Propinsi(d.getId(), d.getNama());
		}
		
		return propinsi;		
	}
	
	private Kabupaten convertKabupatenDataToKabupaten(KabupatenData d) {
		Kabupaten kabupaten = null;
		
		if(d != null) {
			kabupaten = new Kabupaten(d.getId(), d.getNama());
		}
		
		return kabupaten;		
	}

	private Kecamatan convertKecamatanDataToKecamatan(KecamatanData d) {
		Kecamatan kecamatan = null;
		
		if(d != null) {
			kecamatan = new Kecamatan(d.getId(), d.getNama());
		}
		
		return kecamatan;		
	}
	
	private Desa convertDesaDataToDesa(DesaData d) {
		Desa desa = null;
		
		if(d != null) {
			desa = new Desa(d.getId(), d.getNama());
		}
		
		return desa;		
	}

	private Alamat convertAlamatDataToAlamat(AlamatData d) {
		Alamat alamat = null;
		
		if( d != null) {
			alamat = new Alamat(
					convertPropinsiDataToPropinsi(d.getPropinsi()), 
					convertKabupatenDataToKabupaten(d.getKabupaten()), 
					convertKecamatanDataToKecamatan(d.getKecamatan()), 
					convertDesaDataToDesa(d.getDesa()), 
					d.getDetailAlamat()
					);					
		}
		
		return alamat;
	}
	
	private Kontak convertKontakDataToKontak(KontakData d) {
		Kontak kontak = null;
		
		if(d != null) {
			kontak = new Kontak(
					d.getTelepone(), 
					d.getFax(), 
					d.getEmail()
					);
		}
		
		return kontak;
	}
	
	private Person convertPersonDataToPerson(PersonData d) {
		Person person = null;
		
		if(d != null) {
			JenisKelaminData jenisKelaminData = d.getSex();
			JenisKelamin jenisKelamin = jenisKelaminData != null ?
					new JenisKelamin(jenisKelaminData.getId(), jenisKelaminData.getNama()) : null;
			
			Alamat alamat = convertAlamatDataToAlamat(d.getAlamat());
			
			Kontak kontak = convertKontakDataToKontak(d.getKontak());
			
			person = new Person(
					d.getId(), 
					d.getNama(), 
					jenisKelamin, 
					alamat, 
					d.getScanKtp(), 
					kontak
					);
		}
		
		return person;
	}
	
	private PosisiTahapPemberkasan convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(PosisiTahapPemberkasanData d) {
		PosisiTahapPemberkasan statusTahapPemberkasan = null;
		
		if(d != null) {
			statusTahapPemberkasan = new PosisiTahapPemberkasan(
					d.getId(), 
					d.getNama(), 
					d.getKeterangan()
					); 
		}
		
		return statusTahapPemberkasan;
	}
	
	private HakAkses convertHakAksesDataToHakAkses(HakAksesData d) {
		HakAkses hakAkses = null;
		
		if(d != null) {
			hakAkses = new HakAkses(d.getId(), d.getNama(), d.getKeterangan());
		}
		
		return hakAkses;
	}
	
	private Authority convertAutorisasiDataToAuthority(AutorisasiData d) {
		Authority authority = null;
		
		if(d != null) {
			authority = new Authority(
					d.getId(), 
					convertPersonDataToPerson(d.getPerson()), 
					convertHakAksesDataToHakAkses(d.getHakAkses()), 
					d.getStatusInternal(), 
					d.getIsVerified(), 
					d.getUserName()
					);
		}
		
		return authority;
	}
		
	private ModelPerizinan convertModelPerizinanDataToModelPerizinan(ModelPerizinanData d) {
		ModelPerizinan modelPerizinan = null;
		
		if(d != null) {
			modelPerizinan = new ModelPerizinan(d.getId(), d.getNama(), d.getSingkatan());
		}
		
		return modelPerizinan;
	}
	
	private SkalaUsaha convertSkalaUsahaDataToSkalaUsaha(SkalaUsahaData d) {
		SkalaUsaha skalaUsaha = null;
		
		if(d != null) {
			skalaUsaha = new SkalaUsaha(d.getId(), d.getNama(), d.getSingkatan());
		}
		
		return skalaUsaha;
	}
	
	private KategoriPelakuUsaha convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(KategoriPelakuUsahaData d) {
		KategoriPelakuUsaha kategoriPelakuUsaha = null;
				
		if(d != null) {
			kategoriPelakuUsaha = new KategoriPelakuUsaha(d.getId(), d.getNama());
		}
		
		return kategoriPelakuUsaha;
	}
	
	private PelakuUsaha convertPelakuUsahaDataToPelakuUsaha(PelakuUsahaData d) {
		PelakuUsaha pelakuUsaha = null;
		
		if(d != null) {
			KategoriPelakuUsaha kategoriPelakuUsaha = convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(d.getKategoriPelakuUsaha());
			pelakuUsaha = new PelakuUsaha(d.getId(), d.getNama(), d.getSingkatan(), kategoriPelakuUsaha);
		}
		
		return pelakuUsaha;
	}
	
	private List<RegisterDokumen> convertDaftarRegisterDokumenDataToDaftarRegisterDokumen(List<RegisterDokumenData> d) {
		List<RegisterDokumen> daftarRegisterDokumen = null;
		
		if(d != null) {
			daftarRegisterDokumen = new ArrayList<RegisterDokumen>();
			for(RegisterDokumenData item : d) {		
				daftarRegisterDokumen.add(convertRegisterDokumenDataToRegisterDokumen(item));
			}
		}
		
		return daftarRegisterDokumen;		
	}
	
	private RegisterPerusahaan convertRegisterPerusahaanDataToRegisterPerusahaan(RegisterPerusahaanData d) {
		RegisterPerusahaan registerPerusahaan = null; 
		Authority kreator = convertAutorisasiDataToAuthority(d.getKreator());
		Authority verifikator = convertAutorisasiDataToAuthority(d.getVerifikator());
		ModelPerizinan modelPerizinan = convertModelPerizinanDataToModelPerizinan(d.getModelPerizinanData());
		SkalaUsaha skalaUsaha = convertSkalaUsahaDataToSkalaUsaha(d.getSkalaUsahaData());
		PelakuUsaha pelakuUsaha = convertPelakuUsahaDataToPelakuUsaha(d.getPelakuUsaha());
		Alamat alamat = convertAlamatDataToAlamat(d.getAlamatPerusahaanData());
		Kontak kontak = convertKontakDataToKontak(d.getKontakPerusahaanData());
		List<RegisterDokumen> daftarRegisterDokumen = convertDaftarRegisterDokumenDataToDaftarRegisterDokumen(d.getDaftarRegisterDokumenData());
		
		Perusahaan perusahaan = new Perusahaan(
				d.getNpwp(), 
				d.getNama(), 
				modelPerizinan, 
				skalaUsaha, 
				pelakuUsaha, 
				alamat, 
				kontak, 
				daftarRegisterDokumen, 
				d.getStatusVerifikasi()
				);
		
		if( d != null) {
			registerPerusahaan = new RegisterPerusahaan(
					d.getId(), 
					d.getTanggalRegistrasi(), 
					kreator, 
					verifikator, 
					perusahaan
					);
		}
		
		return registerPerusahaan;
	}

}
