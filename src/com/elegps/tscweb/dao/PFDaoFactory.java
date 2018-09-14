package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;
import com.elegps.tscweb.model.TbPFInfo;

public interface PFDaoFactory {
	
	/**
	 * 添加套餐信息
	 * @param sess
	 * @param pfInfo
	 */
	void addPFInfo(Session sess,TbPFInfo pfInfo);
	
	/**
	 * 
	 * @return 所有套餐信息
	 */
	List<TbPFInfo> findAllPFInfo(Session sess); 
	
	/**
	 *  修改套餐信息
	 * @param sess
	 * @param pfInfo
	 */
	void updatePFInfo(Session sess,TbPFInfo pfInfo);
	
	/**
	 * id查找套餐信息
	 * @param sess,pfId
	 * @return
	 */
	TbPFInfo findPFInfo(Session sess,int pfId);
	
	/**
	 * 删除代码信息
	 * @param sess
	 * @param pfInfo
	 */
	void deletePFInfo(Session sess,TbPFInfo pfInfo);
	
	
	/**
	 * 通过金额查询
	 * @param sess
	 * @param how
	 * @return
	 */
	TbPFInfo findPFinfoByHow(Session sess,Double how);
}
