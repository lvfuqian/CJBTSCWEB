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

public interface RoleManager {
	/**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * 
	 * @param role_name
	 * @return ��ѯ����ȡ�ü�¼����
	 */
	int getRole_SearchCount(String role_name);

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
	 * @param role_name
	 * @return ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 */
	List<TbRoleInfo> getTbRoleInfoby_name(int pageNo, int pageSize,
			String role_name);

	/**
	 * ����menu_id�б� ɾ����¼
	 * 
	 * @param role_id
	 */
	Boolean deleteRole(String[] role_id);
	
	
	/**
	 * ����һ����Ϣ
	 * @param role_name
	 * @return
	 */
	String createRole(String role_name);
	
	
	/**
	 * �����ض�ָ��������ɫ��Ϣ
	 * @param role_id ��ɫID
	 */
	TbRoleInfo getRoleInfoby_roleid(String role_id);
	
	
	/**����role_id���¼�¼�б�
	 * ���¼�¼�б�
	 * @param role_id
	 * @param role_name
	 */
	String modifyRole(String role_id,String role_name);
	
	/**
	 * 
	 * @return ���еĽ�ɫ��Ϣ
	 */
	List<TbRoleInfo> getAllRole_Info();
}