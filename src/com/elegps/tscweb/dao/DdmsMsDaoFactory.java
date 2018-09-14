package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;

public interface DdmsMsDaoFactory
{
	
	 /**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param ddmsid
	 * @param msid
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
    Integer findDdmsMs_SearchCount(Session sess,String ddmsid,String msid,String agent,String ep);	
    
    /**
     * ����ddmsms����������Ϣ��¼�б�(�����û�)
	 */
	List findDdms_Info(Session sess);
	
	
	 /**
     * ����ddmsms����������Ϣ��¼�б�(�ǵ����û�)
	 */
	List findMs_Info(Session sess);
	
	
	
	/**
	    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    * @param pageNo
	    * @param pageSize
	    * @param ddmsid
	    * @param msid
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
     List findDdmsMs_SearchByPage(Session sess,int pageNo,int pageSize,String ddmsid,String msid,String agent,String ep);
     
     /**
      * ����
      * @param sess
      * @param ddms_id
      * @param ms_id
      * @return
      */
     TbDdmsMsListInfo get(Session sess , String ddms_id,String ms_id); 
     
     /**
      * ����
      * @param sess
      * @param ddmsms
      */
     String save(Session sess , TbDdmsMsListInfo ddmsms);
     
     
     /**
      * ɾ��
      * @param sess
     * @param tbddmsmsinfo 
      * @param 
      */
     void delete(Session sess, TbDdmsMsListInfo tbddmsmsinfo);
     
     
 	/**
 	 * ȡ��(ms)���е����û���Ϣ
 	 * @param sess
 	 * @return
 	 */
 	List findinDdms_Info(Session sess);
 	
	/**
 	 * ȡ��(ms)���зǵ����û���Ϣ
 	 * @param sess
 	 * @return
 	 */
 	List findnotDdms_Info (Session sess);
 	
 	
	/**����ms_idɾ����¼
	 * @param ms_id �ն��û�ID
	 */
	void deletems_id(Session sess,String ms_id);
	/**
	 *  ���ݵ����û�ID��ѯ�Ѿ����ڵķǵ����û� zr
	 * @param sess
	 * @param ddmsid
	 * @return
	 */
	List findAllms_bymsid(Session sess, String ddmsid);
	/**
	 * ���ݵ����û�ID��ѯ����û�еķǵ����û� zr
	 * @param sess
	 * @param ddmsid
	 * @return
	 */
	List findms_notinddms(Session sess, String ddmsid);
	/**
	 *  ɾ�������û�ID�£��������ڵ��ն��û� zr
	 */
	void deleteexistms(Session sess, String ddms_id);
	
}