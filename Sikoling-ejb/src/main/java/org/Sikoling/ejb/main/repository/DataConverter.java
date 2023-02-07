package org.Sikoling.ejb.main.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Authority;
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
import org.Sikoling.ejb.abstraction.entity.dokumen.NibOss;
import org.Sikoling.ejb.abstraction.entity.dokumen.StatusDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.SuratArahan;
import org.Sikoling.ejb.main.repository.alamat.AlamatData;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.dokumen.KategoriDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.MasterDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.NibOssData;
import org.Sikoling.ejb.main.repository.dokumen.RegisterDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.StatusDokumenData;
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

public class DataConverter implements Serializable {	
	private static final long serialVersionUID = 2511360024000140782L;
	
	public DataConverter() {
	}
	
	public Alamat convertAlamatDataToAlamat(AlamatData d) {
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
	
	public Authority convertAutorisasiDataToAuthority(AutorisasiData d) {
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

	public List<RegisterDokumen> convertDaftarRegisterDokumenDataToDaftarRegisterDokumenWithOutPerusahaan(List<RegisterDokumenData> d) {
		List<RegisterDokumen> daftarRegisterDokumen = null;
		
		if(d != null) {
			daftarRegisterDokumen = new ArrayList<RegisterDokumen>();
			for(RegisterDokumenData item : d) {		
				RegisterDokumen registerDokumen = convertRegisterDokumenDataToRegisterDokumenWithOutPerusahaan(item);
				if(registerDokumen != null) {
					daftarRegisterDokumen.add(registerDokumen);
				}				
			}
		}
		
		return daftarRegisterDokumen;		
	}
	
	public Desa convertDesaDataToDesa(DesaData d) {
		Desa desa = null;
		
		if(d != null) {
			desa = new Desa(d.getId(), d.getNama());
		}
		
		return desa;		
	}

	public HakAkses convertHakAksesDataToHakAkses(HakAksesData d) {
		HakAkses hakAkses = null;
		
		if(d != null) {
			hakAkses = new HakAkses(d.getId(), d.getNama(), d.getKeterangan());
		}
		
		return hakAkses;
	}
	
	public Kabupaten convertKabupatenDataToKabupaten(KabupatenData d) {
		Kabupaten kabupaten = null;
		
		if(d != null) {
			kabupaten = new Kabupaten(d.getId(), d.getNama());
		}
		
		return kabupaten;		
	}

	public KategoriDokumen convertKategoriDokumenDataToKategoriDokumen(KategoriDokumenData d) {
		KategoriDokumen kategoriDokumen = null;
		
		if(d != null) {
			kategoriDokumen = new KategoriDokumen(d.getId(), d.getNama(), d.getParent());
		}
		
		return kategoriDokumen;
	}
	
	public KategoriPelakuUsaha convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(KategoriPelakuUsahaData d) {
		KategoriPelakuUsaha kategoriPelakuUsaha = null;
				
		if(d != null) {
			kategoriPelakuUsaha = new KategoriPelakuUsaha(d.getId(), d.getNama());
		}
		
		return kategoriPelakuUsaha;
	}
	
	public Kecamatan convertKecamatanDataToKecamatan(KecamatanData d) {
		Kecamatan kecamatan = null;
		
		if(d != null) {
			kecamatan = new Kecamatan(d.getId(), d.getNama());
		}
		
		return kecamatan;		
	}	

	public Kontak convertKontakDataToKontak(KontakData d) {
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
	
	public Dokumen convertMasterDokumenDataToDokumen(MasterDokumenData d) {
		Dokumen dokumen = null;
		
		if(d != null) {
			KategoriDokumen kategoriDokumen = convertKategoriDokumenDataToKategoriDokumen(d.getKategoriDokumenData());
			dokumen = new Dokumen(d.getId(), d.getNama(), kategoriDokumen);
		}
		
		return dokumen;
	}
	
	public ModelPerizinan convertModelPerizinanDataToModelPerizinan(ModelPerizinanData d) {
		ModelPerizinan modelPerizinan = null;
		
		if(d != null) {
			modelPerizinan = new ModelPerizinan(d.getId(), d.getNama(), d.getSingkatan());
		}
		
		return modelPerizinan;
	}
	
	public PelakuUsaha convertPelakuUsahaDataToPelakuUsaha(PelakuUsahaData d) {
		PelakuUsaha pelakuUsaha = null;
		
		if(d != null) {
			KategoriPelakuUsaha kategoriPelakuUsaha = convertKategoriPelakuUsahaDataToKategoriPelakuUsaha(d.getKategoriPelakuUsaha());
			pelakuUsaha = new PelakuUsaha(d.getId(), d.getNama(), d.getSingkatan(), kategoriPelakuUsaha);
		}
		
		return pelakuUsaha;
	}
	
	public NibOss convertNibOssDataToNibOss(Dokumen dok, NibOssData d) {
		NibOss nibOss = null;
		
		if(d != null) {
			nibOss = new NibOss(
					dok.getId(), 
					dok.getNama(), 
					dok.getKategoriDokumen(), 
					d.getNomor(), 
					d.getTanggalPenetapan(), 
					null
					);
		}
		
		return nibOss;
	}
	
	public Person convertPersonDataToPerson(PersonData d) {
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
	
	public Propinsi convertPropinsiDataToPropinsi(PropinsiData d) {
		Propinsi propinsi = null;
		
		if(d != null) {
			propinsi = new Propinsi(d.getId(), d.getNama());
		}
		
		return propinsi;		
	}
	
	public RegisterDokumen convertRegisterDokumenDataToRegisterDokumenWithOutPerusahaan(RegisterDokumenData d) {
		RegisterDokumen registerDokumen = null;
		
		if(d != null) {			
			SuratArahan suratArahan = convertSuratArahanDataToSuratArahan(
					convertMasterDokumenDataToDokumen(d.getDokumenData()), 
					d.getSuratArahanData()
					);	
			
			NibOss nibOss = convertNibOssDataToNibOss(
					convertMasterDokumenDataToDokumen(d.getDokumenData()), 
					d.getNibOssData()
					);	
						
			if(suratArahan != null) {
				registerDokumen = new RegisterDokumen(
						d.getId(), 
						suratArahan, 
						null, 
						d.getLokasiFile(), 
						convertStatusDokumenDataToStatusDokumen(d.getStatusDokumen()),
						d.getTanggalRegistrasi(), 
						convertAutorisasiDataToAuthority(d.getUploader()),
						d.getStatusVerified()
						);
			}
			else if(nibOss != null ) {
				registerDokumen = new RegisterDokumen(
						d.getId(), 
						nibOss, 
						null, 
						d.getLokasiFile(), 
						convertStatusDokumenDataToStatusDokumen(d.getStatusDokumen()),
						d.getTanggalRegistrasi(), 
						convertAutorisasiDataToAuthority(d.getUploader()),
						d.getStatusVerified()
						);
			}
		}
		
		return registerDokumen;
	}
	
	public RegisterDokumen convertRegisterDokumenDataToRegisterDokumenWithPerusahaan(RegisterDokumenData d) {
		RegisterDokumen registerDokumen = null;
		
		if(d != null) {			
			SuratArahan suratArahan = convertSuratArahanDataToSuratArahan(
					convertMasterDokumenDataToDokumen(d.getDokumenData()), 
					d.getSuratArahanData()
					);			
			Authority authority = convertAutorisasiDataToAuthority(d.getUploader());			
			RegisterPerusahaanData registerPerusahaanData = d.getPerusahaanData();
			Perusahaan perusahaan = null;
			
			if(registerPerusahaanData != null) {
				perusahaan = new Perusahaan(
						registerPerusahaanData.getNpwp(), 
						registerPerusahaanData.getNama(), 
						convertModelPerizinanDataToModelPerizinan(registerPerusahaanData.getModelPerizinanData()), 
						convertSkalaUsahaDataToSkalaUsaha(registerPerusahaanData.getSkalaUsahaData()), 
						convertPelakuUsahaDataToPelakuUsaha(registerPerusahaanData.getPelakuUsaha()), 
						convertAlamatDataToAlamat(registerPerusahaanData.getAlamatPerusahaanData()), 
						convertKontakDataToKontak(registerPerusahaanData.getKontakPerusahaanData()), 
						null, 
						registerPerusahaanData.getStatusVerifikasi()
						);
			}
			
			
			if(suratArahan != null) {
				registerDokumen = new RegisterDokumen(
						d.getId(), 
						suratArahan, 
						perusahaan, 
						d.getLokasiFile(), 
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
	
	public RegisterPerusahaan convertRegisterPerusahaanDataToRegisterPerusahaan(RegisterPerusahaanData d) {
		RegisterPerusahaan registerPerusahaan = null; 
		Authority kreator = convertAutorisasiDataToAuthority(d.getKreator());
		Authority verifikator = convertAutorisasiDataToAuthority(d.getVerifikator());
		ModelPerizinan modelPerizinan = convertModelPerizinanDataToModelPerizinan(d.getModelPerizinanData());
		SkalaUsaha skalaUsaha = convertSkalaUsahaDataToSkalaUsaha(d.getSkalaUsahaData());
		PelakuUsaha pelakuUsaha = convertPelakuUsahaDataToPelakuUsaha(d.getPelakuUsaha());
		Alamat alamat = convertAlamatDataToAlamat(d.getAlamatPerusahaanData());
		Kontak kontak = convertKontakDataToKontak(d.getKontakPerusahaanData());
		List<RegisterDokumen> daftarRegisterDokumen = convertDaftarRegisterDokumenDataToDaftarRegisterDokumenWithOutPerusahaan(d.getDaftarRegisterDokumenData());
		
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
	
	public RegisterPerusahaan convertRegisterPerusahaanDataToRegisterPerusahaanWithOutRegisterDokumen(RegisterPerusahaanData d) {
		RegisterPerusahaan registerPerusahaan = null; 
		Authority kreator = convertAutorisasiDataToAuthority(d.getKreator());
		Authority verifikator = convertAutorisasiDataToAuthority(d.getVerifikator());
		ModelPerizinan modelPerizinan = convertModelPerizinanDataToModelPerizinan(d.getModelPerizinanData());
		SkalaUsaha skalaUsaha = convertSkalaUsahaDataToSkalaUsaha(d.getSkalaUsahaData());
		PelakuUsaha pelakuUsaha = convertPelakuUsahaDataToPelakuUsaha(d.getPelakuUsaha());
		Alamat alamat = convertAlamatDataToAlamat(d.getAlamatPerusahaanData());
		Kontak kontak = convertKontakDataToKontak(d.getKontakPerusahaanData());
		
		Perusahaan perusahaan = new Perusahaan(
				d.getNpwp(), 
				d.getNama(), 
				modelPerizinan, 
				skalaUsaha, 
				pelakuUsaha, 
				alamat, 
				kontak, 
				null, 
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
	
	public SkalaUsaha convertSkalaUsahaDataToSkalaUsaha(SkalaUsahaData d) {
		SkalaUsaha skalaUsaha = null;
		
		if(d != null) {
			skalaUsaha = new SkalaUsaha(d.getId(), d.getNama(), d.getSingkatan());
		}
		
		return skalaUsaha;
	}
	
	public StatusDokumen convertStatusDokumenDataToStatusDokumen(StatusDokumenData d) {
		StatusDokumen statusDokumen = null;
		
		if(d != null) {
			statusDokumen = new StatusDokumen(d.getId(), d.getNama());
		}
		
		return statusDokumen;
	}
	
	public SuratArahan convertSuratArahanDataToSuratArahan(Dokumen dok, SuratArahanData d) {
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
}
