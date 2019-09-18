package com.web.model;
/**
 * 订单明细表
 * @author sh
 *
 */
public class OrderDetail  implements java.io.Serializable {

     private Integer orderDetailId;
     private Integer detailNum;
     private Double detailPrice;
     private Integer productId;
     private String orderId;
     private Integer surplus;//商品入库的数量
     
     private Product product;

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Integer getDetailNum() {
		return detailNum;
	}

	public void setDetailNum(Integer detailNum) {
		this.detailNum = detailNum;
	}

	public Double getDetailPrice() {
		return detailPrice;
	}

	public void setDetailPrice(Double detailPrice) {
		this.detailPrice = detailPrice;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getSurplus() {
		return surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", detailNum="
				+ detailNum + ", detailPrice=" + detailPrice + ", productId="
				+ productId + ", orderId=" + orderId + ", surplus=" + surplus
				+ ", product=" + product + "]";
	}

	public OrderDetail(Integer orderDetailId, Integer detailNum,
			Double detailPrice, Integer productId, String orderId,
			Integer surplus, Product product) {
		super();
		this.orderDetailId = orderDetailId;
		this.detailNum = detailNum;
		this.detailPrice = detailPrice;
		this.productId = productId;
		this.orderId = orderId;
		this.surplus = surplus;
		this.product = product;
	}

	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
}