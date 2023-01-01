package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.dokumen.RegisterKbli;
import org.Sikoling.ejb.abstraction.repository.IRegisterKbliRepository;

import jakarta.persistence.EntityManager;

public class RegisterKbliRepositoryJPA implements IRegisterKbliRepository {
	
	private final EntityManager entityManager;	

	public RegisterKbliRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<RegisterKbli> getAll() {
		return entityManager.createNamedQuery("RegisterKbliData.findAll", RegisterKbliData.class)
				.getResultList()
				.stream()
				.map(t -> convertRegisterKbliDataToRegisterKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public RegisterKbli save(RegisterKbli t) {
		RegisterKbliData registerKbliData = convertRegisterKbliToRegisterKbliData(t);
		entityManager.persist(registerKbliData);
		entityManager.flush();
		
		return convertRegisterKbliDataToRegisterKbli(registerKbliData);
	}

	@Override
	public RegisterKbli update(RegisterKbli t) {
		RegisterKbliData registerKbliData = convertRegisterKbliToRegisterKbliData(t);
		registerKbliData = entityManager.merge(registerKbliData);
		
		return convertRegisterKbliDataToRegisterKbli(registerKbliData);
	}

	@Override
	public DeleteResponse delete(String nib, String kode) {
		RegisterKbliDataId id = new RegisterKbliDataId();
		id.setNib(nib);
		id.setKbli(kode);
		
		RegisterKbliData registerKbliData = entityManager.find(RegisterKbliData.class, id);
		entityManager.remove(registerKbliData);
		
		return new DeleteResponse(true, nib.concat("-").concat(kode));
	}

	@Override
	public List<RegisterKbli> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterKbliData.findAll", RegisterKbliData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertRegisterKbliDataToRegisterKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterKbli> getByNama(String nama) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("RegisterKbliData.findByNama", RegisterKbliData.class)
				.setParameter("nama", nama)
				.getResultList()
				.stream()
				.map(t -> convertRegisterKbliDataToRegisterKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterKbli> getByNamaAndPage(String nama, Integer page, Integer pageSize) {
		nama = "%" + nama + "%";
		return entityManager.createNamedQuery("RegisterKbliData.findByNama", RegisterKbliData.class)
				.setParameter("nama", nama)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertRegisterKbliDataToRegisterKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterKbli> getByKode(String kode) {
		kode = "%" + kode + "%";
		return entityManager.createNamedQuery("RegisterKbliData.findByKode", RegisterKbliData.class)
				.setParameter("kode", kode)
				.getResultList()
				.stream()
				.map(t -> convertRegisterKbliDataToRegisterKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterKbli> getByKodeAndPage(String kode, Integer page, Integer pageSize) {
		kode = "%" + kode + "%";
		return entityManager.createNamedQuery("RegisterKbliData.findByKode", RegisterKbliData.class)
				.setParameter("kode", kode)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertRegisterKbliDataToRegisterKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterKbli> getByNib(String nib) {
		return entityManager.createNamedQuery("RegisterKbliData.findByNib", RegisterKbliData.class)
				.setParameter("nib", nib)
				.getResultList()
				.stream()
				.map(t -> convertRegisterKbliDataToRegisterKbli(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<RegisterKbli> getByNibAndPage(String nib, Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("RegisterKbliData.findByNib", RegisterKbliData.class)
				.setParameter("nib", nib)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertRegisterKbliDataToRegisterKbli(t))
				.collect(Collectors.toList());
	}

	private RegisterKbli convertRegisterKbliDataToRegisterKbli(RegisterKbliData d) {
		return new RegisterKbli(
				d.getNib(), 
				d.getKbli(),
				d.getNama()
				);
	}
	
	private RegisterKbliData convertRegisterKbliToRegisterKbliData(RegisterKbli t) {
		RegisterKbliData registerKbliData = new RegisterKbliData();
		registerKbliData.setNib(t.getIdNib());
		registerKbliData.setKbli(t.getIdKbli());
		registerKbliData.setNama(t.getNama());
		
		return registerKbliData;
	}
	
}
