package com.web.model;
/**
 * 商品表
 * @author sh
 *
 */
public class Product implements java.io.Serializable {

	private Integer productId;
	private Integer productTypeId;
	private String name;
	private String origin;
	private String producer;
	private String unit;	//商品的计量单位
	private Double inPrice;
	private Double outPrice;
	//多个商品对应一个类别
	private ProductType productType;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getInPrice() {
		return inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}

	public Double getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productTypeId="
				+ productTypeId + ", name=" + name + ", origin=" + origin
				+ ", producer=" + producer + ", unit=" + unit + ", inPrice="
				+ inPrice + ", outPrice=" + outPrice + ", productType="
				+ productType + "]";
	}

	public Product(Integer productId, Integer productTypeId, String name,
			String origin, String producer, String unit, Double inPrice,
			Double outPrice, ProductType productType) {
		super();
		this.productId = productId;
		this.productTypeId = productTypeId;
		this.name = name;
		this.origin = origin;
		this.producer = producer;
		this.unit = unit;
		this.inPrice = inPrice;
		this.outPrice = outPrice;
		this.productType = productType;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}