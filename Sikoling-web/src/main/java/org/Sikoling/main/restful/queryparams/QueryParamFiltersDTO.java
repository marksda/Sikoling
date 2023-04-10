package org.Sikoling.main.restful.queryparams;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.QueryParamFilters;

public class QueryParamFiltersDTO implements Serializable {

	private static final long serialVersionUID = -3863126437095056054L;
	private Integer pageNumber;
	private Integer pageSize;
	private List<FilterDTO> filters;
	private List<SortOrderDTO> sortOrders;
	
	public QueryParamFiltersDTO() {
	}
	
	public QueryParamFiltersDTO(QueryParamFilters t) {
		if(t != null) {
			this.pageNumber = t.getPageNumber();
			this.pageSize = t.getPageSize();
			this.filters = t.getFilters() != null ?
					t.getFilters()
					.stream()
					.map(i -> new FilterDTO(i))
					.collect(Collectors.toList()) : null;
			this.sortOrders = t.getSortOrders()!= null ?
					t.getSortOrders()
					.stream()
					.map(i -> new SortOrderDTO(i))
					.collect(Collectors.toList()) : null;
		}
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<FilterDTO> getFilters() {
		return filters;
	}

	public void setFilters(List<FilterDTO> filters) {
		this.filters = filters;
	}

	public List<SortOrderDTO> getSortOrders() {
		return sortOrders;
	}

	public void setSortOrders(List<SortOrderDTO> sortOrders) {
		this.sortOrders = sortOrders;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		int hash = 737;
		hash = 171 * hash + Objects.hashCode(pageNumber.toString());
		hash = 171 * hash + Objects.hashCode(pageSize.toString());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final QueryParamFiltersDTO other = (QueryParamFiltersDTO) obj;
        
        if (!(this.pageNumber == other.getPageNumber())) {
            return false;
        }
        
        if (!(this.pageSize == other.getPageSize())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "QueryParamFiltersDTO {"
				.concat("pageNumber=")
				.concat(pageNumber.toString())
				.concat(", pageSize=")
				.concat(pageSize.toString())
				.concat("}");
	}	

	public QueryParamFilters toQueryParamFilters() {
		return new QueryParamFilters(
				pageNumber, 
				pageSize, 
				filters != null ?
						filters.stream()
						.map(i -> i.toFilter())
						.collect(Collectors.toList()) : null, 
				sortOrders != null ?
						sortOrders.stream()
						.map(i -> i.toSortOrder())
						.collect(Collectors.toList()) : null
				);
	}

}
