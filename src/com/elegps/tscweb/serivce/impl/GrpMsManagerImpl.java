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
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}

	}

	/**
	 * 获取群组所有信息数量 群组所有信息总记录数
	 * 
	 * @return 获取群组所有信息数量
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
	 * 返回群组终端对应关系指定页面所有群组终端对应关系信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @return 返回群组终端对应关系指定页面所有群组终端对应关系信息
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
	 * 获取指定群组号所有信息数量
	 * 
	 * @param grp_id
	 *            群组号
	 * @return 获取指定群组号所有信息数量
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
	 * 返回群组终端对应关系中按群组号指定页面所有群组终端对应关系信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @param grp_id
	 *            群组号
	 * @return 返回群组终端对应关系中按群组号指定页面所有群组终端对应关系信息
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
	 * 获取终端所有信息数量 群组所有信息总记录数
	 * 
	 * @return 获取群组所有信息数量
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
	 * 返回群组终端对应关系指定页面所有群组终端对应关系信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @return 返回群组终端对应关系指定页面所有群组终端对应关系信息
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
	 * 获取指定终端号所有信息数量
	 * 
	 * @param ms_id
	 *            终端号
	 * @return 获取指定终端号所有信息数量
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
	 * 群组终端对应关系中按终端号指定页面所有群组终端对应关系信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @param ms_id
	 *            终端号
	 * @return 返回群组终端对应关系中按终端号指定页面所有群组终端对应关系信息
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
	 * 根据grp_id,ms_id 删除记录
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
	 * 根据grp_id删除记录
	 * 
	 * @param grp_id
	 *            群组号码
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
	 * 根据ms_id删除记录
	 * 
	 * @param ms_id
	 *            群组号码
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
	 * 创建一条群组终端对应关系信息
	 * 
	 * @param grp_id
	 *            群组号码
	 * @param ms_id
	 *            终端号
	 * @return 新创终端的主键,如果创建失败，返回null。
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
				// 判断这个组是不是这个终端创建
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
				if(grpmsinfo!=null){  //说明这个终端号已经与群组建立了对应关系，则新建与别的群组对应关系时，群组配置变为阻止;如果不存在群组配置变为阻止就为允计
					grpms.setConfig(1);  //1为阻止  0为允许；默认为0
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
	 * 根据查询条件取得记录总数
	 * @param grpid
	 * @param msid
	 * @return  查询条件取得记录总数
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
	    * 根据指定页及查询条件取得记录信息
	    * @param pageNo
	    * @param pageSize
	    * @param grpid
	    * @param msid
	    * @return  指定页及查询条件取得记录信息
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
			//全部删除
			if(msid==null||msid.length<1){
				grpmsdao.deleteGrpMsInfoByGrp_id(sess, grpid);
				tx.commit();
				return new String("删除成功！");
			}
			
			for(int i=0;i<msid.length;i++) {
				TbGrpMsListInfo tbgrpmsinfo = grpmsdao.get(sess, grpid, msid[i]);
				if (tbgrpmsinfo == null){
					int iscreator = 0;
					// 判断这个组是不是这个终端创建
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
					if(grpmsinfo!=null){  //说明这个终端号已经与群组建立了对应关系，则新建与别的群组对应关系时，群组配置变为阻止;如果不存在群组配置变为阻止就为允计
						grpms.setConfig(1);  //1为阻止  0为允许；默认为0
					}
					grpmsdao.save(sess, grpms);
					
				}	
			}
			/**
			 * 删除
			 */
			grpmsdao.delete(sess, grpid, msid);
			
			tx.commit();
			return new String("保存成功！");
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return new String("添加失败！");
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
			
			//全部删除
			if(grpid==null||grpid.length<1){
				grpmsdao.deleteGrpMsInfoByMs_id(sess, msid);
					tx.commit();
					return new String("删除成功！");
			}
			
			for(int i=0;i<grpid.length;i++) {
				TbGrpMsListInfo tbgrpmsinfo = grpmsdao.get(sess, grpid[i], msid);
				if (tbgrpmsinfo == null){
					int iscreator = 0;
					// 判断这个组是不是这个终端创建
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
					if(grpmsinfo!=null){  //说明这个终端号已经与群组建立了对应关系，则新建与别的群组对应关系时，群组配置变为阻止;如果不存在群组配置变为阻止就为允计
						grpms.setConfig(1);  //1为阻止  0为允许；默认为0
					}
					grpmsdao.save(sess, grpms);
				}	
			}
			
			/**
			 * 删除
			 */
			grpmsdao.delete(sess, grpid, msid);
			tx.commit();
			return new String("添加成功！");
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return new String("添加失败!");	
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
//					 if(Integer.valueOf(config)==0){  //1为阻止  0为允许；默认为0
//						 grpmsdao.setConfig(sess,grpid,rgrpid,msid,Integer.valueOf(config)); //在msid
//						 tbgrpmsinfo.setConfig(0);
//					 }else{
//						 grpmsdao.setConfig(sess,grpid,rgrpid,msid,Integer.valueOf(config)); //在msid
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
