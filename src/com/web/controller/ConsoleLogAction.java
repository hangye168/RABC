package com.web.controller;
/**
 * 记录操作日志
 */
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.web.model.ConsoleLog;
import com.web.query.ConsoleLogQuery;
import com.web.service.ConsoleLogService;
import com.web.utils.Page;

public class ConsoleLogAction extends BaseAction {
	
	private ConsoleLogQuery query = new ConsoleLogQuery();
	
	private ConsoleLogService consoleLogService;
	
	public String consoleLog_list(){	
		ActionContext context = ActionContext.getContext();	
		Integer pageNo = query.getPageNo();
		if(pageNo == null || pageNo <= 1){
			query.setPageNo(1);
		}	
		Page page = consoleLogService.queryObjByCondition(query, exclude);	
		context.put("page", page);
		return SUCCESS;
	}
	
	public String consoleLog_input(){
		return SUCCESS;
	}
	
	public String consoleLog_consoleLog() throws UnsupportedEncodingException{
		String optType = new String(query.getOptType().getBytes("ISO-8859-1"),"UTF-8");
		query.setOptType(optType);
		List<ConsoleLog> logList = consoleLogService.queryObjByConditionNoPage(query, exclude);
		ActionContext context = ActionContext.getContext();
		context.put("logList", logList);
		return SUCCESS;
	}
	
	/**
	 * 所有的get和set方法
	 * -----------------------------------------------------------------
	 * @return
	 */
	public ConsoleLogQuery getQuery() {
		return query;
	}

	public void setQuery(ConsoleLogQuery query) {
		this.query = query;
	}
	
	public ConsoleLogService getConsoleLogService() {
		return consoleLogService;
	}

	public void setConsoleLogService(ConsoleLogService consoleLogService) {
		this.consoleLogService = consoleLogService;
	}
	
}
