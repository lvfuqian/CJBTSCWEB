package com.elegps.tscweb.dao.impl;

import java.util.List;
import org.hibernate.Session;

import com.elegps.tscweb.dao.GpsDaoFactory;
import com.elegps.tscweb.dao.GrpDaoFactory;
import com.elegps.tscweb.model.TbGpsInfo;
import com.elegps.tscweb.model.TbGrpInfo;

public class GpsDaoHibernate implements GpsDaoFactory {

	/**
	 * ��ȡ����GPS�����̵�¼�˺�����
	 * 
	 * @return ����GPS�����̵�¼�˺ŵ�����
	 */
	public Integer getGps_idAllCount(Session sess) {
		try {
			Object obj = sess.createQuery(
					"select count(m.gpsop_id) from TbGpsInfo as m")
					.uniqueResult();
			if (obj != null) {
				int gpscount = (Integer) obj;
				return gpscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return 0;

	}

	/**
	 * �����ض�ҳ������GPS�����̵�¼�˺�
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @return ָ��ҳ��ȫ��GPS�����̵�¼�˺���Ϣ
	 */
	public List findAllGps_typeByPage(Session sess, int pageNo, int pageSize) {
		try {
			int offset = (pageNo - 1) * pageSize;
			List list=sess
			.createQuery("from TbGpsInfo m order by m.create_time desc")
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
	 * ��ȡ����GPS�����̵�¼�˺�����
	 * 
	 * @return ����GPS�����̵�¼�˺ŵ�����
	 */
	public Integer getGps_idCount(Session sess, String gps_id,String gps_name) {
		String hql="select count(m.gpsop_id) from TbGpsInfo as m ";
		if(gps_name==""){
			if(gps_id==""){
				
			}else{
				hql=hql+"where m.gpsop_id like '%"+ gps_id + "%'";
			}
		}else{
			if(gps_id==""){
				hql=hql+"where m.gps_name like '%"+gps_name+"%' ";
			}else{
				hql=hql+"where m.gps_name like '%"+gps_name+"%' and m.gpsop_id like '%"+ gps_id + "%'";
			}
		}
		try {
			Object obj = sess.createQuery(hql).uniqueResult();
			if (obj != null) {
				int gpscount = (Integer) obj;
				return gpscount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return 0;

	}

	/**
	 * �����ض�ҳ��ָ��GPS�����̵�¼�˺�
	 * 
	 * @param pageNo
	 *            ָ��ҳ��
	 * @param pagesize
	 *            ÿҳ��ʾ������
	 * @return ָ��ҳ��GPS�����̵�¼�˺���Ϣ
	 */
	public List findGps_typeByPage(Session sess, int pageNo, int pageSize,
			String gps_id,String gps_name) {
		String hql="from TbGpsInfo as m ";
		if(gps_name==""){
			if(gps_id==""){
				
			}else{
				hql=hql+"where m.gpsop_id like '%"+ gps_id + "%'";
			}
		}else{
			if(gps_id==""){
				hql=hql+"where m.gps_name like '%"+gps_name+"%' ";
			}else{
				hql=hql+"where m.gps_name like '%"+gps_name+"%' and m.gpsop_id like '%"+ gps_id + "%'";
			}
		}
		try {
			int offset = (pageNo - 1) * pageSize;
			List list=sess.createQuery(hql).setFirstResult(offset).setMaxResults(
			pageSize).list();
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
	 * �����������ط����̵�¼�˺���Ϣ��Ϣ
	 * 
	 * @param sess
	 *            �־û���������Ҫ��Hiberate Session
	 * @param gps_id
	 *            ��Ҫ���ص�¼�˺���Ϣ
	 * @return ���ط����̵�¼�˺���Ϣ��Ϣ
	 */
	public TbGpsInfo get(Session sess, String gps_id) {
		try {
			List ms = sess.createQuery("from TbGpsInfo m where m.gpsop_id=?")
					.setParameter(0, gps_id).list();
			if (ms != null && ms.size() > 0) {
				return (TbGpsInfo) ms.get(0);
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

	public String save(Session sess, TbGpsInfo m) {
		try {
			sess.save(m);
			sess.flush();
			return m.getGpsop_id();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;

	}

	public void delete(Session sess, TbGpsInfo m) {
		try {
			sess.delete(m);
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
	 * ����GPS������Ϣ
	 * 
	 * @return ָ��ҳ��ȫ��GPS������Ϣ
	 */
	public List findAllGps_Info(Session sess) {
		try {
			List list=sess.createQuery("from TbGpsInfo m  order by m.create_time desc").list();
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

	public TbGpsInfo getidorname(Session sess, String gps_id, String gps_name) {
		try {
			List ms = sess.createQuery("from TbGpsInfo m where m.gpsop_id='"+gps_id+"' or m.gps_name='"+gps_name+"'").list();
			if (ms != null && ms.size() > 0) {
				return (TbGpsInfo) ms.get(0);
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

	public TbGpsInfo get_byname(Session sess, String gps_id, String gps_name) {
		try {
			List ms = sess.createQuery("from TbGpsInfo m where m.gpsop_id!='"+gps_id+"' and m.gps_name='"+gps_name+"'").list();
			if (ms != null && ms.size() > 0) {
				return (TbGpsInfo) ms.get(0);
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
