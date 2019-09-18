package com.web.dao.impl;

import com.web.dao.ConsoleLogDao;
import com.web.model.ConsoleLog;
import com.web.query.ConsoleLogQuery;

public class ConsoleLogDaoImpl extends BasedaoImpl<ConsoleLog, ConsoleLogQuery> implements ConsoleLogDao {
	@Override
	public String createHql(ConsoleLogQuery q) {
		String hql = "from ConsoleLog t where 1=1 ";
		hql = hql + createHqlCondition(q) + " order by t.logId desc";
		return hql;
	}
	
	@Override
	public String createHqlCount(ConsoleLogQuery q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createHqlCondition(ConsoleLogQuery q) {
		// TODO Auto-generated method stub
		String hql = " and entityId = :entityId and t.tableName like :tableName and t.optType like :optType";
		return hql;
	}
}
