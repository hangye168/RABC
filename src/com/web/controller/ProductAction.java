package com.web.controller;
/**
 * ��Ʒ��Ĵ���
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
	
	//����ע��
	private ProductService productService;
	private SupplierService supplierService;
	
	//����ǰ�˵�����
	private Product product = new Product();
	
	public String product_list(){	
		ActionContext context = ActionContext.getContext();	
		//��ѯ���еĹ�Ӧ��
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
		//��ѯ���еĹ�Ӧ��
		List<Supplier> suppliers = supplierService.list();
		context.put("suppliers", suppliers);
		return SUCCESS;
	}
	
	
	/**
	 * ajax������---------------------------------------------------
	 * @throws IOException
	 */
	//ʹ��ajax�����Ʒ   update
	public void ajax_product_add() throws IOException{
		productService.save(product);
		response.getWriter().write("success");
	}
	
	//Ϊ������Ϣ��ȡ��Ʒ�����Ϣ
	public void ajax_product_getProduct() throws IOException{
		Product pro = productService.getObj(query.getProductId());
		JSONUtils.printJSON(response, pro, new String[]{"productType"});
	}
	
	/**
	 * ���е�get��set����
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
