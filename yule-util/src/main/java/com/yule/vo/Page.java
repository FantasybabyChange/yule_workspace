package com.yule.vo;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2344705226299587074L;
	// 每页的记录数
	private int pageSize = 10;
	// 当前页
	private int pageNo = 1;
	// 总行数
	private int rowCount;
	// 总页数
	private int pageCount;
	
	private List<T> datas;

	public Page() {
		
	}

	public Page(int pageNo) {
		this.pageNo = pageNo;
	}

	public Page(int pageSize, int pageNo) {
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		if (rowCount % pageSize == 0) {
			this.pageCount = rowCount / pageSize;
		} else {
			this.pageCount = rowCount / pageSize + 1;
		}
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Page(int pageSize, int pageNo, int rowCount, int pageCount,
			List<T> datas) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.rowCount = rowCount;
		this.pageCount = pageCount;
		this.datas = datas;
	}
	
	public int getNextPage(){
		if (this.pageNo + 1 > this.pageCount) {
			return this.pageNo;
		} else {
			return this.pageNo+1;
		}
	}
	
	public int getPreviousPage(){
		if (this.pageNo - 1 == 0) {
			return 1;
		} else {
			return this.pageNo - 1;
		}
	}
	
	public int getFirstPage(){
		return 1;
	}
	
	public int getLastPage(){
		return this.pageCount;
	}
	
	public void cleanDatas(){
		if(null!=this.datas){
			this.datas.clear();
			this.datas = null;
		}
	}
	
}
