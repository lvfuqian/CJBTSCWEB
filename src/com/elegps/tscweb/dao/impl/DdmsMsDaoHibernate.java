package com.elegps.tscweb.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import com.elegps.tscweb.dao.DdmsMsDaoFactory;
import com.elegps.tscweb.dao.GpsMsDaoFactory;
import com.elegps.tscweb.model.TbDdmsMsListInfo;
import com.elegps.tscweb.model.TbGpsMsListInfo;
import com.elegps.tscweb.model.TbGrpMsListInfo;
import com.elegps.tscweb.model.TbMsInfo;

public class DdmsMsDaoHibernate implements DdmsMsDaoFactory {
	/**
	 * ���ݲ�ѯ����ȡ�ü�¼����
	 * 
	 * @param gpsid
	 * @param msid
	 * @return ��ѯ����ȡ�ü�¼����
	 */
	public Integer findDdmsMs_SearchCount(Session sess, String ddmsid,
			String msid,String agent,String ep) {
		String hql = "select count(dm.ddms_id) from TbDdmsMsListInfo dm where 1=1 ";
		String stragent="";//������
		String strep="";//�û���λ
		String strddms="";//�����ն�
		String strms="";//�ն�
		if(!ddmsid.equals("")){//�����ն˺��벻��ȫ��
			strddms=" and dm.ddms_id='"+ddmsid+"'";
		}
		if(!msid.equals("")){//�ն˺��벻��ȫ��
			strms=" and dm.ms_id='"+msid+"'";
		}
		if(!agent.equals("")){//�����̲���ȫ��
			stragent=" and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+agent+")";
			if(!ep.equals("")){//�û���λ����ȫ��
				strep=" and dm.ms_id in(select M.msId from TbMsInfo M where 1=1 and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="+ep+stragent+")) and " +
						" dm.ddms_id in(select M.msId from TbMsInfo M where 1=1 and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="+ep+stragent+")) ";
			}else{
				strep=" and dm.ms_id in(select M.msId from  TbMsInfo M where 1=1 and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "+stragent+")) and " +
						" dm.ddms_id in(select M.msId from  TbMsInfo M where 1=1 and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "+stragent+")) ";
			}
		}else{
			if(!ep.equals("")){//�û���λ����ȫ��
				strep=" and dm.ms_id in(select M.msId from TbMsInfo M where 1=1 and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="+ep+")) and " +
						" dm.ddms_id in(select M.msId from TbMsInfo M where 1=1 and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="+ep+")) ";
			}else{
				
			}
		}
	
		hql+=strddms+strms+strep;
		try {
			Object obj = sess.createQuery(hql).uniqueResult();
			if (obj != null) {
				int ddmscount = (Integer) obj;
				return ddmscount;
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

	public List findDdms_Info(Session sess) {
		try {
			List list = sess.createQuery("select DISTINCT(m.ddms_id) from TbDdmsMsListInfo m order by m.ddms_id desc").list();
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

	public List findMs_Info(Session sess) {
		try {
			List list = sess
					.createQuery(
							"from TbDdmsMsListInfo m order by m.ms_id desc")
					.list();
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

	
	public List findDdmsMs_SearchByPage(Session sess, int pageNo, int pageSize,
			String ddmsid, String msid,String agent,String ep) {
		String hql = " from TbDdmsMsListInfo dm where 1=1 ";
		String stragent="";//������
		String strep="";//�û���λ
		String strddms="";//�����ն�
		String strms="";//�ն�
		if(!ddmsid.equals("")){//�����ն˺��벻��ȫ��
			strddms=" and dm.ddms_id='"+ddmsid+"'";
		}
		if(!msid.equals("")){//�ն˺��벻��ȫ��
			strms=" and dm.ms_id='"+msid+"'";
		}
		if(!agent.equals("")){//�����̲���ȫ��
			stragent=" and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+agent+")";
			if(!ep.equals("")){//�û���λ����ȫ��
				strep=" and dm.ms_id in(select M.msId from TbMsInfo M where 1=1 and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="+ep+stragent+")) and " +
						" dm.ddms_id in(select M.msId from TbMsInfo M where 1=1 and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="+ep+stragent+")) ";
			}else{
				strep=" and dm.ms_id in(select M.msId from  TbMsInfo M where 1=1 and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "+stragent+")) and " +
						" dm.ddms_id in(select M.msId from  TbMsInfo M where 1=1 and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "+stragent+")) ";
			}
		}else{
			if(!ep.equals("")){//�û���λ����ȫ��
				strep=" and dm.ms_id in(select M.msId from TbMsInfo M where 1=1 and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="+ep+")) and " +
						" dm.ddms_id in(select M.msId from TbMsInfo M where 1=1 and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="+ep+")) ";
			}else{
				
			}
		}
		
		hql+=strddms+strms+strep+" order by dm.create_time desc";
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

	public TbDdmsMsListInfo get(Session sess, String ddms_id, String ms_id) {
		try {
			List ddmssms = sess.createQuery(
					"from TbDdmsMsListInfo m where m.ddms_id='" + ddms_id
							+ "' and m.ms_id='" + ms_id + "'").list();
			if (ddmssms != null && ddmssms.size() > 0) {
				return (TbDdmsMsListInfo) ddmssms.get(0);
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

	public String save(Session sess, TbDdmsMsListInfo ddmsms) {
		try {
			sess.save(ddmsms);
			sess.flush();
			return new String("suc");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	public void delete(Session sess, TbDdmsMsListInfo tbddmsmsinfo) {
		try {
			sess.delete(tbddmsmsinfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
	}

	public List findinDdms_Info(Session sess) {
		try {
			List inddmslist = sess.createQuery("from TbMsInfo m where m.msType=2 and  m.flag=1").list();
			if (inddmslist != null && inddmslist.size() > 0) {
				return inddmslist;
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

	public List findnotDdms_Info(Session sess) {
		try {
			List notddmslist = sess.createQuery("from TbMsInfo m where m.msType!=2 and m.flag=1 and m.msId not in(select c.ms_id from TbDdmsMsListInfo c)").list();
			if (notddmslist != null && notddmslist.size() > 0) {
				return notddmslist;
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

	public void deletems_id(Session sess, String ms_id) {
		try {
			String hql = "delete TbDdmsMsListInfo where ddms_id='"+ms_id+"' or ms_id='"+ms_id+"'";
			Query q = sess.createQuery(hql);
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
	 * ���ݵ����û�ID��ѯ�����Ѿ����ڵķǵ����û� zr
	 * @param sess
	 * @param gpsid
	 * @return
	 */
	public List findAllms_bymsid(Session sess, String ddmsid) {		
		try {			
			List existmslist = sess.createQuery("from TbMsInfo ms where ms.msId in(select ddms.ms_id from TbDdmsMsListInfo ddms where ddms.ddms_id='"+ddmsid+"')").list();
			if (existmslist != null && existmslist.size() > 0) {
				return existmslist;
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
	 * �����û�ID�²����ڵ��û� zr
	 * @param sess
	 * @param gpsid
	 * @return
	 */
	public List findms_notinddms(Session sess, String ddmsid) {
		try {
			List list=sess.createQuery("from TbMsInfo ms where ms.msId not in(select ddms.ms_id from TbDdmsMsListInfo ddms) and ms.flag=1 and msType!=2").list();
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
	 *  ɾ�������û�ID�£��������ڵ��ն��û� zr
	 */
	public void deleteexistms(Session sess, String ddms_id) {
		try {
			String hql = "delete TbDdmsMsListInfo where ddms_id=?";
			Query query = sess.createQuery(hql);
			query.setString(0, ddms_id);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				//sess.close();
			}
		}
	}
}
