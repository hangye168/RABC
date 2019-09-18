package com.web.interceptor;
/**
 * 登录拦截器
 */
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.web.controller.BaseAction;
import com.web.model.Emp;

public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		// TODO Auto-generated method stub
		String result = null;
		//获得Action的容器
		ActionContext context = ai.getInvocationContext();
		//获得session
		Map<String, Object> session = context.getSession();
		//获得session用户
		Emp emp = (Emp) session.get("user");
		if(emp != null){
			//让拦截器继续
			result = ai.invoke();
		}else {
			result = BaseAction.LOGIN;
		}
		return result;
	}

}
