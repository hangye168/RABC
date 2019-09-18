package com.web.service;

import com.web.model.ProductType;
import com.web.query.ProductTypeQuery;

public interface ProductTypeService extends BaseService<ProductType, ProductTypeQuery>{
	
	public ProductType getProductTypeBySName(ProductType pt);
}
