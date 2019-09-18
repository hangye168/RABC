package com.web.utils;
/**
 * ����������
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
//		System.out.println(demo + "���������");
//	}
	
	/**
	 * ͳһ�����ļ�����
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
	 * ���ɲ�ѯ����
	 * @param className
	 * @throws Exception
	 */
	public static void generQuery(String className) throws Exception{
		//��demo�ļ�
		BufferedReader br = new BufferedReader(new FileReader("config/com/web/template/DemoQuery.tlf"));
		//��query����дclassname�ļ�
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/web/query/" + className + "Query.java"));
		String line = null;
		String newLine = null;
		//�����滻
		while((line = br.readLine()) != null){
			newLine = line.replace("Demo", className);
			bw.write(newLine);
			//����
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();
	}
	
	/**
	 * ����dao�ļ�
	 * @param className
	 * @throws Exception
	 */
	public static void generDao(String className) throws Exception{
		//��demo�ļ�
		BufferedReader br = new BufferedReader(new FileReader("config/com/web/template/DemoDao.tlf"));
		//��query����дclassname�ļ�
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/web/dao/" + className + "Dao.java"));
		String line = null;
		String newLine = null;
		//�����滻
		while((line = br.readLine()) != null){
			newLine = line.replace("Demo", className);
			bw.write(newLine);
			//����
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();
	}
	
	/**
	 * ����dao��ʵ���෽��
	 * @param className
	 * @throws Exception
	 */
	public static void generDaoImpl(String className) throws Exception{
		//��demo�ļ�
		BufferedReader br = new BufferedReader(new FileReader("config/com/web/template/DemoDaoImpl.tlf"));
		//��query����дclassname�ļ�
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/web/dao/impl/" + className + "DaoImpl.java"));
		String line = null;
		String newLine = null;
		//�����滻
		while((line = br.readLine()) != null){
			newLine = line.replace("Demo", className);
			bw.write(newLine);
			//����
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();
	}
	
	/**
	 * ����service����
	 * @param className
	 * @throws Exception
	 */
	public static void generService(String className) throws Exception{
		//��demo�ļ�
		BufferedReader br = new BufferedReader(new FileReader("config/com/web/template/DemoService.tlf"));
		//��query����дclassname�ļ�
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/web/service/" + className + "Service.java"));
		String line = null;
		String newLine = null;
		//�����滻
		while((line = br.readLine()) != null){
			newLine = line.replace("Demo", className);
			bw.write(newLine);
			//����
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();	
	}
	
	/**
	 * ����service��ʵ����
	 * @param className
	 * @throws Exception
	 */
	public static void generServiceImpl(String className) throws Exception{
		//���Сд������  ��һ����ĸת��Сд������Ĳ���
		String lowerCaseClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
		//��demo�ļ�
		BufferedReader br = new BufferedReader(new FileReader("config/com/web/template/DemoServiceImpl.tlf"));
		//��query����дclassname�ļ�
		BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/web/service/impl/" + className + "ServiceImpl.java"));
		String line = null;
		String newLine = null;
		//�����滻
		while((line = br.readLine()) != null){
			//Demo�滻Ϊԭ����className��demo��className������ĸ��λСд
			newLine = line.replace("Demo", className).replace("demo", lowerCaseClassName);
			bw.write(newLine);
			//����
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();	
	}
	
	/**
	 * ����dao��Spring�����ļ�
	 * @param className
	 * @throws Exception
	 */
	public static void generDaoConfig(String className) throws Exception{
		//���Сд������  ��һ����ĸת��Сд������Ĳ���
		String lowerCaseClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
		//��ȡxml�ļ�
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new FileReader("config/ApplicationContext-dao.xml"));
		//���ڵ�
		Element rootElement = doc.getRootElement();
		//�ڸ��ڵ������bean����
		Element newEle = rootElement.addElement("bean").addAttribute("id", lowerCaseClassName + "Dao")
				.addAttribute("class", "com.web.dao.impl." + className + "DaoImpl");
		newEle.addElement("property").addAttribute("name", "sessionFactory").addAttribute("ref", "sessionFactory");
		XMLWriter writer = new XMLWriter(new FileWriter("config/ApplicationContext-dao.xml"),OutputFormat.createPrettyPrint());
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * service��Spring�����ļ�
	 * @param className
	 * @throws Exception
	 */
	public static void generServiceConfig(String className) throws Exception{
		//���Сд������  ��һ����ĸת��Сд������Ĳ���
		String lowerCaseClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
		//��ȡxml�ļ�
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new FileReader("config/ApplicationContext-service.xml"));
		//���ڵ�
		Element rootElement = doc.getRootElement();
		//�ڸ��ڵ������bean����
		Element newEle = rootElement.addElement("bean").addAttribute("id", lowerCaseClassName + "Service")
				.addAttribute("class", "com.web.service.impl." + className + "ServiceImpl");
		newEle.addElement("property").addAttribute("name", lowerCaseClassName + "Dao").addAttribute("ref", lowerCaseClassName + "Dao");
		XMLWriter writer = new XMLWriter(new FileWriter("config/ApplicationContext-service.xml"),OutputFormat.createPrettyPrint());
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * ����action
	 * @param className
	 * @throws Exception
	 */
	public static void generAction(String className) throws Exception{
		//���Сд������
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
	 * ����Spring��action�����ļ�
	 * @param className
	 * @throws Exception
	 */
	public static void generSpringActionConfig(String className) throws Exception{
		//���Сд������
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
