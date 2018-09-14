package com.elegps.tscweb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.elegps.tscweb.dao.AgentDaoFactory;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.model.TbEnterpriseInfo;
import com.elegps.tscweb.model.TbGrpInfo;
import com.elegps.tscweb.model.TbMenuInfo;
import com.elegps.tscweb.model.TbMsInfo;
import com.elegps.tscweb.model.TbUserInfo;
import com.elegps.tscweb.serivce.impl.EnterPriseManagerImpl;

public class AgentDaoHibernate implements AgentDaoFactory {

	public Integer getAgent_SearchCount(Session sess, String agent_name,String agent_type) {
			String hql = "select count(a.Agent_Name) from TbAgentInfo a  where a.Agent_Name like '%"+agent_name+"%'"+" and a.Agent_Id!=-1";
			if(agent_type.equals("-1")){   //总部
			}else{
				hql+=" and a.Agent_PId="+agent_type;
			}
			try {
				Object obj = sess.createQuery(hql).uniqueResult();
				if (obj != null) {
					int agentcount = (Integer) obj;
					return agentcount;
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

	public List getTbAgentInfo_ByPage(Session sess, int pageNo, int pageSize,String agent_name,
			String agent_type) {
		String hql="from TbAgentInfo a where a.Agent_Name like '%"+agent_name+"%'"+" and a.Agent_Id!=-1";
		if(agent_type.equals("-1")){   //代表总部  可以看到全部代理商
			
		}else{
			hql+=" and a.Agent_PId="+agent_type;
		}
		hql+=" order by a.Agent_PId";
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

	public TbAgentInfo get(Session sess, String agentId) {
		try {
			List agent = sess.createQuery("from TbAgentInfo a where a.Agent_Id="+agentId).list();
			if (agent != null && agent.size() > 0) {
				return (TbAgentInfo) agent.get(0);
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
    * 代理商信息添加 zr
    */
	public String save(Session sess, TbAgentInfo agentinfo) {
		// TODO Auto-generated method stub
		try{
			sess.save(agentinfo);
			sess.flush();
			return agentinfo.getAgent_Id().toString();
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
	 * 代理商信息修改  zr
	 */
	public void update(Session sess, TbAgentInfo agentinfo) {
		// TODO Auto-generated method stub
		try{
			sess.update(agentinfo);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sess!=null){
				//sess.close();
			}
		}
	}

	/**
	    * 根据代理商名称查询代理商信息  zr
	    */
		public TbAgentInfo getAgent_Byagentname(Session sess, String agent_name) {
			try{
				
				List q=sess.createQuery("from TbAgentInfo where Agent_Name='"+agent_name+"'").list();
				
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
 * 查询所有代理商 zr
 */
public List getAllAgent(Session sess) {
	// TODO Auto-generated method stub
	String sql="from TbAgentInfo";
	try{
		List list=sess.createQuery(sql).list();
		 if(list!=null){
				return list;
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
 * 根据代理商ID删除代理商 zr
 */
public void delete(Session sess,String id){
	try{
		EnterPriseDaoHibernate epdao = new EnterPriseDaoHibernate();
		GrpDaoHibernate grpdao = new GrpDaoHibernate();
		MsDaoHibernate msdao=new MsDaoHibernate();
		List list=sess.createQuery("from TbEnterpriseInfo ep where ep.Agent_Id in(select A.Agent_Id from TbAgentInfo A where A.Agent_Id="+id+" or A.Agent_PId="+id+")").list();
		String[] tt = new String[list.size()]; 
		if (list != null && list.size() > 0) {
			for (int i=0;i<list.size();i++) {
				TbEnterpriseInfo me = (TbEnterpriseInfo) list.get(i);
				tt[i]=me.getEp_Id().toString();
				 epdao.delete(sess, me.getEp_Id().toString());
				 grpdao.deleteGrpInfoByEp_id(sess, me.getEp_Id().toString());
				 msdao.deleteMsInfoByEp_id(sess,me.getEp_Id().toString());
			}
		}
		sess.createQuery("delete from TbAgentInfo t where t.Agent_Id="+id+" or t.Agent_PId="+id).executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(sess!=null){
			//sess.close();
		}
	}	
}
/**
 * 根据代理商名称加载信息
 * @param sess
 * @param menu_name
 * @return
 */
public TbAgentInfo getagent_byname(Session sess,String agentid, String agent_name) {
	try {
		List agents = sess.createQuery("from TbAgentInfo a where a.Agent_Name='"+agent_name+"' and Agent_Id!="+agentid).list();
		if (agents != null && agents.size() > 0) {
			return (TbAgentInfo) agents.get(0);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (sess != null) {
//			sess.close();
		}
	}
	return null;
}
/**
 * 根据ID查询父级代理商名称
 */
public TbAgentInfo getName_byid(Session sess,String id){
	try{
		String sql="from TbAgentInfo where Agent_Id = (select Agent_PId from TbAgentInfo where Agent_Id='"+id+"')";
		List agentname=sess.createQuery(sql).list();
	    if(agentname!=null&&agentname.size()>0){
	    	return (TbAgentInfo)agentname.get(0);
	    }
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		//sess.close();
	}
	return null;
}


/**
 * 取得所有的一级代理商 zr
 */
public List getAgentByagentpid(Session sess,int agent_id,int r_id) {
	try{
		List list;
		String sql="from TbAgentInfo a where a.Agent_PId=0";
		
		//wanglei 企业用户,代理商
		if(r_id == 3 || r_id == 2 || r_id == 4){
			sql += " and a.Agent_Id = " + agent_id;
		}	
		sql += " order by a.Agent_Id ";
		Query q=sess.createQuery(sql);
		list=q.list();
		if(list!=null){
			return list;
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		if(sess!=null){
			//sess.close();
		}
		
	}
	
	return null;
	
}

public List getChildAgentByParamentid(Session sess, String paramentid) {
	try{
		List list;
		String sql="from TbAgentInfo a where  a.Agent_PId="+paramentid;
		Query q=sess.createQuery(sql);
		list=q.list();
		if(list!=null){
			return list;
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
		if(sess!=null){
			//sess.close();
		}
		
	}
	return null;
}

public int getAgentType(Session sess, String agent_Id) {
	try{
		
		List q=sess.createQuery("from TbAgentInfo A where A.Agent_Id="+agent_Id).list();
		
		if(q!=null&&q.size()>0){
			TbAgentInfo agent=(TbAgentInfo)q.get(0);
			return agent.getAgent_PId();
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(sess!=null){
			//sess.close();
		}
	}
	return 0;
}

	@Override
	public List getAgentNotIncludeZBByagentpid(Session sess) {
		try{
			List list;
			String sql="from TbAgentInfo a where a.Agent_PId=0 and a.Agent_Id!=-1 order by a.Agent_Id";
			Query q=sess.createQuery(sql);
			list=q.list();
			if(list!=null){
				return list;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(sess!=null){
				//sess.close();
			}
			
		}
		
		return null;
	}

	@Override
	public List getChildAgentByAId(Session sess, String agent) {
		try{
			List list;
			String sql="from TbAgentInfo a where  a.Agent_Id="+agent;
			Query q=sess.createQuery(sql);
			list=q.list();
			if(list!=null){
				return list;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(sess!=null){
				//sess.close();
			}
			
		}
		return null;
	}

	@Override
	public Integer getAgent_SearchCount(Session sess, String agentName,
			String agentType, int pid) {
		String hql = "select count(a.Agent_Name) from TbAgentInfo a  where a.Agent_Name like '%"+agentName+"%'"+" and a.Agent_Id!=-1";
			hql+=" and a.Agent_PId="+pid;
		try {
			Object obj = sess.createQuery(hql).uniqueResult();
			if (obj != null) {
				int agentcount = (Integer) obj;
				return agentcount;
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
	public List getTbAgentInfo_ByPage(Session sess, int pageNo, int pageSize,
			String agentName, String agentType, int pid) {
		String hql="from TbAgentInfo a where a.Agent_Id!=-1";
		if(!agentName.equals("")){
			hql += "a.Agent_Name like '%"+agentName+"%'";
		}
			hql+=" and a.Agent_PId="+ pid +" order by a.Agent_PId";
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

}
