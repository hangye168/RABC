package com.web.controller;
/**
 * �����ɫ��ص�����
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
	
	//���ܲ�ѯ����  ͨ��query���󣬴�ǰ�˽���ֵ
	private RoleQuery query = new RoleQuery();
	//ע��
	private RoleService roleService;
	//ע��menuservie
	private MenuService menuService;
	
	private Role role;
	
	//��ȡǰ�˵Ĵ��ݵĵ��ѡ��֮���id��ɵ��ַ���
	private String permIds;
	
	public String role_list(){
		ActionContext context = ActionContext.getContext();
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}
		
		//��ѯԱ������
		Page page = roleService.queryObjByCondition(query,exclude);
		context.put("page", page);
		return SUCCESS;
	}
	
	//�����û���Ϣ����д
	public String role_input(){
		return SUCCESS;
	}
	
	//�����޸�ʱ����empId  ��ѯ�û���Ϣ
	public String role_update(){
		List<Role> list = roleService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		role = roleService.getObj(role.getRoleId());
		return SUCCESS;
	}	
	
	//ɾ����ɫ��Ϣ
	public String role_delete(){	
		roleService.delete(role.getRoleId());
		return LIST;
	}
	
	//����״����ʽ����ǰ��չʾĿ¼�ṹ
	public String role_listperm(){
		//����roleId��ѯrole����
		Role role1 = roleService.getObj(query.getRoleId());
		Set<Menu> menus = role1.getMenus();
		//���ϵͳ�˵�
		Menu rootMenu = menuService.getObj(1);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		createTreeData(rootMenu, list, menus);
		JSONArray ja = JSONArray.fromObject(list);
		ActionContext context = ActionContext.getContext();
		context.put("zNodes", ja);
		return SUCCESS;
	}
	
	//��װ����
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
	//չʾ����ӵ�е�Ȩ��
	public void createTreeData(Menu menu, List<Map<String, Object>> list, Set<Menu> roleMenus){
		Map<String,Object> map = new HashMap<String, Object>();
		if(menu != null){
			//��ȡ��ǰ�ڵ�
			Integer id = menu.getMenuId();
			//��ȡ���ڵ�
			Integer pId = menu.getParentMenuId();
			//��ȡ�˵�����
			String name = menu.getName();
			if(id.intValue() != 1){
				map.put("id", id);
				map.put("pId", pId);
				map.put("name", name);
				for(Menu m : roleMenus){
					if(m.getMenuId().intValue() == menu.getMenuId().intValue()){
						//�Ƿ�ѡ
						map.put("checked", true);
						//�Ƿ��
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
	
	//ajax������------------------------------------------
	//����Ȩ��
	public void ajax_role_grantPerm() throws IOException{
		//���ú���������Ȩ��
		roleService.updateGrantPerm(query.getRoleId(), permIds);
		response.getWriter().write("success");
	}
	
	//ajax��ӽ�ɫ��Ϣ
	public void ajax_role_add() throws IOException{
		roleService.save(role);
		response.getWriter().write("success");
	}
			
	//ajax��֤��ɫ���Ƿ����
	public void ajax_role_validUname() throws IOException{
		String result = "yes";
//		Role role = RoleDao.getRoleByUname(role.getName());
//		if(emp2 != null){
//			result = "no";
//		}
		response.getWriter().write(result);
	}
	
	//ajax�޸Ľ�ɫ���ƺͱ��
	public void ajax_role_update() throws IOException{
		roleService.updateRole(role);
		response.getWriter().write("success");
	}
	
	/**
	 * ���е�get��set����
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
