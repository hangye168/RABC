package com.web.model;

import java.util.Set;

/**
 * 部门信息表
 * @author sh
 *
 */
public class Dep implements java.io.Serializable {

	private Integer depId;
	private String name;
	private String tel;
	
	private Set<Emp> emps;
	
	public Integer getDepId() {
		return depId;
	}
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Set<Emp> getEmps() {
		return emps;
	}
	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}
	public Dep() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dep(Integer depId, String name, String tel) {
		super();
		this.depId = depId;
		this.name = name;
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Dep [depId=" + depId + ", name=" + name + ", tel=" + tel + "]";
	}
}