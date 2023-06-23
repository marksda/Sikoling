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
import org.Sikoling.ejb.main.repository.log.FlowLogRepositoryJPA;
import org.Sikoling.ejb.main.repository.log.KategoriLogRepositoryJPA;
import org.Sikoling.ejb.main.repository.log.StatusFlowLogRepositoryJPA;
import org.Sikoling.ejb.main.repository.modelperizinan.ModelPerizinanRepositoryJPA;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.penanggungjawab.PenanggungJawabRepositoryJPA;
import org.Sikoling.ejb.main.repository.permohonan.KategoriPermohonanRepositoryJPA;
import org.Sikoling.ejb.main.repository.permohonan.KategoriPermohonanSuratArahanRepositoryJPA;
import org.Sikoling.ejb.main.repository.permohonan.PosisiTahapPemberkasanRepositoryJPA;
import org.Sikoling.ejb.main.repository.permohonan.RegisterPermohonanRepositoryJPA;
import org.Sikoling.ejb.main.repository.permohonan.StatusPengurusPermohonanRepositoryJPA;
import org.Sikoling.ejb.main.repository.person.PersonRepositoryJPA;
import org.Sikoling.ejb.main.repository.perusahaan.AutorityPerusahaanRepositoryJPA;
import org.Sikoling.ejb.main.repository.perusahaan.PegawaiPerusahaanRepositoryJPA;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanRepositoryJPA;
import org.Sikoling.ejb.main.repository.produk.ProdukRepositoryJPA;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiRepositoryJPA;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminRepositoryJPA;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaRepositoryJPA;
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
	public DataConverter getDataConverter(EntityManager entityManager) {
		return new DataConverter(entityManager);
	}
	
	@Produces
	public PropinsiRepositoryJPA getPropinsiRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new PropinsiRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public KabupatenRepositoryJPA getKabupatenRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new KabupatenRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public KecamatanRepositoryJPA getKecamatanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new KecamatanRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public DesaRepositoryJPA getDesaRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new DesaRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public ModelPerizinanRepositoryJPA getModelPerizinanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new ModelPerizinanRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public BidangUsahaRepositoryJPA getBidangUsahaRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new BidangUsahaRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public JabatanRepositoryJPA getJabatanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new JabatanRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public JenisKelaminRepositoryJPA getJenisKelaminRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new JenisKelaminRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public PenanggungJawabRepositoryJPA getPenanggungJawabRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new PenanggungJawabRepositoryJPA(entityManager, dataConverter);
	}
	
//	@Produces
//	public UserRepositoryJPA getUserRepositoryJPA(EntityManager entityManager) {
//		return new UserRepositoryJPA(entityManager);
//	}
	
	@Produces
	public KeyCloakUserJPA getKeyCloakUserRepository(EntityManager entityManager, Properties properties, 
			Client client, ITokenValidationService tokenValidationService,  DataConverter dataConverter) {
		Keycloak keycloak = Keycloak.getInstance(
			properties.getProperty("SSO_AUTH_URL"), "master", properties.getProperty("SSO_AUTH_USER"), 
			properties.getProperty("SSO_AUTH_PASSWORD"), "admin-cli"
        );
		
		return new KeyCloakUserJPA(keycloak, properties.getProperty("SSO_REALM"), entityManager, 
				properties.getProperty("SSO_TOKEN_URL"), client, tokenValidationService, dataConverter);
	}
	
	@Produces
	public RegisterPerusahaanRepositoryJPA getRegisterPerusahaanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new RegisterPerusahaanRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public PegawaiPerusahaanRepositoryJPA getPegawaiPerusahaanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new PegawaiPerusahaanRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public KategoriProdukRepositoryJPA getKategoriProdukRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new KategoriProdukRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public ProdukRepositoryJPA getProdukRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new ProdukRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public PersonRepositoryJPA getPersonRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new PersonRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public KategoriPelakuUsahaRepositoryJPA getKategoriPelakuUsahaRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new KategoriPelakuUsahaRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public PelakuUsahaRepositoryJPA getPelakuUsahaJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new PelakuUsahaRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public SkalaUsahaRepositoryJPA getSkalaUsahaRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new SkalaUsahaRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public KategoriDokumenRepositoryJPA getKategoriDokumenRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new KategoriDokumenRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public MasterDokumenRepositoryJPA getDokumenRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new MasterDokumenRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public Kbli2020RepositoryJPA getKbli2020RepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new Kbli2020RepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public RegisterKbliRepositoryJPA getRegisterKbliRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new RegisterKbliRepositoryJPA(entityManager, dataConverter);
	}
		
	@Produces
	public RegisterDokumenRepositoryJPA getRegisterDokumenRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new RegisterDokumenRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public HakAksesRepositoryJPA getHakAksesRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new HakAksesRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public AutorisasiRepositoryJPA getAutorisasiRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new AutorisasiRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public AutorityPerusahaanRepositoryJPA getAutorityPerusahaanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new AutorityPerusahaanRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public KategoriPermohonanRepositoryJPA getKategoriPermohonanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new KategoriPermohonanRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public KategoriPermohonanSuratArahanRepositoryJPA getKategoriPermohonanSuratArahanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new KategoriPermohonanSuratArahanRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public StatusPengurusPermohonanRepositoryJPA getStatusPengurusPermohonanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new StatusPengurusPermohonanRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public RegisterPermohonanRepositoryJPA getRegisterPermohonanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new RegisterPermohonanRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public StatusFlowLogRepositoryJPA getStatusFlowLogRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new StatusFlowLogRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public FlowLogRepositoryJPA getFlowLogRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new FlowLogRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public KategoriLogRepositoryJPA getKategoriLogRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new KategoriLogRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public PosisiTahapPemberkasanRepositoryJPA getPosisiTahapPemberkasanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new PosisiTahapPemberkasanRepositoryJPA(entityManager, dataConverter);
	}
}