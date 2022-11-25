package org.Sikoling.ejb.main.repository.dokumen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Dokumen;
import org.Sikoling.ejb.abstraction.entity.DokumenOss;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.KategoriPelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Kbli;
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
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanData;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.pelakuusaha.KategoriPelakuUsahaData;
import org.Sikoling.ejb.main.repository.perusahaan.AlamatPerusahaanData;
import org.Sikoling.ejb.main.repository.perusahaan.KontakPerusahaanData;
import org.Sikoling.ejb.main.repository.perusahaan.PerusahaanData;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiData;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaData;

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
	
	private Kbli convertRegisterKbliDataToKbli(RegisterKbliData d) {
		return new Kbli(d.getKode(), d.getNama());
	}
	
	private RegisterKbliData convertKbliToRegisterKbliData(Kbli kbli, String nib) {
		RegisterKbliData registerKbliData = new RegisterKbliData();
		registerKbliData.setKode(kbli.getKode());
		registerKbliData.setNama(kbli.getNama());
				
		return registerKbliData;
	}
	
	private Dokumen convertDokumenDataToDokumen(DokumenData d) {
		KategoriDokumenData kategoriDokumenData = d.getKategori();
		return new Dokumen(
				d.getId(), 
				d.getNama(),
				new KategoriDokumen(
						kategoriDokumenData.getId(), 
						kategoriDokumenData.getNama(), 
						kategoriDokumenData.getParent())
				);
	}
	
	private Perusahaan convertPerusahaanDataToPerusahaan(PerusahaanData d) {
		ModelPerizinanData modelPerizinanData = d.getModelPerizinanData();
		SkalaUsahaData skalaUsahaData = d.getSkalaUsaha();
		PelakuUsahaData pelakuUsahaData = d.getPelakuUsahaData();
		KategoriPelakuUsahaData kategoriPelakuUsahaData = pelakuUsahaData.getKategoriPelakuUsahaData();
		AlamatPerusahaanData alamatPerusahaanData = d.getAlamatPerusahaanData();
		PropinsiData propinsiDataPerusahaan = alamatPerusahaanData.getPropinsi();
		KabupatenData kabupatenDataPerusahaan = alamatPerusahaanData.getKabupaten();
		KecamatanData kecamatanDataPerusahaan = alamatPerusahaanData.getKecamatan();
		DesaData desaDataPerusahaan = alamatPerusahaanData.getDesa();
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
						)
				);
	}
	
	private RegisterDokumen convertRegisterDokumenDataToRegisterDokumen(RegisterDokumenData d) {
		RegisterDokumen registerDokumen;
		DokumenData masterDokumenData = d.getDokumenData();
		KategoriDokumenData kategoriDokumenData = masterDokumenData.getKategori();
		
		switch (d.getDokumenData().getId()) {
		case "010301":
			RegisterDokumenOssData registerDokumenOssData = d.getRegisterDokumenOssData();
			DokumenOss dokumenOss = new DokumenOss(
					new Dokumen(
							masterDokumenData.getId(), 
							masterDokumenData.getNama(), 
							new KategoriDokumen(
									kategoriDokumenData.getId(), 
									kategoriDokumenData.getNama(), 
									kategoriDokumenData.getParent()
									)
							), 
					registerDokumenOssData.getNib(), 
					null, 
					null
					);
			registerDokumen = new RegisterDokumen(dokumenOss, null, null, null, false, null);
//			RegisterDokumenOssData registerDokumenOssData = d.getDokumenOssData();			
//			DokumenOss dokumen = new DokumenOss(null, null, null, null);
//					
//					
//					
//			RegisterDokumenOssData registerDokumenOssData = d.getRegisterDokumenOssData();
//			detailDokumen = new RegisterDokumenOss(
//					convertDokumenDataToDokumen(dokumenData),  
//					d.getLokasiFile(), 
//					registerDokumenOssData.getNib(), 
//					registerDokumenOssData.getTanggal(), 
//					registerDokumenOssData
//						.getDaftarKbli()
//						.stream()
//						.map(t -> convertRegisterKbliDataToKbli(t))
//						.collect(Collectors.toList())
//					);			
			break;
		default:
			registerDokumen = null;
			break;
		}		
		
		return registerDokumen;

	}
	
	private RegisterDokumenData convertRegisterDokumenToRegisterDokumenData(RegisterDokumen t) {
		
		RegisterDokumenData registerDokumenData = new RegisterDokumenData();
		PerusahaanData perusahaanData = new PerusahaanData();
		registerDokumenData.setPerusahaan(perusahaanData);
		
		DokumenData masterDokumenData = new DokumenData();
		masterDokumenData.setId(t.getDokumen().getId());
		registerDokumenData.setDokumenData(masterDokumenData);				
		registerDokumenData.setTanggalRegistrasi(t.getTanggalRegistrasi());		
		registerDokumenData.setStatusBerlaku(t.isStatusBerlaku());
		

		switch (t.getDokumen().getId()) {
		case "010301":
			DokumenOss dokumenOss = (DokumenOss) t.getDokumen();
			String nib = dokumenOss.getNib();
			RegisterDokumenOssData registerDokumenOssData = new RegisterDokumenOssData();			
			registerDokumenOssData.setNib(nib);
			registerDokumenOssData.setTanggalPenerbitan(dokumenOss.getTanggalPenerbitan());
			registerDokumenOssData.setRegisterDokumenData(registerDokumenData);
			List<Kbli> daftarKbli = dokumenOss.getDaftarKbli();
			Set<RegisterKbliData> daftarKbliData = new HashSet<RegisterKbliData>();
			
			for(int i = 0; i < daftarKbli.size(); i++) {
				daftarKbliData.add(convertKbliToRegisterKbliData(daftarKbli.get(i), nib));
			}
			
			registerDokumenOssData.setDaftarRegisterKbliData(daftarKbliData);
			
			registerDokumenData.setRegisterDokumenOssData(registerDokumenOssData);
			break;
		default:
			break;
		}
		
		return registerDokumenData;
	}
		
	@Override
	public RegisterDokumen updateById(String id, RegisterDokumen registerDokumen) {
		RegisterDokumenData updateData = convertRegisterDokumenToRegisterDokumenData(registerDokumen);
		RegisterDokumenData registerDokumenData = entityManager.find(RegisterDokumenData.class, id);
		registerDokumenData.setId(id);
		registerDokumenData.setPerusahaan(updateData.getPerusahaan());
		registerDokumenData.setDokumen(updateData.getDokumen());
		registerDokumenData.setTanggalUpload(updateData.getTanggalUpload());
		registerDokumenData.setIsBerlaku(updateData.getIsBerlaku());
		
		return convertRegisterDokumenDataToRegisterDokumen(registerDokumenData);
	}
		
	@Override
	public DeleteResponse delete(String id) {
		RegisterDokumenData registerDokumenData = entityManager.find(RegisterDokumenData.class, id);
		entityManager.remove(registerDokumenData);
		return new DeleteResponse(true, id);
	}
	
}
