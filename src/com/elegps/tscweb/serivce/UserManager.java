package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbRoleInfo;
import com.elegps.tscweb.model.TbUserInfo;

public interface UserManager {
	/**
	 * ���ݲ�ѯ����(�û���)ȡ�ü�¼����
	 * 
	 * @param user_name
	 * @return ��ѯ����ȡ�ü�¼����
	 */
	int getUser_SearchCount(String user_name, int agent_id);

	/**
	 * ����ÿҳ��¼�����ܼ�¼����ȡ��ҳ��
	 * 
	 * @param count
	 *            �ܼ�¼��
	 * @param pageSize
	 *            ÿҳ��ʾ�ļ�¼��
	 * @return ����õ�����ҳ��
	 */
	int getPageCount(int count, int pageSize);
	
	
	/**
	 * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 * 
	 * @param user_name
	 * @return ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 */
	List getTbUserInfoby_name(int pageNo, int pageSize,
			String user_name,int agent_id);
	

	/**
	 * ����һ���û���Ϣ
	 * @param user_name
	 * @param psw
	 * @return
	 */
	String createUser(String user_name,String psw,String roleid,String agentid,String epid);
	
	/**
	 * ����user_id�б� ɾ����¼
	 * 
	 * @param user_id
	 */
	Boolean deleteUser(String[] user_id);
	
	
	/**
	 * �����ض�ָ�������û���Ϣ
	 * @param user_id �û�ID
	 */
	TbUserInfo getUserInfoby_userid(String user_id);
	
	
	/**����user_id���¼�¼�б�
	 * ���¼�¼�б�
	 * @param user_id
	 * @param user_name
	 * @param psw
	 */
	String modifyUser(String user_id,String user_name,String psw,String roleid,String agentid,String epid);
	
	
	/**
	 * 
	 * @return ���������û���Ϣ
	 */
	List<TbUserInfo> getAllUser_Info();
	
	
	/**
	 * @return ��������û�з����ɫ�û���Ϣ
	 */
	List<TbUserInfo> getNotUser_Info();
	
	
   /**
    * 
    * @return�������д�����
    */
	List<TbAgentInfo> getAllAgent_Info();
	//�޸��û�����
    Boolean ChangePassword(String name, String psasword);
    
    /**
	 * �޸�user��Ϣ
	 * @param user
	 * @return
	 */
	Boolean updateUser(TbUserInfo user);
	
	TbUserInfo get_User(String name);
}