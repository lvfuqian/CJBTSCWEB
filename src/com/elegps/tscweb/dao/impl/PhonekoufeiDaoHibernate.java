package com.elegps.tscweb.dao.impl;


import com.elegps.tscweb.model.TbPhonekoufeiLog;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import com.elegps.tscweb.dao.PhonekoufeiDaoFactory;
import com.elegps.tscweb.model.TbPhonekoufeiLogVo;

import java.util.ArrayList;
import java.util.List;

public class PhonekoufeiDaoHibernate implements PhonekoufeiDaoFactory{

	@Override
	public TbPhonekoufeiLogVo findPhonekoufeiLog(Session sess, String msid,String startTime,String endTime) {
		//select COUNT(chuan_z_phone_num),SUM(money),(select COUNT(des_phone_num) from tb_phonekoufei_log where des_phone_numin (select chuan_z_phone_num from tb_phonekoufei_log where msid=860311000013226427258))from tb_phonekoufei_log where msid=860311000013226427258";
		try {
			StringBuffer sql = new StringBuffer("select COUNT(chuan_z_phone_num) as dialingNum,IFNULL(SUM(p.money),0) as dialingMoney,");
			sql.append("(select COUNT(des_phone_num) from tb_phonekoufei_log where des_phone_num in ");
			sql.append("(select chuan_z_phone_num from tb_phonekoufei_log where msid="+msid).append(")) as calledNum,");
			sql.append(" IFNULL(SUM(p.call_time),0) as dialingTime,");
			sql.append("(select IFNULL(SUM(call_time),0) from tb_phonekoufei_log where des_phone_num in ");
			sql.append("(select chuan_z_phone_num from tb_phonekoufei_log where msid="+msid).append(")) as calledTime");
			sql.append(" from tb_phonekoufei_log p where msid="+msid);
			sql.append(" and start_time between '"+startTime+"' and '"+endTime+"'");
			Object[] obj = (Object[]) sess.createSQLQuery(sql.toString()).uniqueResult();
			TbPhonekoufeiLogVo pkVo = new TbPhonekoufeiLogVo();
			if(obj!=null){
				pkVo.setDialingNum(String.valueOf(obj[0]));
				pkVo.setDialingMoney(String.valueOf(obj[1]));
				pkVo.setCalledNum(String.valueOf(obj[2]));
				pkVo.setCalledMoney(String.valueOf(0));
				pkVo.setDialingTime(String.valueOf(obj[3]));
				pkVo.setCalledTime(String.valueOf(obj[4]));
			}
			return pkVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Integer findPKFCountByPage(Session sess,String pagentid,String childagentid,String ep,
									 String msid, int type,
									 String startTime, String endTime,String imsi) {
		String stragent="";// ������id
		String strEp="";   //��ҵid
		int pagent_id = Integer.parseInt(pagentid); // һ��������
		int cagent_id = Integer.parseInt(childagentid); // ����������

		StringBuffer hql = new StringBuffer("select count(kf.id) from TbPhonekoufeiLog kf,TbMsInfo m where kf.msid=m.msId");
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

		hql.append(" and kf.startTime between '"+startTime+"' and '"+endTime+"'");
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
	public List<TbPhonekoufeiLog> findPKFListInfo_sertchByPage(Session sess, int pageNo, int pageSize,
															String pagentid, String childagentid, String ep,
															String msid, int type, String startTime, String endTime, String imsi) {

		String stragent="";// ������id
		String strEp="";   //��ҵid
		int pagent_id = Integer.parseInt(pagentid); // һ��������
		int cagent_id = Integer.parseInt(childagentid); // ����������
		StringBuffer hql = new StringBuffer("select kf.id,kf.msid,kf.startTime,kf.type,kf.money, kf.imsi from TbPhonekoufeiLog kf,TbMsInfo m where kf.msid=m.msId");
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

		hql.append(" and kf.startTime between '"+startTime+"' and '"+endTime+"'");
		if(msid!=null && !msid.equals("")){
			hql.append(" and kf.msid like '%" + msid+"%'");
		}
		if(imsi!=null && !imsi.equals("")){
			hql.append(" and kf.imsi like '%" + imsi+"%'");
		}

		hql.append(" order by kf.id desc");
		try {
			int offset = (pageNo - 1) * pageSize;
			ScrollableResults srs =sess.createQuery(hql.toString()).setFirstResult(offset).setMaxResults(pageSize).scroll();
			List<TbPhonekoufeiLog> kfList=new ArrayList<TbPhonekoufeiLog>();
//			if(kfList.size()<1){
//				return null;
//			}
			while(srs.next()){
				TbPhonekoufeiLog kf = new TbPhonekoufeiLog();
				kf.setId(srs.getInteger(0));
				kf.setMsid(srs.getString(1));
				kf.setStartTime(srs.getDate(2));
				kf.setType(srs.getInteger(3));
				kf.setMoney(srs.getInteger(4));
				kf.setImsi(srs.getString(5));
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
