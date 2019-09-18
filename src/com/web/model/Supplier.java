package com.web.model;

import java.util.Set;

/**
 * 供应商信息表
 */

public class Supplier implements java.io.Serializable {

	private Integer supplierId;
	private String name;
	private String address;
	private String contact;
	private String tel;
	private Integer needs;
	
	private Set<ProductType> pts;
	
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getNeeds() {
		return needs;
	}
	public void setNeeds(Integer needs) {
		this.needs = needs;
	}
	
	public Set<ProductType> getPts() {
		return pts;
	}
	public void setPts(Set<ProductType> pts) {
		this.pts = pts;
	}
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Supplier(Integer supplierId, String name, String address,
			String contact, String tel, Integer needs, Set<ProductType> pts) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.tel = tel;
		this.needs = needs;
		this.pts = pts;
	}
	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", name=" + name
				+ ", address=" + address + ", contact=" + contact + ", tel="
				+ tel + ", needs=" + needs + ", pts=" + pts + "]";
	}
}