package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

public interface IRepository<T> {
	List<T> getAll();
	T save(T t);
	T update(T t);
}
