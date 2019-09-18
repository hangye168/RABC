package com.web.interceptor;
/**
 * ��¼������
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
		//���Action������
		ActionContext context = ai.getInvocationContext();
		//���session
		Map<String, Object> session = context.getSession();
		//���session�û�
		Emp emp = (Emp) session.get("user");
		if(emp != null){
			//������������
			result = ai.invoke();
		}else {
			result = BaseAction.LOGIN;
		}
		return result;
	}

}
