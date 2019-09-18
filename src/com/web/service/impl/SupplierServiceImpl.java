package com.web.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.web.dao.SupplierDao;
import com.web.model.Emp;
import com.web.model.Supplier;
import com.web.query.SupplierQuery;
import com.web.service.SupplierService;
import com.web.utils.MD5Utils;

public class SupplierServiceImpl extends BaseServiceImpl<Supplier, SupplierQuery> implements SupplierService {

	private SupplierDao supplierDao;
	
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
		this.baseDao = supplierDao;
	}

	@Override
	public void updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		Supplier supplier1 = supplierDao.getObj(supplier.getSupplierId());
		try {
			//把supplier中的所有属性拷贝到supplier1中  两者主键相同，会报错
			BeanUtils.copyProperties(supplier1, supplier);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		supplierDao.update(supplier1);
	}
	
	

}
