package com.web.controller;
/**
 * �Բ��ŵĲ���
 */
import java.io.IOException;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.web.model.Dep;
import com.web.query.DepQuery;
import com.web.service.DepService;
import com.web.utils.Page;

public class DepAction extends BaseAction{
	
	//���ܲ�ѯ����
	private DepQuery query = new DepQuery();
	//ע�벿����Ϣ��service
	private DepService depService;
	//����ǰ�˷�������
	private Dep dep = new Dep();
	
	//�����û���ѯ
	public String dep_list(){
		//��ѯ������Ϣ
		List<Dep> list = depService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}
		
		//��ѯ��������
		Page page = depService.queryObjByCondition(query,exclude);
		context.put("page", page);
		return SUCCESS;
	}
	
	//ɾ��������Ϣ
	public String dep_delete(){
		depService.delete(dep.getDepId());			
		return LIST;
	}
	
	//�����޸�ʱ����depId  ��ѯ������Ϣ
	public String dep_update(){
		dep = depService.getObj(dep.getDepId());
		return SUCCESS;
	}
	
	//ajax������-----------------------------------
	//��Ӳ�����Ϣ
	public void ajax_dep_add() throws IOException{
		depService.save(dep);
		response.getWriter().write("success");
	}
	
	//ajax�޸��û���Ϣ
	public void ajax_dep_update() throws IOException{
		depService.updateDep(dep);
		response.getWriter().write("success");
	}
	
	//�����û���Ϣ����д
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
