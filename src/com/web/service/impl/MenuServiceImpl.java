package com.web.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.web.dao.MenuDao;
import com.web.model.Emp;
import com.web.model.Menu;
import com.web.query.MenuQuery;
import com.web.service.MenuService;
import com.web.utils.MD5Utils;

public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuQuery> implements MenuService {

	private MenuDao menuDao;
	
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
		this.baseDao = menuDao;
	}

	//查询所有的2级菜单
	@Override
	public List<Menu> listMenu() {
		// TODO Auto-generated method stub
		return menuDao.listmenu();
	}

	@Override
	public void updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		Menu menu1 = menuDao.getObj(menu.getMenuId());
		try {
			//把emp中的所有属性拷贝到emp1中  两者主键相同，会报错
			BeanUtils.copyProperties(menu1, menu);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		menuDao.update(menu1);
	}
	
	

}
