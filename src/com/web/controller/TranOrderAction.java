package com.web.controller;
/**
 * 商品运输信息
 */
import java.io.IOException;
import java.util.Map;

import com.web.model.Dep;
import com.web.model.Emp;
import com.web.model.OrderModel;
import com.web.query.OrderModelQuery;
import com.web.service.DepService;
import com.web.service.OrderModelService;
import com.web.service.ProductService;
import com.web.service.SupplierService;
import com.web.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class TranOrderAction extends BaseAction {
	
	
	private OrderModelQuery query = new OrderModelQuery();
	//存储订单信息
	private OrderModel order = new OrderModel();
	//注入供应商
	private SupplierService supplierService;
	//注入商品
	private ProductService productService;
	//注入部门信息
	private DepService depService;
	//注入订单
	private OrderModelService orderModelService;

	public String tranOrder_taskList(){
		ActionContext context = ActionContext.getContext();
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}
		Page page = orderModelService.queryObjByCondition(query, exclude);
		context.put("page", page);
		return SUCCESS;
	}
	
	//查询订单和部门信息（需要运输的） 为前端显示使用
	public String tranOrder_taskDetail(){
		order = orderModelService.getObj(order.getOrderId());
		//查询编号为2的部门
		Dep dep = depService.getObj(2);
		ActionContext context = ActionContext.getContext();
		context.put("dep", dep);
		return SUCCESS;
	}
	
	//运输任务查询
	public String tranOrder_tasks(){
		ActionContext context = ActionContext.getContext();
		//获取当前用户
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		//设置运输的人
		query.setCompleter(emp.getEmpId());
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		Page page = orderModelService.queryObjByCondition(query, exclude);
		context.put("page", page);
		return SUCCESS;
	}
	
	//展示运输任务的详情
	public String tranOrder_taskDetailbuying(){
		order = orderModelService.getObj(order.getOrderId());
		Dep dep = depService.getObj(2);
		ActionContext context = ActionContext.getContext();
		context.put("dep", dep);
		return SUCCESS;
	}
	
	//入库
	public String tranOrder_inList(){	
		ActionContext context = ActionContext.getContext();	
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}	
		Page page = orderModelService.queryObjByCondition(query, exclude);	
		context.put("page", page);	
		return "store_success";
	}
	
	//入库时的订单明细
	public String tranOrder_inDetail(){
		order = orderModelService.getObj(query.getOrderId());
		return "store_inDetail";
	}
	/**
	 * ajax方法区--------------------------------------------------------------------
	 * @throws IOException
	 */
	//通过ajax提交保存指派人运输的信息
	public void ajax_tranOrder_assginOrder() throws IOException{
		orderModelService.updateAssginOrder(order);
		response.getWriter().write("success");
	}
	//通过ajax方法为运输详情获取商品信息
	public void ajax_tranOrder_getOrderProduct() throws IOException{
		OrderModel order1 = orderModelService.getObj(order.getOrderId());
		order1.setOrderState(3);
		orderModelService.update(order1);
		response.getWriter().write("success");
	}
	//完成订单
	public void ajax_tranOrder_finishTranOrder() throws IOException{
		OrderModel order1 = orderModelService.getObj(order.getOrderId());
		order1.setOrderType(3);
		order1.setOrderState(1);
		orderModelService.update(order1);
		response.getWriter().write("success");
	}
	
	/**
	 * set和get方法区
	 */
	public OrderModelService getOrderModelService() {
		return orderModelService;
	}

	public void setOrderModelService(OrderModelService orderModelService) {
		this.orderModelService = orderModelService;
	}
	public void setDepService(DepService depService) {
		this.depService = depService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

	public OrderModelQuery getQuery() {
		return query;
	}

	public void setQuery(OrderModelQuery query) {
		this.query = query;
	}
}
