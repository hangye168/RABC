package com.web.service;
/**
 * ������Ϣ����ӿ�
 */
import com.web.model.Dep;
import com.web.model.Emp;
import com.web.query.DepQuery;

/**
 * service��  Dep
 * @author sh
 *
 */
public interface DepService extends BaseService<Dep, DepQuery>{
	public void updateDep(Dep dep);
}
