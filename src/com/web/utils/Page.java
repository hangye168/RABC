package com.web.utils;
/**
 * 分页的相关参数
 */
import java.util.List;

public class Page {
	
	//页码
	private int pageNo = 1;
	//每页的数据条数
	private int pageSize = 5;
	//指定查询条件下的总条数
	private int totalCount = 0;
	//指定查询条件下的总页数
	private int totalPage = 1;
	//开始行号 = (pageNo - 1)*pageSize
	private int startNum = 0;
	//结果集
	private List<?> list;
	
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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * 计算页数
	 * @return
	 */
	public int getTotalPage() {
		totalPage = totalCount / pageSize;
		if(totalCount == 0 || totalPage % pageSize != 0){
			totalPage++;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * 根据页码和每页显示数据数来计算开始行号
	 * @return
	 */
	public int getStartNum() {
		return (pageNo - 1) * pageSize;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
}
