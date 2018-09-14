package com.elegps.tscweb.tscconfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * XML �ļ�������
 * @author LuYun
 * <font color="red"><br>��Ҫ����:</font>
 * <ul>
 * <li>���tsc.xml�ļ�·�����ļ�����
 * <li>����tsc�����ļ�������
 * <li>�����־���������ļ�
 * </ul>
 *
 */
public class ConfigXML {
	//TSC �����ļ�·��
	private static final String TSC_FILENAME = FilePathConfig.getTSCFileName();
	//��־�����ļ�·��
	private static final String LOG_FILENAME =PathUtils.getInstance().getWebinfoPath()+ "/classes/logtype.xml";
	

	private static Logger consoleLogger = Logger.getLogger(ConfigXML.class);

	public static void main(String[] args) {
		// Iterator it = doc.getRootElement().elementIterator();
		// while (it.hasNext()) {
		// Element e = (Element) it.next();
		// System.out.println("<" + e.getName().toString() + ">"
		// + e.getTextTrim() + "</" + e.getName().toString() + ">");
		// }
		// System.out.println(doc.asXML());
//		List<LogType> list=logTypeList();

		XmlBean xml = new XmlBean();
		xml.seteName("luyun");
		xml.seteValue("1000000");
		xml.seteType("6");
		xml.seteComment("�Ӹ�����,�������");
//		updateXml(xml, "LuYun");
//		int i = addXml(xml, TSC_FILENAME);
//		if (i > 0)
			formatXML("F:\\monitor.xml");

//		List<XmlBean> beans = listXml(null);
//		for (XmlBean xmlBean : beans) {
//			System.out.println("<" + xmlBean.geteName() + " etype="
//					+ xmlBean.geteType() + "  ecomment="
//					+ xmlBean.geteComment() + ">" + xmlBean.geteValue() + "</"
//					+ xmlBean.geteName() + ">");
//		}
	}

	/**
	 * ��ʽ��XML�ĵ�,�������������
	 * 
	 * @param filename
	 */
	public static void formatXML(String filename) {
		try {
			Document doc = LoadXML.init(filename);
			XMLWriter writer = null;
			// ָ���˸�ʽ���ķ�ʽΪ����ʽ����ǽ���ʽ
			OutputFormat format = OutputFormat.createPrettyPrint();
			// ָ������
			format.setEncoding("GB2312");
			writer = new XMLWriter(new FileWriter(new File(filename)), format);
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ȡ������xml�����б�
	 * 
	 * @return
	 */
	public static List<XmlBean> listXml(String type) {
		List<XmlBean> list = new ArrayList<XmlBean>();
		Document doc = LoadXML.init(TSC_FILENAME);
		XmlBean bean = null;
		Iterator<Object> i = (Iterator<Object>) doc.getRootElement().elementIterator();
		while (i.hasNext()) {
			Element e = (Element) i.next();
			bean = new XmlBean();
			if (null != type && !"".equals(type)) {
				if (e.attributeValue("etype").equals(type)) {
					bean.seteName(e.getName().trim().toString());
					bean.seteValue(e.getTextTrim().toString());
					bean.seteType(e.attributeValue("etype"));
					bean.seteComment(e.attributeValue("ecomment"));
					list.add(bean);
				}
			} else {
				bean.seteName(e.getName().trim().toString());
				bean.seteValue(e.getTextTrim().toString());
				bean.seteType(e.attributeValue("etype"));
				bean.seteComment(e.attributeValue("ecomment"));
				list.add(bean);
			}
		}
		return list;
	}
	/**
	 * ��õ���Ԫ�ص�����
	 * @param name Ԫ������
	 * @return
	 */
	public static XmlBean getXMLBean(String name){
		XmlBean bean=null;
		Document doc=LoadXML.init(TSC_FILENAME);
		Iterator it=doc.getRootElement().elementIterator();
		while(it.hasNext()){
			Element e=(Element)it.next();
			if(null!=name&&e.getName().equals(name)){
				bean=new XmlBean();
				System.out.println(e.asXML());
				bean.seteName(e.getName().trim().toString());
				bean.seteValue(e.getTextTrim().toString());
				bean.seteType(e.attributeValue("etype").toString());
				bean.seteComment(e.attributeValue("ecomment").toString());
			}
		}
		return bean;
	}
	/**
	 * ��XML�ĵ������һ��Ԫ��
	 * 
	 * @param xml
	 * @return
	 */
	public static int addXml(XmlBean xml) {
		int returnValue = 0;
		Document doc = LoadXML.init(TSC_FILENAME);
		try {
			Iterator it = doc.nodeIterator();
			if (it.hasNext()) {
				Element tsc = (Element) it.next();
				Element newxml = tsc.addElement(xml.geteName());
				newxml.addAttribute("etype", xml.geteType());
				newxml.addAttribute("ecomment", xml.geteComment());
				newxml.setText(xml.geteValue());
				tsc.addComment("���Ӹ�ע��");
				consoleLogger.debug(xml.geteName() + "�ڵ���ӳɹ�!");
			}

			// ָ���˸�ʽ���ķ�ʽΪ����ʽ����ǽ���ʽ
			OutputFormat format = OutputFormat.createPrettyPrint();
			// ָ������
			format.setEncoding("GB2312");
			XMLWriter writer = new XMLWriter(new FileWriter(new File(TSC_FILENAME)), format);
			writer.write(doc);
			writer.close();
			returnValue = 1;
		} catch (IOException e) {
			consoleLogger.error(e);
		}
		return returnValue;
	}
/**
 * �����־����
 * @return
 */
	public static List<LogType> logTypeList(){
		List<LogType> list=new ArrayList<LogType>();
		LogType bean=null;
		Document document=LoadXML.init(LOG_FILENAME);
		Iterator it=document.getRootElement().elementIterator();
		while(it.hasNext()){
			Element e=(Element) it.next();	
			bean=new LogType();
			bean.setId(e.attributeValue("id").toString());
			bean.setTypeText(e.getTextTrim().toString());
			list.add(bean);
		}
		return list;
	}
	/**
	 * �޸�����һ��Ԫ�ص�����
	 * @param xml
	 * @param eName
	 * @return
	 */
	public static int updateXml(XmlBean xml,String eName) {
		int returnValue = 0;
		Document doc = LoadXML.init(TSC_FILENAME);
		try {
			Iterator it = doc.getRootElement().elementIterator();
			while(it.hasNext()){
				Element e=(Element)it.next();
				if(null!=eName&&e.getName().toString().equals(eName)){
					e.setName(xml.geteName());
					e.addAttribute("etype",xml.geteType().toString());
					e.addAttribute("ecomment", xml.geteComment().toString());
					e.setText(xml.geteValue());
					
					// ָ���˸�ʽ���ķ�ʽΪ����ʽ����ǽ���ʽ
					OutputFormat format = OutputFormat.createPrettyPrint();
					// ָ������
					format.setEncoding("GB2312");
					XMLWriter writer = new XMLWriter(new FileWriter(new File(TSC_FILENAME)), format);
					writer.write(doc);
					writer.close();
					returnValue=1;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnValue;
	}
}
