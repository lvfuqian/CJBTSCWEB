package com.elegps.tscweb.dao;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.model.ControlInfo;
import com.elegps.tscweb.model.TbMsControlInfo;

public interface MsDaoControlFactory {
	/**
	 * ���
	 * 
	 * @param tmc
	 */
	public void add(Session session, TbMsControlInfo tmc);

	/**
	 * �޸Ļ���ɾ��
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 */
	public int executeHql(Session session, String hql, Object... obj);

	/**
	 * ͳ������
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 */
	public int executeControlCount(Session session, String hql, Object... obj);

	/**
	 * ��ȡlist �б�
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
	 * ��ȡ������¼
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 */
	public TbMsControlInfo getControl(Session session, String hql,
			Object... obj);
}
