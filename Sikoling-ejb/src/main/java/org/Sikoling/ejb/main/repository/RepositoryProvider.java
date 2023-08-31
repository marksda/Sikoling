package org.Sikoling.ejb.main.repository;

import java.util.Properties;

import org.Sikoling.ejb.main.integrator.onlyoffice.OnlyOfficeImpl;
import org.Sikoling.ejb.main.integrator.onlyoffice.OnlyOfficeTokenManager;
import org.Sikoling.ejb.main.integrator.onlyoffice.helpers.DocumentManager;
import org.Sikoling.ejb.main.integrator.onlyoffice.helpers.ServiceConverter;
import org.Sikoling.ejb.main.integrator.onlyoffice.helpers.TrackManager;
import org.Sikoling.ejb.main.repository.bidangusaha.BidangUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.desa.DesaRepositoryJPA;
import org.Sikoling.ejb.main.repository.dokumen.DokumenRepositoryJPA;
import org.Sikoling.ejb.main.repository.dokumen.KategoriDokumenRepositoryJPA;
import org.Sikoling.ejb.main.repository.dokumen.KbliRepositoryJPA;
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
import org.Sikoling.ejb.main.repository.otoritas.OtoritasRepositoryJPA;
import org.Sikoling.ejb.main.repository.otoritas.OtoritasPerusahaanRepositoryJPA;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.penanggungjawab.PenanggungJawabRepositoryJPA;
import org.Sikoling.ejb.main.repository.permohonan.KategoriPermohonanRepositoryJPA;
import org.Sikoling.ejb.main.repository.permohonan.KategoriPermohonanSuratArahanRepositoryJPA;
import org.Sikoling.ejb.main.repository.permohonan.PosisiTahapPemberkasanRepositoryJPA;
import org.Sikoling.ejb.main.repository.permohonan.RegisterPermohonanRepositoryJPA;
import org.Sikoling.ejb.main.repository.permohonan.StatusPengurusPermohonanRepositoryJPA;
import org.Sikoling.ejb.main.repository.person.PersonRepositoryJPA;
import org.Sikoling.ejb.main.repository.perusahaan.PegawaiPerusahaanRepositoryJPA;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanRepositoryJPA;
import org.Sikoling.ejb.main.repository.produk.ProdukRepositoryJPA;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiRepositoryJPA;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminRepositoryJPA;
import org.Sikoling.ejb.main.repository.skalausaha.SkalaUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.user.KeyCloakUserJPA;
import org.Sikoling.ejb.main.security.keycloack.KeycloakClient;
import org.Sikoling.ejb.main.security.tokenvalidation.TokenValidation;
import org.Sikoling.ejb.main.storage.LocalStorageImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.JWTProcessor;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
	
	@Produces
	public TokenValidation getTokenValidation(JWTProcessor<SecurityContext> jwtProcessor) {
		return new TokenValidation(jwtProcessor);
	}
	
	@Produces
	public KeycloakClient getKeycloakClient(Properties properties) {
		return new KeycloakClient(
				properties.getProperty("SSO_AUTH_URL"), properties.getProperty("SSO_REALM"), 
				properties.getProperty("SSO_CLIENT_ID"), properties.getProperty("SSO_CLIENT_SECRET")
				);
	}
	
	@Produces
	public KeyCloakUserJPA getKeyCloakUserRepository(EntityManager entityManager, Properties properties,  DataConverter dataConverter, KeycloakClient keycloakClient) {
		Keycloak keycloak = KeycloakBuilder.builder()
			     .serverUrl(properties.getProperty("SSO_AUTH_URL"))
			     .realm(properties.getProperty("SSO_REALM"))
			     .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
			     .clientId(properties.getProperty("SSO_CLIENT_ID"))
			     .clientSecret(properties.getProperty("SSO_CLIENT_SECRET"))
			     .build();

		return new KeyCloakUserJPA(keycloakClient, keycloak, entityManager, dataConverter);
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
	public DokumenRepositoryJPA getDokumenRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new DokumenRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public KbliRepositoryJPA getKbli2020RepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new KbliRepositoryJPA(entityManager, dataConverter);
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
	public OtoritasRepositoryJPA getAutorisasiRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new OtoritasRepositoryJPA(entityManager, dataConverter);
	}
	
	@Produces
	public OtoritasPerusahaanRepositoryJPA getAutorityPerusahaanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		return new OtoritasPerusahaanRepositoryJPA(entityManager, dataConverter);
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
	
	@Produces
	public LocalStorageImpl getLocalStorageImpl(Properties properties) {
		return new LocalStorageImpl(properties.getProperty("STORAGE_PATH"));
	}
	
	@Produces
	public ServiceConverter getServiceConverter(Properties properties, OnlyOfficeTokenManager tokenManager) {
		return new ServiceConverter(properties, tokenManager);
	}
	
	@Produces
	public DocumentManager getDokuDocumentManager(Properties properties, ServiceConverter serviceConverter) {
		return new DocumentManager(properties, serviceConverter);
	}
	
	@Produces
	public TrackManager getTrackManager(Properties properties, DocumentManager documentManager,
			ServiceConverter serviceConverter, OnlyOfficeTokenManager tokenManager) {
		return new TrackManager(properties, documentManager, serviceConverter, tokenManager);
	}
	
	@Produces
	public OnlyOfficeImpl getOnlyOfficeImpl(TrackManager trackManager, DocumentManager documentManager, OnlyOfficeTokenManager tokenManager) {
		return new OnlyOfficeImpl(trackManager, documentManager, tokenManager);
	}
	
}