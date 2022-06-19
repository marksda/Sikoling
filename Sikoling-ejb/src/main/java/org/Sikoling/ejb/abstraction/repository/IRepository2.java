package org.Sikoling.ejb.abstraction.repository;

import java.util.List;

public interface IRepository2<T, S> {
	List<T> getAll();
	T save(T t, S s);
	T update(T t, S s);
}
