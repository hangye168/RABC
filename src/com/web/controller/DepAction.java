package com.web.controller;
/**
 * 对部门的操作
 */
import java.io.IOException;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.web.model.Dep;
import com.web.query.DepQuery;
import com.web.service.DepService;
import com.web.utils.Page;

public class DepAction extends BaseAction{
	
	//接受查询对象
	private DepQuery query = new DepQuery();
	//注入部门信息的service
	private DepService depService;
	//接收前端发的数据
	private Dep dep = new Dep();
	
	//用于用户查询
	public String dep_list(){
		//查询部门信息
		List<Dep> list = depService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}
		
		//查询部门数据
		Page page = depService.queryObjByCondition(query,exclude);
		context.put("page", page);
		return SUCCESS;
	}
	
	//删除部门信息
	public String dep_delete(){
		depService.delete(dep.getDepId());			
		return LIST;
	}
	
	//根据修改时传的depId  查询部门信息
	public String dep_update(){
		dep = depService.getObj(dep.getDepId());
		return SUCCESS;
	}
	
	//ajax方法区-----------------------------------
	//添加部门信息
	public void ajax_dep_add() throws IOException{
		depService.save(dep);
		response.getWriter().write("success");
	}
	
	//ajax修改用户信息
	public void ajax_dep_update() throws IOException{
		depService.updateDep(dep);
		response.getWriter().write("success");
	}
	
	//用于用户信息的填写
	public String dep_input(){
		return SUCCESS;
	}

	public DepQuery getQuery() {
		return query;
	}

	public void setQuery(DepQuery query) {
		this.query = query;
	}

	public DepService getDepService() {
		return depService;
	}

	public void setDepService(DepService depService) {
		this.depService = depService;
	}

	public Dep getDep() {
		return dep;
	}

	public void setDep(Dep dep) {
		this.dep = dep;
	}

}
