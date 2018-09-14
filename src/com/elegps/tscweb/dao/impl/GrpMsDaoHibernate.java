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
	 * ��ȡȺ��������Ϣ���� Ⱥ��������Ϣ�ܼ�¼��
	 * 
	 * @return ��ȡȺ��������Ϣ����
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
	 * ��ȡָ��Ⱥ���������Ϣ����
	 * 
	 * @param grp_id
	 *            Ⱥ���
	 * @return ��ȡָ��Ⱥ���������Ϣ����
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
	 * ����Ⱥ���ն˶�Ӧ��ϵ�а�Ⱥ���ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @param grp_id
	 *            Ⱥ���
	 * @return ����Ⱥ���ն˶�Ӧ��ϵ�а�Ⱥ���ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
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
	 * ��ȡ�ն�������Ϣ���� Ⱥ��������Ϣ�ܼ�¼��
	 * 
	 * @return ��ȡȺ��������Ϣ����
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
	 * ��ȡָ���ն˺�������Ϣ����
	 * 
	 * @param ms_id
	 *            �ն˺�
	 * @return ��ȡָ���ն˺�������Ϣ����
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
	 * Ⱥ���ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @param ms_id
	 *            �ն˺�
	 * @return ����Ⱥ���ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������Ⱥ���ն˶�Ӧ��ϵ��Ϣ
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
	 * ��������������Ⱥ����˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param ms_id
	 *            �ն˺�
	 * @param grp_id
	 *            �ն˺�
	 * @return ��������������Ⱥ����˶�Ӧ��ϵ��Ϣ
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
	 * ����msid���Ҷ�ӦȺ���ϵ��Ϣ
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
	 * ����grp_idɾ����¼
	 * 
	 * @param grp_id
	 *            Ⱥ�����
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
	 * ����ms_idɾ����¼
	 * 
	 * @param ms_id
	 *            Ⱥ�����
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
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param grpid
	 * @param msid
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
	public Integer findGrpMs_SearchCount(Session sess, String grpid, String msid,String pagentid,String childagentid,String ep) {
		    String hql="select count(gm.grp_id) from TbGrpMsListInfo gm,TbMsInfo m,TbGrpInfo g where gm.ms_id=m.msId and gm.grp_id=g.grpid";
			String strgrp="";//Ⱥ��
			String strms="";//�ն�
			String stragent="";// ������id
			String strEp="";   //��ҵid
			int pagent_id=Integer.parseInt(pagentid);  //һ��������
			int cagent_id=Integer.parseInt(childagentid);  //����������
			if(!msid.equals("")){//�ն˺��벻��ȫ��
				strms=" and gm.ms_id='"+msid+"'";
			}
			if(!grpid.equals("")){//Ⱥ����벻��ȫ��
				strgrp=" and gm.grp_id='"+grpid+"'";
			}
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
				strEp = " and g.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
						+ ep + stragent + ")";
			} else { // Ϊȫ��
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
	    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    * @param pageNo
	    * @param pageSize
	    * @param grpid
	    * @param msid
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
	public List findGrpMs_SearchByPage(Session sess, int pageNo, int pageSize,String pagentid,String childagentid,String ep,
			String grpid, String msid) {
		String hql="select gm.grp_id,g.grpname,gm.ms_id,m.msName,gm.config from TbGrpMsListInfo gm,TbMsInfo m,TbGrpInfo g where gm.ms_id=m.msId and gm.grp_id=g.grpid ";
		String strgrp="";//Ⱥ��
		String strms="";//�ն�
		String stragent="";// ������id
		String strEp="";   //��ҵid
		int pagent_id=Integer.parseInt(pagentid);  //һ��������
		int cagent_id=Integer.parseInt(childagentid);  //����������
		if(!msid.equals("")){//�ն˺��벻��ȫ��
			strms=" and gm.ms_id='"+msid+"'";
		}
		if(!grpid.equals("")){//Ⱥ����벻��ȫ��
			strgrp=" and gm.grp_id='"+grpid+"'";
		}

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
			strEp = " and g.Ep_Id in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // Ϊȫ��
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
	 * ɾ��
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
	 * ɾ��
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
