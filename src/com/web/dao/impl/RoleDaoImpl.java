package com.web.dao.impl;

import org.apache.commons.lang.StringUtils;

import com.web.dao.RoleDao;
import com.web.model.Role;
import com.web.query.RoleQuery;

public class RoleDaoImpl extends BasedaoImpl<Role, RoleQuery> implements RoleDao {
	@Override
	public String createHql(RoleQuery q) {
		String hql = "from Role t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCount(RoleQuery q) {
		// TODO Auto-generated method stub
		String hql = "select count(roleId) from Role t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(RoleQuery q) {
		// TODO Auto-generated method stub
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(q.getCode())){
			hql = hql + " and t.code like :code";
		}
		return hql;
	}
}
