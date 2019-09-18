package com.web.model;
/**
 * �ֿ���Ϣ
 */
import java.util.Set;

public class Store implements java.io.Serializable {

	private Integer storeId;
	private Integer stockman;
	private String name;
	private String address;
	//�ֿ����Ա
	private Emp storeAdmin;
	//��StoreDetail�б����ֶΣ����ֿ����������ݱ���
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