package org.Sikoling.main.restful;

import java.util.Properties;

import org.Sikoling.ejb.abstraction.repository.IBentukUsahaRepository;
import org.Sikoling.ejb.abstraction.repository.IBidangUsahaRepository;
import org.Sikoling.ejb.abstraction.repository.IDesaRepository;
import org.Sikoling.ejb.abstraction.repository.IJabatanRepository;
import org.Sikoling.ejb.abstraction.repository.IJenisKelaminRepository;
import org.Sikoling.ejb.abstraction.repository.IJenisPelakuUsahaRepository;
import org.Sikoling.ejb.abstraction.repository.IKabupatenRepository;
import org.Sikoling.ejb.abstraction.repository.IKategoriProdukRepository;
import org.Sikoling.ejb.abstraction.repository.IKecamatanRepository;
import org.Sikoling.ejb.abstraction.repository.IPemrakarsaRepository;
import org.Sikoling.ejb.abstraction.repository.IPenanggungJawabRepository;
import org.Sikoling.ejb.abstraction.repository.IPersonRepository;
import org.Sikoling.ejb.abstraction.repository.IProdukRepository;
import org.Sikoling.ejb.abstraction.repository.IPropinsiRepository;
import org.Sikoling.ejb.abstraction.repository.IUserRepository;
import org.Sikoling.ejb.abstraction.service.propinsi.IPropinsiService;
import org.Sikoling.ejb.abstraction.service.propinsi.PropinsiService;
import org.Sikoling.ejb.abstraction.service.sex.IJenisKelaminService;
import org.Sikoling.ejb.abstraction.service.sex.JenisKelaminService;
import org.Sikoling.ejb.abstraction.service.user.IUserService;
import org.Sikoling.ejb.abstraction.service.user.UserService;
import org.Sikoling.ejb.abstraction.service.desa.IDesaService;
import org.Sikoling.ejb.abstraction.service.file.IStorageService;
import org.Sikoling.ejb.abstraction.service.jabatan.IJabatanService;
import org.Sikoling.ejb.abstraction.service.jabatan.JabatanService;
import org.Sikoling.ejb.abstraction.service.jenispelakuusaha.IJenisPelakuUsahaService;
import org.Sikoling.ejb.abstraction.service.jenispelakuusaha.JenisPelakuUsahaService;
import org.Sikoling.ejb.abstraction.service.bentukusaha.BentukUsahaService;
import org.Sikoling.ejb.abstraction.service.bentukusaha.IBentukUsahaService;
import org.Sikoling.ejb.abstraction.service.bidangusaha.BidangUsahaService;
import org.Sikoling.ejb.abstraction.service.bidangusaha.IBidangUsahaService;
import org.Sikoling.ejb.abstraction.service.desa.DesaService;
import org.Sikoling.ejb.abstraction.service.kabupaten.IKabupatenService;
import org.Sikoling.ejb.abstraction.service.kabupaten.KabupatenService;
import org.Sikoling.ejb.abstraction.service.kategoriproduk.IKategoriProdukService;
import org.Sikoling.ejb.abstraction.service.kategoriproduk.KategoriProdukService;
import org.Sikoling.ejb.abstraction.service.kecamatan.IKecamatanService;
import org.Sikoling.ejb.abstraction.service.kecamatan.KecamatanService;
import org.Sikoling.ejb.abstraction.service.pemrakarsa.IPemrakarsaService;
import org.Sikoling.ejb.abstraction.service.pemrakarsa.PemrakarsaService;
import org.Sikoling.ejb.abstraction.service.penanggungjawab.IPenanggungJawabService;
import org.Sikoling.ejb.abstraction.service.penanggungjawab.PenanggungJawabService;
import org.Sikoling.ejb.abstraction.service.person.IPersonService;
import org.Sikoling.ejb.abstraction.service.person.PersonService;
import org.Sikoling.ejb.abstraction.service.produk.IProdukService;
import org.Sikoling.ejb.abstraction.service.produk.ProdukService;
import org.Sikoling.ejb.main.Infrastructure;
import org.Sikoling.ejb.main.storage.disk.DiskStorageService;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Produces;

@Stateless
@LocalBean
public class RestfulProvider {
	
	@Produces
	public IPropinsiService getPropinsiService(
			@Infrastructure IPropinsiRepository propinsiRepository) {
		return new PropinsiService(propinsiRepository);
	}
	
	@Produces
	public IKabupatenService getKabupatenService(
			@Infrastructure IKabupatenRepository kabupatenRepository) {
		return new KabupatenService(kabupatenRepository);
	}
	
	@Produces
	public IKecamatanService getKecamatanService(
			@Infrastructure IKecamatanRepository kecamatanRepository) {
		return new KecamatanService(kecamatanRepository);
	}
	
	@Produces
	public IDesaService getDesaService(
			@Infrastructure IDesaRepository desaRepository) {
		return new DesaService(desaRepository);
	}
	
	@Produces
	public IBentukUsahaService getBentukUsahaService(
			@Infrastructure IBentukUsahaRepository bentukUsahaRepository) {
		return new BentukUsahaService(bentukUsahaRepository);
	}
	
	@Produces
	public IBidangUsahaService getBidangUsahaService(
			@Infrastructure IBidangUsahaRepository bidangUsahaRepository) {
		return new BidangUsahaService(bidangUsahaRepository);
	}
	
	@Produces
	public IJabatanService getJabatanService(
			@Infrastructure IJabatanRepository jabatanRepository) {
		return new JabatanService(jabatanRepository);
	}
	
	@Produces
	public IJenisKelaminService getJenisKelaminService(
			@Infrastructure IJenisKelaminRepository jenisKelaminRepository) {
		return new JenisKelaminService(jenisKelaminRepository);
	}
	
	@Produces 
	public IPenanggungJawabService getPenanggungJawabService(
			@Infrastructure IPenanggungJawabRepository penanggungJawabRepository) {
		return new PenanggungJawabService(penanggungJawabRepository);
	}
	
	@Produces
	public IPersonService getPersonService(
			@Infrastructure IPersonRepository personRepository) {
		return new PersonService(personRepository);
	}
	
	@Produces
	public IUserService getUserService(@Infrastructure IUserRepository userRepository) {
		return new UserService(userRepository);
	}
	
	@Produces
	public IPemrakarsaService getPemrakarsaService(@Infrastructure IPemrakarsaRepository pemrakarsaRepository) {
		return new PemrakarsaService(pemrakarsaRepository);		
	}
	
	@Produces
	public IKategoriProdukService getKategoriProdukService( 
			@Infrastructure IKategoriProdukRepository kategoriProdukRepository) {
		return new KategoriProdukService(kategoriProdukRepository);
	}
	
	@Produces
	public IProdukService getProdukService(
			@Infrastructure IProdukRepository produkRepository) {
		return new ProdukService(produkRepository);
	}
	
	@Produces
	public IJenisPelakuUsahaService getJenisPelakuUsahaService(
			@Infrastructure IJenisPelakuUsahaRepository jenisPelakuUsahaRepository) {
		return new JenisPelakuUsahaService(jenisPelakuUsahaRepository);
	}
	
	@Produces
	public IStorageService getStorageService(Properties properties) {
		return new DiskStorageService(properties.getProperty("STORAGE_PATH"));
	}
	
}