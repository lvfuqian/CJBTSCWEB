package com.elegps.tscweb.serivce.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.DDBHibernateUtil;
import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.GrpDaoFactory;
import com.elegps.tscweb.dao.GrpMsDaoFactory;
import com.elegps.tscweb.dao.impl.GrpDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpMsDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TabBaseGrpextinfo;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.serivce.GrpManager;

public class GrpManagerImpl implements GrpManager {

	
	private GrpDaoFactory grpdao;
	private GrpMsDaoFactory grpmsdao;

	public GrpManagerImpl() throws MessageException {
		try {
			grpdao = new GrpDaoHibernate();
			grpmsdao = new GrpMsDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("��ʼ��ҵ���߼���������쳣...");
		}

	}

	/**
	 * ��������Ⱥ��������Ϣ���� rutrun ����Ⱥ��������Ϣ����
	 */
	public int getAllgrp_typeCount() {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=grpdao.findAllGrp_typeCount(sess);
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
	 * �����ض�ҳ������Ⱥ����Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @return ָ��ҳ���û���״̬Ϊ1(����)ȫ��Ⱥ����Ϣ ���ն���������
	 */
	public List<TbGrpInfo> getAllGrp_typeByPage(int pageNo, int pagesize) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpdao.findAllGrp_typeByPage(sess, pageNo, pagesize);
			if (ml != null && ml.size() > 0) {
				List<TbGrpInfo> result = new ArrayList<TbGrpInfo>();
				for (Object obj : ml) {
					TbGrpInfo me = (TbGrpInfo) obj;
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
	 * �����ض�ָ������Ⱥ��������Ϣ����
	 * 
	 * @param grp_type
	 *            Ⱥ������
	 */
	public int getGrp_typeCount(int grp_type) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=grpdao.findGrp_typeCount(sess, grp_type);
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
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	/**
	 * �����ض�Ⱥ������ҳ������Ⱥ����Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @param ms_type
	 *            Ⱥ������
	 * @return ָ��Ⱥ������,Ⱥ�����Ч״̬Ϊ1(����)ҳ��ȫ��Ⱥ����Ϣ
	 */
	public List<TbGrpInfo> getGrp_typeByPage(int pageNo, int pagesize,
			int ms_type) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpdao
					.findGrp_typeByPage(sess, pageNo, pagesize, ms_type);
			if (ml != null && ml.size() > 0) {
				List<TbGrpInfo> result = new ArrayList<TbGrpInfo>();
				for (Object obj : ml) {
					TbGrpInfo me = (TbGrpInfo) obj;
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
	 * �����ض�ָ������Ⱥ����Ϣ
	 * 
	 * @param grpid
	 *            Ⱥ�����
	 */
	public TbGrpInfo getGrpinfoby_grpid(String grpid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbGrpInfo grpinfo=grpdao.get(sess, grpid);
			if(grpinfo!=null){
				tx.commit();
				return grpinfo;
			}else{
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * ��ȡ����Ⱥ������ͨ��̬��Ϣ����
	 * 
	 * @return Ⱥ������ͨ��̬��Ϣ����
	 */
	public int getAllGrpStatus_allCount() {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count= grpdao.findAllGrp_statusCount(sess);
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

	public List<TbGrpInfo> getAllGrpStatus_allByPage(int pageNo, int pagesize) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpdao.findAllStatueByPage(sess, pageNo, pagesize);
			if (ml != null && ml.size() > 0) {
				List<TbGrpInfo> result = new ArrayList<TbGrpInfo>();
				for (Object obj : ml) {
					TbGrpInfo me = (TbGrpInfo) obj;
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
	 * ��ȡȺ������ͨ��̬��Ϣ����
	 * 
	 * @param stauts
	 *            Ⱥ����ͨ��̬��Ϣ��Ӧ�ܼ�¼��
	 * @return Ⱥ������ͨ��̬��Ϣ����
	 */
	public int getGrp_StauteCount(int stauts) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count= grpdao.findGrp_StatusCount(sess, stauts);
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

	public List<TbGrpInfo> getGrp_StatusByPage(int pageNo, int pagesize,
			int status) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpdao.findGrp_StatusByPage(sess, pageNo, pagesize,
					status);
			if (ml != null && ml.size() > 0) {
				List<TbGrpInfo> result = new ArrayList<TbGrpInfo>();
				for (Object obj : ml) {
					TbGrpInfo me = (TbGrpInfo) obj;
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

	public Boolean deleteGrp(String grpid) {
		String msid = null;
		Transaction tx=null;
		Boolean returnvalue;
		try {
			Session sess = HibernateUtil.currentSession();
			//tx = sess.beginTransaction();
			returnvalue=grpdao.grpall_byid(sess, grpid);  //����Ⱥ���ҳ�������Ⱥ��,��ɾ����Ⱥ�飬��ɾ����Ⱥ�飬ͬʱɾ��Ⱥ�����ն˶�Ӧ��ϵ
//				TbGrpInfo tbgrp = grpdao.get(sess, grpid);
//				if (tbgrp != null) {
					//sess.delete(tbgrp);
					// ɾ��TbGrpMsListInfo�������grp_id����grpid
					//grpmsdao.deleteGrpMsInfoByGrp_id(sess, grpid);
//				}
			//tx.commit();
			return true;
		} catch (Exception e) {
			if (null != tx)
			//	tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	/**
	 * ����grp_id��ģ����ѯ
	 * 
	 * @return Ⱥ��grp_id��ģ����ѯ������
	 */
	public int getGrp_idCount(String grp_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=grpdao.findGrp_idAllCount(sess, grp_id);
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

	public List<TbGrpInfo> getTbGrpinfoby_grpid(int pageNo, int pagesize,
			String grpid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpdao
					.findGrp_idlistByPage(sess, pageNo, pagesize, grpid);
			if (ml != null && ml.size() > 0) {
				List<TbGrpInfo> result = new ArrayList<TbGrpInfo>();
				for (Object obj : ml) {
					TbGrpInfo me = (TbGrpInfo) obj;
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
	 * ��ȡȺ����Ч״̬��Ϣ���� Ⱥ����Ч״̬��Ӧ�ܼ�¼��
	 * 
	 * @return Ⱥ����Ч״̬��Ϣ������
	 */
	public int getAllGrp_flagCount() {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=grpdao.findGrp_flagAllCount(sess);
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

	/**
	 * ��������Ⱥ����Ч״״̬ҳ������Ⱥ����Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @return ����Ⱥ����Ч״״̬ҳ������Ⱥ����Ϣ
	 */
	public List<TbGrpInfo> getAllGrp_flagByPage(int pageNo, int pagesize) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpdao.findGrp_flaglistByPage(sess, pageNo, pagesize);
			if (ml != null && ml.size() > 0) {
				List<TbGrpInfo> result = new ArrayList<TbGrpInfo>();
				for (Object obj : ml) {
					TbGrpInfo me = (TbGrpInfo) obj;
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

	public int getGrp_flagCount(int flag) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=grpdao.findGrp_flagCount(sess, flag);
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

	public List<TbGrpInfo> getGrp_flagByPage(int pageNo, int pagesize, int flag) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpdao.findGrp_flaglistByPage(sess, pageNo, pagesize,
					flag);
			if (ml != null && ml.size() > 0) {
				List<TbGrpInfo> result = new ArrayList<TbGrpInfo>();
				for (Object obj : ml) {
					TbGrpInfo me = (TbGrpInfo) obj;
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

	public List getTbMsInfo() {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpdao.findMs_flagList(sess);
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

	public String createGrp(String grp_id, String grp_name, String ms_id,
			int grp_type, int flag,int talksc,String ep_id,int grp_lf,String grp_pid,String C03) {
		String msid = null;
		Transaction tx=null;
		String ret=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbGrpInfo tbms = grpdao.get(sess, grp_id);
			Date date = new Date();
			if (tbms == null) {
				TbGrpInfo grp = new TbGrpInfo();
				grp.setGrpid(grp_id);
				grp.setGrpname(grp_name);
				grp.setMsid(ms_id);
				grp.setRegtime(date);
				grp.setGrptype(grp_type);
				grp.setFlag(flag);
				grp.setStatus(0);
				grp.setEp_Id(Integer.parseInt(ep_id));
				grp.setTalkinglast(new Long(talksc*60000));
				grp.setGrplf(grp_lf);
				grp.setGrppid(grp_pid);
				grp.setC10("0");
				
				grp.setC03(C03);
				grp.setC04(C03);
				
				ret=grpdao.save(sess, grp);
				tx.commit();
				return new String("succeed");				 
			}else{
				tx.commit();
				return new String("exist");
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
	 * ����grp_id���¼�¼�б� ���¼�¼�б�
	 * 
	 * @param grp_id
	 * @param grp_name
	 * @param grp_type
	 * @param grp_flag
	 */
	public String modifyGrp(String grp_id,String ep_id, String grp_name, int grp_type,
			int grp_flag,int talksc,int grp_lf,String C03) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbGrpInfo tbgrp = grpdao.get(sess, grp_id);
			if (tbgrp != null) {
				tbgrp.setGrpname(grp_name);
				tbgrp.setGrptype(grp_type);
				tbgrp.setFlag(grp_flag);
				tbgrp.setEp_Id(Integer.parseInt(ep_id));
				tbgrp.setTalkinglast(new Long(talksc*60000));
				tbgrp.setGrplf(grp_lf);
				tbgrp.setC03(C03);
				tbgrp.setC04(C03);
				sess.update(tbgrp);
				tx.commit();
				return tbgrp.getGrpid();
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
	 * ����Ⱥ����Ϣ
	 * 
	 * @return ָ��ҳ��ȫ��Ⱥ����Ϣ
	 */
	public List<TbGrpInfo> getAllGrp_Info(String pagentid,String childagentid,String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List<TbGrpInfo> ml = grpdao.findAllGrp_Info(sess,pagentid,childagentid,ep);
			if (ml != null && ml.size() > 0) {
				List<TbGrpInfo> result = new ArrayList<TbGrpInfo>();
				for (TbGrpInfo obj : ml) {
					TbGrpInfo me = (TbGrpInfo) obj;
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
	 * ����Ⱥ����Ϣ
	 * 
	 * @return ָ��ҳ��ȫ��Ⱥ����Ϣ
	 */
	public List<TbGrpInfo> getBaseGrp_Info(String pagentid,String childagentid,String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List<TbGrpInfo> ml = grpdao.findBaseGrp_Info(sess,pagentid,childagentid,ep);
			if (ml != null && ml.size() > 0) {
				List<TbGrpInfo> result = new ArrayList<TbGrpInfo>();
				for (TbGrpInfo obj : ml) {
					TbGrpInfo me = (TbGrpInfo) obj;
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

	
	/**Ⱥ����ѯ����
	 * @param grp_type  Ⱥ������
	 * @param statue  ͨ��״̬
	 * @param flag  ��Ч״̬
	 * @param grp_id  Ⱥ�����
	 * @param grp_name  Ⱥ������
	 * @return Ⱥ��ģ����ѯ������
	 */
	public int getGrp_sertch(String grp_type, String statue, String flag,
			String grp_id, String grp_name,String pagentid,String childagentid,String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=grpdao.findGrp_sertchCount(sess,grp_type,statue,flag, grp_id,grp_name,pagentid,childagentid,ep);
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

	
	/**Ⱥ����ѯ��Ϣ
	 * @param grp_type  Ⱥ������
	 * @param statue  ͨ��״̬
	 * @param flag  ��Ч״̬
	 * @param grp_id  Ⱥ�����
	 * @param grp_name  Ⱥ������
	 * @return Ⱥ��ģ����ѯ����Ϣ
	 */
	public List<TbGrpInfo> getTBGrpinfoby_grppage(int pageNo, int pagesize,
			String grp_type, String statue, String flag, String grp_id,
			String grp_name,String pagentid,String childagentid,String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();	
			List ml =grpdao.findGrp_sertchByPage(sess, pageNo, pagesize,grp_type,statue,flag,grp_id,grp_name,pagentid,childagentid,ep);
			if (ml != null && ml.size() > 0) {
				List<TbGrpInfo> result = new ArrayList<TbGrpInfo>();
				for (Object obj : ml) {
					TbGrpInfo me = (TbGrpInfo) obj;
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

	public List getAllGrp_Info_not_Bymsid(String msid,String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpdao.findGrp_Info_bymsid(sess,msid,ep);
			if (ml != null && ml.size() > 0) {
				List<TbGrpInfo> result = new ArrayList<TbGrpInfo>();
				for (Object obj : ml) {
					TbGrpInfo me = (TbGrpInfo) obj;
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
    * ��ѯ�����û���λ��Ϣ zr
    */
	public List selectEp() {
		Transaction tx=null;
		try{
			Session sess=HibernateUtil.currentSession();
			tx=sess.beginTransaction();
			TbAgentInfo agent=new TbAgentInfo();
			GrpDaoFactory epinfo=new GrpDaoHibernate();
			List list=epinfo.getAllEp(sess);
			if(list!=null){
				tx.commit();
				return list;
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				e.printStackTrace();
			}
		}finally{
			HibernateUtil.closeSession();
		}
		
		return null;
	}
	/**
	 * �����û���λID��ѯ�û���λ��Ϣ zr
	 */
	public TbEnterpriseInfo getEpByid(Integer epid) {
		Transaction tx=null;
		try{
			Session session=HibernateUtil.currentSession();
			tx=session.beginTransaction();
			TbEnterpriseInfo info=(TbEnterpriseInfo)grpdao.getEpNameByEpid(session, epid);
		    if(info!=null){
		    	tx.commit();
		    	return info;
		    }else{
		    	return null;
		    }
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		
		return null;
	}
	/**
	 * ����Ⱥ��ID��ѯȺ���������û���λ zr
	 */
	public TbEnterpriseInfo getEpBygrpid(String grpid){
		Transaction tx=null;
		try{
			Session session=HibernateUtil.currentSession();
			tx=session.beginTransaction();
			TbEnterpriseInfo grpinfo=(TbEnterpriseInfo)grpdao.getNameByGrpid(session, grpid);
		    if(grpinfo!=null){
		    	tx.commit();
		    	return grpinfo;
		    }
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		
		return null;
		
	}
	
	
	public List getAllGrp_Info_not_BymsidGrp(String msid,String yep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpdao.findGrp_Info_bymsidGrp(sess,msid,yep);
			if (ml != null && ml.size() > 0) {
				List<TbGrpInfo> result = new ArrayList<TbGrpInfo>();
				for (Object obj : ml) {
					TbGrpInfo me = (TbGrpInfo) obj;
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

	public List<TabBaseGrpextinfo> listAll(int pageNo, int pageSize,TabBaseGrpextinfo baseGrp) {
		try{
			Session session = DDBHibernateUtil.currentSession();
			int fromIdx = (pageNo - 1) * pageSize;
			session.clear();
			StringBuilder hql=new StringBuilder();
			hql.append("  FROM  TabBaseGrpextinfo  WHERE 1=1 ");
			if(baseGrp.getCompanyName()!=null&&!"".equals(baseGrp.getCompanyName())){
				hql.append(" AND  companyName like '%"+baseGrp.getCompanyName()+"%'");
			}
			if(baseGrp.getGrpId()!=null&&!"".equals(baseGrp.getGrpId())){
				hql.append(" AND  grpId like '%"+baseGrp.getGrpId()+"%'");
			} 
			if(baseGrp.getType()!=null&&baseGrp.getType()>=0){
				hql.append(" AND type =  "+baseGrp.getType());
			}
			return (List<TabBaseGrpextinfo>)grpdao.listObject(session, hql.toString(), fromIdx, pageSize);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public void create(TabBaseGrpextinfo baseGrp) {
		try{
			Session session=DDBHibernateUtil.currentSession();
			grpdao.create(session, baseGrp);
			Session sess=HibernateUtil.currentSession();
			String hql=" UPDATE TbGrpInfo SET c10='1'  WHERE   grpid=?";
			grpdao.update(sess, hql, baseGrp.getGrpId());
		}catch(Exception e){
			DDBHibernateUtil.closeSession();
			HibernateUtil.closeSession();
			e.printStackTrace();
		}finally{
			DDBHibernateUtil.closeSession();
			HibernateUtil.closeSession();
		}
	}

	public int update(String hql, Object... objects) {
		try{
		Session session=DDBHibernateUtil.currentSession();
		return grpdao.update(session, hql, objects);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(String grpId) {
		Session session=DDBHibernateUtil.currentSession();
		String hql=" DELETE TabBaseGrpextinfo WHERE  grpId = ?";
		return grpdao.executeQuery(session, hql,grpId);
	}

	public int totalCount(TabBaseGrpextinfo baseGrp) {
		try{
			Session session=DDBHibernateUtil.currentSession();
			session.clear();
			StringBuilder hql=new StringBuilder("SELECT COUNT(*) FROM  TabBaseGrpextinfo WHERE 1=1 ");
			if(baseGrp.getGrpId()!=null&&!"".equals(baseGrp.getGrpId())){
				hql.append(" AND  grpId like '%"+baseGrp.getGrpId()+"%'");
			}
			if(baseGrp.getCompanyName()!=null&&!"".equals(baseGrp.getCompanyName())){
				hql.append(" AND  companyName like '%"+baseGrp.getCompanyName()+"%'");
			}
			if(baseGrp.getType()!=null&&baseGrp.getType()>=0){
				hql.append(" AND type="+baseGrp.getType());
			}
			return grpdao.toaltCount(session, hql.toString());
		}catch(Exception e){
				e.printStackTrace();
		}
	return 0;
}

	public TabBaseGrpextinfo getBean(String grpId) {
		Session session=DDBHibernateUtil.currentSession();
		String hql=" FROM TabBaseGrpextinfo WHERE  grpId=?";
		return (TabBaseGrpextinfo) grpdao.getBean(session, hql, grpId);
	}

	
	@Override
	public String getGrpId(String grp) {
		// TODO Auto-generated method stub
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			String gid = grpdao.getGrpId(sess, grp);
			if(gid != null){
				tx.commit();
				return gid;
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

	@Override
	public List findGrp_Info_byMsId(String msid) {
		// TODO Auto-generated method stub
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List list = grpdao.findGrp_Info_byMsId(sess, msid);
			if (list != null && list.size() > 0) {			
				tx.commit();
				return list;
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

	@Override
	public void update(TbGrpInfo grpInfo) {
		// TODO Auto-generated method stub
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			grpdao.update(sess, grpInfo);
		
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public String createGrp(String grp_id, String grp_name, String ms_id,
			int grp_type, int flag,int talksc,String ep_id,int grp_lf,String grp_pid,String C03,String desc) {
		String msid = null;
		Transaction tx=null;
		String ret=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbGrpInfo tbms = grpdao.get(sess, grp_id);
			Date date = new Date();
			if (tbms == null) {
				TbGrpInfo grp = new TbGrpInfo();
				grp.setGrpid(grp_id);
				grp.setGrpname(grp_name);
				grp.setMsid(ms_id);
				grp.setRegtime(date);
				grp.setGrptype(grp_type);
				grp.setFlag(flag);
				grp.setStatus(0);
				grp.setEp_Id(Integer.parseInt(ep_id));
				grp.setTalkinglast(new Long(talksc*60000));
				grp.setGrplf(grp_lf);
				grp.setGrppid(grp_pid);
				grp.setC10("0");
				grp.setC09(desc);
				grp.setC03(C03);
				grp.setC04(C03);
				
				ret=grpdao.save(sess, grp);
				tx.commit();
				return new String("succeed");				 
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

	@Override
	public Map<String, String> getGrpByNameOrId(String nameOrId, int epid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Map<String, String> map = grpdao.getGrpByNameOrId(sess, nameOrId, epid);
			if (map != null && map.size() > 0) {			
				tx.commit();
				return map;
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
}
