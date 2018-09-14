package com.elegps.tscweb.comm;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.elegps.tscweb.model.TbAdvInfo;

@SuppressWarnings("serial")
public class UpLoadFileUtil extends HttpServlet{

    private static File filePath ;    // 文件存放目录  
    private static File tempPath ;    // 临时文件目录  

	
	  public static TbAdvInfo uploadHeadShow(HttpServletRequest request, HttpServletResponse response,String advFile) throws IOException{
		  filePath = new File(request.getSession().getServletContext().getRealPath("/")+advFile+"/");
		  if(!filePath.exists()){
			  filePath.mkdirs();
		  }
		  tempPath = new File(request.getSession().getServletContext().getRealPath("/")+advFile+"temp"+"/");
		  if(!tempPath.exists()){
			  tempPath.mkdirs();
		  }
		  	final long MAX_SIZE = 2 * 1024 * 1024;// 设置上传文件最大为 3M
			// 允许上传的文件格式的列表
			final String[] allowedExt = new String[] { "jpg", "jpeg", "gif", "bmp",
					"png" };

			// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
			dfif.setRepository(tempPath);// 设置存放临时文件的目录,web根目录下的temp目录

			// 用以上工厂实例化上传组件
			ServletFileUpload sfu = new ServletFileUpload(dfif);
			// 设置最大上传尺寸
			sfu.setSizeMax(MAX_SIZE);

			PrintWriter out = response.getWriter();
			// 从request得到 所有 上传域的列表
			List fileList = null;
			try {
				fileList = sfu.parseRequest(request);
			} catch (FileUploadException e) {// 处理文件尺寸过大异常
				if (e instanceof SizeLimitExceededException) {
					return null;
				}
				e.printStackTrace();
			}
			// 没有文件上传
			if (fileList == null || fileList.size() == 0) {
				return null;
			}
			// 得到所有上传的文件
			Iterator fileItr = fileList.iterator();
			
			TbAdvInfo advInfo = new TbAdvInfo();
			advInfo.setCreatTime(new Date());
			StringBuffer allPath = new StringBuffer();
			// 循环处理所有文件
			int i = 0;
			while (fileItr.hasNext()) {
				FileItem fileItem = null;
				String path = null;
				long size = 0;
				// 得到当前文件
				fileItem = (FileItem) fileItr.next();
				// 非文件流处理，非file数据
				if (fileItem == null || fileItem.isFormField()) {
					if("advTitle".equals(fileItem.getFieldName())){
						String val =new String(fileItem.getString().getBytes("ISO-8859-1"),"UTF-8");
						if(val.equals(null) || val.equals("")){
							return null;
						}
						advInfo.setAdvTitle(val);
						continue;
					}
					if("advContent".equals(fileItem.getFieldName())){
						String val =new String(fileItem.getString().getBytes("ISO-8859-1"),"UTF-8");
						if(val.equals(null) || val.equals("")){
							return null;
						}
						advInfo.setAdvContent(val);
						continue;
					}
					if("sendSTime".equals(fileItem.getFieldName())){
						
						String val =fileItem.getString();
						if(val.equals(null) || val.equals("")){
							return null;
						}
						Date ss = null;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						try {
							ss = sdf.parse(val);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						advInfo.setSendSTime(ss);
						continue;
					}
					if("sendETime".equals(fileItem.getFieldName())){
					
						String val =fileItem.getString();
						if(val.equals(null) || val.equals("")){
							return null;
						}
						Date ss = null;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						try {
							ss = sdf.parse(val);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						advInfo.setSendETime(ss);
						continue;
					}
					if("advUrl".equals(fileItem.getFieldName())){
						String val =new String(fileItem.getString().getBytes("ISO-8859-1"),"UTF-8");
						advInfo.setAdvUrl(val);
						continue;
					}
					break;
				}
				// 得到文件的完整路径
				path = fileItem.getName();
				// 得到文件的大小
				size = fileItem.getSize();
				if ("".equals(path) || size == 0) {
					continue;
				}

				// 得到去除路径的文件名
				String t_name = path.substring(path.lastIndexOf("\\") + 1);
				// 得到文件的扩展名(无扩展名时将得到全名)
				String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
				// 拒绝接受规定文件格式之外的文件类型
				int allowFlag = 0;
				int allowedExtCount = allowedExt.length;
				for (; allowFlag < allowedExtCount; allowFlag++) {
					if (allowedExt[allowFlag].equals(t_ext))
						break;
				}
				if (allowFlag == allowedExtCount) {
					out.println("请上传以下类型的文件<p />");
					for (allowFlag = 0; allowFlag < allowedExtCount; allowFlag++)
						out.println("*." + allowedExt[allowFlag]
								+ "&nbsp;&nbsp;&nbsp;");
					return null;
				}
				
				long now = System.currentTimeMillis()+i;
				i++;
				// 根据系统时间生成上传后保存的文件名
				String prefix =String.valueOf(now);
				// 保存的最终文件完整路径,保存在web根目录下的ImagesUploaded目录下
				String u_name =
//						+request.getParameter("phone") +"/"
						prefix + "." + t_ext;
				try {
					// 保存文件
					fileItem.write(new File(filePath,u_name));
					System.out.println("文件上传成功. 已保存为: " + prefix + "." + t_ext
							+ "文件大小: " + size + "字节");
					allPath.append(advFile+"/"+u_name+"|");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(!allPath.toString().equals(null)&&!allPath.toString().equals("")){
				String pic = allPath.toString();
				advInfo.setPicUrl(pic.substring(0, pic.length()-1));
			}
	        return advInfo;
	    }
	  
	  
	  
}
