package com.web.service;

import com.web.model.Emp;
import com.web.model.OrderModel;
import com.web.query.OrderModelQuery;

public interface OrderModelService extends BaseService<OrderModel, OrderModelQuery>{
	//提交订单
	public void submitOrder(OrderModel order);
	//保存订单审核信息
	public void updateAuditOrder(Emp checker, OrderModel order, String note);
	//修改订单信息
	public void updateOrder(OrderModel order);
	//指定由谁去运输
	public void updateAssginOrder(OrderModel order);
	
}
