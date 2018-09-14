package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.dao.AdvDaoFactory;
import com.elegps.tscweb.model.TbAdvInfo;

public class AdvDaoHibernate implements AdvDaoFactory {

	@Override
	public void addAdvInfo(Session sess, TbAdvInfo advInfo) {
		// TODO Auto-generated method stub
		try {
			sess.save(advInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void deleteAdvInfo(Session sess, TbAdvInfo advInfo) {
		// TODO Auto-generated method stub
		try {
			sess.delete(advInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public List<TbAdvInfo> findAllAdvInfo(Session sess) {
		// TODO Auto-generated method stub
		try {
			List<TbAdvInfo> cm =(List<TbAdvInfo>)sess.createQuery(" from TbAdvInfo order by creatTime desc").list();
			if (cm != null && cm.size() > 0) {
				return cm;
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
	public TbAdvInfo findAdvInfoById(Session sess, int advId) {
		// TODO Auto-generated method stub
		String hql =" from TbAdvInfo cm where advId = "+advId;
		try {
			List cm = sess.createQuery(hql).list();
			if (cm != null && cm.size() > 0) {
				
				return (TbAdvInfo)cm.get(0);
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
	public void updateAdvInfo(Session sess, TbAdvInfo advInfo) {
		// TODO Auto-generated method stub
		try {
			sess.merge(advInfo);
			//sess.saveOrUpdate(checkInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public List<TbAdvInfo> findAllAdvInfo(Session sess,Integer pageNo,Integer pageSize,String title,String url,
			String sendSTime,String sendETime,String creatSTime,String creatETime,int advType) {
		Integer offset = (pageNo - 1) * pageSize;
		StringBuffer hql = new StringBuffer("from TbAdvInfo A where 1=1");
		if(!title.equals("") && !title.equals(null))
			hql.append(" and A.advTitle like '%"+title+"%' ");
		if(!url.equals("") && !url.equals(null))
			hql.append(" and A.advUrl like '%"+url+"%' ");
		if(advType != -1)
			hql.append(" and A.advType = "+advType);
		if(!sendSTime.equals("") && !sendSTime.equals(null)){
			if(!sendETime.equals("") && !sendETime.equals(null)){
				hql.append(" and A.sendSTime > '"+sendSTime+"' and A.sendETime < ' "+ sendETime +" ' ");
			}else{
				hql.append(" and A.sendSTime > '"+sendSTime+"' ");
			}
		}else{
			if(!sendETime.equals("") && !sendETime.equals(null)){
				hql.append(" and A.sendETime < '"+sendETime+"' ");
			}
		}
		if(!creatSTime.equals("") && !creatSTime.equals(null)){
			if(!creatETime.equals("") && !creatETime.equals(null)){
				hql.append(" and A.creatTime between '"+creatSTime+"' and ' "+ creatETime +" ' ");
			}else{
				hql.append(" and A.creatTime > '"+creatSTime+"' ");
			}
		}else{
			if(!creatETime.equals("") && !creatETime.equals(null)){
				hql.append(" and A.creatTime < '"+creatETime+"' ");
			}
		}
		
		hql.append(" order by A.creatTime desc");
		try {
			List list=sess.createQuery(hql.toString()).setFirstResult(offset)
			.setMaxResults(pageSize).list();
			if(list!=null){
				return list ;
		   }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null ;
	}

	@Override
	public Integer findAdvCount(Session sess,String title,String url,String sendSTime,
			String sendETime,String creatSTime,String creatETime,int advType) {
		StringBuffer hql = new StringBuffer("select count(A.advId) from TbAdvInfo A where 1=1");
		if(!title.equals("") && !title.equals(null))
			hql.append(" and A.advTitle like '%"+title+"%' ");
		if(!url.equals("") && !url.equals(null))
			hql.append(" and A.advUrl like '%"+url+"%' ");
		if(advType != -1)
			hql.append(" and A.advType = "+advType);
		
		if(!sendSTime.equals("") && !sendSTime.equals(null)){
			if(!sendETime.equals("") && !sendETime.equals(null)){
				hql.append(" and A.sendSTime > '"+sendSTime+"' and A.sendETime < ' "+ sendETime +" ' ");
			}else{
				hql.append(" and A.sendSTime > '"+sendSTime+"' ");
			}
		}else{
			if(!sendETime.equals("") && !sendETime.equals(null)){
				hql.append(" and A.sendETime < '"+sendETime+"' ");
			}
		}
		if(!creatSTime.equals("") && !creatSTime.equals(null)){
			if(!creatETime.equals("") && !creatETime.equals(null)){
				hql.append(" and A.creatTime between '"+creatSTime+"' and ' "+ creatETime +" ' ");
			}else{
				hql.append(" and A.creatTime > '"+creatSTime+"' ");
			}
		}else{
			if(!creatETime.equals("") && !creatETime.equals(null)){
				hql.append(" and A.creatTime < '"+creatETime+"' ");
			}
		}
		try {
			Object obj = sess.createQuery(hql.toString()).uniqueResult();
			if (obj!= null) {
				Integer advcount = (Integer) obj;
				return advcount;
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
