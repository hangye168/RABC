package com.web.service.impl;

import com.web.dao.ConsoleLogDao;
import com.web.model.ConsoleLog;
import com.web.query.ConsoleLogQuery;
import com.web.service.ConsoleLogService;

public class ConsoleLogServiceImpl extends BaseServiceImpl<ConsoleLog, ConsoleLogQuery> implements ConsoleLogService {

	private ConsoleLogDao consoleLogDao;
	
	public void setConsoleLogDao(ConsoleLogDao consoleLogDao) {
		this.consoleLogDao = consoleLogDao;
		this.baseDao = consoleLogDao;
	}
	
	

}
