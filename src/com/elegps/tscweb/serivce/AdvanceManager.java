package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.TbORGAdvanceInfo;




public interface AdvanceManager 
{ 
	
	/**
	 * ���ݲ�ѯ��������������,��ҵ����ȡ�ü�¼����
	 * @param agent,ep
	 * @return ��ѯ����ȡ�ü�¼����
	 */
	int getAdvance_SearchCount(String pagentid,String childagentid,String ep,String advancereulst);
	
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
	 *  ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 * @param pageNo
	 * @param pageSize
	 * @param agent
	 * @param ep
	 * @return
	 */
	 List getAdvance_SearchByPage(int pageNo,int pageSize,String pagentid,String childagentid,String ep,String advanceresult);
	 
	 
     /**
      * ����Ԥ��ֵ��Ϣ
      * @param orgtype
      * @param orgid
      * @param advance
      * @param advanename
      * @return
      */
	 String createAdvance(String orgtype, String orgid, String advance,String advanename);
	 
	 
	 /**
	  * ����Ԥ��ֵid��ѯԤ��ֵ��Ϣ
	  * @param advanceid
	  * @return
	  */
	 TbORGAdvanceInfo getTbORGAdvanceInfo_byadvancetid(String advanceid);
	 
	 /**
	  * �޸�Ԥ��ֵ��Ϣ
	  * @param advanceid
	  * @param advance
	  * @return
	  */
	 String modifyAdvanece(String advanceid, String advance);
	 
	 /**
	  * ɾ��Ԥ��ֵ��Ϣ
	  * @param advanceidlist
	  * @return
	  */
	 Boolean deleteAdvance(String[] advanceidlist);
	 
	 /**
	  * ��֤
	  * @param advanceid
	  * @param aresult
	  * @param remark
	  * @param validatename
	  * @return
	  */
	 String validate(String advanceid,String aresult,String remark,String validatename);
	 
	 /**
	  * ��ѯ��ֵ��ʷ��¼��Ϣ����
	  * @param agent
	  * @param ep
	  * @return
	  */
	 int getCharge_SearchCount(String pagentid,String childagentid,String ep);
	 
	 
	 /**
	  * ��ѯ��ֵ��ʷ��¼��Ϣ
	  * @param pageNo
	  * @param pageSize
	  * @param agent
	  * @param ep
	  * @return
	  */
	 List getCharge_SearchByPage(int pageNo,int pageSize,String pagentid,String childagentid,String ep);
}

