package com.elegps.tscweb.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAppPayInfo;
import com.elegps.tscweb.model.TbKoFeiLogInfo;

/**
 * app��ֵ
 * @author wanglei
 *
 */
public interface AppPayDaoFactory {
	
	/**
	 * ��ӳ�ֵ��Ϣ
	 * @param sess
	 * @param checkInfo
	 */
	void addAPInfo(Session sess,TbAppPayInfo apInfo);
	
	/**
	 * 
	 * @return ��ѯ���г�ֵ������Ϣ
	 */
	List<TbAppPayInfo> findAllApInfo(Session sess); 
	
	/**
	 * ������ѯ���г�ֵ��Ϣ(��ҳ��ʾ)
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @return list
	 */
	List<TbAppPayInfo> findAllApInfo(Session sess,int pageNo,int pageSize); 
	
	/**
	 * ������ѯ ������
	 * @param sess
	 * @return count
	 */
	Integer findApCount(Session sess);
	
	/**
	 *  ���³�ֵ��Ϣ
	 * @param sess
	 * @param apInfo
	 */
	void updateApInfo(Session sess,TbAppPayInfo apInfo);
	
	/**
	 * id���ҳ�ֵ��Ϣ
	 * @param sess,Id
	 * @return
	 */
	TbAppPayInfo findApInfoById(Session sess,String id);
	
	/**
	 * ����������Ϣ������
	 * @param sess
	 * @param phonems
	 * @param payType
	 * @return
	 */
	Integer findApCountByPage(Session sess,String phonems,int payType);
	
	/**
	 * ������ҳ���ҳ�ֵ��Ϣ
	 * @param sess,
	 * @return
	 */
	List<TbAppPayInfo> findApInfoByPage(Session sess,int pageNo,int pageSize,String phonems,int payType);
	
	/**
	 * ɾ����ֵ��Ϣ
	 * @param sess
	 * @param apInfo
	 */
	void deleteApInfo(Session sess,TbAppPayInfo apInfo);
	
	
	/**
	 * ��ѯ�۷Ѽ�¼��
	 * @param sess
	 * @param msid
	 * @param type
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Integer findKFCountByPage(Session sess,String pagentid,String childagentid,String ep,
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
	List<TbKoFeiLogInfo> findKFListInfo_sertchByPage(Session sess, int pageNo, int pageSize,
			String pagentid,String childagentid,String ep,
			String msid,int type,String startTime,String endTime,String imsi);
	
	
	/**
	 * app��ѯ��ֵ���շѼ�¼
	 * @param sess
	 * @param msid
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Map<String,String> findApCountByTime(Session sess,String msid,String startTime,String endTime);
	Map<String,String> findKFByTime(Session sess,String msid,String startTime,String endTime);
}
