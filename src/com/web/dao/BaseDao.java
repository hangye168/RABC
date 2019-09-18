package com.web.dao;
/**
 * 基础dao实现增删改查  和查全部的数据
 */
import java.util.List;

public interface BaseDao<T,Q> {
	
	public void save(T t);
	
	public void update(T t);
	
	public T getObj(Integer id);
	
	public void delete(Integer id);
	
	public void delete(T t);
	
	/*
	 * 查数据的 每一页记录
	 */
	public List<T> queryObjByCondition(final Q q, final List<String> exclude);
	/**
	 * 查询指定条件下的总的数据数
	 * @return
	 */
	public Long queryObjByConditionCount(Q q,List<String> exclude);
	
	public List<T> list();
	
	public List<T> queryObjByConditionNoPage(final Q q, final List<String> exclude );
}
