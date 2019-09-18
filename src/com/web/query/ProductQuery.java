package com.web.query;
/**
 * ��Ʒ��Ĳ�ѯ����
 */
import com.web.model.Product;

public class ProductQuery extends Product{

	private int pageNo;
	private int startNum;

	private Integer supplierId;
	
	//�۸���ߵͽ����ۺ���ߵͳ�����
	private Double minInPrice;
	private Double maxInPrice;
	private Double  minOutPrice;
	private Double  maxOutPrice;
	

	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
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
	public Double getMinInPrice() {
		return minInPrice;
	}
	public void setMinInPrice(Double minInPrice) {
		this.minInPrice = minInPrice;
	}
	public Double getMaxInPrice() {
		return maxInPrice;
	}
	public void setMaxInPrice(Double maxInPrice) {
		this.maxInPrice = maxInPrice;
	}
	public Double getMinOutPrice() {
		return minOutPrice;
	}
	public void setMinOutPrice(Double minOutPrice) {
		this.minOutPrice = minOutPrice;
	}
	public Double getMaxOutPrice() {
		return maxOutPrice;
	}
	public void setMaxOutPrice(Double maxOutPrice) {
		this.maxOutPrice = maxOutPrice;
	}
}
