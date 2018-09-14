package com.elegps.tscweb.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.elegps.tscweb.dao.EnterPriseDaoFactory;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;


public class EnterPriseDaoHibernate  implements  EnterPriseDaoFactory
{

	/**
	 * 返回总记录数
	 */
	public Integer findEp_sertchCount(Session sess, String agent_id,String childagentid,
			String ep_name, int ep_id,int r_id) {
		String hql="select count(E.Ep_Id) from TbEnterpriseInfo E where 1=1";
		
		String stragent="";
		String epname="";
		int pagent_id=Integer.parseInt(agent_id);
		int cagent_id=Integer.parseInt(childagentid);
		//企业用户查询（id=3）
		if(r_id == 3 || r_id ==4){
			hql +=" and E.Ep_Id =" + ep_id;
		}else{
		if(pagent_id==-1){   //说明是总部
			if(cagent_id==-1){    //直属企业(总部直接发展的企业)
				stragent=" and  E.Agent_Id ="+agent_id;
			}else{              //-2 所有企业（系统中所有企业）
				
			}
		}else{  //一级代理商(除总部外)
			if(cagent_id==-1)    //直属企业(指定一级代理商下的企业)
			{
				stragent=" and E.Agent_Id="+agent_id;
			}else if(cagent_id==-2){   //所有企业 (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业
				stragent=" and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+agent_id+" or A.Agent_PId="+agent_id+")";
			}else{     //指定二级代理商下的所有企业
				stragent=" and E.Agent_Id="+childagentid;
			}
		}
		}
		  
		if(ep_name.trim().length()>0) //模糊查询
			epname=" and E.Ep_Name like '%"+ep_name+"%'";
		hql+=stragent+epname;
		
		try {
			Object obj = sess.createQuery(hql)
					.uniqueResult();
			if (obj!= null) {
				int mscount = (Integer) obj;
				return mscount;
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
	 * 查询用户信息
	 */
	public List getEpInfo(Session sess, int pageNo, int pageSize,
			String agent_id,String childagentid, String ep_name, int ep_id,int r_id) {
		int offset = (pageNo - 1) * pageSize;
		String hql="from TbEnterpriseInfo E where 1=1 ";
		
		
		String stragent="";
		String epname="";
		int pagent_id=Integer.parseInt(agent_id);
		int cagent_id=Integer.parseInt(childagentid);
		
		//企业用户查询（id=3）
		if(r_id == 3 || r_id == 4){
			hql +=" and E.Ep_Id =" + ep_id;
//		}else if(r_id == 2){
//			hql +=" and E.Agent_Id =" + ep_id;
		}else{
			
		if(pagent_id==-1){   //说明是总部
			if(cagent_id==-1){    //直属企业(总部直接发展的企业)
				stragent=" and  E.Agent_Id ="+agent_id;
			}else{              //-2 所有企业（系统中所有企业）
				
			}
		}else{  //一级代理商(除总部外)
			if(cagent_id==-1)    //直属企业(指定一级代理商下的企业)
			{
				stragent=" and E.Agent_Id="+agent_id;
			}else if(cagent_id==-2){   //所有企业 (指定一级代理商下的企业和这个一级代理商下所有二级代理商下的的企业
				stragent=" and E.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+agent_id+" or A.Agent_PId="+agent_id+")";
			}else{     //指定二级代理商下的所有企业
				stragent=" and E.Agent_Id="+childagentid;
			}
		}
		}
		if(ep_name.trim().length()>0){ //模糊查询
			epname=" and E.Ep_Name like '%"+ep_name+"%'";
			hql+=stragent+epname+" order by E.Ep_Name";
		}else{
			hql+=stragent+" order by E.Ep_Name";
		}
		
		try {
			List list=sess.createQuery(hql).setFirstResult(offset)
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
		return null;
	}

//	public List getAgentInfo(Session sess, String agent_id) {
//		String hql="from TbAgentInfo ";
//		if(agent_id.equals("")){//所有代理商
//			
//		}else{
//			hql=hql+"where Agent_Id="+agent_id+" or Agent_PId="+agent_id;
//		}
//		try {
//			List list=sess.createQuery(hql).list();
//			if(list!=null){
//				return list ;
//		   }
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (sess != null) {
////				sess.close();
//			}
//		}
//		return null;
//	}

	public TbEnterpriseInfo get(Session sess, String epId) {
		try {
			List ep = sess.createQuery("from TbEnterpriseInfo a where a.Ep_Id="+epId).list();
			if (ep != null && ep.size() > 0) {
				return (TbEnterpriseInfo) ep.get(0);
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

	public String save(Session sess, TbEnterpriseInfo m) {
		try {
			sess.save(m);
			sess.flush();
			return m.getEp_Id().toString();
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
	 * 查询指定代理商下的所有企业   
	 * @param sess
	 * @param agentid
	 * @return
	 */
	public List getEpInfo_byagentid(Session sess,String pagentid,String childagentid,int a_id,int r_id) {
		String hql="from TbEnterpriseInfo E where 1=1";
		String stragent="";
		int pagent_id=Integer.parseInt(pagentid);
		int cagent_id=Integer.parseInt(childagentid);
		
		if(r_id == 3){//wanglei 企业用户
			hql+= " and E.Agent_Id =" + a_id;
		}else{
			if(pagent_id==-1){   //说明是总部
//				if(cagent_id==-1){    //直属企业(总部直接发展的企业)
					stragent=" and  E.Agent_Id ="+pagentid;
//				}else{              //-2 所有企业（系统中所有企业）
				
//				}
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
		}
			hql+=stragent+" order by E.Ep_Name";
		try {
			List listepinfo = sess.createQuery(hql).list();
			if (listepinfo.size()>0) {
				return listepinfo;
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
	 * 查询指定代理商下的指定企业   
	 * @param sess
	 * @param agentid
	 * @return
	 */
	public List getEpinfo_byeid(Session sess,String pagentid,String childagentid,int ep_id,int r_id) {
		String hql="from TbEnterpriseInfo E where 1=1";
		String stragent="";
		int pagent_id=Integer.parseInt(pagentid);
		int cagent_id=Integer.parseInt(childagentid);
		
		if(r_id == 3 || r_id == 4  ){//wanglei 企业用户
			hql+= " and E.Ep_Id =" + ep_id;
		}else{
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
		}
			hql+=stragent+" order by E.Ep_Name";
		try {
			List listepinfo = sess.createQuery(hql).list();
			if (listepinfo.size()>0) {
				return listepinfo;
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
	
	public void delete(Session sess, String epid) {
		try {
			sess.createQuery("delete from TbEnterpriseInfo ep where ep.Ep_Id="+epid).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sess != null) {
//				sess.close();
			}
		}

	}
	/**
	    * 根据企业名称查询信息  zr
	    */
		public TbEnterpriseInfo getEp_Byepname(Session sess, String ep_name) {
			try{
				
				List q=sess.createQuery("from TbEnterpriseInfo where Ep_Name='"+ep_name+"'").list();
				
				if(q!=null&&q.size()>0){
					return (TbEnterpriseInfo)q.get(0);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(sess!=null){
					//sess.close();
				}
			}
			return null;
		}
		/**
		 * 根据用户单位ID查询所属的代理商
		 */
		
		public TbAgentInfo getAgent_ByEpid(Session sess,String epid){
             try{
				List q=sess.createQuery("from TbAgentInfo a where a.Agent_Id=(select e.Agent_Id from TbEnterpriseInfo e where e.Ep_Id='"+epid+"')").list();
				
				if(q!=null&&q.size()>0){
					return (TbAgentInfo)q.get(0);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(sess!=null){
					//sess.close();
				}
			}
			return null;
			
		}
		
		/**
		    * 根据企业名称查询信息  level
		    */
			public TbEnterpriseInfo getEp_Byepname(Session sess,String ep_id, String ep_name) {
				try{
					List q=sess.createQuery("from TbEnterpriseInfo where Ep_Name='"+ep_name+"' and Ep_Id!="+ep_id).list();
					if(q!=null&&q.size()>0){
						return (TbEnterpriseInfo)q.get(0);
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(sess!=null){
						//sess.close();
					}
				}
				return null;
			}

		public List getEpInfo(Session sess, String epid) {
			try {
				List ep = sess.createQuery("from TbEnterpriseInfo a where a.Ep_Id="+epid).list();
				if (ep != null && ep.size() > 0) {
					return ep;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (sess != null) {
//					sess.close();
				}
			}
			return null;
		}
		
		
		/**
		 * 查询指定代理商下的所有企业   
		 * @param sess
		 * @param agentid
		 * @return
		 */
		public List getEpInfo_byagentid(Session sess,String agent) {
			String hql="from TbEnterpriseInfo E where 1=1 ";
			String stragent="";
			int agentid=Integer.parseInt(agent);
            if(agentid!=0){
            	stragent=" and E.Agent_Id="+agentid;
            }
			
			hql+=stragent+" order by E.Ep_Name";
			try {
				List listepinfo = sess.createQuery(hql).list();
				if (listepinfo.size()>0) {
					return listepinfo;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (sess != null) {
//					sess.close();
				}
			}
			return null;
		}

		@Override
		public void updateInfo(Session sess, TbEnterpriseInfo ep) {
			// TODO Auto-generated method stub
			try {
				sess.saveOrUpdate(ep);
				sess.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (sess != null) {
//					sess.close();
				}
			}
		}
}