package com.web.dao;
/**
 * ����daoʵ����ɾ�Ĳ�  �Ͳ�ȫ��������
 */
import java.util.List;

public interface BaseDao<T,Q> {
	
	public void save(T t);
	
	public void update(T t);
	
	public T getObj(Integer id);
	
	public void delete(Integer id);
	
	public void delete(T t);
	
	/*
	 * �����ݵ� ÿһҳ��¼
	 */
	public List<T> queryObjByCondition(final Q q, final List<String> exclude);
	/**
	 * ��ѯָ�������µ��ܵ�������
	 * @return
	 */
	public Long queryObjByConditionCount(Q q,List<String> exclude);
	
	public List<T> list();
	
	public List<T> queryObjByConditionNoPage(final Q q, final List<String> exclude );
}
