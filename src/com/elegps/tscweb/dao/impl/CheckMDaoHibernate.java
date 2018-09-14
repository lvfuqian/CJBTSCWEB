package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.dao.CheckMDaoFactory;
import com.elegps.tscweb.model.TbCheckMInfo;
import com.elegps.tscweb.model.TbPFInfo;

public class CheckMDaoHibernate implements CheckMDaoFactory {

	@Override
	public void addCMInfo(Session sess, TbCheckMInfo checkInfo) {
		// TODO Auto-generated method stub
		try {
			sess.save(checkInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void deleteCMInfo(Session sess, TbCheckMInfo checkInfo) {
		// TODO Auto-generated method stub
		try {
			sess.delete(checkInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public List<TbCheckMInfo> findAllCMInfo(Session sess) {
		// TODO Auto-generated method stub
		try {
			List<TbCheckMInfo> cm =(List<TbCheckMInfo>)sess.createQuery(" from TbCheckMInfo order by proTime desc").list();
			if (cm != null && cm.size() > 0) {
				return cm;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
			
		}
		
		return null;
	}

	@Override
	public TbCheckMInfo findCMInfoById(Session sess, int finId) {
		// TODO Auto-generated method stub
		String hql =" from TbCheckMInfo cm where finId = "+finId;
		try {
			List cm = sess.createQuery(hql).list();
			if (cm != null && cm.size() > 0) {
				
				return (TbCheckMInfo)cm.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
			
		}
		return null;
	}

	@Override
	public void updateCMInfo(Session sess, TbCheckMInfo checkInfo) {
		// TODO Auto-generated method stub
		try {
			sess.merge(checkInfo);
			//sess.saveOrUpdate(checkInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public List<TbCheckMInfo> findAllCMInfo(Session sess, int pageNo,
			int pageSize, String userName, String resName, int proStatus,
			int roleId,int uId,int cRole) {
		// TODO Auto-generated method stub
		int offset = (pageNo - 1) * pageSize;
		String hql ="from TbCheckMInfo C where 1=1";
		if(roleId == 40){//财务角色
			if(userName.trim().length()>0){ //申请人模糊查询
				hql +=" and C.proposer.userId in (select U.userId from TbUserInfo U where U.userName like '%"+userName+"%')";
			}
		}else if(roleId == 35 || roleId == 1 || roleId ==36){//超级管理人，管理人，客服
			hql += " and C.proposer.userId =" + uId;
		}

		if(resName.trim().length()>0){ //充值帐号模糊查询
			if(cRole == 2){//代理商
				hql +=" and C.resId in (select A.Agent_Id from TbAgentInfo A where A.Agent_Name like '%"+resName+"%')";
				//hql +=" and C.resId like '%"+resName+"%'";
			}else if(cRole == 10){//企业
				hql +=" and C.resId in (select E.Ep_Id from TbEnterpriseInfo E where E.Ep_Name like '%"+resName+"%')";
			}else if(cRole == 0){//终端
				hql +=" and C.resId like '%"+resName+"%'";
			}			
		}
		if(proStatus > 0 || proStatus==0){//审核状态查询
			hql +=" and C.proStatus =" +proStatus;
		}
		
		if(cRole == 10){//企业
			hql +=" and C.resRole.roleId = 3 or C.resRole.roleId = 4 ";
		}else if(cRole == 0){//终端
			hql +=" and C.resRole.roleId = null";
		}else{//代理商
			hql +=" and C.resRole.roleId =" +cRole;
		}
		
		hql +=" order by C.proStatus asc,C.proTime desc, C.checkTime desc";
		try {
			List list=sess.createQuery(hql).setFirstResult(offset)
			.setMaxResults(pageSize).list();
			if(list!=null){
				return list ;
		   }
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null ;
	}

	@Override
	public Integer findCMCount(Session sess, String userName, String resName,
			int proStatus, int roleId,int uId,int cRole) {
		// TODO Auto-generated method stub
		String hql ="select count(C.finId) from TbCheckMInfo C where 1=1";
		if(roleId == 40){//财务角色
			if(userName.trim().length()>0){ //申请人模糊查询
				hql +=" and C.proposer.userId in (select U.userId from TbUserInfo U where U.userName like '%"+userName+"%')";
			}
		}else if(roleId == 35 || roleId == 1 || roleId ==36){//超级管理人，管理人，客服
			hql += " and C.proposer.userId =" + uId;
		}
		if(resName.trim().length()>0){ //充值帐号模糊查询
			if(cRole == 2){//代理商
				hql +=" and C.resId in (select A.Agent_Id from TbAgentInfo A where A.Agent_Name like '%"+resName+"%')";
				//hql +=" and C.resId like '%"+resName+"%'";
			}else if(cRole == 10){//企业
				hql +=" and C.resId in (select E.Ep_Id from TbEnterpriseInfo E where E.Ep_Name like '%"+resName+"%')";
			}else if(cRole == 0){//终端
				hql +=" and C.resId like '%"+resName+"%'";
			}			
		}
		if(proStatus > 0 || proStatus==0){//审核状态查询
			hql +=" and C.proStatus =" +proStatus;
		}
		if(cRole == 10){//企业
			hql +=" and C.resRole.roleId = 3 or C.resRole.roleId = 4 ";
		}else if(cRole == 0){//终端
			hql +=" and C.resRole.roleId = null";
		}else{//代理商
			hql +=" and C.resRole.roleId =" +cRole;
		}
		try {
			Object obj = sess.createQuery(hql)
					.uniqueResult();
			if (obj!= null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

}
