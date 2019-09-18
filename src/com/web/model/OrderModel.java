package com.web.model;
/**
 * 订单信息
 */
import java.util.Date;
import java.util.Set;

public class OrderModel implements java.io.Serializable {

	private Integer orderId;
	private String orderNum;
	private Integer creater;
	private Date createTime;
	private Integer checker;
	private Date checkTime;
	private Integer completer;
	private Date endTime;
	private Integer orderType;
	private Integer orderState;
	private Integer totalNum;
	private Double totalPrice;
	private Integer supplierId;
	
	//订单创建人
	private Emp orderCreater;
	//订单审核人
	private Emp orderChecker;
	//供应商信息
	private Supplier supplier;
	//订单明细
	private Set<OrderDetail> details;
	//订单与日志至之间是一对多关系
	private Set<ConsoleLog> logs;
	//创建跟单人
	private Emp orderCompleter;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getCreater() {
		return creater;
	}
	public void setCreater(Integer creater) {
		this.creater = creater;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getChecker() {
		return checker;
	}
	public void setChecker(Integer checker) {
		this.checker = checker;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public Integer getCompleter() {
		return completer;
	}
	public void setCompleter(Integer completer) {
		this.completer = completer;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Emp getOrderCreater() {
		return orderCreater;
	}
	public void setOrderCreater(Emp orderCreater) {
		this.orderCreater = orderCreater;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Set<OrderDetail> getDetails() {
		return details;
	}
	public void setDetails(Set<OrderDetail> details) {
		this.details = details;
	}
	public Set<ConsoleLog> getLogs() {
		return logs;
	}
	public void setLogs(Set<ConsoleLog> logs) {
		this.logs = logs;
	}
	public Emp getOrderChecker() {
		return orderChecker;
	}
	public void setOrderChecker(Emp orderChecker) {
		this.orderChecker = orderChecker;
	}
	public Emp getOrderCompleter() {
		return orderCompleter;
	}
	public void setOrderCompleter(Emp orderCompleter) {
		this.orderCompleter = orderCompleter;
	}
	
}