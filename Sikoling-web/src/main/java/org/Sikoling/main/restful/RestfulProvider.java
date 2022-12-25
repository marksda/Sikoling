package org.Sikoling.main.restful;

import java.util.Properties;

import org.Sikoling.ejb.abstraction.repository.IAuthorityRepository;
import org.Sikoling.ejb.abstraction.repository.IBidangUsahaRepository;
import org.Sikoling.ejb.abstraction.repository.IDesaRepository;
import org.Sikoling.ejb.abstraction.repository.IMasterDokumenRepository;
import org.Sikoling.ejb.abstraction.repository.IPelakuUsahaRepository;
import org.Sikoling.ejb.abstraction.repository.IJabatanRepository;
import org.Sikoling.ejb.abstraction.repository.IJenisKelaminRepository;
import org.Sikoling.ejb.abstraction.repository.IKategoriPelakuUsahaRepository;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanRepository;
import org.Sikoling.ejb.abstraction.repository.IKabupatenRepository;
import org.Sikoling.ejb.abstraction.repository.IKategoriDokumenRepository;
import org.Sikoling.ejb.abstraction.repository.IKategoriProdukRepository;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;
import org.Sikoling.ejb.abstraction.repository.IKecamatanRepository;
import org.Sikoling.ejb.abstraction.repository.IModelPerizinanRepository;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;
import org.Sikoling.ejb.abstraction.repository.IPenanggungJawabRepository;
import org.Sikoling.ejb.abstraction.repository.IPersonRepository;
import org.Sikoling.ejb.abstraction.repository.IProdukRepository;
import org.Sikoling.ejb.abstraction.repository.IPropinsiRepository;
import org.Sikoling.ejb.abstraction.repository.IRegisterDokumenRepository;
import org.Sikoling.ejb.abstraction.repository.IRegisterKbliRepository;
import org.Sikoling.ejb.abstraction.repository.ISkalaUsahaRepository;
import org.Sikoling.ejb.abstraction.repository.IUserRepository;
import org.Sikoling.ejb.abstraction.service.propinsi.IPropinsiService;
import org.Sikoling.ejb.abstraction.service.propinsi.PropinsiService;
import org.Sikoling.ejb.abstraction.service.security.IOpenIdConnectService;
import org.Sikoling.ejb.abstraction.service.security.ITokenValidationService;
import org.Sikoling.ejb.abstraction.service.security.OpenIdConnectionService;
import org.Sikoling.ejb.abstraction.service.sex.IJenisKelaminService;
import org.Sikoling.ejb.abstraction.service.sex.JenisKelaminService;
import org.Sikoling.ejb.abstraction.service.skalausaha.ISkalaUsahaService;
import org.Sikoling.ejb.abstraction.service.skalausaha.SkalaUsahaService;
import org.Sikoling.ejb.abstraction.service.user.IUserService;
import org.Sikoling.ejb.abstraction.service.user.UserService;
import org.Sikoling.ejb.abstraction.service.desa.IDesaService;
import org.Sikoling.ejb.abstraction.service.dokumen.MasterDokumenService;
import org.Sikoling.ejb.abstraction.service.dokumen.IMasterDokumenService;
import org.Sikoling.ejb.abstraction.service.dokumen.IKategoriDokumenService;
import org.Sikoling.ejb.abstraction.service.dokumen.IKbliService;
import org.Sikoling.ejb.abstraction.service.dokumen.IRegisterDokumenService;
import org.Sikoling.ejb.abstraction.service.dokumen.IRegisterKbliService;
import org.Sikoling.ejb.abstraction.service.dokumen.KategoriDokumenService;
import org.Sikoling.ejb.abstraction.service.dokumen.KbliService;
import org.Sikoling.ejb.abstraction.service.dokumen.RegisterDokumenService;
import org.Sikoling.ejb.abstraction.service.dokumen.RegisterKbliService;
import org.Sikoling.ejb.abstraction.service.file.IStorageService;
import org.Sikoling.ejb.abstraction.service.jabatan.IJabatanService;
import org.Sikoling.ejb.abstraction.service.jabatan.JabatanService;
import org.Sikoling.ejb.abstraction.service.authority.AuthorityService;
import org.Sikoling.ejb.abstraction.service.authority.IAuthorityService;
import org.Sikoling.ejb.abstraction.service.bidangusaha.BidangUsahaService;
import org.Sikoling.ejb.abstraction.service.bidangusaha.IBidangUsahaService;
import org.Sikoling.ejb.abstraction.service.desa.DesaService;
import org.Sikoling.ejb.abstraction.service.kabupaten.IKabupatenService;
import org.Sikoling.ejb.abstraction.service.kabupaten.KabupatenService;
import org.Sikoling.ejb.abstraction.service.kategoripelakuusaha.IKategoriPelakuUsahaServices;
import org.Sikoling.ejb.abstraction.service.kategoripelakuusaha.KategoriPelakuUsahaService;
import org.Sikoling.ejb.abstraction.service.kategoriproduk.IKategoriProdukService;
import org.Sikoling.ejb.abstraction.service.kategoriproduk.KategoriProdukService;
import org.Sikoling.ejb.abstraction.service.kecamatan.IKecamatanService;
import org.Sikoling.ejb.abstraction.service.kecamatan.KecamatanService;
import org.Sikoling.ejb.abstraction.service.modelperizinan.IModelPerizinanService;
import org.Sikoling.ejb.abstraction.service.modelperizinan.ModelPerizinanService;
import org.Sikoling.ejb.abstraction.service.pelakuusaha.PelakuUsahaService;
import org.Sikoling.ejb.abstraction.service.pelakuusaha.IPelakuUsahaServices;
import org.Sikoling.ejb.abstraction.service.penanggungjawab.IPenanggungJawabService;
import org.Sikoling.ejb.abstraction.service.penanggungjawab.PenanggungJawabService;
import org.Sikoling.ejb.abstraction.service.permohonan.IKategoriPermohonanService;
import org.Sikoling.ejb.abstraction.service.permohonan.KategoriPermohonanService;
import org.Sikoling.ejb.abstraction.service.person.IPersonService;
import org.Sikoling.ejb.abstraction.service.person.PersonService;
import org.Sikoling.ejb.abstraction.service.perusahaan.IRegisterPerusahaanService;
import org.Sikoling.ejb.abstraction.service.perusahaan.RegisterPerusahaanService;
import org.Sikoling.ejb.abstraction.service.produk.IProdukService;
import org.Sikoling.ejb.abstraction.service.produk.ProdukService;
import org.Sikoling.ejb.main.Infrastructure;
import org.Sikoling.ejb.main.storage.disk.DiskStorageService;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.client.Client;

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
	public IRegisterPerusahaanService getPerusahaanService(@Infrastructure IRegisterPerusahaanRepository registerPerusahaanRepository) {
		return new RegisterPerusahaanService(registerPerusahaanRepository);		
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
	public IKategoriPelakuUsahaServices getKategoriPelakuUsahaServices(
			@Infrastructure IKategoriPelakuUsahaRepository kategoriPelakuUsahaRepository) {
		return new KategoriPelakuUsahaService(kategoriPelakuUsahaRepository);
	}
	
	@Produces
	public IModelPerizinanService getModelPerizinanService(
			@Infrastructure IModelPerizinanRepository modelPerizinanRepository) {
		return new ModelPerizinanService(modelPerizinanRepository);
	}
	
	@Produces
	public ISkalaUsahaService getSkalaUsahaService(
			@Infrastructure ISkalaUsahaRepository skalaUsahaRepository) {
		return new SkalaUsahaService(skalaUsahaRepository);
	}
		
	@Produces
	public IPelakuUsahaServices getPelakuUsahaServices(
			@Infrastructure IPelakuUsahaRepository pelakuUsahaRepository) {
		return new PelakuUsahaService(pelakuUsahaRepository);
	}
	
	@Produces
	public IKbliService getKbliService(
			@Infrastructure IKbliRepository kbliRepository) {
		return new KbliService(kbliRepository);
	}
	
	@Produces
	public IRegisterKbliService getRegisterKbliService(
			@Infrastructure IRegisterKbliRepository registerKbliRepository) {
		return new RegisterKbliService(registerKbliRepository);
	}
		
	@Produces
	public IKategoriDokumenService getKategoriDokumenService(
			@Infrastructure IKategoriDokumenRepository kategoriDokumenRepository) {
		return new KategoriDokumenService(kategoriDokumenRepository);
	}
	
	@Produces
	public IMasterDokumenService getDokumenService(
			@Infrastructure IMasterDokumenRepository dokumenRepository) {
		return new MasterDokumenService(dokumenRepository);
	}
	
	@Produces
	public IRegisterDokumenService getRegisterDokumenService(
			@Infrastructure IRegisterDokumenRepository registerDokumenRepository) {
		return new RegisterDokumenService(registerDokumenRepository);
	}
	
	@Produces
	public IAuthorityService getAuthorityService(
			@Infrastructure IAuthorityRepository authorityRepository) {
		return new AuthorityService(authorityRepository);
	}
	
	@Produces
	public IKategoriPermohonanService getKategoriPermohonanService(
			@Infrastructure IKategoriPermohonanRepository kategoriPermohonanRepository) {
		return new KategoriPermohonanService(kategoriPermohonanRepository);
	}
	
	@Produces
	public IOpenIdConnectService getOpenIdConnectService(ITokenValidationService tokenValidationService, Client client, Properties properties) {
		return new OpenIdConnectionService(
				tokenValidationService, client, properties.getProperty("SSO_TOKEN_URL"),
				properties.getProperty("SSO_CLIENT_ID"),
				properties.getProperty("SSO_CLIENT_SECRET"));
	}
	
	@Produces
	public IStorageService getStorageService(Properties properties) {
		return new DiskStorageService(properties.getProperty("STORAGE_PATH"));
	}
	
}