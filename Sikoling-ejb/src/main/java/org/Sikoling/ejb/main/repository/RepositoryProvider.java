package org.Sikoling.ejb.main.repository;

import java.util.Properties;

import org.Sikoling.ejb.abstraction.service.security.ITokenValidationService;
import org.Sikoling.ejb.main.repository.authority.AutorisasiRepositoryJPA;
import org.Sikoling.ejb.main.repository.bidangusaha.BidangUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.desa.DesaRepositoryJPA;
import org.Sikoling.ejb.main.repository.dokumen.MasterDokumenRepositoryJPA;
import org.Sikoling.ejb.main.repository.dokumen.KategoriDokumenRepositoryJPA;
import org.Sikoling.ejb.main.repository.dokumen.Kbli2020RepositoryJPA;
import org.Sikoling.ejb.main.repository.dokumen.RegisterDokumenRepositoryJPA;
import org.Sikoling.ejb.main.repository.dokumen.RegisterKbliRepositoryJPA;
import org.Sikoling.ejb.main.repository.hakakses.HakAksesRepositoryJPA;
import org.Sikoling.ejb.main.repository.jabatan.JabatanRepositoryJPA;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenRepositoryJPA;
import org.Sikoling.ejb.main.repository.kategoripelakuusaha.KategoriPelakuUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.kategoriproduk.KategoriProdukRepositoryJPA;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanRepositoryJPA;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanRepositoryJPA;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.penanggungjawab.PenanggungJawabRepositoryJPA;
import org.Sikoling.ejb.main.repository.permohonan.KategoriPermohonanRepositoryJPA;
import org.Sikoling.ejb.main.repository.permohonan.RegisterPermohonanRepositoryJPA;
import org.Sikoling.ejb.main.repository.person.PersonRepositoryJPA;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanRepositoryJPA;
import org.Sikoling.ejb.main.repository.produk.ProdukRepositoryJPA;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiRepositoryJPA;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminRepositoryJPA;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.user.UserRepositoryJPA;
import org.Sikoling.ejb.main.security.user.keycloack.KeyCloakUserJPA;
import org.keycloak.admin.client.Keycloak;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.client.Client;

@Stateless
@LocalBean
public class RepositoryProvider {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Produces
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Produces
	public PropinsiRepositoryJPA getPropinsiRepositoryJPA(EntityManager entityManager) {
		return new PropinsiRepositoryJPA(entityManager);
	}
	
	@Produces
	public KabupatenRepositoryJPA getKabupatenRepositoryJPA(EntityManager entityManager) {
		return new KabupatenRepositoryJPA(entityManager);
	}
	
	@Produces
	public KecamatanRepositoryJPA getKecamatanRepositoryJPA(EntityManager entityManager) {
		return new KecamatanRepositoryJPA(entityManager);
	}
	
	@Produces
	public DesaRepositoryJPA getDesaRepositoryJPA(EntityManager entityManager) {
		return new DesaRepositoryJPA(entityManager);
	}
	
	@Produces
	public ModelPerizinanRepositoryJPA getModelPerizinanRepositoryJPA(EntityManager entityManager) {
		return new ModelPerizinanRepositoryJPA(entityManager);
	}
	
	@Produces
	public BidangUsahaRepositoryJPA getBidangUsahaRepositoryJPA(EntityManager entityManager) {
		return new BidangUsahaRepositoryJPA(entityManager);
	}
	
	@Produces
	public JabatanRepositoryJPA getJabatanRepositoryJPA(EntityManager entityManager) {
		return new JabatanRepositoryJPA(entityManager);
	}
	
	@Produces
	public JenisKelaminRepositoryJPA getJenisKelaminRepositoryJPA(EntityManager entityManager) {
		return new JenisKelaminRepositoryJPA(entityManager);
	}
	
	@Produces
	public PenanggungJawabRepositoryJPA getPenanggungJawabRepositoryJPA(EntityManager entityManager) {
		return new PenanggungJawabRepositoryJPA(entityManager);
	}
	
	@Produces
	public UserRepositoryJPA getUserRepositoryJPA(EntityManager entityManager) {
		return new UserRepositoryJPA(entityManager);
	}
	
	@Produces
	public KeyCloakUserJPA getKeyCloakUserRepository(EntityManager entityManager, Properties properties, 
			Client client, ITokenValidationService tokenValidationService) {
		Keycloak keycloak = Keycloak.getInstance(
				properties.getProperty("SSO_AUTH_URL"),
                "master",
                properties.getProperty("SSO_AUTH_USER"),
                properties.getProperty("SSO_AUTH_PASSWORD"),
                "admin-cli");
		
		return new KeyCloakUserJPA(keycloak, properties.getProperty("SSO_REALM"), entityManager, 
				properties.getProperty("SSO_TOKEN_URL"), client, tokenValidationService);
	}
	
	@Produces
	public RegisterPerusahaanRepositoryJPA getRegisterPerusahaanRepositoryJPA(EntityManager entityManager) {
		return new RegisterPerusahaanRepositoryJPA(entityManager);
	}
	
	@Produces
	public KategoriProdukRepositoryJPA getKategoriProdukRepositoryJPA(EntityManager entityManager) {
		return new KategoriProdukRepositoryJPA(entityManager);
	}
	
	@Produces
	public ProdukRepositoryJPA getProdukRepositoryJPA(EntityManager entityManager) {
		return new ProdukRepositoryJPA(entityManager);
	}
	
	@Produces
	public PersonRepositoryJPA getPersonRepositoryJPA(EntityManager entityManager) {
		return new PersonRepositoryJPA(entityManager);
	}
	
	@Produces
	public KategoriPelakuUsahaRepositoryJPA getKategoriPelakuUsahaRepositoryJPA(EntityManager entityManager) {
		return new KategoriPelakuUsahaRepositoryJPA(entityManager);
	}
	
	@Produces
	public PelakuUsahaRepositoryJPA getPelakuUsahaJPA(EntityManager entityManager) {
		return new PelakuUsahaRepositoryJPA(entityManager);
	}
	
	@Produces
	public SkalaUsahaRepositoryJPA getSkalaUsahaRepositoryJPA(EntityManager entityManager) {
		return new SkalaUsahaRepositoryJPA(entityManager);
	}
	
	@Produces
	public KategoriDokumenRepositoryJPA getKategoriDokumenRepositoryJPA(EntityManager entityManager) {
		return new KategoriDokumenRepositoryJPA(entityManager);
	}
	
	@Produces
	public MasterDokumenRepositoryJPA getDokumenRepositoryJPA(EntityManager entityManager) {
		return new MasterDokumenRepositoryJPA(entityManager);
	}
	
	@Produces
	public Kbli2020RepositoryJPA getKbli2020RepositoryJPA(EntityManager entityManager) {
		return new Kbli2020RepositoryJPA(entityManager);
	}
	
	@Produces
	public RegisterKbliRepositoryJPA getRegisterKbliRepositoryJPA(EntityManager entityManager) {
		return new RegisterKbliRepositoryJPA(entityManager);
	}
		
	@Produces
	public RegisterDokumenRepositoryJPA getRegisterDokumenRepositoryJPA(EntityManager entityManager) {
		return new RegisterDokumenRepositoryJPA(entityManager);
	}
	
	@Produces
	public HakAksesRepositoryJPA getHakAksesRepositoryJPA(EntityManager entityManager) {
		return new HakAksesRepositoryJPA(entityManager);
	}
	
	@Produces
	public AutorisasiRepositoryJPA getAutorisasiRepositoryJPA(EntityManager entityManager) {
		return new AutorisasiRepositoryJPA(entityManager);
	}
	
	@Produces
	public KategoriPermohonanRepositoryJPA getKategoriPermohonanRepositoryJPA(EntityManager entityManager) {
		return new KategoriPermohonanRepositoryJPA(entityManager);
	}
	
	@Produces
	public RegisterPermohonanRepositoryJPA getRegisterPermohonanRepositoryJPA(EntityManager entityManager) {
		return new RegisterPermohonanRepositoryJPA(entityManager);
	}
}