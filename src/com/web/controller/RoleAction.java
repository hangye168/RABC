package com.web.controller;
/**
 * 处理角色相关的问题
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionContext;
import com.web.model.Menu;
import com.web.model.Role;
import com.web.query.RoleQuery;
import com.web.service.MenuService;
import com.web.service.RoleService;
import com.web.utils.Page;

public class RoleAction extends BaseAction{
	
	//接受查询对象  通过query对象，从前端接收值
	private RoleQuery query = new RoleQuery();
	//注入
	private RoleService roleService;
	//注入menuservie
	private MenuService menuService;
	
	private Role role;
	
	//获取前端的传递的点击选项之后的id组成的字符串
	private String permIds;
	
	public String role_list(){
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}
		
		//查询员工数据
		Page page = roleService.queryObjByCondition(query,exclude);
		context.put("page", page);
		return SUCCESS;
	}
	
	//用于用户信息的填写
	public String role_input(){
		return SUCCESS;
	}
	
	//根据修改时传的empId  查询用户信息
	public String role_update(){
		List<Role> list = roleService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		role = roleService.getObj(role.getRoleId());
		return SUCCESS;
	}	
	
	//删除角色信息
	public String role_delete(){	
		roleService.delete(role.getRoleId());
		return LIST;
	}
	
	//以树状的形式，给前端展示目录结构
	public String role_listperm(){
		//根据roleId查询role对象
		Role role1 = roleService.getObj(query.getRoleId());
		Set<Menu> menus = role1.getMenus();
		//获得系统菜单
		Menu rootMenu = menuService.getObj(1);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		createTreeData(rootMenu, list, menus);
		JSONArray ja = JSONArray.fromObject(list);
		ActionContext context = ActionContext.getContext();
		context.put("zNodes", ja);
		return SUCCESS;
	}
	
	//组装数据
	public void createTreeData(Menu menu, List<Map<String, Object>> list){
		Map<String,Object> map = new HashMap<String, Object>();
		if(menu != null){
			Integer id = menu.getMenuId();
			Integer pId = menu.getParentMenuId();
			String name = menu.getName();
			if(id.intValue() != 1){
				map.put("id", id);
				map.put("pId", pId);
				map.put("name", name);
				list.add(map);
			}
			
			Set<Menu> menus = menu.getMenus();
			if(menus != null && menus.size() > 0){
				for(Menu m: menus){
					createTreeData(m, list);
				}
			}	
		}
	}
	//展示现在拥有的权限
	public void createTreeData(Menu menu, List<Map<String, Object>> list, Set<Menu> roleMenus){
		Map<String,Object> map = new HashMap<String, Object>();
		if(menu != null){
			//获取当前节点
			Integer id = menu.getMenuId();
			//获取父节点
			Integer pId = menu.getParentMenuId();
			//获取菜单名称
			String name = menu.getName();
			if(id.intValue() != 1){
				map.put("id", id);
				map.put("pId", pId);
				map.put("name", name);
				for(Menu m : roleMenus){
					if(m.getMenuId().intValue() == menu.getMenuId().intValue()){
						//是否勾选
						map.put("checked", true);
						//是否打开
						map.put("open", true);
						break;
					}
				}
				
				list.add(map);
			}
			
			Set<Menu> menus = menu.getMenus();
			if(menus != null && menus.size() > 0){
				for(Menu m: menus){
					createTreeData(m, list,roleMenus);
				}
			}	
		}
	}
	
	//ajax方法区------------------------------------------
	//赋予权限
	public void ajax_role_grantPerm() throws IOException{
		//调用函数，分配权限
		roleService.updateGrantPerm(query.getRoleId(), permIds);
		response.getWriter().write("success");
	}
	
	//ajax添加角色信息
	public void ajax_role_add() throws IOException{
		roleService.save(role);
		response.getWriter().write("success");
	}
			
	//ajax验证角色名是否存在
	public void ajax_role_validUname() throws IOException{
		String result = "yes";
//		Role role = RoleDao.getRoleByUname(role.getName());
//		if(emp2 != null){
//			result = "no";
//		}
		response.getWriter().write(result);
	}
	
	//ajax修改角色名称和编号
	public void ajax_role_update() throws IOException{
		roleService.updateRole(role);
		response.getWriter().write("success");
	}
	
	/**
	 * 所有的get和set方法
	 * -----------------------------------------------------------------
	 * @return
	 */
	public RoleQuery getQuery() {
		return query;
	}

	public void setQuery(RoleQuery query) {
		this.query = query;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService RoleService) {
		this.roleService = RoleService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	public String getPermIds() {
		return permIds;
	}
	public void setPermIds(String permIds) {
		this.permIds = permIds;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
