package com.web.service;

import java.util.List;

import com.web.model.Emp;
import com.web.model.Menu;
import com.web.query.MenuQuery;

public interface MenuService extends BaseService<Menu, MenuQuery>{
	public List<Menu> listMenu();
	
	public void updateMenu(Menu menu);
}
