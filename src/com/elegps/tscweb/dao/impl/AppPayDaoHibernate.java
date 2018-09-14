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
		if(payType == 0){	//��ѯ�Լ���ֵ��Ϣ
			hql.append(" and ap.payer = '");
			hql.append(phonems);
			hql.append("' and ap.phoneOrMs = '");
			hql.append(phonems);
			hql.append("' ");
		}else if(payType == 1){//��ѯΪ�����ų�ֵ��Ϣ
			hql.append(" and ap.payer = '");
			hql.append(phonems);
			hql.append("' and ap.phoneOrMs != '");
			hql.append(phonems);
			hql.append("' ");
		}else if(payType == 2){//��ѯ����Ϊ�ҳ�ֵ��Ϣ
			hql.append(" and ap.payer != '");
			hql.append(phonems);
			hql.append("' and ap.phoneOrMs = '");
			hql.append(phonems);
			hql.append("'");
		}else{//�����ҵļ�¼
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
		if(payType == 0){	//��ѯ�Լ���ֵ��Ϣ
			hql.append(" and ap.payer = '");
			hql.append(phonems);
			hql.append("' and ap.phoneOrMs = '");
			hql.append(phonems);
			hql.append("' ");
		}else if(payType == 1){//��ѯΪ�����ų�ֵ��Ϣ
			hql.append(" and ap.payer = '");
			hql.append(phonems);
			hql.append("' and ap.phoneOrMs != '");
			hql.append(phonems);
			hql.append("' ");
		}else if(payType == 2){//��ѯ����Ϊ�ҳ�ֵ��Ϣ
			hql.append(" and ap.payer != '");
			hql.append(phonems);
			hql.append("' and ap.phoneOrMs = '");
			hql.append(phonems);
			hql.append("' ");
		}else{//�����ҵļ�¼
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
		//���͵��˵���Ϣ
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
		
		//���յ��˵���Ϣ
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
				//���͵��˵���Ϣ
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
				
				//���յ��˵���Ϣ
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
		String stragent="";// ������id
		String strEp="";   //��ҵid
		int pagent_id = Integer.parseInt(pagentid); // һ��������
		int cagent_id = Integer.parseInt(childagentid); // ����������
		
		StringBuffer hql = new StringBuffer("select count(kf.id) from TbKoFeiLogInfo kf,TbMsInfo m where kf.msid=m.msId");
		if(type!=-1){
			hql.append(" and kf.type =" + type);
		}
		
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
		
		if(ep != null && !ep.equals("")){
			if (!(ep.equals("-1"))) { // �û���λ����ȫ��
				strEp = " and m.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
						+ ep + stragent + ")";
			} else { // Ϊȫ��
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
		
		String stragent="";// ������id
		String strEp="";   //��ҵid
		int pagent_id = Integer.parseInt(pagentid); // һ��������
		int cagent_id = Integer.parseInt(childagentid); // ����������
		StringBuffer hql = new StringBuffer("select kf.id,kf.msid,kf.time,kf.type,kf.msgId,kf.money,kf.grpOrNot, kf.imsi from TbKoFeiLogInfo kf,TbMsInfo m where kf.msid=m.msId");
		if(type!=-1){
			hql.append(" and kf.type =" + type);
		}
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
		
		if(ep != null && !ep.equals("")){
			if (!(ep.equals("-1"))) { // �û���λ����ȫ��
				strEp = " and m.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
						+ ep + stragent + ")";
			} else { // Ϊȫ��
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