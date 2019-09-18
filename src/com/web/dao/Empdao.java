package com.web.dao;

import com.web.model.Emp;
import com.web.query.EmpQuery;

/**
 * dao层  用户和数据库交互
 * @author sh
 *
 */
public interface Empdao extends BaseDao<Emp, EmpQuery>{
	
	//查询用户名的接口
	public Emp getEmpByUname(String username);
	//验证用户名和密码接口
	public Emp getEmpByUnameAndPWord(String username, String password);
}
