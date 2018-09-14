package com.elegps.tscweb.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAppPayInfo;
import com.elegps.tscweb.model.TbKoFeiLogInfo;

/**
 * app充值
 * @author wanglei
 *
 */
public interface AppPayDaoFactory {
	
	/**
	 * 添加充值信息
	 * @param sess
	 * @param checkInfo
	 */
	void addAPInfo(Session sess,TbAppPayInfo apInfo);
	
	/**
	 * 
	 * @return 查询所有充值申请信息
	 */
	List<TbAppPayInfo> findAllApInfo(Session sess); 
	
	/**
	 * 条件查询所有充值信息(分页显示)
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @return list
	 */
	List<TbAppPayInfo> findAllApInfo(Session sess,int pageNo,int pageSize); 
	
	/**
	 * 条件查询 总数量
	 * @param sess
	 * @return count
	 */
	Integer findApCount(Session sess);
	
	/**
	 *  更新充值信息
	 * @param sess
	 * @param apInfo
	 */
	void updateApInfo(Session sess,TbAppPayInfo apInfo);
	
	/**
	 * id查找充值信息
	 * @param sess,Id
	 * @return
	 */
	TbAppPayInfo findApInfoById(Session sess,String id);
	
	/**
	 * 条件查找信息总数量
	 * @param sess
	 * @param phonems
	 * @param payType
	 * @return
	 */
	Integer findApCountByPage(Session sess,String phonems,int payType);
	
	/**
	 * 条件分页查找充值信息
	 * @param sess,
	 * @return
	 */
	List<TbAppPayInfo> findApInfoByPage(Session sess,int pageNo,int pageSize,String phonems,int payType);
	
	/**
	 * 删除充值信息
	 * @param sess
	 * @param apInfo
	 */
	void deleteApInfo(Session sess,TbAppPayInfo apInfo);
	
	
	/**
	 * 查询扣费记录数
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
	List<TbKoFeiLogInfo> findKFListInfo_sertchByPage(Session sess, int pageNo, int pageSize,
			String pagentid,String childagentid,String ep,
			String msid,int type,String startTime,String endTime,String imsi);
	
	
	/**
	 * app查询充值、空费记录
	 * @param sess
	 * @param msid
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Map<String,String> findApCountByTime(Session sess,String msid,String startTime,String endTime);
	Map<String,String> findKFByTime(Session sess,String msid,String startTime,String endTime);
}
