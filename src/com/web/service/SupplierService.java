package com.web.service;

import com.web.model.Emp;
import com.web.model.Supplier;
import com.web.query.SupplierQuery;

public interface SupplierService extends BaseService<Supplier, SupplierQuery>{
	public void updateSupplier(Supplier supplier);
}
