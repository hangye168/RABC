package com.web.controller;
/**
 * baseactionʵ��   Ϊ����action�ṩ��Ҫ������
 */
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	
	HttpServletRequest request;//����http����
	
	HttpServletResponse response;//�������
	
	HttpSession session;//����һ��������Session
	
	ServletContext application;//ȫ�ֵĴ�����Ϣ�Ŀռ�
	
	List<String> exclude = new ArrayList<String>();
	
	String LIST = "list";//ΪStruts�ṩ
	//Ϊ���ص��������ҳ��ͷ�ҳ����
	public BaseAction(){
		exclude.add("pageNo");
		exclude.add("startNum");
		//ServletActionContext����ֱ�Ӽ̳���ActionContext�ṩֱ����Servlet��ض�����ʵĹ���
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
		application = ServletActionContext.getServletContext();
	}
}
