package com.elegps.tscweb.dao;

import com.elegps.tscweb.model.TbPhonekoufeiLog;
import org.hibernate.Session;

import com.elegps.tscweb.model.TbPhonekoufeiLogVo;

import java.util.List;

public interface PhonekoufeiDaoFactory {
	TbPhonekoufeiLogVo findPhonekoufeiLog(Session sess,String msid,String startTime,String endTime);

	/**
	 * 查询扣费记录数
	 * @param sess
	 * @param msid
	 * @param type
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Integer findPKFCountByPage(Session sess,String pagentid,String childagentid,String ep,
							  String msid,int type,String startTime,String endTime,String imsi);
	/**
	 * 分页查询扣费记录
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param msid
	 * @param type
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<TbPhonekoufeiLog> findPKFListInfo_sertchByPage(Session sess, int pageNo, int pageSize,
													   String pagentid, String childagentid, String ep,
													   String msid, int type, String startTime, String endTime, String imsi);
}
