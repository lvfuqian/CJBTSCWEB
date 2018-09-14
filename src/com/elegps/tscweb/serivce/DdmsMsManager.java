package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;




public interface DdmsMsManager 
{ 
	/**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param ddmsid
	 * @param msid
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
   int getDdmsMs_SearchCount(String ddmssid,String msid,String agent,String ep);
   
   /**
	 * ����ÿҳ��¼�����ܼ�¼����ȡ��ҳ��
	 * @param count �ܼ�¼��
	 * @param pageSize ÿҳ��ʾ�ļ�¼��
	 * @return ����õ�����ҳ��
	 */
	int getPageCount(int count , int pageSize);
	
	
	/**����TbDdmsMsListInfo���м�¼�б�ĵ����û�������Ϣ
	 */
	List <String> getAllDdms_Info();
	
	
	/**����TbDdmsMsListInfo���м�¼�б�ķǵ����û�������Ϣ
	 */
	List <TbDdmsMsListInfo> getAllMs_Info();
	
	
	 /**
	    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    * @param pageNo
	    * @param pageSize
	    * @param ddmsid
	    * @param msid
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
	   List <TbDdmsMsListInfo> getDdmsMs_SearchByPage(int pageNo,int pageSize,String ddmsid,String msid,String agent,String ep);
	   
	   
	   
	   /**
		 * ����һ�����ȶ�Ӧ��ϵ��Ϣ
		 * @param ddms_id 
		 * @param ms_id 
		 * @return �´��ն˵�����,�������ʧ�ܣ�����null��
		 */  
		String createDdmsMsInfo(String ddms_id , String ms_id);
		
		
		/**����ddms_id,ms_id ɾ����¼
		 * @param gpsmsidlist
		 */
		Boolean deleteDdmsMs(String[] ddmsmsidlist);
		
		
		/**
		 * ms���е����û�
		 * @return
		 */
		List <TbMsInfo> getAllinDdms_Info();
		
		
		/**
		 * ms���зǵ����û�
		 * @return
		 */
		List <TbMsInfo> getAllnotDdms_Info();
		/**
		 * ���ȹ����������
		 * @return
		 * 
		 */
		 String createDdMsInfo_Byddmsid(String ddmsid,String[] msid);
		 /**
		  * ���ddms_id�·ǵ����û�  zr
		  */
		 List getallms(String ddms_id);
		 /**
		  * ��õ����û��µ�û�еķǵ����û�  zr
		  */
		 List getallms_notbyddmsid(String ddms_id);
}