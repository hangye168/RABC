package com.web.controller;
/**
 * baseaction实现   为其他action提供必要的数据
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
	
	HttpServletRequest request;//接受http请求
	
	HttpServletResponse response;//输出数据
	
	HttpSession session;//创建一个独立的Session
	
	ServletContext application;//全局的储存信息的空间
	
	List<String> exclude = new ArrayList<String>();
	
	String LIST = "list";//为Struts提供
	//为返回的数据添加页面和分页功能
	public BaseAction(){
		exclude.add("pageNo");
		exclude.add("startNum");
		//ServletActionContext该类直接继承了ActionContext提供直接与Servlet相关对象访问的功能
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
		application = ServletActionContext.getServletContext();
	}
}
