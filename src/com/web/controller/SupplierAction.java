package com.web.controller;
/**
 * 供应商信息
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
	 * ajax方法区-------------------------------------------------------
	 */
	//添加供应商
	public void ajax_supplier_add() throws IOException{
		supplierService.save(supplier);
		response.getWriter().write("success");
	}
	
	//修改供应商
	public void ajax_supplier_update() throws IOException{
		supplierService.updateSupplier(supplier);
		response.getWriter().write("success");
	}
	
	//查询供应商信息
	public void ajax_supplier_getSupplier(){
		//根据Id查询供应商
		Supplier supplier = supplierService.getObj(query.getSupplierId());
		//获得所有供应商下的类别
		Set<ProductType> pts = supplier.getPts();
		JSONUtils.printJSONArray(response, pts, new String[]{"supplier","products"});
	}
	/**
	 * 所有的get和set方法
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
