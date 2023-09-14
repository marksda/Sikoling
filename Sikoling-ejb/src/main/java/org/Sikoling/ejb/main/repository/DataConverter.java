package org.Sikoling.ejb.main.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Otoritas;
import org.Sikoling.ejb.abstraction.entity.OtoritasPerusahaan;
import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.HakAkses;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Kontak;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.RegisterKbli;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.SkalaUsaha;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.entity.dokumen.DokumenAktaPendirian;
import org.Sikoling.ejb.abstraction.entity.dokumen.DokumenGenerik;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.KategoriDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.Kbli;
import org.Sikoling.ejb.abstraction.entity.dokumen.LampiranSuratArahan;
import org.Sikoling.ejb.abstraction.entity.dokumen.DokumenNibOss;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiDPLH;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiUKLUPL;
import org.Sikoling.ejb.abstraction.entity.dokumen.StatusDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.SuratArahan;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;
import org.Sikoling.ejb.abstraction.entity.log.FlowLogPermohonan;
import org.Sikoling.ejb.abstraction.entity.log.KategoriFlowLog;
import org.Sikoling.ejb.abstraction.entity.log.StatusFlowLog;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonanSuratArahan;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonan;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonanArahan;
import org.Sikoling.ejb.abstraction.entity.permohonan.StatuswaliPermohonan;
import org.Sikoling.ejb.main.repository.alamat.AlamatData;
import org.Sikoling.ejb.main.repository.bidangusaha.BidangUsahaData;
import org.Sikoling.ejb.main.repository.desa.DesaData;
import org.Sikoling.ejb.main.repository.dokumen.AktaPendirianData;
import org.Sikoling.ejb.main.repository.dokumen.KategoriDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.KbliData;
import org.Sikoling.ejb.main.repository.dokumen.LampiranSuratArahanData;
import org.Sikoling.ejb.main.repository.dokumen.DokumenData;
import org.Sikoling.ejb.main.repository.dokumen.DokumenGenerikData;
import org.Sikoling.ejb.main.repository.dokumen.DokumenNibOssData;
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
import org.Sikoling.ejb.main.repository.kategoriproduk.KategoriProdukData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.kontak.KontakData;
import org.Sikoling.ejb.main.repository.log.FlowLogData;
import org.Sikoling.ejb.main.repository.log.FlowLogPermohonanData;
import org.Sikoling.ejb.main.repository.log.KategoriLogData;
import org.Sikoling.ejb.main.repository.log.StatusFlowLogData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.otoritas.OtoritasData;
import org.Sikoling.ejb.main.repository.otoritas.OtoritasPerusahaanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.penanggungjawab.PenanggungJawabData;
import org.Sikoling.ejb.main.repository.permohonan.DokumenPersyaratanPermohonanData;
import org.Sikoling.ejb.main.repository.permohonan.KategoriPermohonanData;
import org.Sikoling.ejb.main.repository.permohonan.KategoriPermohonanSuratArahanData;
import org.Sikoling.ejb.main.repository.permohonan.KategoriSuratArahanData;
import org.Sikoling.ejb.main.repository.permohonan.PosisiTahapPemberkasanData;
import org.Sikoling.ejb.main.repository.permohonan.RegisterPermohonanData;
import org.Sikoling.ejb.main.repository.permohonan.RegisterPermohonanSuratArahanData;
import org.Sikoling.ejb.main.repository.permohonan.StatusPengurusPermohonanData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.perusahaan.PegawaiData;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanData;
import org.Sikoling.ejb.main.repository.produk.ProdukData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;
import org.keycloak.representations.idm.UserRepresentation;

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
	
	public Otoritas convertAutorisasiDataToAutority(OtoritasData d) {
		Otoritas autority = null;
		
		if(d != null) {
			autority = new Otoritas(
					d.getId(), 
					d.getTanggalRegistrasi(),
					convertPersonDataToPerson(d.getPerson()), 
					convertHakAksesDataToHakAkses(d.getHakAkses()), 
					d.getStatusInternal(), 
					d.getIsVerified(), 
					d.getUserName()
					);
		}
		
		return autority;
	}
		
	public Desa convertDesaDataToDesa(DesaData d) {
		Desa desa = null;
		
		if(d != null) {
			desa = new Desa(
					d.getId(), 
					d.getNama(),
					d.getKecamatan() != null ? convertKecamatanDataToKecamatan(d.getKecamatan()):null
					);
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
			kabupaten = new Kabupaten(
					d.getId(), 
					d.getNama(),
					d.getPropinsi() != null ? convertPropinsiDataToPropinsi(d.getPropinsi()):null
					);
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
			kategoriPelakuUsaha = new KategoriPelakuUsaha(
					d.getId(), 
					d.getNama(), 
					convertSkalaUsahaDataToSkalaUsaha(d.getSkalaUsaha())
					);
		}
		
		return kategoriPelakuUsaha;
	}
	
	public Kecamatan convertKecamatanDataToKecamatan(KecamatanData d) {
		Kecamatan kecamatan = null;
		
		if(d != null) {
			kecamatan = new Kecamatan(
					d.getId(), 
					d.getNama(), 
					d.getKabupaten() != null ? convertKabupatenDataToKabupaten(d.getKabupaten()):null
					);
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
	
	public Dokumen convertMasterDokumenDataToMasterDokumen(DokumenData d) {
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
			registerKbli = new RegisterKbli(
					d.getNib(),
					convertKbliDataToKbli(d.getKbli())
					);
		}
		
		return registerKbli;
	}
	
	public Kbli convertKbliDataToKbli(KbliData d) {
		Kbli kbli = null;
		
		if(d != null) {
			kbli = new Kbli(d.getId(), d.getNama(), d.getKategori());
		}
		
		return kbli;
	}
		
	public DokumenNibOss convertDokumenNibOssDataToDokumenNibOss(Dokumen dok, DokumenNibOssData d) {
		DokumenNibOss dokumenNibOss = null;
		
		if(d != null) {
			List<RegisterKbliData> daftarRegisterKbli = d.getDaftarRegisterKbli();
			List<Kbli> daftarKbli = new ArrayList<Kbli>();		
			for(RegisterKbliData item : daftarRegisterKbli) {		
				Kbli kbli = convertKbliDataToKbli(item.getKbli());
				if(kbli != null) {
					daftarKbli.add(kbli);
				}				
			}
			
			dokumenNibOss = new DokumenNibOss(
					dok.getId(), 
					dok.getNama(), 
					dok.getKategoriDokumen(),
					d.getNib(), 
					d.getTanggalPenetapan(), 
					daftarKbli
					);					
		}
		
		return dokumenNibOss;
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
			
	public DokumenAktaPendirian convertAktaPendirianDataToAktaPendirian(Dokumen dok, AktaPendirianData d) {
		DokumenAktaPendirian aktaPendirian = null;
		
		if(d != null) {
			aktaPendirian = new DokumenAktaPendirian(
					dok.getId(), 
					dok.getNama(), 
					dok.getKategoriDokumen(),
					d.getNomor(), 
					d.getTanggal(), 
					d.getNotaris(), 
					convertPegawaiDataToPegawai(d.getPenanggungJawabData())
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
	
	public DokumenGenerik convertDokumenGenerikDataToDokumenGenerik(Dokumen dok, DokumenGenerikData d){
		DokumenGenerik dokumenGenerik = null;
		
		if(d != null) {			
			dokumenGenerik = new DokumenGenerik(
					dok.getId(), 
					dok.getNama(), 
					dok.getKategoriDokumen(),
					d.getNomor(), 
					d.getTanggalPenetapan()
					);					
		}
		
		return dokumenGenerik;
	}
	
	public RegisterDokumen convertRegisterDokumenDataToRegisterDokumenWithOutPerusahaan(RegisterDokumenData d) {
		RegisterDokumen registerDokumen = null;
		
		if(d != null) {			
			Dokumen dokumen = convertMasterDokumenDataToMasterDokumen(d.getDokumenData());	
						
			if(d.getAktaPendirianData() != null) {
				dokumen = convertAktaPendirianDataToAktaPendirian(dokumen, d.getAktaPendirianData());
			}
			else if(d.getNibOssData() != null ) {
				dokumen = convertDokumenNibOssDataToDokumenNibOss(dokumen, d.getNibOssData());
			}
			else if(d.getGenerikData() != null) {
				dokumen = convertDokumenGenerikDataToDokumenGenerik(dokumen, d.getGenerikData());
			}
			else if(d.getSuratArahanData() != null) {
				dokumen = convertSuratArahanDataToSuratArahan(dokumen, d.getSuratArahanData());;
			}
			else if(d.getLampiranSuratArahanData() != null) {
				dokumen = convertLampiranSuratArahanDataToLampiranSuratArahan(dokumen, d.getLampiranSuratArahanData());
			}
			else if(d.getRekomendasiUKLUPLData() != null) {
				dokumen = convertRekomendasiUKLUPLDataToRekomendasiUKLUPL(dokumen, d.getRekomendasiUKLUPLData());
			}
			else if(d.getRekomendasiDPLHData() != null) {
				dokumen = convertRekomendasiDPLHDataToRekomendasiDPLH(dokumen, d.getRekomendasiDPLHData());
			}
			
			registerDokumen = new RegisterDokumen(
					d.getId(), 
					dokumen, 
					null, 
					d.getLokasiFile(), 
					convertStatusDokumenDataToStatusDokumen(d.getStatusDokumen()),
					d.getTanggalRegistrasi(), 
					convertAutorisasiDataToAutority(d.getUploader()),
					d.getStatusVerified()
					);
		}		
		
		return registerDokumen;
	}
	
	public RegisterDokumen convertRegisterDokumenDataToRegisterDokumenWithPerusahaan(RegisterDokumenData d) {
		RegisterDokumen registerDokumen = null;
		
		if(d != null) {	
			Dokumen dokumen = convertMasterDokumenDataToMasterDokumen(d.getDokumenData());	
			if(d.getAktaPendirianData() != null) {
				dokumen = convertAktaPendirianDataToAktaPendirian(dokumen, d.getAktaPendirianData());
			}
			else if(d.getNibOssData() != null ) {
				dokumen = convertDokumenNibOssDataToDokumenNibOss(dokumen, d.getNibOssData());
			}
			else if(d.getGenerikData() != null) {
				dokumen = convertDokumenGenerikDataToDokumenGenerik(dokumen, d.getGenerikData());
			}
			else if(d.getSuratArahanData() != null) {
				dokumen = convertSuratArahanDataToSuratArahan(dokumen, d.getSuratArahanData());;
			}
			else if(d.getLampiranSuratArahanData() != null) {
				dokumen = convertLampiranSuratArahanDataToLampiranSuratArahan(dokumen, d.getLampiranSuratArahanData());
			}
			else if(d.getRekomendasiUKLUPLData() != null) {
				dokumen = convertRekomendasiUKLUPLDataToRekomendasiUKLUPL(dokumen, d.getRekomendasiUKLUPLData());
			}
			else if(d.getRekomendasiDPLHData() != null) {
				dokumen = convertRekomendasiDPLHDataToRekomendasiDPLH(dokumen, d.getRekomendasiDPLHData());
			}
			
			registerDokumen = new RegisterDokumen(
					d.getId(), 
					dokumen, 
					convertRegisterPerusahaanDataToRegisterPerusahaan(d.getPerusahaanData()), 
					d.getLokasiFile(), 
					convertStatusDokumenDataToStatusDokumen(d.getStatusDokumen()),
					d.getTanggalRegistrasi(), 
					convertAutorisasiDataToAutority(d.getUploader()),
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
	
	public List<Otoritas> convertDaftarAuthorityToDaftarAutorisasiData(List<OtoritasData> d) {
		List<Otoritas> daftarAuthority = new ArrayList<Otoritas>();
		Iterator<OtoritasData> iter = d.iterator();
		
		OtoritasData autorisasiData = null;
		Otoritas item;
		while (iter.hasNext()) {
			autorisasiData = (OtoritasData) iter.next();					
			item = convertAutorisasiDataToAutority(autorisasiData);
			daftarAuthority.add(item);
		}
		
		return daftarAuthority;
	}
		
	public RegisterPerusahaan convertRegisterPerusahaanDataToRegisterPerusahaan(RegisterPerusahaanData d) {
		RegisterPerusahaan registerPerusahaan = null; 	
		
		if( d != null) {			
			Perusahaan perusahaan = new Perusahaan(
					d.getNpwp(), 
					d.getNama(), 
					convertModelPerizinanDataToModelPerizinan(d.getModelPerizinanData()), 
					convertSkalaUsahaDataToSkalaUsaha(d.getSkalaUsahaData()), 
					convertPelakuUsahaDataToPelakuUsaha(d.getPelakuUsaha()), 
					convertAlamatDataToAlamat(d.getAlamatPerusahaanData()), 
					convertKontakDataToKontak(d.getKontakPerusahaanData())
					);
			
			registerPerusahaan = new RegisterPerusahaan(
					d.getId(), 
					d.getTanggalRegistrasi(), 
					convertAutorisasiDataToAutority(d.getKreator()), 
					convertAutorisasiDataToAutority(d.getVerifikator()), 
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
	
	public Pegawai convertPegawaiDataToPegawai(PegawaiData d) {
		Pegawai pegawai = null;
		
		if(d != null) {
			pegawai = new Pegawai(
					d.getId(), 
					d.getRegisterPerusahaanData() != null ? convertRegisterPerusahaanDataToRegisterPerusahaan(d.getRegisterPerusahaanData()):null, 
					d.getPersonData() != null ? convertPersonDataToPerson(d.getPersonData()):null, 
					d.getJabatanData() != null ? convertJabatanDataToJabatan(d.getJabatanData()):null
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
	
	public StatuswaliPermohonan convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(StatusPengurusPermohonanData d) {
		StatuswaliPermohonan statusPengurusPermohonan = null;
		
		if(d != null) {
			statusPengurusPermohonan = new StatuswaliPermohonan(d.getId(), d.getNama());
		}
		
		return statusPengurusPermohonan;
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
			switch (d.getKategoriPermohonanData().getId()) {
			case "01":
				if(d.getPermohonanSuratArahanData() != null) {
					registerPermohonan = new RegisterPermohonanArahan(
							d.getId(), 
							d.getKategoriPermohonanData() != null ? convertKategoriPermohonanDataToKategoriPermohonan(d.getKategoriPermohonanData()):null, 
							d.getTanggalRegistrasi(), 
							d.getPerusahaanData() != null ? convertRegisterPerusahaanDataToRegisterPerusahaan(d.getPerusahaanData()):null, 
							d.getAutorisasiData() != null ? convertAutorisasiDataToAutority(d.getAutorisasiData()):null, 
							d.getStatusPengurusPermohonanData() != null ? convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(d.getStatusPengurusPermohonanData()):null,
							d.getPenanggungJawab() != null ? convertPegawaiDataToPegawai(d.getPenanggungJawab()):null,
							convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(d.getPosisiTahapPemberkasanPengirimData()),
							convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(d.getPosisiTahapPemberkasanPenerimaData()),
							convertStatusFlowLogDataToStatusFlowLog(d.getStatusFlowData()),
							convertDaftarDokumenPersyaratanPermohonanToDaftarRegisterDokumen(d.getDaftarDokumenSyarat()), 
							null, 
							convertKategoriSuratArahanDataToJenisPermohonanSuratArahan(d.getPermohonanSuratArahanData().getKategoriSuratArahanData()), 
							d.getPermohonanSuratArahanData().getUraianKegiatan()
							);
				}	
				else {
					registerPermohonan = new RegisterPermohonan(
							d.getId(), 
							convertKategoriPermohonanDataToKategoriPermohonan(d.getKategoriPermohonanData()), 
							d.getTanggalRegistrasi(), 
							convertRegisterPerusahaanDataToRegisterPerusahaan(d.getPerusahaanData()), 
							convertAutorisasiDataToAutority(d.getAutorisasiData()), 
							convertStatusPengurusPermohonanDataToStatusPengurusPermohonan(d.getStatusPengurusPermohonanData()), 
							convertPegawaiDataToPegawai(d.getPenanggungJawab()), 
							convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(d.getPosisiTahapPemberkasanPengirimData()), 
							convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(d.getPosisiTahapPemberkasanPenerimaData()), 
							convertStatusFlowLogDataToStatusFlowLog(d.getStatusFlowData()), 
							convertDaftarDokumenPersyaratanPermohonanToDaftarRegisterDokumen(d.getDaftarDokumenSyarat()), 
							null
							);							
				}
				break;
			default:
				break;
			}
		}
		
		return registerPermohonan;
		
	}

	public KategoriFlowLog convertKategoriFlowLogDataToKategoriFlowLog(KategoriLogData d) {
		KategoriFlowLog kategoriFlowLog = null;
		
		if(d != null) {			
			kategoriFlowLog = new KategoriFlowLog(d.getId(), d.getNama());
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
						convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(d.getPosisiTahapPemberkasanPengirimData()), 
						convertStatusTahapPemberkasanDataToStatusTahapPemberkasan(d.getPosisiTahapPemberkasanPenerimaData()), 
						convertStatusFlowLogDataToStatusFlowLog(d.getStatusFlowData()),
						d.getKeterangan(), 
						convertAutorisasiDataToAutority(d.getPengaksesData()), 
						convertRegisterPermohonanDataToRegisterPermohonan(d.getFlowLogPermohonanData().getRegisterPermohonan())
						);
			}
			
			
		}
		
		return flowLog;	
	}
		
	public KategoriPermohonanSuratArahan convertKategoriSuratArahanDataToJenisPermohonanSuratArahan(KategoriSuratArahanData d) {
		KategoriPermohonanSuratArahan jenisPermohonanSuratArahan = null;
		
		if(d != null) {
			jenisPermohonanSuratArahan = new KategoriPermohonanSuratArahan(d.getId(), d.getNama());
		}
		
		return jenisPermohonanSuratArahan;		
	}
	
	public StatusFlowLog convertStatusFlowLogDataToStatusFlowLog(StatusFlowLogData d) {
		StatusFlowLog statusFlowLog = null;
		
		if(d != null) {
			statusFlowLog = new StatusFlowLog(d.getId(), d.getKeterangan());
		}
		
		return statusFlowLog;		
	}
	
	public OtoritasPerusahaan convertAutorityPerusahaanDataToAutorityPerusahaan(OtoritasPerusahaanData d) {
		OtoritasPerusahaan autorityPerusahaan = null;
		
		if(d != null) {
			OtoritasData autorisasiData = d.getAutority();
			Otoritas autority = new Otoritas(
					autorisasiData.getId(), 
					autorisasiData.getTanggalRegistrasi(),
					convertPersonDataToPerson(autorisasiData.getPerson()), 
					convertHakAksesDataToHakAkses(autorisasiData.getHakAkses()), 
					autorisasiData.getStatusInternal(), 
					autorisasiData.getIsVerified(), 
					autorisasiData.getUserName()
					);
			
			RegisterPerusahaanData registerPerusahaanData = d.getPerusahaan();
			Perusahaan perusahaan = new Perusahaan(
					registerPerusahaanData.getNpwp(), 
					registerPerusahaanData.getNama(), 
					convertModelPerizinanDataToModelPerizinan(registerPerusahaanData.getModelPerizinanData()), 
					convertSkalaUsahaDataToSkalaUsaha(registerPerusahaanData.getSkalaUsahaData()), 
					convertPelakuUsahaDataToPelakuUsaha(registerPerusahaanData.getPelakuUsaha()), 
					convertAlamatDataToAlamat(registerPerusahaanData.getAlamatPerusahaanData()), 
					convertKontakDataToKontak(registerPerusahaanData.getKontakPerusahaanData())
					);
			RegisterPerusahaan registerPerusahaan = new RegisterPerusahaan(
					registerPerusahaanData.getId(), 
					registerPerusahaanData.getTanggalRegistrasi(), 
					convertAutorisasiDataToAutority(registerPerusahaanData.getKreator()), 
					convertAutorisasiDataToAutority(registerPerusahaanData.getVerifikator()), 
					perusahaan,
					registerPerusahaanData.getStatusVerifikasi()
					);
			
			autorityPerusahaan = new OtoritasPerusahaan(autority, registerPerusahaan);
		}
		
		return autorityPerusahaan;	
	}

	public BidangUsaha convertBidangUsahaDataToBidangUsaha(BidangUsahaData d) {
		BidangUsaha bidangUsaha = null;
		
		if(d != null) {
			bidangUsaha = new BidangUsaha(d.getId(), d.getNama());
		}
		
		return bidangUsaha;
	}
	
	public KategoriProduk convertKategoriProdukDataToKategoriProduk(KategoriProdukData d) {
		KategoriProduk kategoriProduk = null;
		
		if(d != null) {
			kategoriProduk = new KategoriProduk(d.getId(), d.getNama());
		}
		
		return kategoriProduk;
	}
	
	public PenanggungJawab convertPenanggungJawabDataToPenanggungJawab(PenanggungJawabData d) {
		PenanggungJawab penanggungJawab = null;
		
		if(d != null) {
			penanggungJawab = new PenanggungJawab(
					d.getId(), 
					d.getPerson() != null ? convertPersonDataToPerson(d.getPerson()):null, 
					d.getJabatan() != null ? convertJabatanDataToJabatan(d.getJabatan()):null, 
					d.getPemrakarsa() != null ? convertRegisterPerusahaanDataToRegisterPerusahaan(d.getPemrakarsa()):null
					);
		}
		
		return penanggungJawab;
	}
	
	public KategoriPermohonanSuratArahan convertKategoriPermohonanSuratArahanDataToKategoriPermohonanSuratArahan(KategoriPermohonanSuratArahanData d) {
		KategoriPermohonanSuratArahan kategoriPermohonanSuratArahan = null;
		
		if(d != null) {
			kategoriPermohonanSuratArahan = new KategoriPermohonanSuratArahan(d.getId(), d.getKeterangan());
		}
		
		return kategoriPermohonanSuratArahan;
	}
	
	public Produk convertProdukDataToProduk(ProdukData d) {
		Produk produk = null;
		
		if(d != null) {
			produk = new Produk(d.getId(), d.getNama(), d.getKategoriProduk() != null ? convertKategoriProdukDataToKategoriProduk(d.getKategoriProduk()):null);
		}
		
		return produk;
	}
	
	public Otoritas convertKeyCloackUserRepresentationToAutority(UserRepresentation d) {
		OtoritasData autorisasiData = entityManager.createNamedQuery("AutorisasiData.findByUserName", OtoritasData.class)
				.setParameter("userName", d.getEmail())
				.getResultList().stream().findFirst().orElse(null);		
		Otoritas autority = null;
		
		if(autorisasiData != null) {
			autority = convertAutorisasiDataToAutority(autorisasiData);
		}
		
		return autority;
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
			String id = t.getId();			
			kabupatenData = new KabupatenData();
			kabupatenData.setId(id != null ? id:getGenerateIdKabupatenData(t.getPropinsi().getId()));
			kabupatenData.setNama(t.getNama());
			PropinsiData propinsiData = t.getPropinsi() != null ? convertPropinsiToPropinsiData(t.getPropinsi()):null;
			kabupatenData.setPropinsi(propinsiData);
		}
		
		return kabupatenData;
	}
	
	public KecamatanData convertKecamatanToKecamatanData(Kecamatan t) {
		KecamatanData kecamatanData = null;
		
		if(t != null) {
			String id = t.getId();
			kecamatanData = new KecamatanData();
			kecamatanData.setId(id != null ? id:getGenerateIdKecamatanData(t.getKabupaten().getId()));
			kecamatanData.setNama(t.getNama());
			KabupatenData kabupatenData = t.getKabupaten() != null ? convertKabupatenToKabupatenData(t.getKabupaten()):null;
			kecamatanData.setKabupaten(kabupatenData);
		}
		
		return kecamatanData;
	}
	
	public DesaData convertDesaToDesaData(Desa t) {
		DesaData desaData = null;
		
		if(t != null) {
			String id = t.getId();
			desaData = new DesaData();
			desaData.setId(id != null ? id:getGenerateIdDesaData(t.getKecamatan().getId()));
			desaData.setNama(t.getNama());
			KecamatanData kecamatanData = t.getKecamatan() != null ? convertKecamatanToKecamatanData(t.getKecamatan()):null;
			desaData.setKecamatan(kecamatanData);			
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
			SkalaUsahaData skalaUsahaData = new SkalaUsahaData();
			skalaUsahaData.setId(t.getSkalaUsaha().getId());			
			kategoriPelakuUsahaData.setSkalaUsaha(skalaUsahaData);			
		}
		
		return kategoriPelakuUsahaData;
	}
	
	public PelakuUsahaData convertPelakuUsahaToPelakuUsahaData(PelakuUsaha t) {
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
			String id = t.getId();
			hakAksesData = new HakAksesData();
			hakAksesData.setId(id != null ? id:getGenerateIdHakAksesData());
			hakAksesData.setNama(t.getNama());
			hakAksesData.setKeterangan(t.getKeterangan());
		}
		
		return hakAksesData;
	}
	
	public OtoritasData convertAuthorityToAutorisasiData(Otoritas t) {
		OtoritasData autorisasiData = null;
		
		if(t != null) {
			autorisasiData = new OtoritasData();
			autorisasiData.setId(t.getId() != null ? t.getId(): getGenerateIdAutorisasiData());
			autorisasiData.setTanggalRegistrasi(t.getTanggal());
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
			Perusahaan perusahaan = t.getPerusahaan();
			registerPerusahaanData = new RegisterPerusahaanData();
			registerPerusahaanData.setId(id != null ? id : getGenerateIdRegisterPerusahaanData());
			if(perusahaan != null) {
				registerPerusahaanData.setNama(perusahaan.getNama());
				registerPerusahaanData.setAlamatPerusahaanData(perusahaan.getAlamat() != null ? convertAlamatToAlamatData(t.getPerusahaan().getAlamat()):null);
				registerPerusahaanData.setModelPerizinanData(perusahaan.getModelPerizinan() != null ? convertModelPerizinanToModelPerizinanData(perusahaan.getModelPerizinan()):null);
				registerPerusahaanData.setSkalaUsahaData(perusahaan.getSkalaUsaha() != null ? convertSkalaUsahaToSkalaUsahaData(perusahaan.getSkalaUsaha()):null);
				registerPerusahaanData.setPelakuUsaha(perusahaan.getPelakuUsaha() != null ? convertPelakuUsahaToPelakuUsahaData(perusahaan.getPelakuUsaha()):null);
				registerPerusahaanData.setKontakPerusahaanData(perusahaan.getKontak() != null ? convertKontakToKontakData(perusahaan.getKontak()):null);
				registerPerusahaanData.setNpwp(perusahaan.getId() != null ? perusahaan.getId():null);			
			}			
			registerPerusahaanData.setStatusVerifikasi(t.getStatusVerifikasi());
			registerPerusahaanData.setKreator(t.getKreator() != null ? convertAuthorityToAutorisasiData(t.getKreator()):null);
			registerPerusahaanData.setVerifikator(t.getVerifikator() != null ? convertAuthorityToAutorisasiData(t.getVerifikator()):null);
			registerPerusahaanData.setTanggalRegistrasi(t.getTanggalRegistrasi());
		}
		
		return registerPerusahaanData;
	}
	
	public PegawaiData convertPegawaiToPegawaiData(Pegawai t) {
		PegawaiData pegawaiData = new PegawaiData();
		
		if(t != null) {
			String id = t.getId();
			pegawaiData.setId(id != null ? id:getGenerateIdPegawaiPerusahaan(t.getRegisterPerusahaan().getId(), t.getJabatan().getId(), t.getPerson().getNik()));
			pegawaiData.setPersonData(t.getPerson() != null ? convertPersonToPersonData(t.getPerson()):null);
			pegawaiData.setRegisterPerusahaanData(t.getRegisterPerusahaan() != null ? convertRegisterPerusahaanToRegisterPerusahaanData(t.getRegisterPerusahaan()):null);
			pegawaiData.setJabatanData(t.getJabatan() != null ? convertJabatanToJabatanData(t.getJabatan()):null);			
		}
		
		return pegawaiData;
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
	
	public AktaPendirianData convertAktaPendirianToAktaPendirianData(DokumenAktaPendirian t, String idRegisterDokumen) {
		AktaPendirianData aktaPendirianData = null;
		
		if(t != null) {
			aktaPendirianData = new AktaPendirianData();
			aktaPendirianData.setId(idRegisterDokumen);
			aktaPendirianData.setNomor(t.getNomor());
			aktaPendirianData.setTanggal(t.getTanggal());
			aktaPendirianData.setNotaris(t.getNamaNotaris());
			aktaPendirianData.setPenanggungJawabData(convertPegawaiToPegawaiData(t.getPenanggungJawab()));
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
			registerKbliData.setNib(t.getNib());
			registerKbliData.setKbli(
					t.getKbli() != null ? convertKbliToKbliData(t.getKbli()):null
					);
		}
		
		return registerKbliData;
	}
	
	public KbliData convertKbliToKbliData(Kbli t) {
		KbliData kbliData = null;
		
		if(t != null) {			
			kbliData = new KbliData();
			kbliData.setId(t.getKode());	
			kbliData.setNama(t.getNama());
			kbliData.setKategori(t.getKategori());			
		}
		
		return kbliData;
	}
	
	public List<OtoritasData> convertDaftarAutorisasiDataToDaftarAuthority(List<Otoritas> t) {
		List<OtoritasData> daftarAutorisasiData = null;
		
		if(t != null) {
			daftarAutorisasiData = new ArrayList<OtoritasData>();
			for(Otoritas item : t) {		
				OtoritasData autorisasiData = convertAuthorityToAutorisasiData(item);
				if(autorisasiData != null) {
					daftarAutorisasiData.add(autorisasiData);
				}				
			}
		}
		
		return daftarAutorisasiData;
	}
	
	public DokumenNibOssData convertDokumenNibOssToDokumenNibOssData(DokumenNibOss t, String idRegisterDokumen) {
		DokumenNibOssData dokumenNibOssData = null;
		
		if(t != null) {
			dokumenNibOssData = new DokumenNibOssData();
			dokumenNibOssData.setId(idRegisterDokumen);
			dokumenNibOssData.setNib(t.getNomor());
			dokumenNibOssData.setTanggalPenetapan(t.getTanggal());
			
			List<RegisterKbliData> daftarRegisterKbliData = t.getDaftarKbli().stream().map((kbli) -> {
						RegisterKbliData registerKbliData = new RegisterKbliData();
						registerKbliData.setNib(t.getNomor());
						KbliData kbliData = convertKbliToKbliData(kbli);
						registerKbliData.setKbli(kbliData);					
						return registerKbliData;
					})
					.collect(Collectors.toList());
			
			dokumenNibOssData.setDaftarRegisterKbli(daftarRegisterKbliData);
		}
		
		return dokumenNibOssData;
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
			
	public DokumenGenerikData convertDokumenGenerikToDokumenGenerikData(DokumenGenerik t, String idRegisterDokumen) {
		DokumenGenerikData dokumenGenerikData = null;
		
		if(t != null) {
			dokumenGenerikData = new DokumenGenerikData();
			dokumenGenerikData.setId(idRegisterDokumen);
			dokumenGenerikData.setNomor(t.getNomor());
			dokumenGenerikData.setTanggalPenetapan(t.getTanggal());
		}
		
		return dokumenGenerikData;
	}
	
	public RegisterDokumenData convertRegisterDokumenToRegisterDokumenData(RegisterDokumen t) {
		RegisterDokumenData registerDokumenData = null;
		
		if(t != null) {
			Dokumen dokumen = t.getDokumen();
			String id = t.getId();
			
			registerDokumenData = new RegisterDokumenData();
			registerDokumenData.setId(id != null ? id : getGenerateIdRegisterDokumen());
			registerDokumenData.setPerusahaanData(
					t.getRegisterPerusahaan() != null ? convertRegisterPerusahaanToRegisterPerusahaanData(t.getRegisterPerusahaan()):null
					);	
			registerDokumenData.setDokumenData(
					dokumen != null ? convertMasterDokumenToMasterDokumenData(dokumen):null
					);			
			registerDokumenData.setTanggalRegistrasi(
					t.getTanggalRegistrasi() != null ? t.getTanggalRegistrasi():null
					);
			registerDokumenData.setUploader(
					t.getUploader() != null ? convertAuthorityToAutorisasiData(t.getUploader()):null
					);
			registerDokumenData.setLokasiFile(
					t.getLokasiFile() != null ? t.getLokasiFile():null
					);
			registerDokumenData.setStatusDokumen(
					t.getStatusDokumen() != null ? convertStatusDokumenToStatusDokumenData(t.getStatusDokumen()):null
					);
			registerDokumenData.setStatusVerified(
					t.getStatusVerified() != null ? t.getStatusVerified():null
					);
			

			if(dokumen instanceof DokumenAktaPendirian) {
				registerDokumenData.setAktaPendirianData(convertAktaPendirianToAktaPendirianData((DokumenAktaPendirian) dokumen, registerDokumenData.getId()));
			}
			else if(dokumen instanceof DokumenNibOss) {
				registerDokumenData.setNibOssData(convertDokumenNibOssToDokumenNibOssData((DokumenNibOss) dokumen, registerDokumenData.getId()));
			}
			else if(dokumen instanceof DokumenGenerik) {
				registerDokumenData.setGenerikData(convertDokumenGenerikToDokumenGenerikData((DokumenGenerik) dokumen, registerDokumenData.getId()));
			}
			else if(dokumen instanceof SuratArahan) {
				registerDokumenData.setSuratArahanData(convertSuratArahanToSuratArahanData((SuratArahan) dokumen, registerDokumenData.getId()));
			}
			else if(dokumen instanceof LampiranSuratArahan) {
				registerDokumenData.setLampiranSuratArahanData(convertLampiranSuratArahanToLampiranSuratArahanData((LampiranSuratArahan) dokumen, registerDokumenData.getId()));
			}
			else if(dokumen instanceof RekomendasiUKLUPL) {
				registerDokumenData.setRekomendasiUKLUPLData(convertRekomendasiUKLUPLToRekomendasiUKLUPLData((RekomendasiUKLUPL) dokumen, registerDokumenData.getId()));
			}
			else if(dokumen instanceof RekomendasiDPLH) {
				registerDokumenData.setRekomendasiDPLHData(convertRekomendasiDPLHToRekomendasiDPLHData((RekomendasiDPLH) dokumen, registerDokumenData.getId()));
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
	
	public StatusPengurusPermohonanData convertStatusPengurusPermohonanToStatusPengurusPermohonanData(StatuswaliPermohonan t) {
		StatusPengurusPermohonanData statusPengurusPermohonanData = null;
		
		if(t != null) {
			statusPengurusPermohonanData = new StatusPengurusPermohonanData();
			statusPengurusPermohonanData.setId(t.getId());
			statusPengurusPermohonanData.setNama(t.getNama());
		}
		
		return statusPengurusPermohonanData;
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
	
	public KategoriSuratArahanData convertJenisPermohonanSuratArahanToKategoriSuratArahanData(KategoriPermohonanSuratArahan t) {
		KategoriSuratArahanData kategoriSuratArahanData = null;
		
		if(t != null) {
			kategoriSuratArahanData = new KategoriSuratArahanData();
			kategoriSuratArahanData.setId(t.getId());
			kategoriSuratArahanData.setNama(t.getKeterangan());
		}
		
		return kategoriSuratArahanData;		
	}
	
	public StatusFlowLogData convertStatusFlowLogToStatusFlowData(StatusFlowLog t) {
		StatusFlowLogData statusFlowData = null;
		
		if(t != null) {
			statusFlowData = new StatusFlowLogData();
			statusFlowData.setId(t.getId());
			statusFlowData.setKeterangan(t.getNama());
		}
		
		return statusFlowData;		
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
			registerPermohonanData = new RegisterPermohonanData();
			String id = t.getId();
			registerPermohonanData.setId(id != null ? id : getGenerateIdRegisterPermohonan());
			registerPermohonanData.setKategoriPermohonanData(
					convertKategoriPermohonanToKategoriPermohonanData(t.getKategoriPermohonan()));			
			registerPermohonanData.setTanggalRegistrasi(t.getTanggalRegistrasi());
			registerPermohonanData.setPerusahaanData(convertRegisterPerusahaanToRegisterPerusahaanData(t.getPerusahaan()));
			registerPermohonanData.setAutorisasiData(convertAuthorityToAutorisasiData(t.getPengurusPermohonan()));
			registerPermohonanData.setStatusPengurusPermohonanData(
					convertStatusPengurusPermohonanToStatusPengurusPermohonanData(t.getStatusPengurusPermohonan()));
			registerPermohonanData.setPosisiTahapPemberkasanPengirimData(
					convertStatusTahapPemberkasanToStatusTahapPemberkasanData(t.getPengirimBerkas())
					);
			registerPermohonanData.setPosisiTahapPemberkasanPenerimaData(
					convertStatusTahapPemberkasanToStatusTahapPemberkasanData(t.getPenerimaBerkas())
					);
			registerPermohonanData.setStatusFlowData(convertStatusFlowLogToStatusFlowData(t.getStatusFlowLog()));
			registerPermohonanData.setPenanggungJawab(convertPegawaiToPegawaiData(t.getPenanggungJawabPermohonan()));			
			registerPermohonanData.setDaftarDokumenSyarat(
					convertDaftarRegisterDokumenToDaftarDokumenPersyaratanData(t.getDaftarDokumenSyarat(), registerPermohonanData)
					);
			switch (t.getKategoriPermohonan().getId()) {
			case "01":
				RegisterPermohonanArahan s = (RegisterPermohonanArahan) t;
				RegisterPermohonanSuratArahanData registerPermohonanSuratArahanData = new RegisterPermohonanSuratArahanData();
				registerPermohonanSuratArahanData.setKategoriSuratArahanData(convertJenisPermohonanSuratArahanToKategoriSuratArahanData(s.getJenisPermohonanSuratArahan()));
				registerPermohonanSuratArahanData.setUraianKegiatan(s.getUraianKegiatan());
				registerPermohonanSuratArahanData.setRegisterPermohonanData(registerPermohonanData);
				registerPermohonanData.setPermohonanSuratArahanData(registerPermohonanSuratArahanData);
				break;
			default:
				break;
			}
			
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
				flowLogData.setPosisiTahapPemberkasanPengirimData(
						convertStatusTahapPemberkasanToStatusTahapPemberkasanData(flowLogPermohonan.getPengirimBerkas())
				);
				flowLogData.setPosisiTahapPemberkasanPenerimaData(
						convertStatusTahapPemberkasanToStatusTahapPemberkasanData(flowLogPermohonan.getPenerimaBerkas())
						);
				flowLogData.setStatusFlowData(convertStatusFlowLogToStatusFlowData(flowLogPermohonan.getStatusFlowLog()));
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
		
	public DokumenData convertMasterDokumenToMasterDokumenData(Dokumen t) {
		DokumenData masterDokumenData = new DokumenData();
		
		if(t != null) {
			masterDokumenData.setId(t.getId());
			masterDokumenData.setNama(t.getNama());
			masterDokumenData.setKategoriDokumenData(
					t.getKategoriDokumen() != null ? convertKategoriDokumenToKategoriDokumenData(t.getKategoriDokumen()):null
					);
		}
		
		return masterDokumenData;
	}
	
	public KategoriDokumenData convertKategoriDokumenToKategoriDokumenData(KategoriDokumen t) {
		KategoriDokumenData kategoriDokumenData = new KategoriDokumenData();
		
		if(t != null) {
			kategoriDokumenData.setId(t.getId());
			kategoriDokumenData.setNama(t.getNama());			
			kategoriDokumenData.setParent(t.getParent());
		}
		
		return kategoriDokumenData;
	}
	
	public OtoritasPerusahaanData convertAutorityPerusahaanToAutorityPerusahaanData(OtoritasPerusahaan t) {
		OtoritasPerusahaanData autorityPerusahaanData = new OtoritasPerusahaanData();
		
		if(t != null) {			
			Otoritas autority = t.getOtoritas();
			OtoritasData autorisasiData = new OtoritasData();
			autorisasiData.setId(autority.getId());
			autorisasiData.setTanggalRegistrasi(autority.getTanggal());
			autorisasiData.setUserName(autority.getUserName());
			autorisasiData.setPerson(convertPersonToPersonData(autority.getPerson()));
			autorisasiData.setHakAkses(convertHakAksesToHakAksesData(autority.getHakAkses()));
			autorisasiData.setStatusInternal(autority.isStatusInternal());
			autorisasiData.setIsVerified(autority.isVerified());			
			autorityPerusahaanData.setAutority(autorisasiData);
			
			RegisterPerusahaan registerPerusahaan = t.getRegisterPerusahaan();
			RegisterPerusahaanData registerPerusahaanData = new RegisterPerusahaanData();
			String id = registerPerusahaan.getId();
			registerPerusahaanData.setId(id != null ? id : getGenerateIdRegisterPerusahaanData());
			registerPerusahaanData.setNama(registerPerusahaan.getPerusahaan().getNama());
			registerPerusahaanData.setAlamatPerusahaanData(convertAlamatToAlamatData(registerPerusahaan.getPerusahaan().getAlamat()));
			registerPerusahaanData.setModelPerizinanData(convertModelPerizinanToModelPerizinanData(registerPerusahaan.getPerusahaan().getModelPerizinan()));
			registerPerusahaanData.setSkalaUsahaData(convertSkalaUsahaToSkalaUsahaData(registerPerusahaan.getPerusahaan().getSkalaUsaha()));
			registerPerusahaanData.setPelakuUsaha(convertPelakuUsahaToPelakuUsahaData(registerPerusahaan.getPerusahaan().getPelakuUsaha()));
			registerPerusahaanData.setStatusVerifikasi(registerPerusahaan.getStatusVerifikasi());
			registerPerusahaanData.setKontakPerusahaanData(convertKontakToKontakData(registerPerusahaan.getPerusahaan().getKontak()));
			registerPerusahaanData.setKreator(convertAuthorityToAutorisasiData(registerPerusahaan.getKreator()));
			registerPerusahaanData.setVerifikator(convertAuthorityToAutorisasiData(registerPerusahaan.getVerifikator()));
			registerPerusahaanData.setTanggalRegistrasi(registerPerusahaan.getTanggalRegistrasi());
			registerPerusahaanData.setNpwp(registerPerusahaan.getPerusahaan().getId());		
			autorityPerusahaanData.setPerusahaan(registerPerusahaanData);
		}
		
		return autorityPerusahaanData;	
	}
	
	public BidangUsahaData convertBidangUsahaToBidangUsahaData(BidangUsaha t) {
		BidangUsahaData bidangUsahaData = new BidangUsahaData();
		
		if(t != null) {
			bidangUsahaData.setId(t.getId());
			bidangUsahaData.setNama(t.getNama());
		}
		
		return bidangUsahaData;
	}
	
	public KategoriProdukData convertKategoriProdukToKategoriProdukData(KategoriProduk t) {
		KategoriProdukData kategoriProdukData = new KategoriProdukData();
		
		if(t != null) {
			kategoriProdukData.setId(t.getId());
			kategoriProdukData.setNama(t.getNama());
		}
		
		return kategoriProdukData;
	}
	
	public PenanggungJawabData convertPenanggungJawabToPenanggungJawabData(PenanggungJawab t) {
		PenanggungJawabData penanggungJawabData = new PenanggungJawabData();
		
		if(t != null) {
			penanggungJawabData.setId(t.getId());
			penanggungJawabData.setPerson(convertPersonToPersonData(t.getPerson()));
			penanggungJawabData.setPemrakarsa(convertRegisterPerusahaanToRegisterPerusahaanData(t.getRegisterPerusahaan()));
			penanggungJawabData.setJabatan(convertJabatanToJabatanData(t.getJabatan()));
		}
		
		return penanggungJawabData;
	}
	
	public KategoriPermohonanSuratArahanData convertKategoriPermohonanSuratArahanToKategoriPermohonanSuratArahanData(KategoriPermohonanSuratArahan t) {
		KategoriPermohonanSuratArahanData kategoriPermohonanSuratArahanData = new KategoriPermohonanSuratArahanData();
		
		if(t != null) {
			kategoriPermohonanSuratArahanData.setId(t.getId());
			kategoriPermohonanSuratArahanData.setKeterangan(t.getKeterangan());
		}
		
		return kategoriPermohonanSuratArahanData;
	}
	
	public ProdukData convertProdukToProdukData(Produk t) {
		ProdukData produkData = new ProdukData();
		
		if(t != null) {
			produkData.setId(t.getId());
			produkData.setNama(t.getNama());
			produkData.setKategoriProduk(t.getKategoriProduk() != null ? convertKategoriProdukToKategoriProdukData(t.getKategoriProduk()):null);			
		}
		
		return produkData;
	}
	
	public UserRepresentation convertUserToUserRepresentationKeyCloack(User t) {
		UserRepresentation userRepresentation = null;
		
		if(t != null) {
			userRepresentation = new UserRepresentation();
			userRepresentation.setId(t.getPerson().getNik());
			userRepresentation.setEmail(t.getPerson().getKontak().getEmail());
	        userRepresentation.setUsername(t.getCredential().getUserName());
	        userRepresentation.setFirstName(t.getPerson().getNama());
		}
		
		return userRepresentation;
	}
	
	/*----------id generator function---------------*/
	private String getGenerateIdKabupatenData(String idPropinsi) {
		String hasil;
		
		Query q = entityManager.createQuery("SELECT MAX(k.id) "
				+ "FROM KabupatenData k "
				+ "WHERE k.propinsi.id = :idPropinsi");
		
		q.setParameter("idPropinsi", idPropinsi);
		
		try {
			hasil = (String) q.getSingleResult();
			Long idBaru = Long.valueOf(hasil)  + 1;
			hasil = LPad(Long.toString(idBaru), 4, '0');
			return hasil;
		} catch (Exception e) {			
			hasil = "0001";			
			return hasil;
		}		
	}
	
	private String getGenerateIdKecamatanData(String idKabupaten) {
		String hasil;
		
		Query q = entityManager.createQuery("SELECT MAX(k.id) "
				+ "FROM KecamatanData k "
				+ "WHERE k.kabupaten.id = :idKabupaten");
		
		q.setParameter("idKabupaten", idKabupaten);
		
		try {
			hasil = (String) q.getSingleResult();
			Long idBaru = Long.valueOf(hasil)  + 1;
			hasil = LPad(Long.toString(idBaru), 7, '0');
			return hasil;
		} catch (Exception e) {			
			hasil = "0000001";			
			return hasil;
		}		
	}
	
	private String getGenerateIdDesaData(String idKecamatan) {
		String hasil;
		
		Query q = entityManager.createQuery("SELECT MAX(d.id) "
				+ "FROM DesaData d "
				+ "WHERE d.kecamatan.id = :idKecamatan");
		
		q.setParameter("idKecamatan", idKecamatan);
		
		try {
			hasil = (String) q.getSingleResult();
			Long idBaru = Long.valueOf(hasil)  + 1;
			hasil = LPad(Long.toString(idBaru), 10, '0');
			return hasil;
		} catch (Exception e) {			
			hasil = "0000000001";			
			return hasil;
		}		
	}
	
	private String getGenerateIdRegisterPerusahaanData() {
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
			hasil = hasil.substring(0, 7);
			Long idBaru = Long.valueOf(hasil)  + 1;
			hasil = LPad(Long.toString(idBaru), 7, '0');
			return hasil.concat(Integer.toString(tahun));
		} catch (Exception e) {	
			hasil = "0000001";			
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
	
	private String getGenerateIdAutorisasiData() {
		int tahun = LocalDate.now().getYear();
		String hasil;
		
		Query q = entityManager.createQuery("SELECT MAX(a.id) "
				+ "FROM AutorisasiData a "
				+ "WHERE EXTRACT(YEAR FROM a.tanggal) = :tahun");
		
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
	
	private String getGenerateIdHakAksesData() {
		String hasil;		
		Query q = entityManager.createQuery("SELECT MAX(a.id) "
				+ "FROM HakAksesData a ");
		try {
			hasil = (String) q.getSingleResult();
			Long idBaru = Long.valueOf(hasil)  + 1;
			hasil = LPad(Long.toString(idBaru), 2, '0');
			return hasil;
		} catch (Exception e) {			
			hasil = "01";			
			return hasil;
		}		
	}
	
 	private String LPad(String str, Integer length, char car) {
		  return (String.format("%" + length + "s", "").replace(" ", String.valueOf(car)) + str).substring(str.length(), length + str.length());
	}
	
}
