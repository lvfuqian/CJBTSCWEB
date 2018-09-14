package com.elegps.tscweb.serivce;

import java.util.List;
import java.util.Map;
import com.elegps.tscweb.model.TbAppPayInfo;
import com.elegps.tscweb.model.TbKoFeiLogInfo;
import com.elegps.tscweb.model.TbMsInfo;




public interface AppPayManager 
{ 
	/**
	 * 添加充值信息
	 * @param sess
	 * @param checkInfo
	 */
	String addAPInfo(TbAppPayInfo apInfo);
	
	/**
	 * 
	 * @return 查询所有充值申请信息
	 */
	List<TbAppPayInfo> findAllApInfo(); 
	
	/**
	 * 条件查询所有充值信息(分页显示)
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @return list
	 */
	List<TbAppPayInfo> findAllApInfo(int pageNo,int pageSize); 
	
	/**
	 * 条件查询 总数量
	 * @param sess
	 * @return count
	 */
	Integer findApCount();
	
	/**
	 *  更新充值信息
	 * @param sess
	 * @param apInfo
	 */
	Boolean updateApInfo(TbAppPayInfo apInfo);
	
	/**
	 * id查找充值信息
	 * @param sess,Id
	 * @return
	 */
	TbAppPayInfo findApInfoById(String id);
	
	/**
	 * 查找总数量（条件分页查询）
	 * @return
	 */
	Integer findApCountByPage(String phonems,int payType);
	/**
	 * 根据时间查找总数量
	 * @return
	 */
	Map<String,String> findApCountByTime(String msid,String startTime,String endTime);
	/**
	 * 查找充值信息（条件分页查询）
	 * @param ,Id
	 * @return
	 */
	List<TbAppPayInfo> findApInfoByPage(int pageNo,int pageSize,String phonems,int payType);
	
	/**
	 * 删除充值信息
	 * @param sess
	 * @param apInfo
	 */
	Boolean deleteApInfo(String[] id);
	
	/**
	 * 删除充值信息
	 * @param sess
	 * @param apInfo
	 */
	Boolean deleteApInfo(String id);
	
	/**
	 * 查询扣费记录数
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
	List<TbKoFeiLogInfo> findKFListInfo_sertchByPage(int pageNo, int pageSize,
			String pagentid,String childagentid,String ep,
			String msid,int type,String startTime,String endTime,String imsi);
	
	/**
	 * 充值信息记录，更新余额
	 * @param apInfo
	 * @param msinfo
	 * @return
	 */
	boolean appPay(TbAppPayInfo apInfo,TbMsInfo msinfo);
	
	Map<String,String> findKFByTime(String msid,String startTime, String endTime);
}