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
	 * ��ѯ�ն��û�������Ϣ������
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param ms_type
	 *            �ն��û�����
	 * @return �ն��û�������Ϣ����
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
	 * ��ѯ�ն��û�������Ϣ������
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param ms_type
	 *            �ն��û�����
	 * @return �����ն��û�������Ϣ����
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
	 * ��ѯָ���û�״̬Ϊ1��ָ��ҳ���ն��û���Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param pageNo
	 *            ��Ҫ��ѯ��ָ��ҳ
	 * @param pageSize
	 *            ҳ�ĸ���
	 * @param onlineStatus
	 *            �ն�����״̬
	 * @return ��ѯ�����ն��û���Ϣ���� ������״̬����
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
	 * ��ѯָ���û���ָ��ҳ���ն��û�������Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param pageNo
	 *            ��Ҫ��ѯ��ָ��ҳ
	 * @param pageSize
	 *            ��Ҫ��ѯ��ָ��ҳ����
	 * @param ms_type
	 *            ��Ҫ��ѯ��ָ���ն��û�����
	 * @return ��ѯ�����ն��û���Ϣ����
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
	 * ��ѯ�ն��û�����״̬��Ϣ������
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param ms_online
	 *            �ն��û�����״̬
	 * @return �ն��û�����״̬��Ϣ����
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
	 * ��ѯ�����ն��û�����״̬��Ϣ������
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param ms_online
	 *            �ն��û�����״̬
	 * @return �����ն��û�����״̬��Ϣ����
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
	 * ��ѯָ���û�״̬Ϊ1��ָ��ҳ���ն��û���Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param pageNo
	 *            ��Ҫ��ѯ��ָ��ҳ
	 * @param pageSize
	 *            ҳ�ĸ���
	 * @return ��ѯ�����ն��û���Ϣ���� ������״̬����
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
	 * ��ѯָ���û�״̬Ϊ1��ָ��ҳ���ն��û���Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param pageNo
	 *            ��Ҫ��ѯ��ָ��ҳ
	 * @param pageSize
	 *            ҳ�ĸ���
	 * @param onlineStatus
	 *            �ն�����״̬
	 * @return ��ѯ�����ն��û���Ϣ���� ������״̬����
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
	 * ����ms_id��ģ����ѯ
	 * 
	 * @return �ն��û�ms_id��ģ����ѯ������
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
	 * ��ѯ�����ն��û�״̬��Ϣ������
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param ms_online
	 *            �ն��û�״̬
	 * @return �����ն��û�״̬��Ϣ����
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
	 * ��ѯ�����ն��û�״̬��Ϣ������
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param ms_online
	 *            �ն��û�����״̬
	 * @return �����ն��û�����״̬��Ϣ����
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
	 * ��ȡ�ն��û�״̬��Ϣ����
	 * 
	 * @param flag
	 *            �ն��û�״̬��Ӧ�ܼ�¼��
	 * @return �ն��û�״̬Ϊflag��Ϣ������
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
		String stragent = "";// ������id
		String strEp = ""; // ��ҵid
		int pagent_id = Integer.parseInt(pagentid); // һ��������
		int cagent_id = Integer.parseInt(childagentid); // ����������
		String hql = "from TbMsInfo M where M.flag=1 ";
		if (pagent_id == -1) { // ˵�����ܲ�
			if (cagent_id == -1) { // ֱ����ҵ(�ܲ�ֱ�ӷ�չ����ҵ)
				stragent = " and  E.Agent_Id =" + pagentid;
			} else { // -2 ������ҵ��ϵͳ��������ҵ��

			}
		} else { // һ��������(���ܲ���)
			if (cagent_id == -1) // ֱ����ҵ(ָ��һ���������µ���ҵ)
			{
				stragent = " and E.Agent_Id=" + pagentid;
			} else if (cagent_id == -2) { // ������ҵ
											// (ָ��һ���������µ���ҵ�����һ�������������ж����������µĵ���ҵ
				stragent = " and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="
						+ pagentid + " or A.Agent_PId=" + pagentid + ")";
			} else { // ָ�������������µ�������ҵ
				stragent = " and E.Agent_Id=" + childagentid;
			}
		}

		if (!(ep.equals("-1"))) { // �û���λ����ȫ��
			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // Ϊȫ��
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

		String strUserType = ""; // ����
		String strStatue = ""; // ����״̬
		String strFlag = ""; // ��Ч״̬
		String strMsId = ""; // �ն�id
		String strMSName = ""; // �ն�����
		String stragent = ""; // ������id
		String strEp = ""; // ��ҵid
		String isMobile="";//�Ƿ������л�����绰ģʽ
		String StrArrearage = ""; // ����/�ֳֻ�
		int pagent_id = Integer.parseInt(pagentid); // һ��������
		int cagent_id = Integer.parseInt(childagentid); // ����������
		String hql = "from TbMsInfo M where 1 = 1 ";
		if (!(user_type.equals("3"))) // ���Ͳ���ȫ������
			strUserType = " and M.msType = " + user_type;

		if (!(statue.equals("3"))) // ����״̬����ȫ��
			strStatue = " and M.onlineStatus = " + statue;

		if (!(flag.equals("2"))) // ��Ч״̬����ȫ��
			strFlag = " and M.flag = " + flag;
		if(!("2".equals(ismobile)))//0:��ֹ�л�;1:�����л�
			isMobile=" AND M.c03="+ismobile;
		
		if (!(arrearage.equals("-1"))) // �������Ͳ���ȫ��
			StrArrearage = " and M.msCategory = " + arrearage;

		if (ms_id.trim().length() > 0) // �ն����ǿ�,�����ȴ���0
			strMsId = " and M.msId like '%" + ms_id + "%' ";

		if (ms_name.trim().length() > 0) // �ն����ǿ�,�����ȴ���0
			strMSName = " and M.msName like '%" + ms_name + "%' ";

		if (pagent_id == -1) { // ˵�����ܲ�
			if (cagent_id == -1) { // ֱ����ҵ(�ܲ�ֱ�ӷ�չ����ҵ)
				stragent = " and  E.Agent_Id =" + pagentid;
			} else { // -2 ������ҵ��ϵͳ��������ҵ��

			}
		} else { // һ��������(���ܲ���)
			if (cagent_id == -1) // ֱ����ҵ(ָ��һ���������µ���ҵ)
			{
				stragent = " and E.Agent_Id=" + pagentid;
			} else if (cagent_id == -2) { // ������ҵ
											// (ָ��һ���������µ���ҵ�����һ�������������ж����������µĵ���ҵ
				stragent = " and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="
						+ pagentid + " or A.Agent_PId=" + pagentid + ")";
			} else { // ָ�������������µ�������ҵ
				stragent = " and E.Agent_Id=" + childagentid;
			}
		}

		if (!(ep.equals("-1"))) { // �û���λ����ȫ��
			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // Ϊȫ��
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
		String strUserType = ""; // ����
		String strStatue = ""; // ����״̬
		String strFlag = ""; // ��Ч״̬
		String strMsId = ""; // �ն�id
		String strMSName = ""; // �ն�����
		String stragent = "";// ������id
		String strEp = ""; // ��ҵid
		String isMobile="";//�Ƿ������л�����绰ģʽ
		String StrArrearage = ""; // �ʷ�״̬
		int pagent_id = Integer.parseInt(pagentid); // һ��������
		int cagent_id = Integer.parseInt(childagentid); // ����������
		String hql = "select count(M.msId) from TbMsInfo M where 1=1";
		if (!(user_type.equals("3"))) // ���Ͳ���ȫ������
			strUserType = " and M.msType = " + user_type;

		if (!(statue.equals("3"))) // ����״̬����ȫ��
			strStatue = " and M.onlineStatus = " + statue;

		if (!(flag.equals("2"))) // ��Ч״̬����ȫ��
			strFlag = " and M.flag = " + flag;
		if(!("2").equals(ismobile))//�Ƿ������л���绰ģʽ����ȫ��
			isMobile=" AND M.c03="+ismobile;
		if (!(arrearage.equals("-1"))) // ����״̬����ȫ��
			StrArrearage = " and M.msCategory = " + arrearage;

		if (ms_id.trim().length() > 0) // �ն����ǿ�,�����ȴ���0
			strMsId = " and M.msId like '%" + ms_id + "%' ";

		if (ms_name.trim().length() > 0) // �ն����ǿ�,�����ȴ���0
			strMSName = " and M.msName like '%" + ms_name + "%' ";

		if (pagent_id == -1) { // ˵�����ܲ�
			if (cagent_id == -1) { // ֱ����ҵ(�ܲ�ֱ�ӷ�չ����ҵ)
				stragent = " and  E.Agent_Id =" + pagentid;
			} else { // -2 ������ҵ��ϵͳ��������ҵ��

			}
		} else { // һ��������(���ܲ���)
			if (cagent_id == -1) // ֱ����ҵ(ָ��һ���������µ���ҵ)
			{
				stragent = " and E.Agent_Id=" + pagentid;
			} else if (cagent_id == -2) { // ������ҵ
											// (ָ��һ���������µ���ҵ�����һ�������������ж����������µĵ���ҵ
				stragent = " and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="
						+ pagentid + " or A.Agent_PId=" + pagentid + ")";
			} else { // ָ�������������µ�������ҵ
				stragent = " and E.Agent_Id=" + childagentid;
			}
		}

		if (!(ep.equals("-1"))) { // �û���λ����ȫ��
			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // Ϊȫ��
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

	// ����Ⱥ�����ն��û���ϵ�������ʱ(ȡ�ø�grpidû����ӵ��ն���Ϣ
	public List findMs_Info_bygrpid(Session sess, String grpid, String ep) {
		String strAgent = ""; // ������id
		String strEp = ""; // ��ҵid
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
		String strAgent = ""; // ������id
		String strEp = ""; // ��ҵid
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
		String strAgent = ""; // ������id
		String strEp = ""; // ��ҵid
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
		String strAgent = ""; // ������id
		String strEp = ""; // ��ҵid
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
			// ɾ��Ⱥ���ն˶�Ӧ��ϵ��Ϣ
			String deletegrpms = "delete from TbGrpMsListInfo GM where GM.ms_id in(select M.msId from TbMsInfo M where M.epid="
					+ epid + ")";
			// ɾ���������ն˶�Ӧ��ϵ��Ϣ
			String deleteddms = "delete from TbDdmsMsListInfo DDMS where DDMS.ddms_id in(select M.msId from TbMsInfo M where M.epid="
					+ epid
					+ ") or DDMS.ms_id in(select M.msId from TbMsInfo M where M.epid="
					+ epid + ")";
			// ɾ��GPS���ն˶�Ӧ��ϵ��Ϣ
			String deletegpsms = "delete from TbGpsMsListInfo GPSMS where GPSMS.l_ms_id in(select M.msId from TbMsInfo M where M.epid="
					+ epid + ")";
			// ɾ���ն���Ϣ
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
	 *��ǰ�����������û������ڰ�����
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
	 * ���ݴ����̲�ѯ��������նˣ�epID=0 �����䣩
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
	// -------------------------------�ּ������õ���-----------------------------------
	/**
	 * epid����ҵID����grpid(Ⱥ��id)
	 * 
	 */
	public List getNonentityMsinfo_ByGrpid(Session sess, String epid,
			String grpid) {
		// �ó�û���õ��ĵ���
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
				// ----------------------------------------(��ǰȺ��Ϊ��Ⱥ��)�����ϼ�Ⱥ�����ǰȺ�������ȣ�������Ҫ����ϼ�Ⱥ�����õ��ĵ��ȶ���ͬ��ǰȺ��ͬһ����Ⱥ��û���õ��ĵ���-----------------------------
				// ����ϼ�Ⱥ���õ��ĵ���
				// String
				// sql1="select M.MS_Id from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="+epid+" and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id in(select GRP.GRP_Id from tb_GRP_Info GRP where GRP.Enterprise_Id="+epid+" and GRP.GRP_Id='"+grpinfo.getGrppid()+"'))";
				// ͬǰ��ǰȺ��ͬһ����Ⱥ���õ��ĵ���
				// String
				// sql2="select M.MS_Id from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="+epid+" and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id in(select GRP.GRP_Id from tb_GRP_Info GRP where GRP.Enterprise_Id="+epid+" and GRP.Grp_PId='"+grpinfo.getGrppid()+"'))";
				// ������Ҫ����ϼ�Ⱥ�����õ��ĵ��ȶ���ͬ��ǰȺ��ͬһ����Ⱥ��û���õ��ĵ���
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

				// -----------------------------------------(��ǰȺ��Ϊ��Ⱥ��)�����¼�Ⱥ�鼯����ǰȺ�������ȣ�����Ҫ�����Ⱥ���õ�����Ⱥ��û���õ��ĵ���---------------------------------------------
				// ���ǰȺ�����Ⱥ���õ��ĵ���
				String sql3 = "select M.MS_Id from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="
						+ epid
						+ " and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id in(select GRP.GRP_Id from tb_GRP_Info GRP where GRP.Enterprise_Id="
						+ epid
						+ " and GRP.Grp_PId='"
						+ grpinfo.getGrpid()
						+ "'))";
				// �����ǰ���õ��ĵ���
				String sql4 = "select M.MS_Id from tb_MS_Info M where M.Flag=1 and M.Enterprise_Id="
						+ epid
						+ " and M.MS_Type=2 and M.MS_Id in(select GM.MS_Id from  tb_MS_GRP_List GM  where GM.GRP_Id='"
						+ grpinfo.getGrpid() + "')";
				// ����Ҫ�����Ⱥ���õ�����Ⱥ��û���õ��ĵ���
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
	 * ��Ӷ���
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
	 * �޸Ķ���
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
	 * ��ȡ����
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
		
//		String strUserType = ""; // ����
//		String strStatue = ""; // ����״̬
		String strFlag = ""; // ��Ч״̬
//		String strMsId = ""; // �ն�id
//		String strMSName = ""; // �ն�����
		String stragent = ""; // ������id
//		String strEp = ""; // ��ҵid
//		String isMobile="";//�Ƿ������л�����绰ģʽ
//		String StrArrearage = ""; // ����/�ֳֻ�
		int pagent_id = Integer.parseInt(pagentid); // һ��������
		int cagent_id = Integer.parseInt(childagentid); // ����������
//		String hql = "from TbMsInfo M where 1 = 1 ";
//		if (!(userType.equals("3"))) // ���Ͳ���ȫ������
//			strUserType = " and M.msType = " + userType;
//
//		if (!(statue.equals("3"))) // ����״̬����ȫ��
//			strStatue = " and M.onlineStatus = " + statue;
//
//		if (!(flag.equals("2"))) // ��Ч״̬����ȫ��
//			strFlag = " and M.flag = " + flag;
//		if(!("2".equals(ismobile)))//0:��ֹ�л�;1:�����л�
//			isMobile=" AND M.c03="+ismobile;
//		
//		if (!(arrearage.equals("2"))) // ����/�ֳֻ����Ͳ���ȫ��
//			StrArrearage = " and M.c04 = " + arrearage;
//
//		if (msId.trim().length() > 0) // �ն����ǿ�,�����ȴ���0
//			strMsId = " and M.msId like '%" + msId + "%' ";
//
//		if (msName.trim().length() > 0) // �ն����ǿ�,�����ȴ���0
//			strMSName = " and M.msName like '%" + msName + "%' ";
//
//		if (pagent_id == -1) { // ˵�����ܲ�
//			if (cagent_id == -1) { // ֱ����ҵ(�ܲ�ֱ�ӷ�չ����ҵ)
//				stragent = " and  E.Agent_Id =" + pagentid;
//			} else { // -2 ������ҵ��ϵͳ��������ҵ��
//
//			}
//		} else { // һ��������(���ܲ���)
//			if (cagent_id == -1) // ֱ����ҵ(ָ��һ���������µ���ҵ)
//			{
//				stragent = " and E.Agent_Id=" + pagentid;
//			} else if (cagent_id == -2) { // ������ҵ
//											// (ָ��һ���������µ���ҵ�����һ�������������ж����������µĵ���ҵ
//				stragent = " and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="
//						+ pagentid + " or A.Agent_PId=" + pagentid + ")";
//			} else { // ָ�������������µ�������ҵ
//				stragent = " and E.Agent_Id=" + childagentid;
//			}
//		}
//
//		if (!(ep.equals("-1"))) { // �û���λ����ȫ��
//			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
//					+ ep + stragent + ")";
//		} else { // Ϊȫ��
//			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
//					+ stragent + ")";
//		}
//

//		String hql = "select M ,MT.pfType from TbMsInfo M " +
//				"LEFT JOIN TbPFInfo MT on M.pf.pfId = MT.pfId where 1 = 1 " ;
		String hql = "from TbMsInfo M " +
		" where 1 = 1 " ;
		if (!(flag.equals("2"))) // ��Ч״̬����ȫ��
			strFlag = " and M.flag = " + flag;		
		
//		if (pagent_id != -1) { // ˵�������ܲ�
//			if (cagent_id == -1) // ֱ����ҵ(ָ��һ���������µ���ҵ)
//			{
//				stragent = " and E.Agent_Id=" + pagentid;
//			} else if (cagent_id == -2) { // ������ҵ
//											// (ָ��һ���������µ���ҵ�����һ�������������ж����������µĵ���ҵ
//				stragent = " and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="
//						+ pagentid + " or A.Agent_PId=" + pagentid + ")";
//			} else { // ָ�������������µ�������ҵ
//				stragent = " and E.Agent_Id=" + childagentid;
//			}
//		}
	
		if(roleId == 2){//������
			stragent = " and M.Ep_Id in (select E.Ep_Id from TbEnterPriseInfo E where E.Agent_Id =" + agentid +") or M.agentId = "+agentid ;
		}else if(roleId == 3){//��ҵ�û�
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
