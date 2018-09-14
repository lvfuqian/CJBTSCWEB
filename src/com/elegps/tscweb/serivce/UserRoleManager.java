package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;

public interface UserRoleManager {
	
	/**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param user_id
	 * @param role_id
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
   int getUserRole_SearchCount(String user_id,String role_id);
         
   /**
	 * ����ÿҳ��¼�����ܼ�¼����ȡ��ҳ��
	 * @param count �ܼ�¼��
	 * @param pageSize ÿҳ��ʾ�ļ�¼��
	 * @return ����õ�����ҳ��
	 */
	int getPageCount(int count , int pageSize);
	
	
	/**
	    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    * @param pageNo
	    * @param pageSize
	    * @param user_id
	    * @param role_id
	    * @return ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
	List getUserRole_SearchByPage(int pageNo,int pageSize,String user_id,String role_id);
	
	
	 /**
	    * ����һ���û���ɫ��ϵ��Ϣ
	    * @param user_id
	    * @param role_id
	    * @return
	    */
	   String createUserRoleInfo(String user_id,String role_id);
	   
	   
	   /**
	    * ����userrole_id�б� ɾ����¼
	    * @param userrole_id
	    * @return
	    */
	   Boolean deleteUserRole(String[] userrole_id);
	   
	   
	   /**�����û�id��ѯ���û��ҽ�ɫid
	    * 
	    * @param user_id
	    * @return
	    */
	   String getRoidinfo_ByUserid(String user_id);
	
}