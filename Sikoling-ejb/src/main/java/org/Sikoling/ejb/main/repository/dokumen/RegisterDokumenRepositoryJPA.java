package org.Sikoling.ejb.main.repository.dokumen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Person;
import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.Pegawai;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.RegisterDokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.AktaPendirian;
import org.Sikoling.ejb.abstraction.entity.dokumen.Dokumen;
import org.Sikoling.ejb.abstraction.entity.dokumen.LampiranSuratArahan;
import org.Sikoling.ejb.abstraction.entity.dokumen.NibOss;
import org.Sikoling.ejb.abstraction.entity.dokumen.RegisterKbli;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiDPLH;
import org.Sikoling.ejb.abstraction.entity.dokumen.RekomendasiUKLUPL;
import org.Sikoling.ejb.abstraction.entity.dokumen.SuratArahan;
import org.Sikoling.ejb.abstraction.repository.IRegisterDokumenRepository;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.jabatan.JabatanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.person.AlamatPersonData;
import org.Sikoling.ejb.main.repository.person.PersonData;
import org.Sikoling.ejb.main.repository.perusahaan.PegawaiData;
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
		Dokumen dokumen = t.getDokumen();
		if(dokumen instanceof SuratArahan) {
			registerDokumenData.setSuratArahanData(null);
			entityManager.persist(registerDokumenData);	
		}
		else if(dokumen instanceof LampiranSuratArahan) {			
			registerDokumenData.setLampiranSuratArahanData(null);
			entityManager.persist(registerDokumenData);	
		}
		else if(dokumen instanceof AktaPendirian) {
			registerDokumenData.setAktaPendirianData(null);
			entityManager.persist(registerDokumenData);	
		}
		else if(dokumen instanceof RekomendasiUKLUPL) {
			registerDokumenData.setRekomendasiUKLUPLData(null);
			entityManager.persist(registerDokumenData);	
		}
		else if(dokumen instanceof RekomendasiDPLH) {			
			registerDokumenData.setRekomendasiDPLHData(null);
			entityManager.persist(registerDokumenData);	
		}
		else if(dokumen instanceof NibOss) {			
			NibOssData nibOssData = registerDokumenData.getNibOssData();
			RegisterDokumenData regDokData = new RegisterDokumenData();
			regDokData.setId(registerDokumenData.getId());
			nibOssData.setRegisterDokumenData(regDokData);

			registerDokumenData.setNibOssData(null);
			
			entityManager.persist(registerDokumenData);	
			entityManager.persist(nibOssData);
		}
		
//		entityManager.persist(registerDokumenData);	
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
							),
					d.getStatusVerified()
					);
		}
		else if(d.getAktaPendirianData() != null) {
			AktaPendirianData aktaPendirianData = d.getAktaPendirianData();
			
			return new RegisterDokumen(
					d.getId(), 
					new AktaPendirian(
							masterDokumenData.getId(), 
							masterDokumenData.getNama(), 
							null, 
							aktaPendirianData.getNomor(), 
							aktaPendirianData.getTanggal(), 
							aktaPendirianData.getNotaris(), 
							aktaPendirianData.getPenanggungJawabData() != null ?
									convertPegawaiDataToPegawai(
											aktaPendirianData.getPenanggungJawabData()
											) : null
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
							),
					d.getStatusVerified()
					);
		}
		else if(d.getLampiranSuratArahanData() != null) {
			LampiranSuratArahanData lampiranSuratArahanData = d.getLampiranSuratArahanData();
			
			return new RegisterDokumen(
					d.getId(), 
					new LampiranSuratArahan(
							masterDokumenData.getId(), 
							masterDokumenData.getNama(), 
							null, 
							lampiranSuratArahanData.getNoSurat(), 
							lampiranSuratArahanData.getTanggalSurat()
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
							),
					d.getStatusVerified()
					);
		}
		else if(d.getRekomendasiUKLUPLData() != null) {
			RekomendasiUKLUPLData rekomendasiUKLUPLData = d.getRekomendasiUKLUPLData();
			
			return new RegisterDokumen(
					d.getId(), 
					new RekomendasiUKLUPL(
							masterDokumenData.getId(), 
							masterDokumenData.getNama(), 
							null, 
							rekomendasiUKLUPLData.getNoSurat(), 
							rekomendasiUKLUPLData.getTanggalSurat(),
							rekomendasiUKLUPLData.getPerihalSurat()
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
							),
					d.getStatusVerified()
					);
		}
		else if(d.getRekomendasiDPLHData() != null) {
			RekomendasiDPLHData rekomendasiDPLHData = d.getRekomendasiDPLHData();
			
			return new RegisterDokumen(
					d.getId(), 
					new RekomendasiUKLUPL(
							masterDokumenData.getId(), 
							masterDokumenData.getNama(), 
							null, 
							rekomendasiDPLHData.getNoSurat(), 
							rekomendasiDPLHData.getTanggalSurat(),
							rekomendasiDPLHData.getPerihalSurat()
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
							),
					d.getStatusVerified()
					);
		}
		else if(d.getNibOssData() != null) {
			NibOssData nibOssData = d.getNibOssData();
			
			return new RegisterDokumen(
					d.getId(), 
					new NibOss(
							masterDokumenData.getId(), 
							masterDokumenData.getNama(), 
							null, 
							nibOssData.getNomor(), 
							nibOssData.getTanggalPenetapan(),
							toDaftarRegisterKbli(nibOssData.getDaftarKbli())
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
							),
					d.getStatusVerified()
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

		RegisterPerusahaanData registerPerusahaanData = entityManager.createNamedQuery("RegisterPerusahaanData.findByNpwp", RegisterPerusahaanData.class)
				.setParameter("npwp", t.getPerusahaan().getId())
				.getSingleResult();				
		registerDokumenData.setPerusahaanData(registerPerusahaanData);	
		
		MasterDokumenData masterDokumenData = new MasterDokumenData();
		masterDokumenData.setId(dokumen.getId());
		registerDokumenData.setDokumenData(masterDokumenData);
		
		registerDokumenData.setTanggalRegistrasi(t.getTanggalRegistrasi());	
		
		Authority uploader = t.getUploader();
		AutorisasiData uploaderData = new AutorisasiData();
		uploaderData.setId(uploader.getId());		
		registerDokumenData.setUploader(uploaderData);		
		
		registerDokumenData.setStatusVerified(t.getStatusVerified());
				
		if(dokumen instanceof SuratArahan) {
			SuratArahan suratArahan = (SuratArahan) dokumen;
			SuratArahanData suratArahanData = new SuratArahanData();
			suratArahanData.setNoSurat(suratArahan.getNoSurat());
			suratArahanData.setTanggalSurat(suratArahan.getTanggalSurat());
			suratArahanData.setPerihalSurat(suratArahan.getPerihalSurat());
			suratArahanData.setUraianKegiatan(suratArahan.getUraianKegiatan());
			registerDokumenData.setSuratArahanData(null);
		}
		else if(dokumen instanceof LampiranSuratArahan) {
			LampiranSuratArahan lampiranSuratArahan = (LampiranSuratArahan) dokumen;
			LampiranSuratArahanData lampiranSuratArahanData = new LampiranSuratArahanData();			
			lampiranSuratArahanData.setNoSurat(lampiranSuratArahan.getNoSuratArahan());
			lampiranSuratArahanData.setTanggalSurat(lampiranSuratArahan.getTanggalSuratArahan());
			
			registerDokumenData.setLampiranSuratArahanData(lampiranSuratArahanData);
		}
		else if(dokumen instanceof AktaPendirian) {
			AktaPendirian aktaPendirian = (AktaPendirian) dokumen;
			AktaPendirianData aktaPendirianData = new AktaPendirianData();			
			aktaPendirianData.setNomor(aktaPendirian.getNomor());
			aktaPendirianData.setTanggal(aktaPendirian.getTanggal());
			aktaPendirianData.setNotaris(aktaPendirian.getNamaNotaris());
			PegawaiData pegawaiData = new PegawaiData();
			pegawaiData.setId(aktaPendirian.getPenanggungJawab().getId());
			aktaPendirianData.setPenanggungJawabData(pegawaiData);
			registerDokumenData.setAktaPendirianData(aktaPendirianData);
		}
		else if(dokumen instanceof RekomendasiUKLUPL) {
			RekomendasiUKLUPL rekomendasiUKLUPL = (RekomendasiUKLUPL) dokumen;
			RekomendasiUKLUPLData rekomendasiUKLUPLData = new RekomendasiUKLUPLData();			
			rekomendasiUKLUPLData.setNoSurat(rekomendasiUKLUPL.getNomor());
			rekomendasiUKLUPLData.setTanggalSurat(rekomendasiUKLUPL.getTanggal());
			rekomendasiUKLUPLData.setPerihalSurat(rekomendasiUKLUPL.getPerihal());
			
			registerDokumenData.setRekomendasiUKLUPLData(rekomendasiUKLUPLData);
		}
		else if(dokumen instanceof RekomendasiDPLH) {
			RekomendasiDPLH rekomendasiDPLH = (RekomendasiDPLH) dokumen;
			RekomendasiDPLHData rekomendasiDPLHData = new RekomendasiDPLHData();			
			rekomendasiDPLHData.setNoSurat(rekomendasiDPLH.getNomor());
			rekomendasiDPLHData.setTanggalSurat(rekomendasiDPLH.getTanggal());
			rekomendasiDPLHData.setPerihalSurat(rekomendasiDPLH.getPerihal());
			
			registerDokumenData.setRekomendasiDPLHData(rekomendasiDPLHData);
		}
		else if(dokumen instanceof NibOss) {
			NibOss nibOss = (NibOss) dokumen;
			NibOssData nibOssData = new NibOssData();		
			nibOssData.setNomor(nibOss.getNomor());
			nibOssData.setTanggalPenetapan(nibOss.getTanggal());
			nibOssData.setDaftarKbli(toDaftarRegisterKbliData(nibOss.getDaftarKbli()));		
			registerDokumenData.setNibOssData(nibOssData);
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
	
	private Pegawai convertPegawaiDataToPegawai(PegawaiData d) {
		JabatanData jabatanData = d.getJabatanData();
		PersonData personData = d.getPersonData();
		AlamatPersonData alamatPersonData = personData != null ? personData.getAlamat() : null;
		Alamat alamat = null;
		
		if(alamatPersonData != null) {
			Propinsi propinsi = alamatPersonData.getPropinsi() != null ?
					new Propinsi(
							alamatPersonData.getPropinsi().getId(), 
							alamatPersonData.getPropinsi().getNama()
							) : null;
			Kabupaten kabupaten = alamatPersonData.getKabupaten() != null ?
					new Kabupaten(
							 alamatPersonData.getKabupaten().getId(), 
							 alamatPersonData.getKabupaten().getNama()
							 ) : null;
			Kecamatan kecamatan = alamatPersonData.getKecamatan() != null ?
					new Kecamatan(
							alamatPersonData.getKecamatan().getId(), 
							alamatPersonData.getKecamatan().getNama()
							) : null;
			Desa desa = alamatPersonData.getDesa() != null ?
					new Desa(
							alamatPersonData.getDesa().getId(), 
							alamatPersonData.getDesa().getNama()
							) : null;
			
			alamat = new Alamat(propinsi, kabupaten, kecamatan, desa, alamatPersonData.getDetailAlamat());
		}
		
		return new Pegawai(
				d.getId(), 
				null, 
				personData != null ?
						new Person(
								personData.getId(), 
								personData.getNama(), 
								null, 
								alamat, 
								null, 
								null
								) : null,
				jabatanData != null ?
						new Jabatan(
								jabatanData.getId(), 
								jabatanData.getNama()
								) : null
				);
	}
		
	private List<RegisterKbli> toDaftarRegisterKbli(List<RegisterKbliData> d) {
		if(!d.isEmpty()) {
			List<RegisterKbli> daftarKbli = new ArrayList<>();
			Iterator<RegisterKbliData> iter = d.iterator();
			
			RegisterKbli item;
			while (iter.hasNext()) {
				RegisterKbliData registerKbliData = (RegisterKbliData) iter.next();
				item = new RegisterKbli(
						registerKbliData.getNib().getNomor(), 
						registerKbliData.getKbli().getId(), 
						registerKbliData.getKbli().getNama()
						);
				daftarKbli.add(item);
			}
			
			return daftarKbli;
		}
		else {
			return null;
		}
	}
	
	private List<RegisterKbliData> toDaftarRegisterKbliData(List<RegisterKbli> t) {
		if(!t.isEmpty()) {
			List<RegisterKbliData> daftarKbliData = new ArrayList<>();
			Iterator<RegisterKbli> iter = t.iterator();
			
			RegisterKbliData item;
			while (iter.hasNext()) {
				RegisterKbli registerKbli = (RegisterKbli) iter.next();
				item = new RegisterKbliData();
				
				NibOssData nibOssData = new NibOssData();
				nibOssData.setNomor(registerKbli.getIdNib());
				item.setNib(nibOssData);
				
				Kbli2020Data kbli2020Data = new Kbli2020Data();
				kbli2020Data.setId(registerKbli.getIdKbli());
				item.setKbli(kbli2020Data);
				daftarKbliData.add(item);
			}
			
			return daftarKbliData;
		}
		else {
			return null;
		}
	}
	
}
