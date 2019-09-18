package com.web.utils;
/**
 * 日期与字符串之间的装换
 * 
 * 执行继承StrutsTypeConverter的类型转换时， 
 * 页面向后台传数据执行convertFromString()， 
 * 后台向前台传数据执行convertToString()
 */
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter {
/**
 * 字符串到日期
 */
	@Override
	public Object convertFromString(Map map, String[] vals, Class class1) {
		// TODO Auto-generated method stub
		Date date = null;
		if (vals != null && vals.length > 0) {
			String dateStr = vals[0];
			if(StringUtils.isNotBlank(dateStr) && class1 == Date.class){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					date = sdf.parse(dateStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return date;
	}
/**
 * 日期到字符串
 */
	@Override
	public String convertToString(Map map, Object obj) {
		// TODO Auto-generated method stub
		String result = "";
		if(obj != null && (obj.getClass() == Date.class || obj.getClass() == Timestamp.class)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			result = sdf.format(obj);
		}
		return result;
	}

}
