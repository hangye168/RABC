package com.web.dao.impl;

import org.apache.commons.lang.StringUtils;

import com.web.dao.ProductDao;
import com.web.model.Product;
import com.web.query.ProductQuery;

public class ProductDaoImpl extends BasedaoImpl<Product, ProductQuery> implements ProductDao {
	@Override
	public String createHql(ProductQuery q) {
		String hql = "from Product t where 1=1 ";
		hql = hql + createHqlCondition(q) + " order by t.productId desc";
		return hql;
	}
	
	@Override
	public String createHqlCount(ProductQuery q) {
		// TODO Auto-generated method stub
		String hql = "select count(t.productId) from Product t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(ProductQuery q) {
		// TODO Auto-generated method stub
		String hql = "";
		if(q.getSupplierId() != null){
			hql = hql + " and t.productType.supplier.supplierId = :supplierId";
		}
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(StringUtils.isNotBlank(q.getProducer())){
			hql = hql + " and t.producer like :producer";
		}
		if(StringUtils.isNotBlank(q.getUnit())){
			hql = hql + " and t.unit like :unit";
		}	
		if(q.getMinInPrice() != null){
			hql = hql + " and t.inPrice >= :minInPrice";
		}	
		if(q.getMaxInPrice() != null){
			hql = hql + " and t.inPrice <= :maxInPrice";
		}
		if(q.getMinOutPrice() != null){
			hql = hql + " and t.outPrice >= :minOutPrice";
		}
		if(q.getMaxOutPrice() != null){
			hql = hql + " and t.outPrice <= :maxOutPrice";
		}
		return hql;
	}
}
