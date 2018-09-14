package com.elegps.tscweb.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import com.elegps.tscweb.dao.GpsMsDaoFactory;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;

public class GpsMsDaoHibernate implements GpsMsDaoFactory {

	/**
	 * 获取GPS服务商登录账号所有信息数量 GPS服务商登录账号所有信息总记录数
	 * 
	 * @return 获取GPS服务商登录账号所有信息数量
	 */
	public Integer findGps_idAllCount(Session sess) {
		try {
			Object obj = sess
					.createQuery(
							"select count(m.gpsop_id) from TbGpsMsListInfo as m order by m.gpsop_id")
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
	 * 返回GPS厂商终端对应关系指定页面所有GPS厂商终端对应关系信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @return 返回GPS厂商终端对应关系指定页面所有GPS厂商终端对应关系信息
	 */
	public List findAllGpsMs_InfoGrpidByPage(Session sess, int pageNo,
			int pagesize) {
		try {
			int offset = (pageNo - 1) * pagesize;
			List list=sess.createQuery(
			"from TbGpsMsListInfo m order by m.create_time desc")
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
	 * 获取指定GPS厂商所有信息数量
	 * 
	 * @param gps_id
	 *            GPS厂商帐号
	 * @return 获取指定GPS厂商所有信息数量
	 */
	public Integer findGps_idCount(Session sess, String gps_id) {
		try {
			Object obj = sess.createQuery(
					"select count(m.gpsop_id) from TbGpsMsListInfo as m where m.gpsop_id='"
							+ gps_id + "' order by m.l_ms_id desc")
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
	 * 返回GPS厂商终端对应关系中按GPS厂商帐号指定页面所有GPS厂商终端对应关系信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @param gps_id
	 *            GPS厂商帐号
	 * @return 返回GPS厂商终端对应关系中按GPS厂商帐号指定页面所有GPS厂商终端对应关系信息
	 */
	public List findAllGpsid_InfoByPage(Session sess, int pageNo, int pagesize,
			String gps_id) {
		try {
			int offset = (pageNo - 1) * pagesize;
			List list=sess.createQuery(
					"from TbGpsMsListInfo as m where m.gpsop_id='" + gps_id
					+ "' order by m.create_time desc").setFirstResult(
			offset).setMaxResults(pagesize).list();
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
	 * 返回GPS厂商终端对应关系指定页面所有GPS厂商终端对应关系信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @return 返回GPS厂商终端对应关系指定页面所有GPS厂商终端对应关系信息
	 */
	public List findAllGpsMs_InfoMsidByPage(Session sess, int pageNo,
			int pagesize) {
		try {
			int offset = (pageNo - 1) * pagesize;
			List list=sess.createQuery(
			"from TbGpsMsListInfo m order by m.create_time desc")
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
					"select count(m.l_ms_id) from TbGpsMsListInfo as m where m.l_ms_id='"
							+ ms_id + "'").uniqueResult();
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
	 * GPS厂商终端对应关系中按终端号指定页面所有GPS厂商终端对应关系信息
	 * 
	 * @param pageNo
	 *            指定页码
	 * @param pagesize
	 *            每页显示的条数
	 * @param ms_id
	 *            终端号
	 * @return 返回GPS厂商终端对应关系中按终端号指定页面所有GPS厂商终端对应关系信息
	 */
	public List findAllMsid_InfoByPage(Session sess, int pageNo, int pagesize,
			String ms_id) {
		try {
			int offset = (pageNo - 1) * pagesize;
			List list=sess.createQuery(
					"from TbGpsMsListInfo as m where m.l_ms_id='" + ms_id
					+ "' order by m.create_time desc").setFirstResult(offset).setMaxResults(pagesize).list();
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

	public TbGpsMsListInfo get(Session sess, String gps_id, String ms_id) {
		try {
			List gpsms = sess.createQuery(
					"from TbGpsMsListInfo m where m.gpsop_id='" + gps_id
							+ "' and m.l_ms_id='" + ms_id + "'").list();
			if (gpsms != null && gpsms.size() > 0) {
				return (TbGpsMsListInfo) gpsms.get(0);
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
	 * 删除GPS厂商与终端对应关系信息
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param tbgrpmsinfo
	 *            GPS厂商与终端对应关系信息对象
	 */
	public void delete(Session sess, TbGpsMsListInfo tbgpsmsinfo) {
		try {
			sess.delete(tbgpsmsinfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}

	}

	public String save(Session sess, TbGpsMsListInfo gpsms) {
		try {
			sess.save(gpsms);
			sess.flush();
			return gpsms.getGpsop_id().toString();
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
	 * 根据gps_id删除记录
	 * 
	 * @param gps_id
	 *            GPS号码
	 */
	public void deleteGpsMsInfoByGps_id(Session sess, String gps_id) {
		try {
			String hql = "delete TbGpsMsListInfo where gpsop_id=?";
			Query q = sess.createQuery(hql);
			q.setString(0, gps_id);
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
	public void deleteGpsMsInfoByMs_id(Session sess, String ms_id) {
		try {
			String hql = "delete TbGpsMsListInfo where l_ms_id=?";
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

	
	
	/**
	 * 根据查询条件取得记录总数
	 * @param gpsid
	 * @param msid
	 * @return  查询条件取得记录总数
	 */
	public Integer findGpsMs_SearchCount(Session sess, String gpsid, String msid,String pagentid,String childagentid,String ep) {
		String hql="select count(gm.gpsop_id) from TbGpsMsListInfo gm,TbMsInfo m where gm.l_ms_id=m.msId ";
		String stragent="";// 代理商id
		String strEp="";   //企业id
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商
		String strgps="";//gps
		String strms="";//终端
		
		
		if(!msid.equals("")){//终端号码不是全部
			strms=" and gm.l_ms_id='"+msid+"'";
		}
		if(!gpsid.equals("")){//群组号码不是全部
			strgps=" and gm.gpsop_id='"+gpsid+"'";
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
			strEp = " and m.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // 为全部
			strEp = " and m.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
					+ stragent + ")";
		}
		
		hql+=strms+strgps+strEp+" order by gm.create_time desc";
		
		
		try {
			Object obj = sess.createQuery(hql).uniqueResult();
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
	    * 根据指定页及查询条件取得记录信息
	    * @param pageNo
	    * @param pageSize
	    * @param gpsid
	    * @param msid
	    * @return  指定页及查询条件取得记录信息
	    */
	public List findGpsMs_SearchByPage(Session sess, int pageNo, int pageSize,
			String gpsid, String msid,String pagentid,String childagentid,String ep) {
		String hql="select gm.gpsop_id,gm.l_ms_id, m.msName,g.gps_name from TbGpsMsListInfo gm,TbMsInfo m,TbGpsInfo g where gm.l_ms_id=m.msId and gm.gpsop_id=g.gpsop_id ";
		String stragent="";// 代理商id
		String strEp="";   //企业id
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商
		String strgps="";//gps
		String strms="";//终端
		if(!msid.equals("")){//终端号码不是全部
			strms=" and gm.l_ms_id='"+msid+"'";
		}
		if(!gpsid.equals("")){//群组号码不是全部
			strgps=" and gm.gpsop_id='"+gpsid+"'";
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
			strEp = " and m.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // 为全部
			strEp = " and m.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
					+ stragent + ")";
		}
		hql+=strms+strgps+strEp+" order by gm.create_time desc";
		
		try {
			int offset = (pageNo - 1) * pageSize;
			ScrollableResults  srs =sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).scroll();
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("gpsid",srs.getString(0));
				map.put("msid", srs.getString(1));
				map.put("msname", srs.getString(2));
				map.put("gpsname", srs.getString(3));
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

	public List findAllms_bygpsid(Session sess, String gpsid) {
		try {
			ScrollableResults srs = sess.createQuery(
					"select gm.l_ms_id as msid,m.msName as msname  from TbGpsMsListInfo gm,TbMsInfo m where  gm.l_ms_id=m.msId AND m.msType=0 and gm.gpsop_id='" + gpsid + "'").scroll();
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
	
	
}
