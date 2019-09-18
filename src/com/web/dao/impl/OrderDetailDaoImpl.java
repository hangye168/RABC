package com.web.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.web.dao.OrderDetailDao;
import com.web.model.OrderDetail;
import com.web.query.OrderDetailQuery;

public class OrderDetailDaoImpl extends BasedaoImpl<OrderDetail, OrderDetailQuery> implements OrderDetailDao {
	@Override
	public String createHql(OrderDetailQuery q) {
		String hql = "from OrderDetail t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}
	
	@Override
	public String createHqlCount(OrderDetailQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createHqlCondition(OrderDetailQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	//删除之指定的数据order
	@Override
	public void deleteDetailByOrderId(final Integer orderId) {
		// TODO Auto-generated method stub
		final String hql = "delete from OrderDetail t where t.orderId = :orderId";
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException{
				Query q = session.createQuery(hql);
				q.setParameter("orderId", orderId+"");
				try {
					q.executeUpdate();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}
