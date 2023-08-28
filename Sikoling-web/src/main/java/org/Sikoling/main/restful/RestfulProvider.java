package org.Sikoling.main.restful;

import org.Sikoling.ejb.abstraction.repository.IOtoritasPerusahaanRepository;
import org.Sikoling.ejb.abstraction.repository.IAutorityRepository;
import org.Sikoling.ejb.abstraction.repository.IBidangUsahaRepository;
import org.Sikoling.ejb.abstraction.repository.IDesaRepository;
import org.Sikoling.ejb.abstraction.repository.IFlowLogRepository;
import org.Sikoling.ejb.abstraction.repository.IHakAksesRepository;
import org.Sikoling.ejb.abstraction.repository.IDokumenRepository;
import org.Sikoling.ejb.abstraction.repository.IPelakuUsahaRepository;
import org.Sikoling.ejb.abstraction.repository.IJabatanRepository;
import org.Sikoling.ejb.abstraction.repository.IJenisKelaminRepository;
import org.Sikoling.ejb.abstraction.repository.IKategoriPelakuUsahaRepository;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanRepository;
import org.Sikoling.ejb.abstraction.repository.IKategoriPermohonanSuratArahahanRepository;
import org.Sikoling.ejb.abstraction.repository.IKabupatenRepository;
import org.Sikoling.ejb.abstraction.repository.IKategoriDokumenRepository;
import org.Sikoling.ejb.abstraction.repository.IKategoriLogRepository;
import org.Sikoling.ejb.abstraction.repository.IKategoriProdukRepository;
import org.Sikoling.ejb.abstraction.repository.IKbliRepository;
import org.Sikoling.ejb.abstraction.repository.IKecamatanRepository;
import org.Sikoling.ejb.abstraction.repository.IModelPerizinanRepository;
import org.Sikoling.ejb.abstraction.repository.IOnlyOfficeRepository;
import org.Sikoling.ejb.abstraction.repository.IPegawaiPerusahaanRepository;
import org.Sikoling.ejb.abstraction.repository.IRegisterPerusahaanRepository;
import org.Sikoling.ejb.abstraction.repository.IPenanggungJawabRepository;
import org.Sikoling.ejb.abstraction.repository.IPersonRepository;
import org.Sikoling.ejb.abstraction.repository.IPosisiTahapPemberkasanRepository;
import org.Sikoling.ejb.abstraction.repository.IProdukRepository;
import org.Sikoling.ejb.abstraction.repository.IPropinsiRepository;
import org.Sikoling.ejb.abstraction.repository.IRegisterDokumenRepository;
import org.Sikoling.ejb.abstraction.repository.IRegisterKbliRepository;
import org.Sikoling.ejb.abstraction.repository.IRegisterPermohonanRepository;
import org.Sikoling.ejb.abstraction.repository.ISkalaUsahaRepository;
import org.Sikoling.ejb.abstraction.repository.IStatusFlowLogRepository;
import org.Sikoling.ejb.abstraction.repository.IStatusPengurusPermohonanRepository;
import org.Sikoling.ejb.abstraction.repository.ITokenValidation;
import org.Sikoling.ejb.abstraction.security.IOnlyofficeTokenManager;
import org.Sikoling.ejb.abstraction.repository.IKeyCloackUserRepository;
import org.Sikoling.ejb.abstraction.repository.ILocalStorageRepository;
import org.Sikoling.ejb.abstraction.service.propinsi.IPropinsiService;
import org.Sikoling.ejb.abstraction.service.propinsi.PropinsiService;
import org.Sikoling.ejb.abstraction.service.security.IOnlyofficeTokenManagerService;
import org.Sikoling.ejb.abstraction.service.security.ITokenValidationService;
import org.Sikoling.ejb.abstraction.service.security.OnlyofficeTokenManajerService;
import org.Sikoling.ejb.abstraction.service.security.TokenValidationService;
import org.Sikoling.ejb.abstraction.service.sex.IJenisKelaminService;
import org.Sikoling.ejb.abstraction.service.sex.JenisKelaminService;
import org.Sikoling.ejb.abstraction.service.skalausaha.ISkalaUsahaService;
import org.Sikoling.ejb.abstraction.service.skalausaha.SkalaUsahaService;
import org.Sikoling.ejb.abstraction.service.storage.ILocalStorageService;
import org.Sikoling.ejb.abstraction.service.storage.LocalStorageService;
import org.Sikoling.ejb.abstraction.service.desa.IDesaService;
import org.Sikoling.ejb.abstraction.service.dokumen.DokumenService;
import org.Sikoling.ejb.abstraction.service.dokumen.IDokumenService;
import org.Sikoling.ejb.abstraction.service.dokumen.IKategoriDokumenService;
import org.Sikoling.ejb.abstraction.service.dokumen.IKbliService;
import org.Sikoling.ejb.abstraction.service.dokumen.IRegisterDokumenService;
import org.Sikoling.ejb.abstraction.service.dokumen.IRegisterKbliService;
import org.Sikoling.ejb.abstraction.service.dokumen.KategoriDokumenService;
import org.Sikoling.ejb.abstraction.service.dokumen.KbliService;
import org.Sikoling.ejb.abstraction.service.dokumen.RegisterDokumenService;
import org.Sikoling.ejb.abstraction.service.dokumen.RegisterKbliService;
import org.Sikoling.ejb.abstraction.service.hakakses.HakAksesService;
import org.Sikoling.ejb.abstraction.service.hakakses.IHakAksesService;
import org.Sikoling.ejb.abstraction.service.jabatan.IJabatanService;
import org.Sikoling.ejb.abstraction.service.jabatan.JabatanService;
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
import org.Sikoling.ejb.abstraction.service.keycloackuser.IKeyCloackUserService;
import org.Sikoling.ejb.abstraction.service.keycloackuser.KeyCloackUserService;
import org.Sikoling.ejb.abstraction.service.log.FlowLogService;
import org.Sikoling.ejb.abstraction.service.log.IFlowLogService;
import org.Sikoling.ejb.abstraction.service.log.IKategoriLogService;
import org.Sikoling.ejb.abstraction.service.log.IStatusFlowLogServices;
import org.Sikoling.ejb.abstraction.service.log.KategoriLogService;
import org.Sikoling.ejb.abstraction.service.log.StatusFlowLogServices;
import org.Sikoling.ejb.abstraction.service.modelperizinan.IModelPerizinanService;
import org.Sikoling.ejb.abstraction.service.modelperizinan.ModelPerizinanService;
import org.Sikoling.ejb.abstraction.service.onlyoffice.IOnlyofficeService;
import org.Sikoling.ejb.abstraction.service.onlyoffice.OnlyofficeService;
import org.Sikoling.ejb.abstraction.service.otoritas.OtoritasService;
import org.Sikoling.ejb.abstraction.service.otoritas.OtoritasPerusahaanService;
import org.Sikoling.ejb.abstraction.service.otoritas.IOtoritasPerusahaanService;
import org.Sikoling.ejb.abstraction.service.otoritas.IOtoritasService;
import org.Sikoling.ejb.abstraction.service.pegawai.IPegawaiPerusahaanService;
import org.Sikoling.ejb.abstraction.service.pegawai.PegawaiPerusahaanService;
import org.Sikoling.ejb.abstraction.service.pelakuusaha.PelakuUsahaService;
import org.Sikoling.ejb.abstraction.service.pelakuusaha.IPelakuUsahaServices;
import org.Sikoling.ejb.abstraction.service.penanggungjawab.IPenanggungJawabService;
import org.Sikoling.ejb.abstraction.service.penanggungjawab.PenanggungJawabService;
import org.Sikoling.ejb.abstraction.service.permohonan.IKategoriPermohonanService;
import org.Sikoling.ejb.abstraction.service.permohonan.IKategoriPermohonanSuratArahanService;
import org.Sikoling.ejb.abstraction.service.permohonan.IPosisiTahapPemberkasanService;
import org.Sikoling.ejb.abstraction.service.permohonan.IRegisterPermohonanService;
import org.Sikoling.ejb.abstraction.service.permohonan.IStatusPengurusPermohonanService;
import org.Sikoling.ejb.abstraction.service.permohonan.KategoriPermohonanService;
import org.Sikoling.ejb.abstraction.service.permohonan.KategoriPermohonanSuratArahanService;
import org.Sikoling.ejb.abstraction.service.permohonan.PosisiTahapPemberkasanService;
import org.Sikoling.ejb.abstraction.service.permohonan.RegisterPermohonanService;
import org.Sikoling.ejb.abstraction.service.permohonan.StatusPengurusPermohonanService;
import org.Sikoling.ejb.abstraction.service.person.IPersonService;
import org.Sikoling.ejb.abstraction.service.person.PersonService;
import org.Sikoling.ejb.abstraction.service.perusahaan.IRegisterPerusahaanService;
import org.Sikoling.ejb.abstraction.service.perusahaan.RegisterPerusahaanService;
import org.Sikoling.ejb.abstraction.service.produk.IProdukService;
import org.Sikoling.ejb.abstraction.service.produk.ProdukService;
import org.Sikoling.ejb.main.Infrastructure;
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
	public ITokenValidationService getTokenValidationService(@Infrastructure ITokenValidation tokenValidationRepository) {
		return new TokenValidationService(tokenValidationRepository);
	}
	
	@Produces
	public IKeyCloackUserService getUserService(@Infrastructure IKeyCloackUserRepository keyCloackUserRepository) {
		return new KeyCloackUserService(keyCloackUserRepository);
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
	public ISkalaUsahaService getSkalaUsahaService(@Infrastructure ISkalaUsahaRepository skalaUsahaRepository) {
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
	public IDokumenService getDokumenService(
			@Infrastructure IDokumenRepository dokumenRepository) {
		return new DokumenService(dokumenRepository);
	}
	
	@Produces
	public IRegisterDokumenService getRegisterDokumenService(
			@Infrastructure IRegisterDokumenRepository registerDokumenRepository) {
		return new RegisterDokumenService(registerDokumenRepository);
	}
	
	@Produces
	public IHakAksesService getHakAksesService(
			@Infrastructure IHakAksesRepository hakAksesRepository) {
		return new HakAksesService(hakAksesRepository);
	}
	
	@Produces
	public IOtoritasService getAuthorityService(
			@Infrastructure IAutorityRepository authorityRepository) {
		return new OtoritasService(authorityRepository);
	}
	
	@Produces
	public IOtoritasPerusahaanService getAutorityPerusahaanService(
			@Infrastructure IOtoritasPerusahaanRepository otoritasPerusahaanRepository) {
		return new OtoritasPerusahaanService(otoritasPerusahaanRepository);
	}
	
	@Produces
	public IKategoriPermohonanService getKategoriPermohonanService(
			@Infrastructure IKategoriPermohonanRepository kategoriPermohonanRepository) {
		return new KategoriPermohonanService(kategoriPermohonanRepository);
	}
	
	@Produces
	public IRegisterPermohonanService getRegisterPermohonanService(
			@Infrastructure IRegisterPermohonanRepository registerPermohonanRepository) {
		return new RegisterPermohonanService(registerPermohonanRepository);
	}
	
	@Produces
	public IStatusFlowLogServices getStatusFlowLogServices(@Infrastructure IStatusFlowLogRepository statusFlowLogRepository) {
		return new StatusFlowLogServices(statusFlowLogRepository);
	}
	
	@Produces
	public IFlowLogService getFlowLogService(
			@Infrastructure IFlowLogRepository flowLogRepository) {
		return new FlowLogService(flowLogRepository);
	}
	
	@Produces
	public IKategoriLogService getIKategoriLogService(
			@Infrastructure IKategoriLogRepository kategoriLogRepository) {
		return new KategoriLogService(kategoriLogRepository);
	}
	
	@Produces
	public IKategoriPermohonanSuratArahanService getKategoriPermohonanSuratArahanService(
			@Infrastructure IKategoriPermohonanSuratArahahanRepository kategoriPermohonanSuratArahahanRepository) {
		return new KategoriPermohonanSuratArahanService(kategoriPermohonanSuratArahahanRepository);
	}
	
	@Produces
	public IStatusPengurusPermohonanService getStatusPengurusPermohonanService(
			@Infrastructure IStatusPengurusPermohonanRepository statusPengurusPermohonanRepository) {
		return new StatusPengurusPermohonanService(statusPengurusPermohonanRepository);
	}
	
	@Produces
	public IPegawaiPerusahaanService getPegawaiPerusahaanService(
			@Infrastructure IPegawaiPerusahaanRepository pegawaiPerusahaanRepository) {
		return new PegawaiPerusahaanService(pegawaiPerusahaanRepository);
	}
	
	@Produces
	public IPosisiTahapPemberkasanService getPosisiTahapPemberkasanService(
			@Infrastructure IPosisiTahapPemberkasanRepository posisiTahapPemberkasanRepository) {
		return new PosisiTahapPemberkasanService(posisiTahapPemberkasanRepository);
	}
	
	@Produces
	public ILocalStorageService getLocalStorageService(@Infrastructure ILocalStorageRepository localStorageRepository) {
		return new LocalStorageService(localStorageRepository);
	}
	
	@Produces
	public IOnlyofficeService getOnlyofficeService(@Infrastructure IOnlyOfficeRepository onlyOfficeRepository) {
		return new OnlyofficeService(onlyOfficeRepository);
	}
	
	@Produces
	public IOnlyofficeTokenManagerService getOnlyofficeTokenManagerService(@Infrastructure IOnlyofficeTokenManager onlyofficeTokenManager) {
		return new OnlyofficeTokenManajerService(onlyofficeTokenManager);
	}
}