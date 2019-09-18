package com.web.query;

import java.util.Date;

import com.web.model.OrderModel;

public class OrderModelQuery extends OrderModel{

	private int pageNo;
	private int startNum;

	//����������
	private String createName;
	
	//������������
	private Integer minTotalNum;
	private Integer maxTotalNum;
	
	//��������ʱ������
	private Date minCreateTime;
	private Date maxCreateTime;
	
	//�����ܼ�����
	private Double minTotalPrice;
	private Double maxTotalPrice;
	
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
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Integer getMinTotalNum() {
		return minTotalNum;
	}
	public void setMinTotalNum(Integer minTotalNum) {
		this.minTotalNum = minTotalNum;
	}
	public Integer getMaxTotalNum() {
		return maxTotalNum;
	}
	public void setMaxTotalNum(Integer maxTotalNum) {
		this.maxTotalNum = maxTotalNum;
	}
	public Date getMinCreateTime() {
		return minCreateTime;
	}
	public void setMinCreateTime(Date minCreateTime) {
		this.minCreateTime = minCreateTime;
	}
	public Date getMaxCreateTime() {
		return maxCreateTime;
	}
	public void setMaxCreateTime(Date maxCreateTime) {
		this.maxCreateTime = maxCreateTime;
	}
	public Double getMinTotalPrice() {
		return minTotalPrice;
	}
	public void setMinTotalPrice(Double minTotalPrice) {
		this.minTotalPrice = minTotalPrice;
	}
	public Double getMaxTotalPrice() {
		return maxTotalPrice;
	}
	public void setMaxTotalPrice(Double maxTotalPrice) {
		this.maxTotalPrice = maxTotalPrice;
	}
}
