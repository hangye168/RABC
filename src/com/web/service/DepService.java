package com.web.service;
/**
 * 部门信息服务接口
 */
import com.web.model.Dep;
import com.web.model.Emp;
import com.web.query.DepQuery;

/**
 * service层  Dep
 * @author sh
 *
 */
public interface DepService extends BaseService<Dep, DepQuery>{
	public void updateDep(Dep dep);
}
