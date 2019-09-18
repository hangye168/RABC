package com.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.web.utils.ERPConstants;

public class ERPOrderTypeTag extends TagSupport {

	//���ö�������ֵ
	private String orderType;
	
	//ʹ�ñ�ǩʱ���Ĺ���
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		//���JSPWriter������jspҳ��д�ı�
		JspWriter out = pageContext.getOut();
		String text = "";
		switch(orderType){
		//����utils�е�ERPConstants�ӿ�
		case ERPConstants.ORDER_TYPE_BUY://�ɹ���
			text = ERPConstants.ORDER_TYPE_BUY_TEXT;
			break;
		case ERPConstants.ORDER_TYPE_TRANS://���䵥
			text = ERPConstants.ORDER_TYPE_TRANS_TEXT;
			break;
		case ERPConstants.ORDER_TYPE_INSTOCK://��ⵥ
			text = ERPConstants.ORDER_TYPE_INSTOCK_TEXT;
			break;
		case ERPConstants.ORDER_TYPE_SALES://���۵�
			text = ERPConstants.ORDER_TYPE_SALES_TEXT;
			break;
	}
		try {
			out.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
	
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}
