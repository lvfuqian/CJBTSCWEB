package com.elegps.tscweb.serivce.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.MsChargeDaoFactory;
import com.elegps.tscweb.dao.impl.MsChargeDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.serivce.MsChargeManager;

public class MsChargeManagerImpl implements MsChargeManager {

	private MsChargeDaoFactory mschargedao;


	public MsChargeManagerImpl() throws MessageException {
		try {
			mschargedao = new MsChargeDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}

	}


	public int getMsCharge_sertch(String ms_id, String ms_name, String pagentid,String childagentid,
			String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=mschargedao.getMsChareg_sertchCount(sess,ms_id,ms_name,pagentid,childagentid,ep);
			if(count!=null){
				tx.commit();
				return count;
			}else{
				tx.commit();
				return 0;
			}			
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}


	public int getPageCount(int count, int pageSize) {
			return (count + pageSize - 1) / pageSize;
	}


	public List<TbMsInfo> getMsChargeinfo_bypage(int pageNo, int pagesize,
			String ms_id, String ms_name, String pagentid,String childagentid, String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = mschargedao.getdMsCharge_sertchByPage(sess, pageNo, pagesize,ms_id,ms_name,pagentid,childagentid,ep);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
				System.out.println("result.size()=" + result.size());
				return result;
			}else{
				tx.commit();
				return null;
			}		
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}


	public List getBalanceinfo_byagentid(String agent_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = mschargedao.getBalanceinfo_byagentid(sess,agent_id);
			if (ml != null && ml.size() > 0) {
				return ml;
			}else{
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}


	public List getBalancefo_byeptid(String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = mschargedao.getBalanceInfo_byepid(sess,ep);
			if (ml != null && ml.size() > 0) {
				return ml;
			}else{
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}


	public int getAgentBalance(String agent_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			int ml = mschargedao.getAgentBalancedao(sess,agent_id);
				return ml;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}


	public int getEpBalance(String ep_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			int ml = mschargedao.getEpBalancedao(sess,ep_id);
				return ml;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}


	public String getMsCharge_byagentid(String changeagentid, String ep_id,
			String charge, String[] ms_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			String ml = mschargedao.getMsCharge_byagentiddao(sess,changeagentid,ep_id,charge,ms_id);
			return ml;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}


	public String getMsCharge_byepid(String changeepid, String ep_id,
			String charge, String[] ms_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			String ml = mschargedao.getMsCharge_byepiddao(sess,changeepid,ep_id,charge,ms_id);
			return ml;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}


	public int getEpCharge_SearchCount(String agent, String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=mschargedao.getEpCharge_Count(sess, agent,ep);
			if (count!= null){
				tx.commit();
				return count;
			}else{
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}


	public List getEpCharge_SearchByPage(int pageNo, int pageSize,
			String agent, String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = mschargedao.getEpCharge_SearchByPage(sess, pageNo, pageSize,agent,ep);
			if (ml != null && ml.size() > 0) {
					tx.commit();
					return ml;		
			}	
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;	
	}


	public int getMsCharge_SearchCount(String agent, String ep,String ms_id,String ms_name) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=mschargedao.getMsCharge_Count(sess, agent,ep,ms_id,ms_name);
			if (count!= null){
				tx.commit();
				return count;
			}else{
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}


	public List getMsCharge_SearchByPage(int pageNo, int pageSize,
			String agent, String ep,String ms_id,String ms_name) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = mschargedao.getMsCharge_SearchByPage(sess, pageNo, pageSize,agent,ep,ms_id,ms_name);
			if (ml != null && ml.size() > 0) {
					tx.commit();
					return ml;		
			}	
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;	
	}


	public int getMsDeduct_SearchCount(String agent, String ep,String ms_id,String ms_name) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=mschargedao.getMsDeduct_Count(sess, agent,ep,ms_id,ms_name);
			if (count!= null){
				tx.commit();
				return count;
			}else{
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}


	public List getMsDeduct_SearchByPage(int pageNo, int pageSize,
			String agent, String ep,String ms_id,String ms_name) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = mschargedao.getMsDeduct_SearchByPage(sess, pageNo, pageSize,agent,ep,ms_id,ms_name);
			if (ml != null && ml.size() > 0) {
					tx.commit();
					return ml;		
			}	
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;	
	}


}
