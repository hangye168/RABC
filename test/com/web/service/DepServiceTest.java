package com.web.service;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.model.Dep;
import com.web.query.DepQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class DepServiceTest {

	@Autowired
	private DepService depService;
	
//	@Test
	public void testSaveDep() {
		Dep dep = new Dep();
		dep.setName("开发部");
		dep.setTel("010-8888888");
		depService.save(dep);
	}

//	@Test
	public void testUpdateDep() {
		Dep emp = depService.getObj(13);
		emp.setName("rl");
		depService.update(emp);
	}

//	@Test
	public void testGetDep() {
		Dep dep = depService.getObj(9);
		System.out.println(dep);
	}

//	@Test
	public void testDeleteDep() {
		depService.delete(13);
	}

	@Test
	public void testQueryDepByCondition() {
		DepQuery dq = new DepQuery();
		dq.setName("采");
//		List<Dep> list = depService.queryObjByCondition(dq);
//		System.out.println(list);
	}

}
