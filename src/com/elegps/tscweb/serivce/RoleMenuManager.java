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

public interface RoleMenuManager {
	/**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param role_id
	 * @param menu_id
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
   int getRoleMenu_SearchCount(String role_id,String menu_id);
   
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
	    * @param role_id
	    * @param menu_id
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
	   List getRoleMenu_SearchByPage(int pageNo,int pageSize,String role_id,String menu_id);
	   
	   /**
	    * ����һ��
	    * @param role_id
	    * @param menu_id
	    * @return
	    */
	   String createRoleMenuInfo(String role_id,String menu_id);
	   
	   /**
	    * ����rolemenu_id�б� ɾ����¼
	    * @param rolemenu_id
	    * @return
	    */
	   Boolean deleteRoleMenu(String[] rolemenu_id);
	   
	   /**
	    * ���ݽ�ɫID��ѯ�Ѿ����ڵĲ˵���Ϣ
	    * @param roleid
	    * @return
	    */
	   List  getMenu_info(String roleid);
	   
	   /**
	    * ���ݽ�ɫID������Ӳ˵�
	    * @param roleid
	    * @param menuid
	    * @return
	    */
	  String createRoleMenuInfo_ByRoleid(String roleid,String[] menuid);
	      
}