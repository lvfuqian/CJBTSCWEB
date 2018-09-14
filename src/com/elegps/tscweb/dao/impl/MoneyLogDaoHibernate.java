package com.elegps.tscweb.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.elegps.tscweb.dao.MoneyLogDaoFactory;
import com.elegps.tscweb.model.TbMoneyLog;

public class MoneyLogDaoHibernate implements MoneyLogDaoFactory {

	@Override
	public void addMoneyLog(Session sess, TbMoneyLog mLog) {
		try {
			sess.save(mLog);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List getMoneyLogListByPage(Session sess,int pageNo, int pageSize, int roleId,int uId,
			String userName,String dateStart,String dateEnd,int ptype,
			String pay_num,String teadeNo,String orderNo) {
		// TODO Auto-generated method stub
		String hql="from TbMoneyLog M where M.payType= " +ptype;
		if(roleId != 40 && roleId != 35){
			hql += " and M.user.userId = " + uId ;
		}
		if(userName.trim().length()>0){
			hql +=" and M.user.userName like '%"+userName+"%'";
		}
		
		if(ptype != 0){
			if(ptype == 3){//回收企业余额的数据
				hql += " and M.payNum in ( select ep.Ep_Id from TbEnterpriseInfo ep where ep.Ep_Name like '%"+pay_num+"%' ) ";
			}else if(ptype == 4){//转移终端id的数据
				hql += " and M.payNum in ( select ms.msId from TbMsInfo ms where ms.msId like '%"+pay_num+"%' ) ";
			}
			else if(ptype == 1 || ptype == 2){//转移终端id的数据
				hql += " and M.payNum like '%"+pay_num+"%' ";
				if(teadeNo.trim().length()>0)
					hql += " and M.teadeNo like '%" + teadeNo + "%' ";
				if(orderNo.trim().length()>0)
					hql += " and M.money_Trade_No like '%" + orderNo+ "%' ";
			}
		}
		
		if(dateStart.trim().length()>0){
			if(dateEnd.trim().length()==0 || dateEnd==null){
				hql +=" and M.mlogTime between '"+dateStart+"' and NOW()" ;
			}else{
				hql +=" and M.mlogTime between '"+dateStart+"' and '" + dateEnd +"' " ;
			}
			
		}

		hql += "  order by M.mlogTime desc" ;
		try {
			int offset = (pageNo - 1) * pageSize;
			List list=sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
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
	public Integer getMoneyLogCount(Session sess, int roleId, int uId,
			String userName,String dateStart,String dateEnd ,int ptype,
			String pay_num,String teadeNo,String orderNo) {
		// TODO Auto-generated method stub
		String hql="select count(M.mlogId)from TbMoneyLog M where M.payType= " +ptype;
		
		if(ptype != 0){
			if(ptype == 3){//回收企业余额的数据
				hql += " and M.payNum in ( select ep.Ep_Id from TbEnterpriseInfo ep where ep.Ep_Name like '%"+pay_num+"%' ) ";
			}else if(ptype == 4){//转移终端id的数据
				hql += " and M.payNum in ( select ms.msId from TbMsInfo ms where ms.msId like '%"+pay_num+"%' ) ";
			}
			else if(ptype == 1 || ptype == 2){//转移终端id的数据
				hql += " and M.payNum like '%"+pay_num+"%' ";
				if(teadeNo.trim().length()>0)
					hql += " and M.teadeNo like '%" + teadeNo + "%' ";
				if(orderNo.trim().length()>0)
					hql += " and M.money_Trade_No like '%" + orderNo+ "%' ";
			}
		}
		
		if(roleId != 40 && roleId != 35){
			hql += " and M.user.userId = " + uId ;
		}
		if(userName.trim().length()>0){
			hql +=" and M.user.userName like '%"+userName+"%'";
		}
		if(dateStart.trim().length()>0){
			if(dateEnd.trim().length()==0 || dateEnd==null){
				hql +=" and M.mlogTime between '"+dateStart+"' and NOW()" ;
			}else{
				hql +=" and M.mlogTime between '"+dateStart+"' and '" + dateEnd +"' " ;
			}
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

}
