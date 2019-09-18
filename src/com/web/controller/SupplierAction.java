package com.web.controller;
/**
 * ��Ӧ����Ϣ
 */
import java.io.IOException;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.web.model.ProductType;
import com.web.model.Supplier;
import com.web.query.SupplierQuery;
import com.web.service.SupplierService;
import com.web.utils.JSONUtils;
import com.web.utils.Page;

public class SupplierAction extends BaseAction {
	
	private SupplierQuery query = new SupplierQuery();
	
	private SupplierService supplierService;
	
	private Supplier supplier = new Supplier();
	
	public String supplier_list(){	
		ActionContext context = ActionContext.getContext();	
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}	
		Page page = supplierService.queryObjByCondition(query, exclude);	
		context.put("page", page);
		return SUCCESS;
	}
	
	public String supplier_input(){
		return SUCCESS;
	}
	
	
	public String supplier_update(){
		supplier = supplierService.getObj(supplier.getSupplierId());
		return SUCCESS;
	}
	
	public String supplier_delete(){
		supplierService.delete(supplier.getSupplierId());
		return LIST;
	}
	
	/**
	 * ajax������-------------------------------------------------------
	 */
	//��ӹ�Ӧ��
	public void ajax_supplier_add() throws IOException{
		supplierService.save(supplier);
		response.getWriter().write("success");
	}
	
	//�޸Ĺ�Ӧ��
	public void ajax_supplier_update() throws IOException{
		supplierService.updateSupplier(supplier);
		response.getWriter().write("success");
	}
	
	//��ѯ��Ӧ����Ϣ
	public void ajax_supplier_getSupplier(){
		//����Id��ѯ��Ӧ��
		Supplier supplier = supplierService.getObj(query.getSupplierId());
		//������й�Ӧ���µ����
		Set<ProductType> pts = supplier.getPts();
		JSONUtils.printJSONArray(response, pts, new String[]{"supplier","products"});
	}
	/**
	 * ���е�get��set����
	 * -----------------------------------------------------------------
	 * @return
	 */
	public SupplierQuery getQuery() {
		return query;
	}

	public void setQuery(SupplierQuery query) {
		this.query = query;
	}
	
	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
}
