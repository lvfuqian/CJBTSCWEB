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
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpInfo;

public class GrpDaoHibernate implements GrpDaoFactory {


	/**
	 * 查询群组类型信息的条数
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @return 所有群组类型信息条数
	 */
	public Integer findAllGrp_typeCount(Session sess) {
		try {
			Object obj = sess.createQuery(
					"select count(m.grpid) from TbGrpInfo as m where m.flag=1")
					.uniqueResult();
			if(obj!=null){
				int grpcount = (Integer) obj;
				return grpcount;
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
	 * 查询指定群组有效状态为1、指定页的群组信息
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param pageNo
	 *            需要查询的指定页
	 * @param pageSize
	 *            页的个数
	 * @param onlineStatus
	 *            终端在线状态
	 * @return 查询到的群组信息集合
	 */
	public List findAllGrp_typeByPage(Session sess, int pageNo, int pageSize) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list=sess.createQuery(
			"from TbGrpInfo m where m.flag=1  order by m.regtime desc")
			.setFirstResult(offset).setMaxResults(pageSize).list();
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
	 * 查询群组类型信息的条数
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param grp_type
	 *            群组类型
	 * @return 群组类型信息条数
	 */
	public Integer findGrp_typeCount(Session sess, int grp_type) {
		try {
			Object obj = sess.createQuery(
					"select count(m.grpid) from TbGrpInfo as m where m.flag=1 and m.grptype="
							+ grp_type).uniqueResult();
			if(obj!=null){
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
	 * 查询指定群组类型、指定页的群组类型信息
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param pageNo
	 *            需要查询的指定页
	 * @param pageSize
	 *            需要查询的指定页数量
	 * @param grp_type
	 *            需要查询的指定群组类型
	 * @return 查询到的群组信息集合
	 */
	public List findGrp_typeByPage(Session sess, int pageNo, int pageSize,
			int grp_type) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list=sess.createQuery(
					"from TbGrpInfo m where m.flag=1 and m.grptype=" + grp_type
					+ "  order by m.regtime desc").setFirstResult(offset)
			.setMaxResults(pageSize).list();
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
	 * 根据主键加载群组信息
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param grp_id
	 *            需要加载的群组信息
	 * @return 加载的群组信息
	 */
	public TbGrpInfo get(Session sess, String grp_id) {
		try {
			List grp = sess.createQuery("from TbGrpInfo m where m.grpid=?")
					.setParameter(0, grp_id).list();
			if (grp != null && grp.size() > 0) {
				return (TbGrpInfo) grp.get(0);
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
	 * 查询所有群组在线通话状态信息的条数
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @return 所有群组在线通话状态信息条数
	 */
	public Integer findAllGrp_statusCount(Session sess) {
		try {
			Object obj = sess
					.createQuery(
							"select count(m.grpid) from TbGrpInfo as m where m.flag=1 order by m.status desc")
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
	 * 查询指定群组有效在线状态为1、指定页的终端用户信息
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param pageNo
	 *            需要查询的指定页
	 * @param pageSize
	 *            页的个数
	 * @return 查询到的群组所有有效状态信息集合 按在线通话状态排序
	 */
	public List findAllStatueByPage(Session sess, int pageNo, int pageSize) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list=sess.createQuery(
			"from TbGrpInfo m where m.flag=1  order by m.regtime desc")
			.setFirstResult(offset).setMaxResults(pageSize).list();
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
	 * 查询群组有效在线状态信息的条数
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param status
	 *            群组有效在线状态
	 * @return 群组有效在线状态信息的条数
	 */
	public Integer findGrp_StatusCount(Session sess, int status) {
		try {
			Object obj = sess.createQuery(
					"select count(m.grpid) from TbGrpInfo as m where m.flag=1 and m.status="
							+ status + " order by m.grpid desc").uniqueResult();
			if (obj != null) {
				int grpcount = (Integer) obj;
				return grpcount;
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

	public List findGrp_StatusByPage(Session sess, int pageNo, int pageSize,
			int status) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list=sess.createQuery(
					"from TbGrpInfo m where m.flag=1 and m.status=" + status
					+ "  order by m.regtime desc").setFirstResult(offset)
			.setMaxResults(pageSize).list();
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
	 * 根据grpid做模糊查询
	 * 
	 * @return 群组grpid做模糊查询的数量
	 */
	public Integer findGrp_idAllCount(Session sess, String grpid) {
		try {
			Object obj = sess.createQuery(
					"select count(m.grpid) from TbGrpInfo as m where m.flag=1 and m.grpid like '%"
							+ grpid + "%' order by m.status desc")
					.uniqueResult();
			if (obj != null) {
				int grpcount = (Integer) obj;
				return grpcount;
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
	 * 根据grpid做模糊查询
	 * 
	 * @return 群组grpid做模糊查询页的全部群组信息
	 */
	public List findGrp_idlistByPage(Session sess, int pageNo, int pageSize,
			String grpid) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list=sess.createQuery(
					"from TbGrpInfo m where m.flag=1 and m.grpid like '%"
					+ grpid + "%'  order by m.regtime desc")
			.setFirstResult(offset).setMaxResults(pageSize).list();
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
	 * 查询所有群组有效状态信息的条数
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @return 所有群组有效状态信息条数
	 */
	public Integer findGrp_flagAllCount(Session sess) {
		try {
			Object obj = sess
					.createQuery(
							"select count(m.grpid) from TbGrpInfo as m order by m.flag desc")
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
	 * 查询所有群组有效状态信息
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @return 所有群组有效状态信息
	 */
	public List findGrp_flaglistByPage(Session sess, int pageNo, int pageSize) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list=sess.createQuery("from TbGrpInfo m  order by m.regtime desc")
			.setFirstResult(offset).setMaxResults(pageSize).list();
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
	 * 获取指定群组有效状态信息数量
	 * 
	 * @param flag
	 *            群组有效状对应总记录数
	 * @return 指定群组有效状态信息数量
	 */
	public Integer findGrp_flagCount(Session sess, int flag) {
		try {
			Object obj = sess.createQuery(
					"select count(m.grpid) from TbGrpInfo as m where m.flag="
							+ flag + " order by m.flag desc").uniqueResult();
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
	 * 查询指定群组有效状态信息
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param falg
	 *            群组有效状态
	 * @return 指定群组有效状态信息
	 */
	public List findGrp_flaglistByPage(Session sess, int pageNo, int pageSize,
			int falg) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list=sess.createQuery(
					"from TbGrpInfo m where m.flag=" + falg
					+ "  order by m.regtime desc").setFirstResult(offset)
			.setMaxResults(pageSize).list();
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

	public List findMs_flagList(Session sess) {
		try {
			List list=sess.createQuery(
			"from TbMsInfo m where m.flag=1  order by m.regtime desc")
			.list();
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

	public String save(Session sess, TbGrpInfo m) {
		try {
			sess.save(m);
			sess.flush();
			return m.getGrpid();
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
	 * 所有群组信息
	 * 
	 * @return 指定页的全部群组信息
	 */
	public List findAllGrp_Info(Session sess,String pagentid,String childagentid,String ep) {

		String stragent="";// 代理商id
		String strEp="";   //企业id
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商
		String hql="";
		hql="  FROM TbGrpInfo G where G.flag=1 ";
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

		if(ep != null && !ep.equals("")){
			if (!(ep.equals("-1"))) { // 用户单位不是全部
				strEp = " and G.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
						+ ep + stragent + ")";
			} else { // 为全部
				strEp = " and G.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
						+ stragent + ")";
			}
		}
		
		hql+=strEp+" order by G.regtime desc";
		try {
			List list=sess.createQuery(hql).list();
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
	
	public List findBaseGrp_Info(Session sess,String pagentid,String childagentid,String ep) {

		String stragent="";// 代理商id
		String strEp="";   //企业id
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商
		String hql="";
		hql="  FROM TbGrpInfo G where G.flag=1 AND G.c10='0' ";
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
			strEp = " and G.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // 为全部
			strEp = " and G.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
					+ stragent + ")";
		}
		
		
		hql+=strEp+" order by G.regtime desc";
		try {
			List list=sess.createQuery(hql).list();
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
	 * 根据grp_id,ms_id查询是不是这个终端创建的组
	 * 
	 * @param sess
	 * @param grp_id
	 * @param ms_id
	 * @return
	 */
	public Boolean findGrp_InfobyMs_id(Session sess, String grp_id, String ms_id) {
		try {
			List list = sess.createQuery(
					"from TbGrpInfo as m where m.msid='" + ms_id
							+ "' and m.grpid='" + grp_id + "'").list();
			if (list.size() > 0 && list != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return false;

	}

	/**群组表查询数量
	 * @param grp_type  群组类型
	 * @param statue  通话状态
	 * @param flag  有效状态
	 * @param ms_id  群组号码
	 * @param ms_id  群组名称
	 * @return 群组模糊查询的数量
	 */
	public Integer  findGrp_sertchCount(Session sess,String grp_type,String statue,String flag,String grp_id,String grp_name,String pagentid,String childagentid,String ep){
		String grpType = "";  //类型
		String grpStatue="";    //在线状态
		String grpFlag="";   //有效状态
		String grpId="";  //群组id
		String grpName="";  //群组名称
		String stragent="";// 代理商id
		String strEp="";   //企业id
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商
		String hql="select count(G.grpid) from TbGrpInfo G where 1=1";
		
		if(!(grp_type.equals("99")))  //类型不是全部类型
			grpType = " and G.grptype = " +  grp_type;
        
		if(!(statue.equals("2")))  //在线状态不是全部
			grpStatue = " and G.status = " +  statue;
		
		if(!(flag.equals("2")))  //有效状态不是全部
			grpFlag = " and G.flag = " +  flag;
		
		if(grp_id.trim().length() > 0)   //群组名非空,并长度大于0
			grpId = " and G.grpid like '%" + grp_id + "%' ";
		
		if(grp_name.trim().length() > 0)   //终端名非空,并长度大于0
			grpName = " and G.grpname like '%" + grp_name + "%' ";
		
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
			strEp = " and G.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // 为全部
			strEp = " and G.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
					+ stragent + ")";
		}
		
		hql+=grpType+grpStatue+grpFlag+grpId+grpName+strEp;
		
		try {
			Object obj = sess.createQuery(hql)
					.uniqueResult();
			if (obj!= null) {
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

	
	/**群组表查询信息
	 * @param grp_type  群组类型
	 * @param statue  通话状态
	 * @param flag  有效状态
	 * @param grp_id  群组号码
	 * @param grp_name  群组名称
	 * @return 群组模糊查询的信息
	 */
	public List findGrp_sertchByPage(Session sess, int pageNo, int pageSize,
			String grp_type, String statue, String flag, String grp_id,
			String grp_name,String pagentid,String childagentid,String ep) {
		
		String grpType = "";  //类型
		String grpStatue="";    //在线状态
		String grpFlag="";   //有效状态
		String grpId="";  //群组id
		String grpName="";  //群组名称
		String stragent="";// 代理商id
		String strEp="";   //企业id
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商
		String hql="from TbGrpInfo G where 1=1 ";
		if(!(grp_type.equals("99")))  //类型不是全部类型
			grpType = " and G.grptype = " +  grp_type;
        
		if(!(statue.equals("2")))  //在线状态不是全部
			grpStatue = " and G.status = " +  statue;
		
		if(!(flag.equals("2")))  //有效状态不是全部
			grpFlag = " and G.flag = " +  flag;
		
		if(grp_id.trim().length() > 0)   //群组名非空,并长度大于0
			grpId = " and G.grpid like '%" + grp_id + "%' ";
		
		if(grp_name.trim().length() > 0)   //终端名非空,并长度大于0
			grpName = " and G.grpname like '%" + grp_name + "%' ";
		
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
			strEp = " and G.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // 为全部
			strEp = " and G.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
					+ stragent + ")";
		}
		
		
		hql+=grpType+grpStatue+grpFlag+grpId+grpName+strEp+" order by G.regtime desc";
		try {
			int offset = (pageNo - 1) * pageSize;
			List list=sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
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

	public List findGrp_Info_bymsid(Session sess, String msid,String ep) {
		String hql="";
		hql="from TbGrpInfo g where g.flag=1 and g.Ep_Id="+ep+" and g.grpid not in(select gm.grp_id from TbGrpMsListInfo gm where  gm.ms_id='" + msid + "')";
		try {
			List list=sess.createQuery(hql).list();
			if(list!=null){
				return  list;
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
	 * 查询所有用户单位信息 zr
	 * @param sess
	 * @return
	 */
	public List getAllEp(Session sess){
		try{
			String sql="from TbEnterpriseInfo";
			List info=sess.createQuery(sql).list();
			if(info!=null){
				return info;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}
	/**
	 * 根据用户单位id查询用户单位信息 zr
	 * @param sess
	 * @param id
	 * @return
	 */
	public TbEnterpriseInfo getEpNameByEpid(Session sess,Integer id){
		try{
			
			String sql="from TbEnterpriseInfo where Enterprise_Id="+id;
			List list=sess.createQuery(sql).list();
			if(list!=null){
				
				return (TbEnterpriseInfo)list.get(0);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
		
	}
	/**
	 * 根据群组ID查询群组所属的用户单位 zr
	 */
	public TbEnterpriseInfo getNameByGrpid(Session sess,String grpid){
		try{
			String sql="from TbEnterpriseInfo e where e.Ep_Id =(select g.Ep_Id from TbGrpInfo g where g.grpid='"+grpid+"')";
			List listinfo=sess.createQuery(sql).list();
			if(listinfo!=null&&listinfo.size()>0){
				return (TbEnterpriseInfo)listinfo.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	public void deleteGrpInfoByEp_id(Session sess, String epid) {
		try{
			List list=sess.createQuery("from TbGrpInfo G where G.Ep_Id="+epid).list();
			if (list != null && list.size() > 0) {
				for (Object obj : list) {
					TbGrpInfo me = (TbGrpInfo) obj;
					String deletegrpms="delete from TbGrpMsListInfo GM where GM.grp_id='"+me.getGrpid()+"'";
					sess.createQuery(deletegrpms).executeUpdate();
				}
			}
			String deletegrp="delete from TbGrpInfo G where G.Ep_Id="+epid;
			sess.createQuery(deletegrp).executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			sess.beginTransaction().rollback();
		}finally{
			if (sess != null) {
//				sess.close();
			}
		}
		
	}
	
	
	public List findGrp_Info_bymsidGrp(Session sess, String msid,String yep) {
		String hql="";
		hql="from TbGrpInfo grp where grp.flag=1 and grp.Ep_Id="+yep+" and grp.grpid not in(select gm.grp_id from TbGrpMsListInfo gm where  gm.ms_id='" + msid + "')";
	
		try {
			List list=sess.createQuery(hql).list();
			if(list!=null){
				return  list;
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

	//--------------分级调度用到------------
	public Boolean grpall_byid(Session sess, String grpid) {
		Transaction tx=null;
		try {
			tx=sess.beginTransaction();
			String lastgrpid="";
			String deletegrphql;
			String deletegrpmshql;
			ScrollableResults rsmenu = sess.createQuery("select grp.grpid from TbGrpInfo grp where grp.grppid in('"+grpid+"')" ).scroll();
			List rsmenucount=sess.createQuery("select grp.grpid from TbGrpInfo grp where grp.grppid in('"+grpid+"')" ).list();
			while(rsmenucount.size()>0){
				while(rsmenu.next()){
					lastgrpid=rsmenu.getString(0);
					rsmenu = sess.createQuery("select grp.grpid from TbGrpInfo grp where grp.grppid in('"+rsmenu.getString(0)+"')" ).scroll();	
			  }
				deletegrphql="delete from TbGrpInfo G where G.grpid='"+lastgrpid+"'";
				deletegrpmshql="delete from TbGrpMsListInfo GM where GM.grp_id='"+lastgrpid+"'";
				sess.createQuery(deletegrphql).executeUpdate();
				sess.createQuery(deletegrpmshql).executeUpdate();
			    rsmenu = sess.createQuery("select grp.grpid from TbGrpInfo grp where grp.grppid in('"+grpid+"')" ).scroll();
			    rsmenucount=sess.createQuery("select grp.grpid from TbGrpInfo grp where grp.grppid in('"+grpid+"')" ).list();
			}
			rsmenu.close();
			deletegrphql="delete from TbGrpInfo G where G.grpid='"+grpid+"'";
			deletegrpmshql="delete from TbGrpMsListInfo GM where GM.grp_id='"+grpid+"'";
			sess.createQuery(deletegrphql).executeUpdate();
			sess.createQuery(deletegrpmshql).executeUpdate();
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			if (sess != null) {
				//sess.close();
			}
		}
		return null;
	}


	public void create(Session session, Object objects) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(objects);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	/**
	 * 执行指定hql
	 */
	public int executeQuery(Session session, String hql, Object... objects) {
		Transaction tx = null;
		Number n = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery(hql);
			for (int i = 0; i < objects.length; i++) {
				q.setParameter(i, objects[i]);
			}
			n = (Number) q.executeUpdate();
			tx.commit();
			return n.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return 0;
	}

	/**
	 * 查询对象
	 */
	public List<Object> listObject(Session session, String hql, int pageNO,
			int pageSize, Object... objects) {
		Query q = session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		if (pageNO > 0) {
			q.setFirstResult(pageNO);
		}
		if (pageSize > 0) {
			q.setMaxResults(pageSize);
		}
		return q.list();
	}

	/**
	 * 查询对象
	 */
	public Object getBean(Session session, String hql, Object... objects) {
		Query q = session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		q.setMaxResults(1);
		return q.uniqueResult();
	}

	/**
	 * 统计条数
	 */
	public int toaltCount(Session session, String hql, Object... objects) {
		Query q = session.createQuery(hql);
		if (objects != null && objects.length > 0) {
			for (int i = 0; i < objects.length; i++) {
				q.setParameter(i, objects[i]);
			}
		}
		Number n = (Number) q.uniqueResult();
		return (n != null) ? n.intValue() : 0;
	}

	/**
	 * 修改对象
	 */
	public int update(Session session, String hql, Object... objects) {
		Transaction tx = null;
		Number n = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery(hql);
			for (int i = 0; i < objects.length; i++) {
				q.setParameter(i, objects[i]);
			}
			n = (Number) q.executeUpdate();
			tx.commit();
			return n.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return 0;
	}

	public List listObjectInfo(Session session, String hql, int pageNO,
			int pageSize) {
		Query q=session.createQuery(hql);
		if (pageNO > 0) {
			q.setFirstResult(pageNO);
		}
		if (pageSize > 0) {
			q.setMaxResults(pageSize);
		}
		return q.list();
	}

	@Override
	public String getGrpId(Session sess, String grp) {
		// TODO Auto-generated method stub
		try{
			String sql="SELECT g.grpid FROM TbGrpInfo g where g.grpid like '%"+
						grp+"%' ORDER BY g.grpid DESC ";
			List gid=sess.createQuery(sql).setMaxResults(1).list();
			if(gid.size() >0){
				
				return gid.get(0).toString();
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	@Override
	public List findGrp_Info_byMsId(Session sess, String msid) {
		// TODO Auto-generated method stub
		String hql="";
		hql=" from TbGrpInfo grp where grp.msid= '" + msid + "'";
	
		try {
			List list=sess.createQuery(hql).list();
			if(list!=null){
				return  list;
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
	public void update(Session session, TbGrpInfo grpInfo) {
		// TODO Auto-generated method stub
		try {
			session.saveOrUpdate(grpInfo);
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				// sess.close();
			}
		}
	}

	@Override
	public Map<String, String> getGrpByNameOrId(Session session,
			String nameOrId, int epid) {
		try{
			
			String sql="select grp.grpid,grp.grpname from TbGrpInfo grp where grp.Ep_Id="+epid 
					+" and (grp.grpid like '%"+nameOrId+"%' or grp.grpname like '%"+nameOrId+"%')";
			List list=session.createQuery(sql).setFirstResult(0).setMaxResults(5).list();
			if(list!=null){
				Map<String, String> map = new HashMap<String, String>();
				for(int i = 0;i<list.size();i++){
					Object[] obj =  (Object[]) list.get(i);
					map.put(String.valueOf(obj[0]),String.valueOf(obj[1]));
				}
				return map;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
//				sess.close();
			}
		}
		return null;
	}
	
}
