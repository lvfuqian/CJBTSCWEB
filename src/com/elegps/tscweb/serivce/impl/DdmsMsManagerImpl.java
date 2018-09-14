package com.elegps.tscweb.serivce.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.DdmsMsDaoFactory;
import com.elegps.tscweb.dao.GpsDaoFactory;
import com.elegps.tscweb.dao.GpsMsDaoFactory;
import com.elegps.tscweb.dao.GrpDaoFactory;
import com.elegps.tscweb.dao.GrpMsDaoFactory;
import com.elegps.tscweb.dao.MsDaoFactory;
import com.elegps.tscweb.dao.impl.DdmsMsDaoHibernate;
import com.elegps.tscweb.dao.impl.GpsDaoHibernate;
import com.elegps.tscweb.dao.impl.GpsMsDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpMsDaoHibernate;
import com.elegps.tscweb.dao.impl.MsDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.serivce.DdmsMsManager;
import com.elegps.tscweb.serivce.GpsMsManager;
import com.elegps.tscweb.serivce.GrpMsManager;

public class DdmsMsManagerImpl implements DdmsMsManager {

	private DdmsMsDaoFactory ddmssmsdao;

	public DdmsMsManagerImpl() throws MessageException {
		try {
			ddmssmsdao = new DdmsMsDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}
	}
	
	public int getDdmsMs_SearchCount(String ddmssid, String msid,String agent,String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=ddmssmsdao.findDdmsMs_SearchCount(sess,ddmssid,msid,agent,ep);
			if (count!= null) {
				tx.commit();
				return count;
			}
			else{
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

	/**
	 * 根据每页记录数，总记录数获取总页数
	 * 
	 * @param count
	 *            总记录数
	 * @param pageSize
	 *            每页显示的记录数
	 * @return 计算得到的总页数
	 */
	public int getPageCount(int count, int pageSize) {
		return (count + pageSize - 1) / pageSize;
	}

	
	/**
	 * 调度用户的信息
	 */
	public List<String> getAllDdms_Info() {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = ddmssmsdao.findDdms_Info(sess);
			if (ml != null && ml.size() > 0) {
				List<String> result = new ArrayList<String>();
				for (int i=0;i<ml.size();i++) {
					String me = (String )ml.get(i);
					result.add(me);
				}
				tx.commit();
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

	public List<TbDdmsMsListInfo> getAllMs_Info() {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = ddmssmsdao.findMs_Info(sess);
			if (ml != null && ml.size() > 0) {
				List<TbDdmsMsListInfo> result = new ArrayList<TbDdmsMsListInfo>();
				for (Object obj : ml) {
					TbDdmsMsListInfo me = (TbDdmsMsListInfo) obj;
					result.add(me);
				}
				tx.commit();
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

	public List<TbDdmsMsListInfo> getDdmsMs_SearchByPage(int pageNo,
			int pageSize, String ddmsid, String msid,String agent,String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = ddmssmsdao.findDdmsMs_SearchByPage(sess, pageNo, pageSize,
					ddmsid,msid,agent,ep);
			if (ml != null && ml.size() > 0) {
				List<TbDdmsMsListInfo> result = new ArrayList<TbDdmsMsListInfo>();
				for (Object obj : ml) {
					TbDdmsMsListInfo me = (TbDdmsMsListInfo) obj;
					result.add(me);
				}
				tx.commit();
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

	public String createDdmsMsInfo(String ddms_id, String ms_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbDdmsMsListInfo tbddmsmsinfo = ddmssmsdao.get(sess, ddms_id, ms_id);
			if (tbddmsmsinfo == null) {
				TbDdmsMsListInfo ddmsms = new TbDdmsMsListInfo();
				Date date = new Date();
				ddmsms.setDdms_id(ddms_id);
				ddmsms.setMs_id(ms_id);
				ddmsms.setCreate_time(date);
				ddmssmsdao.save(sess, ddmsms);
				tx.commit();
				return ddmsms.getDdms_id();
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

	public Boolean deleteDdmsMs(String[] ddmsmsidlist) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			for (int i=0;i<ddmsmsidlist.length;i++) {
				StringBuffer ddmsmslist = new StringBuffer(ddmsmsidlist[i]);
				int start = 0;
				int end = ddmsmslist.indexOf(",");
				String ddmsid = ddmsmslist.substring(start, end);
				String msid=ddmsmslist.substring(end+1,ddmsmslist.length());
				TbDdmsMsListInfo tbddmsmsinfo = ddmssmsdao.get(sess, ddmsid, msid);
				if (tbddmsmsinfo != null) {
					ddmssmsdao.delete(sess, tbddmsmsinfo);					
				}
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	public List<TbMsInfo> getAllinDdms_Info() {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = ddmssmsdao.findinDdms_Info(sess);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
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

	public List<TbMsInfo> getAllnotDdms_Info() {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = ddmssmsdao.findnotDdms_Info(sess);
			if (ml != null && ml.size() > 0) {
				List<TbMsInfo> result = new ArrayList<TbMsInfo>();
				for (Object obj : ml) {
					TbMsInfo me = (TbMsInfo) obj;
					result.add(me);
				}
				tx.commit();
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
	
	/**
	 * 查询调度用户ID下已经存在的一般用户 zr
	 */
		public List getallms(String ddms_id) {
			Transaction tx=null;
			try {
				Session sess =HibernateUtil.currentSession();
				tx = sess.beginTransaction();
				List ddms = ddmssmsdao.findAllms_bymsid(sess, ddms_id);
				if (ddms != null && ddms.size() > 0) {
					tx.commit();
					return ddms;
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
		
		
		/**
		 * 查询调度用户ID下没有的非调度用户 zr
		 */
		
		public List getallms_notbyddmsid(String ddms_id) {
			
			Transaction tx=null;
			try {
				Session session = HibernateUtil.currentSession();
				tx = session.beginTransaction();
				List ddms = ddmssmsdao.findms_notinddms(session, ddms_id);
				if (ddms != null && ddms.size() > 0) {
					tx.commit();
					return ddms;
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
		/**
		 * 调度用户批量添加 ,提交时删除本来存在的用户 zr
		 */
		public String createDdMsInfo_Byddmsid(String ddmsid, String[] msid) {
			// TODO Auto-generated method stub
			Transaction tx=null;
			try{
			Session session=HibernateUtil.currentSession();
			 tx=session.beginTransaction();
			 ddmssmsdao.deleteexistms(session, ddmsid);
			if(msid.length<1||msid==null){
				tx.commit();
				return "已删除";
			}
			for(int i=0;i<msid.length;i++) {
				TbDdmsMsListInfo ddms=new TbDdmsMsListInfo();
				StringBuffer gpsmslist = new StringBuffer(msid[i]);
				Date d=new Date();
				ddms.setMs_id(msid[i]);
				ddms.setDdms_id(ddmsid);
				ddms.setCreate_time(d);
				ddmssmsdao.save(session, ddms);
			}
			   tx.commit();
			   return new String("添加成功！");
			} catch (Exception e) {
				if (tx!=null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				//HibernateUtil.closeSession();
			}
			return new String("添加失败！");
		}

}
