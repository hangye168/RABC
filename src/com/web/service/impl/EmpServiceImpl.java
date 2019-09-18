package com.web.service.impl;
/**
 * Ա����Ϣ����ʵ����
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

	//ע��dao
	private Empdao empdao;

	private RoleDao roleDao;
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setEmpdao(Empdao empdao) {
		this.empdao = empdao;
		this.baseDao = empdao;
	}

	//����EmpDao�еķ��� ��ѯ�û����Ƿ����
	@Override
	public Emp getEmpByUname(String username) {
		// TODO Auto-generated method stub
		return empdao.getEmpByUname(username);
	}

	//�޸�emp�е�����
	@Override
	public void updateEmp(Emp emp) {
		// TODO Auto-generated method stub
		Emp emp1 = empdao.getObj(emp.getEmpId());
		emp.setPassword(MD5Utils.md5(emp.getPassword()));
		try {
			//��emp�е��������Կ�����emp1��  ����������ͬ���ᱨ��
			BeanUtils.copyProperties(emp1, emp);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		empdao.update(emp1);
	}

	//����Ա��id��ѯ��ɫ��Ϣ
	@Override
	public List<Role> getStateRoles(int empId) {
		// TODO Auto-generated method stub
		//�����н�ɫ
		List<Role> list = roleDao.list();
		//��ȡ��Ӧ��emp���ݣ�����Ϊ����
		Emp emp = empdao.getObj(empId);
		//��ȡemp��Ӧ�Ľ�ɫ  �ӱ�relation_emp_role��ȥȡ
		Set<Role> roles = emp.getRoles();
		for(Role r:list){
			for(Role er:roles){
				//תΪInt����
				if(r.getRoleId().intValue() == er.getRoleId().intValue()){
					r.setSelect("yes");
				}
			}
		}
		return list;
	}

	//�޸��û��Ľ�ɫ
	@Override
	public void updateEmpRole(Integer empId, String roleIds) {
		// TODO Auto-generated method stub
		//����û�
		Emp emp = empdao.getObj(empId);
		Set<Role> roles = emp.getRoles();
		//��ԭ�еĽ�ɫ���
		roles.clear();
		if(StringUtils.isNotBlank(roleIds)){
			String[] ids = roleIds.split(",");
			for(String roleId : ids){
				//����id���Ҫ����role����
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
