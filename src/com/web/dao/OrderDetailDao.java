package com.web.dao;

import com.web.model.OrderDetail;
import com.web.query.OrderDetailQuery;

public interface OrderDetailDao extends BaseDao<OrderDetail, OrderDetailQuery>{
	//�������޸���ɾ�����������
	public void deleteDetailByOrderId(Integer orderId);
}
