package com.web.model;
/**
 * 仓库信息
 */
import java.util.Set;

public class Store implements java.io.Serializable {

	private Integer storeId;
	private Integer stockman;
	private String name;
	private String address;
	//仓库管理员
	private Emp storeAdmin;
	//在StoreDetail中保存字段，将仓库中物资数据保存
	private Set<StoreDetail> details;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getStockman() {
		return stockman;
	}

	public void setStockman(Integer stockman) {
		this.stockman = stockman;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Emp getStoreAdmin() {
		return storeAdmin;
	}

	public void setStoreAdmin(Emp storeAdmin) {
		this.storeAdmin = storeAdmin;
	}

	public Set<StoreDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<StoreDetail> details) {
		this.details = details;
	}
	
}