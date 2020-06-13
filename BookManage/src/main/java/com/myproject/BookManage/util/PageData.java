package com.myproject.BookManage.util;

import java.io.Serializable;
import java.util.List;

public class PageData implements Serializable{

	private static final long serialVersionUID = 6572340587383882894L;
	
	private Integer minPage;
	private Integer maxPage;
	private Integer currentPage;
	private Integer recordCountPerPage;
	private Integer maxRecordCount;
	private Integer currentRecordCount;
	private List<?> listData;
	private String keyword;
	
	

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getMinPage() {
		return minPage;
	}

	public void setMinPage(Integer minPage) {
		this.minPage = minPage;
	}

	public Integer getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(Integer recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public Integer getMaxRecordCount() {
		return maxRecordCount;
	}

	public void setMaxRecordCount(Integer maxRecordCount) {
		this.maxRecordCount = maxRecordCount;
	}

	public Integer getCurrentRecordCount() {
		return currentRecordCount;
	}

	public void setCurrentRecordCount(Integer currentRecordCount) {
		this.currentRecordCount = currentRecordCount;
	}

	public List<?> getListData() {
		return listData;
	}

	public void setListData(List<?> listData) {
		this.listData = listData;
	}

	@Override
	public String toString() {
		return "PageData [minPage=" + minPage + ", maxPage=" + maxPage + ", currentPage=" + currentPage
				+ ", recordCountPerPage=" + recordCountPerPage + ", maxRecordCount=" + maxRecordCount
				+ ", currentRecordCount=" + currentRecordCount + ", listData=" + listData + "]";
	}

}
