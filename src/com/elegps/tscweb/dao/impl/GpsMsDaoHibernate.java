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
	 * ��ȡGPS�����̵�¼�˺�������Ϣ���� GPS�����̵�¼�˺�������Ϣ�ܼ�¼��
	 * 
	 * @return ��ȡGPS�����̵�¼�˺�������Ϣ����
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
	 * ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @return ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
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
	 * ��ȡָ��GPS����������Ϣ����
	 * 
	 * @param gps_id
	 *            GPS�����ʺ�
	 * @return ��ȡָ��GPS����������Ϣ����
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
	 * ����GPS�����ն˶�Ӧ��ϵ�а�GPS�����ʺ�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @param gps_id
	 *            GPS�����ʺ�
	 * @return ����GPS�����ն˶�Ӧ��ϵ�а�GPS�����ʺ�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
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
	 * ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @return ����GPS�����ն˶�Ӧ��ϵָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
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
	 * ��ȡָ���ն˺�������Ϣ����
	 * 
	 * @param ms_id
	 *            �ն˺�
	 * @return ��ȡָ���ն˺�������Ϣ����
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
	 * GPS�����ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @param ms_id
	 *            �ն˺�
	 * @return ����GPS�����ն˶�Ӧ��ϵ�а��ն˺�ָ��ҳ������GPS�����ն˶�Ӧ��ϵ��Ϣ
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
	 * ɾ��GPS�������ն˶�Ӧ��ϵ��Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param tbgrpmsinfo
	 *            GPS�������ն˶�Ӧ��ϵ��Ϣ����
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
	 * ����gps_idɾ����¼
	 * 
	 * @param gps_id
	 *            GPS����
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
	 * ����ms_idɾ����¼
	 * 
	 * @param ms_id
	 *            Ⱥ�����
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
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * @param gpsid
	 * @param msid
	 * @return  ��ѯ����ȡ�ü�¼����
	 */
	public Integer findGpsMs_SearchCount(Session sess, String gpsid, String msid,String pagentid,String childagentid,String ep) {
		String hql="select count(gm.gpsop_id) from TbGpsMsListInfo gm,TbMsInfo m where gm.l_ms_id=m.msId ";
		String stragent="";// ������id
		String strEp="";   //��ҵid
		int pagent_id=Integer.parseInt(pagentid);  //һ��������
		int cagent_id=Integer.parseInt(childagentid);  //����������
		String strgps="";//gps
		String strms="";//�ն�
		
		
		if(!msid.equals("")){//�ն˺��벻��ȫ��
			strms=" and gm.l_ms_id='"+msid+"'";
		}
		if(!gpsid.equals("")){//Ⱥ����벻��ȫ��
			strgps=" and gm.gpsop_id='"+gpsid+"'";
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
			strEp = " and m.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // Ϊȫ��
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
	    * ����ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    * @param pageNo
	    * @param pageSize
	    * @param gpsid
	    * @param msid
	    * @return  ָ��ҳ����ѯ����ȡ�ü�¼��Ϣ
	    */
	public List findGpsMs_SearchByPage(Session sess, int pageNo, int pageSize,
			String gpsid, String msid,String pagentid,String childagentid,String ep) {
		String hql="select gm.gpsop_id,gm.l_ms_id, m.msName,g.gps_name from TbGpsMsListInfo gm,TbMsInfo m,TbGpsInfo g where gm.l_ms_id=m.msId and gm.gpsop_id=g.gpsop_id ";
		String stragent="";// ������id
		String strEp="";   //��ҵid
		int pagent_id=Integer.parseInt(pagentid);  //һ��������
		int cagent_id=Integer.parseInt(childagentid);  //����������
		String strgps="";//gps
		String strms="";//�ն�
		if(!msid.equals("")){//�ն˺��벻��ȫ��
			strms=" and gm.l_ms_id='"+msid+"'";
		}
		if(!gpsid.equals("")){//Ⱥ����벻��ȫ��
			strgps=" and gm.gpsop_id='"+gpsid+"'";
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
			strEp = " and m.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // Ϊȫ��
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
