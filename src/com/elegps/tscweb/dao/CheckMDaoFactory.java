package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbCheckMInfo;
import com.elegps.tscweb.model.TbPFInfo;

/**
 * 充值提交财务审核
 * @author wanglei
 *
 */
public interface CheckMDaoFactory {
	
	/**
	 * 添加充值申请信息
	 * @param sess
	 * @param checkInfo
	 */
	void addCMInfo(Session sess,TbCheckMInfo checkInfo);
	
	/**
	 * 
	 * @return 查询所有充值申请信息
	 */
	List<TbCheckMInfo> findAllCMInfo(Session sess); 
	
	/**
	 * 条件查询所有充值申请信息(分页显示)
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param userName
	 * @param resName
	 * @param proStatus
	 * @param roleId
	 * @return list
	 */
	List<TbCheckMInfo> findAllCMInfo(Session sess,int pageNo,int pageSize,String userName,String resName,int proStatus, int roleId,int uId,int cRole); 
	
	/**
	 * 条件查询 总数量
	 * @param sess
	 * @param userName
	 * @param resName
	 * @param proStatus
	 * @param roleId
	 * @return count
	 */
	Integer findCMCount(Session sess,String userName,String resName,int proStatus, int roleId,int uId,int cRole);
	
	/**
	 *  更新充值申请信息
	 * @param sess
	 * @param checkInfo
	 */
	void updateCMInfo(Session sess,TbCheckMInfo checkInfo);
	
	/**
	 * id查找充值申请信息
	 * @param sess,finId
	 * @return
	 */
	TbCheckMInfo findCMInfoById(Session sess,int finId);
	
	/**
	 * 删除充值申请信息
	 * @param sess
	 * @param checkInfo
	 */
	void deleteCMInfo(Session sess,TbCheckMInfo checkInfo);
}
