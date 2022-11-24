package org.Sikoling.ejb.main.repository.dokumen;

import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.RegisterKbli;
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
		id.setKbliData(kode);
		id.setRegisterDokumenOssData(nib);
		
		RegisterKbliData registerKbliData = entityManager.find(RegisterKbliData.class, id);
		entityManager.remove(registerKbliData);
		
		return new DeleteResponse(true, nib.concat("-").concat(kode));
	}

	@Override
	public RegisterKbli updateById(String nib, String kode, RegisterKbli registerKbli) {
		RegisterKbliData updateData = convertRegisterKbliToRegisterKbliData(registerKbli);
		
		RegisterKbliDataId id = new RegisterKbliDataId();
		id.setKbliData(kode);
		id.setRegisterDokumenOssData(nib);
		
		RegisterKbliData registerKbliData = entityManager.find(RegisterKbliData.class, id);
		registerKbliData.setKbliData(updateData.getKbliData());
		registerKbliData.setRegisterDokumenOssData(updateData.getRegisterDokumenOssData());
		
		return null;
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
				d.getRegisterDokumenOssData().getNib(), 
				d.getKbliData().getId()
				);
	}
	
	private RegisterKbliData convertRegisterKbliToRegisterKbliData(RegisterKbli t) {
		RegisterKbliData registerKbliData = new RegisterKbliData();
		DokumenOssData registerDokumenOssData = new DokumenOssData();
		registerDokumenOssData.setNib(t.getNib());		
		registerKbliData.setRegisterDokumenOssData(registerDokumenOssData);
		KbliData kbliData = new KbliData();
		kbliData.setId(t.getKode());	
		registerKbliData.setKbliData(kbliData);
		
		return registerKbliData;
	}
	
}
