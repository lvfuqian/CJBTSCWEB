package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbORGBalanceInfo;

public interface BalanceDaoFactory
{
   /**
    * ����Ԥ��ֵid��ѯ��¼
    * @param sess
    * @param advanceid
    * @return
    */
	TbORGBalanceInfo get(Session sess , String orgtype,String orgid); 
	
}