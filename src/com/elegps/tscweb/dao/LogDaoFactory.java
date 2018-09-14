package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbUserLog;

import java.util.List;

/**
 * 
 * @author LuYun
 * @date 2012-06-20
 */
public interface LogDaoFactory {

	/**
	 * 根据查询条件取得记录总数
	 * 
	 * @param objects
	 *            {} 参数数组
	 * @return 查询条件取得记录总数
	 */
	public Integer searchUserLogCount(Session sess,String hql, Object... objects);

	/**
	 * 根据指定页及查询条件取得记录信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param objects
	 *            {} 参数数组
	 * @return 指定页及查询条件取得记录信息
	 */
	public List searchUserLogList(Session sess,String hql, int pageNo, int pageSize,
			Object... objects);

	/**
	 * 添加一条用户操作记录
	 * 
	 * @param userLog
	 *            操作记录对象
	 */
	public void save(Session session, TbUserLog userLog);
}