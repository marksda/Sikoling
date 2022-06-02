package org.Sikoling.ejb.main.repository;

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
	
	

}
