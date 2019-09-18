package com.web.controller;
/**
 * 显示订单明细
 */
import com.web.query.OrderDetailQuery;
import com.web.service.OrderDetailService;
import com.web.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class OrderDetailAction extends BaseAction {
	
	private OrderDetailQuery query = new OrderDetailQuery();
	
	private OrderDetailService orderDetailService;
	
	public String orderDetail_list(){	
		ActionContext context = ActionContext.getContext();	
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}	
		Page page = orderDetailService.queryObjByCondition(query, exclude);	
		context.put("page", page);
		return SUCCESS;
	}
	
	public String orderDetail_input(){
		return SUCCESS;
	}
	
	/**
	 * 所有的get和set方法
	 * -----------------------------------------------------------------
	 * @return
	 */
	public OrderDetailQuery getQuery() {
		return query;
	}

	public void setQuery(OrderDetailQuery query) {
		this.query = query;
	}
	
	public OrderDetailService getOrderDetailService() {
		return orderDetailService;
	}

	public void setOrderDetailService(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}
	
}
