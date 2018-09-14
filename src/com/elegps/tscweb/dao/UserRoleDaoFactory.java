package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbRoleMenuInfo;
import com.elegps.tscweb.model.TbRoleUserInfo;


import java.util.List;

public interface UserRoleDaoFactory
{
	
	 /**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param user_id
	 * @param role_id
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
    Integer findUserRole_SearchCount(Session sess,String user_id,String role_id);
    
    
    /**
	    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    * @param pageNo
	    * @param pageSize
	    * @param user_id
	    * @param role_id
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
    List findUserRole_SearchByPage(Session sess,int pageNo,int pageSize,String user_id,String role_id);
    
    
    
    /**
     * �����û�ID�ͽ�ɫID
     * @param sess
     * @param role_id
     * @param menu_id
     * @return
     */
    TbRoleUserInfo get_ByUsRoId(Session sess,String user_id,String role_id);
    
    
    /**
     * ��������������Ϣ
     * @param sess
     * @param userrole_id
     */
    TbRoleUserInfo get(Session sess , String userrole_id); 
    
    
    /**
     * ɾ������
     * @param sess
     * @param m
     */
    void delete(Session sess,TbRoleUserInfo m);
    
    
    /**
     * �����û�ID(user_id)ɾ���û���ɫ��Ӧ��ϵ
     * @param user_id
     * @return
     */
    void deleteUser(Session sess,String user_id);
    
    /**
     * ���ݽ�ɫID(role_id)ɾ���û���ɫ��Ӧ��ϵ
     * @param role_id
     * @return
     */
    void deleteRole(Session sess,String role_id);
    
    
    /**
     * �����û�ID��ѯ��ɫID
     * @param sess
     * @param user_id
     * @return
     */
    TbRoleUserInfo getRoleid_byUserid(Session sess,String user_id); 
	
}