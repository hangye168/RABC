package com.web.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import com.web.dao.ConsoleLogDao;
import com.web.dao.OrderDetailDao;
import com.web.dao.OrderModelDao;
import com.web.model.ConsoleLog;
import com.web.model.Emp;
import com.web.model.OrderModel;
import com.web.query.OrderModelQuery;
import com.web.service.OrderModelService;
import com.web.utils.ERPConstants;

public class OrderModelServiceImpl extends BaseServiceImpl<OrderModel, OrderModelQuery> implements OrderModelService {

	private OrderModelDao orderModelDao;
	
	private ConsoleLogDao consoleLogDao;
	
	private OrderDetailDao orderDetailDao;
	
	public void setOrderModelDao(OrderModelDao orderModelDao) {
		this.orderModelDao = orderModelDao;
		this.baseDao = orderModelDao;
	}

	@Override
	public void submitOrder(OrderModel order) {
		// TODO Auto-generated method stub
		orderModelDao.save(order);
	}

	//订单审核
	@Override
	public void updateAuditOrder(Emp checker, OrderModel order, String note) {
		// TODO Auto-generated method stub
		OrderModel order1 = orderModelDao.getObj(order.getOrderId());
		order1.setOrderState(order.getOrderState());
		order1.setCheckTime(new Date());
		order1.setOrderChecker(checker);
		
		//创建一个日志对象
		ConsoleLog cl = new ConsoleLog();
		cl.setEmp(checker);
		cl.setEntityId(order.getOrderId());
		cl.setNote(note);
		cl.setOptTime(new Timestamp(new Date().getTime()));
		cl.setOptType("审核订单");
		cl.setTableName("order_model");
		
		consoleLogDao.save(cl);
	}
	//订单修改
	@Override
	public void updateOrder(OrderModel order){
		orderDetailDao.deleteDetailByOrderId(order.getOrderId());
		orderModelDao.update(order);
	}

	//保存运输信息
	@Override
	public void updateAssginOrder(OrderModel order) {
		// TODO Auto-generated method stub
		OrderModel order1 = orderModelDao.getObj(order.getOrderId());
		order1.setOrderCompleter(order.getOrderCompleter());
		order1.setOrderType(new Integer(ERPConstants.ORDER_TYPE_TRANS));
		order1.setOrderState(new Integer(ERPConstants.ORDER_TYPE_TRANS_BUY));
	}
	
	/**
	 * set和get方法区
	 * @return
	 */
	public ConsoleLogDao getConsoleLogDao() {
		return consoleLogDao;
	}
	public void setConsoleLogDao(ConsoleLogDao consoleLogDao) {
		this.consoleLogDao = consoleLogDao;
	}
	public OrderModelDao getOrderModelDao() {
		return orderModelDao;
	}
	public OrderDetailDao getOrderDetailDao() {
		return orderDetailDao;
	}
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
}
