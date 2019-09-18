package com.web.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.web.dao.MenuDao;
import com.web.dao.RoleDao;
import com.web.model.Emp;
import com.web.model.Menu;
import com.web.model.Role;
import com.web.query.RoleQuery;
import com.web.service.RoleService;
import com.web.utils.MD5Utils;

public class RoleServiceImpl extends BaseServiceImpl<Role, RoleQuery> implements RoleService {

	private RoleDao roleDao;
	
	private MenuDao menuDao;
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		this.baseDao = roleDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	//����Ȩ��
	@Override
	public void updateGrantPerm(Integer roleId, String permIds) {
		// TODO Auto-generated method stub
		Role role = roleDao.getObj(roleId);
		Set<Menu> menus = role.getMenus();
		//�����е�Ȩ�����
		menus.clear();
		if(StringUtils.isNotBlank(permIds)){
			//ǰ����װ����ʱ��ʹ�ö��Ÿ���
			String[] menuIds = permIds.split(",");
			for(String menuId : menuIds){
				Menu menu = menuDao.getObj(new Integer(menuId));
				menus.add(menu);
			}
		}
	}

	//�޸Ľ�ɫ
	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		Role role1 = roleDao.getObj(role.getRoleId());
		try {
			BeanUtils.copyProperties(role1, role);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		roleDao.update(role1);
	}
	
	

}
