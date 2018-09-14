package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;

public interface DeductDaoFactory
{
	/**
	 * 根据查询条件取得记录总数
	 * 
	 * @param agent_name
	 * @return 查询条件取得记录总数
	 */
	void  excuteDeduct(Session sess);
	
	/**
	 * 根据套餐到期时间查询所有欠费终端
	 * @param sess
	 * @param dataNow
	 * @return
	 */
	List<String> findArrearsMS(Session sess ,String dataNow);

	/**
	 * 冻结所有欠费终端
	 * @param sess
	 * @param msid
	 */
	void frozenMS(Session sess,String msid);
}