package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.TbPhoneTypeInfo;

public interface PhoneTypeManager {

	/**
	 * 添加型号信息
	 * @param sess
	 * @param pfInfo
	 */
	void addPTInfo(TbPhoneTypeInfo ptInfo);
	
	
	/**
	 * 修改型号信息
	 * @param ptInfo
	 * @return
	 */
	Boolean updatePt(TbPhoneTypeInfo ptInfo);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TbPhoneTypeInfo findOneById(int id);
	
	/**
	 * 根据型号查询
	 * @param sess
	 * @param type
	 * @return
	 */
	TbPhoneTypeInfo findOneByTypeAndFlag(String type,int flag);
	
	/**
	 * 
	 * @return 所有型号信息
	 */
	String findAllPTInfo(); 
	
	/**
	 * 删除型号信息
	 * @param sess
	 * @param pfInfo
	 */
	void deletePTInfo(TbPhoneTypeInfo ptInfo);
	
	/**
	 * 批量删除型号信息
	 * @param ptId
	 * @return Boolean
	 */
	Boolean deletePtInfo(String[] ptId);
	
	/**
	 * 获取型号所有数量
	 * @return
	 */
	int getPtCount(String type);
	
	/**
	 * 分页获取型号信息列表
	 * @param type
	 * @return
	 */
	List<TbPhoneTypeInfo> getPtList(int pageNo,int pageSize,String type);
}
