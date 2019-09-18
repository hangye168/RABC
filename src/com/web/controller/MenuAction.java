package com.web.controller;
/**
 * 菜单处理 对菜单进行增删改查
 */
import java.io.IOException;
import java.util.List;

import com.web.model.Menu;
import com.web.query.MenuQuery;
import com.web.service.MenuService;
import com.web.utils.Page;
import com.opensymphony.xwork2.ActionContext;

public class MenuAction extends BaseAction {
	
	private MenuQuery query = new MenuQuery();
	
	private MenuService menuService;
	
	private Menu menu = new Menu();
	
	public String menu_list(){	
		List<Menu> list = menuService.listMenu();
		ActionContext context = ActionContext.getContext();	
		context.put("dList", list);
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}	
		Page page = menuService.queryObjByCondition(query, exclude);	
		context.put("page", page);
		return SUCCESS;
	}
	
	public String menu_input(){
		List<Menu> list = menuService.listMenu();
		ActionContext context = ActionContext.getContext();	
		context.put("dList", list);
		return SUCCESS;
	}
	
	//修改跳转
	public String menu_update(){
		List<Menu> list = menuService.listMenu();
		ActionContext context = ActionContext.getContext();	
		context.put("dList", list);
		menu = menuService.getObj(menu.getMenuId());
		return SUCCESS;
	}
	//删除信息
	public String menu_delete(){
		menuService.delete(menu.getMenuId());
		return LIST;
	}
	
	/**
	 * ajax方法区----------------------------------
	 */
	//ajax添加菜单信息
	public void ajax_menu_add() throws IOException{
		Menu menu1 = new Menu();
		menu1.setIsMenu(1);
		menu1.setName(menu.getName());
		menu1.setParentMenuId(menu.getParentMenuId());
		if (menu1.getParentMenuId() == 1) {
			menu1.setLevel(2);
		}else {
			menu1.setLevel(3);
		}
		menu1.setUrl(menu.getUrl());
		menuService.save(menu1);
		response.getWriter().write("success");
	}
	
	//ajax修改菜单信息
	public void ajax_menu_update() throws IOException{
		if (menu.getParentMenuId() == 1) {
			menu.setLevel(2);
		}else {
			menu.setLevel(3);
		}
		menuService.updateMenu(menu);
		response.getWriter().write("success");
	}
	/**
	 * 所有的get和set方法
	 * -----------------------------------------------------------------
	 * @return
	 */
	public MenuQuery getQuery() {
		return query;
	}

	public void setQuery(MenuQuery query) {
		this.query = query;
	}
	
	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
