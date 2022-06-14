package org.Sikoling.ejb.main.repository.sex;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@RunWith(MockitoJUnitRunner.class)
public class JenisKelaminRepositoryJPATest {
	
	@Mock
	private EntityManager entityManager;
	
	@Mock
	private TypedQuery<JenisKelaminData> typedQuery;
	
	@InjectMocks
	private JenisKelaminRepositoryJPA jenisKelaminRepositoryJPA;
	
	@Test
	public void save() {
		JenisKelamin newJenisKelamin = new JenisKelamin("01", "Laki-laki");
		
		JenisKelaminData jenisKelaminData = new JenisKelaminData();
		jenisKelaminData.setId(newJenisKelamin.getId());
		jenisKelaminData.setNama(newJenisKelamin.getNama());
		
		jenisKelaminRepositoryJPA.save(newJenisKelamin);
		
		verify(entityManager).persist(jenisKelaminData);
		verify(entityManager).flush();
	}
	
	@Test
	public void update() {
		JenisKelamin newJenisKelamin = new JenisKelamin("01", "Laki-laki");
		
		JenisKelaminData jenisKelaminData = new JenisKelaminData();
		jenisKelaminData.setId(newJenisKelamin.getId());
		jenisKelaminData.setNama(newJenisKelamin.getNama());
		
		when(entityManager.merge(jenisKelaminData)).thenReturn(jenisKelaminData);
		
		jenisKelaminRepositoryJPA.update(newJenisKelamin);
		
		verify(entityManager).merge(jenisKelaminData);		
		
	}
	
    @Test
	public void getAll() {
    	JenisKelamin newJenisKelamin = new JenisKelamin("01", "Laki-laki");
		
		JenisKelaminData jenisKelaminData = new JenisKelaminData();
		jenisKelaminData.setId(newJenisKelamin.getId());
		jenisKelaminData.setNama(newJenisKelamin.getNama());
		
		when(typedQuery.getResultList()).thenReturn(Arrays.asList(jenisKelaminData));
		when(entityManager.createNamedQuery("JenisKelaminData.findAll", JenisKelaminData.class)).thenReturn(typedQuery);
		
		List<JenisKelamin> jenisKelamins = jenisKelaminRepositoryJPA.getAll();
		
		assertThat(jenisKelamins).isEqualTo(Arrays.asList(newJenisKelamin));
		
	}
    
    
	
}
