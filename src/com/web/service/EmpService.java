package com.web.service;
/**
 * Ա����Ϣ����
 */
import java.util.List;

import com.web.model.Emp;
import com.web.model.Role;
import com.web.query.EmpQuery;

/**
 * service��
 * @author sh
 *
 */
public interface EmpService extends BaseService<Emp, EmpQuery>{
	//ΪempServiceImpl�ṩ�ӿ�  �û���ѯ�û���
	public Emp getEmpByUname(String username);
	
	public void updateEmp(Emp emp);
	
	public List<Role> getStateRoles(int empId);
	
	public void updateEmpRole(Integer empId, String roleIds);
	
	public Emp getEmpByUnameAndPWord(String username, String password);
}
