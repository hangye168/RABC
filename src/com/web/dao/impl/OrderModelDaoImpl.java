package com.web.dao.impl;

import org.apache.commons.lang.StringUtils;

import com.web.dao.OrderModelDao;
import com.web.model.OrderModel;
import com.web.query.OrderModelQuery;

public class OrderModelDaoImpl extends BasedaoImpl<OrderModel, OrderModelQuery> implements OrderModelDao {
	@Override
	public String createHql(OrderModelQuery q) {
		String hql = "from OrderModel t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}
	
	@Override
	public String createHqlCount(OrderModelQuery q) {
		// TODO Auto-generated method stub
		String hql = "select count(orderId) from OrderModel t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(OrderModelQuery q) {
		// TODO Auto-generated method stub
		String hql = "";
		if(StringUtils.isNotBlank(q.getCreateName())){
			hql = hql + " and t.orderCreater.name like :createrName";
		}
		//订单数量区间
		if(q.getMinTotalNum() != null){
			hql = hql + " and t.totalNum >= :minTotalNum";
		}
		if(q.getMaxTotalNum() != null){
			hql = hql + " and t.totalNum <= :maxTotalNum";
		}
		//订单创建时间区间
		if(q.getMinCreateTime() != null){
			hql = hql + " and t.createTime >= :minCreateTime";
		}
		if(q.getMaxCreateTime() != null){
			hql = hql + " and t.createTime <= :maxCreateTime";
		}
		//订单总价区间
		if(q.getMinTotalPrice() != null){
			hql = hql + " and t.totalPrice >= :minTotalPrice";
		}
		if(q.getMaxTotalPrice() != null){
			hql = hql + " and t.totalPrice <= :maxTotalPrice";
		}
		if(q.getOrderType() != null){
			hql = hql + " and t.orderType = :orderType";
		}
		if(q.getOrderState() != null){
			hql = hql + " and t.orderState = :orderState";
		}
		if(q.getCompleter() != null){
			hql = hql + " and t.orderCompleter.empId = :completer";
		}
		return hql;
	}
}
