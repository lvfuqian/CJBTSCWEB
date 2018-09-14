package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;


import java.util.List;

public interface RoleMenuDaoFactory
{
	 /**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param role_id
	 * @param menu_id
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
    Integer findRoleMenu_SearchCount(Session sess,String role_id,String menu_id);
    
    
    /**
	    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    * @param pageNo
	    * @param pageSize
	    * @param role_id
	    * @param menu_id
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
  List findRoleMenu_SearchByPage(Session sess,int pageNo,int pageSize,String role_id,String menu_id);
  
  /**
   * ���ݽ�ɫID�Ͳ˵�ID
   * @param sess
   * @param role_id
   * @param menu_id
   * @return
   */
  TbRoleMenuInfo get_ByRoMeId(Session sess,String role_id,String menu_id);
  
  /**
   * ��������������Ϣ
   * @param sess
   * @param rolemenu_id
   */
  TbRoleMenuInfo get(Session sess , String rolemenu_id); 
  
  
  /**
   * ɾ������
   * @param sess
   * @param m
   */
  void delete(Session sess,TbRoleMenuInfo m);
  
  
  /**
   * ���ݽ�ɫID(role_id)ɾ����ɫ�˵���Ӧ��ϵ
   * @param role_id
   * @return
   */
  void deleteRole(Session sess,String role_id);
  
  
  /**
   * ���ݲ˵�ID(menu_id)ɾ����ɫ�˵���Ӧ��ϵ
   * @param menu_id
   * @return
   */
  void deleteMenu(Session sess,String menu_id);
  
  /**
   * ���ݽ�ɫID��ѯ�Ѿ����ڵĲ˵���Ϣ
   * @param roleid
   * @return
   */
  List findAllmenu_byroleid(Session sess,String roleid);
  
  /**
   * ����
   * @param sess
   * @param rolemenu
   */
  void save(Session sess, TbRoleMenuInfo rolemenu);
	
}