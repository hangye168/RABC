package com.web.dao;

import com.web.model.ProductType;
import com.web.query.ProductTypeQuery;

public interface ProductTypeDao extends BaseDao<ProductType, ProductTypeQuery>{
	
	public ProductType getProductTypeBySName(ProductType pt);
}
