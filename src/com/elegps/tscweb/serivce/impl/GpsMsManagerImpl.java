package com.elegps.tscweb.serivce.impl;

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
import com.elegps.tscweb.dao.MsDaoFactory;
import com.elegps.tscweb.dao.impl.GpsDaoHibernate;
import com.elegps.tscweb.dao.impl.GpsMsDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpMsDaoHibernate;
import com.elegps.tscweb.dao.impl.MsDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.serivce.GpsMsManager;
import com.elegps.tscweb.serivce.GrpMsManager;

public class GpsMsManagerImpl implements GpsMsManager {

	private GpsMsDaoFactory gpsmsdao;

	public GpsMsManagerImpl() throws MessageException {
		try {
			gpsmsdao = new GpsMsDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("��ʼ��ҵ���߼���������쳣...");
		}

	}

	/**
	 * ��ȡGPS�����̵�¼�˺�������Ϣ���� GPS�����̵�¼�˺�������Ϣ�ܼ�¼��
	 * 
	 * @return ��ȡGPS�����̵�¼�˺�������Ϣ����
	 */
	public int getAllGps_idCount() {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx= sess.beginTransaction();
			Integer count=gpsmsdao.findGps_idAllCount(sess);
			if ( count!= null) {
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

	/**
	 * ����ÿҳ��¼�����ܼ�¼����ȡ��ҳ��
	 * 
	 * @param count
	 *            �ܼ�¼��
	 * @param pageSize
	 *            ÿҳ��ʾ�ļ�¼��
	 * @return ����õ�����ҳ��
	 */
	public int getPageCount(int count, int pageSize) {
		return (count + pageSize - 1) / pageSize;
	}

	/**
	 * ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @return ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 */
	public List<TbGpsMsListInfo> getAllGrpid_InfoByPage(int pageNo, int pagesize) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			 tx= sess.beginTransaction();
			List ml = gpsmsdao.findAllGpsMs_InfoGrpidByPage(sess, pageNo,
					pagesize);
			if (ml != null && ml.size() > 0) {
				List<TbGpsMsListInfo> result = new ArrayList<TbGpsMsListInfo>();
				for (Object obj : ml) {
					TbGpsMsListInfo me = (TbGpsMsListInfo) obj;
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
	 * ��ȡָ��GPS����������Ϣ����
	 * 
	 * @param gps_id
	 *            GPS�����ʺ�
	 * @return ��ȡָ��GPS����������Ϣ����
	 */
	public int getGps_idCount(String gps_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=gpsmsdao.findGps_idCount(sess, gps_id);
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

	/**
	 * ����GPS�����ն˶�Ӧ��ϵ�а�GPS�����ʺ�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @param gps_id
	 *            GPS�����ʺ�
	 * @return ����GPS�����ն˶�Ӧ��ϵ�а�GPS�����ʺ�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 */
	public List<TbGpsMsListInfo> getGpsid_InfoByPage(int pageNo, int pagesize,
			String gps_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = gpsmsdao.findAllGpsid_InfoByPage(sess, pageNo, pagesize,
					gps_id);
			if (ml != null && ml.size() > 0) {
				List<TbGpsMsListInfo> result = new ArrayList<TbGpsMsListInfo>();
				for (Object obj : ml) {
					TbGpsMsListInfo me = (TbGpsMsListInfo) obj;
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
	 * ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @return ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 */
	public List<TbGpsMsListInfo> getAllMsid_InfoByPage(int pageNo, int pagesize) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = gpsmsdao.findAllGpsMs_InfoMsidByPage(sess, pageNo,
					pagesize);
			if (ml != null && ml.size() > 0) {
				List<TbGpsMsListInfo> result = new ArrayList<TbGpsMsListInfo>();
				for (Object obj : ml) {
					TbGpsMsListInfo me = (TbGpsMsListInfo) obj;
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
	 * ��ȡָ���ն˺�������Ϣ����
	 * 
	 * @param ms_id
	 *            �ն˺�
	 * @return ��ȡָ���ն˺�������Ϣ����
	 */
	public int getMs_idCount(String ms_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=gpsmsdao.findMs_idCount(sess, ms_id);
			if (count!= null) {
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

	public List<TbGpsMsListInfo> getMsid_InfoByPage(int pageNo, int pagesize,
			String ms_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = gpsmsdao.findAllMsid_InfoByPage(sess, pageNo, pagesize,
					ms_id);
			if (ml != null && ml.size() > 0) {
				List<TbGpsMsListInfo> result = new ArrayList<TbGpsMsListInfo>();
				for (Object obj : ml) {
					TbGpsMsListInfo me = (TbGpsMsListInfo) obj;
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
	 * ����gps_id,ms_id ɾ����¼
	 * 
	 * @param grpmsidlist
	 */
	public Boolean deleteGpsMs(String[] gpsmsidlist) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			for (int i=0;i<gpsmsidlist.length;i++) {
				StringBuffer gpsmslist = new StringBuffer(gpsmsidlist[i]);
				int start = 0;
				int end = gpsmslist.indexOf(",");
				String gpsid = gpsmslist.substring(start, end);
				String msid=gpsmslist.substring(end+1,gpsmslist.length());
				TbGpsMsListInfo tbgpsmsinfo = gpsmsdao.get(sess, gpsid, msid);
				if (tbgpsmsinfo != null) {
					gpsmsdao.delete(sess, tbgpsmsinfo);					
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

	public String createGpsMsInfo(String gps_id, String ms_id) {
		String msid = null; // ����ms_id��12λ
		Transaction tx=null;
		String ret=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbGpsMsListInfo tbgpsmsinfo = gpsmsdao.get(sess, gps_id, ms_id);
			if (tbgpsmsinfo == null) {
				StringBuffer gpsmslist = new StringBuffer(ms_id);
				int start = 9;
				int end = 21;
				msid = gpsmslist.substring(start, end);
				TbGpsMsListInfo gpsms = new TbGpsMsListInfo();
				Date date = new Date();
				gpsms.setGpsop_id(gps_id);
				gpsms.setL_ms_id(ms_id);
				gpsms.setMs_id(msid);
				gpsms.setCreate_time(date);
				ret=gpsmsdao.save(sess, gpsms);
				tx.commit();
				return ret;
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
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param gpsid
	 * @param msid
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
	public int getGpsMs_SearchCount(String gpsid, String msid,String pagentid,String childagentid,String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=gpsmsdao.findGpsMs_SearchCount(sess,gpsid,msid,pagentid,childagentid,ep);
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
	    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    * @param pageNo
	    * @param pageSize
	    * @param gpsid
	    * @param msid
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
	public List getGpsMs_SearchByPage(int pageNo,
			int pageSize, String gpsid, String msid,String pagentid,String childagentid,String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = gpsmsdao.findGpsMs_SearchByPage(sess, pageNo, pageSize,
					gpsid,msid,pagentid,childagentid,ep);
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

	public List getMs_info(String gpsid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = gpsmsdao.findAllms_bygpsid(sess,gpsid);
			if (ml != null && ml.size() > 0) {
				tx.commit();
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

	public String createGpsMsInfo_ByGpsid(String gpsid, String[] msid) {
		Transaction tx=null;
		String smsid=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			gpsmsdao.deleteGpsMsInfoByGps_id(sess, gpsid);
			if(msid==null || msid.length<1){
				tx.commit();
				return new String("ɾ���ɹ���");
			}
			for(int i=0;i<msid.length;i++) {
				TbGpsMsListInfo gpsms = new TbGpsMsListInfo();
				StringBuffer gpsmslist = new StringBuffer(msid[i]);
				int start = 9;
				int end = 21;
				smsid = gpsmslist.substring(start, end);
				Date date = new Date();
				gpsms.setGpsop_id(gpsid);
                gpsms.setMs_id(smsid);
                gpsms.setL_ms_id(msid[i]);
                gpsms.setCreate_time(date);
				gpsmsdao.save(sess, gpsms);
			}
			tx.commit();
			return new String("��ӳɹ���");
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return new String("���ʧ�ܣ�");
	}

	/**
	 * ����ID��ѯ���д�����
	 * @param agent_id
	 * @return
	 */

	
}
