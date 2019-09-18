package com.web.dao;

import com.web.model.OrderDetail;
import com.web.query.OrderDetailQuery;

public interface OrderDetailDao extends BaseDao<OrderDetail, OrderDetailQuery>{
	//订单的修改是删除后重新添加
	public void deleteDetailByOrderId(Integer orderId);
}
