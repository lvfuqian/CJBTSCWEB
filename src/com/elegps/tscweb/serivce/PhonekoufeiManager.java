package com.elegps.tscweb.serivce;

import com.elegps.tscweb.model.TbKoFeiLogInfo;
import com.elegps.tscweb.model.TbPhonekoufeiLog;
import com.elegps.tscweb.model.TbPhonekoufeiLogVo;

import java.util.List;

public interface PhonekoufeiManager {
	TbPhonekoufeiLogVo getPhonekoufeiLog(String msid,String startTime,String endTime);

	/**
	 * ��ѯ�۷Ѽ�¼��
	 * @param sess
	 * @param msid
	 * @param type
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Integer findPKFCountByPage(String pagentid,String childagentid,String ep,
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
	List<TbPhonekoufeiLog> findPKFListInfo_sertchByPage(int pageNo, int pageSize,
														String pagentid, String childagentid, String ep,
														String msid, int type, String startTime, String endTime, String imsi);
}
