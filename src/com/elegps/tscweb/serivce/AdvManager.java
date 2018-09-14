package com.elegps.tscweb.serivce;

import java.util.List;
import com.elegps.tscweb.model.TbAdvInfo;

public interface AdvManager {

	/**
	 * 添加广告信息
	 *
	 * @param advInfo
	 */
	String addAdvInfo(TbAdvInfo advInfo);
	
	/**
	 * 
	 * @return 所有广告信息
	 */
	List<TbAdvInfo> findAllAdvInfo();
	
	/**
	 * id查找广告信息
	 * @param advId
	 * @return
	 */
	TbAdvInfo findAdvInfoById(String advId);
	
	/**
	 * id查找广告信息
	 * @param advId
	 * @return Boolean
	 */
	Boolean findAdvById(String advId);
	
	/**
	 *  修改广告信息
	 * 
	 * @param advInfo
	 * @return Boolean
	 */
	Boolean updateAdvInfo(TbAdvInfo advInfo);
	
	/**
	 * 删除
	 * @param advId
	 * @return Boolean
	 */
	Boolean deleteAdvInfo(String[] advId);
	
	/**
	 * 条件查询总数量
	 * @param title
	 * @param url
	 * @param sendSTime
	 * @param sendETime
	 * @param creatSTime
	 * @param creatETime
	 * @return Integer
	 */
	Integer findAdvCount(String title,String url,String sendSTime,
			String sendETime,String creatSTime,String creatETime,int advType);
	
	/**
	 * 条件查询广告信息（分页）
	 * @param pageNo
	 * @param pageSize
	 * @param title
	 * @param url
	 * @param sendSTime
	 * @param sendETime
	 * @param creatSTime
	 * @param creatETime
	 * @return List<TbAdvInfo>
	 */
	List<TbAdvInfo> findAdvInfoByPage(Integer pageNo,Integer pageSize,String title,String url,
			String sendSTime,String sendETime,String creatSTime,String creatETime,int advType);
}
