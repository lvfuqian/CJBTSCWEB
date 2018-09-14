package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.ControlInfo;
import com.elegps.tscweb.model.TbMsControlInfo;

public interface MsDaoControlFactory {
	/**
	 * 添加
	 * 
	 * @param tmc
	 */
	public void add(Session session, TbMsControlInfo tmc);

	/**
	 * 修改或者删除
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 */
	public int executeHql(Session session, String hql, Object... obj);

	/**
	 * 统计行数
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 */
	public int executeControlCount(Session session, String hql, Object... obj);

	/**
	 * 获取list 列表
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param obj
	 * @return
	 */
	public List findList(Session session, String hql,
			int pageNo, int pageSize, Object... obj);

	/**
	 * 获取单条记录
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 */
	public TbMsControlInfo getControl(Session session, String hql,
			Object... obj);
}
