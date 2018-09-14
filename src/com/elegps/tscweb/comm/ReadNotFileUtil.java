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

			// ʵ����һ��Ӳ���ļ�����,���������ϴ����ServletFileUpload
			DiskFileItemFactory dfif = new DiskFileItemFactory();

			// �����Ϲ���ʵ�����ϴ����
			ServletFileUpload sfu = new ServletFileUpload(dfif);

			@SuppressWarnings("unused")
			PrintWriter out = response.getWriter();
			// ��request�õ� ���� �ϴ�����б�
			List fileList = null;
			try {
				fileList = sfu.parseRequest(request);
			} catch (FileUploadException e) {// �����ļ��ߴ�����쳣
				if (e instanceof SizeLimitExceededException) {
					return "tooMax";
				}
				e.printStackTrace();
			}
			// û���ļ��ϴ�
			if (fileList == null || fileList.size() == 0) {
				return "noFile";
			}
			// �õ������ϴ����ļ�
			Iterator fileItr = fileList.iterator();
			// ѭ�����������ļ�
			String val = null;
			while (fileItr.hasNext()) {
				FileItem fileItem = null;
				// �õ���ǰ�ļ�
				fileItem = (FileItem) fileItr.next();
				// ���ļ���������file����
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
