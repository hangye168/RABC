package com.web.utils;
/**
 * ��ҳ����ز���
 */
import java.util.List;

public class Page {
	
	//ҳ��
	private int pageNo = 1;
	//ÿҳ����������
	private int pageSize = 5;
	//ָ����ѯ�����µ�������
	private int totalCount = 0;
	//ָ����ѯ�����µ���ҳ��
	private int totalPage = 1;
	//��ʼ�к� = (pageNo - 1)*pageSize
	private int startNum = 0;
	//�����
	private List<?> list;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * ����ҳ��
	 * @return
	 */
	public int getTotalPage() {
		totalPage = totalCount / pageSize;
		if(totalCount == 0 || totalPage % pageSize != 0){
			totalPage++;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * ����ҳ���ÿҳ��ʾ�����������㿪ʼ�к�
	 * @return
	 */
	public int getStartNum() {
		return (pageNo - 1) * pageSize;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
}
