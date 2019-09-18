package com.web.service.impl;
/**
 * 员工信息服务实现类
 */
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.web.dao.Empdao;
import com.web.dao.RoleDao;
import com.web.model.Emp;
import com.web.model.Role;
import com.web.query.EmpQuery;
import com.web.service.EmpService;
import com.web.utils.MD5Utils;

public class EmpServiceImpl extends BaseServiceImpl<Emp, EmpQuery> implements EmpService{

	//注入dao
	private Empdao empdao;

	private RoleDao roleDao;
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setEmpdao(Empdao empdao) {
		this.empdao = empdao;
		this.baseDao = empdao;
	}

	//调用EmpDao中的方法 查询用户名是否存在
	@Override
	public Emp getEmpByUname(String username) {
		// TODO Auto-generated method stub
		return empdao.getEmpByUname(username);
	}

	//修改emp中的数据
	@Override
	public void updateEmp(Emp emp) {
		// TODO Auto-generated method stub
		Emp emp1 = empdao.getObj(emp.getEmpId());
		emp.setPassword(MD5Utils.md5(emp.getPassword()));
		try {
			//把emp中的所有属性拷贝到emp1中  两者主键相同，会报错
			BeanUtils.copyProperties(emp1, emp);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		empdao.update(emp1);
	}

	//根据员工id查询角色信息
	@Override
	public List<Role> getStateRoles(int empId) {
		// TODO Auto-generated method stub
		//查所有角色
		List<Role> list = roleDao.list();
		//获取对应的emp数据，保存为对象
		Emp emp = empdao.getObj(empId);
		//获取emp对应的角色  从表relation_emp_role中去取
		Set<Role> roles = emp.getRoles();
		for(Role r:list){
			for(Role er:roles){
				//转为Int类型
				if(r.getRoleId().intValue() == er.getRoleId().intValue()){
					r.setSelect("yes");
				}
			}
		}
		return list;
	}

	//修改用户的角色
	@Override
	public void updateEmpRole(Integer empId, String roleIds) {
		// TODO Auto-generated method stub
		//获得用户
		Emp emp = empdao.getObj(empId);
		Set<Role> roles = emp.getRoles();
		//把原有的角色清掉
		roles.clear();
		if(StringUtils.isNotBlank(roleIds)){
			String[] ids = roleIds.split(",");
			for(String roleId : ids){
				//根据id获得要分配role对象
				Role role = roleDao.getObj(new Integer(roleId));
				roles.add(role);
			}
		}
	}

	@Override
	public Emp getEmpByUnameAndPWord(String username, String password) {
		// TODO Auto-generated method stub
		return empdao.getEmpByUnameAndPWord(username, password);
	}
}
