package com.elegps.tscweb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.dao.MsDaoFactory;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbMsInfoExt;

public class MsDaoHibernate implements MsDaoFactory {

	public void delete(Session sess, TbMsInfo m) {
		try {
			sess.delete(m);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}

	}

	public void delete(Session sess, String MS_Id) {
		try {
			sess.delete(get(sess, MS_Id));
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}

	}

	public TbMsInfo get(Session sess, String MS_Id) {
		try {
			List ms = sess.createQuery(" from TbMsInfo m where m.msId=?")
					.setParameter(0, MS_Id).list();
			if (ms != null && ms.size() > 0) {
				return (TbMsInfo) ms.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	public String save(Session sess, TbMsInfo m) {
		try {
			sess.save(m);
			sess.flush();
			return m.getMsId();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	public void update(Session sess, TbMsInfo m) {
		try {
			sess.clear();
			sess.saveOrUpdate(m);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				//sess.close();
			}
		}

	}

	public List getTbMsInfo(Session sess, String msId, int msType, int Flag) {
		String hqlwhere = "";
		if (msId.trim().length() >= 1) {
			hqlwhere = " msId='" + msId + "' ";
			if (msType != -1) {
				hqlwhere = hqlwhere + "and mstype=" + msType;
				if (Flag != -1) {
					hqlwhere = hqlwhere + " and Flag=" + Flag;
				}
			} else {
				if (Flag != -1) {
					hqlwhere = hqlwhere + " and Flag=" + Flag;
				}
			}
		} else {
			if (msType != -1) {
				hqlwhere = hqlwhere + "mstype=" + msType;
				if (Flag != -1) {
					hqlwhere = hqlwhere + "and Flag=" + Flag;
				}
			} else {
				if (Flag != -1) {
					hqlwhere = hqlwhere + "Flag=" + Flag;
				}
			}
		}

		if (hqlwhere.trim().length() > 1) {
			return sess.createQuery(
					"from TbMsInfo m where " + hqlwhere
							+ " order by m.msId desc").list();
		} else {
			return sess.createQuery("from TbMsInfo m  order by m.msId desc")
					.list();
		}

	}

	/**
	 * 查询终端用户类型信息的条数
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param ms_type
	 *            终端用户类型
	 * @return 终端用户类型信息条数
	 */
	public Integer findMs_typeCount(Session sess, int ms_type) {
		try {
			Object obj = sess.createQuery(
					"select count(m.msId) from TbMsInfo as m where m.flag=1 and m.msType="
							+ ms_type).uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	/**
	 * 查询终端用户类型信息的条数
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param ms_type
	 *            终端用户类型
	 * @return 所有终端用户类型信息条数
	 */
	public Integer findAllMs_typeCount(Session sess) {
		try {
			Object obj = sess.createQuery(
					"select count(m.msId) from TbMsInfo as m where m.flag=1")
					.uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	/**
	 * 查询指定用户状态为1、指定页的终端用户信息
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param pageNo
	 *            需要查询的指定页
	 * @param pageSize
	 *            页的个数
	 * @param onlineStatus
	 *            终端在线状态
	 * @return 查询到的终端用户信息集合 按在线状态排序
	 */
	public List findAllMs_typeByPage(Session sess, int pageNo, int pageSize) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list = sess
					.createQuery(
							"from TbMsInfo m where m.flag=1 order by m.create_time desc")
					.setFirstResult(offset).setMaxResults(pageSize).list();
			if (list != null) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	/**
	 * 查询指定用户、指定页的终端用户类型信息
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param pageNo
	 *            需要查询的指定页
	 * @param pageSize
	 *            需要查询的指定页数量
	 * @param ms_type
	 *            需要查询的指定终端用户类型
	 * @return 查询到的终端用户信息集合
	 */
	public List findMs_typeByPage(Session sess, int pageNo, int pageSize,
			int ms_type) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list = sess.createQuery(
					"from TbMsInfo m where m.flag=1 and m.msType=" + ms_type
							+ " order by m.msId desc").setFirstResult(offset)
					.setMaxResults(pageSize).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	/**
	 * 查询终端用户在线状态信息的条数
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param ms_online
	 *            终端用户在线状态
	 * @return 终端用户在线状态信息条数
	 */
	public Integer findMs_OnlineCount(Session sess, int ms_online) {
		try {
			Object obj = sess.createQuery(
					"select count(m.msId) from TbMsInfo as m where m.flag=1 and m.onlineStatus="
							+ ms_online + " order by m.msId desc")
					.uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	/**
	 * 查询所有终端用户在线状态信息的条数
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param ms_online
	 *            终端用户在线状态
	 * @return 所有终端用户在线状态信息条数
	 */
	public Integer findAllMs_OnlineCount(Session sess) {
		try {
			Object obj = sess
					.createQuery(
							"select count(m.msId) from TbMsInfo as m where m.flag=1 order by m.onlineStatus desc")
					.uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	/**
	 * 查询指定用户状态为1、指定页的终端用户信息
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param pageNo
	 *            需要查询的指定页
	 * @param pageSize
	 *            页的个数
	 * @return 查询到的终端用户信息集合 按在线状态排序
	 */
	public List findAllOnlineByPage(Session sess, int pageNo, int pageSize) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list = sess
					.createQuery(
							"from TbMsInfo m where m.flag=1 order by m.onlineStatus desc")
					.setFirstResult(offset).setMaxResults(pageSize).list();
			if (list != null) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	/**
	 * 查询指定用户状态为1、指定页的终端用户信息
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param pageNo
	 *            需要查询的指定页
	 * @param pageSize
	 *            页的个数
	 * @param onlineStatus
	 *            终端在线状态
	 * @return 查询到的终端用户信息集合 按在线状态排序
	 */
	public List findMs_OnlineByPage(Session sess, int pageNo, int pageSize,
			int onlineStatus) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list = sess.createQuery(
					"from TbMsInfo m where m.flag=1 and m.onlineStatus="
							+ onlineStatus + " order by m.msId desc")
					.setFirstResult(offset).setMaxResults(pageSize).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	/**
	 * 根据ms_id做模糊查询
	 * 
	 * @return 终端用户ms_id做模糊查询的数量
	 */
	public Integer findMs_idAllCount(Session sess, String ms_id) {
		try {
			Object obj = sess.createQuery(
					"select count(m.msId) from TbMsInfo as m where m.flag=1 and m.msId like '%"
							+ ms_id + "%' order by m.onlineStatus desc")
					.uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	public List findMs_idlistByPage(Session sess, int pageNo, int pageSize,
			String ms_id) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list = sess.createQuery(
					"from TbMsInfo m where m.flag=1 and m.msId like '%" + ms_id
							+ "%' order by m.msId desc").setFirstResult(offset)
					.setMaxResults(pageSize).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	/**
	 * 查询所有终端用户状态信息的条数
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param ms_online
	 *            终端用户状态
	 * @return 所有终端用户状态信息条数
	 */
	public Integer findMs_flagAllCount(Session sess) {
		try {
			Object obj = sess
					.createQuery(
							"select count(m.msId) from TbMsInfo as m order by m.flag desc")
					.uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	/**
	 * 查询所有终端用户状态信息的条数
	 * 
	 * @param sess
	 *            持久化操作所需要的Hiberate Session
	 * @param ms_online
	 *            终端用户在线状态
	 * @return 所有终端用户在线状态信息条数
	 */
	public List findMs_flaglistByPage(Session sess, int pageNo, int pageSize) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list = sess.createQuery(
					"from TbMsInfo m  order by m.flag desc").setFirstResult(
					offset).setMaxResults(pageSize).list();
			if (list != null) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	/**
	 * 获取终端用户状态信息数量
	 * 
	 * @param flag
	 *            终端用户状态对应总记录数
	 * @return 终端用户状态为flag信息的数量
	 */
	public Integer findMs_flagAllCount(Session sess, int flag) {
		try {
			Object obj = sess.createQuery(
					"select count(m.msId) from TbMsInfo as m where m.flag="
							+ flag + " order by m.flag desc").uniqueResult();
			if (obj != null) {
				int mscount = (Integer) obj;
				return mscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	public List findMs_flaglistByPage(Session sess, int pageNo, int pageSize,
			int falg) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list = sess.createQuery(
					"from TbMsInfo m where m.flag=" + falg
							+ " order by m.flag desc").setFirstResult(offset)
					.setMaxResults(pageSize).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;

	}

	public List findMs_Info(Session sess, String pagentid, String childagentid,
			String ep) {
		String stragent = "";// 代理商id
		String strEp = ""; // 企业id
		int pagent_id = Integer.parseInt(pagentid); // 一级代理商
		int cagent_id = Integer.parseInt(childagentid); // 二级代理商
		String hql = "from TbMsInfo M where M.flag=1 ";
		if (pagent_id == -1) { // 说明是总部
			if (cagent_id == -1) { // 直属企业(总部直接发展的企业)
				stragent = " and  E.Agent_Id =" + pagentid;
			} else { // -2 所有企业（系统中所有企业）

			}
		} else { // 一级代理商(除总部外)
			if (cagent_id == -1) // 直属企业(指定一级代理商下的企业)
			{
				stragent = " and E.Agent_Id=" + pagentid;
			} else if (cagent_id == -2) { // 所有企业
											// (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业
				stragent = " and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="
						+ pagentid + " or A.Agent_PId=" + pagentid + ")";
			} else { // 指定二级代理商下的所有企业
				stragent = " and E.Agent_Id=" + childagentid;
			}
		}

		if (!(ep.equals("-1"))) { // 用户单位不是全部
			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // 为全部
			strEp = " and (M.epid = 0 or M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
					+ stragent + "))";
		}

		hql += strEp + " order by M.create_time desc";
		try {
			List list = sess.createQuery(hql).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	public List findMs_sertchByPage(Session sess, int pageNo, int pageSize,
			String user_type, String statue, String flag, String ms_id,
			String ms_name, String pagentid, String childagentid, String ep,String ismobile,
			String arrearage) {

		String strUserType = ""; // 类型
		String strStatue = ""; // 在线状态
		String strFlag = ""; // 有效状态
		String strMsId = ""; // 终端id
		String strMSName = ""; // 终端名称
		String stragent = ""; // 代理商id
		String strEp = ""; // 企业id
		String isMobile="";//是否允许切换到打电话模式
		String StrArrearage = ""; // 车机/手持机
		int pagent_id = Integer.parseInt(pagentid); // 一级代理商
		int cagent_id = Integer.parseInt(childagentid); // 二级代理商
		String hql = "from TbMsInfo M where 1 = 1 ";
		if (!(user_type.equals("3"))) // 类型不是全部类型
			strUserType = " and M.msType = " + user_type;

		if (!(statue.equals("3"))) // 在线状态不是全部
			strStatue = " and M.onlineStatus = " + statue;

		if (!(flag.equals("2"))) // 有效状态不是全部
			strFlag = " and M.flag = " + flag;
		if(!("2".equals(ismobile)))//0:禁止切换;1:允许切换
			isMobile=" AND M.c03="+ismobile;
		
		if (!(arrearage.equals("-1"))) // 机型类型不是全部
			StrArrearage = " and M.msCategory = " + arrearage;

		if (ms_id.trim().length() > 0) // 终端名非空,并长度大于0
			strMsId = " and M.msId like '%" + ms_id + "%' ";

		if (ms_name.trim().length() > 0) // 终端名非空,并长度大于0
			strMSName = " and M.msName like '%" + ms_name + "%' ";

		if (pagent_id == -1) { // 说明是总部
			if (cagent_id == -1) { // 直属企业(总部直接发展的企业)
				stragent = " and  E.Agent_Id =" + pagentid;
			} else { // -2 所有企业（系统中所有企业）

			}
		} else { // 一级代理商(除总部外)
			if (cagent_id == -1) // 直属企业(指定一级代理商下的企业)
			{
				stragent = " and E.Agent_Id=" + pagentid;
			} else if (cagent_id == -2) { // 所有企业
											// (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业
				stragent = " and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="
						+ pagentid + " or A.Agent_PId=" + pagentid + ")";
			} else { // 指定二级代理商下的所有企业
				stragent = " and E.Agent_Id=" + childagentid;
			}
		}

		if (!(ep.equals("-1"))) { // 用户单位不是全部
			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // 为全部
//			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
//				+ stragent + ")";
			strEp = " and (M.epid = 0 or M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
					+ stragent + ")) and (M.agentId = null or M.agentId = "+pagentid+")";
		}

		hql += strUserType + strStatue + strFlag +isMobile+ StrArrearage + strMsId
				+ strMSName + strEp + " order by M.create_time desc";
		try {
			int offset = (pageNo - 1) * pageSize;
			List list = sess.createQuery(hql).setFirstResult(offset)
					.setMaxResults(pageSize).list();
			if (list != null) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	public Integer findMs_sertchCount(Session sess, String user_type,
			String statue, String flag, String ms_id, String ms_name,
			String pagentid, String childagentid, String ep, String ismobile,String arrearage) {
		String strUserType = ""; // 类型
		String strStatue = ""; // 在线状态
		String strFlag = ""; // 有效状态
		String strMsId = ""; // 终端id
		String strMSName = ""; // 终端名称
		String stragent = "";// 代理商id
		String strEp = ""; // 企业id
		String isMobile="";//是否允许切换到打电话模式
		String StrArrearage = ""; // 资费状态
		int pagent_id = Integer.parseInt(pagentid); // 一级代理商
		int cagent_id = Integer.parseInt(childagentid); // 二级代理商
		String hql = "select count(M.msId) from TbMsInfo M where 1=1";
		if (!(user_type.equals("3"))) // 类型不是全部类型
			strUserType = " and M.msType = " + user_type;

		if (!(statue.equals("3"))) // 在线状态不是全部
			strStatue = " and M.onlineStatus = " + statue;

		if (!(flag.equals("2"))) // 有效状态不是全部
			strFlag = " and M.flag = " + flag;
		if(!("2").equals(ismobile))//是否允许切换打电话模式不是全部
			isMobile=" AND M.c03="+ismobile;
		if (!(arrearage.equals("-1"))) // 机型状态不是全部
			StrArrearage = " and M.msCategory = " + arrearage;

		if (ms_id.trim().length() > 0) // 终端名非空,并长度大于0
			strMsId = " and M.msId like '%" + ms_id + "%' ";

		if (ms_name.trim().length() > 0) // 终端名非空,并长度大于0
			strMSName = " and M.msName like '%" + ms_name + "%' ";

		if (pagent_id == -1) { // 说明是总部
			if (cagent_id == -1) { // 直属企业(总部直接发展的企业)
				stragent = " and  E.Agent_Id =" + pagentid;
			} else { // -2 所有企业（系统中所有企业）

			}
		} else { // 一级代理商(除总部外)
			if (cagent_id == -1) // 直属企业(指定一级代理商下的企业)
			{
				stragent = " and E.Agent_Id=" + pagentid;
			} else if (cagent_id == -2) { // 所有企业
											// (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业
				stragent = " and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="
						+ pagentid + " or A.Agent_PId=" + pagentid + ")";
			} else { // 指定二级代理商下的所有企业
				stragent = " and E.Agent_Id=" + childagentid;
			}
		}

		if (!(ep.equals("-1"))) { // 用户单位不是全部
			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // 为全部
//			strEp = " and (M.epid = 0 or M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
//					+ stragent + "))";
			strEp = " and (M.epid = 0 or M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
				+ stragent + ")) and (M.agentId = null or M.agentId = "+pagentid+")";
		}

		hql += strUserType + strStatue + strFlag +isMobile+ StrArrearage + strMsId
				+ strMSName + strEp;
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
				// sess.close();
			}
		}
		return null;
	}

	// 用于群组与终端用户关系批量添加时(取得该grpid没有添加的终端信息
	public List findMs_Info_bygrpid(Session sess, String grpid, String ep) {
		String strAgent = ""; // 代理商id
		String strEp = ""; // 企业id
		String hql = "";
		hql = " from TbMsInfo M where M.flag=1 and M.epid="
				+ ep ;
		if(!grpid.equals("-1")){
			hql+= " and  M.msId not in(select gm.ms_id from TbGrpMsListInfo gm where  gm.grp_id='"
			+ grpid + "')";
		}
				
		hql += strEp + " order by M.msMoneyTime";
		try {
			List list = sess.createQuery(hql).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}
	public List findMs_Falg_bygrpid(Session sess, String grpid, String ep) {
		String strAgent = ""; // 代理商id
		String strEp = ""; // 企业id
		String hql = "";
		hql = " from TbMsInfo M where M.epid="
				+ ep ;
		if(!grpid.equals("-1")){
			hql+= " and  M.msId not in(select gm.ms_id from TbGrpMsListInfo gm where  gm.grp_id='"
			+ grpid + "')";
		}
				
		hql += strEp + " order by M.msMoneyTime";
		try {
			List list = sess.createQuery(hql).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}
	
	public List find_notQF_Ms_Info_bygrpid(Session sess, String grpid, String ep,String dateNow) {
		String strAgent = ""; // 代理商id
		String strEp = ""; // 企业id
		String hql = "";
		hql = " from TbMsInfo M where M.flag=1 and (M.msMoneyTime > '"+dateNow+"' or M.msMoneyTime = '0000-00-00 00:00:00') and M.epid="
				+ ep ;
		if(!grpid.equals("-1")){
			hql+= " and  M.msId not in(select gm.ms_id from TbGrpMsListInfo gm where  gm.grp_id='"
			+ grpid + "')";
		}
				
		hql += strEp + " order by M.msMoneyTime";
		try {
			List list = sess.createQuery(hql).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}
	
	
	public List findMs_Info_bygpsid(Session sess, String ep, String gpsid) {
		String strAgent = ""; // 代理商id
		String strEp = ""; // 企业id
		String hql = "";
		hql = " from TbMsInfo m where m.flag=1 AND m.msType=0  and m.epid="
				+ ep
				+ " and m.msId not in(select gm.l_ms_id from TbGpsMsListInfo gm where  gm.gpsop_id='"
				+ gpsid + "')";

		hql += strEp;
		try {
			List list = sess.createQuery(hql).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	public void deleteMsInfoByEp_id(Session sess, String epid) {
		try {
			// 删除群组终端对应关系信息
			String deletegrpms = "delete from TbGrpMsListInfo GM where GM.ms_id in(select M.msId from TbMsInfo M where M.epid="
					+ epid + ")";
			// 删除调度与终端对应关系信息
			String deleteddms = "delete from TbDdmsMsListInfo DDMS where DDMS.ddms_id in(select M.msId from TbMsInfo M where M.epid="
					+ epid
					+ ") or DDMS.ms_id in(select M.msId from TbMsInfo M where M.epid="
					+ epid + ")";
			// 删除GPS与终端对应关系信息
			String deletegpsms = "delete from TbGpsMsListInfo GPSMS where GPSMS.l_ms_id in(select M.msId from TbMsInfo M where M.epid="
					+ epid + ")";
			// 删除终端信息
			String deletems = "delete from TbMsInfo M where M.epid=" + epid;
			sess.createQuery(deletegrpms).executeUpdate();
			sess.createQuery(deleteddms).executeUpdate();
			sess.createQuery(deletegpsms).executeUpdate();
			sess.createQuery(deletems).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			sess.beginTransaction().rollback();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
	}

	/**
	 *以前不包括调度用户，现在包括。
	 */
	public List getMsInfo_byEpidao(Session sess, String ep_id) {
		// String hql="from TbMsInfo m where m.msType!=2 and m.epid=" + ep_id+
		// " order by m.msId desc";
		String hql = "from TbMsInfo m where  m.epid=" + ep_id
				+ " order by m.msId desc";
		try {
			List list = sess.createQuery(hql).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	/**
	 * 根据代理商查询待分配的终端（epID=0 待分配）
	 */
	public List getMsInfo_byAid(Session sess, String aid,int msCount) {
		// String hql="from TbMsInfo m where m.msType!=2 and m.epid=" + ep_id+
		// " order by m.msId desc";
		String hql = "from TbMsInfo m where  m.epid= 0 and m.agentId =" + aid
				+ " order by m.msId desc";
		try {
			List list = sess.createQuery(hql).setFirstResult(0).setMaxResults(msCount).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}
	
	public List getMsInfo_byEpidao(Session sess, StringBuffer ep_id) {
		// String hql="from TbMsInfo m where m.msType!=2 and m.epid=" + ep_id+
		// " order by m.msId desc";
		String hql = "from TbMsInfo m where  m.epid in (" + ep_id
				+ ") order by m.msId desc";
		try {
			List list = sess.createQuery(hql).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}
	
	public List getMsInfo_byEpidao(Session sess, StringBuffer ep_id,String ms) {
		// String hql="from TbMsInfo m where m.msType!=2 and m.epid=" + ep_id+
		// " order by m.msId desc";
		String hql = "from TbMsInfo m where 1=1 and m.flag = 1";
		if(ep_id.length() == 0){
			
		}else{
			hql += " and m.epid in (" + ep_id
			+ ")";
		}
		hql += " and m.msId like '%"+ms+"%' order by m.msMoneyTime";
		try {
			List list = sess.createQuery(hql).setFirstResult(0).setMaxResults(51).list();
			if (list != null) {
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}
	// -------------------------------分级调度用到的-----------------------------------
	/**
	 * epid（企业ID）和grpid(群组id)
	 * 
	 */
	public List getNonentityMsinfo_ByGrpid(Session sess, String epid,
			String grpid) {
		// 得出没有用到的调度
		// String hql =
		// "from TbMsInfo M where M.flag=1  and M.epid="+epid+" and M.msType=2 and M.msId not in(select GM.ms_id  from TbGrpMsListInfo GM where GM.grp_id='"+grpid+"')";
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		List<TbMsInfo> msinfolist = new ArrayList();

		String sql = "select  M.MS_Id,M.MS_Name from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="
				+ epid
				+ " and M.MS_Type=2 and M.MS_Id not in (select GM.MS_Id from  tb_MS_GRP_List GM )  union  select  M.MS_Id,M.MS_Name from tb_MS_Info M where M.Flag=1 and M.MS_Type!=2 and M.Enterprise_Id="
				+ epid
				+ " and M.MS_Id not in (select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id='"
				+ grpid + "')";
		try {
			TbGrpInfo grpinfo;
			List grp = sess.createQuery("from TbGrpInfo m where m.grpid=?")
					.setParameter(0, grpid).list();
			if (grp != null && grp.size() > 0) {
				grpinfo = (TbGrpInfo) grp.get(0);
				// ----------------------------------------(当前群组为子群组)根据上级群组给当前群组分配调度：现在是要求出上级群组中用到的调度而在同当前群组同一级子群组没有用到的调度-----------------------------
				// 求出上级群组用到的调度
				// String
				// sql1="select M.MS_Id from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="+epid+" and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id in(select GRP.GRP_Id from tb_GRP_Info GRP where GRP.Enterprise_Id="+epid+" and GRP.GRP_Id='"+grpinfo.getGrppid()+"'))";
				// 同前当前群组同一级子群组用到的调度
				// String
				// sql2="select M.MS_Id from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="+epid+" and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id in(select GRP.GRP_Id from tb_GRP_Info GRP where GRP.Enterprise_Id="+epid+" and GRP.Grp_PId='"+grpinfo.getGrppid()+"'))";
				// 现在是要求出上级群组中用到的调度而在同当前群组同一级子群组没有用到的调度
				// String
				// sql5=" union select MS_Id,MS_Name from (select M.MS_Id,M.MS_Name from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="+epid+" and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id in(select GRP.GRP_Id from tb_GRP_Info GRP where GRP.Enterprise_Id="+epid+" and GRP.GRP_Id='"+grpinfo.getGrppid()+"')))as a where ms_id not in((select M.MS_Id from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="+epid+" and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id in(select GRP.GRP_Id from tb_GRP_Info GRP where GRP.Enterprise_Id="+epid+" and GRP.Grp_PId='"+grpinfo.getGrppid()+"'))) )";
				sql = sql
						+ " union select MS_Id,MS_Name from (select M.MS_Id,M.MS_Name from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="
						+ epid
						+ " and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id in(select GRP.GRP_Id from tb_GRP_Info GRP where GRP.Enterprise_Id="
						+ epid
						+ " and GRP.GRP_Id='"
						+ grpinfo.getGrppid()
						+ "')))as a where ms_id not in((select M.MS_Id from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="
						+ epid
						+ " and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id in(select GRP.GRP_Id from tb_GRP_Info GRP where GRP.Enterprise_Id="
						+ epid + " and GRP.Grp_PId='" + grpinfo.getGrppid()
						+ "'))) )";

				// -----------------------------------------(当前群组为父群组)根据下级群组集给当前群组分配调度：现在要求出子群组用到而父群组没有用到的调度---------------------------------------------
				// 求出前群组的子群中用到的调度
				String sql3 = "select M.MS_Id from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="
						+ epid
						+ " and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id in(select GRP.GRP_Id from tb_GRP_Info GRP where GRP.Enterprise_Id="
						+ epid
						+ " and GRP.Grp_PId='"
						+ grpinfo.getGrpid()
						+ "'))";
				// 求出当前组用到的调度
				String sql4 = "select M.MS_Id from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="
						+ epid
						+ " and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id='"
						+ grpinfo.getGrpid() + "')";
				// 现在要求出子群组用到而父群组没有用到的调度
				// String
				// sql6="select MS_Id,MS_Name from (select M.MS_Id,M.MS_Name from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="+epid+" and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id in(select GRP.GRP_Id from tb_GRP_Info GRP where GRP.Enterprise_Id="+epid+" and GRP.Grp_PId='"+grpinfo.getGrpid()+"')))as a where ms_id not in((select M.MS_Id from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="+epid+" and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id='"+grpinfo.getGrpid()+"')))";
				sql = sql
						+ " union select MS_Id,MS_Name from (select M.MS_Id,M.MS_Name from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="
						+ epid
						+ " and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id in(select GRP.GRP_Id from tb_GRP_Info GRP where GRP.Enterprise_Id="
						+ epid
						+ " and GRP.Grp_PId='"
						+ grpinfo.getGrpid()
						+ "')))as a where ms_id not in((select M.MS_Id from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="
						+ epid
						+ " and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id='"
						+ grpinfo.getGrpid() + "')))";

				conn = sess.connection();
				ps = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				rs = ps.executeQuery(sql);
				while (rs.next()) {
					TbMsInfo msinfo = new TbMsInfo();
					msinfo.setMsId(rs.getString("MS_Id"));
					msinfo.setMsName(rs.getString("MS_Name"));
					msinfolist.add(msinfo);
				}
				for (int i = 0; i < msinfolist.size(); i++) {
					TbMsInfo msinfo1 = (TbMsInfo) msinfolist.get(i);
				}
				if (msinfolist != null && msinfolist.size() > 0) {
					return msinfolist;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	/**
	 * 添加对象
	 */
	public void create(Session session, Object obj) {
		try {
			if(obj!=null){
				session.save(obj);
				session.flush();
			}
			
		} catch (Exception e) {
		}
			
	}

	/**
	 * 修改对象
	 */
	public int update(Session session, String hql, Object...obj) {
		Query q=session.createQuery(hql);
		//q.setReadOnly(false);
		for (int i = 0; i < obj.length; i++) {
			q.setParameter(i, obj[i]);
		}
		return q.executeUpdate();
	}

	/**
	 * 获取对象
	 */
	public Object getExtById(Session session, String hql, Object... obj) {
		Query q=session.createQuery(hql);
		for (int i = 0; i < obj.length; i++) {
			q.setParameter(i, obj[i]);
		}
		q.setMaxResults(1);
		return (Object) q.uniqueResult();
	}

	public int getMsById(Session session, String hql, Object... obj) {
		Query q=session.createQuery(hql);
		for (int i = 0; i < obj.length; i++) {
			q.setParameter(i, obj[i]);
		}
		Number n=(Number) q.uniqueResult();
		return (n!=null)?n.intValue():0;
	}
	
	public  List<TbMsInfo> listMsInfo(Session session){
		String hql=" FROM TbMsInfo  ORDER BY msId DESC";
		Query q=session.createQuery(hql);
		return q.list();
	}

	public List<TbMsInfo> listMsInfoByEpId(Session session, Object...objects) {
		String hql=" FROM  TbMsInfo  as tm WHERE  tm.epid=? AND tm.msType=? AND tm.c01!=?";
		Query q=session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}
	public List<TbMsInfo> listMsInfoIsByEpId(Session session, Object...objects) {
		String hql=" FROM  TbMsInfo  as tm WHERE  tm.epid=? AND tm.msType=? AND tm.c01!=?  OR tm.msId=? ";
		Query q=session.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}

	@Override
	public List findMsListInfo_sertchByPage(Session sess, int pageNo,
			int pageSize, String userType, String statue, String flag,
			String msId, String msName, String pagentid, String childagentid,
			String ep, String ismobile, String arrearage,int roleId,String agentid,String epId) {
		// TODO Auto-generated method stub
		
//		String strUserType = ""; // 类型
//		String strStatue = ""; // 在线状态
		String strFlag = ""; // 有效状态
//		String strMsId = ""; // 终端id
//		String strMSName = ""; // 终端名称
		String stragent = ""; // 代理商id
//		String strEp = ""; // 企业id
//		String isMobile="";//是否允许切换到打电话模式
//		String StrArrearage = ""; // 车机/手持机
		int pagent_id = Integer.parseInt(pagentid); // 一级代理商
		int cagent_id = Integer.parseInt(childagentid); // 二级代理商
//		String hql = "from TbMsInfo M where 1 = 1 ";
//		if (!(userType.equals("3"))) // 类型不是全部类型
//			strUserType = " and M.msType = " + userType;
//
//		if (!(statue.equals("3"))) // 在线状态不是全部
//			strStatue = " and M.onlineStatus = " + statue;
//
//		if (!(flag.equals("2"))) // 有效状态不是全部
//			strFlag = " and M.flag = " + flag;
//		if(!("2".equals(ismobile)))//0:禁止切换;1:允许切换
//			isMobile=" AND M.c03="+ismobile;
//		
//		if (!(arrearage.equals("2"))) // 车机/手持机类型不是全部
//			StrArrearage = " and M.c04 = " + arrearage;
//
//		if (msId.trim().length() > 0) // 终端名非空,并长度大于0
//			strMsId = " and M.msId like '%" + msId + "%' ";
//
//		if (msName.trim().length() > 0) // 终端名非空,并长度大于0
//			strMSName = " and M.msName like '%" + msName + "%' ";
//
//		if (pagent_id == -1) { // 说明是总部
//			if (cagent_id == -1) { // 直属企业(总部直接发展的企业)
//				stragent = " and  E.Agent_Id =" + pagentid;
//			} else { // -2 所有企业（系统中所有企业）
//
//			}
//		} else { // 一级代理商(除总部外)
//			if (cagent_id == -1) // 直属企业(指定一级代理商下的企业)
//			{
//				stragent = " and E.Agent_Id=" + pagentid;
//			} else if (cagent_id == -2) { // 所有企业
//											// (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业
//				stragent = " and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="
//						+ pagentid + " or A.Agent_PId=" + pagentid + ")";
//			} else { // 指定二级代理商下的所有企业
//				stragent = " and E.Agent_Id=" + childagentid;
//			}
//		}
//
//		if (!(ep.equals("-1"))) { // 用户单位不是全部
//			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
//					+ ep + stragent + ")";
//		} else { // 为全部
//			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
//					+ stragent + ")";
//		}
//

//		String hql = "select M ,MT.pfType from TbMsInfo M " +
//				"LEFT JOIN TbPFInfo MT on M.pf.pfId = MT.pfId where 1 = 1 " ;
		String hql = "from TbMsInfo M " +
		" where 1 = 1 " ;
		if (!(flag.equals("2"))) // 有效状态不是全部
			strFlag = " and M.flag = " + flag;		
		
//		if (pagent_id != -1) { // 说明不是总部
//			if (cagent_id == -1) // 直属企业(指定一级代理商下的企业)
//			{
//				stragent = " and E.Agent_Id=" + pagentid;
//			} else if (cagent_id == -2) { // 所有企业
//											// (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业
//				stragent = " and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="
//						+ pagentid + " or A.Agent_PId=" + pagentid + ")";
//			} else { // 指定二级代理商下的所有企业
//				stragent = " and E.Agent_Id=" + childagentid;
//			}
//		}
	
		if(roleId == 2){//代理商
			stragent = " and M.Ep_Id in (select E.Ep_Id from TbEnterPriseInfo E where E.Agent_Id =" + agentid +") or M.agentId = "+agentid ;
		}else if(roleId == 3){//企业用户
			stragent = " and M.Ep_Id=" + epId;
		}
		hql += strFlag + stragent +"order by M.create_time desc";
		
		try {
			int offset = (pageNo - 1) * pageSize;
			List list = sess.createQuery(hql).setFirstResult(offset)
					.setMaxResults(pageSize).list();
			if (list != null) {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		
		return null;
	}

	@Override
	public String findMsIdByPhone(Session sess,String phone) {
		String hql = "from TbMsInfo m where m.msId like '%"+phone+"' or m.mobileid = '"+phone+"'";
		try {
			List list = sess.createQuery(hql).list();
			if (list.size() > 0) {
				System.out.println("findMsIdByPhone  Dao");
				return ((TbMsInfo)list.get(0)).getMsId();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}
}
