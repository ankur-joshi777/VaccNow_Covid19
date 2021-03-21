package com.vaccnow.covidvaccination.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {

	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private long totalSize;

}
