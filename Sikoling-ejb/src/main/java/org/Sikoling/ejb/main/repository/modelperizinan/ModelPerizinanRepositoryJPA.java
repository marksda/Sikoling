package org.Sikoling.ejb.main.repository.modelperizinan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.Filter;
import org.Sikoling.ejb.abstraction.entity.ModelPerizinan;
import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;
import org.Sikoling.ejb.abstraction.entity.SortOrder;
import org.Sikoling.ejb.abstraction.repository.IModelPerizinanRepository;
import org.Sikoling.ejb.main.repository.DataConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ModelPerizinanRepositoryJPA implements IModelPerizinanRepository {
	
	private final EntityManager entityManager;		
	private final DataConverter dataConverter;

	public ModelPerizinanRepositoryJPA(EntityManager entityManager, DataConverter dataConverter) {
		this.entityManager = entityManager;
		this.dataConverter = dataConverter;
	}
	
	@Override
	public List<ModelPerizinan> getAll() {
		return entityManager.createNamedQuery("ModelPerizinanData.findAll", ModelPerizinanData.class)
				.getResultList()
				.stream()
				.map(d -> dataConverter.convertModelPerizinanDataToModelPerizinan(d))
				.collect(Collectors.toList());
	}

	@Override
	public ModelPerizinan save(ModelPerizinan t) {
		ModelPerizinanData modelPerizinanData = dataConverter.convertModelPerizinanToModelPerizinanData(t);
		entityManager.persist(modelPerizinanData);
		entityManager.flush();
		return dataConverter.convertModelPerizinanDataToModelPerizinan(modelPerizinanData);
	}

	@Override
	public ModelPerizinan update(ModelPerizinan t) {
		ModelPerizinanData modelPerizinanData = dataConverter.convertModelPerizinanToModelPerizinanData(t);
		modelPerizinanData = entityManager.merge(modelPerizinanData);
		return dataConverter.convertModelPerizinanDataToModelPerizinan(modelPerizinanData);
	}

	@Override
	public DeleteResponse delete(String id) {
		ModelPerizinanData modelPerizinanData = entityManager.find(ModelPerizinanData.class, id);
		entityManager.remove(modelPerizinanData);
		return new DeleteResponse(true, id);
	}

	
	@Override
	public List<ModelPerizinan> getDaftarModelPerizinan(QueryParamFilters queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ModelPerizinanData> cq = cb.createQuery(ModelPerizinanData.class);
		Root<ModelPerizinanData> root = cq.from(ModelPerizinanData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.getFilters().iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "singkatan":
				daftarPredicate.add(cb.like(cb.lower(root.get("singkatan")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			default:
				break;
			}			
		}
		
		if(daftarPredicate.isEmpty()) {
			cq.select(root);
		}
		else {
			cq.select(root).where(cb.and(daftarPredicate.toArray(new Predicate[0])));
		}
		
		// sort clause
		Iterator<SortOrder> iterSort = queryParamFilters.getSortOrders().iterator();
				
		while (iterSort.hasNext()) {
			SortOrder sort = (SortOrder) iterSort.next();
			switch (sort.getFieldName()) {
			case "id":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("id")));
				}
				else {
					cq.orderBy(cb.desc(root.get("id")));
				}
				break;
			case "nama":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("nama")));
				}
				else {
					cq.orderBy(cb.desc(root.get("nama")));
				}
				break;
			case "singkatan":
				if(sort.getValue().equals("ASC")) {
					cq.orderBy(cb.asc(root.get("singkatan")));
				}
				else {
					cq.orderBy(cb.desc(root.get("singkatan")));
				}
				break;
			default:
				break;
			}			
		}
		
		TypedQuery<ModelPerizinanData> q = null;		
		if( queryParamFilters.getPageSize() != null && queryParamFilters.getPageSize() > 0) { //limit query result
			q = entityManager.createQuery(cq)
					.setMaxResults(queryParamFilters.getPageSize())
					.setFirstResult((queryParamFilters.getPageNumber()-1)*queryParamFilters.getPageSize());
		}
		else {
			q = entityManager.createQuery(cq);
		}
		
		return q.getResultList()
				.stream()
				.map(d -> dataConverter.convertModelPerizinanDataToModelPerizinan(d))
				.collect(Collectors.toList());
	}

	
	@Override
	public Long getCount(List<Filter> queryParamFilters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<ModelPerizinanData> root = cq.from(ModelPerizinanData.class);		
		
		// where clause
		Iterator<Filter> iterFilter = queryParamFilters.iterator();
		ArrayList<Predicate> daftarPredicate = new ArrayList<Predicate>();
		
		while (iterFilter.hasNext()) {
			Filter filter = (Filter) iterFilter.next();
			
			switch (filter.getFieldName()) {
			case "id":
				daftarPredicate.add(cb.equal(root.get("id"), filter.getValue()));
				break;
			case "nama":
				daftarPredicate.add(cb.like(cb.lower(root.get("nama")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			case "singkatan":
				daftarPredicate.add(cb.like(cb.lower(root.get("singkatan")), "%"+filter.getValue().toLowerCase()+"%"));
				break;
			default:
				break;
			}			
		}
		
		if(daftarPredicate.isEmpty()) {
			cq.select(cb.count(root));
		}
		else {
			cq.select(cb.count(root)).where(cb.and(daftarPredicate.toArray(new Predicate[0])));
		}
		
		return entityManager.createQuery(cq).getSingleResult();
	}
	
}
