package com.web.dao;

import java.util.List;

import com.web.model.Menu;
import com.web.query.MenuQuery;

public interface MenuDao extends BaseDao<Menu, MenuQuery>{
	public List<Menu> listmenu();
}
