package com.elegps.tscweb.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import com.elegps.tscweb.comm.DateFormat;
import com.elegps.tscweb.dao.AppPayDaoFactory;
import com.elegps.tscweb.model.TbAppPayInfo;
import com.elegps.tscweb.model.TbKoFeiLogInfo;

public class AppPayDaoHibernate implements AppPayDaoFactory {

	@Override
	public void addAPInfo(Session sess, TbAppPayInfo apInfo) {
		try {
			sess.save(apInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteApInfo(Session sess, TbAppPayInfo apInfo) {
		try {
			sess.delete(apInfo);
			sess.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TbAppPayInfo> findAllApInfo(Session sess) {
		return null;
	}

	@Override
	public List<TbAppPayInfo> findAllApInfo(Session sess, int pageNo,
			int pageSize) {
		return null;
	}

	@Override
	public Integer findApCount(Session sess) {
		return null;
	}

	@Override
	public TbAppPayInfo findApInfoById(Session sess, String id) {
		try {
			List ap = sess.createQuery(" from TbAppPayInfo ap where ap.id=?")
					.setParameter(0, id).list();
			if (ap != null && ap.size() > 0) {
				return (TbAppPayInfo) ap.get(0);
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
	public List<TbAppPayInfo> findApInfoByPage(Session sess,int pageNo,int pageSize,String phonems,int payType) {
		StringBuffer hql = new StringBuffer(" from TbAppPayInfo ap where 1=1 ");
		if(payType == 0){	//查询自己充值信息
			hql.append(" and ap.payer = '");
			hql.append(phonems);
			hql.append("' and ap.phoneOrMs = '");
			hql.append(phonems);
			hql.append("' ");
		}else if(payType == 1){//查询为其他号充值信息
			hql.append(" and ap.payer = '");
			hql.append(phonems);
			hql.append("' and ap.phoneOrMs != '");
			hql.append(phonems);
			hql.append("' ");
		}else if(payType == 2){//查询他人为我充值信息
			hql.append(" and ap.payer != '");
			hql.append(phonems);
			hql.append("' and ap.phoneOrMs = '");
			hql.append(phonems);
			hql.append("'");
		}else{//所有我的记录
			hql.append(" and ap.payer = '");
			hql.append(phonems);
			hql.append("' or ap.phoneOrMs = '");
			hql.append(phonems);
			hql.append("' ");
		}
		hql.append(" order by ap.time desc");
		int offset = (pageNo - 1) * pageSize;
		try {
			List ap = sess.createQuery(hql.toString())
			.setFirstResult(offset).setMaxResults(pageSize).list();
			if (ap != null && ap.size() > 0) {
				return ap;
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
	public void updateApInfo(Session sess, TbAppPayInfo apInfo) {
		try {
			sess.clear();
			sess.saveOrUpdate(apInfo);
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
	public Integer findApCountByPage(Session sess, String phonems, int payType) {
		StringBuffer hql = new StringBuffer("select count(ap.id) from TbAppPayInfo ap where 1=1 ");
		if(payType == 0){	//查询自己充值信息
			hql.append(" and ap.payer = '");
			hql.append(phonems);
			hql.append("' and ap.phoneOrMs = '");
			hql.append(phonems);
			hql.append("' ");
		}else if(payType == 1){//查询为其他号充值信息
			hql.append(" and ap.payer = '");
			hql.append(phonems);
			hql.append("' and ap.phoneOrMs != '");
			hql.append(phonems);
			hql.append("' ");
		}else if(payType == 2){//查询他人为我充值信息
			hql.append(" and ap.payer != '");
			hql.append(phonems);
			hql.append("' and ap.phoneOrMs = '");
			hql.append(phonems);
			hql.append("' ");
		}else{//所有我的记录
			hql.append(" and ap.payer = '");
			hql.append(phonems);
			hql.append("' or ap.phoneOrMs = '");
			hql.append(phonems);
			hql.append("' ");
		}
		try {
			Object obj = sess.createQuery(hql.toString()).uniqueResult();
			if (obj != null) {
				int apcount = (Integer) obj;
				return apcount;
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

	@Override
	public Map<String,String> findApCountByTime(Session sess, String msid,
			String startTime, String endTime) {
		StringBuffer hql = new StringBuffer("select count(ap.id),sum(ap.payMoney) from TbAppPayInfo ap where ap.phoneOrMs ='"+msid+"' ");
		hql.append(" and ap.time between '"+startTime+"' and '"+endTime+"'");
		try {
			Object[] obj = (Object[]) sess.createQuery(hql.toString()).uniqueResult();
			if (obj != null) {
				String apcount = String.valueOf(obj[0]);
				String apsum = String.valueOf(obj[1]);
				if(apsum.equals("null")){
					apsum="0";
				}
				Map<String,String> map = new HashMap<String, String>();
				map.put("apcount", apcount);
				map.put("apsum", apsum);
				return map;
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
	public Map<String, String> findKFByTime(Session sess, String msid,
			String startTime, String endTime) {
		//发送的账单信息
		StringBuffer hql = new StringBuffer("select count(if(k.send_or_read=0,true,null)) as totalCount,");
		hql.append("round(IFNULL(sum(k.money*IF(k.send_or_read=0,1,0)),0),2) as totalMoney,");
		hql.append("count(if(k.type=0 AND k.send_or_read=0,true,null)) as msgCount,");
		hql.append("count(if(k.type=0 AND k.send_or_read=0 and k.grp_or_not=0,true,null)) as msgOneCount,");
		hql.append("count(if(k.type=0 AND k.send_or_read=0 and k.grp_or_not=1,true,null)) as msgManyCount,");
		hql.append("round(IFNULL(sum(k.money*IF(k.type=0 AND k.send_or_read=0,1,0)),0),2) AS msgmoney,");
		
		hql.append("count(if(k.type=2 AND k.send_or_read=0,true,null)) as picCount,");
		hql.append("count(if(k.type=2 AND k.send_or_read=0 and k.grp_or_not=0,true,null)) as picOneCount,");
		hql.append("count(if(k.type=2 AND k.send_or_read=0 and k.grp_or_not=1,true,null)) as picManyCount,");
		hql.append("round(IFNULL(sum(k.money*IF(k.type=2 AND k.send_or_read=0,1,0)),0),2) AS picmoney,");
		
		hql.append("count(if(k.type=1 AND k.send_or_read=0,true,null)) as voiCount,");
		hql.append("count(if(k.type=1 AND k.send_or_read=0 and k.grp_or_not=0,true,null)) as voiOneCount,");
		hql.append("count(if(k.type=1 AND k.send_or_read=0 and k.grp_or_not=1,true,null)) as voiManyCount,");
		hql.append("round(IFNULL(sum(k.money*IF(k.type=1 AND k.send_or_read=0,1,0)),0),2) AS voimoney,");
		
		//接收的账单信息
		hql.append("count(if(k.send_or_read=1,true,null)) as rtotalCount,");
		hql.append("round(IFNULL(sum(k.money*IF(k.send_or_read=1,1,0)),0),2) as rtotalMoney,");
		hql.append("count(if(k.type=0 AND k.send_or_read=1,true,null)) as rmsgCount,");
		hql.append("count(if(k.type=0 AND k.send_or_read=1 and k.grp_or_not=0,true,null)) as rmsgOneCount,");
		hql.append("count(if(k.type=0 AND k.send_or_read=1 and k.grp_or_not=1,true,null)) as rmsgManyCount,");
		hql.append("round(IFNULL(sum(k.money*IF(k.type=0 AND k.send_or_read=1,1,0)),0),2) AS rmsgmoney,");
		
		hql.append("count(if(k.type=2 AND k.send_or_read=1,true,null)) as rpicCount,");
		hql.append("count(if(k.type=2 AND k.send_or_read=1 and k.grp_or_not=0,true,null)) as rpicOneCount,");
		hql.append("count(if(k.type=2 AND k.send_or_read=1 and k.grp_or_not=1,true,null)) as rpicManyCount,");
		hql.append("round(IFNULL(sum(k.money*IF(k.type=2 AND k.send_or_read=1,1,0)),0),2) AS rpicmoney,");
		
		hql.append("count(if(k.type=1 AND k.send_or_read=1,true,null)) as rvoiCount,");
		hql.append("count(if(k.type=1 AND k.send_or_read=1 and k.grp_or_not=0,true,null)) as rvoiOneCount,");
		hql.append("count(if(k.type=1 AND k.send_or_read=1 and k.grp_or_not=1,true,null)) as rvoiManyCount,");
		hql.append("round(IFNULL(sum(k.money*IF(k.type=1 AND k.send_or_read=1,1,0)),0),2) AS rvoimoney");
		
		hql.append(" from tb_koufei_log AS k ");
		hql.append(" where k.msid ='"+msid+"'");
		hql.append(" and k.time between '"+startTime+"' and '"+endTime+"'");
		try {
			Object[] obj = (Object[]) sess.createSQLQuery(hql.toString()).uniqueResult();//createQuery(hql.toString())
			if (obj != null) {

				Map<String,String> map = new HashMap<String, String>();
				//发送的账单信息
				map.put("totalCount", String.valueOf(obj[0]));
				map.put("totalMoney", String.valueOf(obj[1]));
				map.put("msgCount", String.valueOf(obj[2]));
				map.put("msgOneCount", String.valueOf(obj[3]));
				map.put("msgManyCount", String.valueOf(obj[4]));
				map.put("msgTotalMoney", String.valueOf(obj[5]));
				
				map.put("picCount", String.valueOf(obj[6]));
				map.put("picOneCount", String.valueOf(obj[7]));
				map.put("picManyCount", String.valueOf(obj[8]));
				map.put("picTotalMoney", String.valueOf(obj[9]));
				
				map.put("voiCount", String.valueOf(obj[10]));
				map.put("voiOneCount", String.valueOf(obj[11]));
				map.put("voiManyCount", String.valueOf(obj[12]));
				map.put("voiTotalMoney", String.valueOf(obj[13]));
				
				//接收的账单信息
				map.put("rtotalCount", String.valueOf(obj[14]));
				map.put("rtotalMoney", String.valueOf(obj[15]));
				map.put("rmsgCount", String.valueOf(obj[16]));
				map.put("rmsgOneCount", String.valueOf(obj[17]));
				map.put("rmsgManyCount", String.valueOf(obj[18]));
				map.put("rmsgTotalMoney", String.valueOf(obj[19]));
				
				map.put("rpicCount", String.valueOf(obj[20]));
				map.put("rpicOneCount", String.valueOf(obj[21]));
				map.put("rpicManyCount", String.valueOf(obj[22]));
				map.put("rpicTotalMoney", String.valueOf(obj[23]));
				
				map.put("rvoiCount", String.valueOf(obj[24]));
				map.put("rvoiOneCount", String.valueOf(obj[25]));
				map.put("rvoiManyCount", String.valueOf(obj[26]));
				map.put("rvoiTotalMoney", String.valueOf(obj[27]));
				
				return map;
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
	public Integer findKFCountByPage(Session sess,String pagentid,String childagentid,String ep,
			String msid, int type,
			String startTime, String endTime,String imsi) {
		String stragent="";// 代理商id
		String strEp="";   //企业id
		int pagent_id = Integer.parseInt(pagentid); // 一级代理商
		int cagent_id = Integer.parseInt(childagentid); // 二级代理商
		
		StringBuffer hql = new StringBuffer("select count(kf.id) from TbKoFeiLogInfo kf,TbMsInfo m where kf.msid=m.msId");
		if(type!=-1){
			hql.append(" and kf.type =" + type);
		}
		
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
		
		if(ep != null && !ep.equals("")){
			if (!(ep.equals("-1"))) { // 用户单位不是全部
				strEp = " and m.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
						+ ep + stragent + ")";
			} else { // 为全部
				strEp = " and m.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
						+ stragent + ")";
			}
		}
		
		hql.append(strEp);
		
		hql.append(" and kf.time between '"+startTime+"' and '"+endTime+"'");
		if(msid!=null && !msid.equals("")){
			hql.append(" and kf.msid like '%" + msid+"%'");
		}
		if(imsi!=null && !imsi.equals("")){
			hql.append(" and kf.imsi like '%" + imsi+"%'");
		}
		try {
			Object obj = (Object) sess.createQuery(hql.toString()).uniqueResult();
			if (obj != null) {
				int count = (Integer)obj;
				return count;
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

	@Override
	public List<TbKoFeiLogInfo> findKFListInfo_sertchByPage(Session sess,int pageNo, int pageSize,
			String pagentid,String childagentid,String ep,
			String msid, int type, String startTime,String endTime,String imsi) {
		
		String stragent="";// 代理商id
		String strEp="";   //企业id
		int pagent_id = Integer.parseInt(pagentid); // 一级代理商
		int cagent_id = Integer.parseInt(childagentid); // 二级代理商
		StringBuffer hql = new StringBuffer("select kf.id,kf.msid,kf.time,kf.type,kf.msgId,kf.money,kf.grpOrNot, kf.imsi from TbKoFeiLogInfo kf,TbMsInfo m where kf.msid=m.msId");
		if(type!=-1){
			hql.append(" and kf.type =" + type);
		}
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
		
		if(ep != null && !ep.equals("")){
			if (!(ep.equals("-1"))) { // 用户单位不是全部
				strEp = " and m.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
						+ ep + stragent + ")";
			} else { // 为全部
				strEp = " and m.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
						+ stragent + ")";
			}
		}
		
		hql.append(strEp);
		
		hql.append(" and kf.time between '"+startTime+"' and '"+endTime+"'");
		if(msid!=null && !msid.equals("")){
			hql.append(" and kf.msid like '%" + msid+"%'");
		}
		if(imsi!=null && !imsi.equals("")){
			hql.append(" and kf.imsi like '%" + imsi+"%'");
		}
		
		hql.append(" order by kf.id desc");
		try {
			int offset = (pageNo - 1) * pageSize;
			ScrollableResults  srs =sess.createQuery(hql.toString()).setFirstResult(offset).setMaxResults(pageSize).scroll();
			List<TbKoFeiLogInfo> kfList=new ArrayList<TbKoFeiLogInfo>();
//			if(kfList.size()<1){
//				return null;
//			}
			while(srs.next()){
				TbKoFeiLogInfo kf = new TbKoFeiLogInfo();
				kf.setId(srs.getInteger(0));
				kf.setMsid(srs.getString(1));
				kf.setTime(srs.getDate(2));
				kf.setType(srs.getInteger(3));
				kf.setMsgId(srs.getInteger(4));
				kf.setMoney(srs.getDouble(5));
				kf.setGrpOrNot(srs.getInteger(6));
				kf.setImsi(srs.getString(7));
				kfList.add(kf);
			}
			if (kfList != null && kfList.size()>0) {
				return kfList;
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