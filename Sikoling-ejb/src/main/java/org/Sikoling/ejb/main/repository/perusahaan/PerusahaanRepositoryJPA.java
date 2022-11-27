package org.Sikoling.ejb.main.repository.perusahaan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.entity.DokumenOss;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Kbli;
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
import org.Sikoling.ejb.main.repository.dokumen.DokumenData;
import org.Sikoling.ejb.main.repository.dokumen.DokumenOssData;
import org.Sikoling.ejb.main.repository.dokumen.KategoriDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.KbliData;
import org.Sikoling.ejb.main.repository.dokumen.RegisterDokumenData;
import org.Sikoling.ejb.main.repository.dokumen.RegisterKbliData;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenData;
import org.Sikoling.ejb.main.repository.kategoripelakuusaha.KategoriPelakuUsahaData;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;
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
	public Perusahaan save(Perusahaan t) {
		PerusahaanData perusahaanData = convertPerusahaanToPerusahaanData(t);
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
	public Perusahaan update(Perusahaan t) {
		PerusahaanData pemrakarsaData = convertPerusahaanToPerusahaanData(t);
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
			entityManager.flush();
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
		
	private List<Kbli> convertDaftarRegisterKbliDataToDaftarKbli(Set<RegisterKbliData> daftaRegisterKbliData) {
		List<Kbli> daftarKbli = new ArrayList<Kbli>();
		
		for(RegisterKbliData item : daftaRegisterKbliData) {
			KbliData kbliData = item.getKbliData();
			Kbli kbli = new Kbli(kbliData.getId(), kbliData.getNama(), kbliData.getKategori());
			daftarKbli.add(kbli);		
		}
		
		return daftarKbli;
	}
		
	private Set<RegisterKbliData> convertDaftarKbliToDaftarRegisterKbliData(List<Kbli> daftarKbli) {
		
		Set<RegisterKbliData> daftarRegisterKbliData = new HashSet<RegisterKbliData>();
		
		for(Kbli item : daftarKbli) {
			RegisterKbliData registerKbliData = new RegisterKbliData();
			KbliData kbliData = new KbliData();
			kbliData.setId(item.getKode());
			kbliData.setNama(item.getNama());
			kbliData.setKategori(item.getKategori());			
			daftarRegisterKbliData.add(registerKbliData);		
		}
		
		return daftarRegisterKbliData;
		
	}
	
	private PerusahaanData convertPerusahaanToPerusahaanData(Perusahaan p) {	
		
		PerusahaanData perusahaanData = new PerusahaanData();	
		perusahaanData.setId(p.getId());
		perusahaanData.setNama(p.getNama());
				
		AlamatPerusahaanData alamatPerusahaanData = new AlamatPerusahaanData();
		alamatPerusahaanData.setKeterangan(p.getAlamat().getKeterangan());
		DesaData desaData = new DesaData();
		desaData.setId(p.getAlamat().getDesa().getId());
		alamatPerusahaanData.setDesaData(desaData);
		KecamatanData kecamatanData = new KecamatanData();
		kecamatanData.setId(p.getAlamat().getKecamatan().getId());
		alamatPerusahaanData.setKecamatanData(kecamatanData);
		KabupatenData kabupatenData = new KabupatenData();
		kabupatenData.setId(p.getAlamat().getKabupaten().getId());
		alamatPerusahaanData.setKabupatenData(kabupatenData);
		PropinsiData propinsiData = new PropinsiData();
		propinsiData.setId(p.getAlamat().getPropinsi().getId());
		alamatPerusahaanData.setPropinsiData(propinsiData);
		perusahaanData.setAlamatPerusahaanData(alamatPerusahaanData);
		
		ModelPerizinanData modelPerizinanData = new ModelPerizinanData();
		modelPerizinanData.setId(p.getModelPerizinan().getId());
		modelPerizinanData.setNama(p.getModelPerizinan().getNama());
		modelPerizinanData.setSingkatan(p.getModelPerizinan().getSingkatan());
		perusahaanData.setModelPerizinanData(modelPerizinanData);
		
		SkalaUsahaData skalaUsahaData = new SkalaUsahaData();
		skalaUsahaData.setId(p.getSkalaUsaha().getId());
		skalaUsahaData.setNama(p.getSkalaUsaha().getNama());
		skalaUsahaData.setSingkatan(p.getSkalaUsaha().getSingkatan());
		perusahaanData.setSkalaUsahaData(skalaUsahaData);
		
		KategoriPelakuUsahaData jenisPelakuUsahaData = new KategoriPelakuUsahaData();
		jenisPelakuUsahaData.setId(p.getPelakuUsaha().getKategoriPelakuUsaha().getId());
		jenisPelakuUsahaData.setNama(p.getPelakuUsaha().getKategoriPelakuUsaha().getNama());
		
		PelakuUsahaData detailPelakuUsahaData = new PelakuUsahaData();
		detailPelakuUsahaData.setId(p.getPelakuUsaha().getId());
		detailPelakuUsahaData.setNama(p.getPelakuUsaha().getNama());
		detailPelakuUsahaData.setSingkatan(p.getPelakuUsaha().getSingkatan());
		detailPelakuUsahaData.setKategoriPelakuUsahaData(jenisPelakuUsahaData);
		perusahaanData.setPelakuUsahaData(detailPelakuUsahaData);
		
		KontakPerusahaanData kontakPerusahaanData = new KontakPerusahaanData();
		kontakPerusahaanData.setEmail(p.getKontak().getEmail());
		kontakPerusahaanData.setFax(p.getKontak().getFax());
		kontakPerusahaanData.setTelepone(p.getKontak().getTelepone());	
		perusahaanData.setKontakPerusahaanData(kontakPerusahaanData);		
		
		List<RegisterDokumen> daftarRegisterDokumen = p.getDaftarRegisterDokumen();
		List<RegisterDokumenData> daftarRegisterDokumenData = new ArrayList<RegisterDokumenData>();
		
		for(RegisterDokumen item : daftarRegisterDokumen) {
			RegisterDokumenData registerDokumenData = new RegisterDokumenData();

			DokumenData dokumenData = new DokumenData();
			dokumenData.setId(item.getDokumen().getId());
			dokumenData.setNama(item.getDokumen().getNama());
			KategoriDokumen kategoriDokumen = item.getDokumen().getKategoriDokumen();
			KategoriDokumenData kategoriDokumenData = new KategoriDokumenData();
			kategoriDokumenData.setId(kategoriDokumen.getId());
			kategoriDokumenData.setNama(kategoriDokumen.getNama());
			kategoriDokumenData.setParent(kategoriDokumen.getParent());
			dokumenData.setKategoriDokumenData(kategoriDokumenData);
			registerDokumenData.setDokumenData(dokumenData);
			registerDokumenData.setLokasiFile(item.getLokasiFile());
			registerDokumenData.setTanggalRegistrasi(item.getTanggalRegistrasi() == null ? LocalDate.now():item.getTanggalRegistrasi());
			registerDokumenData.setStatusBerlaku(item.isStatusBerlaku());
			
			switch (item.getDokumen().getId()) {
			case "010301":
				DokumenOss dokumenOss = (DokumenOss) item.getDokumen();
				DokumenOssData dokumenOssData = new DokumenOssData();
				dokumenOssData.setNib(dokumenOss.getNib());
				dokumenOssData.setTanggalPenerbitan(dokumenOss.getTanggalPenerbitan());
				dokumenOssData.setDaftarRegisterKbliData(convertDaftarKbliToDaftarRegisterKbliData(dokumenOss.getDaftarKbli()));
								
				registerDokumenData.setDokumenOssData(dokumenOssData);
				break;

			default:
				break;
			}
			
			
		}
		
		perusahaanData.setDaftarRegisterDokumenData(daftarRegisterDokumenData);
		perusahaanData.setStatusVerifikasi(p.isStatusVerifikasi());
		
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
		
		for(RegisterDokumenData item : daftarRegisterDokumenData) {			
			DokumenData dokumenData = item.getDokumenData();
			KategoriDokumenData kategoriDokumenData = dokumenData.getKategoriDokumenData();
			Dokumen dokumen = new Dokumen(
					dokumenData.getId(), 
					dokumenData.getNama(), 
					new KategoriDokumen(
							kategoriDokumenData.getId(), 
							kategoriDokumenData.getNama(), 
							kategoriDokumenData.getParent()
							)
					);			
		
			switch (dokumenData.getId()) {
			case "010301":				
				DokumenOssData dokumenOssData = item.getDokumenOssData();						
				DokumenOss dokumenOss = new DokumenOss(
						dokumen, 
						dokumenOssData.getNib(), 
						dokumenOssData.getTanggalPenerbitan(), 
						convertDaftarRegisterKbliDataToDaftarKbli(dokumenOssData.getDaftarRegisterKbliData())
						);			
				daftarRegisterDokumen.add(
						new RegisterDokumen(
								dokumenOss, 
								item.getLokasiFile(), 
								item.getTanggalRegistrasi(), 
								item.isStatusBerlaku()
								)
						);
				break;
			default:
				break;
			}			
		}
		
		return new Perusahaan( 
				d.getId(), d.getNama(), modelPerizinan, skalaUsaha, pelakuUsaha, 
				alamatPerusahaan, kontakPerusahaan, daftarRegisterDokumen, d.isStatusVerifikasi()
				);
	}
	
}
