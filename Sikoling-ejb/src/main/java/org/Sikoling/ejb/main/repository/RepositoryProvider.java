package org.Sikoling.ejb.main.repository;

import org.Sikoling.ejb.main.repository.permohonan.PermohonanRepositoryJPA;
import org.Sikoling.ejb.main.repository.propinsi.PropinsiRepositoryJPA;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
@LocalBean
public class RepositoryProvider {
	
	@PersistenceContext
	private EntityManager em;

	@Produces
	public EntityManager getEm() {
		return em;
	}
	
	@Produces
	public PropinsiRepositoryJPA getPropinsiRepositoryJPA(EntityManager entityManager) {
		return new PropinsiRepositoryJPA(entityManager);
	}
	
	@Produces
	public PermohonanRepositoryJPA getPermohonanRepositoryJPA(EntityManager entityManager) {
		return new PermohonanRepositoryJPA(entityManager);
	}

}
