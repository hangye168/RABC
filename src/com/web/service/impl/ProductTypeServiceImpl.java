package com.web.service.impl;

import com.web.dao.ProductTypeDao;
import com.web.model.ProductType;
import com.web.query.ProductTypeQuery;
import com.web.service.ProductTypeService;

public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, ProductTypeQuery> implements ProductTypeService {

	private ProductTypeDao productTypeDao;
	
	public void setProductTypeDao(ProductTypeDao productTypeDao) {
		this.productTypeDao = productTypeDao;
		this.baseDao = productTypeDao;
	}

	@Override
	public ProductType getProductTypeBySName(ProductType pt) {
		// TODO Auto-generated method stub
		return productTypeDao.getProductTypeBySName(pt);
	}
	
	

}
