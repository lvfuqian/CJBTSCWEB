package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAdvInfo;


/**
 * 广告信息
 * @author wanglei
 *
 */
public interface AdvDaoFactory {
	
	/**
	 * 添加广告信息
	 * @param sess
	 * @param advInfo
	 */
	void addAdvInfo(Session sess,TbAdvInfo advInfo);
	
	/**
	 * 
	 * @return 查询所有广告信息
	 */
	List<TbAdvInfo> findAllAdvInfo(Session sess); 
	
	/**
	 * 条件查询所有广告信息(分页显示)
	 * @param sess
	 * @param pageNo
	 * @param pageSize
	 * @param userId
	 * @param isSend
	 * @param sendSTime
	 * @param sendETime
	 * @param creatSTime
	 * @return creatETime
	 */
	List<TbAdvInfo> findAllAdvInfo(Session sess,Integer pageNo,Integer pageSize,String title,String url,String sendSTime,String sendETime,String creatSTime,String creatETime,int advType); 
	
	/**
	 * 条件查询 总数量
	 * @param sess
	 * @param userId
	 * @param isSend
	 * @param sendSTime
	 * @param sendETime
	 * @param creatSTime
	 * @return creatETime
	 */
	Integer findAdvCount(Session sess,String title,String url,String sendSTime,String sendETime,String creatSTime,String creatETime,int advType);
	
	/**
	 *  更新广告信息
	 * @param sess
	 * @param advInfo
	 */
	void updateAdvInfo(Session sess,TbAdvInfo advInfo);
	
	/**
	 * id查找广告信息
	 * @param sess,AdvId
	 * @return
	 */
	TbAdvInfo findAdvInfoById(Session sess,int AdvId);
	
	/**
	 * 删除广告信息
	 * @param sess
	 * @param advInfo
	 */
	void deleteAdvInfo(Session sess,TbAdvInfo advInfo);
}
