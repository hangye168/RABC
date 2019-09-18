package com.web.dao.impl;

import org.apache.commons.lang.StringUtils;

import com.web.dao.SupplierDao;
import com.web.model.Supplier;
import com.web.query.SupplierQuery;

public class SupplierDaoImpl extends BasedaoImpl<Supplier, SupplierQuery> implements SupplierDao {
	@Override
	public String createHql(SupplierQuery q) {
		String hql = "from Supplier t where 1=1 ";
		hql = hql + createHqlCondition(q) + " order by t.supplierId desc";
		return hql;
	}
	
	@Override
	public String createHqlCount(SupplierQuery q) {
		// TODO Auto-generated method stub
		String hql = "select count(supplierId) from Supplier t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(SupplierQuery q) {
		// TODO Auto-generated method stub
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(q.getAddress())){
			hql = hql + " and t.address like :address";
		}
		if(StringUtils.isNotBlank(q.getContact())){
			hql = hql + " and t.contact like :contact";
		}
		if(StringUtils.isNotBlank(q.getTel())){
			hql = hql + " and t.tel like :tel";
		}
		if(q.getNeeds() != null){
			hql = hql + " and t.needs like :needs";
		}
		return hql;
	}
}
