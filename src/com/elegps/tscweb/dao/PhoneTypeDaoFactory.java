package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;
import com.elegps.tscweb.model.TbPhoneTypeInfo;

public interface PhoneTypeDaoFactory {
	
	/**
	 * 添加型号信息
	 * @param sess
	 * @param pfInfo
	 */
	void addPTInfo(Session sess,TbPhoneTypeInfo ptInfo);
	
	/**
	 * 修改型号信息
	 * @param ptInfo
	 * @return
	 */
	void updatePt(Session sess,TbPhoneTypeInfo ptInfo);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TbPhoneTypeInfo findOneById(Session sess,int id);
	
	
	/**
	 * 根据型号查询
	 * @param sess
	 * @param type
	 * @return
	 */
	TbPhoneTypeInfo findOneByTypeAndFlag(Session sess,String type,int flag);
	
	/**
	 * 
	 * @return 所有型号信息
	 */
	String findAllPTInfo(Session sess); 
	
	/**
	 * 删除型号信息
	 * @param sess
	 * @param pfInfo
	 */
	void deletePTInfo(Session sess,TbPhoneTypeInfo ptInfo);
	
	/**
	 * 获取型号所有数量
	 * @return
	 */
	int getPtCount(Session sess,String type);
	
	/**
	 * 分页获取型号信息列表
	 * @param type
	 * @return
	 */
	List<TbPhoneTypeInfo> getPtList(Session sess,int pageNo,int pageSize,String type);
}
