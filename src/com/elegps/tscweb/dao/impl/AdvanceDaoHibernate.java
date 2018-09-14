package com.elegps.tscweb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import com.elegps.tscweb.dao.AdvanceDaoFactory;
import com.elegps.tscweb.dao.AgentDaoFactory;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbORGAdvanceInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.serivce.impl.EnterPriseManagerImpl;

public class AdvanceDaoHibernate implements AdvanceDaoFactory {

	public List findAdvance_SearchByPage(Session sess, int pageNo,
			int pageSize, String pagentid,String childagentid,String ep,String advanceresult) {
		String hql="";
		String stragent="";//代理商
		String strep="";//用户单位
		Connection conn = null; 
		Statement ps = null; 
		ResultSet srs=null;
		int i=0;
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商
		String aresult="";
		if(!advanceresult.equals("")){
			aresult=" and oa.Validate_Result="+advanceresult;
		}
		if(pagent_id==-1){   //说明是总部
			if(cagent_id==-1){    //直属企业(总部直接发展的企业)
				stragent="select oa.Advance_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id and oa.ORG_Id=-1"+aresult;	
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id=-1)"+aresult;
				}else{     //指定企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep+aresult;
				}
			}else{  //-2 所有企业（系统中所有企业）
				stragent="select oa.Advance_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id "+aresult;
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id "+aresult;
				}else{     //指定企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep+aresult;
				}
			}
		}else{  //一级代理商(除总部外)
			if(cagent_id==-1)    //直属企业(指定一级代理商下的企业)
			{
				stragent="select oa.Advance_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id  and oa.ORG_Id="+pagentid+aresult;
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id="+pagentid+")"+aresult;
				}else{     //指定企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep+aresult;
				}
			}else if(cagent_id==-2){   //所有企业 (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业)
				stragent="select oa.Advance_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id  and oa.ORG_Id="+pagentid+" and oa.ORG_Id in(select a.Agent_Id from tb_Agent_Info a where a.Agent_PId="+pagentid+")"+aresult;
				if(ep.equals("-1")){  //全部企业                                                                                                                                                                                                          																																										//or ep.Agent_Id in (select a.Agent_Id from tb_Agent_Info a where a.Agent_PId=8)
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id="+pagentid+" or ep.Agent_Id in (select a.Agent_Id from tb_Agent_Info a where a.Agent_PId="+pagentid+"))"+aresult;
				}else{     //指定企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep+aresult;
				}
			}else{     //指定二级代理商下的所有企业
				stragent="select oa.Advance_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id  and oa.ORG_Id="+childagentid+aresult;
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id="+cagent_id+")"+aresult;
				}else{     //指定企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep+aresult;
				}

			}
		}
		
		hql=stragent+strep;
		System.out.println("hql="+hql);
		
		try {
			conn=sess.connection();
			int offset = (pageNo - 1) * pageSize;
			hql+=" limit "+offset+","+pageSize;
			ps=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			srs=ps.executeQuery(hql); 
//			createSQLQuery
//			createQuery
//			ScrollableResults  srs =sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).scroll();
//			ScrollableResults  srs =sess.createSQLQuery(hql).setFirstResult(offset).setMaxResults(pageSize).scroll();
			List res=new ArrayList();
			while(srs.next()){
				Map map = new HashMap();
				map.put("advanceid",srs.getInt(1));
				map.put("advancetype",srs.getInt(2));
				map.put("advancename", srs.getString(3));
				map.put("advance", srs.getInt(4));
				map.put("advancedate", srs.getObject(5));
				map.put("advanceperson", srs.getString(6));
				map.put("validateperson", srs.getString(7));
				map.put("validatedate", srs.getObject(8));
				map.put("validateresult", srs.getInt(9));
				map.put("remark", srs.getString(10));
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

	public Integer findadvance_Count(Session sess, String pagentid,String childagentid, String ep,String advanceresult) {
		String hql="";
		String stragent="";//代理商
		String strep="";//用户单位
		Connection conn = null; 
		Statement ps = null; 
		ResultSet srs=null;
		int i=0;
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商
		String aresult="";
		if(!advanceresult.equals("")){
			aresult=" and oa.Validate_Result="+advanceresult;
		}
		
		if(pagent_id==-1){   //说明是总部
			if(cagent_id==-1){    //直属企业(总部直接发展的企业)
				stragent="select oa.Advance_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id and oa.ORG_Id=-1"+aresult;	
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id=-1)"+aresult;
				}else{     //指定企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep+aresult;
				}
			}else{  //-2 所有企业（系统中所有企业）
				stragent="select oa.Advance_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id "+aresult;
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id "+aresult;
				}else{     //指定企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep+aresult;
				}
			}
		}else{  //一级代理商(除总部外)
			if(cagent_id==-1)    //直属企业(指定一级代理商下的企业)
			{
				stragent="select oa.Advance_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id  and oa.ORG_Id="+pagentid+aresult;
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id="+pagentid+")"+aresult;
				}else{     //指定企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep+aresult;
				}
			}else if(cagent_id==-2){   //所有企业 (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业)
				stragent="select oa.Advance_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id  and oa.ORG_Id="+pagentid+" and oa.ORG_Id in(select a.Agent_Id from tb_Agent_Info a where a.Agent_PId="+pagentid+")"+aresult;
				if(ep.equals("-1")){  //全部企业                                                                                                                                                                                                          																																										//or ep.Agent_Id in (select a.Agent_Id from tb_Agent_Info a where a.Agent_PId=8)
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id="+pagentid+" or ep.Agent_Id in (select a.Agent_Id from tb_Agent_Info a where a.Agent_PId="+pagentid+"))"+aresult;
				}else{     //指定企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep+aresult;
				}
			}else{     //指定二级代理商下的所有企业
				stragent="select oa.Advance_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id  and oa.ORG_Id="+childagentid+aresult;
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id="+cagent_id+")"+aresult;
				}else{     //指定企业
					strep=" union select oa.Advance_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Advance_Cash,oa.Advance_Date,oa.Advance_Person,oa.Validate_Person,oa.Validate_Date,oa.Validate_Result,oa.Remark from tb_ORGAdvance_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep+aresult;
				}

			}
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

	public String save(Session sess, TbORGAdvanceInfo orgadvance) {
		try {
			sess.save(orgadvance);
			sess.flush();
			return new String("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
		return new String("添加失败");
	}

	public TbORGAdvanceInfo get(Session sess, String advanceid) {
		try {
			List advance = sess.createQuery("from TbORGAdvanceInfo m where m.advanceId="+advanceid).list();
			if (advance != null && advance.size() > 0) {
				return (TbORGAdvanceInfo) advance.get(0);
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

	public void delete(Session sess, String advanceid) {
		try {
			sess.createQuery("delete from TbORGAdvanceInfo o where o.advanceId="+advanceid).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}
	}

	
	/**
	 * 根据条件查询充值历史记录信息
	 */
	public Integer getCharge_Count(Session sess, String pagentid,String childagentid,String ep) {
		String hql="";
		String stragent="";//代理商
		String strep="";//用户单位
		Connection conn = null; 
		Statement ps = null; 
		ResultSet srs=null;
		int i=0;
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商
		if(pagent_id==-1){   //说明是总部
			if(cagent_id==-1){    //直属企业(总部直接发展的企业)
				stragent="select oa.Charge_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id and oa.ORG_Id=-1";	
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id=-1)";
				}else{     //指定企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep;
				}
			}else{  //-2 所有企业（系统中所有企业）
				stragent="select oa.Charge_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id ";
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id ";
				}else{     //指定企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep;
				}
			}
		}else{  //一级代理商(除总部外)
			if(cagent_id==-1)    //直属企业(指定一级代理商下的企业)
			{
				stragent="select oa.Charge_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id  and oa.ORG_Id="+pagentid;
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id="+pagentid+")";
				}else{     //指定企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep;
				}
			}else if(cagent_id==-2){   //所有企业 (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业)
				stragent="select oa.Charge_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id  and oa.ORG_Id="+pagentid+" and oa.ORG_Id in(select a.Agent_Id from tb_Agent_Info a where a.Agent_PId="+pagentid+")";
				if(ep.equals("-1")){  //全部企业                                                                                                                                                                                                          																																										
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id="+pagentid+" or ep.Agent_Id in (select a.Agent_Id from tb_Agent_Info a where a.Agent_PId="+pagentid+"))";
				}else{     //指定企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep;
				}
			}else{     //指定二级代理商下的所有企业
				stragent="select oa.Charge_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id  and oa.ORG_Id="+childagentid;
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id="+cagent_id+")";
				}else{     //指定企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep;
				}

			}
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

	public List getCharge_SearchByPage(Session sess, int pageNo, int pageSize,
			String pagentid,String childagentid,String ep) {
		String hql="";
		String stragent="";//代理商
		String strep="";//用户单位
		Connection conn = null; 
		Statement ps = null; 
		ResultSet srs=null;
		int i=0;
		int pagent_id=Integer.parseInt(pagentid);  //一级代理商
		int cagent_id=Integer.parseInt(childagentid);  //二级代理商
		if(pagent_id==-1){   //说明是总部
			if(cagent_id==-1){    //直属企业(总部直接发展的企业)
				stragent="select oa.Charge_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id and oa.ORG_Id=-1";	
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id=-1)";
				}else{     //指定企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep;
				}
			}else{  //-2 所有企业（系统中所有企业）
				stragent="select oa.Charge_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id ";
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id ";
				}else{     //指定企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep;
				}
			}
		}else{  //一级代理商(除总部外)
			if(cagent_id==-1)    //直属企业(指定一级代理商下的企业)
			{
				stragent="select oa.Charge_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id  and oa.ORG_Id="+pagentid;
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id="+pagentid+")";
				}else{     //指定企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep;
				}
			}else if(cagent_id==-2){   //所有企业 (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业)
				stragent="select oa.Charge_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id  and oa.ORG_Id="+pagentid+" and oa.ORG_Id in(select a.Agent_Id from tb_Agent_Info a where a.Agent_PId="+pagentid+")";
				if(ep.equals("-1")){  //全部企业                                                                                                                                                                                                          																																										
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id="+pagentid+" or ep.Agent_Id in (select a.Agent_Id from tb_Agent_Info a where a.Agent_PId="+pagentid+"))";
				}else{     //指定企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep;
				}
			}else{     //指定二级代理商下的所有企业
				stragent="select oa.Charge_Id,oa.ORG_Type,Ag.Agent_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_Agent_Info Ag where oa.ORG_Type=1 and oa.ORG_Id=Ag.Agent_Id  and oa.ORG_Id="+childagentid;
				if(ep.equals("-1")){  //全部企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id in(select ep.Enterprise_Id from tb_EnterPrise_Info ep where ep.Agent_Id="+cagent_id+")";
				}else{     //指定企业
					strep=" union select oa.Charge_Id,oa.ORG_Type,Ag.Enterprise_Name as name,oa.Charge_Cash,oa.Advance_Person,oa.Validate_Person,oa.Charge_Date,oa.Remark from tb_ORGCharge_Info oa,tb_EnterPrise_Info Ag where oa.ORG_Type=2 and oa.ORG_Id=Ag.Enterprise_Id and oa.ORG_Id="+ep;
				}

			}
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
				map.put("chargeid",srs.getInt(1));
				map.put("chargetype",srs.getInt(2));
				map.put("name", srs.getString(3));
				map.put("chargecash", srs.getInt(4));
				map.put("advanceperson", srs.getString(5));
				map.put("validateperson", srs.getString(6));
				map.put("chargedate", srs.getObject(7));
				map.put("remark", srs.getString(8));
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

	
}
