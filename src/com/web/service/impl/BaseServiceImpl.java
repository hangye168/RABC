package com.web.service.impl;
/**
 * 抽象出来的公共实现类
 */
import java.lang.reflect.Field;
import java.util.List;

import com.web.dao.BaseDao;
import com.web.service.BaseService;
import com.web.utils.Page;

public class BaseServiceImpl<T,Q> implements BaseService<T, Q>{

	BaseDao<T, Q> baseDao;
	
	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		baseDao.save(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		baseDao.update(t);
	}

	@Override
	public T getObj(Integer id) {
		// TODO Auto-generated method stub
		return (T)baseDao.getObj(id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		baseDao.delete(id);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		baseDao.delete(t);
	}

	@Override
	public List<T> list() {
		// TODO Auto-generated method stub
		return baseDao.list();
	}

	@Override
	public Page queryObjByCondition(Q q, List<String> exclude) {
		// TODO Auto-generated method stub
		//创建page对象
		Page page = new Page();
		//获得查询对象中的pageNo
		Class<? extends Object> class1 = q.getClass();
		try {
			//获得pageNo
			Field field = class1.getDeclaredField("pageNo");
			field.setAccessible(true);
			Integer pageNo = (Integer) field.get(q);
			//把pageNo设置给page对象
			page.setPageNo(pageNo);
			//计算开始行号
			int startNum = page.getStartNum();
			//给q查询对象设置startNum
			Field startNumField = class1.getDeclaredField("startNum");
			//获得pageNo
			startNumField.setAccessible(true);
			//设置私有属性值
			startNumField.set(q, startNum);
			//查询结果集
			List<T> list = baseDao.queryObjByCondition(q, exclude);
			//把结果集设置给page对象
			page.setList(list);
			//查询当前条件下的数据总数
			Long count = baseDao.queryObjByConditionCount(q, exclude);
			page.setTotalCount(new Integer(count+""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	
	public List<T> queryObjByConditionNoPage(final Q q, final List<String> exclude ){
		return baseDao.queryObjByConditionNoPage(q, exclude);
	}
}
