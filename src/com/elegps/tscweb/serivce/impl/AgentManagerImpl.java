package com.elegps.tscweb.serivce.impl;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.elegps.tscweb.comm.HibernateUtil;
import com.elegps.tscweb.dao.AgentDaoFactory;
import com.elegps.tscweb.dao.impl.AgentDaoHibernate;
import com.elegps.tscweb.exception.MessageException;
import com.elegps.tscweb.model.TbAgentInfo;
import com.elegps.tscweb.serivce.AgentManager;

public class AgentManagerImpl implements AgentManager {
	
	private AgentDaoFactory agentdao;

    public AgentManagerImpl() throws MessageException {
		try {
			agentdao = new AgentDaoHibernate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MessageException("初始化业务逻辑组件出现异常...");
		}

	}

	public int getAgent_SearchCount(String agent_name,String agent_type) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			Integer count = agentdao.getAgent_SearchCount(sess,agent_name,agent_type);
			if (count != null) {
				tx.commit();
				return count;
			} else {
				tx.commit();
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return 0;
	}

	public int getPageCount(int count, int pageSize) {
			return (count + pageSize - 1) / pageSize;
	}

	public List getTbAgentInfoby_name(int pageNo, int pageSize,String agent_type,
			String agent_name) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = agentdao.getTbAgentInfo_ByPage(sess, pageNo, pageSize,agent_type,
					agent_name);
			if (ml != null && ml.size() > 0) {
				tx.commit();
				return ml;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	public TbAgentInfo getTbAgentinfo_byagentid(String agentId) {
		Transaction tx=null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbAgentInfo agentinfo=agentdao.get(sess, agentId);
			if(agentinfo!=null){
				tx.commit();
				return agentinfo;
			}else{
				tx.commit();
				return null;
			}			
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}
	/**
	 * 查询所有一级代理商(代理商父ID为0的)  zr
	 */
	public List getParentAgent(int agent_id,int r_id) {
		Transaction tx=null;
		try{
			Session sess=HibernateUtil.currentSession();
			tx=sess.beginTransaction();
			TbAgentInfo agent=new TbAgentInfo();
			agentdao=new AgentDaoHibernate();
			List list=agentdao.getAgentByagentpid(sess, agent_id, r_id);
			if(list!=null){
				tx.commit();
				return list;
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				e.printStackTrace();
			}
		}finally{
			HibernateUtil.closeSession();
		}
		
		return null;
	}

	/**
	 * 添加代理商信息 zr
	 */
	public String saveagent(String agentname,
			String agentaddress, String agenttel,String agenttel1,String agentemail,
			String agenturl, String agentman,String agentman1, String agentqq, String agentpid,
			String agentremark) {
		Transaction tx=null;
		String ret=null;
		try{
			Session session=HibernateUtil.currentSession();
			tx=session.beginTransaction();
			TbAgentInfo agentinfo=new TbAgentInfo();
			agentinfo.setAgent_Name(agentname);
			agentinfo.setAgent_Address(agentaddress);
			agentinfo.setAgent_Tel(agenttel);
			agentinfo.setAgent_Tel1(agenttel1);
			agentinfo.setAgent_Email(agentemail);
			agentinfo.setAgent_URL(agenturl);
			agentinfo.setAgent_Man(agentman);
			agentinfo.setAgent_Man1(agentman1);
			agentinfo.setAgent_QQ(agentqq);
			agentinfo.setAgent_PId(Integer.parseInt(agentpid));
			agentinfo.setAgent_Remark(agentremark);
			agentinfo.setAgent_Money("0.00");
			ret=agentdao.save(session, agentinfo);
			tx.commit();
			return ret;
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				e.printStackTrace();
			}
		}finally{
			HibernateUtil.closeSession();
		}
		return "";
	}
    /**
     * 修改代理商信息,不能修改与所属上一级代理商的关系 zr
     */
	public String updateagent(String agentid,String agentname,String agentaddress, String agenttel,String agenttel1,
			String agentemail, String agenturl, String agentman,String agentman1,
			String agentqq, String agentremark) {
		Transaction tx=null;
		try{
			Session session=HibernateUtil.currentSession();
			tx=session.beginTransaction();
			TbAgentInfo agent=agentdao.getagent_byname(session, agentid, agentname);
			if(agent!=null){
				tx.commit();
				return null;
			}
			TbAgentInfo infolist=(TbAgentInfo)agentdao.get(session, agentid);
			if(infolist!=null){
				infolist.setAgent_Name(agentname);
				infolist.setAgent_Address(agentaddress);
				infolist.setAgent_Tel(agenttel);
				infolist.setAgent_Tel1(agenttel1);
				infolist.setAgent_Email(agentemail);
				infolist.setAgent_URL(agenturl);
				infolist.setAgent_Man(agentman);
				infolist.setAgent_Man1(agentman1);
				infolist.setAgent_QQ(agentqq);
				//infolist.setAgent_PId(Integer.parseInt(agentpid));
				infolist.setAgent_Remark(agentremark);
				session.update(infolist);
				tx.commit();
				
				return infolist.getAgent_Id().toString();
			}else{
				tx.commit();
				return null;
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				e.printStackTrace();
			}
		}
		finally{
			HibernateUtil.closeSession();
		}
		return null;
	}

	/**
	 * 根据代理商ID查询代理商信息  zr
	 */
	public TbAgentInfo getAgent_ByAgentID(String agentid) {
		Transaction tx=null;
		try{
			Session session=HibernateUtil.currentSession();
			tx=session.beginTransaction();
			TbAgentInfo info=(TbAgentInfo)agentdao.get(session, agentid);
		    if(info!=null){
		    	tx.commit();
		    	return info;
		    }else{
		    	return null;
		    }
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		
		return null;
	}

	/**
	 * 查询所有代理商 zr
	 */
	public List getAll_Agent() {
		Transaction tx=null;
		try{
			Session sess=HibernateUtil.currentSession();
			tx=sess.beginTransaction();
			List list=agentdao.getAllAgent(sess);
			if(list!=null){
				tx.commit();
				return list;
			}
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	/**
	 * 根据代理商ID删除代理商
	 * @return
	 */
	public boolean deleteagentById(String[] agentid){
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			for (int i = 0; i < agentid.length; i++) {
				String aid = agentid[i];
				agentdao.delete(sess, aid);
			}
			
			tx.commit();
			return true;
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return false;
}
	/**
	 * 根据ID获得代理商名称
	 */
	public TbAgentInfo getAgentName(String id){
		Transaction tx=null;
		try{
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbAgentInfo agentname=(TbAgentInfo)agentdao.getName_byid(sess, id);
			if(agentname!=null){
				return agentname;
			}
			
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				e.printStackTrace();
			}
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}
	/**
	 * 根据代理商名称查询代理商信息 zr
	 * @param agentname
	 * @return
	 */
	public TbAgentInfo getAgentByName(String agentname){
		Transaction tx=null;
		try{
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			TbAgentInfo names=(TbAgentInfo)agentdao.getAgent_Byagentname(sess, agentname);
			if(names!=null){
				return names;
			}
			
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				e.printStackTrace();
			}
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}

	public List getChildAgentByParamentid(String paramentid) {
		Transaction tx=null;
		List list=null;
		try{
			Session sess=HibernateUtil.currentSession();
			tx=sess.beginTransaction();
			List allagent=agentdao.getChildAgentByParamentid(sess,paramentid);
			if(allagent!=null){
				tx.commit();
				return allagent;
			}
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}

	public int getAgenttype(String agent_Id) {
		Transaction tx=null;
		try{
			Session sess=HibernateUtil.currentSession();
			tx=sess.beginTransaction();
			int agenttype=agentdao.getAgentType(sess,agent_Id);
		    return agenttype;
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return 0;
	}

	@Override
	public List getParentAgentNotIncludeZB() {
		Transaction tx=null;
		try{
			Session sess=HibernateUtil.currentSession();
			tx=sess.beginTransaction();
			TbAgentInfo agent=new TbAgentInfo();
			agentdao=new AgentDaoHibernate();
			List list=agentdao.getAgentNotIncludeZBByagentpid(sess);
			if(list!=null){
				tx.commit();
				return list;
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				e.printStackTrace();
			}
		}finally{
			HibernateUtil.closeSession();
		}
		
		return null;
	}

	@Override
	public Boolean updateAgent(TbAgentInfo aInfo) {
		Transaction tx=null;
		try{
			Session session=HibernateUtil.currentSession();
			tx=session.beginTransaction();
			if(aInfo!=null){
				
				agentdao.update(session, aInfo);
				tx.commit();
				
				return true;
			}else{
				tx.commit();
				return false;
			}
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
				e.printStackTrace();
			}
		}
		finally{
			HibernateUtil.closeSession();
		}
		return false;
	}

	@Override
	public List getChildAgentByAId(String agent) {
		Transaction tx=null;
		List list=null;
		try{
			Session sess=HibernateUtil.currentSession();
			tx=sess.beginTransaction();
			List allagent=agentdao.getChildAgentByAId(sess,agent);
			if(allagent!=null){
				tx.commit();
				return allagent;
			}
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		return null;
	}

	@Override
	public int getAgent_SearchCount(String agentName, String agentOne, int pid) {
			Transaction tx = null;
			try {
				Session sess = HibernateUtil.currentSession();
				tx = sess.beginTransaction();
				Integer count = agentdao.getAgent_SearchCount(sess,agentName,agentOne,pid);
				if (count != null) {
					tx.commit();
					return count;
				} else {
					tx.commit();
					return 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				HibernateUtil.closeSession();
			}
			return 0;
	}

	@Override
	public List getTbAgentInfoby_name(int pageNo, int pageSize,
			String agentName, String agentType, int pid) {
		Transaction tx = null;
		try {
			Session sess = HibernateUtil.currentSession();
			tx = sess.beginTransaction();
			List ml = agentdao.getTbAgentInfo_ByPage(sess, pageNo, pageSize,agentName,
					agentType,pid);
			if (ml != null && ml.size() > 0) {
				tx.commit();
				return ml;
			} else {
				tx.commit();
				return null;
			}
		} catch (Exception e) {
			if (null != tx)
				tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return null;
	}

	
}
