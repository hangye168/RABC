package com.web.query;
/**
 * 员工类的时间管理
 * 查询对象
 */
import java.util.Date;

import com.web.model.Emp;

public class EmpQuery extends Emp{
	
	private Date startBirth; 
	private Date endBirth;
	
	private int pageNo;
	private int startNum;
	
	public Date getStartBirth() {
		return startBirth;
	}
	public void setStartBirth(Date startBirth) {
		this.startBirth = startBirth;
	}
	public Date getEndBirth() {
		return endBirth;
	}
	public void setEndBirth(Date endBirth) {
		this.endBirth = endBirth;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
}
