package com.web.model;

import java.util.Set;

/**
 * 商品类
 * @author sh
 *
 */

public class ProductType implements java.io.Serializable {

	private Integer productTypeId;
	private Integer supplierId;
	private String name;
	//商品与供应商之间，多对一
	private Supplier supplier;
	
	//为订单信息 显示商品类型
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