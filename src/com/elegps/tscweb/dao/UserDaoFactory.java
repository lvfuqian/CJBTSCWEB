package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbUserInfo;

import java.util.List;

public interface UserDaoFactory {

	/**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * 
	 * @param user_name
	 * @return ��ѯ����ȡ�ü�¼����
	 */
	Integer findUser_SearchCount(Session sess, String user_name,int agent_id);

	/**
	 * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param user_name
	 * @return ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 */
	List findUserInfo_SearchByPage(Session sess, int pageNo, int pageSize,
			String user_name,int agent_id);

	/**
	 * �����û���������Ϣ 
	 * @param sess    �־û���������Ҫ��Hiberate Session
	 * @param user_name   ��Ҫ�����û���
	 */
	TbUserInfo get_byname(Session sess, String user_name);
	
	
	/**
	 * �����û���������Ϣ
	 * @param sess
	 * @param user_id
	 * @param user_name
	 * @return
	 */
	TbUserInfo get_byname(Session sess, String user_id, String user_name);
	
	
	 /**
	 * �������������û���Ϣ
	 * @param sess �־û���������Ҫ��Hiberate Session
	 * @param user_id ��Ҫ�����û���Ϣ
	 */
	TbUserInfo get(Session sess , String user_id);  
	
	/**
	   * ɾ������
	   * @param sess
	   * @param m
	   */
    void delete(Session sess,TbUserInfo m);
    
    /**
     * 
     * @return �����û���Ϣ
     */
    List findAllUserInfo(Session sess);
    
    /**
     * 
     * @return ����û�з����ɫ�û���Ϣ
     */
    List findNotUserInfo(Session sess);
    
    /**
     * 
     * @return  �������д�����
     */
     
    List getAllAgentInfo(Session sess);
    
    
	/**
	 * 2009-11-20
	 * �����û���,��������û���Ϣ
	 * @param sess
	 * @param name
	 * @param password
	 * @return
	 */
	TbUserInfo get_User(Session sess, String name);
	
	/**
     * �޸���Ϣ
     * @param sess
     * @param user
     */
    void updateInfo(Session sess,TbUserInfo user);
}