package com.vaccnow.covidvaccination.response;

public class Pagination {

	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private long totalSize;

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public long getTotalSize() {
		return totalSize;
	}

	private Pagination() {
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private int pageNumber;
		private int pageSize;
		private int totalPages;
		private long totalSize;

		public Pagination build() {
			Pagination pagination = new Pagination();
			pagination.pageNumber = this.pageNumber;
			pagination.pageSize = this.pageSize;
			pagination.totalPages = this.totalPages;
			pagination.totalSize = this.totalSize;
			return pagination;
		}

		public Builder pageNumber(int pageNumber) {
			this.pageNumber = pageNumber;
			return this;
		}

		public Builder pageSize(int pageSize) {
			this.pageSize = pageSize;
			return this;
		}

		public Builder totalPages(int totalPages) {
			this.totalPages = totalPages;
			return this;
		}

		public Builder totalSize(long totalSize) {
			this.totalSize = totalSize;
			return this;
		}
	}
}
