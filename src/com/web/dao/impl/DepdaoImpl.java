package com.web.dao.impl;
/**
 * 部门实现类
 */
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.web.dao.Depdao;
import com.web.model.Dep;
import com.web.model.Emp;
import com.web.query.DepQuery;

public class DepdaoImpl extends BasedaoImpl<Dep, DepQuery> implements Depdao {
	
	@Override
	public String createHql(DepQuery q) {
		String hql = "from Dep t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCount(DepQuery q) {
		// TODO Auto-generated method stub
		String hql = "select count(depId) from Dep t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(DepQuery q) {
		// TODO Auto-generated method stub
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(q.getTel())){
			hql = hql + " and t.tel like :tel";
		}
		return hql;
	}
}
