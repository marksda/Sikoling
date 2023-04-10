package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.List;

public class QueryParamFilters implements Serializable {

	private static final long serialVersionUID = 5030075038226260480L;
	private final Integer pageNumber;
	private final Integer pageSize;
	private final List<Filter> filters;
	private final List<SortOrder> sortOrders;
	
	public QueryParamFilters(Integer pageNumber, Integer pageSize, List<Filter> filters, List<SortOrder> sortOrders) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.filters = filters;
		this.sortOrders = sortOrders;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}
	
	public List<Filter> getFilters() {
		return filters;
	}
	
	public List<SortOrder> getSortOrders() {
		return sortOrders;
	}	
}


