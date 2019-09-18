package com.web.controller;
/**
 * 仓库数据的处理
 */
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.web.model.Emp;
import com.web.model.Store;
import com.web.model.StoreDetail;
import com.web.query.StoreQuery;
import com.web.service.EmpService;
import com.web.service.StoreService;
import com.web.utils.Page;

public class StoreAction extends BaseAction {
	
	private StoreQuery query = new StoreQuery();
	
	private StoreService storeService;
	
	private EmpService empService;
	
	private Store store = new Store();
	
	//ajax提交入库信息时的明细
	private Integer productNum;//商品数量
	private Integer productId;//商品ID
	private Integer orderDetailId;//存在目的：记录存入商品的数目，如果存满，将不再入库
	
	/**
	 * 方法实现区---------------------------------------------------
	 * @return
	 */
	//仓库货物明细
	public String store_slist(){	
		ActionContext context = ActionContext.getContext();	
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}	
		Page page = storeService.queryObjByCondition(query, exclude);	
		context.put("page", page);
		return SUCCESS;
	}
	
	public String store_input(){
		List<Emp> list = empService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		return SUCCESS;
	}
	
	//基础维护中，仓库管理跳转
	public String store_store(){
		ActionContext context = ActionContext.getContext();	
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}	
		Page page = storeService.queryObjByCondition(query, exclude);	
		context.put("page", page);
		return SUCCESS;
	}
	
	//查看获取明细
	public String store_storeDetail(){
		Store store = storeService.getObj(query.getStoreId());
		Set<StoreDetail> details = store.getDetails();
		ActionContext context = ActionContext.getContext();
		context.put("details", details);
		return SUCCESS;
	}
	//商品入库操作
	public String store_inStock(){
		List<Store> list = storeService.list();
		ActionContext context = ActionContext.getContext();
		context.put("sLsit", list);
		return SUCCESS;
	}
	
	//修改仓库信息
	public String store_update(){
		store = storeService.getObj(store.getStoreId());
		List<Emp> list = empService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		return SUCCESS;
	}	
	
	//删除仓库信息
	public String store_delete(){
		storeService.delete(store.getStoreId());
		return LIST;
	}
	
	/**
	 * ajax方法区-----------------------------------------------------------------------
	 * @throws IOException
	 */
	//入库信息提交
	public void ajax_store_inStock() throws IOException{
		
		try {
			storeService.updateInstock(query.getStoreId(), productId, productNum, orderDetailId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write("success");	
	}
	
	//ajax添加仓库信息
	public void ajax_store_add() throws IOException{
		storeService.save(store);
		response.getWriter().write("success");
	}
	
	//ajax修改仓库信息
	public void ajax_store_update() throws IOException{
		storeService.updateStore(store);
		response.getWriter().write("success");
	}
	/**
	 * 所有的get和set方法
	 * -----------------------------------------------------------------
	 * @return
	 */
	public StoreQuery getQuery() {
		return query;
	}

	public void setQuery(StoreQuery query) {
		this.query = query;
	}
	
	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public EmpService getEmpService() {
		return empService;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
}
