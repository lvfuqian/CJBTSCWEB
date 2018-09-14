package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleInfo;


import java.util.List;

public interface RoleDaoFactory
{
	
	 /**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param role_name
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
    Integer findRole_SearchCount(Session sess,String role_name);
    
    
    /**
	    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    * @param pageNo
	    * @param pageSize
	    * @param role_name
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
  List findRoleInfo_SearchByPage(Session sess,int pageNo,int pageSize,String role_name);
 
  
    /**
	 * �����������ؽ�ɫ��Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param role_id ��Ҫ���ؽ�ɫid
	 */
  TbRoleInfo get(Session sess , String role_id);  
  
 
  /**
	 * ���ݽ�ɫ��������Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param role_name ��Ҫ���ؽ�ɫ��
	 * @param role_id ��Ҫ���ؽ�ɫID
	 */
  TbRoleInfo get_byname(Session sess ,String role_id, String role_name);
  
  
  /**
	 * ���ݽ�ɫ��������Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param role_name ��Ҫ���ؽ�ɫ��
	 */
  TbRoleInfo get_byname(Session sess ,String role_name);
  
  /**
   * ɾ������
   * @param sess
   * @param m
   */
  void delete(Session sess,TbRoleInfo m);
  
  
  /**
   * 
   * @return ���н�ɫ��Ϣ
   */
  List findAllRoleInfo(Session sess);
  
	
}