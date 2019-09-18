package com.web.service.impl;
/**
 * ������Ϣ����ʵ����
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

	
	//ע��dao
	private Depdao depdao;
	
	public void setDepdao(Depdao depdao) {
		this.depdao = depdao;
		//��ʹ�ù��õ�dao��ʱ�����Ҫһ�������dao��ֵ��baseDao
		this.baseDao = depdao;
	}

	@Override
	public void updateDep(Dep dep) {
		// TODO Auto-generated method stub
		Dep dep1 = depdao.getObj(dep.getDepId());
		try {
			//��emp�е��������Կ�����emp1��  ����������ͬ���ᱨ��
			BeanUtils.copyProperties(dep1, dep);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		depdao.update(dep1);
	}
}
