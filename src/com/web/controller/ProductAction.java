package com.web.controller;
/**
 * 商品表的处理
 */
import java.io.IOException;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.web.model.Product;
import com.web.model.Supplier;
import com.web.query.ProductQuery;
import com.web.service.ProductService;
import com.web.service.SupplierService;
import com.web.utils.JSONUtils;
import com.web.utils.Page;

public class ProductAction extends BaseAction {
	
	private ProductQuery query = new ProductQuery();
	
	//接收注入
	private ProductService productService;
	private SupplierService supplierService;
	
	//接受前端的数据
	private Product product = new Product();
	
	public String product_list(){	
		ActionContext context = ActionContext.getContext();	
		//查询所有的供应商
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}	
		Page page = productService.queryObjByCondition(query, exclude);	
		context.put("page", page);
		return SUCCESS;
	}
	
	public String product_input(){
		ActionContext context = ActionContext.getContext();
		//查询所有的供应商
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		return SUCCESS;
	}
	
	
	/**
	 * ajax方法区---------------------------------------------------
	 * @throws IOException
	 */
	//使用ajax添加商品   update
	public void ajax_product_add() throws IOException{
		productService.save(product);
		response.getWriter().write("success");
	}
	
	//为订单信息获取商品类别信息
	public void ajax_product_getProduct() throws IOException{
		Product pro = productService.getObj(query.getProductId());
		JSONUtils.printJSON(response, pro, new String[]{"productType"});
	}
	
	/**
	 * 所有的get和set方法
	 * -----------------------------------------------------------------
	 * @return
	 */
	public ProductQuery getQuery() {
		return query;
	}

	public void setQuery(ProductQuery query) {
		this.query = query;
	}
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
