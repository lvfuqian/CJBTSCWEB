package com.elegps.tscweb.serivce.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.MsDaoControlFactory;
import com.elegps.tscweb.dao.impl.MsDaoControlHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.ControlInfo;
import com.elegps.tscweb.model.TbMsControlInfo;
import com.elegps.tscweb.serivce.MsControlBiz;

public class MsControlBizImpl implements MsControlBiz {
	private MsDaoControlFactory controlFactory;

	public MsControlBizImpl() throws MessageException {
		try {
			controlFactory = new MsDaoControlHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}

	public void add(TbMsControlInfo tmc) {
		Session session = HibernateUtil.currentSession();
		controlFactory.add(session, tmc);
	}

	public int update(String hql, Object... obj) {
		Session session = HibernateUtil.currentSession();
		return controlFactory.executeHql(session, hql, obj);
	}

	public int executeControlCount(String msId, String msName, String epId,
			String grpId, String r01) {
		Session session = HibernateUtil.currentSession();
		StringBuilder hql = new StringBuilder("SELECT   ");
		hql.append(" COUNT(*) ")
				.append("  FROM tb_MS_Info AS m ,tb_EnterPrise_Info AS e , tb_Ms_Control_Info AS c ");
				if (grpId != null && !"".equals(grpId) && !"-1".equals(grpId)) {
					hql.append(", tb_MS_GRP_List AS msgrp");
				}
				hql.append("  WHERE e.Enterprise_Id =  c.ENTER_ID AND m.MS_Id =  c.MS_ID");
				if (grpId != null && !"".equals(grpId) && !"-1".equals(grpId)) {
					hql.append(" AND m.MS_Id =  msgrp.MS_Id    ");
				}
		if (msId != null && !"".equals(msId)) {
			hql.append(" AND m.MS_Id LIKE '%" + msId + "%'");
		}
		if (msName != null && !"".equals(msName)) {
			hql.append("  AND m.MS_Name LIKE '%" + msName + "%'");
		}
		if (epId != null && !"".equals(epId) && !"-1".equals(epId)) {
			hql.append("  AND e.Enterprise_Id=" + Integer.parseInt(epId));
		}
		if (grpId != null && !"".equals(grpId)&& !"-1".equals(grpId)) {
			hql.append("  AND msgrp.GRP_Id=" + grpId);
		}
		if (r01 != null && !"".equals(r01)&& !"-1".equals(r01)) {
			hql.append("  AND  c.R01=" + Integer.parseInt(r01));
		}
		return controlFactory.executeControlCount(session, hql.toString());
	}

	public List<ControlInfo> findList(int pageNo, int pageSize, String msId,
			String msName, String epId, String grpId, String r01)throws Exception {
		List<ControlInfo> listControl = new ArrayList<ControlInfo>();
		ControlInfo controlInfo = null;
		try{
		Session session = HibernateUtil.currentSession();
		StringBuilder hql = new StringBuilder("SELECT    ");
		hql.append(	" e.Enterprise_Id,e.Enterprise_Name,m.MS_Name,m.MS_Id, c.R01,c.R02,c.R03,c.R04,c.R05")
				.append("  FROM tb_MS_Info AS m ,tb_EnterPrise_Info AS e , tb_Ms_Control_Info AS c ");
				if (grpId != null && !"".equals(grpId) && !"-1".equals(grpId)) {
					hql.append(", tb_MS_GRP_List AS msgrp");
				}
				hql.append("  WHERE e.Enterprise_Id =  c.ENTER_ID AND m.MS_Id =  c.MS_ID");
				if (grpId != null && !"".equals(grpId) && !"-1".equals(grpId)) {
					hql.append(" AND m.MS_Id =  msgrp.MS_Id    ");
				}
		if (msId != null && !"".equals(msId)) {
			hql.append(" AND m.MS_Id LIKE '%" + msId + "%'");
		}
		if (msName != null && !"".equals(msName)) {
			hql.append("  AND m.MS_Name LIKE '%" + msName + "%'");
		}
		if (epId != null && !"".equals(epId) && !"-1".equals(epId)) {
			hql.append(" AND e.Enterprise_Id=" + Integer.parseInt(epId));
		}
		if (grpId != null && !"".equals(grpId) && !"-1".equals(grpId)) {
			hql.append("  AND msgrp.GRP_Id=" + grpId);
		}
		if (r01 != null && !"".equals(r01)&& !"-1".equals(r01)) {
			hql.append(" AND  c.R01=" + Integer.parseInt(r01));
		}

		List list = controlFactory.findList(session, hql.toString(), pageNo,
				pageSize);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			controlInfo = new ControlInfo();
			controlInfo.setEnterpriseId((Integer) obj[0]);
			controlInfo.setEnterpriseName((String) obj[1]);
			controlInfo.setMsName((String) obj[2]);
			controlInfo.setMsId((String) obj[3]);
			controlInfo.setR01((Integer) obj[4]);
			controlInfo.setR02((obj[5]!=null)?(Float) obj[5]:0);
			controlInfo.setR03((obj[6]!=null)?(Float) obj[6]:0);
			controlInfo.setR04((Integer) obj[7]);
			controlInfo.setR05((String) obj[8]);
			listControl.add(controlInfo);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return listControl;
	}

	public TbMsControlInfo getControl(String msId)throws Exception  {
		
		Session session = HibernateUtil.currentSession();
		String hql = " FROM TbMsControlInfo WHERE msId=?";
		return controlFactory.getControl(session, hql, msId);
	}

}
