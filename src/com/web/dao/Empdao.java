package com.web.dao;

import com.web.model.Emp;
import com.web.query.EmpQuery;

/**
 * dao��  �û������ݿ⽻��
 * @author sh
 *
 */
public interface Empdao extends BaseDao<Emp, EmpQuery>{
	
	//��ѯ�û����Ľӿ�
	public Emp getEmpByUname(String username);
	//��֤�û���������ӿ�
	public Emp getEmpByUnameAndPWord(String username, String password);
}
