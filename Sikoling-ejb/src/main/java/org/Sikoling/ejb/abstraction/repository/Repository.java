package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

public interface Repository<T> {
	List<T> getAll();
	T save(T t);
	T update(T t);
}
