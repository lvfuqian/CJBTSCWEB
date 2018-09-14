package com.elegps.tscweb.tscconfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 获得指定文件的系统路径
 * 
 * @author LuYun
 * 
 */
public class PathUtils {
	private static PathUtils instance;

	public static PathUtils getInstance() {
		synchronized (PathUtils.class) {
			if (null == instance) {
				instance = new PathUtils();
			}
		}
		return instance;
	}

	// 获得app类型的路径
	public String getAppPath() {
		String[] s = getWebinfoPath().split("/");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length - 1; i++) {
			sb.append(s[i] + "/");
		}
		return sb.toString();
	}

	// 获得web类型的路径
	public String getWebinfoPath() {
		String[] s = getClassPath().split("/");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length - 1; i++) {
			sb.append(s[i] + "/");
		}
		return sb.toString();
	}

	public String getClassPath() {

		PathUtils j = new PathUtils();
		String t = j.getRealPath();

		if (isWindowsOS()) {
			t = t.replaceAll("file:/", ""); // windows
		} else {
			t = t.replaceAll("file:", ""); // linux,unix
		}
		t = t.replaceAll("wsjar:", ""); // websphere wsjar: has to at jar:
		t = t.replaceAll("jar:", ""); // tomcat,jboss,resin,wasce,apusic
		t = t.replaceAll("zip:", ""); // weblogic
		t = t.replaceAll("/./ ", "/ "); // weblogic

		if (t.indexOf("/lib/") > -1) {
			t = t.split("/lib/")[0] + "/classes/";
		} else {
			t = t.split("/classes/")[0] + "/classes/";
		}
		return t;
	}

	private String getRealPath() {
		return getClass().getProtectionDomain().getCodeSource().getLocation()
				.toString();

	}

	private boolean isWindowsOS() {
		String os = System.getProperty("os.name");
		if (os != null && os.toLowerCase().indexOf("windows") > -1) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(new PathUtils().getWebinfoPath());
	}

	/**
	 * properties字符串转成properties对象
	 * 
	 * @param p_args
	 * @param args
	 * @throws IOException
	 */
	public static Properties stringToProperties(String p_args) {
		if (StringUtils.isEmpty(p_args))
			return null;

		BufferedReader reader = new BufferedReader(new StringReader(p_args));
		try {
			Properties args = new Properties();
			do {
				String line = reader.readLine();
				if (line == null)
					break;
				try {
					int idx = line.indexOf('=');
					if (idx > 0) {
						String key = line.substring(0, idx);
						String value = line.substring(idx + 1);
						args.put(key, value);
					}
				} catch (Exception e) {
				}
			} while (true);

			return args;
		} catch (IOException e) {
			return null;
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}

	/**
	 * 加载properties资源文件
	 * 
	 * @param loader
	 * @param resource
	 * @return
	 */
	public static Properties loadFromResource(Class loader, String resource) {
		InputStream in = null;
		BufferedReader reader = null;
		try {
			in = loader.getResourceAsStream(resource);
			reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			return stringToProperties(IOUtils.toString(reader));
		} catch (Exception excp) {
			throw new RuntimeException(excp);
		} finally {
			IOUtils.closeQuietly(in);
			reader = null;
		}
	}
}
