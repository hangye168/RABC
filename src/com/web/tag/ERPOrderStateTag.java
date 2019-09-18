package com.web.tag;
/**
 * 对EL表达式进行了扩展
 */
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.web.utils.ERPConstants;

public class ERPOrderStateTag extends TagSupport {

	//设置订单类型值
	private String orderType;
	//设置订单状态值
	private String orderState;
	
	//使用展示订单的状态
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		//获得JSPWriter对象向jsp页面写文本
		JspWriter out = pageContext.getOut();
		String text = "";
		switch(orderType){
			case ERPConstants.ORDER_TYPE_BUY:
				switch(orderState){//展示采购单的状态
					case ERPConstants.ORDER_TYPE_BUY_AUDIT:
						text = ERPConstants.ORDER_TYPE_BUY_AUDIT_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_BUY_AUDIT_REJECT:
						text = ERPConstants.ORDER_TYPE_BUY_AUDIT_REJECT_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_BUY_AUDIT_PASS:
						text = ERPConstants.ORDER_TYPE_BUY_AUDIT_PASS_TEXT;
						break;
				}
				break;
			case ERPConstants.ORDER_TYPE_TRANS:
		
				switch(orderState){//展示运输单的状态
					case ERPConstants.ORDER_TYPE_TRANS_BUY:
						text = ERPConstants.ORDER_TYPE_TRANS_BUY_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_TRANS_ASSGIN:
						text = ERPConstants.ORDER_TYPE_TRANS_ASSGIN_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_TRANS_BUYING:
						text = ERPConstants.ORDER_TYPE_TRANS_BUYING_TEXT;
						break;
				}
				break;
			case ERPConstants.ORDER_TYPE_INSTOCK:
				switch(orderState){//展示入库单的状态
					case ERPConstants.ORDER_TYPE_INSTOCK_WAIT:
						text = ERPConstants.ORDER_TYPE_INSTOCK_WAIT_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_INSTOCK_INING:
						text = ERPConstants.ORDER_TYPE_INSTOCK_INING_TEXT;
						break;
					case ERPConstants.ORDER_TYPE_INSTOCK_FINISH:
						text = ERPConstants.ORDER_TYPE_INSTOCK_FINISH_TEXT;
						break;
				}	
				break;
			case ERPConstants.ORDER_TYPE_SALES://展示销售单的状态
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
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
}
