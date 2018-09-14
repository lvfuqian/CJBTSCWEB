package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbORGAdvanceInfo;

public interface AdvanceDaoFactory
{
	/**
	 * ����ָ��������ָ��ҳ���ļ�¼
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param agent
	 * @param ep
	 * @return
	 */
	 List findAdvance_SearchByPage(Session sess,int pageNo,int pageSize,String pagentid,String childagentid,String ep,String advanceresult);
	 
	 
	 /**
	  * ����������
	  * @param sess
	  * @param agent
	  * @param ep
	  * @return
	  */
    Integer findadvance_Count(Session sess,String pagentid,String childagentid,String ep,String advanceresult);
    
    /**
     * ����Ԥ��ֵ��Ϣ
     * @param sess
     * @param orgadvance
     * @return
     */
    String save(Session sess , TbORGAdvanceInfo orgadvance);
    
   /**
    * ����Ԥ��ֵid��ѯ��¼
    * @param sess
    * @param advanceid
    * @return
    */
    TbORGAdvanceInfo get(Session sess , String advanceid);  //���ﲻ������ֻ��Ϊint,long����Ϊ�ַ���
    
    /**
     * ɾ��Ԥ��ֵ��Ϣ
     * @param sess
     * @param advanceid
     */
    void delete(Session sess,String advanceid);
    
    
    /**
     * ��ֵ��ʷ��¼��
     * @param sess
     * @param agent
     * @param ep
     * @param advanceresult
     * @return
     */
    Integer getCharge_Count(Session sess,String pagentid,String childagentid,String ep);
    
    
    
    /**
     * ��ֵ��ʷ
     * @param sess
     * @param pageNo
     * @param pageSize
     * @param agent
     * @param ep
     * @return
     */
    List getCharge_SearchByPage(Session sess,int pageNo,int pageSize,String pagentid,String childagentid,String ep);
	
}