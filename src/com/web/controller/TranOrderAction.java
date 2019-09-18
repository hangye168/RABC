package com.web.controller;
/**
 * ��Ʒ������Ϣ
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
	//�洢������Ϣ
	private OrderModel order = new OrderModel();
	//ע�빩Ӧ��
	private SupplierService supplierService;
	//ע����Ʒ
	private ProductService productService;
	//ע�벿����Ϣ
	private DepService depService;
	//ע�붩��
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
	
	//��ѯ�����Ͳ�����Ϣ����Ҫ����ģ� Ϊǰ����ʾʹ��
	public String tranOrder_taskDetail(){
		order = orderModelService.getObj(order.getOrderId());
		//��ѯ���Ϊ2�Ĳ���
		Dep dep = depService.getObj(2);
		ActionContext context = ActionContext.getContext();
		context.put("dep", dep);
		return SUCCESS;
	}
	
	//���������ѯ
	public String tranOrder_tasks(){
		ActionContext context = ActionContext.getContext();
		//��ȡ��ǰ�û�
		Map<String, Object> session2 = context.getSession();
		Emp emp = (Emp) session2.get("user");
		//�����������
		query.setCompleter(emp.getEmpId());
		Integer pageNo = query.getPageNo();
		if(pageNo == null ){
			query.setPageNo(1);
		}
		Page page = orderModelService.queryObjByCondition(query, exclude);
		context.put("page", page);
		return SUCCESS;
	}
	
	//չʾ�������������
	public String tranOrder_taskDetailbuying(){
		order = orderModelService.getObj(order.getOrderId());
		Dep dep = depService.getObj(2);
		ActionContext context = ActionContext.getContext();
		context.put("dep", dep);
		return SUCCESS;
	}
	
	//���
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
	
	//���ʱ�Ķ�����ϸ
	public String tranOrder_inDetail(){
		order = orderModelService.getObj(query.getOrderId());
		return "store_inDetail";
	}
	/**
	 * ajax������--------------------------------------------------------------------
	 * @throws IOException
	 */
	//ͨ��ajax�ύ����ָ�����������Ϣ
	public void ajax_tranOrder_assginOrder() throws IOException{
		orderModelService.updateAssginOrder(order);
		response.getWriter().write("success");
	}
	//ͨ��ajax����Ϊ���������ȡ��Ʒ��Ϣ
	public void ajax_tranOrder_getOrderProduct() throws IOException{
		OrderModel order1 = orderModelService.getObj(order.getOrderId());
		order1.setOrderState(3);
		orderModelService.update(order1);
		response.getWriter().write("success");
	}
	//��ɶ���
	public void ajax_tranOrder_finishTranOrder() throws IOException{
		OrderModel order1 = orderModelService.getObj(order.getOrderId());
		order1.setOrderType(3);
		order1.setOrderState(1);
		orderModelService.update(order1);
		response.getWriter().write("success");
	}
	
	/**
	 * set��get������
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
