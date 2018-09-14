package com.elegps.tscweb.serivce;

import java.util.List;
import com.elegps.tscweb.model.TbPFInfo;

public interface PFManager {

	/**
	 * 添加套餐信息
	 *
	 * @param pfInfo
	 */
	String addPFInfo(TbPFInfo pfInfo);
	
	/**
	 * 
	 * @return 所有套餐信息
	 */
	List<TbPFInfo> findAllPFInfo();
	
	/**
	 * id查找套餐信息
	 * @param pfId
	 * @return
	 */
	TbPFInfo findPFInfoById(String pfId);
	
	/**
	 * id查找套餐信息
	 * @param pfId
	 * @return Boolean
	 */
	Boolean findPFById(String pfId);
	
	/**
	 *  修改套餐信息
	 * 
	 * @param pfInfo
	 * @return Boolean
	 */
	Boolean updatePFInfo(TbPFInfo pfInfo);
	
	/**
	 * 删除
	 * @param pfId
	 * @return Boolean
	 */
	Boolean deletePFInfo(String[] pfId);
	
	/**
	 * 
	 * @param how
	 * @return
	 */
	TbPFInfo getPFinfoByHow(double how); 
}
