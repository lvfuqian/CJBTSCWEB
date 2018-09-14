package com.elegps.tscweb.serivce;

import java.util.List;
import com.elegps.tscweb.model.TbCheckMInfo;
import com.elegps.tscweb.model.TbMoneyLog;

/**
 * 充值
 * @author wanglei
 * 2015-4-2
 */
public interface MoneyManager {
	
	/**
	 * 添加充值记录log
	 * @param mLog
	 * @return Boolean
	 */
	Boolean addMoneyLog(TbMoneyLog  mLog);
	
	/**
	 * 条件查询审核数量
	 * @param userName
	 * @param resName
	 * @param proStatus
	 * @param roleId
	 * @return
	 */
	Integer findCMCount(String userName,String resName,int proStatus, int roleId,int uId,int cRole);
	
	/**
	 * 条件查询审核信息
	 * @param pageNo
	 * @param pageSize
	 * @param userName
	 * @param resName
	 * @param proStatus
	 * @param roleId
	 * @return
	 */
	List<TbCheckMInfo> findAllCMInfo(int pageNo,int pageSize,String userName,String resName,int proStatus, int roleId,int uId,int cRole); 
	
	/**
	 * 提交充值申请
	 * @param check
	 * @return
	 */
	Boolean addCheck(TbCheckMInfo check);
	/**
	 *修改充值申请（审核通过）
	 * @param check
	 * @return
	 */
	Boolean updateCheck(TbCheckMInfo check);
	
	/**
	 * id查询审核信息
	 * @param finId
	 * @return
	 */
	TbCheckMInfo getCMInfoById(int finId);

	/**
	 * 查询充值记录数量
	 * @param sess
	 * @param roleId
	 * @param uId
	 * @return
	 */
	Integer getMoneyLogCount(int roleId,int uId,String userName,String dateStart,String dateEnd,int ptype,String pay_num,String teadeNo,String orderNo);
	/**
	 * 分页查询充值记录
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param roleId
	 * @param uId
	 * @return
	 */
	List getMoneyLogListByPage(int pageNo, int pageSize,int roleId,int uId,String userName,String dateStart,String dateEnd,int ptype,String pay_num,String teadeNo,String orderNo);
}
