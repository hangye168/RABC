package com.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.web.utils.ERPConstants;

public class ERPOrderTypeTag extends TagSupport {

	//设置订单类型值
	private String orderType;
	
	//使用标签时做的工作
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		//获得JSPWriter对象向jsp页面写文本
		JspWriter out = pageContext.getOut();
		String text = "";
		switch(orderType){
		//调用utils中的ERPConstants接口
		case ERPConstants.ORDER_TYPE_BUY://采购单
			text = ERPConstants.ORDER_TYPE_BUY_TEXT;
			break;
		case ERPConstants.ORDER_TYPE_TRANS://运输单
			text = ERPConstants.ORDER_TYPE_TRANS_TEXT;
			break;
		case ERPConstants.ORDER_TYPE_INSTOCK://入库单
			text = ERPConstants.ORDER_TYPE_INSTOCK_TEXT;
			break;
		case ERPConstants.ORDER_TYPE_SALES://销售单
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
