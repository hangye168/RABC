package com.web.service.impl;
/**
 * ��������Ĺ���ʵ����
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
		//����page����
		Page page = new Page();
		//��ò�ѯ�����е�pageNo
		Class<? extends Object> class1 = q.getClass();
		try {
			//���pageNo
			Field field = class1.getDeclaredField("pageNo");
			field.setAccessible(true);
			Integer pageNo = (Integer) field.get(q);
			//��pageNo���ø�page����
			page.setPageNo(pageNo);
			//���㿪ʼ�к�
			int startNum = page.getStartNum();
			//��q��ѯ��������startNum
			Field startNumField = class1.getDeclaredField("startNum");
			//���pageNo
			startNumField.setAccessible(true);
			//����˽������ֵ
			startNumField.set(q, startNum);
			//��ѯ�����
			List<T> list = baseDao.queryObjByCondition(q, exclude);
			//�ѽ�������ø�page����
			page.setList(list);
			//��ѯ��ǰ�����µ���������
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
