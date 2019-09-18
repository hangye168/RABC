package com.web.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.web.dao.BaseDao;

public abstract class BasedaoImpl<T,Q> extends HibernateDaoSupport implements BaseDao<T, Q>{

	
	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(t);
	}


	@Override
	public T getObj(Integer id) {//获取泛型具体的类
		// TODO Auto-generated method stub
		Class<?> class1 = getGenericClass();
		return (T)this.getHibernateTemplate().get(class1, id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		T obj = getObj(id);
		this.getHibernateTemplate().delete(obj);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public List<T> queryObjByCondition(final Q q,final List<String> exclude) {
		@SuppressWarnings("unchecked")
		List<T> tList = this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session)//避免session的麻烦
					throws HibernateException {
				//调用创建hql
				String hql = createHql(q);
				//创建查询对象
				Query query = session.createQuery(hql);
				setDynamicParam(query, q,exclude);
				//获得查询对象的类对象
				Class<? extends Object> class1 = q.getClass();
				Object object = null;
				try {
					//获得startNum
					Field startNumField = class1.getDeclaredField("startNum");
					startNumField.setAccessible(true);
					//获得私有属性值
					object = startNumField.get(q);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//设置查询的开始位置和limit  设置每页显示数据的数目
				List<T> list = (List<T>)query.setFirstResult((Integer)object).setMaxResults(5).list();
				return list;
			}
		});
		return tList;
	}
	//查询数据的总数   为分页做准备
	public Long queryObjByConditionCount(final Q q,final List<String> exclude){
		@SuppressWarnings("unchecked")
		Long totalCount = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session)
					throws HibernateException {
				//调用创建hql
				String hql = createHqlCount(q);
				//创建查询对象
				Query query = session.createQuery(hql);
				setDynamicParam(query, q,exclude);
				Long count = (Long) query.uniqueResult();
				return count;
			}
		});
		return totalCount;
	}
	
	//获取泛型的具体类
	public Class getGenericClass(){
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		//把泛型的父类做强制转换ParameterizedType
		ParameterizedType pt = (ParameterizedType) genericSuperclass;
		//根据ParameterizedType获得当前类的所有泛型的类型
		Type[] actualTypeArguments = pt.getActualTypeArguments();
		//获得T的具体类
		Class<?> class1 = (Class<?>)actualTypeArguments[0];
		return class1;
	}
	
	/**
	 * 创建hql
	 */
	public abstract String createHql(Q q);
	
	/**
	 * 创建查询hql  查询数据总数
	 * @param q
	 * @return
	 */
	public abstract String createHqlCount(Q q);
	
	/**
	 * 创建查询条件
	 * @param q
	 * @return
	 */
	public abstract String createHqlCondition(Q q);
	
	/**
	 * 设置动态参数
	 * exclude排除某些参数   pageNo、startNum
	 */
	public void setDynamicParam(Query query, Q q,List<String> exclude){
		//获得查询对象的类对象
		Class<? extends Object> class1 = q.getClass();
		//反向解析查询对象，列出查询对象的所有属性
		Field[] fields = class1.getDeclaredFields();//获取类中所有声明的字段 包括私有、共有和保护类
		//获取其父类对象
		Field[] superFields = class1.getSuperclass().getDeclaredFields();
		List<Field> list1 = Arrays.asList(fields);
		List<Field> list2 = Arrays.asList(superFields);
		//创建一个大的集合，里面存储查询对象的属性对象和他父类的属性对象
		List<Field> fList = new ArrayList<Field>();
		fList.addAll(list1);
		fList.addAll(list2);
		
		//遍历集合
		for(Field f : fList){
			//获得属性的名字
			String fieldName = f.getName();
			if(exclude != null && exclude.contains(fieldName)){
				continue;
			}
			Object val = null;
			try {
				//开启后可以从类中获取私有方法
				f.setAccessible(true);
				//获得属性的值
				val = f.get(q);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			if(val != null){
				if(val.getClass() == String.class){
					if(StringUtils.isNotBlank(val.toString())) {
						query.setParameter(fieldName, "%"+val+"%");
					}
					
				}else{
					//在查询对象中设置参数
					query.setParameter(fieldName, val);
				}
			}
		}
	}
	/**
	 * 查询类列表
	 * @return
	 */
	public List<T> list(){
		Class<?> class1 = getGenericClass();
		String hql = "from " + class1.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}
	
	//无分页查询所有数据
	public List<T> queryObjByConditionNoPage(final Q q, final List<String> exclude ){
		@SuppressWarnings("unchecked")
		List<T> tList = this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

			/**
			 * Session是spring开启的代理session，可以自动的开事务，提交事务和关闭session
			 */
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException {
				String hql = createHql(q);
				//创建查询对象
				Query query = session.createQuery(hql);
				setDynamicParam(query, q, exclude);
				//获得查询对象的类对象
				List<T> list = (List<T>)query.list();	
				return list;
			}
		});
		return tList;
	}
}
