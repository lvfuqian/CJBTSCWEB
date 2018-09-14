package com.hstapi.util.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostBCE {
	
	public static String postApache(String url, String param) throws Exception {
		 URL orderUrl = new URL(url);
		 HttpURLConnection conn = (HttpURLConnection) orderUrl.openConnection();
		 conn.setConnectTimeout(300000); // 设置连接主机超时（单位：毫秒)
		 conn.setReadTimeout(300000); // 设置从主机读取数据超时（单位：毫秒)
		 conn.setDoOutput(true); // post请求参数要放在http正文内，顾设置成true，默认是false
		 conn.setDoInput(true); // 设置是否从httpUrlConnection读入，默认情况下是true
		 conn.setUseCaches(false); // Post 请求不能使用缓存
		 // 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
		 conn.setRequestProperty("accept", "*/*");
		 conn.setRequestProperty("connection", "Keep-Alive");
		 conn.setRequestMethod("POST");
		 conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		 conn.setRequestProperty("charset", "utf-8");
		 //conn.connect();
		 DataOutputStream out = new DataOutputStream(conn.getOutputStream());  
		 if (param != null && !param.trim().equals("")) {
			 out.writeBytes(param);
			 out.flush();
		 }
		 out.close();
		 String result = getOut(conn);
		 System.out.println("result=========返回的json============="+result);
		 return result;
		 
	}
	
	public static String getOut(HttpURLConnection conn) throws IOException{
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            return null;
        }
        // 获取响应内容体
        BufferedReader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String line = "";
        StringBuffer strBuf = new StringBuffer();
        while ((line = in.readLine()) != null) {
            strBuf.append(line).append("\n");
        }
        in.close();
        return  strBuf.toString().trim();
}

}
