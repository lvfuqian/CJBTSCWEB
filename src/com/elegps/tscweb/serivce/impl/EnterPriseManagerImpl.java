package com.elegps.tscweb.serivce.impl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.EnterPriseDaoFactory;
import com.elegps.tscweb.dao.GrpDaoFactory;
import com.elegps.tscweb.dao.MsDaoFactory;
import com.elegps.tscweb.dao.impl.DdmsMsDaoHibernate;
import com.elegps.tscweb.dao.impl.EnterPriseDaoHibernate;
import com.elegps.tscweb.dao.impl.GpsMsDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpDaoHibernate;
import com.elegps.tscweb.dao.impl.GrpMsDaoHibernate;
import com.elegps.tscweb.dao.impl.MsDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.serivce.AgentManager;
import com.elegps.tscweb.serivce.EnterPriseManager;




public class EnterPriseManagerImpl implements EnterPriseManager 
{
	
	private EnterPriseDaoFactory epdao;
	private GrpDaoFactory grpdao;
	private MsDaoFactory msdao;
	public EnterPriseManagerImpl() throws MessageException {
		try {
			epdao = new EnterPriseDaoHibernate();
			grpdao = new GrpDaoHibernate();
			msdao=new MsDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}

	}
	
	
	
	/**
	 * 用户单位数量查询
	 * @param aggent_id  代理商id
	 * @param ep_name  用户单位名称
	 * @return
	 */
	public int getEp_sertch(String aggent_id, String childagentid, String ep_name, int ep_id,int r_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count=epdao.findEp_sertchCount(sess,aggent_id,childagentid,ep_name, ep_id, r_id);
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
	 * 根据每页记录数，总记录数获取总页数
	 * @param count 总记录数
	 * @param pageSize 每页显示的记录数
	 * @return 计算得到的总页数
	 */
	public int getPageCount(int count, int pageSize) {
		return (count + pageSize - 1) / pageSize;
	}


	 /**
	   * 
	   * @param pageNo
	   * @param pageSize
	   * @param agent_id
	   * @param ep_name
	   * @return  跟据用户单位ep_name做模糊查询页的全部用户单位信息
	   */
	public List<TbEnterpriseInfo> getEpinfoby_mspage(int pageNo, int pageSize,
			String agent_id,String childagentid, String ep_name, int ep_id,int r_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = epdao.getEpInfo(sess, pageNo, pageSize,agent_id,childagentid,ep_name, ep_id, r_id);
			if (ml != null && ml.size() > 0) {
				List<TbEnterpriseInfo> result = new ArrayList<TbEnterpriseInfo>();
				for (Object obj : ml) {
					TbEnterpriseInfo me = (TbEnterpriseInfo) obj;
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






	public TbEnterpriseInfo getEpinfo_byepid(String epId) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbEnterpriseInfo epinfo=epdao.get(sess, epId);
			if(epinfo!=null){
				tx.commit();
				return epinfo;
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



	public String createEp(String ep_name, String ep_address, String ep_tel,String ep_tel1,
			String ep_email, String ep_url, String ep_man, String ep_man1, String ep_qq,
			String agent_id, String ep_remark) {
		Transaction tx=null;
		String ret=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbEnterpriseInfo epinfo = new TbEnterpriseInfo();
			epinfo.setEp_Name(ep_name);
			epinfo.setEp_Address(ep_address);
			epinfo.setEp_Tel(ep_tel);
			epinfo.setEp_Tel1(ep_tel1);
			epinfo.setEp_Email(ep_email);
			epinfo.setEp_URL(ep_url);
			epinfo.setEp_Man(ep_man);
			epinfo.setEp_Man1(ep_man1);
			epinfo.setEp_QQ(ep_qq);
			epinfo.setAgent_Id(Integer.parseInt(agent_id));
			epinfo.setEp_Remark(ep_remark);
			ret=epdao.save(sess, epinfo);				
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



	public String modifyEp(String agent_id,String ep_id, String ep_name, String ep_address,
			String ep_tel, String ep_tel1,String ep_email, String ep_url, String ep_man,String ep_man1,
			String ep_qq, String ep_remark) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbEnterpriseInfo info=(TbEnterpriseInfo)epdao.getEp_Byepname(sess,ep_id,ep_name);
			if(info!=null){
				return new String(ep_name+"已经存在，请重新输入!");
			}	
			TbEnterpriseInfo epinfo = epdao.get(sess, ep_id);
			if (epinfo != null) {
				epinfo.setEp_Name(ep_name);
				epinfo.setEp_Address(ep_address);
				epinfo.setEp_Tel(ep_tel);
				epinfo.setEp_Tel1(ep_tel1);
				epinfo.setEp_Email(ep_email);
				epinfo.setEp_URL(ep_url);
				epinfo.setEp_Man(ep_man);
				epinfo.setEp_Man1(ep_man1);
				epinfo.setEp_QQ(ep_qq);
				epinfo.setEp_Remark(ep_remark);
				epinfo.setAgent_Id(Integer.parseInt(agent_id));
				sess.update(epinfo);
				tx.commit();
				return new String("修改成功！");
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



	public Boolean deleteEp(String[] epidlist) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			for(int i=0;i<epidlist.length;i++) {
				   String epid = epidlist[i];
				    epdao.delete(sess, epid);
					grpdao.deleteGrpInfoByEp_id(sess, epid);
					msdao.deleteMsInfoByEp_id(sess,epid);
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



	public List getEpinfo_byagentid(String pagentid,String childagentid,int a_id,int r_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = epdao.getEpInfo_byagentid(sess,pagentid,childagentid, a_id, r_id);
			if (ml != null && ml.size() > 0) {
				List<TbEnterpriseInfo> result = new ArrayList<TbEnterpriseInfo>();
				for (Object obj : ml) {
					TbEnterpriseInfo me = (TbEnterpriseInfo) obj;
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
	
	
	public List getEpinfo_byeid(String pagentid,String childagentid,int ep_id,int r_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = epdao.getEpinfo_byeid(sess,pagentid,childagentid, ep_id, r_id);
			if (ml != null && ml.size() > 0) {
				List<TbEnterpriseInfo> result = new ArrayList<TbEnterpriseInfo>();
				for (Object obj : ml) {
					TbEnterpriseInfo me = (TbEnterpriseInfo) obj;
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
	 * 根据企业名称查询信息 zr
	 * @param agentname
	 * @return
	 */
	public TbEnterpriseInfo getEpByName(String epname){
		Transaction tx=null;
		try{
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbEnterpriseInfo names=(TbEnterpriseInfo)epdao.getEp_Byepname(sess, epname);
			if(names!=null){
				return names;
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
	 * 根据企业ID查询所属代理商 zr
	 */
	public TbAgentInfo getAgent_ByEpName(String epid) {
		Transaction tx=null;
		try{
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbAgentInfo agentnames=(TbAgentInfo)epdao.getAgent_ByEpid(sess, epid);
			if(agentnames!=null){
				return agentnames;
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



	public List getChargeEpinfo_byepid(String ep_id) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List epinfo=epdao.getEpInfo(sess, ep_id);
			if(epinfo!=null){
				return epinfo;
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
	
	public List getEpinfo_byagentid(String agent) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = epdao.getEpInfo_byagentid(sess,agent);
			if (ml != null && ml.size() > 0) {
				List<TbEnterpriseInfo> result = new ArrayList<TbEnterpriseInfo>();
				for (Object obj : ml) {
					TbEnterpriseInfo me = (TbEnterpriseInfo) obj;
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



	@Override
	public Boolean updateEp(TbEnterpriseInfo ep) {
		Transaction tx=null;
		try{
			Session session=HibernateUtil.currentSession();
			tx=session.beginTransaction();
			if(ep!=null){
				
				epdao.updateInfo(session, ep);
				tx.commit();
				
				return true;
			}else{
				tx.commit();
				return false;
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				e.printStackTrace();
			}
		}
		finally{
			HibernateUtil.closeSession();
		}
		return false;
	}
	
	@Override
	public String createEp(String epName, String epAddress, String epTel,
			String epTel1, String productID, String sIID, String bizID,
			String areaCode, String custID, String custAccount, String summary,
			String agentId) {
		Transaction tx=null;
		String ret=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbEnterpriseInfo epinfo = new TbEnterpriseInfo();
			epinfo.setEp_Name(epName);
			epinfo.setEp_Address(epAddress);
			epinfo.setEp_Tel(epTel);
			epinfo.setEp_Tel1(epTel1);
			epinfo.setAgent_Id(Integer.parseInt(agentId));
			ret=epdao.save(sess, epinfo);				
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

}