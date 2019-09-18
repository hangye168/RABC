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

	//��ѯ���е�2���˵�
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
			//��emp�е��������Կ�����emp1��  ����������ͬ���ᱨ��
			BeanUtils.copyProperties(menu1, menu);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		menuDao.update(menu1);
	}
	
	

}
