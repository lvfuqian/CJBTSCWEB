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
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}

	}

	/**
	 * 返回所有群组类型信息个数 rutrun 所有群组类型信息人数
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
	 * 返回特定页面所有群组信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @return 指定页的用户的状态为1(正常)全部群组信息 按终端类型排序
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
	 * 返回特定指定条件群组类型信息个数
	 * 
	 * @param grp_type
	 *            群组类型
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
	 * 返回特定群组类型页面所有群组信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @param ms_type
	 *            群组类型
	 * @return 指定群组类型,群组的有效状态为1(正常)页的全部群组信息
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
	 * 返回特定指定条件群组信息
	 * 
	 * @param grpid
	 *            群组号码
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
	 * 获取所有群组在线通话态信息数量
	 * 
	 * @return 群组在线通话态信息数量
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
	 * 获取群组在线通话态信息数量
	 * 
	 * @param stauts
	 *            群组线通话态信息对应总记录数
	 * @return 群组在线通话态信息数量
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
			returnvalue=grpdao.grpall_byid(sess, grpid);  //根据群组找出所有子群组,先删除子群组，再删除父群组，同时删除群组与终端对应关系
//				TbGrpInfo tbgrp = grpdao.get(sess, grpid);
//				if (tbgrp != null) {
					//sess.delete(tbgrp);
					// 删除TbGrpMsListInfo对象的中grp_id等于grpid
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
	 * 根据grp_id做模糊查询
	 * 
	 * @return 群组grp_id做模糊查询的数量
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
	 * 获取群组有效状态信息数量 群组有效状态对应总记录数
	 * 
	 * @return 群组有效状态信息的数量
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
	 * 返回所有群组有效状状态页面所有群组信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @return 所有群组有效状状态页面所有群组信息
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
	 * 根据grp_id更新记录列表 更新记录列表
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
	 * 所有群组信息
	 * 
	 * @return 指定页的全部群组信息
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
	 * 所有群组信息
	 * 
	 * @return 指定页的全部群组信息
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

	
	/**群组表查询数量
	 * @param grp_type  群组类型
	 * @param statue  通话状态
	 * @param flag  有效状态
	 * @param grp_id  群组号码
	 * @param grp_name  群组名称
	 * @return 群组模糊查询的数量
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

	
	/**群组表查询信息
	 * @param grp_type  群组类型
	 * @param statue  通话状态
	 * @param flag  有效状态
	 * @param grp_id  群组号码
	 * @param grp_name  群组名称
	 * @return 群组模糊查询的信息
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
    * 查询所有用户单位信息 zr
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
	 * 根据用户单位ID查询用户单位信息 zr
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
	 * 根据群组ID查询群组所属的用户单位 zr
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
