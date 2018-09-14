package com.elegps.tscweb.serivce.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.GrpDaoFactory;
import com.elegps.tscweb.dao.GrpMsDaoFactory;
import com.elegps.tscweb.dao.impl.GrpDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpMsDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.serivce.GrpMsManager;

public class GrpMsManagerImpl implements GrpMsManager {

	private GrpMsDaoFactory grpmsdao;
	private GrpDaoFactory grpdao;

	public GrpMsManagerImpl() throws MessageException {
		try {
			grpmsdao = new GrpMsDaoHibernate();
			grpdao = new GrpDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("��ʼ��ҵ���߼���������쳣...");
		}

	}

	/**
	 * ��ȡȺ��������Ϣ���� Ⱥ��������Ϣ�ܼ�¼��
	 * 
	 * @return ��ȡȺ��������Ϣ����
	 */
	public int getAllGrp_idCount() {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=grpmsdao.findGrp_idAllCount(sess);
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
	 * ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @return ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 */
	public List<TbGrpMsListInfo> getAllGrpid_InfoByPage(int pageNo, int pagesize) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpmsdao.findAllGrpMs_InfoGrpidByPage(sess, pageNo,
					pagesize);
			if (ml != null && ml.size() > 0) {
				List<TbGrpMsListInfo> result = new ArrayList<TbGrpMsListInfo>();
				for (Object obj : ml) {
					TbGrpMsListInfo me = (TbGrpMsListInfo) obj;
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

	/**
	 * ��ȡָ��Ⱥ���������Ϣ����
	 * 
	 * @param grp_id
	 *            Ⱥ���
	 * @return ��ȡָ��Ⱥ���������Ϣ����
	 */
	public int getGrp_idCount(String grp_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=grpmsdao.findGrp_idCount(sess, grp_id);
			if ( count!= null) {
				tx.commit();
				return count;
			} else {
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
	 * ����Ⱥ���ն˶�Ӧ��ϵ�а�Ⱥ���ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @param grp_id
	 *            Ⱥ���
	 * @return ����Ⱥ���ն˶�Ӧ��ϵ�а�Ⱥ���ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 */
	public List<TbGrpMsListInfo> getGrpid_InfoByPage(int pageNo, int pagesize,
			String grp_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpmsdao.findAllGrpid_InfoByPage(sess, pageNo, pagesize,
					grp_id);
			if (ml != null && ml.size() > 0) {
				List<TbGrpMsListInfo> result = new ArrayList<TbGrpMsListInfo>();
				for (Object obj : ml) {
					TbGrpMsListInfo me = (TbGrpMsListInfo) obj;
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

	/**
	 * ��ȡ�ն�������Ϣ���� Ⱥ��������Ϣ�ܼ�¼��
	 * 
	 * @return ��ȡȺ��������Ϣ����
	 */
	public int getAllms_idCount() {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=grpmsdao.findMs_idAllCount(sess);
			if (count!= null) {
				tx.commit();
				return count;
			} else {
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
	 * ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @return ����Ⱥ���ն˶�Ӧ��ϵָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 */
	public List<TbGrpMsListInfo> getAllMsid_InfoByPage(int pageNo, int pagesize) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpmsdao.findAllGrpMs_InfoMsidByPage(sess, pageNo,
					pagesize);
			if (ml != null && ml.size() > 0) {
				List<TbGrpMsListInfo> result = new ArrayList<TbGrpMsListInfo>();
				for (Object obj : ml) {
					TbGrpMsListInfo me = (TbGrpMsListInfo) obj;
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
			Integer count=grpmsdao.findMs_idCount(sess, ms_id);
			if (count!= null) {
				tx.commit();
				return count;
			} else {
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
	 * Ⱥ���ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @param ms_id
	 *            �ն˺�
	 * @return ����Ⱥ���ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 */
	public List<TbGrpMsListInfo> getMsid_InfoByPage(int pageNo, int pagesize,
			String ms_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpmsdao.findAllMsid_InfoByPage(sess, pageNo, pagesize,
					ms_id);
			if (ml != null && ml.size() > 0) {
				List<TbGrpMsListInfo> result = new ArrayList<TbGrpMsListInfo>();
				for (Object obj : ml) {
					TbGrpMsListInfo me = (TbGrpMsListInfo) obj;
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

	/**
	 * ����grp_id,ms_id ɾ����¼
	 * 
	 * @param grpmsidlist
	 */
	public Boolean deleteGrpMs(String[] grpmsidlist) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
		   for(int i=0;i<grpmsidlist.length;i++){
			   StringBuffer grpmslist = new StringBuffer(grpmsidlist[i]);
			while ((grpmslist.indexOf(",")) > 0) {
				int start = 0;
				int end = grpmslist.indexOf(",");
				String msid = grpmslist.substring(start, end);
				grpmslist.delete(start, end + 1);
				String grpid = grpmslist.toString();
				TbGrpMsListInfo tbgrpmsinfo = grpmsdao.get(sess, grpid, msid);
				if (tbgrpmsinfo != null) {
					grpmsdao.delete(sess, tbgrpmsinfo);
				}
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

	/**
	 * ����grp_idɾ����¼
	 * 
	 * @param grp_id
	 *            Ⱥ�����
	 */
	public void GrpMsInfodeletebyGrp_id(String grp_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			grpmsdao.deleteGrpMsInfoByGrp_id(sess, grp_id);
			tx.commit();
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}

	/**
	 * ����ms_idɾ����¼
	 * 
	 * @param ms_id
	 *            Ⱥ�����
	 */
	public void GrpMsInfodeletebyms_id(String ms_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			grpmsdao.deleteGrpMsInfoByMs_id(sess, ms_id);
			tx.commit();
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}

	/**
	 * ����һ��Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param grp_id
	 *            Ⱥ�����
	 * @param ms_id
	 *            �ն˺�
	 * @return �´��ն˵�����,�������ʧ�ܣ�����null��
	 */
	public String createGrpMsInfo(String grp_id, String ms_id) {
		Transaction tx=null;
		String ret=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbGrpMsListInfo tbgrpmsinfo = grpmsdao.get(sess, grp_id, ms_id);
			if (tbgrpmsinfo == null) {
				int iscreator = 0;
				// �ж�������ǲ�������ն˴���
				if (grpdao.findGrp_InfobyMs_id(sess, grp_id, ms_id)) {
					iscreator = 1;
				}
				TbGrpMsListInfo grpms = new TbGrpMsListInfo();
				Date date = new Date();
				grpms.setGrp_id(grp_id);
				grpms.setMs_id(ms_id);
				grpms.setIs_creator(iscreator);
				grpms.setCreate_time(date);
				TbGrpMsListInfo grpmsinfo = grpmsdao.get(sess,ms_id);
				if(grpmsinfo!=null){  //˵������ն˺��Ѿ���Ⱥ�齨���˶�Ӧ��ϵ�����½�����Ⱥ���Ӧ��ϵʱ��Ⱥ�����ñ�Ϊ��ֹ;���������Ⱥ�����ñ�Ϊ��ֹ��Ϊ�ʼ�
					grpms.setConfig(1);  //1Ϊ��ֹ  0Ϊ����Ĭ��Ϊ0
				}
				ret=grpmsdao.save(sess, grpms);
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
	 * @param grpid
	 * @param msid
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
	public int getGrpMs_SearchCount(String grpid, String msid,String pagentid,String childagentid,String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=grpmsdao.findGrpMs_SearchCount(sess,grpid,msid,pagentid,childagentid,ep);
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
	    * @param grpid
	    * @param msid
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
	public List getGrpMs_SearchByPage(int pageNo,
			int pageSize, String grpid, String msid,String pagentid,String childagentid,String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpmsdao.findGrpMs_SearchByPage(sess, pageNo, pageSize,pagentid,childagentid,ep,
					grpid,msid);
			if (ml != null && ml.size() > 0) {
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

	public List getMs_info(String grpid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpmsdao.findAllms_bygrpid(sess,grpid);
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

	public String createGrpMsInfo_ByGrpid(String grpid, String[] msid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			//ȫ��ɾ��
			if(msid==null||msid.length<1){
				grpmsdao.deleteGrpMsInfoByGrp_id(sess, grpid);
				tx.commit();
				return new String("ɾ���ɹ���");
			}
			
			for(int i=0;i<msid.length;i++) {
				TbGrpMsListInfo tbgrpmsinfo = grpmsdao.get(sess, grpid, msid[i]);
				if (tbgrpmsinfo == null){
					int iscreator = 0;
					// �ж�������ǲ�������ն˴���
					if (grpdao.findGrp_InfobyMs_id(sess, grpid, msid[i])) {
						iscreator = 1;
					}
					TbGrpMsListInfo grpms = new TbGrpMsListInfo();
					Date date = new Date();
					grpms.setGrp_id(grpid);
					grpms.setMs_id(msid[i]);
					grpms.setIs_creator(iscreator);
					grpms.setCreate_time(date);
					TbGrpMsListInfo grpmsinfo = grpmsdao.get(sess,msid[i]);
					if(grpmsinfo!=null){  //˵������ն˺��Ѿ���Ⱥ�齨���˶�Ӧ��ϵ�����½�����Ⱥ���Ӧ��ϵʱ��Ⱥ�����ñ�Ϊ��ֹ;���������Ⱥ�����ñ�Ϊ��ֹ��Ϊ�ʼ�
						grpms.setConfig(1);  //1Ϊ��ֹ  0Ϊ����Ĭ��Ϊ0
					}
					grpmsdao.save(sess, grpms);
					
				}	
			}
			/**
			 * ɾ��
			 */
			grpmsdao.delete(sess, grpid, msid);
			
			tx.commit();
			return new String("����ɹ���");
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return new String("���ʧ�ܣ�");
	}

	
	public List getGrp_info(String msid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpmsdao.findAllgrp_bymsid(sess,msid);
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

	public String createGrpMsInfo_ByMsid(String msid, String[] grpid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			
			//ȫ��ɾ��
			if(grpid==null||grpid.length<1){
				grpmsdao.deleteGrpMsInfoByMs_id(sess, msid);
					tx.commit();
					return new String("ɾ���ɹ���");
			}
			
			for(int i=0;i<grpid.length;i++) {
				TbGrpMsListInfo tbgrpmsinfo = grpmsdao.get(sess, grpid[i], msid);
				if (tbgrpmsinfo == null){
					int iscreator = 0;
					// �ж�������ǲ�������ն˴���
					if (grpdao.findGrp_InfobyMs_id(sess, grpid[i], msid)) {
						iscreator = 1;
					}
					TbGrpMsListInfo grpms = new TbGrpMsListInfo();
					Date date = new Date();
					grpms.setGrp_id(grpid[i]);
					grpms.setMs_id(msid);
					grpms.setIs_creator(iscreator);
					grpms.setCreate_time(date);
					TbGrpMsListInfo grpmsinfo = grpmsdao.get(sess,msid);
					if(grpmsinfo!=null){  //˵������ն˺��Ѿ���Ⱥ�齨���˶�Ӧ��ϵ�����½�����Ⱥ���Ӧ��ϵʱ��Ⱥ�����ñ�Ϊ��ֹ;���������Ⱥ�����ñ�Ϊ��ֹ��Ϊ�ʼ�
						grpms.setConfig(1);  //1Ϊ��ֹ  0Ϊ����Ĭ��Ϊ0
					}
					grpmsdao.save(sess, grpms);
				}	
			}
			
			/**
			 * ɾ��
			 */
			grpmsdao.delete(sess, grpid, msid);
			tx.commit();
			return new String("��ӳɹ���");
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return new String("���ʧ��!");	
	}

	
	public TbGrpMsListInfo getGrpMs_ByGrpMsid(String grpid, String msid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
				TbGrpMsListInfo tbgrpmsinfo = grpmsdao.get(sess, grpid, msid);
				if (tbgrpmsinfo != null) {
					return tbgrpmsinfo;
				}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	public String modifyGrpMs(String grpid,String rgrpid, String msid, String config) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			//getInfoByMs
			tx = sess.beginTransaction();
			List grpmsList = grpmsdao.getInfoByMs(sess, msid);
			TbGrpMsListInfo grpmsInfo = new TbGrpMsListInfo();
			String isHave = "";
			if(grpmsList != null){
				for(int i = 0 ; i < grpmsList.size();i++){
					TbGrpMsListInfo grpms = new TbGrpMsListInfo(); 
					grpms = (TbGrpMsListInfo)grpmsList.get(i);
					if(grpms.getConfig()== 0 && !grpms.getGrp_id().equals(rgrpid)){
						isHave = "";
					}else if(grpms.getGrp_id().equals(rgrpid) && grpms.getMs_id().equals(msid)){
						isHave = "0";
						grpmsInfo = grpms;
					}
				}
				if(isHave.equals("0")){
					grpmsdao.setConfig(sess,grpid,rgrpid,msid,Integer.valueOf(config));
				}else{
					tx.commit();
					return null;
				}
			}
//				TbGrpMsListInfo tbgrpmsinfo = grpmsdao.get(sess, rgrpid, msid);
				//if (tbgrpmsinfo != null) {
//					 if(Integer.valueOf(config)==0){  //1Ϊ��ֹ  0Ϊ����Ĭ��Ϊ0
//						 grpmsdao.setConfig(sess,grpid,rgrpid,msid,Integer.valueOf(config)); //��msid
//						 tbgrpmsinfo.setConfig(0);
//					 }else{
//						 grpmsdao.setConfig(sess,grpid,rgrpid,msid,Integer.valueOf(config)); //��msid
//						 tbgrpmsinfo.setConfig(1);
//					 }
				//}
			tx.commit();
			return new String("sucess");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	@Override
	public TbGrpMsListInfo getGrp(String msId) {
		// TODO Auto-generated method stub
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
				TbGrpMsListInfo tbgrpmsinfo = grpmsdao.getGrp(sess, msId);
				if (tbgrpmsinfo != null) {
					return tbgrpmsinfo;
				}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	@Override
	public Integer grpMsCount(String grpId) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			int msCount = grpmsdao.grpMsCount(sess, grpId);
			tx.commit();
			return msCount;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	@Override
	public Integer grpOnLineMsCount(String grpId) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			int msCount = grpmsdao.grpOnLineMsCount(sess, grpId);
			tx.commit();
			return msCount;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}


	@Override
	public boolean deleteGrpMsByMsId(String msId) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			grpmsdao.deleteGrpMsInfoByMs_id(sess, msId);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return true;
	}

	@Override
	public boolean changeGrpMsConfig(String msId, String grpId) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			//getInfoByMs
			tx = sess.beginTransaction();
			List grpmsList = grpmsdao.getInfoByMs(sess, msId);
			if(grpmsList != null){
				for(int i = 0 ; i < grpmsList.size();i++){
					TbGrpMsListInfo grpms = new TbGrpMsListInfo();
					grpms = (TbGrpMsListInfo)grpmsList.get(i);
					if(grpms.getConfig() == 0){
						grpmsdao.setConfig(sess,grpms.getGrp_id(),grpms.getGrp_id(),grpms.getMs_id(),1);
					}
					if(grpms.getMs_id().equals(msId) && grpms.getGrp_id().equals(grpId)){
						if(grpms.getConfig() == 1){
							grpmsdao.setConfig(sess,grpms.getGrp_id(),grpms.getGrp_id(),grpms.getMs_id(),0);
						}
					}
				}
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
	}

	@Override
	public List getMsState(String grpid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = grpmsdao.findAllMsStatesByGrpid(sess,grpid);
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


}
