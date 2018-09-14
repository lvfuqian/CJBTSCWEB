package com.elegps.tscweb.serivce;

import java.util.List;

import com.elegps.tscweb.model.ControlInfo;
import com.elegps.tscweb.model.TbMsControlInfo;

public interface MsControlBiz {
	/**
	 * ���
	 * 
	 * @param tmc
	 */
	public void add(TbMsControlInfo tmc);

	/**
	 * �޸Ļ���ɾ��
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 */
	public int update(String hql, Object... obj)throws Exception;

	/**
	 * ͳ������
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 */
	public int executeControlCount(String msId, String msName, String epId,String grpId,
			String r01);

	/**
	 * ��ȡlist �б�
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
	 * ��ȡ������¼
	 * 
	 * @param hql
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public TbMsControlInfo getControl(String msId) throws Exception;
}
