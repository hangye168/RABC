package com.web.model;

import java.util.Set;

/**
 * 角色信息
 * @author sh
 *
 */

public class Role implements java.io.Serializable {

	private Integer roleId;
	private String name;
	private String code;
	
	//给用户分配权限的时候，显示之前的权限
	private String select;
	//一个角色可以操作多个菜单
	private Set<Menu> menus;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name + ", code=" + code
				+ ", select=" + select + ", menus=" + menus + "]";
	}

	public Role(Integer roleId, String name, String code, String select,
			Set<Menu> menus) {
		super();
		this.roleId = roleId;
		this.name = name;
		this.code = code;
		this.select = select;
		this.menus = menus;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
}