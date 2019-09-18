package com.web.controller;
/**
 * 订单信息
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.web.model.Emp;
import com.web.model.OrderDetail;
import com.web.model.OrderModel;
import com.web.model.Product;
import com.web.model.Supplier;
import com.web.query.OrderModelQuery;
import com.web.service.OrderModelService;
import com.web.service.ProductService;
import com.web.service.SupplierService;
import com.web.utils.ERPConstants;
import com.web.utils.Page;

public class OrderModelAction extends BaseAction {
	
	private OrderModelQuery query = new OrderModelQuery();
	
	private OrderModel order = new OrderModel();
	
	private OrderModelService orderModelService;
	//为订单提供供应商信息
	private SupplierService supplierService;
	
	private Integer [] productTypeId;
	private Integer [] productId;
	private Integer [] detailNum;
	private Double [] detailPrice;
	//为订单提供商品类的服务
	private ProductService productService;
	//前端获取操作日志
	private String note;
	
	public String orderModel_list(){
		ActionContext context = ActionContext.getContext();	
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}	
		Page page = orderModelService.queryObjByCondition(query, exclude);	
		context.put("page", page);
		return SUCCESS;
	}
	
	//为订单审批提供数据
	public String orderModel_checklist(){		
		ActionContext context = ActionContext.getContext();		
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}		
		Page page = orderModelService.queryObjByCondition(query, exclude);		
		context.put("page", page);
		return SUCCESS;
	}
	
	//跳转到审核弹窗
	public String orderModel_auditText(){
		return SUCCESS;
	}
	
	//为ordel文件夹下面的input提供数据，并支持其跳转
	public String orderModel_input(){
		ActionContext context = ActionContext.getContext();
		//查询所有的供应商
		List<Supplier> suppliers = supplierService.list();
		//提供供应商信息
		context.put("suppliers", suppliers);
		return SUCCESS;
	}
	
	//为订单审核显示信息
	public String orderModel_orderDetail(){
		order = orderModelService.getObj(query.getOrderId());
		return SUCCESS;
	}
	
	//为订单修改页面
	public String orderModel_update(){
		ActionContext context = ActionContext.getContext();
		//查询所有的供应商
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		order = orderModelService.getObj(query.getOrderId());
		return SUCCESS;
	}
	
	/**
	 * ajax方法区----------------------------------------------------------
	 * @throws IOException
	 */
	//保存采购订单信息
	public void ajax_orderModel_submitOrder() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		Supplier supplier = supplierService.getObj(order.getSupplierId());
		order.setSupplier(supplier);
		order.setOrderNum(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		order.setOrderCreater(emp);
		order.setCreateTime(new Date());
		order.setOrderType(new Integer(ERPConstants.ORDER_TYPE_BUY));
		order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_BUY_AUDIT));
		int totalNum = 0;
		double totalPrice = 0;
		Set<OrderDetail> ods = new HashSet<OrderDetail>();
		for(int i = 0; i< productTypeId.length; i++){
			OrderDetail od = new OrderDetail();
			od.setDetailNum(detailNum[i]);
			od.setDetailPrice(detailPrice[i]);
			Product product = productService.getObj(productId[i]);
			od.setProduct(product);
			od.setDetailNum(detailNum[i]);
			od.setSurplus(detailNum[i]);
			ods.add(od);
			totalNum = totalNum + detailNum[i];
			totalPrice = totalPrice + detailNum[i]*detailPrice[i];
		}
		order.setTotalNum(totalNum);
		order.setTotalPrice(totalPrice);
		order.setDetails(ods);
		orderModelService.submitOrder(order);
		response.getWriter().write("success");
	}
	
	//对审核订单信息保存到日志中
	public void ajax_orderModel_auditOrder() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		orderModelService.updateAuditOrder(emp, order, note);
		response.getWriter().write("success");
	}
	
	//修改订单信息
	public void ajax_orderModel_updateOrder() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		
		Supplier supplier = supplierService.getObj(order.getSupplier().getSupplierId());
		order.setSupplier(supplier);
		order.setOrderCreater(emp);
		order.setCreateTime(new Date());
		order.setOrderType(new Integer(ERPConstants.ORDER_TYPE_BUY));
		order.setOrderState(new Integer(ERPConstants.ORDER_TYPE_BUY_AUDIT));
		int totalNum = 0;
		double totalPrice = 0;
		
		Set<OrderDetail> ods = new HashSet<OrderDetail>();
		for(int i = 0; i< productTypeId.length; i++){
			OrderDetail od = new OrderDetail();
			od.setDetailNum(detailNum[i]);
			od.setDetailPrice(detailPrice[i]);
			Product product = productService.getObj(productId[i]);
			od.setProduct(product);
			od.setDetailNum(detailNum[i]);
			od.setSurplus(detailNum[i]);
			ods.add(od);
			totalNum = totalNum + detailNum[i];
			totalPrice = totalPrice + detailNum[i]*detailPrice[i];
		}
		order.setTotalNum(totalNum);
		order.setTotalPrice(totalPrice);
		order.setDetails(ods);
		orderModelService.updateOrder(order);
		response.getWriter().write("success");
	}
	
	/**
	 * 所有的get和set方法
	 * -----------------------------------------------------------------
	 * @return
	 */
	public OrderModelQuery getQuery() {
		return query;
	}

	public void setQuery(OrderModelQuery query) {
		this.query = query;
	}
	
	public OrderModelService getOrderModelService() {
		return orderModelService;
	}

	public void setOrderModelService(OrderModelService orderModelService) {
		this.orderModelService = orderModelService;
	}

	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public Integer[] getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer[] productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer[] getProductId() {
		return productId;
	}

	public void setProductId(Integer[] productId) {
		this.productId = productId;
	}

	public Integer[] getDetailNum() {
		return detailNum;
	}

	public void setDetailNum(Integer[] detailNum) {
		this.detailNum = detailNum;
	}

	public Double[] getDetailPrice() {
		return detailPrice;
	}

	public void setDetailPrice(Double[] detailPrice) {
		this.detailPrice = detailPrice;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
