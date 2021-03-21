package com.vaccnow.covidvaccination.response;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseBody<T> {

	List<T> data;

	Pagination paginationDetails;

	public ResponseBody(List<T> data, int pageNumber, int pageSize, int totalPages, long totalSize) {
		this.data = data;
		this.paginationDetails = Pagination.builder().pageNumber(pageNumber).pageSize(pageSize)
				.totalPages(totalPages).totalSize(totalSize).build();
	}

	public ResponseBody(List<T> data) {
		this.data = data;
	}

}