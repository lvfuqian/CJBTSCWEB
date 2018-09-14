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
	 * ��ѯȺ��������Ϣ������
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @return ����Ⱥ��������Ϣ����
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
	 * ��ѯָ��Ⱥ����Ч״̬Ϊ1��ָ��ҳ��Ⱥ����Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param pageNo
	 *            ��Ҫ��ѯ��ָ��ҳ
	 * @param pageSize
	 *            ҳ�ĸ���
	 * @param onlineStatus
	 *            �ն�����״̬
	 * @return ��ѯ����Ⱥ����Ϣ����
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
	 * ��ѯȺ��������Ϣ������
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param grp_type
	 *            Ⱥ������
	 * @return Ⱥ��������Ϣ����
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
	 * ��ѯָ��Ⱥ�����͡�ָ��ҳ��Ⱥ��������Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param pageNo
	 *            ��Ҫ��ѯ��ָ��ҳ
	 * @param pageSize
	 *            ��Ҫ��ѯ��ָ��ҳ����
	 * @param grp_type
	 *            ��Ҫ��ѯ��ָ��Ⱥ������
	 * @return ��ѯ����Ⱥ����Ϣ����
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
	 * ������������Ⱥ����Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param grp_id
	 *            ��Ҫ���ص�Ⱥ����Ϣ
	 * @return ���ص�Ⱥ����Ϣ
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
	 * ��ѯ����Ⱥ������ͨ��״̬��Ϣ������
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @return ����Ⱥ������ͨ��״̬��Ϣ����
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
	 * ��ѯָ��Ⱥ����Ч����״̬Ϊ1��ָ��ҳ���ն��û���Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param pageNo
	 *            ��Ҫ��ѯ��ָ��ҳ
	 * @param pageSize
	 *            ҳ�ĸ���
	 * @return ��ѯ����Ⱥ��������Ч״̬��Ϣ���� ������ͨ��״̬����
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
	 * ��ѯȺ����Ч����״̬��Ϣ������
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param status
	 *            Ⱥ����Ч����״̬
	 * @return Ⱥ����Ч����״̬��Ϣ������
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
	 * ����grpid��ģ����ѯ
	 * 
	 * @return Ⱥ��grpid��ģ����ѯ������
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
	 * ����grpid��ģ����ѯ
	 * 
	 * @return Ⱥ��grpid��ģ����ѯҳ��ȫ��Ⱥ����Ϣ
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
	 * ��ѯ����Ⱥ����Ч״̬��Ϣ������
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @return ����Ⱥ����Ч״̬��Ϣ����
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
	 * ��ѯ����Ⱥ����Ч״̬��Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @return ����Ⱥ����Ч״̬��Ϣ
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
	 * ��ȡָ��Ⱥ����Ч״̬��Ϣ����
	 * 
	 * @param flag
	 *            Ⱥ����Ч״��Ӧ�ܼ�¼��
	 * @return ָ��Ⱥ����Ч״̬��Ϣ����
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
	 * ��ѯָ��Ⱥ����Ч״̬��Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param falg
	 *            Ⱥ����Ч״̬
	 * @return ָ��Ⱥ����Ч״̬��Ϣ
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
	 * ����Ⱥ����Ϣ
	 * 
	 * @return ָ��ҳ��ȫ��Ⱥ����Ϣ
	 */
	public List findAllGrp_Info(Session sess,String pagentid,String childagentid,String ep) {

		String stragent="";// ������id
		String strEp="";   //��ҵid
		int pagent_id=Integer.parseInt(pagentid);  //һ��������
		int cagent_id=Integer.parseInt(childagentid);  //����������
		String hql="";
		hql="  FROM TbGrpInfo G where G.flag=1 ";
		if(pagent_id==-1){   //˵�����ܲ�
			if(cagent_id==-1){    //ֱ����ҵ(�ܲ�ֱ�ӷ�չ����ҵ)
				stragent=" and  E.Agent_Id ="+pagentid;
			}else{              //-2 ������ҵ��ϵͳ��������ҵ��
				
			}
		}else{  //һ��������(���ܲ���)
			if(cagent_id==-1)    //ֱ����ҵ(ָ��һ���������µ���ҵ)
			{
				stragent=" and E.Agent_Id="+pagentid;
			}else if(cagent_id==-2){   //������ҵ (ָ��һ���������µ���ҵ�����һ�������������ж����������µĵ���ҵ
				stragent=" and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+pagentid+" or A.Agent_PId="+pagentid+")";
			}else{     //ָ�������������µ�������ҵ
				stragent=" and E.Agent_Id="+childagentid;
			}
		}

		if(ep != null && !ep.equals("")){
			if (!(ep.equals("-1"))) { // �û���λ����ȫ��
				strEp = " and G.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
						+ ep + stragent + ")";
			} else { // Ϊȫ��
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

		String stragent="";// ������id
		String strEp="";   //��ҵid
		int pagent_id=Integer.parseInt(pagentid);  //һ��������
		int cagent_id=Integer.parseInt(childagentid);  //����������
		String hql="";
		hql="  FROM TbGrpInfo G where G.flag=1 AND G.c10='0' ";
		if(pagent_id==-1){   //˵�����ܲ�
			if(cagent_id==-1){    //ֱ����ҵ(�ܲ�ֱ�ӷ�չ����ҵ)
				stragent=" and  E.Agent_Id ="+pagentid;
			}else{              //-2 ������ҵ��ϵͳ��������ҵ��
				
			}
		}else{  //һ��������(���ܲ���)
			if(cagent_id==-1)    //ֱ����ҵ(ָ��һ���������µ���ҵ)
			{
				stragent=" and E.Agent_Id="+pagentid;
			}else if(cagent_id==-2){   //������ҵ (ָ��һ���������µ���ҵ�����һ�������������ж����������µĵ���ҵ
				stragent=" and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+pagentid+" or A.Agent_PId="+pagentid+")";
			}else{     //ָ�������������µ�������ҵ
				stragent=" and E.Agent_Id="+childagentid;
			}
		}

		
		if (!(ep.equals("-1"))) { // �û���λ����ȫ��
			strEp = " and G.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // Ϊȫ��
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
	 * ����grp_id,ms_id��ѯ�ǲ�������ն˴�������
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

	/**Ⱥ����ѯ����
	 * @param grp_type  Ⱥ������
	 * @param statue  ͨ��״̬
	 * @param flag  ��Ч״̬
	 * @param ms_id  Ⱥ�����
	 * @param ms_id  Ⱥ������
	 * @return Ⱥ��ģ����ѯ������
	 */
	public Integer  findGrp_sertchCount(Session sess,String grp_type,String statue,String flag,String grp_id,String grp_name,String pagentid,String childagentid,String ep){
		String grpType = "";  //����
		String grpStatue="";    //����״̬
		String grpFlag="";   //��Ч״̬
		String grpId="";  //Ⱥ��id
		String grpName="";  //Ⱥ������
		String stragent="";// ������id
		String strEp="";   //��ҵid
		int pagent_id=Integer.parseInt(pagentid);  //һ��������
		int cagent_id=Integer.parseInt(childagentid);  //����������
		String hql="select count(G.grpid) from TbGrpInfo G where 1=1";
		
		if(!(grp_type.equals("99")))  //���Ͳ���ȫ������
			grpType = " and G.grptype = " +  grp_type;
        
		if(!(statue.equals("2")))  //����״̬����ȫ��
			grpStatue = " and G.status = " +  statue;
		
		if(!(flag.equals("2")))  //��Ч״̬����ȫ��
			grpFlag = " and G.flag = " +  flag;
		
		if(grp_id.trim().length() > 0)   //Ⱥ�����ǿ�,�����ȴ���0
			grpId = " and G.grpid like '%" + grp_id + "%' ";
		
		if(grp_name.trim().length() > 0)   //�ն����ǿ�,�����ȴ���0
			grpName = " and G.grpname like '%" + grp_name + "%' ";
		
		if(pagent_id==-1){   //˵�����ܲ�
			if(cagent_id==-1){    //ֱ����ҵ(�ܲ�ֱ�ӷ�չ����ҵ)
				stragent=" and  E.Agent_Id ="+pagentid;
			}else{              //-2 ������ҵ��ϵͳ��������ҵ��
				
			}
		}else{  //һ��������(���ܲ���)
			if(cagent_id==-1)    //ֱ����ҵ(ָ��һ���������µ���ҵ)
			{
				stragent=" and E.Agent_Id="+pagentid;
			}else if(cagent_id==-2){   //������ҵ (ָ��һ���������µ���ҵ�����һ�������������ж����������µĵ���ҵ
				stragent=" and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+pagentid+" or A.Agent_PId="+pagentid+")";
			}else{     //ָ�������������µ�������ҵ
				stragent=" and E.Agent_Id="+childagentid;
			}
		}

		
		if (!(ep.equals("-1"))) { // �û���λ����ȫ��
			strEp = " and G.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // Ϊȫ��
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

	
	/**Ⱥ����ѯ��Ϣ
	 * @param grp_type  Ⱥ������
	 * @param statue  ͨ��״̬
	 * @param flag  ��Ч״̬
	 * @param grp_id  Ⱥ�����
	 * @param grp_name  Ⱥ������
	 * @return Ⱥ��ģ����ѯ����Ϣ
	 */
	public List findGrp_sertchByPage(Session sess, int pageNo, int pageSize,
			String grp_type, String statue, String flag, String grp_id,
			String grp_name,String pagentid,String childagentid,String ep) {
		
		String grpType = "";  //����
		String grpStatue="";    //����״̬
		String grpFlag="";   //��Ч״̬
		String grpId="";  //Ⱥ��id
		String grpName="";  //Ⱥ������
		String stragent="";// ������id
		String strEp="";   //��ҵid
		int pagent_id=Integer.parseInt(pagentid);  //һ��������
		int cagent_id=Integer.parseInt(childagentid);  //����������
		String hql="from TbGrpInfo G where 1=1 ";
		if(!(grp_type.equals("99")))  //���Ͳ���ȫ������
			grpType = " and G.grptype = " +  grp_type;
        
		if(!(statue.equals("2")))  //����״̬����ȫ��
			grpStatue = " and G.status = " +  statue;
		
		if(!(flag.equals("2")))  //��Ч״̬����ȫ��
			grpFlag = " and G.flag = " +  flag;
		
		if(grp_id.trim().length() > 0)   //Ⱥ�����ǿ�,�����ȴ���0
			grpId = " and G.grpid like '%" + grp_id + "%' ";
		
		if(grp_name.trim().length() > 0)   //�ն����ǿ�,�����ȴ���0
			grpName = " and G.grpname like '%" + grp_name + "%' ";
		
		if(pagent_id==-1){   //˵�����ܲ�
			if(cagent_id==-1){    //ֱ����ҵ(�ܲ�ֱ�ӷ�չ����ҵ)
				stragent=" and  E.Agent_Id ="+pagentid;
			}else{              //-2 ������ҵ��ϵͳ��������ҵ��
				
			}
		}else{  //һ��������(���ܲ���)
			if(cagent_id==-1)    //ֱ����ҵ(ָ��һ���������µ���ҵ)
			{
				stragent=" and E.Agent_Id="+pagentid;
			}else if(cagent_id==-2){   //������ҵ (ָ��һ���������µ���ҵ�����һ�������������ж����������µĵ���ҵ
				stragent=" and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+pagentid+" or A.Agent_PId="+pagentid+")";
			}else{     //ָ�������������µ�������ҵ
				stragent=" and E.Agent_Id="+childagentid;
			}
		}

		
		if (!(ep.equals("-1"))) { // �û���λ����ȫ��
			strEp = " and G.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // Ϊȫ��
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
	 * ��ѯ�����û���λ��Ϣ zr
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
	 * �����û���λid��ѯ�û���λ��Ϣ zr
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
	 * ����Ⱥ��ID��ѯȺ���������û���λ zr
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

	//--------------�ּ������õ�------------
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
	 * ִ��ָ��hql
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
	 * ��ѯ����
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
	 * ��ѯ����
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
	 * ͳ������
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
	 * �޸Ķ���
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
