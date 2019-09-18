package com.web.controller;
/**
 * 仓库详情
 */
import com.web.query.StoreDetailQuery;
import com.web.service.StoreDetailService;
import com.web.utils.Page;

import com.opensymphony.xwork2.ActionContext;

public class StoreDetailAction extends BaseAction {
	
	private StoreDetailQuery query = new StoreDetailQuery();
	
	private StoreDetailService storeDetailService;
	
	public String storeDetail_list(){	
		ActionContext context = ActionContext.getContext();	
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}	
		Page page = storeDetailService.queryObjByCondition(query, exclude);	
		context.put("page", page);
		return SUCCESS;
	}
	
	public String storeDetail_input(){
		return SUCCESS;
	}
	
	/**
	 * 所有的get和set方法
	 * -----------------------------------------------------------------
	 * @return
	 */
	public StoreDetailQuery getQuery() {
		return query;
	}

	public void setQuery(StoreDetailQuery query) {
		this.query = query;
	}
	
	public StoreDetailService getStoreDetailService() {
		return storeDetailService;
	}

	public void setStoreDetailService(StoreDetailService storeDetailService) {
		this.storeDetailService = storeDetailService;
	}
	
}
