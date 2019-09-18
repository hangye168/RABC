package com.web.service;
/**
 * 员工信息服务
 */
import java.util.List;

import com.web.model.Emp;
import com.web.model.Role;
import com.web.query.EmpQuery;

/**
 * service层
 * @author sh
 *
 */
public interface EmpService extends BaseService<Emp, EmpQuery>{
	//为empServiceImpl提供接口  用户查询用户名
	public Emp getEmpByUname(String username);
	
	public void updateEmp(Emp emp);
	
	public List<Role> getStateRoles(int empId);
	
	public void updateEmpRole(Integer empId, String roleIds);
	
	public Emp getEmpByUnameAndPWord(String username, String password);
}
