package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.TbUserLog;

public interface LogManager {
	/**
	 * ����һ��������¼
	 * 
	 * @param userLog
	 */
	public void save(TbUserLog userLog);

	/**
	 * ͳ�Ʋ�����¼����
	 * 
	 * @param userId
	 * @param lType
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Integer searchUserLogCount(int userId, int lType, String startDate,
			String endDate);

	/**
	 * ��ȡ���������б�
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param userId
	 *            ������ID
	 * @param lType
	 *            ��������
	 * @param startDate
	 *            ��ʼ����ʱ��
	 * @param endDate
	 *            ��������ʱ��
	 * @return list
	 */
	public List<TbUserLog> searchUserLogList(int pageNo, int pageSize, int userId,
			int lType, String startDate, String endDate);
}
