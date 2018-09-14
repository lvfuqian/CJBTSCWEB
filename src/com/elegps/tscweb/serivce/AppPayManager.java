package com.elegps.tscweb.serivce;

import java.util.List;
import java.util.Map;
import com.elegps.tscweb.model.TbAppPayInfo;
import com.elegps.tscweb.model.TbKoFeiLogInfo;
import com.elegps.tscweb.model.TbMsInfo;




public interface AppPayManager 
{ 
	/**
	 * ��ӳ�ֵ��Ϣ
	 * @param sess
	 * @param checkInfo
	 */
	String addAPInfo(TbAppPayInfo apInfo);
	
	/**
	 * 
	 * @return ��ѯ���г�ֵ������Ϣ
	 */
	List<TbAppPayInfo> findAllApInfo(); 
	
	/**
	 * ������ѯ���г�ֵ��Ϣ(��ҳ��ʾ)
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @return list
	 */
	List<TbAppPayInfo> findAllApInfo(int pageNo,int pageSize); 
	
	/**
	 * ������ѯ ������
	 * @param sess
	 * @return count
	 */
	Integer findApCount();
	
	/**
	 *  ���³�ֵ��Ϣ
	 * @param sess
	 * @param apInfo
	 */
	Boolean updateApInfo(TbAppPayInfo apInfo);
	
	/**
	 * id���ҳ�ֵ��Ϣ
	 * @param sess,Id
	 * @return
	 */
	TbAppPayInfo findApInfoById(String id);
	
	/**
	 * ������������������ҳ��ѯ��
	 * @return
	 */
	Integer findApCountByPage(String phonems,int payType);
	/**
	 * ����ʱ�����������
	 * @return
	 */
	Map<String,String> findApCountByTime(String msid,String startTime,String endTime);
	/**
	 * ���ҳ�ֵ��Ϣ��������ҳ��ѯ��
	 * @param ,Id
	 * @return
	 */
	List<TbAppPayInfo> findApInfoByPage(int pageNo,int pageSize,String phonems,int payType);
	
	/**
	 * ɾ����ֵ��Ϣ
	 * @param sess
	 * @param apInfo
	 */
	Boolean deleteApInfo(String[] id);
	
	/**
	 * ɾ����ֵ��Ϣ
	 * @param sess
	 * @param apInfo
	 */
	Boolean deleteApInfo(String id);
	
	/**
	 * ��ѯ�۷Ѽ�¼��
	 * @param sess
	 * @param msid
	 * @param type
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Integer findKFCountByPage(String pagentid,String childagentid,String ep,
			String msid,int type,String startTime,String endTime,String imsi);
	/**
	 * ��ҳ��ѯ�۷Ѽ�¼
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param msid
	 * @param type
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<TbKoFeiLogInfo> findKFListInfo_sertchByPage(int pageNo, int pageSize,
			String pagentid,String childagentid,String ep,
			String msid,int type,String startTime,String endTime,String imsi);
	
	/**
	 * ��ֵ��Ϣ��¼���������
	 * @param apInfo
	 * @param msinfo
	 * @return
	 */
	boolean appPay(TbAppPayInfo apInfo,TbMsInfo msinfo);
	
	Map<String,String> findKFByTime(String msid,String startTime, String endTime);
}