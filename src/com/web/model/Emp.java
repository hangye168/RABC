package com.web.model;

import java.util.Date;
import java.util.Set;
/**
 * Ա����Ϣ��
 * ��Ա���Ƕ�ȥ����Ա���Ͳ����Ƕ��һ��ϵ
 * @author sh
 *
 */
public class Emp implements java.io.Serializable {

	private Integer empId;
	private Integer depId;
	private String name;	//��ʵ����
	private String username;//��¼��	
	private String email;	//����
	private String tel;		//�绰
	private Integer gender;	//�Ա�
	private String address;	//��ַ
	private Date birthday;	//����
	private String password;//����
	
	private Dep dep;//Ա���Ͳ�����֮����һ�����ŶԶ��Ա��
	
	private Set<Role> roles;//Ա����Ȩ��֮���Ƕ�Զ��ϵ
	
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