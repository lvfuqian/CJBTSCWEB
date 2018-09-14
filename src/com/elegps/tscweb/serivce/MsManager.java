package com.elegps.tscweb.serivce;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbMsInfoExt;
import com.elegps.tscweb.model.TbPFInfo;


public interface MsManager 
{ 
	//ÿҳ��ʾ����Ŀ
	int MESSAGE_PAGE_SIZE = 6;
	
	/**
	 * ����һ���ն��û���Ϣ
	 * @param msId �ն˺���
	 * @param msName �ն�����
	 * @param passwd �ն�����
	 * @param msType �ն���������
	 * @param Flag  �ն��û���״̬
	 * @return �´��ն˵�����,�������ʧ�ܣ�����-1��
	 */
	String createMs(String msId ,int mssl, String msName , String passwd,int msType,int Flag,
			String epid,String ms_level,String modid,TbPFInfo package_fee,int ms_df,int call,int mileageas,String memo,
			String c03,String c04,String netWorkType,String msCategory,int pagentid);

	/**
	 * ����һ���ն��û���Ϣ
	 * @param msId �ն˺���
	 * @param msName �ն�����
	 * @param passwd �ն�����
	 * @param msType �ն���������
	 * @param Flag  �ն��û���״̬
	 * @param roleType  �ն��û���ɫ��1��������0����Ա��
	 * @param familyNumbers  �ն��û���������루����ƴ�ӵ��ַ�����
	 * @return �´��ն˵�����,�������ʧ�ܣ�����-1��
	 */
	String createMs(String msId ,int mssl, String msName , String passwd,int msType,int Flag,
					String epid,String ms_level,String modid,TbPFInfo package_fee,int ms_df,int call,int mileageas,String memo,
					String c03,String c04,String netWorkType,String msCategory,int pagentid,String roleType,String familyNumbers);

	/**
	 * �����ض�ҳ�������ն��û���Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ָ��ҳ��ȫ���ն��û���Ϣ
	 */
	List <TbMsInfo> getAllMs_typeByPage(int pageNo,int pagesize);
	
	
	/**
	 * �����ض�ҳ�������ն��û�״̬��Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ָ��ҳ��ȫ���ն��û��û�״̬��Ϣ  ������״̬����
	 */
	List <TbMsInfo> getAllMsOnline_allByPage(int pageNo,int pagesize);
	
	
	/**
	 * �����ض��ն��û�����ҳ�������ն��û���Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  ms_type �ն��û�����
	 * @return ָ���ն��û�����ҳ��ȫ���ն��û���Ϣ
	 */ 
	List <TbMsInfo> getMs_typeByPage(int pageNo,int pagesize,int ms_type);
	
	
	/**
	 * �����ض��ն������û�״̬ҳ�������ն��û���Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @return ָ���ն��û�״̬ҳ��ȫ���û���Ϣ
	 */ 
	List <TbMsInfo> getAllMs_flagByPage(int pageNo,int pagesize);
	
	
	/**
	 * �����ض��ն������û�״̬Ϊflagҳ�������ն��û���Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  ms_type �ն��û�״̬
	 * @return ָ���ն��û�״̬Ϊflagҳ��ȫ���û���Ϣ
	 */ 
	List <TbMsInfo> getMs_flagByPage(int pageNo,int pagesize,int flag);
	
	
	/**
	 * �����ض��ն��û�����ҳ�������ն��û���Ϣ
	 * @param pageNo ָ��ҳ��
	 * @param  pagesize ÿҳ��ʾ������
	 * @param  ms_onlineStatus �ն��û�����״̬
	 * @return ָ���ն��û�����ҳ��ȫ���ն��û���Ϣ
	 */
	List <TbMsInfo> getMs_OnlineByPage(int pageNo,int pagesize,int ms_onlineStatus);
	
	
	/**
	 * ����ms_id��ģ����ѯ
	 * @return �ն��û�ms_id��ģ����ѯҳ��ȫ���ն��û���Ϣ
	 */
	List <TbMsInfo> getTBMsinfoby_msid(int pageNo,int pagesize,String ms_id);
	
	/**
	 * �����ض�ָ�������ն��û���Ϣ
	 * @param msId �ն˺���
	 */
	TbMsInfo getTBMsinfoby_msid(String msid);
	
	
	
	/**
	 * �����ض�ָ�������ն��û���Ϣ
	 * @param msId �ն˺���
	 * @param msType �ն���������
	 * @param Flag  �ն��û���״̬
	 * @return ָ��ҳ��ȫ���ն��û���Ϣ
	 */
	List getTbMsInfo(String msId,int msType,int Flag);
	
	
	
	
	
	/**
	 * ��ȡ�ն��û�������Ϣ����
	 * @param ms_type �ն��û����Ͷ�Ӧ�ܼ�¼��
	 * @return �ն��û�������Ϣ������ getMsCount
	 */
	int getMs_typeCount(int ms_type);
	
	/**
	 * ��ȡ�����ն��û�������Ϣ����
	 * @param ms_type �ն��û����Ͷ�Ӧ�ܼ�¼��
	 * @return �ն��û�������Ϣ������ 
	 */
	int getAllMs_typeCount();
	
	
	/**
	 * ��ȡ�����ն��û�״̬��Ϣ����
	 * �ն��û�״̬��Ӧ�ܼ�¼��
	 * @return �ն��û�״̬��Ϣ������ 
	 */
	int getAllMs_flagCount();
	
	/**
	 * ��ȡ�ն��û�״̬��Ϣ����
	 * @param flag �ն��û�״̬��Ӧ�ܼ�¼��
	 * @return �ն��û�״̬Ϊflag��Ϣ������ 
	 */
	int getMs_flagCount(int flag);
	
	
	/**
	 * ��ȡ�ն��û�����̬��Ϣ����
	 * @param ms_Online �ն��û����Ͷ�Ӧ�ܼ�¼��
	 * @return �ն��û�����̬��Ϣ������
	 */
	int getMs_OnlineCount(int ms_Online);
	
	
	/**
	 * ��ȡ�����ն��û�����̬��Ϣ����
	 * @return �ն��û�����̬��Ϣ������
	 */
	int getAllMsOnline_allCount();
	
	
	/**
	 * ����ms_id��ģ����ѯ
	 * @return �ն��û�ms_id��ģ����ѯ������
	 */
	int getMsms_idCount(String ms_id);
	
	
	
	/**
	 * ����ÿҳ��¼�����ܼ�¼����ȡ��ҳ��
	 * @param count �ܼ�¼��
	 * @param pageSize ÿҳ��ʾ�ļ�¼��
	 * @return ����õ�����ҳ��
	 */
	int getPageCount(int count , int pageSize);
	
	
	/**����ms_id�б�
	 * ɾ����¼
	 * @param msidlist
	 */
	Boolean deleteMs(String[] msidlist);
	
	
	/**����ms_id���¼�¼�б�
	 * ���¼�¼�б�
	 * @param ms_id
	 * @param ms_name
	 * @param ms_type
	 * @param delddms
	 */
	String modifyMs(String ms_id,String ep_id,String ms_name,int ms_type,int ms_flag,int delddms,String modid,String password,int ms_level,TbPFInfo packagefee,int ms_df,int call,int mileageas,String memo,
			String ismobile,String c04,String msCategory,int pagentid,String nwType);
	/**
	 * �޸Ķ�Ӧ��ϵ���¼
	 * @param ms_id
	 * @param SIM_Num
	 * @param deviceNum
	 * @param enterpriseId
	 * @param carPlateColor
	 * @return
	 */
	int updateMsExt(String ms_id,String SIM_Num,String deviceNum,int enterId,int carPlateColor,String newmsId);
	/**
	 * ִ��ָ����hql����޸�
	 * @param hql
	 * @param obj
	 */
	void update (String hql,Object...obj);
	
	/**
	 * �����޸�
	 * @param msList
	 * @return
	 */
	Boolean updateMs(List<TbMsInfo> msList);

	/**
	 * �޸��������
	 * @param info
	 * @return
	 */
	Boolean updateFamilyNumbers(TbMsInfo info);
	
	/**
	 * ��ȡһ������
	 * @param ms_id
	 * @author luyun
	 * @date 2011-03-14
	 */
	TbMsInfoExt getExtById(String ms_id);
	/**
	 * ���������Ƿ����
	 * @param hql
	 * @param obj
	 * @return
	 */
	 public int getMsById(String hql,Object...obj);
	
	/**����ms����������Ϣ(falg=1)��¼�б�
	 */
	List <TbMsInfo> getAllMs_Info(String pagentid,String childagentid,String ep);
	
	
	/**
	 * ����Ⱥ�����ն��û���ϵ�������ʱ(ȡ�ø�grpidû����ӵ��ն���Ϣ)
	 * @param grpid
	 * @return
	 */
	List getAllMs_Info_not_Bygrpid(String grpid,String ep);
	List getAllMs_InfoBygrpid(String grpid,String ep);
	
	List getAllMs_Info_notQF_Bygrpid(String grpid,String ep);
	
	/**
	 * ����GPS���ն˹�ϵ�������ʱ(ȡ�ø�gpsidû����ӵ��ն���Ϣ)
	 * @param gpsid
	 * @return
	 */
	List getAllMs_Info_not_Bygpsid(String ep,String gpsid);


	/**�ն˱��ѯ����
	 * @param user_type  �ն�����
	 * @param statue  ����(����)״̬
	 * @param flag  ��Ч״̬
	 * @param ms_id  �ն˺���
	 * @return �ն��û�ģ����ѯ������
	 */
	int getMs_sertch(String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,String ep,String ismobile,String arrearage);
	
	
	
	/**
	 *@param user_type  �ն�����
	 * @param statue  ����(����)״̬
	 * @param flag  ��Ч״̬
	 * @param ms_id  �ն˺���
	 * @return �ն��û�ms_id��ģ����ѯҳ��ȫ���ն��û���Ϣ
	 */
	List <TbMsInfo> getTBMsinfoby_mspage(int pageNo, int pagesize,
			String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,String ep,String ismobile,String arrearage);
	
	/**
	 * @author wanglei
	 *@param user_type  �ն�����
	 * @param statue  ����(����)״̬
	 * @param flag  ��Ч״̬
	 * @param ms_id  �ն˺���
	 * @return �ն��û�ms_id��ģ����ѯҳ��ȫ���ն��û���Ϣ
	 */
	List <TbMsInfo> getTBMsinfoby_mspage(int pageNo, int pagesize,
			String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,
			String ep,String ismobile,String arrearage,int roleId,String agentid,String epId);

	/**
	 * @author wanglei
	 * id�������msinfo list
	 * @param msidlist
	 * @return
	 */
	List<TbMsInfo> msInfoList(String[] msidlist);
	//	
//	/**
//	 * @author wanglei
//	 *@param user_type  �ն�����
//	 * @param statue  ����(����)״̬
//	 * @param flag  ��Ч״̬
//	 * @param ms_id  �ն˺���
//	 * @return �ն��û�ms_id��ģ����ѯҳ��ȫ���ն��û���Ϣ
//	 */
//	List <TbMsInfo> getTBMsinfoListby_mspage(int pageNo, int pagesize,
//			String user_type,String statue,String flag,String ms_id,String ms_name,String pagentid,String childagentid,String ep,String ismobile,String arrearage);
//	
	
	/**����ms_id�б�
	 * �����¼
	 * @param msidlist
	 */
	Boolean msdj(String[] msidlist);
	
	/**
	 * �����ն�id���޸��ն�����
	 * @param msID
	 * @param pwd
	 * @return
	 */
	Boolean updatePwd(String msID,String pwd);
	/**
	 * �޸��ն�
	 * @param msinfo
	 * @return
	 */
	Boolean update(TbMsInfo msinfo);
	
	/**
	 * ���ݴ�����ID��ѯ�����䵽��ҵ���ն�
	 * @param aid
	 * @return
	 */
	List getMsInfo_byAid(String aid,int msCount);
	
	/**
	 * ����Ҫ��ֵ����ҵ��ѯ�նˣ������������û���
	 * @param ep_id
	 * @return
	 */
	List getMsInfo_byEpid(String ep_id);
	List getMsInfo_byEpid(StringBuffer ep_id);
	List getMsInfo_byEpid(StringBuffer ep_id,String ms);
	
	//-------------------------------�ּ������õ���-----------------------------------
	/**epid����ҵID����grpid(Ⱥ��id) 
	 * 
	 */
	List <TbMsInfo> getNonentityMsinfo_ByGrpid(String epid,String grpid);
	
	public List<TbMsInfo> listMsInfoByEpId(int epid,int mstype,String bool);
	public List<TbMsInfo> listMsInfoByEpId(int epid,int mstype,String bool,String msid);

	String findMsIdByPhone (String phone);

}