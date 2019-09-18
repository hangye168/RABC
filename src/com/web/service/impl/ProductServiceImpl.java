package com.web.service.impl;

import com.web.dao.ProductDao;
import com.web.model.Product;
import com.web.query.ProductQuery;
import com.web.service.ProductService;

public class ProductServiceImpl extends BaseServiceImpl<Product, ProductQuery> implements ProductService {

	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		this.baseDao = productDao;
	}
	
	

}
