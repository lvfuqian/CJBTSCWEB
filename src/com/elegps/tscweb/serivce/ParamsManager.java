package com.elegps.tscweb.serivce;

import java.util.List;
import com.elegps.tscweb.model.TbParamsInfo;

public interface ParamsManager {

	/**
	 * 添加消息通知
	 * @param sess
	 * @param paramsInfo
	 */
	String add(TbParamsInfo paramsInfo);
	
	/**
	 * 
	 * @return 查询所有通知消息
	 */
	List<TbParamsInfo> get(); 
	
	/**
	 * 通知消息
	 * 分页条件查询总数量
	 * @param sess
	 * @param msid type flag operator
	 * @return
	 */
	Integer getCount(int pageNo,int pageSize,String msid,Integer type,Integer flag,String operator);
	
	/**
	 * 通知消息
	 * 分页条件查询
	 * @param msid type flag operator
	 * @return 
	 */
	List<TbParamsInfo> get(int pageNo,int pageSize,String msid,Integer type,Integer flag,String operator); 
	
	/**
	 *  修改通知消息
	 * @param sess
	 * @param paramsInfo
	 */
	Boolean update(TbParamsInfo paramsInfo);
	
	/**
	 * id查找通知消息
	 * @param sess,id
	 * @return
	 */
	TbParamsInfo findById(int id);
	
	/**
	 * 根据终端id查询
	 * @param sess,msid
	 * @return
	 */
	TbParamsInfo findByMsId(String msid);
	
	/**
	 * 删除消息通知
	 * @param sess
	 * @param id
	 */
	Boolean delete(Integer id);
	/**
	 * 批量删除消息通知
	 * @param sess
	 * @param id
	 */
	Boolean delete(String[] id);
}
