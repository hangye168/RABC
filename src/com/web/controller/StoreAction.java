package com.web.controller;
/**
 * �ֿ����ݵĴ���
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
	
	//ajax�ύ�����Ϣʱ����ϸ
	private Integer productNum;//��Ʒ����
	private Integer productId;//��ƷID
	private Integer orderDetailId;//����Ŀ�ģ���¼������Ʒ����Ŀ��������������������
	
	/**
	 * ����ʵ����---------------------------------------------------
	 * @return
	 */
	//�ֿ������ϸ
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
	
	//����ά���У��ֿ������ת
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
	
	//�鿴��ȡ��ϸ
	public String store_storeDetail(){
		Store store = storeService.getObj(query.getStoreId());
		Set<StoreDetail> details = store.getDetails();
		ActionContext context = ActionContext.getContext();
		context.put("details", details);
		return SUCCESS;
	}
	//��Ʒ������
	public String store_inStock(){
		List<Store> list = storeService.list();
		ActionContext context = ActionContext.getContext();
		context.put("sLsit", list);
		return SUCCESS;
	}
	
	//�޸Ĳֿ���Ϣ
	public String store_update(){
		store = storeService.getObj(store.getStoreId());
		List<Emp> list = empService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		return SUCCESS;
	}	
	
	//ɾ���ֿ���Ϣ
	public String store_delete(){
		storeService.delete(store.getStoreId());
		return LIST;
	}
	
	/**
	 * ajax������-----------------------------------------------------------------------
	 * @throws IOException
	 */
	//�����Ϣ�ύ
	public void ajax_store_inStock() throws IOException{
		
		try {
			storeService.updateInstock(query.getStoreId(), productId, productNum, orderDetailId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write("success");	
	}
	
	//ajax��Ӳֿ���Ϣ
	public void ajax_store_add() throws IOException{
		storeService.save(store);
		response.getWriter().write("success");
	}
	
	//ajax�޸Ĳֿ���Ϣ
	public void ajax_store_update() throws IOException{
		storeService.updateStore(store);
		response.getWriter().write("success");
	}
	/**
	 * ���е�get��set����
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
