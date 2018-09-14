package com.elegps.tscweb.dao;

import java.util.List;
import org.hibernate.Session;

import com.elegps.tscweb.model.TbParamsInfo;

public interface ParamsDaoFactory {
	
	/**
	 * 添加消息通知
	 * @param sess
	 * @param paramsInfo
	 */
	void add(Session sess,TbParamsInfo paramsInfo);
	
	/**
	 * 
	 * @return 查询所有通知消息
	 */
	List<TbParamsInfo> get(Session sess); 
	
	/**
	 * 通知消息
	 * 分页条件查询总数量
	 * @param sess
	 * @param msid type flag operator
	 * @return
	 */
	Integer getCount(Session sess,int pageNo,int pageSize,String msid,Integer type,Integer flag,String operator);
	
	/**
	 * 通知消息
	 * 分页条件查询
	 * @param msid type flag operator
	 * @return 
	 */
	List<TbParamsInfo> get(Session sess,int pageNo,int pageSize,String msid,Integer type,Integer flag,String operator); 
	
	/**
	 *  修改通知消息
	 * @param sess
	 * @param paramsInfo
	 */
	void update(Session sess,TbParamsInfo paramsInfo);
	
	/**
	 * id查找通知消息
	 * @param sess,id
	 * @return
	 */
	TbParamsInfo findById(Session sess,int id);
	
	/**
	 * 根据终端id查询
	 * @param sess,msid
	 * @return
	 */
	TbParamsInfo findByMsId(Session sess,String msid);
	
	/**
	 * 删除消息通知
	 * @param sess
	 * @param paramsInfo
	 */
	void delete(Session sess,TbParamsInfo paramsInfo);
	
}
