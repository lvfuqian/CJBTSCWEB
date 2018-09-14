package com.elegps.tscweb.tscconfig;

import java.io.File;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

/**
 * ����xml�ļ�
 * 
 * @author LuYun
 * 
 */
public class LoadXML {

	private static Logger consoleLogger = Logger.getLogger(LoadXML.class);

	private static Document doc = null;

	/**
	 * ��ʼ��xml�ļ�
	 * 
	 * @param pathname
	 */
	public static Document init(String filename) {
		try {
			File file = new File(filename);
			SAXReader sr = new SAXReader();

			if (file.exists()) {
				doc = sr.read(file);
				consoleLogger.debug(file.getName().toString() + "��ʼ���ɹ�!");
				return doc;
			} else {
				consoleLogger.debug(file.getName().toString() + "�ļ�������!");
			}
		} catch (Exception e) {
			consoleLogger.error(filename + "�ļ���ʼ��ʧ��!");
			e.printStackTrace();
		}
		return null;
	}
}
