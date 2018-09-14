package com.elegps.tscweb.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import com.elegps.tscweb.dao.MsChargeDaoFactory;
import com.elegps.tscweb.model.TbEpChargeInfo;
import com.elegps.tscweb.model.TbMsChargeInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbORGBalanceInfo;

public class MsChargeDaoHibernate implements MsChargeDaoFactory {

	public Integer getMsChareg_sertchCount(Session sess, String ms_id,
			String ms_name, String pagentid,String childagentid, String ep) {
		String strMsId = ""; // 终端id
		String strMSName = ""; // 终端名称
		String stragent="";// 代理商id
		String strEp="";   //企业id
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商

		String hql = "select count(M.msId) from TbMsInfo M where 1=1";
		
		if (ms_id.trim().length() > 0) // 终端名非空,并长度大于0
			strMsId = " and M.msId like '%" + ms_id + "%' ";

		if (ms_name.trim().length() > 0) // 终端名非空,并长度大于0
			strMSName = " and M.msName like '%" + ms_name + "%' ";

		if(pagent_id==-1){   //说明是总部
			if(cagent_id==-1){    //直属企业(总部直接发展的企业)
				stragent=" and  E.Agent_Id ="+pagentid;
			}else{              //-2 所有企业（系统中所有企业）
				
			}
		}else{  //一级代理商(除总部外)
			if(cagent_id==-1)    //直属企业(指定一级代理商下的企业)
			{
				stragent=" and E.Agent_Id="+pagentid;
			}else if(cagent_id==-2){   //所有企业 (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业
				stragent=" and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+pagentid+" or A.Agent_PId="+pagentid+")";
			}else{     //指定二级代理商下的所有企业
				stragent=" and E.Agent_Id="+childagentid;
			}
		}
		
		if (!(ep.equals("-1"))) { // 用户单位不是全部
			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // 为全部
			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
					+ stragent + ")";
		}
		hql += strMsId + strMSName + strEp;
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
				// sess.close();
			}
		}
		return null;
	}

	public List getdMsCharge_sertchByPage(Session sess, int pageNo,
			int pageSize, String ms_id, String ms_name, String pagentid,String childagentid, String ep) {
		String strMsId = ""; // 终端id
		String strMSName = ""; // 终端名称
		String stragent="";// 代理商id
		String strEp="";   //企业id
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商

		String hql = "from TbMsInfo M where 1=1";
		
		if (ms_id.trim().length() > 0) // 终端名非空,并长度大于0
			strMsId = " and M.msId like '%" + ms_id + "%' ";

		if (ms_name.trim().length() > 0) // 终端名非空,并长度大于0
			strMSName = " and M.msName like '%" + ms_name + "%' ";

		if(pagent_id==-1){   //说明是总部
			if(cagent_id==-1){    //直属企业(总部直接发展的企业)
				stragent=" and  E.Agent_Id ="+pagentid;
			}else{              //-2 所有企业（系统中所有企业）
				
			}
		}else{  //一级代理商(除总部外)
			if(cagent_id==-1)    //直属企业(指定一级代理商下的企业)
			{
				stragent=" and E.Agent_Id="+pagentid;
			}else if(cagent_id==-2){   //所有企业 (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业
				stragent=" and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+pagentid+" or A.Agent_PId="+pagentid+")";
			}else{     //指定二级代理商下的所有企业
				stragent=" and E.Agent_Id="+childagentid;
			}
		}
		
		if (!(ep.equals("-1"))) { // 用户单位不是全部
			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where  E.Ep_Id="
					+ ep + stragent + ")";
		} else { // 为全部
			strEp = " and M.epid in(select E.Ep_Id from TbEnterpriseInfo E where 1=1 "
					+ stragent + ")";
		}
		hql +=strMsId + strMSName + strEp+ " order by M.msId desc";
		try {
			int offset = (pageNo - 1) * pageSize;
			List list = sess.createQuery(hql).setFirstResult(offset)
					.setMaxResults(pageSize).list();
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

	public List getBalanceinfo_byagentid(Session sess, String agent_id) {
		String hql = "from TbAgentInfo a where a.Agent_Id in(select  b.orgId from TbORGBalanceInfo b where b.orgType=1 ";  
		if(agent_id.equals("")){//所有代理商
			
		}else{
			hql=hql+" and b.orgId="+agent_id;
		}
		hql+=")";
		try {
			List list = sess.createQuery(hql).list();
			if(list.size()>0){
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

	public List getBalanceInfo_byepid(Session sess, String ep_id) {
		String hql = "from TbEnterpriseInfo e where e.Ep_Id in(select b.orgId from TbORGBalanceInfo b where b.orgType=2 ";
		if(ep_id.equals("")){//所有企业
			
		}else{
			hql=hql+" and b.orgId="+ep_id;
		}
		hql+=")";
		try {
			List list = sess.createQuery(hql).list();
			if(list.size()>0){
				return list;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return null;
	}

	public Integer getAgentBalancedao(Session sess, String agent_id) {
		String hql = "from TbORGBalanceInfo b where b.orgType=1 and  b.orgId="+agent_id;
		try {
			List list = sess.createQuery(hql).list();
			if(list.size()>0){
				TbORGBalanceInfo banceinfo=(TbORGBalanceInfo)list.get(0);
				return banceinfo.getBalanceCash();
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return 0;
	}

	public Integer getEpBalancedao(Session sess, String ep_id) {
		String hql = "from TbORGBalanceInfo b where b.orgType=2 and  b.orgId="+ep_id;
		try {
			List list = sess.createQuery(hql).list();
			if(list.size()>0){
				TbORGBalanceInfo banceinfo=(TbORGBalanceInfo)list.get(0);
				return banceinfo.getBalanceCash();
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return 0;
	}

	public String getMsCharge_byagentiddao(Session sess, String changeagentid,
			String ep_id, String charge, String[] ms_id) {
		DeductDaoHibernate deduct=new DeductDaoHibernate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String deductdate=dateFormat.format(new Date());
		sess.beginTransaction();
		try {
			String hql = "from TbORGBalanceInfo b where b.orgType=1 and  b.orgId="+changeagentid;
			List list = sess.createQuery(hql).list();
			if(list.size()>0){
				TbORGBalanceInfo banceinfo=(TbORGBalanceInfo)list.get(0);
				if(banceinfo.getBalanceCash()>Integer.parseInt(charge)){  //说明被扣代理商id的金额大于充值金额可以充值
					banceinfo.setBalanceCash(banceinfo.getBalanceCash()-Integer.parseInt(charge));
					sess.update(banceinfo);  //更新被扣代理商的余额
					
					
					//更新被充值的终端余额
					BigDecimal je= new BigDecimal(String.valueOf((float)Integer.parseInt(charge)/(ms_id.length))).setScale(0, BigDecimal.ROUND_HALF_UP);  //每个终端的充值数
					TbMsInfo msinfo=new TbMsInfo();
					for(int i=0;i<ms_id.length;i++) {
						String emp = ms_id[i];
						List ms = sess.createQuery("from TbMsInfo m where m.msId='"+emp+"'").list();
				        if(ms!=null){
				        	msinfo=(TbMsInfo)ms.get(0);
				        	msinfo.setBalance_cash(msinfo.getBalance_cash()+je.intValue());
				        	if(msinfo.getCharge_date()==null||msinfo.getCharge_date().equals("")){  //没有充过值或充值已过期
				        	  msinfo.setCharge_date(deductdate);
				        	}
				        	sess.update(msinfo);
				        }else{
				        	sess.beginTransaction().rollback();
				        	return new String("终端充值失败!");
				        }
				        
					}
					
					
					//终端充值历史记录
					for(int i=0;i<ms_id.length;i++) {
						String emp = ms_id[i];
						TbMsChargeInfo mschargeinfo=new TbMsChargeInfo();
			        	mschargeinfo.setMsId(emp);
				        mschargeinfo.setEpId(Integer.parseInt(ep_id));
				        mschargeinfo.setChargeCash(je.intValue());
				        mschargeinfo.setChargeDate(new Date());
				        sess.save(mschargeinfo);
					}

					//企业充值历史记录
					TbEpChargeInfo epcharge=new TbEpChargeInfo();
					epcharge.setOrgId(Integer.parseInt(changeagentid));
					epcharge.setOrgType(1);
					epcharge.setEpId(Integer.parseInt(ep_id));
					epcharge.setDeductCash(Integer.parseInt(charge));
					epcharge.setDeductDate(new Date());
					sess.save(epcharge);
				}
			}
	     deduct.excuteDeduct(sess);   //启动扣费模块
		 sess.beginTransaction().commit();
         return new String("终端充值成功!");
		}catch (Exception e) {
			sess.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return new String("充值失败!");
	}

	public String getMsCharge_byepiddao(Session sess, String changeepid,
			String ep_id, String charge, String[] ms_id) {
		DeductDaoHibernate deduct=new DeductDaoHibernate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String deductdate=dateFormat.format(new Date());
		sess.beginTransaction();
		try {
			String hql = "from TbORGBalanceInfo b where b.orgType=2 and  b.orgId="+changeepid;
			List list = sess.createQuery(hql).list();
			if(list.size()>0){
				TbORGBalanceInfo banceinfo=(TbORGBalanceInfo)list.get(0);
				if(banceinfo.getBalanceCash()>Integer.parseInt(charge)){  //说明被扣代理商id的金额大于充值金额可以充值
					banceinfo.setBalanceCash(banceinfo.getBalanceCash()-Integer.parseInt(charge));
					sess.update(banceinfo);  //更新被扣代理商的余额
					
			
					//更新被充值的终端余额
					BigDecimal je= new BigDecimal(String.valueOf((float)Integer.parseInt(charge)/(ms_id.length))).setScale(0, BigDecimal.ROUND_HALF_UP);  //每个终端的充值数
					TbMsInfo msinfo=new TbMsInfo();
					for(int i=0;i<ms_id.length;i++) {
						String emp = ms_id[i];
						List ms = sess.createQuery("from TbMsInfo m where m.msId='"+emp+"'").list();
				        if(ms!=null){
				        	msinfo=(TbMsInfo)ms.get(0);
				        	msinfo.setBalance_cash(msinfo.getBalance_cash()+je.intValue());
				        	if(msinfo.getCharge_date()==null||msinfo.getCharge_date().equals("")){  //没有充过值或充值已过期
					        	  msinfo.setCharge_date(deductdate);
					        	}
				        	sess.update(msinfo);
				        }else{
				        	sess.beginTransaction().rollback();
				        	return new String("终端充值失败!");
				        }
					}

					
					//终端充值历史记录
					for(int i=0;i<ms_id.length;i++) {
						String emp = ms_id[i];
						TbMsChargeInfo mschargeinfo=new TbMsChargeInfo();
			        	mschargeinfo.setMsId(emp);
				        mschargeinfo.setEpId(Integer.parseInt(ep_id));
				        mschargeinfo.setChargeCash(je.intValue());
				        mschargeinfo.setChargeDate(new Date());
				        sess.save(mschargeinfo);
					}
					
					//企业充值历史记录
					TbEpChargeInfo epcharge=new TbEpChargeInfo();
					epcharge.setOrgId(Integer.parseInt(changeepid));
					epcharge.setOrgType(2);
					epcharge.setEpId(Integer.parseInt(ep_id));
					epcharge.setDeductCash(Integer.parseInt(charge));
					epcharge.setDeductDate(new Date());
					sess.save(epcharge);
				}
			}
		 deduct.excuteDeduct(sess);   //启动扣费模块
		 sess.beginTransaction().commit();
         return new String("终端充值成功!");
		}catch (Exception e) {
			sess.beginTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return new String("充值失败!");
	}

	
	/**
	 * 企业充值历史记录个数
	 */
	public Integer getEpCharge_Count(Session sess, String agent, String ep) {
		String hql="";
		String stragent="";//代理商
		String strep="";//用户单位
		Connection conn = null; 
		Statement ps = null; 
		ResultSet srs=null;
		int i=0;
		if(!agent.equals("")){//代理商不是全部
			stragent="select epch.ORG_Type,epch.Deduct_Date,epch.Deduct_Cash,Ag.Agent_Name,ep.Enterprise_Name  from tb_EPCharge_Info epch,tb_Agent_Info Ag,tb_EnterPrise_Info ep where epch.ORG_Type=1  and epch.ORG_Id=Ag.Agent_Id and epch.EP_Id=ep.Enterprise_Id and epch.ORG_Id="+agent;
		}else{
			stragent="select epch.ORG_Type,epch.Deduct_Date,epch.Deduct_Cash,Ag.Agent_Name,ep.Enterprise_Name  from tb_EPCharge_Info epch,tb_Agent_Info Ag,tb_EnterPrise_Info ep where epch.ORG_Type=1  and epch.ORG_Id=Ag.Agent_Id and epch.EP_Id=ep.Enterprise_Id ";
		}
		if(!ep.equals("")){
				 strep=" union  select epch.ORG_Type,epch.Deduct_Date,epch.Deduct_Cash,ep.Enterprise_Name,ep.Enterprise_Name from tb_EPCharge_Info epch,tb_EnterPrise_Info ep where epch.ORG_Type=2  and epch.EP_Id=ep.Enterprise_Id and epch.ORG_Id="+ep;
		}else{
				 strep=" union select epch.ORG_Type,epch.Deduct_Date,epch.Deduct_Cash,ep.Enterprise_Name,ep.Enterprise_Name from tb_EPCharge_Info epch,tb_EnterPrise_Info ep where epch.ORG_Type=2  and epch.EP_Id=ep.Enterprise_Id";
		}
		
		hql=stragent+strep;	
		try {
			conn=sess.connection();
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			srs=ps.executeQuery(hql); 
			while(srs.next()) {
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return i;
	}

	public List getEpCharge_SearchByPage(Session sess, int pageNo,
			int pageSize, String agent, String ep) {
		String hql="";
		String stragent="";//代理商
		String strep="";//用户单位
		Connection conn = null; 
		Statement ps = null; 
		ResultSet srs=null;
		int i=0;
		if(!agent.equals("")){//代理商不是全部
			stragent="select epch.ORG_Type,epch.Deduct_Date,epch.Deduct_Cash,Ag.Agent_Name,ep.Enterprise_Name  from tb_EPCharge_Info epch,tb_Agent_Info Ag,tb_EnterPrise_Info ep where epch.ORG_Type=1  and epch.ORG_Id=Ag.Agent_Id and epch.EP_Id=ep.Enterprise_Id and epch.ORG_Id="+agent;
		}else{
			stragent="select epch.ORG_Type,epch.Deduct_Date,epch.Deduct_Cash,Ag.Agent_Name,ep.Enterprise_Name  from tb_EPCharge_Info epch,tb_Agent_Info Ag,tb_EnterPrise_Info ep where epch.ORG_Type=1  and epch.ORG_Id=Ag.Agent_Id and epch.EP_Id=ep.Enterprise_Id ";
		}
		if(!ep.equals("")){
				 strep=" union  select epch.ORG_Type,epch.Deduct_Date,epch.Deduct_Cash,ep.Enterprise_Name,ep.Enterprise_Name as name from tb_EPCharge_Info epch,tb_EnterPrise_Info ep where epch.ORG_Type=2  and epch.EP_Id=ep.Enterprise_Id and epch.ORG_Id="+ep;
		}else{
				 strep=" union select epch.ORG_Type,epch.Deduct_Date,epch.Deduct_Cash,ep.Enterprise_Name,ep.Enterprise_Name as name from tb_EPCharge_Info epch,tb_EnterPrise_Info ep where epch.ORG_Type=2  and epch.EP_Id=ep.Enterprise_Id";
		}
		hql=stragent+strep;	
		try {
			conn=sess.connection();
			int offset = (pageNo - 1) * pageSize;
			hql+=" limit "+offset+","+pageSize;
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			srs=ps.executeQuery(hql); 	
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("orgtype",srs.getInt(1));
				map.put("chargedate",srs.getObject(2));
				System.out.println(srs.getInt(3));
				map.put("epcash", srs.getInt(3));
				map.put("orgname", srs.getString(4));
				map.put("epname", srs.getString(5));
				res.add(map);
			}
			if (res != null && res.size()>0) {
				return res;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	public Integer getMsCharge_Count(Session sess, String agent, String ep,String ms_id,String ms_name) {
		int i=0;  
		String hql="";
		String stragent="";
		String strep="";
		String strmsid="";
		String strmsname="";
		if(!ms_id.equals("")){
			strmsid = " and ms.msId like '%" + ms_id + "%' ";
		}
		if(!ms_name.equals("")){
			strmsname = " and ms.msName like '%" + ms_name + "%' ";
		}
		if(!agent.equals("")){//代理商不是全部
			stragent="( select ag.Agent_Id  from TbAgentInfo ag where ag.Agent_Id="+agent+" or ag.Agent_PId="+agent+")";
		}
		if(!ep.equals("")){  //企业不是全部
			if(stragent.length()>1){  //不是全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msch.chargeCash,msch.chargeDate from TbMsChargeInfo msch,TbEnterpriseInfo ep,TbMsInfo ms where msch.msId=ms.msId and msch.epId=ep.Ep_Id and ep.Ep_Id="+ep+" and ep.Agent_Id ="+stragent+strmsid+strmsname;
			}else{ //全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msch.chargeCash,msch.chargeDate from TbMsChargeInfo msch,TbEnterpriseInfo ep,TbMsInfo ms where msch.msId=ms.msId and msch.epId=ep.Ep_Id and ep.Ep_Id="+ep+strmsid+strmsname;
			}
		}else{ //全部企业
			if(stragent.length()>1){  //不是全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msch.chargeCash,msch.chargeDate from TbMsChargeInfo msch,TbEnterpriseInfo ep,TbMsInfo ms where msch.msId=ms.msId and msch.epId=ep.Ep_Id and ep.Agent_Id ="+stragent+strmsid+strmsname;
			}else{ //全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msch.chargeCash,msch.chargeDate from TbMsChargeInfo msch,TbEnterpriseInfo ep,TbMsInfo ms where msch.msId=ms.msId and msch.epId=ep.Ep_Id "+strmsid+strmsname;
			}
		}
		hql=strep+" order by ms.msId";
		try {
			ScrollableResults srs = sess.createQuery(hql).scroll();
			while(srs.next()){
				i++;
            }
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	public List getMsCharge_SearchByPage(Session sess, int pageNo,
			int pageSize, String agent, String ep,String ms_id,String ms_name) {
		String hql="";
		String stragent="";
		String strep="";
		String strmsid="";
		String strmsname="";
		if(!ms_id.equals("")){
			strmsid = " and ms.msId like '%" + ms_id + "%' ";
		}
		if(!ms_name.equals("")){
			strmsname = " and ms.msName like '%" + ms_name + "%' ";
		}
		if(!agent.equals("")){//代理商不是全部
			stragent="( select ag.Agent_Id  from TbAgentInfo ag where ag.Agent_Id="+agent+" or ag.Agent_PId="+agent+")";
		}
		if(!ep.equals("")){  //企业不是全部
			if(stragent.length()>1){  //不是全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msch.chargeCash,msch.chargeDate from TbMsChargeInfo msch,TbEnterpriseInfo ep,TbMsInfo ms where msch.msId=ms.msId and msch.epId=ep.Ep_Id and ep.Ep_Id="+ep+" and ep.Agent_Id ="+stragent+strmsid+strmsname;
			}else{ //全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msch.chargeCash,msch.chargeDate from TbMsChargeInfo msch,TbEnterpriseInfo ep,TbMsInfo ms where msch.msId=ms.msId and msch.epId=ep.Ep_Id and ep.Ep_Id="+ep+strmsid+strmsname;
			}
		}else{ //全部企业
			if(stragent.length()>1){  //不是全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msch.chargeCash,msch.chargeDate from TbMsChargeInfo msch,TbEnterpriseInfo ep,TbMsInfo ms where msch.msId=ms.msId and msch.epId=ep.Ep_Id and ep.Agent_Id ="+stragent+strmsid+strmsname;
			}else{ //全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msch.chargeCash,msch.chargeDate from TbMsChargeInfo msch,TbEnterpriseInfo ep,TbMsInfo ms where msch.msId=ms.msId and msch.epId=ep.Ep_Id "+strmsid+strmsname;
			}stragent="( select ag.Agent_Id  from TbAgentInfo ag where ag.Agent_Id="+agent+" or ag.Agent_PId="+agent+")";
		}
		hql=strep+" order by ms.msId";
		try {
			int offset = (pageNo - 1) * pageSize;
			ScrollableResults srs = sess.createQuery(hql).setFirstResult(offset)
					.setMaxResults(pageSize).scroll();
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("epname",srs.getString(0));
				map.put("msid", srs.getString(1));
				map.put("msname", srs.getString(2));
				map.put("chargecash", srs.getInteger(3));
				map.put("chargedate", srs.getDate(4));
                res.add(map);
            }
			if (res != null && res.size()>0) {
				return res;
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

	
	/**
	 * 终端扣费历史记录
	 */
	public Integer getMsDeduct_Count(Session sess, String agent, String ep,String ms_id,String ms_name) {
		int i=0;  
		String hql="";		
		String stragent="";
		String strep="";
		String strmsid="";
		String strmsname="";
		if(!ms_id.equals("")){
			strmsid = " and ms.msId like '%" + ms_id + "%' ";
		}
		if(!ms_name.equals("")){
			strmsname = " and ms.msName like '%" + ms_name + "%' ";
		}
		
		if(!agent.equals("")){//代理商不是全部
			stragent="( select ag.Agent_Id  from TbAgentInfo ag where ag.Agent_Id="+agent+" or ag.Agent_PId="+agent+")";
		}
		if(!ep.equals("")){  //企业不是全部
			if(stragent.length()>1){  //不是全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msde.deductcash,msde.deductdate from TbMSDeductInfo msde,TbEnterpriseInfo ep,TbMsInfo ms where msde.msId=ms.msId and msde.epId=ep.Ep_Id and ep.Ep_Id="+ep+" and ep.Agent_Id ="+stragent+strmsid+strmsname;
			}else{ //全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msde.deductcash,msde.deductdate from TbMSDeductInfo msde,TbEnterpriseInfo ep,TbMsInfo ms where msde.msId=ms.msId and msde.epId=ep.Ep_Id and ep.Ep_Id="+ep+strmsid+strmsname;
			}
		}else{ //全部企业
			if(stragent.length()>1){  //不是全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msde.deductcash,msde.deductdate from TbMSDeductInfo msde,TbEnterpriseInfo ep,TbMsInfo ms where msde.msId=ms.msId and msde.epId=ep.Ep_Id and ep.Agent_Id ="+stragent+strmsid+strmsname;
			}else{ //全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msde.deductcash,msde.deductdate from TbMSDeductInfo msde,TbEnterpriseInfo ep,TbMsInfo ms where msde.msId=ms.msId and msde.epId=ep.Ep_Id"+strmsid+strmsname;
			}
		}
		hql=strep+" order by ms.msId";
		try {
			ScrollableResults srs = sess.createQuery(hql).scroll();
			while(srs.next()){
				i++;
            }
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
				// sess.close();
			}
		}
		return null;
	}

	public List getMsDeduct_SearchByPage(Session sess, int pageNo,
			int pageSize, String agent, String ep,String ms_id,String ms_name) {
		String hql="";
		String stragent="";
		String strep="";
		String strmsid="";
		String strmsname="";
		if(!ms_id.equals("")){
			strmsid = " and ms.msId like '%" + ms_id + "%' ";
		}
		if(!ms_name.equals("")){
			strmsname = " and ms.msName like '%" + ms_name + "%' ";
		}
		if(!agent.equals("")){//代理商不是全部
			stragent="( select ag.Agent_Id  from TbAgentInfo ag where ag.Agent_Id="+agent+" or ag.Agent_PId="+agent+")";
		}
		if(!ep.equals("")){  //企业不是全部
			if(stragent.length()>1){  //不是全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msde.deductcash,msde.deductdate from TbMSDeductInfo msde,TbEnterpriseInfo ep,TbMsInfo ms where msde.msId=ms.msId and msde.epId=ep.Ep_Id and ep.Ep_Id="+ep+" and ep.Agent_Id ="+stragent+strmsid+strmsname;
			}else{ //全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msde.deductcash,msde.deductdate from TbMSDeductInfo msde,TbEnterpriseInfo ep,TbMsInfo ms where msde.msId=ms.msId and msde.epId=ep.Ep_Id and ep.Ep_Id="+ep+strmsid+strmsname;
			}
		}else{ //全部企业
			if(stragent.length()>1){  //不是全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msde.deductcash,msde.deductdate from TbMSDeductInfo msde,TbEnterpriseInfo ep,TbMsInfo ms where msde.msId=ms.msId and msde.epId=ep.Ep_Id and ep.Agent_Id ="+stragent+strmsid+strmsname;
			}else{ //全部代理商
				strep="select ep.Ep_Name,ms.msId,ms.msName,msde.deductcash,msde.deductdate from TbMSDeductInfo msde,TbEnterpriseInfo ep,TbMsInfo ms where msde.msId=ms.msId and msde.epId=ep.Ep_Id"+strmsid+strmsname;
			}
		}
		hql=strep+" order by ms.msId";
		try {
			int offset = (pageNo - 1) * pageSize;
			ScrollableResults srs = sess.createQuery(hql).setFirstResult(offset)
					.setMaxResults(pageSize).scroll();
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("epname",srs.getString(0));
				map.put("msid", srs.getString(1));
				map.put("msname", srs.getString(2));
				map.put("deductcash", srs.getInteger(3));
				map.put("deductdate", srs.getDate(4));
                res.add(map);
            }
			if (res != null && res.size()>0) {
				return res;
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

	
}
