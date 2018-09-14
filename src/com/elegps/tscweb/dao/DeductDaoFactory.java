package com.elegps.tscweb.dao;

import org.hibernate.Session;

import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;

import java.util.List;

public interface DeductDaoFactory
{
	/**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * 
	 * @param agent_name
	 * @return ��ѯ����ȡ�ü�¼����
	 */
	void  excuteDeduct(Session sess);
	
	/**
	 * �����ײ͵���ʱ���ѯ����Ƿ���ն�
	 * @param sess
	 * @param dataNow
	 * @return
	 */
	List<String> findArrearsMS(Session sess ,String dataNow);

	/**
	 * ��������Ƿ���ն�
	 * @param sess
	 * @param msid
	 */
	void frozenMS(Session sess,String msid);
}