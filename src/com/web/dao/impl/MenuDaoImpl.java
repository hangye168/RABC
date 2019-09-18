package com.web.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.web.dao.MenuDao;
import com.web.model.Menu;
import com.web.query.MenuQuery;

public class MenuDaoImpl extends BasedaoImpl<Menu, MenuQuery> implements MenuDao {
	@Override
	public String createHql(MenuQuery q) {
		String hql = "from Menu t where 1=1 ";
		hql = hql + createHqlCondition(q) + " order by t.level asc";
		return hql;
	}
	
	@Override
	public String createHqlCount(MenuQuery q) {
		// TODO Auto-generated method stub
		String hql = "select count(menuId) from Menu t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(MenuQuery q) {
		// TODO Auto-generated method stub
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if (q.getParentMenuId() != null) {
			hql = hql + " and t.menus.menuId = :parentMenuId";
		}
		return hql;
	}

	//≤È—Ø≤Àµ•
	@Override
	public List<Menu> listmenu() {
		// TODO Auto-generated method stub
		Integer level = 3;
		final String hql = "from Menu e where e.level != ?";
		List list = this.getHibernateTemplate().find(hql,level);
		return list;
	}
}
