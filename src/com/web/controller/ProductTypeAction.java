package com.web.controller;
/**
 * 商品类别
 */
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.web.model.Product;
import com.web.model.ProductType;
import com.web.model.Supplier;
import com.web.query.ProductTypeQuery;
import com.web.service.ProductTypeService;
import com.web.service.SupplierService;
import com.web.utils.JSONUtils;
import com.web.utils.Page;

public class ProductTypeAction extends BaseAction {
	
	private ProductTypeQuery query = new ProductTypeQuery();
	
	private ProductTypeService productTypeService;
	
	private SupplierService supplierService;
	
	private ProductType productType;
	
	//分页
	public String productType_list(){	
		ActionContext context = ActionContext.getContext();	
		//查询所有的供应商
		List<Supplier> suppliers = supplierService.list();
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}	
		Page page = productTypeService.queryObjByCondition(query, exclude);	
		context.put("page", page);
		context.put("suppliers", suppliers);
		return SUCCESS;
	}
	
	public String productType_input(){
		ActionContext context = ActionContext.getContext();	
		//查询所有的供应商
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		return SUCCESS;
	}
	
	/**
	 * ajax方法区
	 * ----------------------------------------------------------------
	 */
	//添加商品类别名称
	public void ajax_productType_add() throws IOException{
		productTypeService.save(productType);
		response.getWriter().write("success");
	}
	
	//查询供应商下的重复类别
	public void ajax_productType_validUname() throws IOException{
		String result = "yes";
		ProductType pt = productTypeService.getProductTypeBySName(productType);
		if(pt != null){
			result ="no";
		}
		response.getWriter().write(result);
	}
	
	//为订单信息显示商品类别
	public void ajax_productType_getProduct() throws IOException{
		ProductType pt = productTypeService.getObj(query.getProductTypeId());
		Set<Product> products = pt.getProducts();
		JSONUtils.printJSONArray(response, products, new String[]{"productType"});
	}
	/**
	 * 所有的get和set方法
	 * -----------------------------------------------------------------
	 * @return
	 */
	public ProductTypeQuery getQuery() {
		return query;
	}

	public void setQuery(ProductTypeQuery query) {
		this.query = query;
	}
	
	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
}
