package com.web.model;

import java.sql.Timestamp;
/**
 * 操作日志表
 * @author sh
 *
 */
public class ConsoleLog implements java.io.Serializable {

	private Integer logId;
	private Integer entityId;
	private String tableName;
	private String optType;
	private Timestamp optTime;
	private Integer empId;
	private String note;
	
	private Emp emp;
	
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getOptType() {
		return optType;
	}
	public void setOptType(String optType) {
		this.optType = optType;
	}
	public Timestamp getOptTime() {
		return optTime;
	}
	public void setOptTime(Timestamp optTime) {
		this.optTime = optTime;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public Emp getEmp() {
		return emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}
	public ConsoleLog(Integer logId, Integer entityId, String tableName,
			String optType, Timestamp optTime, Integer empId, String note) {
		super();
		this.logId = logId;
		this.entityId = entityId;
		this.tableName = tableName;
		this.optType = optType;
		this.optTime = optTime;
		this.empId = empId;
		this.note = note;
	}
	public ConsoleLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ConsoleLog [logId=" + logId + ", entityId=" + entityId
				+ ", tableName=" + tableName + ", optType=" + optType
				+ ", optTime=" + optTime + ", empId=" + empId + ", note="
				+ note + "]";
	}

}