package com.web.query;

import com.web.model.Supplier;

public class SupplierQuery extends Supplier{

	private int pageNo;
	private int startNum;

	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
}
