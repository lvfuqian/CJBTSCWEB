package com.elegps.tscweb.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.dao.GrpDaoFactory;
import com.elegps.tscweb.dao.GrpMsDaoFactory;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;

public class GrpMsDaoHibernate implements GrpMsDaoFactory {
 
	/**
	 * 获取群组所有信息数量 群组所有信息总记录数
	 * 
	 * @return 获取群组所有信息数量
	 */
	public Integer findGrp_idAllCount(Session sess) {
		try {
			Object obj = sess
					.createQuery(
							"select count(m.grp_id) from TbGrpMsListInfo as m order by m.grp_id desc")
					.uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;

	}

	public List findAllGrpMs_InfoGrpidByPage(Session sess, int pageNo,
			int pagesize) {
		try {
			int offset = (pageNo - 1) * pagesize;
			List list=sess.createQuery(
			"from TbGrpMsListInfo m order by m.create_time desc")
			.setFirstResult(offset).setMaxResults(pagesize).list();
			if(list!=null){
				return list;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
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
	public Integer findGrp_idCount(Session sess, String grp_id) {
		try {
			Object obj = sess.createQuery(
					"select count(m.grp_id) from TbGrpMsListInfo as m where m.grp_id='"
							+ grp_id + "' order by m.ms_id desc")
					.uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
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
	public List findAllGrpid_InfoByPage(Session sess, int pageNo, int pagesize,
			String grp_id) {
		try {
			int offset = (pageNo - 1) * pagesize;
			List list=sess.createQuery(
					"from TbGrpMsListInfo as m where m.grp_id='" + grp_id
					+ "' order by m.create_time desc").setFirstResult(offset)
			.setMaxResults(pagesize).list();
			if(list!=null){
				return list;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;

	}

	/**
	 * 获取终端所有信息数量 群组所有信息总记录数
	 * 
	 * @return 获取群组所有信息数量
	 */
	public Integer findMs_idAllCount(Session sess) {
		try {
			Object obj = sess
					.createQuery(
							"select count(m.ms_id) from TbGrpMsListInfo as m order by m.ms_id desc")
					.uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	public List findAllGrpMs_InfoMsidByPage(Session sess, int pageNo,
			int pagesize) {
		try {
			int offset = (pageNo - 1) * pagesize;
			List list=sess.createQuery(
			"from TbGrpMsListInfo m order by m.create_time desc")
			.setFirstResult(offset).setMaxResults(pagesize).list();
			if(list!=null){
				return list;
			}			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
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
	public Integer findMs_idCount(Session sess, String ms_id) {
		try {
			Object obj = sess.createQuery(
					"select count(m.ms_id) from TbGrpMsListInfo as m where m.ms_id='"
							+ ms_id + "' order by m.grp_id desc")
					.uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
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
	public List findAllMsid_InfoByPage(Session sess, int pageNo, int pagesize,
			String ms_id) {
		try {
			int offset = (pageNo - 1) * pagesize;
			List list=sess.createQuery(
					"from TbGrpMsListInfo as m where m.ms_id='" + ms_id
					+ "' order by m.create_time desc")
			.setFirstResult(offset).setMaxResults(pagesize).list();
			if(list!=null){
				return list;
			}			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	/**
	 * 根据主键加载终群组与端对应关系信息
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param ms_id
	 *            终端号
	 * @param grp_id
	 *            终端号
	 * @return 根据主键加载终群组与端对应关系信息
	 */
	public TbGrpMsListInfo get(Session sess, String grp_id, String ms_id) {
		try {
			List grpms = sess.createQuery(
					"from TbGrpMsListInfo m where m.ms_id='" + ms_id
							+ "' and grp_id='" + grp_id + "'").list();
			if (grpms != null && grpms.size() > 0) {
				return (TbGrpMsListInfo) grpms.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}
	
	/**
	 * 根据msid查找对应群组关系信息
	 * @param sess
	 * @param ms_id
	 * @return
	 */
	public List getInfoByMs(Session sess, String ms_id){
		try {
			List grpms = sess.createQuery(
					"from TbGrpMsListInfo m where m.ms_id='" + ms_id +"'").list();
			if (grpms != null && grpms.size() > 0) {
				return grpms;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}
	
	public void delete(Session sess, TbGrpMsListInfo tbgrpmsinfo) {
		try {
			sess.delete(tbgrpmsinfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}

	}

	/**
	 * 根据grp_id删除记录
	 * 
	 * @param grp_id
	 *            群组号码
	 */
	public void deleteGrpMsInfoByGrp_id(Session sess, String grp_id) {
		try {
			String hql = "delete TbGrpMsListInfo where grp_id=?";
			Query q = sess.createQuery(hql);
			q.setString(0, grp_id);
			q.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}

	}

	/**
	 * 根据ms_id删除记录
	 * 
	 * @param ms_id
	 *            群组号码
	 */
	public void deleteGrpMsInfoByMs_id(Session sess, String ms_id) {
		try {
			String hql = "delete TbGrpMsListInfo where ms_id=?";
			Query q = sess.createQuery(hql);
			q.setString(0, ms_id);
			q.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}

	}

	public String save(Session sess, TbGrpMsListInfo grpms) {
		try {
			sess.save(grpms);
			sess.flush();
			return grpms.getMs_id();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
		
	}

	
	/**
	 * 根据查询条件取得记录总数
	 * @param grpid
	 * @param msid
	 * @return  查询条件取得记录总数
	 */
	public Integer findGrpMs_SearchCount(Session sess, String grpid, String msid,String pagentid,String childagentid,String ep) {
		    String hql="select count(gm.grp_id) from TbGrpMsListInfo gm,TbMsInfo m,TbGrpInfo g where gm.ms_id=m.msId and gm.grp_id=g.grpid";
			String strgrp="";//群组
			String strms="";//终端
			String stragent="";// 代理商id
			String strEp="";   //企业id
			int pagent_id=Integer.parseInt(pagentid);  //一级代理商
			int cagent_id=Integer.parseInt(childagentid);  //二级代理商
			if(!msid.equals("")){//终端号码不是全部
				strms=" and gm.ms_id='"+msid+"'";
			}
			if(!grpid.equals("")){//群组号码不是全部
				strgrp=" and gm.grp_id='"+grpid+"'";
			}
			if(pagent_id==-1){   //说明是总部
				if(cagent_id==-1){    //直属企业(总部直接发展的企业)
					stragent=" and  E.Agent_Id ="+pagentid;
				}else{              //-2 所有企业（系统中所有企业）
					
				}
			}else{  //一级代理商(除总部外)
				if(cagent_id==-1)    //直属企业(指定一级代理商下的企业)
				{
					stragent=" and E.Agent_Id="+pagentid;
				}else if(cagent_id==-2){   //所有企业 (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业
					stragent=" and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+pagentid+" or A.Agent_PId="+pagentid+")";
				}else{     //指定二级代理商下的所有企业
					stragent=" and E.Agent_Id="+childagentid;
				}
			}
	
			if (!(ep.equals("-1"))) { // 用户单位不是全部
				strEp = " and g.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
						+ ep + stragent + ")";
			} else { // 为全部
				strEp = " and g.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
						+ stragent + ")";
			}
			
			
			hql+=strms+strgrp+strEp+" order by gm.create_time desc";
					
		try {
			Object obj = sess.createQuery(hql).uniqueResult();
			if (obj != null) {
				int grpmscount = (Integer) obj;
				return grpmscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	
	/**
	    * 根据指定页及查询条件取得记录信息
	    * @param pageNo
	    * @param pageSize
	    * @param grpid
	    * @param msid
	    * @return  指定页及查询条件取得记录信息
	    */
	public List findGrpMs_SearchByPage(Session sess, int pageNo, int pageSize,String pagentid,String childagentid,String ep,
			String grpid, String msid) {
		String hql="select gm.grp_id,g.grpname,gm.ms_id,m.msName,gm.config from TbGrpMsListInfo gm,TbMsInfo m,TbGrpInfo g where gm.ms_id=m.msId and gm.grp_id=g.grpid ";
		String strgrp="";//群组
		String strms="";//终端
		String stragent="";// 代理商id
		String strEp="";   //企业id
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商
		if(!msid.equals("")){//终端号码不是全部
			strms=" and gm.ms_id='"+msid+"'";
		}
		if(!grpid.equals("")){//群组号码不是全部
			strgrp=" and gm.grp_id='"+grpid+"'";
		}

		if(pagent_id==-1){   //说明是总部
			if(cagent_id==-1){    //直属企业(总部直接发展的企业)
				stragent=" and  E.Agent_Id ="+pagentid;
			}else{              //-2 所有企业（系统中所有企业）
				
			}
		}else{  //一级代理商(除总部外)
			if(cagent_id==-1)    //直属企业(指定一级代理商下的企业)
			{
				stragent=" and E.Agent_Id="+pagentid;
			}else if(cagent_id==-2){   //所有企业 (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业
				stragent=" and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+pagentid+" or A.Agent_PId="+pagentid+")";
			}else{     //指定二级代理商下的所有企业
				stragent=" and E.Agent_Id="+childagentid;
			}
		}

		if (!(ep.equals("-1"))) { // 用户单位不是全部
			strEp = " and g.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // 为全部
			strEp = " and g.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
					+ stragent + ")";
		}
		hql+=strms+strgrp+strEp+" order by gm.create_time desc";
		try {
			int offset = (pageNo - 1) * pageSize;
			ScrollableResults  srs =sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).scroll();
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("grpid",srs.getString(0));
				map.put("grpname", srs.getString(1));
				map.put("msid", srs.getString(2));
				map.put("msname", srs.getString(3));
				map.put("config", srs.getInteger(4));
				res.add(map);
			}
			if (res != null && res.size()>0) {
				return res;
			}			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	public List findAllms_bygrpid(Session sess,String grpid) {
		try {
			ScrollableResults srs = sess.createQuery(
					"select gm.ms_id as msid,m.msName as msname  from TbGrpMsListInfo gm,TbMsInfo m where  gm.ms_id=m.msId and gm.grp_id='" + grpid + "'  order by m.msId").scroll();
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("msid",srs.getString(0));
				map.put("msname", srs.getString(1));
                res.add(map);
            }
			if (res != null && res.size()>0) {
				return res;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	
	public List findAllgrp_bymsid(Session sess, String msid) {
		try {
			ScrollableResults srs = sess.createQuery(
					"select gm.grp_id as grp_id,m.grpname as grpname  from TbGrpMsListInfo gm,TbGrpInfo m where  gm.grp_id=m.grpid and gm.ms_id='" + msid + "'").scroll();
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("grpid",srs.getString(0));
				map.put("grpname", srs.getString(1));
                res.add(map);
            }
			if (res != null && res.size()>0) {
				return res;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	public TbGrpMsListInfo get(Session sess, String ms_id) {
		try {
			List grpms = sess.createQuery(
					"from TbGrpMsListInfo m where m.ms_id='" + ms_id
							+ "'").list();
			if (grpms != null && grpms.size() > 0) {
				return (TbGrpMsListInfo) grpms.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	
	public void setConfig(Session sess, String grpid,String rgrpid, String msid,int config) {
		try {
//			Transaction tx=null;
//			tx=sess.beginTransaction();
			sess.createQuery("update TbGrpMsListInfo m set  m.config='"+config+"', m.grp_id='"+grpid+"'  where m.ms_id='" + msid
							+ "' and m.grp_id='"+rgrpid+"'").executeUpdate();
//			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
	}

	/**
	 * 删除
	 */
	public void delete(Session sess, String grpid, String[] msid) {
		String msidlist="";
		for(int i=0;i<msid.length-1;i++){
			msidlist+="'"+msid[i]+"',";
		}
		msidlist+="'"+msid[msid.length-1]+"'";
		try{
			sess.createQuery("delete from TbGrpMsListInfo gm  where gm.grp_id='"+grpid+"' and gm.ms_id not in(" + msidlist+")").executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
	/**
	 * 删除
	 */
	public void delete(Session sess, String[] grpid, String msid) {
		String grpidlist="";
		for(int i=0;i<grpid.length-1;i++){
			grpidlist+="'"+grpid[i]+"',";
		}
		grpidlist+="'"+grpid[grpid.length-1]+"'";
		try{
			sess.createQuery("delete from TbGrpMsListInfo gm  where gm.ms_id='"+msid+"' and gm.grp_id not in(" + grpidlist+")").executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	@Override
	public TbGrpMsListInfo getGrp(Session sess, String msId) {
		// TODO Auto-generated method stub
		try {
			List grpms = sess.createQuery(
					"from TbGrpMsListInfo m where m.ms_id='" + msId
							+ "' and m.is_creator = 0 and m.config = 0").list();
			if (grpms != null && grpms.size() > 0) {
				return (TbGrpMsListInfo) grpms.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	@Override
	public Integer grpMsCount(Session sess, String grpId) {
		String hql = "select count(gm.ms_id) from TbGrpMsListInfo gm where gm.grp_id ='"+grpId+"'";
		try {
			Object obj = sess.createQuery(hql).uniqueResult();
			if (obj != null) {
				int grpmscount = (Integer) obj;
				return grpmscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	@Override
	public Integer grpOnLineMsCount(Session sess, String grpId) {
		String hql = "select count(m.msId) from TbGrpMsListInfo gm,TbMsInfo m where gm.grp_id='"+grpId+"' and gm.ms_id=m.msId and m.onlineStatus = 1 ";
		try {
			Object obj = sess.createQuery(hql).uniqueResult();
			if (obj != null) {
				int grpmscount = (Integer) obj;
				return grpmscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List findAllMsStatesByGrpid(Session sess, String grpid) {
		try {
			ScrollableResults srs = sess.createQuery(
					"select gm.ms_id as msid,m.msName as msname,m.onlineStatus from TbGrpMsListInfo gm,TbMsInfo m where  gm.ms_id=m.msId and gm.grp_id='" + grpid + "'  order by m.msId").scroll();
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("msId",srs.getString(0));
				map.put("msName", srs.getString(1));
				map.put("onlineStatus", srs.getInteger(2));
                res.add(map);
            }
			if (res != null && res.size()>0) {
				return res;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

}
