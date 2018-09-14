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

public interface MenuManager {
	/**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * 
	 * @param menu_name
	 * @return ��ѯ����ȡ�ü�¼����
	 */
	int getMenu_SearchCount(String menu_name);

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
	 * @param menu_name
	 * @return ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 */
	List getTbMenuInfoby_name(int pageNo, int pageSize,
			String menu_name);

	/**
	 * ����menu_id�б� ɾ����¼
	 * 
	 * @param grpid_list
	 */
	Boolean deleteMenu(String[] menu_id);
	
	
	/**
	 * ����һ����Ϣ
	 * @param menu_name
	 * @param url
	 * @return
	 */
	String createMenu(String menu_name,String pmenuid,String url);
	
	
	/**
	 * �����ض�ָ�������˵���Ϣ
	 * @param menu_id �˵�ID
	 */
	TbMenuInfo getMenuInfoby_menuid(String menu_id);
	
	
	/**����menu_id���¼�¼�б�
	 * ���¼�¼�б�
	 * @param menu_id
	 * @param meun_name
	 * @param url
	 */
	String modifyMenu(String menu_id,String meun_name, String url,String pmenu_id);
	
	/**
	 * 
	 * @return ���еĲ˵���Ϣ
	 */
	List<TbMenuInfo> getAllMenu_Info();
	
	
	/**
	 * ���ڽ�ɫ��˵���ϵ�������ʱ(ȡ�ø�roleidû����ӵĲ˵�)
	 * @param roleid
	 * @return
	 */
	List getAllMenu_Info_not_Byroleid(String roleid);
}