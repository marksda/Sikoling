package org.Sikoling.ejb.main.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.entity.dokumen.AktaPendirian;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.LampiranSuratArahan;
import org.Sikoling.ejb.abstraction.entity.dokumen.NibOss;
import org.Sikoling.ejb.abstraction.entity.dokumen.RegisterKbli;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiDPLH;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiUKLUPL;
import org.Sikoling.ejb.abstraction.entity.dokumen.StatusDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.SuratArahan;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;
import org.Sikoling.ejb.abstraction.entity.log.FlowLogPermohonan;
import org.Sikoling.ejb.abstraction.entity.log.KategoriFlowLog;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonan;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatusWali;
import org.Sikoling.ejb.main.repository.alamat.AlamatData;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.dokumen.AktaPendirianData;
import org.Sikoling.ejb.main.repository.dokumen.KategoriDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.Kbli2020Data;
import org.Sikoling.ejb.main.repository.dokumen.LampiranSuratArahanData;
import org.Sikoling.ejb.main.repository.dokumen.MasterDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.NibOssData;
import org.Sikoling.ejb.main.repository.dokumen.RegisterDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.RegisterKbliData;
import org.Sikoling.ejb.main.repository.dokumen.RekomendasiDPLHData;
import org.Sikoling.ejb.main.repository.dokumen.RekomendasiUKLUPLData;
import org.Sikoling.ejb.main.repository.dokumen.StatusDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.SuratArahanData;
import org.Sikoling.ejb.main.repository.hakakses.HakAksesData;
import org.Sikoling.ejb.main.repository.jabatan.JabatanData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kategoripelakuusaha.KategoriPelakuUsahaData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.kontak.KontakData;
import org.Sikoling.ejb.main.repository.log.FlowLogData;
import org.Sikoling.ejb.main.repository.log.FlowLogPermohonanData;
import org.Sikoling.ejb.main.repository.log.KategoriLogData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.permohonan.DokumenPersyaratanPermohonanData;
import org.Sikoling.ejb.main.repository.permohonan.KategoriPengurusPermohonanData;
import org.Sikoling.ejb.main.repository.permohonan.KategoriPermohonanData;
import org.Sikoling.ejb.main.repository.permohonan.PosisiTahapPemberkasanData;
import org.Sikoling.ejb.main.repository.permohonan.RegisterPermohonanData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.perusahaan.PegawaiPerusahaanData;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class DataConverter {	
	private final EntityManager entityManager;	
	
	public DataConverter(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/*-----------Converter ObjectData To Object-----------------------------------------------*/	
	
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
	
	public JenisKelamin convertJenisKelaminDataToJenisKelamin(JenisKelaminData d) {
		JenisKelamin jenisKelamin = null;
		
		if(d != null) {
			jenisKelamin = new JenisKelamin(d.getId(), d.getNama());
		}
		
		return jenisKelamin;
	}
	
	public Person convertPersonDataToPerson(PersonData d) {
		Person person = null;
		
		if(d != null) {				
			person = new Person(
					d.getId(), 
					d.getNama(), 
					convertJenisKelaminDataToJenisKelamin(d.getSex()), 
					convertAlamatDataToAlamat(d.getAlamat()), 
					d.getScanKtp(), 
					convertKontakDataToKontak(d.getKontak())
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
	
	public Dokumen convertMasterDokumenDataToDokumen(MasterDokumenData d) {
		Dokumen dokumen = null;
		
		if(d != null) {
			KategoriDokumen kategoriDokumen = convertKategoriDokumenDataToKategoriDokumen(d.getKategoriDokumenData());
			dokumen = new Dokumen(d.getId(), d.getNama(), kategoriDokumen);
		}
		
		return dokumen;
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
	
	public RegisterKbli convertRegisterKbliDataToRegisterKbli(RegisterKbliData d) {
		RegisterKbli registerKbli = null;
		
		if(d != null) {
			registerKbli = new RegisterKbli(d.getNib().getNomor(), d.getKbli().getId(), d.getKbli().getNama());
		}
		
		return registerKbli;
	}
	
	public List<RegisterKbli> convertDaftarRegisterKbliDataToDaftarRegisterKbli(List<RegisterKbliData> d) {
		List<RegisterKbli> daftarRegisterKbli = null;
		
		if(d != null) {
			daftarRegisterKbli = new ArrayList<RegisterKbli>();
			for(RegisterKbliData item : d) {		
				RegisterKbli registerKbli = convertRegisterKbliDataToRegisterKbli(item);
				if(registerKbli != null) {
					daftarRegisterKbli.add(registerKbli);
				}				
			}
		}
		
		return daftarRegisterKbli;
	}
	
	public NibOss convertNibOssDataToNibOss(Dokumen dok, NibOssData d) {
		NibOss nibOss = null;
		
		if(d != null) {
			List<RegisterKbli> daftarRegisterKbli = convertDaftarRegisterKbliDataToDaftarRegisterKbli(d.getDaftarKbli());
			nibOss = new NibOss(
					dok.getId(), 
					dok.getNama(), 
					dok.getKategoriDokumen(), 
					d.getNomor(), 
					d.getTanggalPenetapan(), 
					daftarRegisterKbli
					);
		}
		
		return nibOss;
	}
			
	public AktaPendirian convertAktaPendirianDataToAktaPendirian(Dokumen dok, AktaPendirianData d) {
		AktaPendirian aktaPendirian = null;
		
		if(d != null) {
			aktaPendirian = new AktaPendirian(
					dok.getId(), 
					dok.getNama(), 
					dok.getKategoriDokumen(),
					d.getNomor(), 
					d.getTanggal(), 
					d.getNotaris(), 
					convertPegawaiPerusahaanDataToPegawaiPerusahaan(d.getPenanggungJawabData())
					);
		}
		
		return aktaPendirian;
		
	}
	
	public LampiranSuratArahan convertLampiranSuratArahanDataToLampiranSuratArahan(Dokumen dok, LampiranSuratArahanData d) {
		LampiranSuratArahan lampiranSuratArahan = null;
		
		if(d != null) {
			lampiranSuratArahan = new LampiranSuratArahan(
					dok.getId(), 
					dok.getNama(), 
					dok.getKategoriDokumen(), 
					d.getNoSurat(), 
					d.getTanggalSurat()
					);					
		}
		
		return lampiranSuratArahan;
	}
	
	public RekomendasiUKLUPL convertRekomendasiUKLUPLDataToRekomendasiUKLUPL(Dokumen dok, RekomendasiUKLUPLData d) {
		RekomendasiUKLUPL rekomendasiUKLUPL = null;
		
		if(d != null) {
			rekomendasiUKLUPL = new RekomendasiUKLUPL(
					dok.getId(), 
					dok.getNama(), 
					dok.getKategoriDokumen(), 
					d.getNoSurat(), 
					d.getTanggalSurat(),
					d.getPerihalSurat()
					);					
		}
		
		return rekomendasiUKLUPL;
	}
	
	public RekomendasiDPLH convertRekomendasiDPLHDataToRekomendasiDPLH(Dokumen dok, RekomendasiDPLHData d) {
		RekomendasiDPLH rekomendasiDPLH = null;
		
		if(d != null) {
			rekomendasiDPLH = new RekomendasiDPLH(
					dok.getId(), 
					dok.getNama(), 
					dok.getKategoriDokumen(), 
					d.getNoSurat(), 
					d.getTanggalSurat(),
					d.getPerihalSurat()
					);					
		}
		
		return rekomendasiDPLH;
	}
	
	public RegisterDokumen convertRegisterDokumenDataToRegisterDokumenWithOutPerusahaan(RegisterDokumenData d) {
		RegisterDokumen registerDokumen = null;
		
		if(d != null) {			
			Dokumen dokumen = convertMasterDokumenDataToDokumen(d.getDokumenData());	
			SuratArahan suratArahan = convertSuratArahanDataToSuratArahan(dokumen, d.getSuratArahanData());				
			NibOss nibOss = convertNibOssDataToNibOss(dokumen, d.getNibOssData());				
			AktaPendirian aktaPendirian = convertAktaPendirianDataToAktaPendirian(dokumen, d.getAktaPendirianData());			
			LampiranSuratArahan lampiranSuratArahan = convertLampiranSuratArahanDataToLampiranSuratArahan(dokumen, d.getLampiranSuratArahanData());
			RekomendasiUKLUPL rekomendasiUKLUPL = convertRekomendasiUKLUPLDataToRekomendasiUKLUPL(dokumen, d.getRekomendasiUKLUPLData());
			RekomendasiDPLH rekomendasiDPLH = convertRekomendasiDPLHDataToRekomendasiDPLH(dokumen, d.getRekomendasiDPLHData());
						
			if(suratArahan != null) {
				dokumen = suratArahan;
			}
			else if(nibOss != null ) {
				dokumen = nibOss;
			}
			else if(aktaPendirian != null) {
				dokumen = aktaPendirian;
			}
			else if(lampiranSuratArahan != null) {
				dokumen = lampiranSuratArahan;
			}
			else if(rekomendasiUKLUPL != null) {
				dokumen = rekomendasiUKLUPL;
			}
			else if(rekomendasiDPLH != null) {
				dokumen = rekomendasiDPLH;
			}
			
			registerDokumen = new RegisterDokumen(
					d.getId(), 
					dokumen, 
					null, 
					d.getLokasiFile(), 
					convertStatusDokumenDataToStatusDokumen(d.getStatusDokumen()),
					d.getTanggalRegistrasi(), 
					convertAutorisasiDataToAuthority(d.getUploader()),
					d.getStatusVerified()
					);
		}		
		
		return registerDokumen;
	}
	
	public RegisterDokumen convertRegisterDokumenDataToRegisterDokumenWithPerusahaan(RegisterDokumenData d) {
		RegisterDokumen registerDokumen = null;
		
		if(d != null) {				
			Dokumen dokumen = convertMasterDokumenDataToDokumen(d.getDokumenData());	
			SuratArahan suratArahan = convertSuratArahanDataToSuratArahan(dokumen, d.getSuratArahanData());				
			NibOss nibOss = convertNibOssDataToNibOss(dokumen, d.getNibOssData());				
			AktaPendirian aktaPendirian = convertAktaPendirianDataToAktaPendirian(dokumen, d.getAktaPendirianData());			
			LampiranSuratArahan lampiranSuratArahan = convertLampiranSuratArahanDataToLampiranSuratArahan(dokumen, d.getLampiranSuratArahanData());
			RekomendasiUKLUPL rekomendasiUKLUPL = convertRekomendasiUKLUPLDataToRekomendasiUKLUPL(dokumen, d.getRekomendasiUKLUPLData());
			RekomendasiDPLH rekomendasiDPLH = convertRekomendasiDPLHDataToRekomendasiDPLH(dokumen, d.getRekomendasiDPLHData());
			
			if(suratArahan != null) {
				dokumen = suratArahan;
			}
			else if(nibOss != null ) {
				dokumen = nibOss;
			}
			else if(aktaPendirian != null) {
				dokumen = aktaPendirian;
			}
			else if(lampiranSuratArahan != null) {
				dokumen = lampiranSuratArahan;
			}
			else if(rekomendasiUKLUPL != null) {
				dokumen = rekomendasiUKLUPL;
			}
			else if(rekomendasiDPLH != null) {
				dokumen = rekomendasiDPLH;
			}
			
			registerDokumen = new RegisterDokumen(
					d.getId(), 
					dokumen, 
					convertRegisterPerusahaanDataToRegisterPerusahaanWithOutRegisterDokumen(d.getPerusahaanData()), 
					d.getLokasiFile(), 
					convertStatusDokumenDataToStatusDokumen(d.getStatusDokumen()),
					d.getTanggalRegistrasi(), 
					convertAutorisasiDataToAuthority(d.getUploader()),
					d.getStatusVerified()
					);
		}
		
		return registerDokumen;
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
	
	public RegisterPerusahaan convertRegisterPerusahaanDataToRegisterPerusahaan(RegisterPerusahaanData d) {
		RegisterPerusahaan registerPerusahaan = null; 	
		
		if( d != null) {
			List<RegisterDokumen> daftarRegisterDokumen = convertDaftarRegisterDokumenDataToDaftarRegisterDokumenWithOutPerusahaan(d.getDaftarRegisterDokumenData());
			
			Perusahaan perusahaan = new Perusahaan(
					d.getNpwp(), 
					d.getNama(), 
					convertModelPerizinanDataToModelPerizinan(d.getModelPerizinanData()), 
					convertSkalaUsahaDataToSkalaUsaha(d.getSkalaUsahaData()), 
					convertPelakuUsahaDataToPelakuUsaha(d.getPelakuUsaha()), 
					convertAlamatDataToAlamat(d.getAlamatPerusahaanData()), 
					convertKontakDataToKontak(d.getKontakPerusahaanData()), 
					daftarRegisterDokumen
					);
			
			registerPerusahaan = new RegisterPerusahaan(
					d.getId(), 
					d.getTanggalRegistrasi(), 
					convertAutorisasiDataToAuthority(d.getKreator()), 
					convertAutorisasiDataToAuthority(d.getVerifikator()), 
					perusahaan,
					d.getStatusVerifikasi()
					);
		}
		
		return registerPerusahaan;
	}
	
	public RegisterPerusahaan convertRegisterPerusahaanDataToRegisterPerusahaanWithOutRegisterDokumen(RegisterPerusahaanData d) {
		RegisterPerusahaan registerPerusahaan = null; 		
		
		if( d != null) {
			Perusahaan perusahaan = new Perusahaan(
					d.getNpwp(), 
					d.getNama(), 
					convertModelPerizinanDataToModelPerizinan(d.getModelPerizinanData()), 
					convertSkalaUsahaDataToSkalaUsaha(d.getSkalaUsahaData()), 
					convertPelakuUsahaDataToPelakuUsaha(d.getPelakuUsaha()), 
					convertAlamatDataToAlamat(d.getAlamatPerusahaanData()), 
					convertKontakDataToKontak(d.getKontakPerusahaanData()), 
					null
					);
			
			registerPerusahaan = new RegisterPerusahaan(
					d.getId(), 
					d.getTanggalRegistrasi(), 
					convertAutorisasiDataToAuthority(d.getKreator()), 
					convertAutorisasiDataToAuthority(d.getVerifikator()), 
					perusahaan,
					d.getStatusVerifikasi()
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
		
	public Jabatan convertJabatanDataToJabatan(JabatanData d) {
		Jabatan jabatan = null;
		
		if(d != null) {
			jabatan = new Jabatan(d.getId(), d.getNama());
		}
		
		return jabatan;
	}
	
	public Pegawai convertPegawaiPerusahaanDataToPegawaiPerusahaan(PegawaiPerusahaanData d) {
		Pegawai pegawai = null;
		
		if(d != null) {
			pegawai = new Pegawai(
					d.getId(), 
					convertRegisterPerusahaanDataToRegisterPerusahaanWithOutRegisterDokumen(d.getRegisterPerusahaanData()), 
					convertPersonDataToPerson(d.getPersonData()), 
					convertJabatanDataToJabatan(d.getJabatanData())
					);
		}
		
		return pegawai;		
	}
	
	public KategoriPermohonan convertKategoriPermohonanDataToKategoriPermohonan(KategoriPermohonanData d) {
		KategoriPermohonan kategoriPermohonan = null;
		
		if(d != null) {
			kategoriPermohonan = new KategoriPermohonan(d.getId(), d.getNama());
		}
		
		return kategoriPermohonan;
	}
	
	public StatusWali convertKategoriPengurusPermohonanDataToStatusWali(KategoriPengurusPermohonanData d) {
		StatusWali statusWali = null;
		
		if(d != null) {
			statusWali = new StatusWali(d.getId(), d.getNama());
		}
		
		return statusWali;
	}
	
	public PosisiTahapPemberkasan convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(PosisiTahapPemberkasanData d) {
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
	
	public List<RegisterDokumen> convertDaftarDokumenPersyaratanPermohonanToDaftarRegisterDokumen(List<DokumenPersyaratanPermohonanData> d) {
		List<RegisterDokumen> daftarRegisterDokumen = new ArrayList<RegisterDokumen>();
		Iterator<DokumenPersyaratanPermohonanData> iter = d.iterator();
		
		DokumenPersyaratanPermohonanData dokumenPersyaratanPermohonanData = null;
		RegisterDokumen item;
		while (iter.hasNext()) {
			dokumenPersyaratanPermohonanData = (DokumenPersyaratanPermohonanData) iter.next();					
			item = convertRegisterDokumenDataToRegisterDokumenWithOutPerusahaan(dokumenPersyaratanPermohonanData.getRegisterDokumen());
			daftarRegisterDokumen.add(item);
		}
		
		return daftarRegisterDokumen;
	}
	
	public RegisterPermohonan convertRegisterPermohonanDataToRegisterPermohonan(RegisterPermohonanData d) {
		RegisterPermohonan registerPermohonan = null;
		
		if(d != null) {			
			registerPermohonan = new RegisterPermohonan(
										d.getId(), 
										convertKategoriPermohonanDataToKategoriPermohonan(d.getKategoriPermohonanData()), 
										d.getTanggalRegistrasi(), 
										convertRegisterPerusahaanDataToRegisterPerusahaanWithOutRegisterDokumen(d.getPerusahaanData()), 
										convertAutorisasiDataToAuthority(d.getAutorisasiData()), 
										convertKategoriPengurusPermohonanDataToStatusWali(d.getKategoriPengurusPermohonanData()), 
										convertPegawaiPerusahaanDataToPegawaiPerusahaan(d.getPenanggungJawab()),
										convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(d.getPosisiTahapPemberkasanData()), 
										convertDaftarDokumenPersyaratanPermohonanToDaftarRegisterDokumen(d.getDaftarDokumenSyarat()), 
										null
										);
		}
		
		return registerPermohonan;
		
	}

	public KategoriFlowLog convertKategoriFlowLogDataToKategoriFlowLog(KategoriLogData d) {
		KategoriFlowLog kategoriFlowLog = null;
		
		if(d != null) {			
			kategoriFlowLog = new KategoriFlowLog(d.getNama(), d.getNama());
		}
		
		return kategoriFlowLog;
		
	}
	
	public FlowLog convertFlowLogDataToFlowLog(FlowLogData d) {
		FlowLog flowLog = null;
		
		if(d != null) {
			KategoriFlowLog kategoriFlowLog = convertKategoriFlowLogDataToKategoriFlowLog(d.getKategoriLogData());
			
			if(kategoriFlowLog.getId().equals("1")) {
				flowLog = new FlowLogPermohonan(
						d.getId(), 
						d.getTanggal(), 
						kategoriFlowLog, 
						convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(d.getPosisiTahapPemberkasanData()), 
						d.getKeterangan(), 
						convertAutorisasiDataToAuthority(d.getPengaksesData()), 
						convertRegisterPermohonanDataToRegisterPermohonan(d.getFlowLogPermohonanData().getRegisterPermohonan())
						);
			}
			
			
		}
		
		return flowLog;	
	}

	/*-----------Converter Object To ObjectData-----------------------------------------------*/
	
	public JabatanData convertJabatanToJabatanData(Jabatan t) {
		JabatanData jabatanData = null;
		
		if(t != null) {
			jabatanData = new JabatanData();
			jabatanData.setId(t.getId());
			jabatanData.setNama(t.getNama());
		}
		
		return jabatanData;
	}
	
	public PropinsiData convertPropinsiToPropinsiData(Propinsi t) {
		PropinsiData propinsiData = null;
		
		if(t != null) {
			propinsiData = new PropinsiData();
			propinsiData.setId(t.getId());
			propinsiData.setNama(t.getNama());
		}
		
		return propinsiData;
	}
	
	public KabupatenData convertKabupatenToKabupatenData(Kabupaten t) {
		KabupatenData kabupatenData = null;
		
		if(t != null) {
			kabupatenData = new KabupatenData();
			kabupatenData.setId(t.getId());
			kabupatenData.setNama(t.getNama());
		}
		
		return kabupatenData;
	}
	
	public KecamatanData convertKecamatanToKecamatanData(Kecamatan t) {
		KecamatanData kecamatanData = null;
		
		if(t != null) {
			kecamatanData = new KecamatanData();
			kecamatanData.setId(t.getId());
			kecamatanData.setNama(t.getNama());
		}
		
		return kecamatanData;
	}
	
	public DesaData convertDesaToDesaData(Desa t) {
		DesaData desaData = null;
		
		if(t != null) {
			desaData = new DesaData();
			desaData.setId(t.getId());
			desaData.setNama(t.getNama());
		}
		
		return desaData;
	}
	
	public AlamatData convertAlamatToAlamatData(Alamat t) {
		AlamatData alamatData = null;
		
		if(t != null) {
			alamatData = new AlamatData();
			alamatData.setPropinsi(convertPropinsiToPropinsiData(t.getPropinsi()));
			alamatData.setKabupaten(convertKabupatenToKabupatenData(t.getKabupaten()));
			alamatData.setKecamatan(convertKecamatanToKecamatanData(t.getKecamatan()));
			alamatData.setDesa(convertDesaToDesaData(t.getDesa()));
			alamatData.setDetailAlamat(t.getKeterangan());
		}
		
		return alamatData;
	}
	
	public JenisKelaminData convertJenisKelaminToJenisKelaminData(JenisKelamin t) {
		JenisKelaminData jenisKelaminData = null;
		
		if(t != null) {
			jenisKelaminData = new JenisKelaminData();
			jenisKelaminData.setId(t.getId());
			jenisKelaminData.setNama(t.getNama());
		}
		
		return jenisKelaminData;
	}
	
	public KontakData convertKontakToKontakData(Kontak t) {
		KontakData kontakData = null;
		
		if(t != null) {
			kontakData = new KontakData();
			kontakData.setEmail(t.getEmail());
			kontakData.setFax(t.getFax());
			kontakData.setTelepone(t.getTelepone());
		}
		
		return kontakData;
	}
	
	public PersonData convertPersonToPersonData(Person t) {
		PersonData personData = null;
		
		if(t != null) {
			personData = new PersonData();
			personData.setId(t.getNik());
			personData.setNama(t.getNama());
			personData.setAlamat(convertAlamatToAlamatData(t.getAlamat()));
			personData.setScanKtp(t.getScanKTP());
			personData.setSex(convertJenisKelaminToJenisKelaminData(t.getSex()));
			personData.setKontak(convertKontakToKontakData(t.getKontak()));
		}
		
		return personData;
	}
	
	public ModelPerizinanData convertModelPerizinanToModelPerizinanData(ModelPerizinan t) {
		ModelPerizinanData modelPerizinanData = null;
		
		if(t != null) {
			modelPerizinanData = new ModelPerizinanData();
			modelPerizinanData.setId(t.getId());
			modelPerizinanData.setNama(t.getNama());
			modelPerizinanData.setSingkatan(t.getSingkatan());
		}
		
		return modelPerizinanData;
	}
	
	public SkalaUsahaData convertSkalaUsahaToSkalaUsahaData(SkalaUsaha t) {
		SkalaUsahaData skalaUsahaData = null;
		
		if(t != null) {
			skalaUsahaData = new SkalaUsahaData();
			skalaUsahaData.setId(t.getId());
			skalaUsahaData.setNama(t.getNama());
			skalaUsahaData.setSingkatan(t.getSingkatan());
		}
		
		return skalaUsahaData;
	}
	
	public KategoriPelakuUsahaData convertKategoriPelakuUsahaToKategoriPelakuUsahaData(KategoriPelakuUsaha t) {
		KategoriPelakuUsahaData kategoriPelakuUsahaData = null;
		
		if(t != null) {
			kategoriPelakuUsahaData = new KategoriPelakuUsahaData();
			kategoriPelakuUsahaData.setId(t.getId());
			kategoriPelakuUsahaData.setNama(t.getNama());
		}
		
		return kategoriPelakuUsahaData;
	}
	
	public PelakuUsahaData convertKategoriPelakuUsahaToKategoriPelakuUsahaData(PelakuUsaha t) {
		PelakuUsahaData pelakuUsahaData = null;
		
		if(t != null) {
			pelakuUsahaData = new PelakuUsahaData();
			pelakuUsahaData.setId(t.getId());
			pelakuUsahaData.setNama(t.getNama());
			pelakuUsahaData.setSingkatan(t.getSingkatan());	
			pelakuUsahaData.setKategoriPelakuUsaha(convertKategoriPelakuUsahaToKategoriPelakuUsahaData(t.getKategoriPelakuUsaha()));
		}
		
		return pelakuUsahaData;
	}
	
	public HakAksesData convertHakAksesToHakAksesData(HakAkses t) {
		HakAksesData hakAksesData =  null;
		
		if(t != null) {
			hakAksesData = new HakAksesData();
			hakAksesData.setId(t.getId());
			hakAksesData.setNama(t.getNama());
			hakAksesData.setKeterangan(t.getKeterangan());
		}
		
		return hakAksesData;
	}
	
	public AutorisasiData convertAuthorityToAutorisasiData(Authority t) {
		AutorisasiData autorisasiData = null;
		
		if(t != null) {
			autorisasiData = new AutorisasiData();
			autorisasiData.setId(t.getId());
			autorisasiData.setUserName(t.getUserName());
			autorisasiData.setPerson(convertPersonToPersonData(t.getPerson()));
			autorisasiData.setHakAkses(convertHakAksesToHakAksesData(t.getHakAkses()));
			autorisasiData.setStatusInternal(t.isStatusInternal());
			autorisasiData.setIsVerified(t.isVerified());
		}
		
		return autorisasiData;
	}
	
	public RegisterPerusahaanData convertRegisterPerusahaanToRegisterPerusahaanData(RegisterPerusahaan t) {
		RegisterPerusahaanData registerPerusahaanData = null;
		
		if(t != null) {
			String id = t.getId();
			registerPerusahaanData = new RegisterPerusahaanData();
			registerPerusahaanData.setId(id != null ? id : getGenerateIdRegisterPerusahaan());
			registerPerusahaanData.setNama(t.getPerusahaan().getNama());
			registerPerusahaanData.setAlamatPerusahaanData(convertAlamatToAlamatData(t.getPerusahaan().getAlamat()));
			registerPerusahaanData.setModelPerizinanData(convertModelPerizinanToModelPerizinanData(t.getPerusahaan().getModelPerizinan()));
			registerPerusahaanData.setSkalaUsahaData(convertSkalaUsahaToSkalaUsahaData(t.getPerusahaan().getSkalaUsaha()));
			registerPerusahaanData.setPelakuUsaha(convertKategoriPelakuUsahaToKategoriPelakuUsahaData(t.getPerusahaan().getPelakuUsaha()));
			registerPerusahaanData.setStatusVerifikasi(t.getStatusVerifikasi());
			registerPerusahaanData.setKontakPerusahaanData(convertKontakToKontakData(t.getPerusahaan().getKontak()));
			registerPerusahaanData.setKreator(convertAuthorityToAutorisasiData(t.getKreator()));
			registerPerusahaanData.setVerifikator(convertAuthorityToAutorisasiData(t.getVerifikator()));
			registerPerusahaanData.setTanggalRegistrasi(t.getTanggalRegistrasi());
			registerPerusahaanData.setNpwp(t.getPerusahaan().getId());			
		}
		
		return registerPerusahaanData;
	}
	
	public PegawaiPerusahaanData convertPegawaiPerusahaanToPegawaiPerusahaanData(Pegawai t) {
		PegawaiPerusahaanData pegawaiPerusahaanData = new PegawaiPerusahaanData();
		
		if(t != null) {
			String id = t.getId();
			pegawaiPerusahaanData.setId(id != null ? id: getGenerateIdPegawaiPerusahaan(t.getPerusahaan().getId(), t.getJabatan().getId(), t.getPerson().getNik()));
			pegawaiPerusahaanData.setPersonData(convertPersonToPersonData(t.getPerson()));
			pegawaiPerusahaanData.setRegisterPerusahaanData(convertRegisterPerusahaanToRegisterPerusahaanData(t.getPerusahaan()));
			pegawaiPerusahaanData.setJabatanData(convertJabatanToJabatanData(t.getJabatan()));			
		}
		
		return pegawaiPerusahaanData;
	}
	
	public StatusDokumenData convertStatusDokumenToStatusDokumenData(StatusDokumen t) {
		StatusDokumenData statusDokumenData =  null;
		
		if(t != null) {
			statusDokumenData = new StatusDokumenData();
			statusDokumenData.setId(t.getId());
			statusDokumenData.setNama(t.getNama());
		}
		
		return statusDokumenData;
	}
		
	public SuratArahanData convertSuratArahanToSuratArahanData(SuratArahan t, String idRegisterDokumen) {
		SuratArahanData suratArahanData = null;
		
		if(t != null) {
			suratArahanData = new SuratArahanData();
			suratArahanData.setId(idRegisterDokumen);
			suratArahanData.setNoSurat(t.getNoSurat());
			suratArahanData.setTanggalSurat(t.getTanggalSurat());
			suratArahanData.setPerihalSurat(t.getPerihalSurat());
			suratArahanData.setUraianKegiatan(t.getUraianKegiatan());
		}
		
		return suratArahanData;
	}
	
	public LampiranSuratArahanData convertLampiranSuratArahanToLampiranSuratArahanData(LampiranSuratArahan t, String idRegisterDokumen) {
		LampiranSuratArahanData lampiranSuratArahanData = null;
		
		if(t != null) {
			lampiranSuratArahanData = new LampiranSuratArahanData();
			lampiranSuratArahanData.setId(idRegisterDokumen);
			lampiranSuratArahanData.setNoSurat(t.getNoSuratArahan());
			lampiranSuratArahanData.setTanggalSurat(t.getTanggalSuratArahan());
		}
		
		return lampiranSuratArahanData;
	}
	
	public AktaPendirianData convertAktaPendirianToAktaPendirianData(AktaPendirian t, String idRegisterDokumen) {
		AktaPendirianData aktaPendirianData = null;
		
		if(t != null) {
			aktaPendirianData = new AktaPendirianData();
			aktaPendirianData.setId(idRegisterDokumen);
			aktaPendirianData.setNomor(t.getNomor());
			aktaPendirianData.setTanggal(t.getTanggal());
			aktaPendirianData.setNotaris(t.getNamaNotaris());
			aktaPendirianData.setPenanggungJawabData(convertPegawaiPerusahaanToPegawaiPerusahaanData(t.getPenanggungJawab()));			
		}
		
		return aktaPendirianData;
	}
	
	public RekomendasiUKLUPLData convertRekomendasiUKLUPLToRekomendasiUKLUPLData(RekomendasiUKLUPL t, String idRegisterDokumen) {
		RekomendasiUKLUPLData rekomendasiUKLUPLData = null;
		
		if(t != null) {
			rekomendasiUKLUPLData = new RekomendasiUKLUPLData();
			rekomendasiUKLUPLData.setId(idRegisterDokumen);
			rekomendasiUKLUPLData.setNoSurat(t.getNomor());
			rekomendasiUKLUPLData.setTanggalSurat(t.getTanggal());
			rekomendasiUKLUPLData.setPerihalSurat(t.getPerihal());			
		}
		
		return rekomendasiUKLUPLData;
	}
	
	public RekomendasiDPLHData convertRekomendasiDPLHToRekomendasiDPLHData(RekomendasiDPLH t, String idRegisterDokumen) {
		RekomendasiDPLHData rekomendasiDPLHData = null;
		
		if(t != null) {
			rekomendasiDPLHData = new RekomendasiDPLHData();
			rekomendasiDPLHData.setId(idRegisterDokumen);
			rekomendasiDPLHData.setNoSurat(t.getNomor());
			rekomendasiDPLHData.setTanggalSurat(t.getTanggal());
			rekomendasiDPLHData.setPerihalSurat(t.getPerihal());			
		}
		
		return rekomendasiDPLHData;
	}
	
	public RegisterKbliData convertRegisterKbliToRegisterKbliData(RegisterKbli t) {
		RegisterKbliData registerKbliData = null;
		
		if(t != null) {
			registerKbliData = new RegisterKbliData();
			NibOssData nibOssData = new NibOssData();
			nibOssData.setNomor(t.getIdNib());
			registerKbliData.setNib(nibOssData);
			
			Kbli2020Data kbli2020Data = new Kbli2020Data();
			kbli2020Data.setId(t.getIdKbli());			
			registerKbliData.setKbli(kbli2020Data);
			
		}
		
		return registerKbliData;
	}
	
	public List<RegisterKbliData> convertDaftarRegisterKbliToDaftarRegisterKbliData(List<RegisterKbli> t) {
		List<RegisterKbliData> daftarRegisterKbliData = null;
		
		if(t != null) {
			daftarRegisterKbliData = new ArrayList<RegisterKbliData>();
			for(RegisterKbli item : t) {		
				RegisterKbliData registerKbliData = convertRegisterKbliToRegisterKbliData(item);
				if(registerKbliData != null) {
					daftarRegisterKbliData.add(registerKbliData);
				}				
			}
		}
		
		return daftarRegisterKbliData;
	}
	
	public NibOssData convertNibOssToNibOssData(NibOss t, String idRegisterDokumen) {
		NibOssData nibOssData = null;
		
		if(t != null) {
			List<RegisterKbliData> daftarRegisterKbliData = convertDaftarRegisterKbliToDaftarRegisterKbliData(t.getDaftarKbli());
			nibOssData = new NibOssData();
			nibOssData.setNomor(t.getNomor());
			nibOssData.setTanggalPenetapan(t.getTanggal());
			nibOssData.setDaftarKbli(daftarRegisterKbliData);
		}
		
		return nibOssData;
	}
		
	public RegisterDokumenData convertRegisterDokumenToRegisterDokumenData(RegisterDokumen t) {
		RegisterDokumenData registerDokumenData = null;
		
		if(t != null) {
			Dokumen dokumen = t.getDokumen();
			String id = t.getId();
			
			registerDokumenData = new RegisterDokumenData();
			registerDokumenData.setId(id != null ? id : getGenerateIdRegisterDokumen());
			registerDokumenData.setPerusahaanData(convertRegisterPerusahaanToRegisterPerusahaanData(t.getPerusahaan()));
			
			MasterDokumenData masterDokumenData = new MasterDokumenData();
			masterDokumenData.setId(dokumen.getId());
			masterDokumenData.setNama(dokumen.getNama());
			registerDokumenData.setDokumenData(masterDokumenData);
			
			registerDokumenData.setTanggalRegistrasi(t.getTanggalRegistrasi());
			registerDokumenData.setUploader(convertAuthorityToAutorisasiData(t.getUploader()));
			registerDokumenData.setLokasiFile(t.getLokasiFile());
			registerDokumenData.setStatusDokumen(convertStatusDokumenToStatusDokumenData(t.getStatusDokumen()));
			registerDokumenData.setStatusVerified(t.getStatusVerified());
			
			if(dokumen instanceof SuratArahan) {
				registerDokumenData.setSuratArahanData(convertSuratArahanToSuratArahanData((SuratArahan) dokumen, registerDokumenData.getId()));
			}
			else if(dokumen instanceof LampiranSuratArahan) {
				registerDokumenData.setLampiranSuratArahanData(convertLampiranSuratArahanToLampiranSuratArahanData((LampiranSuratArahan) dokumen, registerDokumenData.getId()));
			}
			else if(dokumen instanceof AktaPendirian) {
				registerDokumenData.setAktaPendirianData(convertAktaPendirianToAktaPendirianData((AktaPendirian) dokumen, registerDokumenData.getId()));
			}
			else if(dokumen instanceof RekomendasiUKLUPL) {
				registerDokumenData.setRekomendasiUKLUPLData(convertRekomendasiUKLUPLToRekomendasiUKLUPLData((RekomendasiUKLUPL) dokumen, registerDokumenData.getId()));
			}
			else if(dokumen instanceof RekomendasiDPLH) {
				registerDokumenData.setRekomendasiDPLHData(convertRekomendasiDPLHToRekomendasiDPLHData((RekomendasiDPLH) dokumen, registerDokumenData.getId()));
			}
			else if(dokumen instanceof NibOss) {
				registerDokumenData.setNibOssData(null);
			}
			
		}
		
		return registerDokumenData;
	}
		
	public KategoriPermohonanData convertKategoriPermohonanToKategoriPermohonanData(KategoriPermohonan t) {
		KategoriPermohonanData kategoriPermohonanData = null;
		
		if(t != null) {
			kategoriPermohonanData = new KategoriPermohonanData();
			kategoriPermohonanData.setId(t.getId());
			kategoriPermohonanData.setNama(t.getNama());
		}
		
		return kategoriPermohonanData;
	}
	
	public KategoriPengurusPermohonanData convertStatusWaliToKategoriPengurusPermohonanData(StatusWali t) {
		KategoriPengurusPermohonanData KategoriPengurusPermohonanData = null;
		
		if(t != null) {
			KategoriPengurusPermohonanData = new KategoriPengurusPermohonanData();
			KategoriPengurusPermohonanData.setId(t.getId());
			KategoriPengurusPermohonanData.setNama(t.getNama());
		}
		
		return KategoriPengurusPermohonanData;
	}
	
	public PosisiTahapPemberkasanData convertStatusTahapPemberkasanToStatusTahapPemberkasanData(PosisiTahapPemberkasan t) {
		PosisiTahapPemberkasanData posisiTahapPemberkasanData = null;
		
		if(t != null) {
			posisiTahapPemberkasanData = new PosisiTahapPemberkasanData();
			posisiTahapPemberkasanData.setId(t.getId());
			posisiTahapPemberkasanData.setNama(t.getNama());
			posisiTahapPemberkasanData.setKeterangan(t.getKeterangan());
		}
		
		return posisiTahapPemberkasanData;
	}
	
	public List<DokumenPersyaratanPermohonanData> convertDaftarRegisterDokumenToDaftarDokumenPersyaratanData(List<RegisterDokumen> t, RegisterPermohonanData registerPermohonanData) {
		List<DokumenPersyaratanPermohonanData> daftarDokumenPersyaratanPermohonan = null;
		
		if(t != null) {
			daftarDokumenPersyaratanPermohonan = new ArrayList<DokumenPersyaratanPermohonanData>();
			for(RegisterDokumen item : t) {		
				RegisterDokumenData registerDokumenData = convertRegisterDokumenToRegisterDokumenData(item);
				if(registerDokumenData != null) {
					DokumenPersyaratanPermohonanData dokumenPersyaratanPermohonanData = new DokumenPersyaratanPermohonanData();
					dokumenPersyaratanPermohonanData.setRegisterDokumen(registerDokumenData);
					dokumenPersyaratanPermohonanData.setRegisterPermohonan(registerPermohonanData);
					daftarDokumenPersyaratanPermohonan.add(dokumenPersyaratanPermohonanData);
				}				
			}
			
		}
		
		return daftarDokumenPersyaratanPermohonan;
	}
		
	public RegisterPermohonanData convertRegisterPermohonanToRegisterPermohonanData(RegisterPermohonan t) {
		RegisterPermohonanData registerPermohonanData = null;
		
		if(t != null) {
			String id = t.getId();
			registerPermohonanData = new RegisterPermohonanData();
			registerPermohonanData.setId(id != null ? id : getGenerateIdRegisterPermohonan());
			registerPermohonanData.setKategoriPermohonanData(
					convertKategoriPermohonanToKategoriPermohonanData(t.getKategoriPermohonan()));			
			registerPermohonanData.setTanggalRegistrasi(t.getTanggalRegistrasi());
			registerPermohonanData.setPerusahaanData(convertRegisterPerusahaanToRegisterPerusahaanData(t.getPerusahaan()));
			registerPermohonanData.setAutorisasiData(convertAuthorityToAutorisasiData(t.getPengurusPermohonan()));
			registerPermohonanData.setKategoriPengurusPermohonanData(
					convertStatusWaliToKategoriPengurusPermohonanData(t.getStatusWaliPengurusPermohonan()));
			registerPermohonanData.setPosisiTahapPemberkasanData(
					convertStatusTahapPemberkasanToStatusTahapPemberkasanData(t.getPosisiBerkas()));	
			registerPermohonanData.setPenanggungJawab(convertPegawaiPerusahaanToPegawaiPerusahaanData(t.getPenanggungJawabPermohonan()));			
			registerPermohonanData.setDaftarDokumenSyarat(
					convertDaftarRegisterDokumenToDaftarDokumenPersyaratanData(t.getDaftarDokumenSyarat(), registerPermohonanData)
					);
		}
		
		return registerPermohonanData;
	}
	
	public KategoriLogData convertKategoriFlowLogToKategoriFlowLogData(KategoriFlowLog t) {
		KategoriLogData kategoriLogData = null;
		
		if(t != null) {			
			kategoriLogData = new KategoriLogData();
			kategoriLogData.setId(t.getId());
			kategoriLogData.setNama(t.getNama());
		}
		
		return kategoriLogData;		
	}
	
	public FlowLogData convertFlowLogToFlowLogData(FlowLog t) {
		FlowLogData flowLogData = new FlowLogData();
		
		if(t != null) {
			String id = t.getId();
			KategoriLogData kategoriLogData = convertKategoriFlowLogToKategoriFlowLogData(t.getKategoriFlowLog());
			
			if(kategoriLogData.getId().equals("1")) {
				FlowLogPermohonan flowLogPermohonan = (FlowLogPermohonan) t;
				flowLogData = new FlowLogData();
				flowLogData.setId(id != null ? id : getGenerateIdFlowLogData());
				flowLogData.setTanggal(flowLogPermohonan.getTanggal());
				flowLogData.setKategoriLogData(kategoriLogData);
				flowLogData.setPosisiTahapPemberkasanData(
						convertStatusTahapPemberkasanToStatusTahapPemberkasanData(flowLogPermohonan.getPosisiTahapPemberkasan()));
				flowLogData.setKeterangan(flowLogPermohonan.getKeterangan());
				flowLogData.setPengaksesData(convertAuthorityToAutorisasiData(flowLogPermohonan.getPengakses()));
				FlowLogPermohonanData flowLogPermohonanData = new FlowLogPermohonanData();
				flowLogPermohonanData.setFlowLog(flowLogData);
				flowLogPermohonanData.setRegisterPermohonan(convertRegisterPermohonanToRegisterPermohonanData(flowLogPermohonan.getRegisterPermohonan()));
				flowLogData.setFlowLogPermohonanData(flowLogPermohonanData);
			}
		}
		
		return flowLogData;
	}
		
	/*----------id generator function---------------*/
	private String getGenerateIdRegisterPerusahaan() {
		int tahun = LocalDate.now().getYear();
		String hasil;
		
		Query q = entityManager.createQuery("SELECT MAX(p.id) "
				+ "FROM RegisterPerusahaanData p "
				+ "WHERE EXTRACT(YEAR FROM p.tanggalRegistrasi) = :tahun");
		
		q.setParameter("tahun", tahun);
		
		try {
			hasil = (String) q.getSingleResult();
			hasil = hasil.substring(0, 4);
			Long idBaru = Long.valueOf(hasil)  + 1;
			hasil = LPad(Long.toString(idBaru), 4, '0');
			return hasil.concat(Integer.toString(tahun));
		} catch (Exception e) {			
			hasil = "0001";			
			return hasil.concat(Integer.toString(tahun));
		}		
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
	
	private String getGenerateIdPegawaiPerusahaan(String idRegisterPerusahaan, String idJabatan, String nik) {
		return idRegisterPerusahaan.concat(idJabatan).concat(nik);
	}
	
	private String getGenerateIdFlowLogData() {
		int tahun = LocalDate.now().getYear();
		String hasil;
		
		Query q = entityManager.createQuery("SELECT MAX(fl.id) "
				+ "FROM FlowLogData fl "
				+ "WHERE EXTRACT(YEAR FROM fl.tanggal) = :tahun");
		
		q.setParameter("tahun", tahun);
		
		try {
			hasil = (String) q.getSingleResult();
			hasil = hasil.substring(0, 7);
			Long idBaru = Long.valueOf(hasil)  + 1;
			hasil = LPad(Long.toString(idBaru), 7, '0');
			return hasil.concat(Integer.toString(tahun));
		} catch (Exception e) {			
			hasil = "0000001";			
			return hasil.concat(Integer.toString(tahun));
		}		
	}
	
	private String LPad(String str, Integer length, char car) {
		  return (String.format("%" + length + "s", "").replace(" ", String.valueOf(car)) + str).substring(str.length(), length + str.length());
	}
	
}
