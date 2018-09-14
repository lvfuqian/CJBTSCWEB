package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.TbUserLog;

public interface LogManager {
	/**
	 * 保存一条操作记录
	 * 
	 * @param userLog
	 */
	public void save(TbUserLog userLog);

	/**
	 * 统计操作记录条数
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
	 * 获取操作条数列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param userId
	 *            操作人ID
	 * @param lType
	 *            操作类型
	 * @param startDate
	 *            开始日期时间
	 * @param endDate
	 *            结束日期时间
	 * @return list
	 */
	public List<TbUserLog> searchUserLogList(int pageNo, int pageSize, int userId,
			int lType, String startDate, String endDate);
}
