package com.device.util;

public class Pagination {

	public static int DEFAULT_PAGE_COUNT = 150;
	
	public Pagination(int pageNO) {
		this.pageNO = 1;
		pageCount = DEFAULT_PAGE_COUNT ;
		recordSum = -1;
		pageSum = 1;
		prePageNO = 1;
		nextPageNO = 1;
		needRecordSum = true;
		this.pageNO = pageNO;
	}

	public Pagination() {
		this.pageNO = 1;
		pageCount = DEFAULT_PAGE_COUNT ;
		recordSum = -1;
		pageSum = 1;
		prePageNO = 1;
		nextPageNO = 1;
		needRecordSum = true;
	}

	public Pagination(int pageNO, int pageCount) {
		this.pageNO = 1;
		this.pageCount = DEFAULT_PAGE_COUNT ;
		recordSum = -1;
		pageSum = 1;
		prePageNO = 1;
		nextPageNO = 1;
		needRecordSum = true;
		this.pageNO = pageNO;
		this.pageCount = pageCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNO() {
		return pageNO;
	}

	public void setPageNO(int pageNO) {
		if (recordSum > -1 && pageNO > getPageSum())
			this.pageNO = getPageSum();
		else
			this.pageNO = pageNO;
	}

	public int getPageSum() {
		return (recordSum - 1) / pageCount + 1;
	}

	public int getStartIndex() {
		int i = (pageNO - 1) * pageCount + 1;
		return i <= 0 ? 0 : i;
	}

	public int getEndIndex() {
		int i = pageNO * pageCount - 1;
		return i <= recordSum ? i : recordSum;
	}

	public int getRecordSum() {
		return recordSum;
	}

	public void setRecordSum(int recordSum) {
		this.recordSum = recordSum;
	}

	public int getNextPageNO() {
		return pageNO >= getPageSum() ? getPageSum() : pageNO + 1;
	}

	public int getPrePageNO() {
		return pageNO <= 1 ? 1 : pageNO - 1;
	}

	public boolean isNeedRecordSum() {
		return needRecordSum;
	}

	public Pagination(int pageNO, int pageCount, boolean needRecordSum) {
		this.pageNO = 1;
		this.pageCount = DEFAULT_PAGE_COUNT ;
		recordSum = -1;
		pageSum = 1;
		prePageNO = 1;
		nextPageNO = 1;
		this.needRecordSum = true;
		this.pageNO = pageNO;
		this.pageCount = pageCount;
		this.needRecordSum = needRecordSum;
	}

	private int pageNO;
	private int pageCount;
	private int recordSum;
	private int pageSum;
	private int prePageNO;
	private int nextPageNO;
	private boolean needRecordSum;

	/**
	 * @param needRecordSum the needRecordSum to set
	 */
	public void setNeedRecordSum(boolean needRecordSum) {
		this.needRecordSum = needRecordSum;
	}
}
