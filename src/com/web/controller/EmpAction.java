package com.web.controller;
/**
 * ����Ա��Ϣ���в���
 * ��¼��֤
 * ������֤��
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
	
	//���ܲ�ѯ����
	private EmpQuery query = new EmpQuery();
	//ע��service
	private EmpService empService;
	//ע�벿����Ϣ��service
	private DepService depService;
	//����ǰ�˷�������
	private Emp emp = new Emp();
	//������֤��
	private String captcha;
	
	private String roleIds;
	
	private String newPassword;
	
	//�����û���ѯ
	public String emp_list(){
		//��ѯ������Ϣ
		List<Dep> list = depService.list();
		//�������ֶ����õ�Action�У���Action��ֱ�ӻ�ȡRequest��Session����Ϣ
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}
		
		//��ѯԱ������
		Page page = empService.queryObjByCondition(query,exclude);
		context.put("page", page);
		return SUCCESS;
	}
	
	//���ڻ�ȡҪ�½�Ա����Ϣʱ�Ĳ�������
	public String emp_input(){
		List<Dep> list = depService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		return SUCCESS;
	}
	
	//�����޸�ʱ����empId  ��ѯ�û���Ϣ
	public String emp_update(){
		List<Dep> list = depService.list();
		ActionContext context = ActionContext.getContext();
		context.put("dList", list);
		emp = empService.getObj(emp.getEmpId());
		return SUCCESS;
	}
	
	//ajax����û���Ϣ
	public void ajax_emp_add() throws IOException{
		String password = emp.getPassword();
		password = MD5Utils.md5(password);
		emp.setPassword(password);
		empService.save(emp);
		response.getWriter().write("success");
	}
	
	//ajax�޸��û���Ϣ
	public void ajax_emp_update() throws IOException{
		empService.updateEmp(emp);
		response.getWriter().write("success");
	}
	
	//����Ա����Ϣ���û�����ajax��֤
	public void ajax_emp_validUname() throws IOException{
		String result = "yes";
		Emp emp2 = empService.getEmpByUname(emp.getUsername());
		if(emp2 != null){
			result = "no";
		}
		response.getWriter().write(result);
	}
	
	//ɾ��Ա����Ϣ
	public String emp_delete(){
		empService.delete(emp.getEmpId());
		return LIST;
	}
	
	//��ѯ���н�ɫ�͵�ǰԱ�������н�ɫ
	public String emp_listpop(){
		List<Role> roles = empService.getStateRoles(emp.getEmpId());
		ActionContext context = ActionContext.getContext();
		context.put("roles", roles);
		return SUCCESS;
	}
	
	//�����ɫ
	public void ajax_emp_grantrole() throws IOException{
		empService.updateEmpRole(emp.getEmpId(), roleIds);
		response.getWriter().write("success");
	}
	
	//��¼��ת
	public String emp_toLogin(){
		return SUCCESS;
	}
	
	//�޸�����
	public String emp_changePwd(){
		return SUCCESS;
	}
	
	//�޸�����  δʹ��
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
	
	//��¼
	public String emp_login(){
		ActionContext context = ActionContext.getContext();
		//�����ȷ����֤��
		String rigthCap = (String) session.getAttribute("piccode");
		//�ж���֤��
		if(!StringUtils.equalsIgnoreCase(rigthCap, captcha)){
			context.put("tip", "caperror");
			return LOGIN;
		}
		//�������������
		String newPass = MD5Utils.md5(emp.getPassword());
		Emp emp1 = empService.getEmpByUnameAndPWord(emp.getUsername(), newPass);
		if(emp1 == null){
			context.put("tip", "userpasserror");
			return LOGIN;
		}
		//���struts��session
		Map<String, Object> session2 = context.getSession();
		//���û�����Ϣ����session��
		session2.put("user", emp1);
		
		//�õ����н�ɫ  ��mainҳ�淵������  Ϊmainҳ����ʾģ���ṩ����
		Set<Role> roles = emp1.getRoles();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for(Role r : roles){
			Set<Menu> menus = r.getMenus();
			createTreeData(list, menus);
		}
		//װ��Ϊjson���ݷ���ҳ��
		JSONArray ja = JSONArray.fromObject(list);
		
		context.getSession().put("zNodes", ja);
		return MAIN;
	}
	
	//ע��
	public String emp_logout(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session2 = context.getSession();
		session2.remove("user");
		return MAIN;
	}
	
	//Ϊ���νṹ��װ����
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
	 * ������֤���ͼƬ
	 * @throws Exception
	 */
	public void ajax_emp_getImage() throws Exception{
		 System.out.println("------------�������ֺ���ĸ����֤��---------------");  
	     BufferedImage img = new BufferedImage(68, 22,BufferedImage.TYPE_INT_RGB);
	     // �õ���ͼƬ�Ļ�ͼ����
	     Graphics g = img.getGraphics();
	     Random r = new Random();
	     Color c = new Color(200, 150, 255);
	     g.setColor(c);  
	     // �������ͼƬ����ɫ 
	     g.fillRect(0, 0, 68, 22); 
	     // ��ͼƬ��������ֺ���ĸ    
	     StringBuffer sb = new StringBuffer();  	        
	     char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
	     int index, len = ch.length;  
	     for (int i = 0; i < 4; i++) {  
	    	 index = r.nextInt(len);
	    	 g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));  
	    	 g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
	    	 // �����  ����ʹ�С    
	    	 g.drawString("" + ch[index], (i * 15) + 3, 18);   
	    	 //дʲô���֣���ͼƬ ��ʲôλ�û� 
	    	 sb.append(ch[index]);
	     }  
	     //����֤���ֵ����session��
	     request.getSession().setAttribute("piccode", sb.toString());
	     System.out.println("�����֤��---------  " + sb.toString()+"  ---------");
	     //����֤���ͼƬд�������
	     ImageIO.write(img, "JPG", response.getOutputStream());  
	}
	
	
	/**
	 * ���е�get��set����
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
