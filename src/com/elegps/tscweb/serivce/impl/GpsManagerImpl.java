package com.elegps.tscweb.serivce.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.GpsDaoFactory;
import com.elegps.tscweb.dao.GpsMsDaoFactory;
import com.elegps.tscweb.dao.GrpDaoFactory;
import com.elegps.tscweb.dao.GrpMsDaoFactory;
import com.elegps.tscweb.dao.impl.GpsDaoHibernate;
import com.elegps.tscweb.dao.impl.GpsMsDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpMsDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.serivce.GpsManager;
import com.elegps.tscweb.serivce.GrpManager;

public class GpsManagerImpl implements GpsManager {

	private GpsDaoFactory gpsdao;
	private GpsMsDaoFactory gpsmsdao;

	public GpsManagerImpl() throws MessageException {
		try {
			gpsdao = new GpsDaoHibernate();
			gpsmsdao = new GpsMsDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}

	}

	/**
	 * 获取所有GPS服务商登录账号数量
	 * 
	 * @return 所有GPS服务商登录账号的数量
	 */
	public int getGps_idAllCount() {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			int result = gpsdao.getGps_idAllCount(sess);
			tx.commit();
			return result;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
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
	 * 返回特定页面所有GPS服务商登录账号
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @return 指定页的全部GPS服务商登录账号信息
	 */
	public List<TbGpsInfo> getAllGps_idyPage(int pageNo, int pagesize) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx= sess.beginTransaction();
			List ml = gpsdao.findAllGps_typeByPage(sess, pageNo, pagesize);
			if (ml != null && ml.size() > 0) {
				List<TbGpsInfo> result = new ArrayList<TbGpsInfo>();
				for (Object obj : ml) {
					TbGpsInfo me = (TbGpsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
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
	 * 获取指定GPS服务商登录账号数量
	 * 
	 * @return 指定GPS服务商登录账号的数量
	 */
	public int getGps_idCount(String gps_id,String gps_name) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			int result = gpsdao.getGps_idCount(sess, gps_id, gps_name);
			tx.commit();
			return result;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	/**
	 * 返回特定页面指定GPS服务商登录账号
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @return 指定页的GPS服务商登录账号信息
	 */
	public List<TbGpsInfo> getGps_idyPage(int pageNo, int pagesize,
			String gps_id,String gps_name) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			 tx= sess.beginTransaction();
			List ml = gpsdao.findGps_typeByPage(sess, pageNo, pagesize, gps_id,gps_name);
			if (ml != null && ml.size() > 0) {
				List<TbGpsInfo> result = new ArrayList<TbGpsInfo>();
				for (Object obj : ml) {
					TbGpsInfo me = (TbGpsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
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
	 * 创建一条GPS服务商登录账号信息
	 * 
	 * @param gps_id
	 *            登录账号
	 * @param password
	 *            密码
	 * @return 新创登录账号的主键,如果创建失败，返回null。
	 */
	public String createGps(String gps_id, String password,String gps_name) {
		String msid = null;
		Transaction tx=null;
		String ret=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbGpsInfo tbgps = gpsdao.getidorname(sess, gps_id,gps_name);
			if (tbgps == null) {
				TbGpsInfo gps = new TbGpsInfo();
				Date date = new Date();
				gps.setGpsop_id(gps_id);
				gps.setGps_name(gps_name);
				gps.setPassword(password);
				gps.setCreate_time(date);
				ret=gpsdao.save(sess, gps);
				tx.commit();
				return ret;
			}
			tx.commit();
			return null;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	public Boolean deleteGps(String[] gpsid_list) {
		String msid = null;
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			 tx = sess.beginTransaction();
			for(int i=0;i<gpsid_list.length;i++) {
				String emp = gpsid_list[i];
				TbGpsInfo tbgps = gpsdao.get(sess, emp);
				if (tbgps != null) {
					gpsdao.delete(sess, tbgps);
					// 删除TbGpsMsListInfo对象的中gps_id等于emp
					gpsmsdao.deleteGpsMsInfoByGps_id(sess, emp);
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

	public TbGpsInfo getGps_bygrpid(String gps_id) {
		String msid = null;
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx= sess.beginTransaction();
			TbGpsInfo tbgps = gpsdao.get(sess, gps_id);
			if (tbgps != null) {
				tx.commit();
				return tbgps;
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
	 * 根据gps_id更新记录列表 更新记录列表
	 * 
	 * @param gps_id
	 * @param password
	 */
	public String modifyGps(String gps_id, String password,String gps_name) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			 tx= sess.beginTransaction();
			 TbGpsInfo tbgpsinfo=gpsdao.get_byname(sess,gps_id, gps_name);
				if(tbgpsinfo!=null){
					tx.commit();
					return null;
				}
			 
			TbGpsInfo tbgps = gpsdao.get(sess, gps_id);
			if (tbgps != null) {
				tbgps.setPassword(password);
				tbgps.setGps_name(gps_name);
				sess.update(tbgps);
			}
			tx.commit();
			return tbgps.getGpsop_id();
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
	 * 所有GPS厂商信息
	 * 
	 * @return 指定页的全部GPS厂商信息
	 */
	public List<TbGpsInfo> getAllGps_Info() {
		Transaction tx =null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx= sess.beginTransaction();
			List ml = gpsdao.findAllGps_Info(sess);
			if (ml != null && ml.size() > 0) {
				List<TbGpsInfo> result = new ArrayList<TbGpsInfo>();
				for (Object obj : ml) {
					TbGpsInfo me = (TbGpsInfo) obj;
					result.add(me);
				}
				tx.commit();
				return result;
				
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
