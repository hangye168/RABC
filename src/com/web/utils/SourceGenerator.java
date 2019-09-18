package com.web.utils;
/**
 * 代码生成器
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.Document;
import org.dom4j.Element;

public class SourceGenerator {
	
//	public static void main(String[] args) throws Exception{
//		String demo = "Menu";
//		generator(demo);
//		System.out.println(demo + "表：生成完毕");
//	}
	
	/**
	 * 统一生成文件函数
	 * @param className
	 * @throws Exception
	 */
	public static void generator(String className) throws Exception{
		generQuery(className);
		
		generDao(className);
		generDaoImpl(className);
		
		generService(className);
		generServiceImpl(className);
		
		generDaoConfig(className);
		generServiceConfig(className);
		
		generAction(className);
		generSpringActionConfig(className);
	}
	
	/**
	 * 生成查询对象
	 * @param className
	 * @throws Exception
	 */
	public static void generQuery(String className) throws Exception{
		//读demo文件
		BufferedReader br = new BufferedReader(new FileReader("config/com/web/template/DemoQuery.tlf"));
		//往query包中写classname文件
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/web/query/" + className + "Query.java"));
		String line = null;
		String newLine = null;
		//进行替换
		while((line = br.readLine()) != null){
			newLine = line.replace("Demo", className);
			bw.write(newLine);
			//换行
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();
	}
	
	/**
	 * 生成dao文件
	 * @param className
	 * @throws Exception
	 */
	public static void generDao(String className) throws Exception{
		//读demo文件
		BufferedReader br = new BufferedReader(new FileReader("config/com/web/template/DemoDao.tlf"));
		//往query包中写classname文件
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/web/dao/" + className + "Dao.java"));
		String line = null;
		String newLine = null;
		//进行替换
		while((line = br.readLine()) != null){
			newLine = line.replace("Demo", className);
			bw.write(newLine);
			//换行
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();
	}
	
	/**
	 * 生成dao的实现类方法
	 * @param className
	 * @throws Exception
	 */
	public static void generDaoImpl(String className) throws Exception{
		//读demo文件
		BufferedReader br = new BufferedReader(new FileReader("config/com/web/template/DemoDaoImpl.tlf"));
		//往query包中写classname文件
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/web/dao/impl/" + className + "DaoImpl.java"));
		String line = null;
		String newLine = null;
		//进行替换
		while((line = br.readLine()) != null){
			newLine = line.replace("Demo", className);
			bw.write(newLine);
			//换行
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();
	}
	
	/**
	 * 生成service方法
	 * @param className
	 * @throws Exception
	 */
	public static void generService(String className) throws Exception{
		//读demo文件
		BufferedReader br = new BufferedReader(new FileReader("config/com/web/template/DemoService.tlf"));
		//往query包中写classname文件
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/web/service/" + className + "Service.java"));
		String line = null;
		String newLine = null;
		//进行替换
		while((line = br.readLine()) != null){
			newLine = line.replace("Demo", className);
			bw.write(newLine);
			//换行
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();	
	}
	
	/**
	 * 生成service的实现类
	 * @param className
	 * @throws Exception
	 */
	public static void generServiceImpl(String className) throws Exception{
		//获得小写的类名  第一个字母转换小写，其余的不变
		String lowerCaseClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
		//读demo文件
		BufferedReader br = new BufferedReader(new FileReader("config/com/web/template/DemoServiceImpl.tlf"));
		//往query包中写classname文件
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/web/service/impl/" + className + "ServiceImpl.java"));
		String line = null;
		String newLine = null;
		//进行替换
		while((line = br.readLine()) != null){
			//Demo替换为原来的className，demo将className的首字母换位小写
			newLine = line.replace("Demo", className).replace("demo", lowerCaseClassName);
			bw.write(newLine);
			//换行
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();	
	}
	
	/**
	 * 生成dao的Spring配置文件
	 * @param className
	 * @throws Exception
	 */
	public static void generDaoConfig(String className) throws Exception{
		//获得小写的类名  第一个字母转换小写，其余的不变
		String lowerCaseClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
		//读取xml文件
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new FileReader("config/ApplicationContext-dao.xml"));
		//根节点
		Element rootElement = doc.getRootElement();
		//在根节点中添加bean属性
		Element newEle = rootElement.addElement("bean").addAttribute("id", lowerCaseClassName + "Dao")
				.addAttribute("class", "com.web.dao.impl." + className + "DaoImpl");
		newEle.addElement("property").addAttribute("name", "sessionFactory").addAttribute("ref", "sessionFactory");
		XMLWriter writer = new XMLWriter(new FileWriter("config/ApplicationContext-dao.xml"),OutputFormat.createPrettyPrint());
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * service的Spring配置文件
	 * @param className
	 * @throws Exception
	 */
	public static void generServiceConfig(String className) throws Exception{
		//获得小写的类名  第一个字母转换小写，其余的不变
		String lowerCaseClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
		//读取xml文件
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new FileReader("config/ApplicationContext-service.xml"));
		//根节点
		Element rootElement = doc.getRootElement();
		//在根节点中添加bean属性
		Element newEle = rootElement.addElement("bean").addAttribute("id", lowerCaseClassName + "Service")
				.addAttribute("class", "com.web.service.impl." + className + "ServiceImpl");
		newEle.addElement("property").addAttribute("name", lowerCaseClassName + "Dao").addAttribute("ref", lowerCaseClassName + "Dao");
		XMLWriter writer = new XMLWriter(new FileWriter("config/ApplicationContext-service.xml"),OutputFormat.createPrettyPrint());
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * 生成action
	 * @param className
	 * @throws Exception
	 */
	public static void generAction(String className) throws Exception{
		//获得小写的类名
		String lowerCaseClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
		BufferedReader br = new BufferedReader(new FileReader("config/com/web/template/DemoAction.tlf"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/web/controller/"+className+"Action.java"));
		String line = null;
		String newLine = null;
		while((line = br.readLine()) != null){
			newLine = line.replace("Demo", className).replace("demo", lowerCaseClassName);
			bw.write(newLine);
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();
	}
	
	/**
	 * 生成Spring的action配置文件
	 * @param className
	 * @throws Exception
	 */
	public static void generSpringActionConfig(String className) throws Exception{
		//获得小写的类名
		String lowerCaseClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new FileReader("config/ApplicationContext-action.xml"));
		Element rootElement = doc.getRootElement();
		
		
		Element newEle = rootElement.addElement("bean")
					.addAttribute("id", lowerCaseClassName+"Action")
					.addAttribute("class", "com.web.controller."+className+"Action")
					.addAttribute("scope", "prototype");
		newEle.addElement("property")
			  .addAttribute("name", lowerCaseClassName+"Service")
			  .addAttribute("ref", lowerCaseClassName+"Service");
		XMLWriter writer = new XMLWriter(new FileWriter("config/ApplicationContext-action.xml"), OutputFormat.createPrettyPrint());
		writer.write(doc);
		writer.close();
	
	}
}
