package org.Sikoling.ejb.main.repository;

import org.Sikoling.ejb.main.repository.bentukusaha.BentukUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.bidangusaha.BidangUsahaRepositoryJPA;
import org.Sikoling.ejb.main.repository.desa.DesaRepositoryJPA;
import org.Sikoling.ejb.main.repository.jabatan.JabatanRepositoryJPA;
import org.Sikoling.ejb.main.repository.kabupaten.KabupatenRepositoryJPA;
import org.Sikoling.ejb.main.repository.kategoriproduk.KategoriProdukRepositoryJPA;
import org.Sikoling.ejb.main.repository.kecamatan.KecamatanRepositoryJPA;
import org.Sikoling.ejb.main.repository.pemrakarsa.PemrakarsaRepositoryJPA;
import org.Sikoling.ejb.main.repository.penanggungjawab.PenanggungJawabRepositoryJPA;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiRepositoryJPA;
import org.Sikoling.ejb.main.repository.sex.JenisKelaminRepositoryJPA;
import org.Sikoling.ejb.main.repository.user.UserRepositoryJPA;

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
	public PemrakarsaRepositoryJPA getPemrakarsaRepositoryJPA(EntityManager entityManager) {
		return new PemrakarsaRepositoryJPA(entityManager);
	}
	
	@Produces
	public KategoriProdukRepositoryJPA getKategoriProdukRepositoryJPA(EntityManager entityManager) {
		return new KategoriProdukRepositoryJPA(entityManager);
	}

}