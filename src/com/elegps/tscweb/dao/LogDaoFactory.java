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
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * 
	 * @param objects
	 *            {} ��������
	 * @return ��ѯ����ȡ�ü�¼����
	 */
	public Integer searchUserLogCount(Session sess,String hql, Object... objects);

	/**
	 * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param objects
	 *            {} ��������
	 * @return ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	 */
	public List searchUserLogList(Session sess,String hql, int pageNo, int pageSize,
			Object... objects);

	/**
	 * ���һ���û�������¼
	 * 
	 * @param userLog
	 *            ������¼����
	 */
	public void save(Session session, TbUserLog userLog);
}