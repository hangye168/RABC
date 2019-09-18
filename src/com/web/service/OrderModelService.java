package com.web.service;

import com.web.model.Emp;
import com.web.model.OrderModel;
import com.web.query.OrderModelQuery;

public interface OrderModelService extends BaseService<OrderModel, OrderModelQuery>{
	//�ύ����
	public void submitOrder(OrderModel order);
	//���涩�������Ϣ
	public void updateAuditOrder(Emp checker, OrderModel order, String note);
	//�޸Ķ�����Ϣ
	public void updateOrder(OrderModel order);
	//ָ����˭ȥ����
	public void updateAssginOrder(OrderModel order);
	
}
