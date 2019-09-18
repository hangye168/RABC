package com.web.model;

import java.util.Date;
import java.util.Set;
/**
 * 员工信息表
 * 从员工角度去看，员工和部门是多对一关系
 * @author sh
 *
 */
public class Emp implements java.io.Serializable {

	private Integer empId;
	private Integer depId;
	private String name;	//真实名字
	private String username;//登录名	
	private String email;	//邮箱
	private String tel;		//电话
	private Integer gender;	//性别
	private String address;	//地址
	private Date birthday;	//生日
	private String password;//密码
	
	private Dep dep;//员工和部门是之间是一个部门对多个员工
	
	private Set<Role> roles;//员工和权限之间是多对多关系
	
	public Emp(Integer empId, Integer depId, String name, String username,
			String email, String tel, Integer gender, String address,
			Date birthday, String password, Dep dep, Set<Role> roles) {
		super();
		this.empId = empId;
		this.depId = depId;
		this.name = name;
		this.username = username;
		this.email = email;
		this.tel = tel;
		this.gender = gender;
		this.address = address;
		this.birthday = birthday;
		this.password = password;
		this.dep = dep;
		this.roles = roles;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public Dep getDep() {
		return dep;
	}
	public void setDep(Dep dep) {
		this.dep = dep;
	}
	public Emp(Integer empId, Integer depId, String name, String username,
			String email, String tel, Integer gender, String address,
			Date birthday, String password) {
		super();
		this.empId = empId;
		this.depId = depId;
		this.name = name;
		this.username = username;
		this.email = email;
		this.tel = tel;
		this.gender = gender;
		this.address = address;
		this.birthday = birthday;
		this.password = password;
	}
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", depId=" + depId + ", name=" + name
				+ ", username=" + username + ", email=" + email + ", tel="
				+ tel + ", gender=" + gender + ", address=" + address
				+ ", birthday=" + birthday + ", password=" + password
				+ ", dep=" + dep + ", roles=" + roles + "]";
	}
	
}