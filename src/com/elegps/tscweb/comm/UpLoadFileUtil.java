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

    private static File filePath ;    // �ļ����Ŀ¼  
    private static File tempPath ;    // ��ʱ�ļ�Ŀ¼  

	
	  public static TbAdvInfo uploadHeadShow(HttpServletRequest request, HttpServletResponse response,String advFile) throws IOException{
		  filePath = new File(request.getSession().getServletContext().getRealPath("/")+advFile+"/");
		  if(!filePath.exists()){
			  filePath.mkdirs();
		  }
		  tempPath = new File(request.getSession().getServletContext().getRealPath("/")+advFile+"temp"+"/");
		  if(!tempPath.exists()){
			  tempPath.mkdirs();
		  }
		  	final long MAX_SIZE = 2 * 1024 * 1024;// �����ϴ��ļ����Ϊ 3M
			// �����ϴ����ļ���ʽ���б�
			final String[] allowedExt = new String[] { "jpg", "jpeg", "gif", "bmp",
					"png" };

			// ʵ����һ��Ӳ���ļ�����,���������ϴ����ServletFileUpload
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			dfif.setSizeThreshold(4096);// �����ϴ��ļ�ʱ������ʱ����ļ����ڴ��С,������4K.���ڵĲ��ֽ���ʱ����Ӳ��
			dfif.setRepository(tempPath);// ���ô����ʱ�ļ���Ŀ¼,web��Ŀ¼�µ�tempĿ¼

			// �����Ϲ���ʵ�����ϴ����
			ServletFileUpload sfu = new ServletFileUpload(dfif);
			// ��������ϴ��ߴ�
			sfu.setSizeMax(MAX_SIZE);

			PrintWriter out = response.getWriter();
			// ��request�õ� ���� �ϴ�����б�
			List fileList = null;
			try {
				fileList = sfu.parseRequest(request);
			} catch (FileUploadException e) {// �����ļ��ߴ�����쳣
				if (e instanceof SizeLimitExceededException) {
					return null;
				}
				e.printStackTrace();
			}
			// û���ļ��ϴ�
			if (fileList == null || fileList.size() == 0) {
				return null;
			}
			// �õ������ϴ����ļ�
			Iterator fileItr = fileList.iterator();
			
			TbAdvInfo advInfo = new TbAdvInfo();
			advInfo.setCreatTime(new Date());
			StringBuffer allPath = new StringBuffer();
			// ѭ�����������ļ�
			int i = 0;
			while (fileItr.hasNext()) {
				FileItem fileItem = null;
				String path = null;
				long size = 0;
				// �õ���ǰ�ļ�
				fileItem = (FileItem) fileItr.next();
				// ���ļ���������file����
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
				// �õ��ļ�������·��
				path = fileItem.getName();
				// �õ��ļ��Ĵ�С
				size = fileItem.getSize();
				if ("".equals(path) || size == 0) {
					continue;
				}

				// �õ�ȥ��·�����ļ���
				String t_name = path.substring(path.lastIndexOf("\\") + 1);
				// �õ��ļ�����չ��(����չ��ʱ���õ�ȫ��)
				String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
				// �ܾ����ܹ涨�ļ���ʽ֮����ļ�����
				int allowFlag = 0;
				int allowedExtCount = allowedExt.length;
				for (; allowFlag < allowedExtCount; allowFlag++) {
					if (allowedExt[allowFlag].equals(t_ext))
						break;
				}
				if (allowFlag == allowedExtCount) {
					out.println("���ϴ��������͵��ļ�<p />");
					for (allowFlag = 0; allowFlag < allowedExtCount; allowFlag++)
						out.println("*." + allowedExt[allowFlag]
								+ "&nbsp;&nbsp;&nbsp;");
					return null;
				}
				
				long now = System.currentTimeMillis()+i;
				i++;
				// ����ϵͳʱ�������ϴ��󱣴���ļ���
				String prefix =String.valueOf(now);
				// ����������ļ�����·��,������web��Ŀ¼�µ�ImagesUploadedĿ¼��
				String u_name =
//						+request.getParameter("phone") +"/"
						prefix + "." + t_ext;
				try {
					// �����ļ�
					fileItem.write(new File(filePath,u_name));
					System.out.println("�ļ��ϴ��ɹ�. �ѱ���Ϊ: " + prefix + "." + t_ext
							+ "�ļ���С: " + size + "�ֽ�");
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
