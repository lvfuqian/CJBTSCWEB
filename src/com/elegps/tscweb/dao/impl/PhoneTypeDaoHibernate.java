package com.elegps.tscweb.dao.impl;

import java.util.List;
import org.hibernate.Session;
import com.elegps.tscweb.dao.PhoneTypeDaoFactory;
import com.elegps.tscweb.model.TbPhoneTypeInfo;

public class PhoneTypeDaoHibernate implements PhoneTypeDaoFactory {

	@Override
	public void addPTInfo(Session sess, TbPhoneTypeInfo ptInfo) {
		// TODO Auto-generated method stub
		try {
			sess.save(ptInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@SuppressWarnings("rawtypes")
	@Override
	public TbPhoneTypeInfo findOneByTypeAndFlag(Session sess, String type,int flag) {
		String hql =" from TbPhoneTypeInfo where type = '"+type+"'";
		if(flag==1){//开启的
			hql +=" and flag = 1";
		}else if(flag==0){//关闭的
			hql +=" and flag = 0";
		}
		try {
			List pt = sess.createQuery(hql).list();
			if (pt != null && pt.size() > 0) {
				
				return (TbPhoneTypeInfo)pt.get(0);
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

	@SuppressWarnings("unchecked")
	@Override
	public String findAllPTInfo(Session sess) {
		try {
			List<String> pt =(List<String>)sess.createQuery("select type from TbPhoneTypeInfo ").list();
			StringBuffer typeList = new StringBuffer();
			if (pt != null && pt.size() > 0) {
				for (int i = 0; i < pt.size(); i++) {
					if(i != pt.size()-1){
						typeList.append(pt.get(i)+",");
					}else{
						typeList.append(pt.get(i));
					}
				}
				return typeList.toString();
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
	public void deletePTInfo(Session sess, TbPhoneTypeInfo ptInfo) {
		try {
			sess.delete(ptInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public int getPtCount(Session sess, String type) {
		String hql="select count(pt.id) from TbPhoneTypeInfo pt where 1=1";
		if(type.trim().length()>0){
			hql +=" and pt.type like '%"+type+"%'";
		}
		try {
			Object obj = sess.createQuery(hql).uniqueResult();
			if (obj != null) {
				int menucount = (Integer) obj;
				return menucount;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbPhoneTypeInfo> getPtList(Session sess, int pageNo,
			int pageSize, String type) {
		String hql="from TbPhoneTypeInfo pt where 1=1 ";
		if(type.trim().length()>0){
			hql +=" and pt.type like '%"+type+"%'";
		}


		hql += "  order by pt.id desc" ;
		try {
			int offset = (pageNo - 1) * pageSize;
			List<TbPhoneTypeInfo> list=sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
			if(list!=null){
				return list;
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public void updatePt(Session sess, TbPhoneTypeInfo ptInfo) {
		try {
			sess.clear();
			sess.saveOrUpdate(ptInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				//sess.close();
			}
		}
	}

	@Override
	public TbPhoneTypeInfo findOneById(Session sess, int id) {
		String hql =" from TbPhoneTypeInfo where id = "+id;
		try {
			List pt = sess.createQuery(hql).list();
			if (pt != null && pt.size() > 0) {
				
				return (TbPhoneTypeInfo)pt.get(0);
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
