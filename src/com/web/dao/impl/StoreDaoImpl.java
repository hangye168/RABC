package com.web.dao.impl;

import org.apache.commons.lang.StringUtils;

import com.web.dao.StoreDao;
import com.web.model.Store;
import com.web.query.StoreQuery;

public class StoreDaoImpl extends BasedaoImpl<Store, StoreQuery> implements StoreDao {
	@Override
	public String createHql(StoreQuery q) {
		String hql = "from Store t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}
	
	@Override
	public String createHqlCount(StoreQuery q) {
		// TODO Auto-generated method stub
		String hql = "select count(storeId) from Store t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(StoreQuery q) {
		// TODO Auto-generated method stub
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(q.getAddress())){
			hql = hql + " and t.address like :address";
		}
		return hql;
	}
}
