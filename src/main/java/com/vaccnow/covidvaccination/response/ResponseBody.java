package com.vaccnow.covidvaccination.response;

import java.util.List;

public class ResponseBody<T> {

	List<T> data;

	Pagination paginationDetails;

	public ResponseBody(List<T> data, int pageNumber, int pageSize, int totalPages, long totalSize) {
		this.data = data;
		this.paginationDetails = new Pagination.Builder().pageNumber(pageNumber).pageSize(pageSize)
				.totalPages(totalPages).totalSize(totalSize).build();
	}

	public ResponseBody(List<T> data) {
		this.data = data;
	}

	public ResponseBody() {
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Pagination getPaginationDetails() {
		return paginationDetails;
	}

	public void setPaginationDetails(Pagination paginationDetails) {
		this.paginationDetails = paginationDetails;
	}
}