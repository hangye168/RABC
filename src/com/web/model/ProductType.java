package com.web.model;

import java.util.Set;

/**
 * ��Ʒ��
 * @author sh
 *
 */

public class ProductType implements java.io.Serializable {

	private Integer productTypeId;
	private Integer supplierId;
	private String name;
	//��Ʒ�빩Ӧ��֮�䣬���һ
	private Supplier supplier;
	
	//Ϊ������Ϣ ��ʾ��Ʒ����
	private Set<Product> products;
	
	public Integer getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
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
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}