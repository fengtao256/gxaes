package com.gxaes.es.common;
/*
 * 封装分页逻辑以及参数辅助类
 */

import java.util.List;

@SuppressWarnings("rawtypes")

public class QuestionPage {
	
	private List dataOfCurrentPage;//错题的集合
	private int	totalRowCount;//总行数
	private int pageNo;
	private int pageSize;
	private int totalPageCount;
	private boolean hasPrev;
	private boolean hasNext;
	private int prevPageNo;
	private int	NextPageNo;//下一页页码
	
	public List getDataOfCurrentPage() {
		return dataOfCurrentPage;
	}
	public void setDataOfCurrentPage(List dataOfCurrentPage) {
		this.dataOfCurrentPage = dataOfCurrentPage;
	}
	public int getTotalRowCount() {
		return totalRowCount;
	}
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPageCount() {
		this.totalPageCount=(totalRowCount-1)/pageSize + 1;
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public boolean isHasPrev() {
		this.hasPrev = this.pageNo > 1;
		return hasPrev;
	}
	public void setHasPrev(boolean hasPrev) {
		this.hasPrev = hasPrev;
	}
	public boolean isHasNext() {
		this.hasNext = this.pageNo < this.totalPageCount;
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public int getPrevPageNo() {
		this.prevPageNo = this.pageNo-1;
		return prevPageNo;
	}
	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}
	public int getNextPageNo() {
		this.NextPageNo = this.pageNo + 1;
		return NextPageNo;
	}
	public void setNextPageNo(int nextPageNo) {
		NextPageNo = nextPageNo;
	}
}
