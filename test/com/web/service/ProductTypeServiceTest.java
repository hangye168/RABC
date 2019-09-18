package com.web.service;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.model.ProductType;
import com.web.query.ProductTypeQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class ProductTypeServiceTest {

	@Autowired
	private ProductTypeService productTypeService;
	
//	@Test
	public void testSaveDep() {
		ProductType pt = new ProductType();
		pt.setSupplierId(7);
		pt.setName("ÀºÇò");
		productTypeService.save(pt);
	}

//	@Test
	public void testUpdateDep() {
		ProductType pt = productTypeService.getObj(7);
		pt.setName("rl");
		productTypeService.update(pt);
	}

	@Test
	public void testGetDep() {
		ProductType pt = productTypeService.getObj(7);
		System.out.println(pt);
	}

//	@Test
	public void testDeleteDep() {
		productTypeService.delete(4);
	}

//	@Test
	public void testQueryDepByCondition() {
		ProductTypeQuery pt = new ProductTypeQuery();
		pt.setName("Ð¬");
//		List<ProductType> list = productTypeService.queryObjByCondition(pt);
//		System.out.println(list);
	}

}
