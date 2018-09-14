package com.elegps.tscweb.dao;

import org.hibernate.Session;


import com.elegps.tscweb.model.TbMenuInfo;


import java.util.List;


public interface MenuDaoFactory
{
	
	 /**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param menu_name
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
    Integer findMenu_SearchCount(Session sess,String menu_name);
    
    
    /**
	    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    * @param pageNo
	    * @param pageSize
	    * @param menu_name
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
  List findMenuInfo_SearchByPage(Session sess,int pageNo,int pageSize,String menu_name);
 
  
    /**
	 * �����������ز˵���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param menu_id ��Ҫ���ز˵�id
	 */
  TbMenuInfo get(Session sess , String menu_id);  
  
  /**
	 * ���ݲ˵������ز˵���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param menu_name ��Ҫ���ز˵���
	 */
  TbMenuInfo get_byname(Session sess , String menu_name);  
  
  
  /**
	 * ���ݲ˵������ز˵���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @parammenu_id  ��Ҫ���ز˵�id
	 * @param menu_name  ��Ҫ���ز˵���
	 */
 TbMenuInfo get_byname(Session sess ,String menu_id, String menu_name);  
  
  /**
   * ɾ������
   * @param sess
   * @param m
   */
  void delete(Session sess,String m);
  
  /**
   * 
   * @return ���в˵���Ϣ
   */
  List findAllMenuInfo(Session sess);
  
  
  /**
	 * ���ڽ�ɫ��˵���ϵ�������ʱ(ȡ�ø�roleidû����ӵĲ˵���Ϣ)
	 * @param sess
	 * @param roleid
	 * @return
	 */
	List findMenu_Info_byroleid(Session sess,String roleid);
}