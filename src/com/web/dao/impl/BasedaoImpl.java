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
	public T getObj(Integer id) {//��ȡ���;������
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
			public List<T> doInHibernate(Session session)//����session���鷳
					throws HibernateException {
				//���ô���hql
				String hql = createHql(q);
				//������ѯ����
				Query query = session.createQuery(hql);
				setDynamicParam(query, q,exclude);
				//��ò�ѯ����������
				Class<? extends Object> class1 = q.getClass();
				Object object = null;
				try {
					//���startNum
					Field startNumField = class1.getDeclaredField("startNum");
					startNumField.setAccessible(true);
					//���˽������ֵ
					object = startNumField.get(q);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//���ò�ѯ�Ŀ�ʼλ�ú�limit  ����ÿҳ��ʾ���ݵ���Ŀ
				List<T> list = (List<T>)query.setFirstResult((Integer)object).setMaxResults(5).list();
				return list;
			}
		});
		return tList;
	}
	//��ѯ���ݵ�����   Ϊ��ҳ��׼��
	public Long queryObjByConditionCount(final Q q,final List<String> exclude){
		@SuppressWarnings("unchecked")
		Long totalCount = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session)
					throws HibernateException {
				//���ô���hql
				String hql = createHqlCount(q);
				//������ѯ����
				Query query = session.createQuery(hql);
				setDynamicParam(query, q,exclude);
				Long count = (Long) query.uniqueResult();
				return count;
			}
		});
		return totalCount;
	}
	
	//��ȡ���͵ľ�����
	public Class getGenericClass(){
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		//�ѷ��͵ĸ�����ǿ��ת��ParameterizedType
		ParameterizedType pt = (ParameterizedType) genericSuperclass;
		//����ParameterizedType��õ�ǰ������з��͵�����
		Type[] actualTypeArguments = pt.getActualTypeArguments();
		//���T�ľ�����
		Class<?> class1 = (Class<?>)actualTypeArguments[0];
		return class1;
	}
	
	/**
	 * ����hql
	 */
	public abstract String createHql(Q q);
	
	/**
	 * ������ѯhql  ��ѯ��������
	 * @param q
	 * @return
	 */
	public abstract String createHqlCount(Q q);
	
	/**
	 * ������ѯ����
	 * @param q
	 * @return
	 */
	public abstract String createHqlCondition(Q q);
	
	/**
	 * ���ö�̬����
	 * exclude�ų�ĳЩ����   pageNo��startNum
	 */
	public void setDynamicParam(Query query, Q q,List<String> exclude){
		//��ò�ѯ����������
		Class<? extends Object> class1 = q.getClass();
		//���������ѯ�����г���ѯ�������������
		Field[] fields = class1.getDeclaredFields();//��ȡ���������������ֶ� ����˽�С����кͱ�����
		//��ȡ�丸�����
		Field[] superFields = class1.getSuperclass().getDeclaredFields();
		List<Field> list1 = Arrays.asList(fields);
		List<Field> list2 = Arrays.asList(superFields);
		//����һ����ļ��ϣ�����洢��ѯ��������Զ��������������Զ���
		List<Field> fList = new ArrayList<Field>();
		fList.addAll(list1);
		fList.addAll(list2);
		
		//��������
		for(Field f : fList){
			//������Ե�����
			String fieldName = f.getName();
			if(exclude != null && exclude.contains(fieldName)){
				continue;
			}
			Object val = null;
			try {
				//��������Դ����л�ȡ˽�з���
				f.setAccessible(true);
				//������Ե�ֵ
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
					//�ڲ�ѯ���������ò���
					query.setParameter(fieldName, val);
				}
			}
		}
	}
	/**
	 * ��ѯ���б�
	 * @return
	 */
	public List<T> list(){
		Class<?> class1 = getGenericClass();
		String hql = "from " + class1.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}
	
	//�޷�ҳ��ѯ��������
	public List<T> queryObjByConditionNoPage(final Q q, final List<String> exclude ){
		@SuppressWarnings("unchecked")
		List<T> tList = this.getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

			/**
			 * Session��spring�����Ĵ���session�������Զ��Ŀ������ύ����͹ر�session
			 */
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException {
				String hql = createHql(q);
				//������ѯ����
				Query query = session.createQuery(hql);
				setDynamicParam(query, q, exclude);
				//��ò�ѯ����������
				List<T> list = (List<T>)query.list();	
				return list;
			}
		});
		return tList;
	}
}
