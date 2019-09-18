package com.web.dao.impl;

import com.web.dao.StoreDetailDao;
import com.web.model.StoreDetail;
import com.web.query.StoreDetailQuery;

public class StoreDetailDaoImpl extends BasedaoImpl<StoreDetail, StoreDetailQuery> implements StoreDetailDao {
	@Override
	public String createHql(StoreDetailQuery q) {
		String hql = "from StoreDetail t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}
	
	@Override
	public String createHqlCount(StoreDetailQuery q) {
		// TODO Auto-generated method stub
		String hql = " ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(StoreDetailQuery q) {
		// TODO Auto-generated method stub
		String hql = "";
		
		return hql;
	}
}
