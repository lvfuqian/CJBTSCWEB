package com.elegps.tscweb.serivce.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.AdvanceDaoFactory;
import com.elegps.tscweb.dao.BalanceDaoFactory;
import com.elegps.tscweb.dao.impl.AdvanceDaoHibernate;
import com.elegps.tscweb.dao.impl.BalanceDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbORGAdvanceInfo;
import com.elegps.tscweb.model.TbORGBalanceInfo;
import com.elegps.tscweb.model.TbORGChargeInfo;
import com.elegps.tscweb.serivce.AdvanceManager;

public class AdvanceManagerImpl implements AdvanceManager {
	
	private AdvanceDaoFactory advancedao;
	private BalanceDaoFactory balancedao;
    public AdvanceManagerImpl() throws MessageException {
		try {
			advancedao = new AdvanceDaoHibernate();
			balancedao =new BalanceDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("��ʼ��ҵ���߼���������쳣...");
		}

	}
	
  /**
   * ���ؼ�¼������ҳ��
   */
	public int getPageCount(int count, int pageSize) {
			return (count + pageSize - 1) / pageSize;
	}

  /**
   * ����ָ��ҳ�ļ�¼
   */
	public List getAdvance_SearchByPage(int pageNo, int pageSize, String pagentid,String childagentid,
			String ep,String advanceresult) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = advancedao.findAdvance_SearchByPage(sess, pageNo, pageSize,
					pagentid,childagentid,ep,advanceresult);
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

  /**
   * ����ָ���������ܼ�¼��
   */
	public int getAdvance_SearchCount(String pagentid,String childagentid, String ep,String advancereulst) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=advancedao.findadvance_Count(sess, pagentid,childagentid,ep,advancereulst);
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
	 * ����Ԥ��ֵ��Ϣ
	 */
public String createAdvance(String orgtype, String orgid, String advance,
		String advanename) {
	Date date = new Date();
	Transaction tx=null;
	String ret=null;
	try {
		Session sess = HibernateUtil.currentSession();
		tx = sess.beginTransaction();
		TbORGAdvanceInfo orgadvance = new TbORGAdvanceInfo();
		orgadvance.setOrgType(Integer.parseInt(orgtype));
		orgadvance.setOrgId(Integer.parseInt(orgid));
		orgadvance.setAdvanceCash(Integer.parseInt(advance));
		orgadvance.setAdvancePerson(advanename);
		orgadvance.setAdvanceDate(date);
		orgadvance.setValidateResult(0);
		ret=advancedao.save(sess, orgadvance);
		tx.commit();
		return ret;				 
	} catch (Exception e) {
		if (null != tx)
			tx.rollback();
		e.printStackTrace();
	} finally {
		HibernateUtil.closeSession();
	}
	return null;
}

	public TbORGAdvanceInfo getTbORGAdvanceInfo_byadvancetid(String advanceid) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbORGAdvanceInfo advaneinfo=advancedao.get(sess, advanceid);
			if(advaneinfo!=null){
				tx.commit();
				return advaneinfo;
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

	public String modifyAdvanece(String advanceid, String advance) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbORGAdvanceInfo advanceinfo = advancedao.get(sess, advanceid);
			if (advanceinfo != null) {
				advanceinfo.setAdvanceCash(Integer.parseInt(advance));
				sess.update(advanceinfo);
				tx.commit();
				return new String("�޸ĳɹ���");
			}		
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return new String("�޸�ʧ�ܣ�");
	}

	public Boolean deleteAdvance(String[] advanceidlist) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			for(int i=0;i<advanceidlist.length;i++) {
				   String advanceid = advanceidlist[i];
				   advancedao.delete(sess, advanceid);
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

	public String validate(String advanceid, String aresult, String remark,
			String validatename) {
		Transaction tx=null;
		Date date = new Date();
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbORGAdvanceInfo advanceinfo = advancedao.get(sess, advanceid);
			if (advanceinfo != null) {
				advanceinfo.setValidateResult(Integer.parseInt(aresult));
				advanceinfo.setRemark(remark);
				advanceinfo.setValidatePerson(validatename);
				advanceinfo.setValidateDate(date);
				sess.update(advanceinfo);
				if(aresult.equals("1")){  //��ʾ��֤�ɹ�
					//��¼��ֵ�ɹ�����ʷ��¼
					TbORGChargeInfo chargeinfo=new TbORGChargeInfo();
					chargeinfo.setOrgId(advanceinfo.getOrgId());
					chargeinfo.setOrgType(advanceinfo.getOrgType());
					chargeinfo.setChargeDate(advanceinfo.getValidateDate());
					chargeinfo.setChargeCash(advanceinfo.getAdvanceCash());
					chargeinfo.setAdvancePerson(advanceinfo.getAdvancePerson());
					chargeinfo.setValidatePerson(advanceinfo.getValidatePerson());
					chargeinfo.setRemark(advanceinfo.getRemark());
					sess.save(chargeinfo);
					//���³�ֵ�ɹ��������Ϣ
					TbORGBalanceInfo banlanceinfo=balancedao.get(sess, advanceinfo.getOrgType().toString(), advanceinfo.getOrgId().toString());
					if(banlanceinfo!=null){
						banlanceinfo.setBalanceCash(banlanceinfo.getBalanceCash()+advanceinfo.getAdvanceCash());
						sess.update(banlanceinfo);
					}else{
						TbORGBalanceInfo banlance=new TbORGBalanceInfo();
						banlance.setOrgId(advanceinfo.getOrgId());
						banlance.setOrgType(advanceinfo.getOrgType());
						banlance.setBalanceCash(advanceinfo.getAdvanceCash());
						sess.save(banlance);
					}
				}
				tx.commit();
				return new String("��֤�ɹ���");
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
	 * ��ѯ��ֵ��ʷ��¼����
	 */
	public int getCharge_SearchCount(String pagentid,String childagentid,String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=advancedao.getCharge_Count(sess, pagentid,childagentid,ep);
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
	 * ��ѯ��ֵ��ʷ��¼
	 */
	public List getCharge_SearchByPage(int pageNo, int pageSize, String pagentid,String childagentid,
			String ep) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = advancedao.getCharge_SearchByPage(sess, pageNo, pageSize,pagentid,childagentid,ep);
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
