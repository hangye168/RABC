package com.web.controller;
/**
 * 对人员信息进行操作
 * 登录验证
 * 生成验证码
 * 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.web.model.Dep;
import com.web.model.Emp;
import com.web.model.Menu;
import com.web.model.Role;
import com.web.query.EmpQuery;
import com.web.service.DepService;
import com.web.service.EmpService;
import com.web.utils.MD5Utils;
import com.web.utils.Page;

public class EmpAction extends BaseAction{
	
	String MAIN = "main";
	
	//接受查询对象
	private EmpQuery query = new EmpQuery();
	//注入service
	private EmpService empService;
	//注入部门信息的service
	private DepService depService;
	//接收前端发的数据
	private Emp emp = new Emp();
	//接受验证码
	private String captcha;
	
	private String roleIds;
	
	private String newPassword;
	
	//用于用户查询
	public String emp_list(){
		//查询部门信息
		List<Dep> list = depService.list();
		//将请求字段设置到Action中，从Action中直接获取Request和Session的信息
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}
		
		//查询员工数据
		Page page = empService.queryObjByCondition(query,exclude);
		context.put("page", page);
		return SUCCESS;
	}
	
	//用于获取要新建员工信息时的部门数据
	public String emp_input(){
		List<Dep> list = depService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		return SUCCESS;
	}
	
	//根据修改时传的empId  查询用户信息
	public String emp_update(){
		List<Dep> list = depService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		emp = empService.getObj(emp.getEmpId());
		return SUCCESS;
	}
	
	//ajax添加用户信息
	public void ajax_emp_add() throws IOException{
		String password = emp.getPassword();
		password = MD5Utils.md5(password);
		emp.setPassword(password);
		empService.save(emp);
		response.getWriter().write("success");
	}
	
	//ajax修改用户信息
	public void ajax_emp_update() throws IOException{
		empService.updateEmp(emp);
		response.getWriter().write("success");
	}
	
	//用于员工信息中用户名的ajax验证
	public void ajax_emp_validUname() throws IOException{
		String result = "yes";
		Emp emp2 = empService.getEmpByUname(emp.getUsername());
		if(emp2 != null){
			result = "no";
		}
		response.getWriter().write(result);
	}
	
	//删除员工信息
	public String emp_delete(){
		empService.delete(emp.getEmpId());
		return LIST;
	}
	
	//查询所有角色和当前员工的所有角色
	public String emp_listpop(){
		List<Role> roles = empService.getStateRoles(emp.getEmpId());
		ActionContext context = ActionContext.getContext();
		context.put("roles", roles);
		return SUCCESS;
	}
	
	//分配角色
	public void ajax_emp_grantrole() throws IOException{
		empService.updateEmpRole(emp.getEmpId(), roleIds);
		response.getWriter().write("success");
	}
	
	//登录跳转
	public String emp_toLogin(){
		return SUCCESS;
	}
	
	//修改密码
	public String emp_changePwd(){
		return SUCCESS;
	}
	
	//修改密码  未使用
	public String emp_changePassWord(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		Emp emp1 = (Emp) session2.get("user");
		String pass_get = emp.getPassword();
		if(pass_get == null){
			return LOGIN;
		}
		String newPass = MD5Utils.md5(pass_get);
		Emp emp2 = empService.getEmpByUnameAndPWord(emp1.getUsername(), newPass);
		if(emp2 == null){
			context.put("tip", "passerror");
			return LOGIN;
		}
		emp1.setPassword(pass_get);
		empService.updateEmp(emp1);
		return LOGIN;
	}
	
	//登录
	public String emp_login(){
		ActionContext context = ActionContext.getContext();
		//获得正确的验证码
		String rigthCap = (String) session.getAttribute("piccode");
		//判断验证码
		if(!StringUtils.equalsIgnoreCase(rigthCap, captcha)){
			context.put("tip", "caperror");
			return LOGIN;
		}
		//给明文密码加密
		String newPass = MD5Utils.md5(emp.getPassword());
		Emp emp1 = empService.getEmpByUnameAndPWord(emp.getUsername(), newPass);
		if(emp1 == null){
			context.put("tip", "userpasserror");
			return LOGIN;
		}
		//获得struts的session
		Map<String, Object> session2 = context.getSession();
		//把用户的信息放入session中
		session2.put("user", emp1);
		
		//拿到所有角色  向main页面返回数据  为main页面显示模块提供数据
		Set<Role> roles = emp1.getRoles();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for(Role r : roles){
			Set<Menu> menus = r.getMenus();
			createTreeData(list, menus);
		}
		//装换为json数据返回页面
		JSONArray ja = JSONArray.fromObject(list);
		
		context.getSession().put("zNodes", ja);
		return MAIN;
	}
	
	//注销
	public String emp_logout(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		session2.remove("user");
		return MAIN;
	}
	
	//为树形结构组装数据
	public void createTreeData(List<Map<String, Object>> list, Set<Menu> menus){
		for(Menu m : menus){
			Map<String,Object> map = new HashMap<String, Object>();
			Integer id = m.getMenuId();
			Integer pId = m.getParentMenuId();
			String name = m.getName();
			map.put("id", id);
			map.put("pId", pId);
			map.put("name", name);
			map.put("url", m.getUrl());
			map.put("target", "main");
			list.add(map);
		}
	}
	
	/**
	 * 生成验证码的图片
	 * @throws Exception
	 */
	public void ajax_emp_getImage() throws Exception{
		 System.out.println("------------生成数字和字母的验证码---------------");  
	     BufferedImage img = new BufferedImage(68, 22,BufferedImage.TYPE_INT_RGB);
	     // 得到该图片的绘图对象
	     Graphics g = img.getGraphics();
	     Random r = new Random();
	     Color c = new Color(200, 150, 255);
	     g.setColor(c);  
	     // 填充整个图片的颜色 
	     g.fillRect(0, 0, 68, 22); 
	     // 向图片中输出数字和字母    
	     StringBuffer sb = new StringBuffer();  	        
	     char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
	     int index, len = ch.length;  
	     for (int i = 0; i < 4; i++) {  
	    	 index = r.nextInt(len);
	    	 g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));  
	    	 g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
	    	 // 输出的  字体和大小    
	    	 g.drawString("" + ch[index], (i * 15) + 3, 18);   
	    	 //写什么数字，在图片 的什么位置画 
	    	 sb.append(ch[index]);
	     }  
	     //把验证码的值放入session中
	     request.getSession().setAttribute("piccode", sb.toString());
	     System.out.println("输出验证码---------  " + sb.toString()+"  ---------");
	     //把验证码的图片写回浏览器
	     ImageIO.write(img, "JPG", response.getOutputStream());  
	}
	
	
	/**
	 * 所有的get和set方法
	 * -----------------------------------------------------------------
	 * @return
	 */
	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public DepService getDepService() {
		return depService;
	}

	public void setDepService(DepService depService) {
		this.depService = depService;
	}

	public EmpQuery getQuery() {
		return query;
	}

	public void setQuery(EmpQuery query) {
		this.query = query;
	}

	public EmpService getEmpService() {
		return empService;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
