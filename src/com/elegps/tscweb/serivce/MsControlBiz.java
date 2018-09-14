package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.ControlInfo;
import com.elegps.tscweb.model.TbMsControlInfo;

public interface MsControlBiz {
	/**
	 * 添加
	 * 
	 * @param tmc
	 */
	public void add(TbMsControlInfo tmc);

	/**
	 * 修改或者删除
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 */
	public int update(String hql, Object... obj)throws Exception;

	/**
	 * 统计行数
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 */
	public int executeControlCount(String msId, String msName, String epId,String grpId,
			String r01);

	/**
	 * 获取list 列表
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public List<ControlInfo> findList(int pageNo, int pageSize, String msId,
			String msName, String epId, String grpId,String r01) throws Exception;

	/**
	 * 获取单条记录
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public TbMsControlInfo getControl(String msId) throws Exception;
}
