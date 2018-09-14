package com.elegps.tscweb.tscconfig;

import java.io.File;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

/**
 * 加载xml文件
 * 
 * @author LuYun
 * 
 */
public class LoadXML {

	private static Logger consoleLogger = Logger.getLogger(LoadXML.class);

	private static Document doc = null;

	/**
	 * 初始化xml文件
	 * 
	 * @param pathname
	 */
	public static Document init(String filename) {
		try {
			File file = new File(filename);
			SAXReader sr = new SAXReader();

			if (file.exists()) {
				doc = sr.read(file);
				consoleLogger.debug(file.getName().toString() + "初始化成功!");
				return doc;
			} else {
				consoleLogger.debug(file.getName().toString() + "文件不存在!");
			}
		} catch (Exception e) {
			consoleLogger.error(filename + "文件初始化失败!");
			e.printStackTrace();
		}
		return null;
	}
}
