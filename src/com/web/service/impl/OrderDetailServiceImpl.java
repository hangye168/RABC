package com.web.service.impl;

import com.web.dao.OrderDetailDao;
import com.web.model.OrderDetail;
import com.web.query.OrderDetailQuery;
import com.web.service.OrderDetailService;

public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail, OrderDetailQuery> implements OrderDetailService {

	private OrderDetailDao orderDetailDao;
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
		this.baseDao = orderDetailDao;
	}
	
	

}
