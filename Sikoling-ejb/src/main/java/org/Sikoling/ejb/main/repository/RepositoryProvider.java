package org.Sikoling.ejb.main.repository;

import java.util.Properties;

import org.Sikoling.ejb.main.repository.bentukusaha.BentukUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.bidangusaha.BidangUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.desa.DesaRepositoryJPA;
import org.Sikoling.ejb.main.repository.jabatan.JabatanRepositoryJPA;
import org.Sikoling.ejb.main.repository.jenispelakuusaha.JenisPelakuUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenRepositoryJPA;
import org.Sikoling.ejb.main.repository.kategoriproduk.KategoriProdukRepositoryJPA;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanRepositoryJPA;
import org.Sikoling.ejb.main.repository.pemrakarsa.PemrakarsaRepositoryJPA;
import org.Sikoling.ejb.main.repository.penanggungjawab.PenanggungJawabRepositoryJPA;
import org.Sikoling.ejb.main.repository.person.PersonRepositoryJPA;
import org.Sikoling.ejb.main.repository.produk.ProdukRepositoryJPA;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiRepositoryJPA;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminRepositoryJPA;
import org.Sikoling.ejb.main.repository.user.UserRepositoryJPA;
import org.Sikoling.ejb.main.security.user.keycloack.KeyCloakUserRepository;
import org.keycloak.admin.client.Keycloak;

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
	public BentukUsahaRepositoryJPA getBentukUsahaRepositoryJPA(EntityManager entityManager) {
		return new BentukUsahaRepositoryJPA(entityManager);
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
	public KeyCloakUserRepository getKeyCloakUserRepository(EntityManager entityManager, Properties properties) {
		Keycloak keycloak = Keycloak.getInstance(
				properties.getProperty("SSO_AUTH_URL"),
                "master",
                properties.getProperty("SSO_AUTH_USER"),
                properties.getProperty("SSO_AUTH_PASSWORD"),
                "admin-cli");
		
		return new KeyCloakUserRepository(keycloak, properties.getProperty("SSO_REALM"), entityManager);
	}
	
	@Produces
	public PemrakarsaRepositoryJPA getPemrakarsaRepositoryJPA(EntityManager entityManager) {
		return new PemrakarsaRepositoryJPA(entityManager);
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
	public JenisPelakuUsahaRepositoryJPA getJenisPelakuUsahaRepositoryJPA(EntityManager entityManager) {
		return new JenisPelakuUsahaRepositoryJPA(entityManager);
	}

}