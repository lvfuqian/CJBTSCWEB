package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbORGBalanceInfo;

public interface BalanceDaoFactory
{
   /**
    * 根据预充值id查询记录
    * @param sess
    * @param advanceid
    * @return
    */
	TbORGBalanceInfo get(Session sess , String orgtype,String orgid); 
	
}