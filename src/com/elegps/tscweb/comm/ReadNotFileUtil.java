package com.elegps.tscweb.comm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@SuppressWarnings("serial")
public class ReadNotFileUtil extends HttpServlet{
	
	  @SuppressWarnings("unchecked")
	public static String getNotFile(HttpServletRequest request, HttpServletResponse response,String fname) throws IOException{

			// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
			DiskFileItemFactory dfif = new DiskFileItemFactory();

			// 用以上工厂实例化上传组件
			ServletFileUpload sfu = new ServletFileUpload(dfif);

			@SuppressWarnings("unused")
			PrintWriter out = response.getWriter();
			// 从request得到 所有 上传域的列表
			List fileList = null;
			try {
				fileList = sfu.parseRequest(request);
			} catch (FileUploadException e) {// 处理文件尺寸过大异常
				if (e instanceof SizeLimitExceededException) {
					return "tooMax";
				}
				e.printStackTrace();
			}
			// 没有文件上传
			if (fileList == null || fileList.size() == 0) {
				return "noFile";
			}
			// 得到所有上传的文件
			Iterator fileItr = fileList.iterator();
			// 循环处理所有文件
			String val = null;
			while (fileItr.hasNext()) {
				FileItem fileItem = null;
				// 得到当前文件
				fileItem = (FileItem) fileItr.next();
				// 非文件流处理，非file数据
				if (fileItem == null || fileItem.isFormField()) {
					if(fname.equals(fileItem.getFieldName())){
						val =new String(fileItem.getString().getBytes("ISO-8859-1"),"UTF-8") ;
						break;
					}
					continue;
				}
			}
	        return val;
	    }
}
