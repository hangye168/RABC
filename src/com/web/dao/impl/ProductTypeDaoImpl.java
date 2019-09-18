package com.web.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.web.dao.ProductTypeDao;
import com.web.model.ProductType;
import com.web.query.ProductTypeQuery;

public class ProductTypeDaoImpl extends BasedaoImpl<ProductType, ProductTypeQuery> implements ProductTypeDao {
	@Override
	public String createHql(ProductTypeQuery q) {
		String hql = "from ProductType t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}
	
	@Override
	public String createHqlCount(ProductTypeQuery q) {
		// TODO Auto-generated method stub
		String hql = "select count(t.productTypeId) from ProductType t where 1=1 ";
		hql = hql + createHqlCondition(q);
		return hql;
	}

	@Override
	public String createHqlCondition(ProductTypeQuery q) {
		// TODO Auto-generated method stub
		String hql = "";
		if(StringUtils.isNotBlank(q.getName())){
			hql = hql + " and t.name like :name";
		}
		if(q.getSupplierId() != null){
			hql = hql + " and t.supplier.supplierId = :supplierId";
		}
		return hql;
	}

	//查供应商中重复类别
	@Override
	public ProductType getProductTypeBySName(final ProductType pt) {
		// TODO Auto-generated method stub
		final String hql = "from ProductType t where t.supplier.supplierId = :supplierId and t.name = :name";
		ProductType productType = this.getHibernateTemplate().execute(new HibernateCallback<ProductType>() {

			@Override
			public ProductType doInHibernate(Session session)
					throws HibernateException {
				// TODO Auto-generated method stub
				Query query = session.createQuery(hql);
				query.setParameter("supplierId", pt.getSupplierId());
				query.setParameter("name", pt.getName());
				return (ProductType) query.uniqueResult();
			}
			
		});
		return productType;
	}
}
