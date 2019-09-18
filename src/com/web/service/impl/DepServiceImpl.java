package com.web.service.impl;
/**
 * 部门信息服务实现类
 */
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.web.dao.Depdao;
import com.web.model.Dep;
import com.web.model.Emp;
import com.web.query.DepQuery;
import com.web.service.DepService;
import com.web.utils.MD5Utils;

public class DepServiceImpl extends BaseServiceImpl<Dep, DepQuery> implements DepService{

	
	//注入dao
	private Depdao depdao;
	
	public void setDepdao(Depdao depdao) {
		this.depdao = depdao;
		//当使用公用的dao的时候必须要一个具体的dao赋值给baseDao
		this.baseDao = depdao;
	}

	@Override
	public void updateDep(Dep dep) {
		// TODO Auto-generated method stub
		Dep dep1 = depdao.getObj(dep.getDepId());
		try {
			//把emp中的所有属性拷贝到emp1中  两者主键相同，会报错
			BeanUtils.copyProperties(dep1, dep);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		depdao.update(dep1);
	}
}
