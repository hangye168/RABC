package com.web.model;
/**
 * �ֿ�����
 * @author hasee
 *
 */
public class StoreDetail implements java.io.Serializable {

	private Integer detailId;
	private Integer storeId;
	private Integer productId;
	private Integer num;
	//��Ʒ��Ϣ
	private Product product;

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}